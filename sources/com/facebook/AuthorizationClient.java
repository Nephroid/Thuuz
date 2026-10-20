package com.facebook;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.facebook.Request;
import com.facebook.RequestBatch;
import com.facebook.Session;
import com.facebook.android.R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.PlatformServiceClient;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AuthorizationClient implements Serializable {
    static final String EVENT_EXTRAS_DEFAULT_AUDIENCE = "default_audience";
    static final String EVENT_EXTRAS_IS_LEGACY = "is_legacy";
    static final String EVENT_EXTRAS_LOGIN_BEHAVIOR = "login_behavior";
    static final String EVENT_EXTRAS_MISSING_INTERNET_PERMISSION = "no_internet_permission";
    static final String EVENT_EXTRAS_NEW_PERMISSIONS = "new_permissions";
    static final String EVENT_EXTRAS_NOT_TRIED = "not_tried";
    static final String EVENT_EXTRAS_PERMISSIONS = "permissions";
    static final String EVENT_EXTRAS_REQUEST_CODE = "request_code";
    static final String EVENT_EXTRAS_TRY_LEGACY = "try_legacy";
    static final String EVENT_EXTRAS_TRY_LOGIN_ACTIVITY = "try_login_activity";
    static final String EVENT_NAME_LOGIN_COMPLETE = "fb_mobile_login_complete";
    private static final String EVENT_NAME_LOGIN_METHOD_COMPLETE = "fb_mobile_login_method_complete";
    private static final String EVENT_NAME_LOGIN_METHOD_START = "fb_mobile_login_method_start";
    static final String EVENT_NAME_LOGIN_START = "fb_mobile_login_start";
    static final String EVENT_PARAM_AUTH_LOGGER_ID = "0_auth_logger_id";
    static final String EVENT_PARAM_ERROR_CODE = "4_error_code";
    static final String EVENT_PARAM_ERROR_MESSAGE = "5_error_message";
    static final String EVENT_PARAM_EXTRAS = "6_extras";
    static final String EVENT_PARAM_LOGIN_RESULT = "2_result";
    static final String EVENT_PARAM_METHOD = "3_method";
    private static final String EVENT_PARAM_METHOD_RESULT_SKIPPED = "skipped";
    static final String EVENT_PARAM_TIMESTAMP = "1_timestamp_ms";
    private static final String TAG = "Facebook-AuthorizationClient";
    private static final String WEB_VIEW_AUTH_HANDLER_STORE = "com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY";
    private static final String WEB_VIEW_AUTH_HANDLER_TOKEN_KEY = "TOKEN";
    private static final long serialVersionUID = 1;
    private transient AppEventsLogger appEventsLogger;
    transient BackgroundProcessingListener backgroundProcessingListener;
    transient boolean checkedInternetPermission;
    transient Context context;
    AuthHandler currentHandler;
    List<AuthHandler> handlersToTry;
    Map<String, String> loggingExtras;
    transient OnCompletedListener onCompletedListener;
    AuthorizationRequest pendingRequest;
    transient StartActivityDelegate startActivityDelegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface BackgroundProcessingListener {
        void onBackgroundProcessingStarted();

        void onBackgroundProcessingStopped();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface OnCompletedListener {
        void onCompleted(Result result);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface StartActivityDelegate {
        Activity getActivityContext();

        void startActivityForResult(Intent intent, int i);
    }

    static /* synthetic */ String access$100() {
        return getE2E();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setContext(Context context) {
        this.context = context;
        this.startActivityDelegate = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setContext(final Activity activity) {
        this.context = activity;
        this.startActivityDelegate = new StartActivityDelegate() { // from class: com.facebook.AuthorizationClient.1
            @Override // com.facebook.AuthorizationClient.StartActivityDelegate
            public void startActivityForResult(Intent intent, int i) {
                activity.startActivityForResult(intent, i);
            }

            @Override // com.facebook.AuthorizationClient.StartActivityDelegate
            public Activity getActivityContext() {
                return activity;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startOrContinueAuth(AuthorizationRequest authorizationRequest) {
        if (getInProgress()) {
            continueAuth();
        } else {
            authorize(authorizationRequest);
        }
    }

    void authorize(AuthorizationRequest authorizationRequest) {
        if (authorizationRequest != null) {
            if (this.pendingRequest != null) {
                throw new FacebookException("Attempted to authorize while a request is pending.");
            }
            if (!authorizationRequest.needsNewTokenValidation() || checkInternetPermission()) {
                this.pendingRequest = authorizationRequest;
                this.handlersToTry = getHandlerTypes(authorizationRequest);
                tryNextHandler();
            }
        }
    }

    void continueAuth() {
        if (this.pendingRequest == null || this.currentHandler == null) {
            throw new FacebookException("Attempted to continue authorization without a pending request.");
        }
        if (this.currentHandler.needsRestart()) {
            this.currentHandler.cancel();
            tryCurrentHandler();
        }
    }

    boolean getInProgress() {
        return (this.pendingRequest == null || this.currentHandler == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancelCurrentHandler() {
        if (this.currentHandler != null) {
            this.currentHandler.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (this.pendingRequest == null || i != this.pendingRequest.getRequestCode()) {
            return false;
        }
        return this.currentHandler.onActivityResult(i, i2, intent);
    }

    private List<AuthHandler> getHandlerTypes(AuthorizationRequest authorizationRequest) {
        ArrayList arrayList = new ArrayList();
        SessionLoginBehavior loginBehavior = authorizationRequest.getLoginBehavior();
        if (loginBehavior.allowsKatanaAuth()) {
            if (!authorizationRequest.isLegacy()) {
                arrayList.add(new GetTokenAuthHandler());
            }
            arrayList.add(new KatanaProxyAuthHandler());
        }
        if (loginBehavior.allowsWebViewAuth()) {
            arrayList.add(new WebViewAuthHandler());
        }
        return arrayList;
    }

    boolean checkInternetPermission() {
        if (this.checkedInternetPermission) {
            return true;
        }
        if (checkPermission("android.permission.INTERNET") != 0) {
            complete(Result.createErrorResult(this.pendingRequest, this.context.getString(R.string.com_facebook_internet_permission_error_title), this.context.getString(R.string.com_facebook_internet_permission_error_message)));
            return false;
        }
        this.checkedInternetPermission = true;
        return true;
    }

    void tryNextHandler() {
        if (this.currentHandler != null) {
            logAuthorizationMethodComplete(this.currentHandler.getNameForLogging(), EVENT_PARAM_METHOD_RESULT_SKIPPED, null, null, this.currentHandler.methodLoggingExtras);
        }
        while (this.handlersToTry != null && !this.handlersToTry.isEmpty()) {
            this.currentHandler = this.handlersToTry.remove(0);
            if (tryCurrentHandler()) {
                return;
            }
        }
        if (this.pendingRequest != null) {
            completeWithFailure();
        }
    }

    private void completeWithFailure() {
        complete(Result.createErrorResult(this.pendingRequest, "Login attempt failed.", null));
    }

    private void addLoggingExtra(String str, String str2, boolean z) {
        if (this.loggingExtras == null) {
            this.loggingExtras = new HashMap();
        }
        if (this.loggingExtras.containsKey(str) && z) {
            str2 = this.loggingExtras.get(str) + "," + str2;
        }
        this.loggingExtras.put(str, str2);
    }

    boolean tryCurrentHandler() {
        boolean z = false;
        if (this.currentHandler.needsInternetPermission() && !checkInternetPermission()) {
            addLoggingExtra(EVENT_EXTRAS_MISSING_INTERNET_PERMISSION, AppEventsConstants.EVENT_PARAM_VALUE_YES, false);
        } else {
            z = this.currentHandler.tryAuthorize(this.pendingRequest);
            if (z) {
                logAuthorizationMethodStart(this.currentHandler.getNameForLogging());
            } else {
                addLoggingExtra(EVENT_EXTRAS_NOT_TRIED, this.currentHandler.getNameForLogging(), true);
            }
        }
        return z;
    }

    void completeAndValidate(Result result) {
        if (result.token != null && this.pendingRequest.needsNewTokenValidation()) {
            validateSameFbidAndFinish(result);
        } else {
            complete(result);
        }
    }

    void complete(Result result) {
        if (this.currentHandler != null) {
            logAuthorizationMethodComplete(this.currentHandler.getNameForLogging(), result, this.currentHandler.methodLoggingExtras);
        }
        if (this.loggingExtras != null) {
            result.loggingExtras = this.loggingExtras;
        }
        this.handlersToTry = null;
        this.currentHandler = null;
        this.pendingRequest = null;
        this.loggingExtras = null;
        notifyOnCompleteListener(result);
    }

    OnCompletedListener getOnCompletedListener() {
        return this.onCompletedListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnCompletedListener(OnCompletedListener onCompletedListener) {
        this.onCompletedListener = onCompletedListener;
    }

    BackgroundProcessingListener getBackgroundProcessingListener() {
        return this.backgroundProcessingListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBackgroundProcessingListener(BackgroundProcessingListener backgroundProcessingListener) {
        this.backgroundProcessingListener = backgroundProcessingListener;
    }

    StartActivityDelegate getStartActivityDelegate() {
        if (this.startActivityDelegate != null) {
            return this.startActivityDelegate;
        }
        if (this.pendingRequest != null) {
            return new StartActivityDelegate() { // from class: com.facebook.AuthorizationClient.2
                @Override // com.facebook.AuthorizationClient.StartActivityDelegate
                public void startActivityForResult(Intent intent, int i) {
                    AuthorizationClient.this.pendingRequest.getStartActivityDelegate().startActivityForResult(intent, i);
                }

                @Override // com.facebook.AuthorizationClient.StartActivityDelegate
                public Activity getActivityContext() {
                    return AuthorizationClient.this.pendingRequest.getStartActivityDelegate().getActivityContext();
                }
            };
        }
        return null;
    }

    int checkPermission(String str) {
        return this.context.checkCallingOrSelfPermission(str);
    }

    void validateSameFbidAndFinish(Result result) {
        if (result.token == null) {
            throw new FacebookException("Can't validate without a token");
        }
        RequestBatch createReauthValidationBatch = createReauthValidationBatch(result);
        notifyBackgroundProcessingStart();
        createReauthValidationBatch.executeAsync();
    }

    RequestBatch createReauthValidationBatch(final Result result) {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        String token = result.token.getToken();
        Request.Callback callback = new Request.Callback() { // from class: com.facebook.AuthorizationClient.3
            @Override // com.facebook.Request.Callback
            public void onCompleted(Response response) {
                try {
                    GraphUser graphUser = (GraphUser) response.getGraphObjectAs(GraphUser.class);
                    if (graphUser != null) {
                        arrayList.add(graphUser.getId());
                    }
                } catch (Exception e) {
                }
            }
        };
        String previousAccessToken = this.pendingRequest.getPreviousAccessToken();
        Request createGetProfileIdRequest = createGetProfileIdRequest(previousAccessToken);
        createGetProfileIdRequest.setCallback(callback);
        Request createGetProfileIdRequest2 = createGetProfileIdRequest(token);
        createGetProfileIdRequest2.setCallback(callback);
        Request createGetPermissionsRequest = createGetPermissionsRequest(previousAccessToken);
        createGetPermissionsRequest.setCallback(new Request.Callback() { // from class: com.facebook.AuthorizationClient.4
            @Override // com.facebook.Request.Callback
            public void onCompleted(Response response) {
                try {
                    Session.PermissionsPair handlePermissionResponse = Session.handlePermissionResponse(response);
                    if (handlePermissionResponse != null) {
                        arrayList2.addAll(handlePermissionResponse.getGrantedPermissions());
                        arrayList3.addAll(handlePermissionResponse.getDeclinedPermissions());
                    }
                } catch (Exception e) {
                }
            }
        });
        RequestBatch requestBatch = new RequestBatch(createGetProfileIdRequest, createGetProfileIdRequest2, createGetPermissionsRequest);
        requestBatch.setBatchApplicationId(this.pendingRequest.getApplicationId());
        requestBatch.addCallback(new RequestBatch.Callback() { // from class: com.facebook.AuthorizationClient.5
            @Override // com.facebook.RequestBatch.Callback
            public void onBatchCompleted(RequestBatch requestBatch2) {
                Result createErrorResult;
                try {
                    if (arrayList.size() == 2 && arrayList.get(0) != null && arrayList.get(1) != null && ((String) arrayList.get(0)).equals(arrayList.get(1))) {
                        createErrorResult = Result.createTokenResult(AuthorizationClient.this.pendingRequest, AccessToken.createFromTokenWithRefreshedPermissions(result.token, arrayList2, arrayList3));
                    } else {
                        createErrorResult = Result.createErrorResult(AuthorizationClient.this.pendingRequest, "User logged in as different Facebook user.", null);
                    }
                    AuthorizationClient.this.complete(createErrorResult);
                } catch (Exception e) {
                    AuthorizationClient.this.complete(Result.createErrorResult(AuthorizationClient.this.pendingRequest, "Caught exception", e.getMessage()));
                } finally {
                    AuthorizationClient.this.notifyBackgroundProcessingStop();
                }
            }
        });
        return requestBatch;
    }

    Request createGetPermissionsRequest(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("access_token", str);
        return new Request(null, "me/permissions", bundle, HttpMethod.GET, null);
    }

    Request createGetProfileIdRequest(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id");
        bundle.putString("access_token", str);
        return new Request(null, "me", bundle, HttpMethod.GET, null);
    }

    private AppEventsLogger getAppEventsLogger() {
        if (this.appEventsLogger == null || !this.appEventsLogger.getApplicationId().equals(this.pendingRequest.getApplicationId())) {
            this.appEventsLogger = AppEventsLogger.newLogger(this.context, this.pendingRequest.getApplicationId());
        }
        return this.appEventsLogger;
    }

    private void notifyOnCompleteListener(Result result) {
        if (this.onCompletedListener != null) {
            this.onCompletedListener.onCompleted(result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBackgroundProcessingStart() {
        if (this.backgroundProcessingListener != null) {
            this.backgroundProcessingListener.onBackgroundProcessingStarted();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBackgroundProcessingStop() {
        if (this.backgroundProcessingListener != null) {
            this.backgroundProcessingListener.onBackgroundProcessingStopped();
        }
    }

    private void logAuthorizationMethodStart(String str) {
        Bundle newAuthorizationLoggingBundle = newAuthorizationLoggingBundle(this.pendingRequest.getAuthId());
        newAuthorizationLoggingBundle.putLong(EVENT_PARAM_TIMESTAMP, System.currentTimeMillis());
        newAuthorizationLoggingBundle.putString(EVENT_PARAM_METHOD, str);
        getAppEventsLogger().logSdkEvent(EVENT_NAME_LOGIN_METHOD_START, null, newAuthorizationLoggingBundle);
    }

    private void logAuthorizationMethodComplete(String str, Result result, Map<String, String> map) {
        logAuthorizationMethodComplete(str, result.code.getLoggingValue(), result.errorMessage, result.errorCode, map);
    }

    private void logAuthorizationMethodComplete(String str, String str2, String str3, String str4, Map<String, String> map) {
        Bundle newAuthorizationLoggingBundle;
        if (this.pendingRequest == null) {
            newAuthorizationLoggingBundle = newAuthorizationLoggingBundle("");
            newAuthorizationLoggingBundle.putString(EVENT_PARAM_LOGIN_RESULT, Result.Code.ERROR.getLoggingValue());
            newAuthorizationLoggingBundle.putString(EVENT_PARAM_ERROR_MESSAGE, "Unexpected call to logAuthorizationMethodComplete with null pendingRequest.");
        } else {
            newAuthorizationLoggingBundle = newAuthorizationLoggingBundle(this.pendingRequest.getAuthId());
            if (str2 != null) {
                newAuthorizationLoggingBundle.putString(EVENT_PARAM_LOGIN_RESULT, str2);
            }
            if (str3 != null) {
                newAuthorizationLoggingBundle.putString(EVENT_PARAM_ERROR_MESSAGE, str3);
            }
            if (str4 != null) {
                newAuthorizationLoggingBundle.putString(EVENT_PARAM_ERROR_CODE, str4);
            }
            if (map != null && !map.isEmpty()) {
                newAuthorizationLoggingBundle.putString(EVENT_PARAM_EXTRAS, new JSONObject(map).toString());
            }
        }
        newAuthorizationLoggingBundle.putString(EVENT_PARAM_METHOD, str);
        newAuthorizationLoggingBundle.putLong(EVENT_PARAM_TIMESTAMP, System.currentTimeMillis());
        getAppEventsLogger().logSdkEvent(EVENT_NAME_LOGIN_METHOD_COMPLETE, null, newAuthorizationLoggingBundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Bundle newAuthorizationLoggingBundle(String str) {
        Bundle bundle = new Bundle();
        bundle.putLong(EVENT_PARAM_TIMESTAMP, System.currentTimeMillis());
        bundle.putString(EVENT_PARAM_AUTH_LOGGER_ID, str);
        bundle.putString(EVENT_PARAM_METHOD, "");
        bundle.putString(EVENT_PARAM_LOGIN_RESULT, "");
        bundle.putString(EVENT_PARAM_ERROR_MESSAGE, "");
        bundle.putString(EVENT_PARAM_ERROR_CODE, "");
        bundle.putString(EVENT_PARAM_EXTRAS, "");
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class AuthHandler implements Serializable {
        private static final long serialVersionUID = 1;
        Map<String, String> methodLoggingExtras;

        abstract String getNameForLogging();

        abstract boolean tryAuthorize(AuthorizationRequest authorizationRequest);

        AuthHandler() {
        }

        boolean onActivityResult(int i, int i2, Intent intent) {
            return false;
        }

        boolean needsRestart() {
            return false;
        }

        boolean needsInternetPermission() {
            return false;
        }

        void cancel() {
        }

        protected void addLoggingExtra(String str, Object obj) {
            if (this.methodLoggingExtras == null) {
                this.methodLoggingExtras = new HashMap();
            }
            this.methodLoggingExtras.put(str, obj == null ? null : obj.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class WebViewAuthHandler extends AuthHandler {
        private static final long serialVersionUID = 1;
        private String applicationId;
        private String e2e;
        private transient WebDialog loginDialog;

        WebViewAuthHandler() {
            super();
        }

        @Override // com.facebook.AuthorizationClient.AuthHandler
        String getNameForLogging() {
            return "web_view";
        }

        @Override // com.facebook.AuthorizationClient.AuthHandler
        boolean needsRestart() {
            return true;
        }

        @Override // com.facebook.AuthorizationClient.AuthHandler
        boolean needsInternetPermission() {
            return true;
        }

        @Override // com.facebook.AuthorizationClient.AuthHandler
        void cancel() {
            if (this.loginDialog != null) {
                this.loginDialog.setOnCompleteListener(null);
                this.loginDialog.dismiss();
                this.loginDialog = null;
            }
        }

        @Override // com.facebook.AuthorizationClient.AuthHandler
        boolean tryAuthorize(final AuthorizationRequest authorizationRequest) {
            this.applicationId = authorizationRequest.getApplicationId();
            Bundle bundle = new Bundle();
            if (!Utility.isNullOrEmpty(authorizationRequest.getPermissions())) {
                String join = TextUtils.join(",", authorizationRequest.getPermissions());
                bundle.putString("scope", join);
                addLoggingExtra("scope", join);
            }
            bundle.putString("default_audience", authorizationRequest.getDefaultAudience().getNativeProtocolAudience());
            String previousAccessToken = authorizationRequest.getPreviousAccessToken();
            if (!Utility.isNullOrEmpty(previousAccessToken) && previousAccessToken.equals(loadCookieToken())) {
                bundle.putString("access_token", previousAccessToken);
                addLoggingExtra("access_token", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            } else {
                Utility.clearFacebookCookies(AuthorizationClient.this.context);
                addLoggingExtra("access_token", AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            WebDialog.OnCompleteListener onCompleteListener = new WebDialog.OnCompleteListener() { // from class: com.facebook.AuthorizationClient.WebViewAuthHandler.1
                @Override // com.facebook.widget.WebDialog.OnCompleteListener
                public void onComplete(Bundle bundle2, FacebookException facebookException) {
                    WebViewAuthHandler.this.onWebDialogComplete(authorizationRequest, bundle2, facebookException);
                }
            };
            this.e2e = AuthorizationClient.access$100();
            addLoggingExtra("e2e", this.e2e);
            this.loginDialog = new AuthDialogBuilder(AuthorizationClient.this.getStartActivityDelegate().getActivityContext(), this.applicationId, bundle).setE2E(this.e2e).setIsRerequest(authorizationRequest.isRerequest()).setOnCompleteListener(onCompleteListener).build();
            this.loginDialog.show();
            return true;
        }

        void onWebDialogComplete(AuthorizationRequest authorizationRequest, Bundle bundle, FacebookException facebookException) {
            String str;
            Result createErrorResult;
            if (bundle != null) {
                if (bundle.containsKey("e2e")) {
                    this.e2e = bundle.getString("e2e");
                }
                AccessToken createFromWebBundle = AccessToken.createFromWebBundle(authorizationRequest.getPermissions(), bundle, AccessTokenSource.WEB_VIEW);
                createErrorResult = Result.createTokenResult(AuthorizationClient.this.pendingRequest, createFromWebBundle);
                CookieSyncManager.createInstance(AuthorizationClient.this.context).sync();
                saveCookieToken(createFromWebBundle.getToken());
            } else if (facebookException instanceof FacebookOperationCanceledException) {
                createErrorResult = Result.createCancelResult(AuthorizationClient.this.pendingRequest, "User canceled log in.");
            } else {
                this.e2e = null;
                String message = facebookException.getMessage();
                if (facebookException instanceof FacebookServiceException) {
                    FacebookRequestError requestError = ((FacebookServiceException) facebookException).getRequestError();
                    str = String.format("%d", Integer.valueOf(requestError.getErrorCode()));
                    message = requestError.toString();
                } else {
                    str = null;
                }
                createErrorResult = Result.createErrorResult(AuthorizationClient.this.pendingRequest, null, message, str);
            }
            if (!Utility.isNullOrEmpty(this.e2e)) {
                AuthorizationClient.this.logWebLoginCompleted(this.applicationId, this.e2e);
            }
            AuthorizationClient.this.completeAndValidate(createErrorResult);
        }

        private void saveCookieToken(String str) {
            AuthorizationClient.this.getStartActivityDelegate().getActivityContext().getSharedPreferences(AuthorizationClient.WEB_VIEW_AUTH_HANDLER_STORE, 0).edit().putString(AuthorizationClient.WEB_VIEW_AUTH_HANDLER_TOKEN_KEY, str).apply();
        }

        private String loadCookieToken() {
            return AuthorizationClient.this.getStartActivityDelegate().getActivityContext().getSharedPreferences(AuthorizationClient.WEB_VIEW_AUTH_HANDLER_STORE, 0).getString(AuthorizationClient.WEB_VIEW_AUTH_HANDLER_TOKEN_KEY, "");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class GetTokenAuthHandler extends AuthHandler {
        private static final long serialVersionUID = 1;
        private transient GetTokenClient getTokenClient;

        GetTokenAuthHandler() {
            super();
        }

        @Override // com.facebook.AuthorizationClient.AuthHandler
        String getNameForLogging() {
            return "get_token";
        }

        @Override // com.facebook.AuthorizationClient.AuthHandler
        void cancel() {
            if (this.getTokenClient != null) {
                this.getTokenClient.cancel();
                this.getTokenClient = null;
            }
        }

        @Override // com.facebook.AuthorizationClient.AuthHandler
        boolean needsRestart() {
            return this.getTokenClient == null;
        }

        @Override // com.facebook.AuthorizationClient.AuthHandler
        boolean tryAuthorize(final AuthorizationRequest authorizationRequest) {
            this.getTokenClient = new GetTokenClient(AuthorizationClient.this.context, authorizationRequest.getApplicationId());
            if (this.getTokenClient.start()) {
                AuthorizationClient.this.notifyBackgroundProcessingStart();
                this.getTokenClient.setCompletedListener(new PlatformServiceClient.CompletedListener() { // from class: com.facebook.AuthorizationClient.GetTokenAuthHandler.1
                    @Override // com.facebook.internal.PlatformServiceClient.CompletedListener
                    public void completed(Bundle bundle) {
                        GetTokenAuthHandler.this.getTokenCompleted(authorizationRequest, bundle);
                    }
                });
                return true;
            }
            return false;
        }

        void getTokenCompleted(AuthorizationRequest authorizationRequest, Bundle bundle) {
            this.getTokenClient = null;
            AuthorizationClient.this.notifyBackgroundProcessingStop();
            if (bundle != null) {
                ArrayList<String> stringArrayList = bundle.getStringArrayList(NativeProtocol.EXTRA_PERMISSIONS);
                List<String> permissions = authorizationRequest.getPermissions();
                if (stringArrayList != null && (permissions == null || stringArrayList.containsAll(permissions))) {
                    AuthorizationClient.this.completeAndValidate(Result.createTokenResult(AuthorizationClient.this.pendingRequest, AccessToken.createFromNativeLogin(bundle, AccessTokenSource.FACEBOOK_APPLICATION_SERVICE)));
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (String str : permissions) {
                    if (!stringArrayList.contains(str)) {
                        arrayList.add(str);
                    }
                }
                if (!arrayList.isEmpty()) {
                    addLoggingExtra(AuthorizationClient.EVENT_EXTRAS_NEW_PERMISSIONS, TextUtils.join(",", arrayList));
                }
                authorizationRequest.setPermissions(arrayList);
            }
            AuthorizationClient.this.tryNextHandler();
        }
    }

    /* loaded from: classes.dex */
    abstract class KatanaAuthHandler extends AuthHandler {
        private static final long serialVersionUID = 1;

        KatanaAuthHandler() {
            super();
        }

        protected boolean tryIntent(Intent intent, int i) {
            if (intent == null) {
                return false;
            }
            try {
                AuthorizationClient.this.getStartActivityDelegate().startActivityForResult(intent, i);
                return true;
            } catch (ActivityNotFoundException e) {
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class KatanaProxyAuthHandler extends KatanaAuthHandler {
        private static final long serialVersionUID = 1;
        private String applicationId;

        KatanaProxyAuthHandler() {
            super();
        }

        @Override // com.facebook.AuthorizationClient.AuthHandler
        String getNameForLogging() {
            return "katana_proxy_auth";
        }

        @Override // com.facebook.AuthorizationClient.AuthHandler
        boolean tryAuthorize(AuthorizationRequest authorizationRequest) {
            this.applicationId = authorizationRequest.getApplicationId();
            String access$100 = AuthorizationClient.access$100();
            Intent createProxyAuthIntent = NativeProtocol.createProxyAuthIntent(AuthorizationClient.this.context, authorizationRequest.getApplicationId(), authorizationRequest.getPermissions(), access$100, authorizationRequest.isRerequest(), authorizationRequest.getDefaultAudience());
            addLoggingExtra("e2e", access$100);
            return tryIntent(createProxyAuthIntent, authorizationRequest.getRequestCode());
        }

        @Override // com.facebook.AuthorizationClient.AuthHandler
        boolean onActivityResult(int i, int i2, Intent intent) {
            Result handleResultOk;
            if (intent == null) {
                handleResultOk = Result.createCancelResult(AuthorizationClient.this.pendingRequest, "Operation canceled");
            } else if (i2 == 0) {
                handleResultOk = Result.createCancelResult(AuthorizationClient.this.pendingRequest, intent.getStringExtra("error"));
            } else if (i2 != -1) {
                handleResultOk = Result.createErrorResult(AuthorizationClient.this.pendingRequest, "Unexpected resultCode from authorization.", null);
            } else {
                handleResultOk = handleResultOk(intent);
            }
            if (handleResultOk != null) {
                AuthorizationClient.this.completeAndValidate(handleResultOk);
                return true;
            }
            AuthorizationClient.this.tryNextHandler();
            return true;
        }

        private Result handleResultOk(Intent intent) {
            Bundle extras = intent.getExtras();
            String string = extras.getString("error");
            if (string == null) {
                string = extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_TYPE);
            }
            String string2 = extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
            String string3 = extras.getString("error_message");
            if (string3 == null) {
                string3 = extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
            }
            String string4 = extras.getString("e2e");
            if (!Utility.isNullOrEmpty(string4)) {
                AuthorizationClient.this.logWebLoginCompleted(this.applicationId, string4);
            }
            if (string == null && string2 == null && string3 == null) {
                return Result.createTokenResult(AuthorizationClient.this.pendingRequest, AccessToken.createFromWebBundle(AuthorizationClient.this.pendingRequest.getPermissions(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB));
            }
            if (ServerProtocol.errorsProxyAuthDisabled.contains(string)) {
                return null;
            }
            if (ServerProtocol.errorsUserCanceled.contains(string)) {
                return Result.createCancelResult(AuthorizationClient.this.pendingRequest, null);
            }
            return Result.createErrorResult(AuthorizationClient.this.pendingRequest, string, string3, string2);
        }
    }

    private static String getE2E() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("init", System.currentTimeMillis());
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logWebLoginCompleted(String str, String str2) {
        AppEventsLogger newLogger = AppEventsLogger.newLogger(this.context, str);
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticsEvents.PARAMETER_WEB_LOGIN_E2E, str2);
        bundle.putLong(AnalyticsEvents.PARAMETER_WEB_LOGIN_SWITCHBACK_TIME, System.currentTimeMillis());
        bundle.putString("app_id", str);
        newLogger.logSdkEvent(AnalyticsEvents.EVENT_WEB_LOGIN_COMPLETE, null, bundle);
    }

    /* loaded from: classes.dex */
    class AuthDialogBuilder extends WebDialog.Builder {
        private static final String OAUTH_DIALOG = "oauth";
        static final String REDIRECT_URI = "fbconnect://success";
        private String e2e;
        private boolean isRerequest;

        public AuthDialogBuilder(Context context, String str, Bundle bundle) {
            super(context, str, OAUTH_DIALOG, bundle);
        }

        public AuthDialogBuilder setE2E(String str) {
            this.e2e = str;
            return this;
        }

        public AuthDialogBuilder setIsRerequest(boolean z) {
            this.isRerequest = z;
            return this;
        }

        @Override // com.facebook.widget.WebDialog.Builder, com.facebook.widget.WebDialog.BuilderBase
        public WebDialog build() {
            Bundle parameters = getParameters();
            parameters.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, "fbconnect://success");
            parameters.putString("client_id", getApplicationId());
            parameters.putString("e2e", this.e2e);
            parameters.putString(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN);
            parameters.putString(ServerProtocol.DIALOG_PARAM_RETURN_SCOPES, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            if (this.isRerequest && !Settings.getPlatformCompatibilityEnabled()) {
                parameters.putString(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, ServerProtocol.DIALOG_REREQUEST_AUTH_TYPE);
            }
            return new WebDialog(getContext(), OAUTH_DIALOG, parameters, getTheme(), getListener());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class AuthorizationRequest implements Serializable {
        private static final long serialVersionUID = 1;
        private final String applicationId;
        private final String authId;
        private final SessionDefaultAudience defaultAudience;
        private boolean isLegacy;
        private boolean isRerequest = false;
        private final SessionLoginBehavior loginBehavior;
        private List<String> permissions;
        private final String previousAccessToken;
        private final int requestCode;
        private final transient StartActivityDelegate startActivityDelegate;

        /* JADX INFO: Access modifiers changed from: package-private */
        public AuthorizationRequest(SessionLoginBehavior sessionLoginBehavior, int i, boolean z, List<String> list, SessionDefaultAudience sessionDefaultAudience, String str, String str2, StartActivityDelegate startActivityDelegate, String str3) {
            this.isLegacy = false;
            this.loginBehavior = sessionLoginBehavior;
            this.requestCode = i;
            this.isLegacy = z;
            this.permissions = list;
            this.defaultAudience = sessionDefaultAudience;
            this.applicationId = str;
            this.previousAccessToken = str2;
            this.startActivityDelegate = startActivityDelegate;
            this.authId = str3;
        }

        StartActivityDelegate getStartActivityDelegate() {
            return this.startActivityDelegate;
        }

        List<String> getPermissions() {
            return this.permissions;
        }

        void setPermissions(List<String> list) {
            this.permissions = list;
        }

        SessionLoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }

        int getRequestCode() {
            return this.requestCode;
        }

        SessionDefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }

        String getApplicationId() {
            return this.applicationId;
        }

        boolean isLegacy() {
            return this.isLegacy;
        }

        void setIsLegacy(boolean z) {
            this.isLegacy = z;
        }

        String getPreviousAccessToken() {
            return this.previousAccessToken;
        }

        boolean needsNewTokenValidation() {
            return (this.previousAccessToken == null || this.isLegacy) ? false : true;
        }

        String getAuthId() {
            return this.authId;
        }

        boolean isRerequest() {
            return this.isRerequest;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void setRerequest(boolean z) {
            this.isRerequest = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Result implements Serializable {
        private static final long serialVersionUID = 1;
        final Code code;
        final String errorCode;
        final String errorMessage;
        Map<String, String> loggingExtras;
        final AuthorizationRequest request;
        final AccessToken token;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public enum Code {
            SUCCESS(Response.SUCCESS_KEY),
            CANCEL(FacebookDialog.COMPLETION_GESTURE_CANCEL),
            ERROR("error");

            private final String loggingValue;

            Code(String str) {
                this.loggingValue = str;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public String getLoggingValue() {
                return this.loggingValue;
            }
        }

        private Result(AuthorizationRequest authorizationRequest, Code code, AccessToken accessToken, String str, String str2) {
            this.request = authorizationRequest;
            this.token = accessToken;
            this.errorMessage = str;
            this.code = code;
            this.errorCode = str2;
        }

        static Result createTokenResult(AuthorizationRequest authorizationRequest, AccessToken accessToken) {
            return new Result(authorizationRequest, Code.SUCCESS, accessToken, null, null);
        }

        static Result createCancelResult(AuthorizationRequest authorizationRequest, String str) {
            return new Result(authorizationRequest, Code.CANCEL, null, str, null);
        }

        static Result createErrorResult(AuthorizationRequest authorizationRequest, String str, String str2) {
            return createErrorResult(authorizationRequest, str, str2, null);
        }

        static Result createErrorResult(AuthorizationRequest authorizationRequest, String str, String str2, String str3) {
            return new Result(authorizationRequest, Code.ERROR, null, TextUtils.join(": ", Utility.asListNoNulls(str, str2)), str3);
        }
    }
}
