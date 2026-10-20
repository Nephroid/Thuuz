package com.facebook.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.AppEventsLogger;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.Request;
import com.facebook.RequestBatch;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.internal.FileLruCache;
import com.facebook.internal.PlatformServiceClient;
import com.facebook.widget.FacebookDialog;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class LikeActionController {
    public static final String ACTION_LIKE_ACTION_CONTROLLER_DID_ERROR = "com.facebook.sdk.LikeActionController.DID_ERROR";
    public static final String ACTION_LIKE_ACTION_CONTROLLER_DID_RESET = "com.facebook.sdk.LikeActionController.DID_RESET";
    public static final String ACTION_LIKE_ACTION_CONTROLLER_UPDATED = "com.facebook.sdk.LikeActionController.UPDATED";
    public static final String ACTION_OBJECT_ID_KEY = "com.facebook.sdk.LikeActionController.OBJECT_ID";
    private static final int ERROR_CODE_OBJECT_ALREADY_LIKED = 3501;
    public static final String ERROR_INVALID_OBJECT_ID = "Invalid Object Id";
    public static final String ERROR_PUBLISH_ERROR = "Unable to publish the like/unlike action";
    private static final String JSON_BOOL_IS_OBJECT_LIKED_KEY = "is_object_liked";
    private static final String JSON_BUNDLE_PENDING_CALL_ANALYTICS_BUNDLE = "pending_call_analytics_bundle";
    private static final String JSON_INT_VERSION_KEY = "com.facebook.internal.LikeActionController.version";
    private static final String JSON_STRING_LIKE_COUNT_WITHOUT_LIKE_KEY = "like_count_string_without_like";
    private static final String JSON_STRING_LIKE_COUNT_WITH_LIKE_KEY = "like_count_string_with_like";
    private static final String JSON_STRING_OBJECT_ID_KEY = "object_id";
    private static final String JSON_STRING_PENDING_CALL_ID_KEY = "pending_call_id";
    private static final String JSON_STRING_SOCIAL_SENTENCE_WITHOUT_LIKE_KEY = "social_sentence_without_like";
    private static final String JSON_STRING_SOCIAL_SENTENCE_WITH_LIKE_KEY = "social_sentence_with_like";
    private static final String JSON_STRING_UNLIKE_TOKEN_KEY = "unlike_token";
    private static final String LIKE_ACTION_CONTROLLER_STORE = "com.facebook.LikeActionController.CONTROLLER_STORE_KEY";
    private static final String LIKE_ACTION_CONTROLLER_STORE_OBJECT_SUFFIX_KEY = "OBJECT_SUFFIX";
    private static final String LIKE_ACTION_CONTROLLER_STORE_PENDING_OBJECT_ID_KEY = "PENDING_CONTROLLER_KEY";
    private static final int LIKE_ACTION_CONTROLLER_VERSION = 2;
    private static final String LIKE_DIALOG_RESPONSE_LIKE_COUNT_STRING_KEY = "like_count_string";
    private static final String LIKE_DIALOG_RESPONSE_OBJECT_IS_LIKED_KEY = "object_is_liked";
    private static final String LIKE_DIALOG_RESPONSE_SOCIAL_SENTENCE_KEY = "social_sentence";
    private static final String LIKE_DIALOG_RESPONSE_UNLIKE_TOKEN_KEY = "unlike_token";
    private static final int MAX_CACHE_SIZE = 128;
    private static final int MAX_OBJECT_SUFFIX = 1000;
    private static Context applicationContext;
    private static FileLruCache controllerDiskCache;
    private static Handler handler;
    private static boolean isInitialized;
    private static boolean isPendingBroadcastReset;
    private static String objectIdForPendingController;
    private static volatile int objectSuffix;
    private AppEventsLogger appEventsLogger;
    private boolean isObjectLiked;
    private boolean isObjectLikedOnServer;
    private boolean isPendingLikeOrUnlike;
    private String likeCountStringWithLike;
    private String likeCountStringWithoutLike;
    private String objectId;
    private boolean objectIsPage;
    private Bundle pendingCallAnalyticsBundle;
    private UUID pendingCallId;
    private Session session;
    private String socialSentenceWithLike;
    private String socialSentenceWithoutLike;
    private String unlikeToken;
    private String verifiedObjectId;
    private static final String TAG = LikeActionController.class.getSimpleName();
    private static final ConcurrentHashMap<String, LikeActionController> cache = new ConcurrentHashMap<>();
    private static WorkQueue mruCacheWorkQueue = new WorkQueue(1);
    private static WorkQueue diskIOWorkQueue = new WorkQueue(1);

    /* loaded from: classes.dex */
    public interface CreationCallback {
        void onComplete(LikeActionController likeActionController);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface RequestCompletionCallback {
        void onComplete();
    }

    public static boolean handleOnActivityResult(Context context, final int i, final int i2, final Intent intent) {
        ensureApplicationContextExists(context);
        final UUID callIdFromIntent = NativeProtocol.getCallIdFromIntent(intent);
        if (callIdFromIntent == null) {
            return false;
        }
        if (Utility.isNullOrEmpty(objectIdForPendingController)) {
            objectIdForPendingController = applicationContext.getSharedPreferences(LIKE_ACTION_CONTROLLER_STORE, 0).getString(LIKE_ACTION_CONTROLLER_STORE_PENDING_OBJECT_ID_KEY, null);
        }
        if (Utility.isNullOrEmpty(objectIdForPendingController)) {
            return false;
        }
        getControllerForObjectId(applicationContext, objectIdForPendingController, new CreationCallback() { // from class: com.facebook.internal.LikeActionController.1
            @Override // com.facebook.internal.LikeActionController.CreationCallback
            public void onComplete(LikeActionController likeActionController) {
                likeActionController.onActivityResult(i, i2, intent, callIdFromIntent);
            }
        });
        return true;
    }

    public static void getControllerForObjectId(Context context, String str, CreationCallback creationCallback) {
        ensureApplicationContextExists(context);
        if (!isInitialized) {
            performFirstInitialize();
        }
        LikeActionController controllerFromInMemoryCache = getControllerFromInMemoryCache(str);
        if (controllerFromInMemoryCache != null) {
            invokeCallbackWithController(creationCallback, controllerFromInMemoryCache);
        } else {
            diskIOWorkQueue.addActiveWorkItem(new CreateLikeActionControllerWorkItem(str, creationCallback));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void createControllerForObjectId(String str, CreationCallback creationCallback) {
        LikeActionController controllerFromInMemoryCache = getControllerFromInMemoryCache(str);
        if (controllerFromInMemoryCache != null) {
            invokeCallbackWithController(creationCallback, controllerFromInMemoryCache);
            return;
        }
        LikeActionController deserializeFromDiskSynchronously = deserializeFromDiskSynchronously(str);
        if (deserializeFromDiskSynchronously == null) {
            deserializeFromDiskSynchronously = new LikeActionController(Session.getActiveSession(), str);
            serializeToDiskAsync(deserializeFromDiskSynchronously);
        }
        putControllerInMemoryCache(str, deserializeFromDiskSynchronously);
        handler.post(new Runnable() { // from class: com.facebook.internal.LikeActionController.2
            @Override // java.lang.Runnable
            public void run() {
                LikeActionController.this.refreshStatusAsync();
            }
        });
        invokeCallbackWithController(creationCallback, deserializeFromDiskSynchronously);
    }

    private static synchronized void ensureApplicationContextExists(Context context) {
        synchronized (LikeActionController.class) {
            if (applicationContext == null) {
                applicationContext = context.getApplicationContext();
            }
        }
    }

    private static synchronized void performFirstInitialize() {
        synchronized (LikeActionController.class) {
            if (!isInitialized) {
                handler = new Handler(Looper.getMainLooper());
                objectSuffix = applicationContext.getSharedPreferences(LIKE_ACTION_CONTROLLER_STORE, 0).getInt(LIKE_ACTION_CONTROLLER_STORE_OBJECT_SUFFIX_KEY, 1);
                controllerDiskCache = new FileLruCache(applicationContext, TAG, new FileLruCache.Limits());
                registerSessionBroadcastReceivers();
                isInitialized = true;
            }
        }
    }

    private static void invokeCallbackWithController(final CreationCallback creationCallback, final LikeActionController likeActionController) {
        if (creationCallback != null) {
            handler.post(new Runnable() { // from class: com.facebook.internal.LikeActionController.3
                @Override // java.lang.Runnable
                public void run() {
                    CreationCallback.this.onComplete(likeActionController);
                }
            });
        }
    }

    private static void registerSessionBroadcastReceivers() {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(applicationContext);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Session.ACTION_ACTIVE_SESSION_UNSET);
        intentFilter.addAction(Session.ACTION_ACTIVE_SESSION_CLOSED);
        intentFilter.addAction(Session.ACTION_ACTIVE_SESSION_OPENED);
        localBroadcastManager.registerReceiver(new BroadcastReceiver() { // from class: com.facebook.internal.LikeActionController.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (!LikeActionController.isPendingBroadcastReset) {
                    String action = intent.getAction();
                    final boolean z = Utility.areObjectsEqual(Session.ACTION_ACTIVE_SESSION_UNSET, action) || Utility.areObjectsEqual(Session.ACTION_ACTIVE_SESSION_CLOSED, action);
                    boolean unused = LikeActionController.isPendingBroadcastReset = true;
                    LikeActionController.handler.postDelayed(new Runnable() { // from class: com.facebook.internal.LikeActionController.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (z) {
                                int unused2 = LikeActionController.objectSuffix = (LikeActionController.objectSuffix + 1) % 1000;
                                LikeActionController.applicationContext.getSharedPreferences(LikeActionController.LIKE_ACTION_CONTROLLER_STORE, 0).edit().putInt(LikeActionController.LIKE_ACTION_CONTROLLER_STORE_OBJECT_SUFFIX_KEY, LikeActionController.objectSuffix).apply();
                                LikeActionController.cache.clear();
                                LikeActionController.controllerDiskCache.clearCache();
                            }
                            LikeActionController.broadcastAction(null, LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_DID_RESET);
                            boolean unused3 = LikeActionController.isPendingBroadcastReset = false;
                        }
                    }, 100L);
                }
            }
        }, intentFilter);
    }

    private static void putControllerInMemoryCache(String str, LikeActionController likeActionController) {
        String cacheKeyForObjectId = getCacheKeyForObjectId(str);
        mruCacheWorkQueue.addActiveWorkItem(new MRUCacheWorkItem(cacheKeyForObjectId, true));
        cache.put(cacheKeyForObjectId, likeActionController);
    }

    private static LikeActionController getControllerFromInMemoryCache(String str) {
        String cacheKeyForObjectId = getCacheKeyForObjectId(str);
        LikeActionController likeActionController = cache.get(cacheKeyForObjectId);
        if (likeActionController != null) {
            mruCacheWorkQueue.addActiveWorkItem(new MRUCacheWorkItem(cacheKeyForObjectId, false));
        }
        return likeActionController;
    }

    private static void serializeToDiskAsync(LikeActionController likeActionController) {
        String serializeToJson = serializeToJson(likeActionController);
        String cacheKeyForObjectId = getCacheKeyForObjectId(likeActionController.objectId);
        if (!Utility.isNullOrEmpty(serializeToJson) && !Utility.isNullOrEmpty(cacheKeyForObjectId)) {
            diskIOWorkQueue.addActiveWorkItem(new SerializeToDiskWorkItem(cacheKeyForObjectId, serializeToJson));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void serializeToDiskSynchronously(String str, String str2) {
        OutputStream outputStream = null;
        try {
            try {
                outputStream = controllerDiskCache.openPutStream(str);
                outputStream.write(str2.getBytes());
                if (outputStream != null) {
                    Utility.closeQuietly(outputStream);
                }
            } catch (IOException e) {
                Log.e(TAG, "Unable to serialize controller to disk", e);
                if (outputStream != null) {
                    Utility.closeQuietly(outputStream);
                }
            }
        } catch (Throwable th) {
            if (outputStream != null) {
                Utility.closeQuietly(outputStream);
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.facebook.internal.LikeActionController deserializeFromDiskSynchronously(java.lang.String r5) {
        /*
            r0 = 0
            java.lang.String r1 = getCacheKeyForObjectId(r5)     // Catch: java.io.IOException -> L21 java.lang.Throwable -> L30
            com.facebook.internal.FileLruCache r2 = com.facebook.internal.LikeActionController.controllerDiskCache     // Catch: java.io.IOException -> L21 java.lang.Throwable -> L30
            java.io.InputStream r2 = r2.get(r1)     // Catch: java.io.IOException -> L21 java.lang.Throwable -> L30
            if (r2 == 0) goto L1b
            java.lang.String r1 = com.facebook.internal.Utility.readStreamToString(r2)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3b
            boolean r3 = com.facebook.internal.Utility.isNullOrEmpty(r1)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3b
            if (r3 != 0) goto L1b
            com.facebook.internal.LikeActionController r0 = deserializeFromJson(r1)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3b
        L1b:
            if (r2 == 0) goto L20
            com.facebook.internal.Utility.closeQuietly(r2)
        L20:
            return r0
        L21:
            r1 = move-exception
            r2 = r0
        L23:
            java.lang.String r3 = com.facebook.internal.LikeActionController.TAG     // Catch: java.lang.Throwable -> L39
            java.lang.String r4 = "Unable to deserialize controller from disk"
            android.util.Log.e(r3, r4, r1)     // Catch: java.lang.Throwable -> L39
            if (r2 == 0) goto L20
            com.facebook.internal.Utility.closeQuietly(r2)
            goto L20
        L30:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L33:
            if (r2 == 0) goto L38
            com.facebook.internal.Utility.closeQuietly(r2)
        L38:
            throw r0
        L39:
            r0 = move-exception
            goto L33
        L3b:
            r1 = move-exception
            goto L23
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.LikeActionController.deserializeFromDiskSynchronously(java.lang.String):com.facebook.internal.LikeActionController");
    }

    private static LikeActionController deserializeFromJson(String str) {
        LikeActionController likeActionController;
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            Log.e(TAG, "Unable to deserialize controller from JSON", e);
            likeActionController = null;
        }
        if (jSONObject.optInt(JSON_INT_VERSION_KEY, -1) != 2) {
            return null;
        }
        likeActionController = new LikeActionController(Session.getActiveSession(), jSONObject.getString("object_id"));
        likeActionController.likeCountStringWithLike = jSONObject.optString(JSON_STRING_LIKE_COUNT_WITH_LIKE_KEY, null);
        likeActionController.likeCountStringWithoutLike = jSONObject.optString(JSON_STRING_LIKE_COUNT_WITHOUT_LIKE_KEY, null);
        likeActionController.socialSentenceWithLike = jSONObject.optString(JSON_STRING_SOCIAL_SENTENCE_WITH_LIKE_KEY, null);
        likeActionController.socialSentenceWithoutLike = jSONObject.optString(JSON_STRING_SOCIAL_SENTENCE_WITHOUT_LIKE_KEY, null);
        likeActionController.isObjectLiked = jSONObject.optBoolean(JSON_BOOL_IS_OBJECT_LIKED_KEY);
        likeActionController.unlikeToken = jSONObject.optString("unlike_token", null);
        String optString = jSONObject.optString(JSON_STRING_PENDING_CALL_ID_KEY, null);
        if (!Utility.isNullOrEmpty(optString)) {
            likeActionController.pendingCallId = UUID.fromString(optString);
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(JSON_BUNDLE_PENDING_CALL_ANALYTICS_BUNDLE);
        if (optJSONObject != null) {
            likeActionController.pendingCallAnalyticsBundle = BundleJSONConverter.convertToBundle(optJSONObject);
        }
        return likeActionController;
    }

    private static String serializeToJson(LikeActionController likeActionController) {
        JSONObject convertToJSON;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(JSON_INT_VERSION_KEY, 2);
            jSONObject.put("object_id", likeActionController.objectId);
            jSONObject.put(JSON_STRING_LIKE_COUNT_WITH_LIKE_KEY, likeActionController.likeCountStringWithLike);
            jSONObject.put(JSON_STRING_LIKE_COUNT_WITHOUT_LIKE_KEY, likeActionController.likeCountStringWithoutLike);
            jSONObject.put(JSON_STRING_SOCIAL_SENTENCE_WITH_LIKE_KEY, likeActionController.socialSentenceWithLike);
            jSONObject.put(JSON_STRING_SOCIAL_SENTENCE_WITHOUT_LIKE_KEY, likeActionController.socialSentenceWithoutLike);
            jSONObject.put(JSON_BOOL_IS_OBJECT_LIKED_KEY, likeActionController.isObjectLiked);
            jSONObject.put("unlike_token", likeActionController.unlikeToken);
            if (likeActionController.pendingCallId != null) {
                jSONObject.put(JSON_STRING_PENDING_CALL_ID_KEY, likeActionController.pendingCallId.toString());
            }
            if (likeActionController.pendingCallAnalyticsBundle != null && (convertToJSON = BundleJSONConverter.convertToJSON(likeActionController.pendingCallAnalyticsBundle)) != null) {
                jSONObject.put(JSON_BUNDLE_PENDING_CALL_ANALYTICS_BUNDLE, convertToJSON);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.e(TAG, "Unable to serialize controller to JSON", e);
            return null;
        }
    }

    private static String getCacheKeyForObjectId(String str) {
        String str2 = null;
        Session activeSession = Session.getActiveSession();
        if (activeSession != null && activeSession.isOpened()) {
            str2 = activeSession.getAccessToken();
        }
        if (str2 != null) {
            str2 = Utility.md5hash(str2);
        }
        return String.format("%s|%s|com.fb.sdk.like|%d", str, Utility.coerceValueIfNullOrEmpty(str2, ""), Integer.valueOf(objectSuffix));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void broadcastAction(LikeActionController likeActionController, String str) {
        broadcastAction(likeActionController, str, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void broadcastAction(LikeActionController likeActionController, String str, Bundle bundle) {
        Intent intent = new Intent(str);
        if (likeActionController != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(ACTION_OBJECT_ID_KEY, likeActionController.getObjectId());
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent);
    }

    private LikeActionController(Session session, String str) {
        this.session = session;
        this.objectId = str;
        this.appEventsLogger = AppEventsLogger.newLogger(applicationContext, session);
    }

    public String getObjectId() {
        return this.objectId;
    }

    public String getLikeCountString() {
        return this.isObjectLiked ? this.likeCountStringWithLike : this.likeCountStringWithoutLike;
    }

    public String getSocialSentence() {
        return this.isObjectLiked ? this.socialSentenceWithLike : this.socialSentenceWithoutLike;
    }

    public boolean isObjectLiked() {
        return this.isObjectLiked;
    }

    public void toggleLike(Activity activity, Fragment fragment, Bundle bundle) {
        this.appEventsLogger.logSdkEvent(AnalyticsEvents.EVENT_LIKE_VIEW_DID_TAP, null, bundle);
        boolean z = !this.isObjectLiked;
        if (canUseOGPublish()) {
            updateLikeState(z);
            if (this.isPendingLikeOrUnlike) {
                this.appEventsLogger.logSdkEvent(AnalyticsEvents.EVENT_LIKE_VIEW_DID_UNDO_QUICKLY, null, bundle);
                return;
            } else {
                if (!publishLikeOrUnlikeAsync(z, bundle)) {
                    updateLikeState(z ? false : true);
                    presentLikeDialog(activity, fragment, bundle);
                    return;
                }
                return;
            }
        }
        presentLikeDialog(activity, fragment, bundle);
    }

    private boolean publishLikeOrUnlikeAsync(boolean z, Bundle bundle) {
        if (canUseOGPublish()) {
            if (z) {
                publishLikeAsync(bundle);
                return true;
            }
            if (!Utility.isNullOrEmpty(this.unlikeToken)) {
                publishUnlikeAsync(bundle);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishDidError(boolean z) {
        updateLikeState(z);
        Bundle bundle = new Bundle();
        bundle.putString(NativeProtocol.STATUS_ERROR_DESCRIPTION, ERROR_PUBLISH_ERROR);
        broadcastAction(this, ACTION_LIKE_ACTION_CONTROLLER_DID_ERROR, bundle);
    }

    private void updateLikeState(boolean z) {
        updateState(z, this.likeCountStringWithLike, this.likeCountStringWithoutLike, this.socialSentenceWithLike, this.socialSentenceWithoutLike, this.unlikeToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateState(boolean z, String str, String str2, String str3, String str4, String str5) {
        String coerceValueIfNullOrEmpty = Utility.coerceValueIfNullOrEmpty(str, null);
        String coerceValueIfNullOrEmpty2 = Utility.coerceValueIfNullOrEmpty(str2, null);
        String coerceValueIfNullOrEmpty3 = Utility.coerceValueIfNullOrEmpty(str3, null);
        String coerceValueIfNullOrEmpty4 = Utility.coerceValueIfNullOrEmpty(str4, null);
        String coerceValueIfNullOrEmpty5 = Utility.coerceValueIfNullOrEmpty(str5, null);
        if ((z == this.isObjectLiked && Utility.areObjectsEqual(coerceValueIfNullOrEmpty, this.likeCountStringWithLike) && Utility.areObjectsEqual(coerceValueIfNullOrEmpty2, this.likeCountStringWithoutLike) && Utility.areObjectsEqual(coerceValueIfNullOrEmpty3, this.socialSentenceWithLike) && Utility.areObjectsEqual(coerceValueIfNullOrEmpty4, this.socialSentenceWithoutLike) && Utility.areObjectsEqual(coerceValueIfNullOrEmpty5, this.unlikeToken)) ? false : true) {
            this.isObjectLiked = z;
            this.likeCountStringWithLike = coerceValueIfNullOrEmpty;
            this.likeCountStringWithoutLike = coerceValueIfNullOrEmpty2;
            this.socialSentenceWithLike = coerceValueIfNullOrEmpty3;
            this.socialSentenceWithoutLike = coerceValueIfNullOrEmpty4;
            this.unlikeToken = coerceValueIfNullOrEmpty5;
            serializeToDiskAsync(this);
            broadcastAction(this, ACTION_LIKE_ACTION_CONTROLLER_UPDATED);
        }
    }

    private void presentLikeDialog(Activity activity, Fragment fragment, Bundle bundle) {
        LikeDialogBuilder likeDialogBuilder = new LikeDialogBuilder(activity, this.objectId);
        likeDialogBuilder.setFragment(fragment);
        if (likeDialogBuilder.canPresent()) {
            trackPendingCall(likeDialogBuilder.build().present(), bundle);
            this.appEventsLogger.logSdkEvent(AnalyticsEvents.EVENT_LIKE_VIEW_DID_PRESENT_DIALOG, null, bundle);
            return;
        }
        String webFallbackUrl = likeDialogBuilder.getWebFallbackUrl();
        if (!Utility.isNullOrEmpty(webFallbackUrl) && FacebookWebFallbackDialog.presentWebFallback(activity, webFallbackUrl, likeDialogBuilder.getApplicationId(), likeDialogBuilder.getAppCall(), getFacebookDialogCallback(bundle))) {
            this.appEventsLogger.logSdkEvent(AnalyticsEvents.EVENT_LIKE_VIEW_DID_PRESENT_FALLBACK, null, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onActivityResult(int i, int i2, Intent intent, UUID uuid) {
        FacebookDialog.PendingCall pendingCallById;
        if (this.pendingCallId == null || !this.pendingCallId.equals(uuid) || (pendingCallById = PendingCallStore.getInstance().getPendingCallById(this.pendingCallId)) == null) {
            return false;
        }
        FacebookDialog.handleActivityResult(applicationContext, pendingCallById, i, intent, getFacebookDialogCallback(this.pendingCallAnalyticsBundle));
        stopTrackingPendingCall();
        return true;
    }

    private FacebookDialog.Callback getFacebookDialogCallback(final Bundle bundle) {
        return new FacebookDialog.Callback() { // from class: com.facebook.internal.LikeActionController.5
            @Override // com.facebook.widget.FacebookDialog.Callback
            public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle bundle2) {
                String str;
                if (bundle2 != null && bundle2.containsKey(LikeActionController.LIKE_DIALOG_RESPONSE_OBJECT_IS_LIKED_KEY)) {
                    boolean z = bundle2.getBoolean(LikeActionController.LIKE_DIALOG_RESPONSE_OBJECT_IS_LIKED_KEY);
                    String str2 = LikeActionController.this.likeCountStringWithLike;
                    String str3 = LikeActionController.this.likeCountStringWithoutLike;
                    if (bundle2.containsKey(LikeActionController.LIKE_DIALOG_RESPONSE_LIKE_COUNT_STRING_KEY)) {
                        str3 = bundle2.getString(LikeActionController.LIKE_DIALOG_RESPONSE_LIKE_COUNT_STRING_KEY);
                        str2 = str3;
                    }
                    String str4 = LikeActionController.this.socialSentenceWithLike;
                    String str5 = LikeActionController.this.socialSentenceWithoutLike;
                    if (bundle2.containsKey(LikeActionController.LIKE_DIALOG_RESPONSE_SOCIAL_SENTENCE_KEY)) {
                        str5 = bundle2.getString(LikeActionController.LIKE_DIALOG_RESPONSE_SOCIAL_SENTENCE_KEY);
                        str4 = str5;
                    }
                    if (!bundle2.containsKey(LikeActionController.LIKE_DIALOG_RESPONSE_OBJECT_IS_LIKED_KEY)) {
                        str = LikeActionController.this.unlikeToken;
                    } else {
                        str = bundle2.getString("unlike_token");
                    }
                    Bundle bundle3 = bundle == null ? new Bundle() : bundle;
                    bundle3.putString(AnalyticsEvents.PARAMETER_CALL_ID, pendingCall.getCallId().toString());
                    LikeActionController.this.appEventsLogger.logSdkEvent(AnalyticsEvents.EVENT_LIKE_VIEW_DIALOG_DID_SUCCEED, null, bundle3);
                    LikeActionController.this.updateState(z, str2, str3, str4, str5, str);
                }
            }

            @Override // com.facebook.widget.FacebookDialog.Callback
            public void onError(FacebookDialog.PendingCall pendingCall, Exception exc, Bundle bundle2) {
                Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Like Dialog failed with error : %s", exc);
                Bundle bundle3 = bundle == null ? new Bundle() : bundle;
                bundle3.putString(AnalyticsEvents.PARAMETER_CALL_ID, pendingCall.getCallId().toString());
                LikeActionController.this.logAppEventForError("present_dialog", bundle3);
                LikeActionController.broadcastAction(LikeActionController.this, LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_DID_ERROR, bundle2);
            }
        };
    }

    private void trackPendingCall(FacebookDialog.PendingCall pendingCall, Bundle bundle) {
        PendingCallStore.getInstance().trackPendingCall(pendingCall);
        this.pendingCallId = pendingCall.getCallId();
        storeObjectIdForPendingController(this.objectId);
        this.pendingCallAnalyticsBundle = bundle;
        serializeToDiskAsync(this);
    }

    private void stopTrackingPendingCall() {
        PendingCallStore.getInstance().stopTrackingPendingCall(this.pendingCallId);
        this.pendingCallId = null;
        this.pendingCallAnalyticsBundle = null;
        storeObjectIdForPendingController(null);
    }

    private void storeObjectIdForPendingController(String str) {
        objectIdForPendingController = str;
        applicationContext.getSharedPreferences(LIKE_ACTION_CONTROLLER_STORE, 0).edit().putString(LIKE_ACTION_CONTROLLER_STORE_PENDING_OBJECT_ID_KEY, objectIdForPendingController).apply();
    }

    private boolean canUseOGPublish() {
        return (this.objectIsPage || this.verifiedObjectId == null || this.session == null || this.session.getPermissions() == null || !this.session.getPermissions().contains("publish_actions")) ? false : true;
    }

    private void publishLikeAsync(final Bundle bundle) {
        this.isPendingLikeOrUnlike = true;
        fetchVerifiedObjectId(new RequestCompletionCallback() { // from class: com.facebook.internal.LikeActionController.6
            @Override // com.facebook.internal.LikeActionController.RequestCompletionCallback
            public void onComplete() {
                if (Utility.isNullOrEmpty(LikeActionController.this.verifiedObjectId)) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(NativeProtocol.STATUS_ERROR_DESCRIPTION, LikeActionController.ERROR_INVALID_OBJECT_ID);
                    LikeActionController.broadcastAction(LikeActionController.this, LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_DID_ERROR, bundle2);
                } else {
                    RequestBatch requestBatch = new RequestBatch();
                    final PublishLikeRequestWrapper publishLikeRequestWrapper = new PublishLikeRequestWrapper(LikeActionController.this.verifiedObjectId);
                    publishLikeRequestWrapper.addToBatch(requestBatch);
                    requestBatch.addCallback(new RequestBatch.Callback() { // from class: com.facebook.internal.LikeActionController.6.1
                        @Override // com.facebook.RequestBatch.Callback
                        public void onBatchCompleted(RequestBatch requestBatch2) {
                            LikeActionController.this.isPendingLikeOrUnlike = false;
                            if (publishLikeRequestWrapper.error != null) {
                                LikeActionController.this.publishDidError(false);
                                return;
                            }
                            LikeActionController.this.unlikeToken = Utility.coerceValueIfNullOrEmpty(publishLikeRequestWrapper.unlikeToken, null);
                            LikeActionController.this.isObjectLikedOnServer = true;
                            LikeActionController.this.appEventsLogger.logSdkEvent(AnalyticsEvents.EVENT_LIKE_VIEW_DID_LIKE, null, bundle);
                            LikeActionController.this.publishAgainIfNeeded(bundle);
                        }
                    });
                    requestBatch.executeAsync();
                }
            }
        });
    }

    private void publishUnlikeAsync(final Bundle bundle) {
        this.isPendingLikeOrUnlike = true;
        RequestBatch requestBatch = new RequestBatch();
        final PublishUnlikeRequestWrapper publishUnlikeRequestWrapper = new PublishUnlikeRequestWrapper(this.unlikeToken);
        publishUnlikeRequestWrapper.addToBatch(requestBatch);
        requestBatch.addCallback(new RequestBatch.Callback() { // from class: com.facebook.internal.LikeActionController.7
            @Override // com.facebook.RequestBatch.Callback
            public void onBatchCompleted(RequestBatch requestBatch2) {
                LikeActionController.this.isPendingLikeOrUnlike = false;
                if (publishUnlikeRequestWrapper.error != null) {
                    LikeActionController.this.publishDidError(true);
                    return;
                }
                LikeActionController.this.unlikeToken = null;
                LikeActionController.this.isObjectLikedOnServer = false;
                LikeActionController.this.appEventsLogger.logSdkEvent(AnalyticsEvents.EVENT_LIKE_VIEW_DID_UNLIKE, null, bundle);
                LikeActionController.this.publishAgainIfNeeded(bundle);
            }
        });
        requestBatch.executeAsync();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshStatusAsync() {
        if (this.session == null || this.session.isClosed() || SessionState.CREATED.equals(this.session.getState())) {
            refreshStatusViaService();
        } else if (this.session.isOpened()) {
            fetchVerifiedObjectId(new RequestCompletionCallback() { // from class: com.facebook.internal.LikeActionController.8
                @Override // com.facebook.internal.LikeActionController.RequestCompletionCallback
                public void onComplete() {
                    final GetOGObjectLikesRequestWrapper getOGObjectLikesRequestWrapper = new GetOGObjectLikesRequestWrapper(LikeActionController.this.verifiedObjectId);
                    final GetEngagementRequestWrapper getEngagementRequestWrapper = new GetEngagementRequestWrapper(LikeActionController.this.verifiedObjectId);
                    RequestBatch requestBatch = new RequestBatch();
                    getOGObjectLikesRequestWrapper.addToBatch(requestBatch);
                    getEngagementRequestWrapper.addToBatch(requestBatch);
                    requestBatch.addCallback(new RequestBatch.Callback() { // from class: com.facebook.internal.LikeActionController.8.1
                        @Override // com.facebook.RequestBatch.Callback
                        public void onBatchCompleted(RequestBatch requestBatch2) {
                            if (getOGObjectLikesRequestWrapper.error == null && getEngagementRequestWrapper.error == null) {
                                LikeActionController.this.updateState(getOGObjectLikesRequestWrapper.objectIsLiked, getEngagementRequestWrapper.likeCountStringWithLike, getEngagementRequestWrapper.likeCountStringWithoutLike, getEngagementRequestWrapper.socialSentenceStringWithLike, getEngagementRequestWrapper.socialSentenceStringWithoutLike, getOGObjectLikesRequestWrapper.unlikeToken);
                            } else {
                                Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Unable to refresh like state for id: '%s'", LikeActionController.this.objectId);
                            }
                        }
                    });
                    requestBatch.executeAsync();
                }
            });
        }
    }

    private void refreshStatusViaService() {
        LikeStatusClient likeStatusClient = new LikeStatusClient(applicationContext, Settings.getApplicationId(), this.objectId);
        if (likeStatusClient.start()) {
            likeStatusClient.setCompletedListener(new PlatformServiceClient.CompletedListener() { // from class: com.facebook.internal.LikeActionController.9
                @Override // com.facebook.internal.PlatformServiceClient.CompletedListener
                public void completed(Bundle bundle) {
                    String str;
                    String str2;
                    String str3;
                    String str4;
                    String str5;
                    if (bundle != null && bundle.containsKey(NativeProtocol.EXTRA_OBJECT_IS_LIKED)) {
                        boolean z = bundle.getBoolean(NativeProtocol.EXTRA_OBJECT_IS_LIKED);
                        if (!bundle.containsKey(NativeProtocol.EXTRA_LIKE_COUNT_STRING_WITH_LIKE)) {
                            str = LikeActionController.this.likeCountStringWithLike;
                        } else {
                            str = bundle.getString(NativeProtocol.EXTRA_LIKE_COUNT_STRING_WITH_LIKE);
                        }
                        if (!bundle.containsKey(NativeProtocol.EXTRA_LIKE_COUNT_STRING_WITHOUT_LIKE)) {
                            str2 = LikeActionController.this.likeCountStringWithoutLike;
                        } else {
                            str2 = bundle.getString(NativeProtocol.EXTRA_LIKE_COUNT_STRING_WITHOUT_LIKE);
                        }
                        if (!bundle.containsKey(NativeProtocol.EXTRA_SOCIAL_SENTENCE_WITH_LIKE)) {
                            str3 = LikeActionController.this.socialSentenceWithLike;
                        } else {
                            str3 = bundle.getString(NativeProtocol.EXTRA_SOCIAL_SENTENCE_WITH_LIKE);
                        }
                        if (!bundle.containsKey(NativeProtocol.EXTRA_SOCIAL_SENTENCE_WITHOUT_LIKE)) {
                            str4 = LikeActionController.this.socialSentenceWithoutLike;
                        } else {
                            str4 = bundle.getString(NativeProtocol.EXTRA_SOCIAL_SENTENCE_WITHOUT_LIKE);
                        }
                        if (!bundle.containsKey(NativeProtocol.EXTRA_UNLIKE_TOKEN)) {
                            str5 = LikeActionController.this.unlikeToken;
                        } else {
                            str5 = bundle.getString(NativeProtocol.EXTRA_UNLIKE_TOKEN);
                        }
                        LikeActionController.this.updateState(z, str, str2, str3, str4, str5);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishAgainIfNeeded(Bundle bundle) {
        if (this.isObjectLiked != this.isObjectLikedOnServer && !publishLikeOrUnlikeAsync(this.isObjectLiked, bundle)) {
            publishDidError(!this.isObjectLiked);
        }
    }

    private void fetchVerifiedObjectId(final RequestCompletionCallback requestCompletionCallback) {
        if (!Utility.isNullOrEmpty(this.verifiedObjectId)) {
            if (requestCompletionCallback != null) {
                requestCompletionCallback.onComplete();
                return;
            }
            return;
        }
        final GetOGObjectIdRequestWrapper getOGObjectIdRequestWrapper = new GetOGObjectIdRequestWrapper(this.objectId);
        final GetPageIdRequestWrapper getPageIdRequestWrapper = new GetPageIdRequestWrapper(this.objectId);
        RequestBatch requestBatch = new RequestBatch();
        getOGObjectIdRequestWrapper.addToBatch(requestBatch);
        getPageIdRequestWrapper.addToBatch(requestBatch);
        requestBatch.addCallback(new RequestBatch.Callback() { // from class: com.facebook.internal.LikeActionController.10
            @Override // com.facebook.RequestBatch.Callback
            public void onBatchCompleted(RequestBatch requestBatch2) {
                LikeActionController.this.verifiedObjectId = getOGObjectIdRequestWrapper.verifiedObjectId;
                if (Utility.isNullOrEmpty(LikeActionController.this.verifiedObjectId)) {
                    LikeActionController.this.verifiedObjectId = getPageIdRequestWrapper.verifiedObjectId;
                    LikeActionController.this.objectIsPage = getPageIdRequestWrapper.objectIsPage;
                }
                if (Utility.isNullOrEmpty(LikeActionController.this.verifiedObjectId)) {
                    Logger.log(LoggingBehavior.DEVELOPER_ERRORS, LikeActionController.TAG, "Unable to verify the FB id for '%s'. Verify that it is a valid FB object or page", LikeActionController.this.objectId);
                    LikeActionController.this.logAppEventForError("get_verified_id", getPageIdRequestWrapper.error != null ? getPageIdRequestWrapper.error : getOGObjectIdRequestWrapper.error);
                }
                if (requestCompletionCallback != null) {
                    requestCompletionCallback.onComplete();
                }
            }
        });
        requestBatch.executeAsync();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logAppEventForError(String str, Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        bundle2.putString("object_id", this.objectId);
        bundle2.putString(AnalyticsEvents.PARAMETER_LIKE_VIEW_CURRENT_ACTION, str);
        this.appEventsLogger.logSdkEvent(AnalyticsEvents.EVENT_LIKE_VIEW_ERROR, null, bundle2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logAppEventForError(String str, FacebookRequestError facebookRequestError) {
        JSONObject requestResult;
        Bundle bundle = new Bundle();
        if (facebookRequestError != null && (requestResult = facebookRequestError.getRequestResult()) != null) {
            bundle.putString("error", requestResult.toString());
        }
        logAppEventForError(str, bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class GetOGObjectIdRequestWrapper extends AbstractRequestWrapper {
        String verifiedObjectId;

        GetOGObjectIdRequestWrapper(String str) {
            super(str);
            Bundle bundle = new Bundle();
            bundle.putString("fields", "og_object.fields(id)");
            bundle.putString("ids", str);
            setRequest(new Request(LikeActionController.this.session, "", bundle, HttpMethod.GET));
        }

        @Override // com.facebook.internal.LikeActionController.AbstractRequestWrapper
        protected void processError(FacebookRequestError facebookRequestError) {
            if (facebookRequestError.getErrorMessage().contains("og_object")) {
                this.error = null;
            } else {
                Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error getting the FB id for object '%s' : %s", this.objectId, facebookRequestError);
            }
        }

        @Override // com.facebook.internal.LikeActionController.AbstractRequestWrapper
        protected void processSuccess(Response response) {
            JSONObject optJSONObject;
            JSONObject tryGetJSONObjectFromResponse = Utility.tryGetJSONObjectFromResponse(response.getGraphObject(), this.objectId);
            if (tryGetJSONObjectFromResponse != null && (optJSONObject = tryGetJSONObjectFromResponse.optJSONObject("og_object")) != null) {
                this.verifiedObjectId = optJSONObject.optString("id");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class GetPageIdRequestWrapper extends AbstractRequestWrapper {
        boolean objectIsPage;
        String verifiedObjectId;

        GetPageIdRequestWrapper(String str) {
            super(str);
            Bundle bundle = new Bundle();
            bundle.putString("fields", "id");
            bundle.putString("ids", str);
            setRequest(new Request(LikeActionController.this.session, "", bundle, HttpMethod.GET));
        }

        @Override // com.facebook.internal.LikeActionController.AbstractRequestWrapper
        protected void processSuccess(Response response) {
            JSONObject tryGetJSONObjectFromResponse = Utility.tryGetJSONObjectFromResponse(response.getGraphObject(), this.objectId);
            if (tryGetJSONObjectFromResponse != null) {
                this.verifiedObjectId = tryGetJSONObjectFromResponse.optString("id");
                this.objectIsPage = !Utility.isNullOrEmpty(this.verifiedObjectId);
            }
        }

        @Override // com.facebook.internal.LikeActionController.AbstractRequestWrapper
        protected void processError(FacebookRequestError facebookRequestError) {
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error getting the FB id for object '%s' : %s", this.objectId, facebookRequestError);
        }
    }

    /* loaded from: classes.dex */
    class PublishLikeRequestWrapper extends AbstractRequestWrapper {
        String unlikeToken;

        PublishLikeRequestWrapper(String str) {
            super(str);
            Bundle bundle = new Bundle();
            bundle.putString("object", str);
            setRequest(new Request(LikeActionController.this.session, "me/og.likes", bundle, HttpMethod.POST));
        }

        @Override // com.facebook.internal.LikeActionController.AbstractRequestWrapper
        protected void processSuccess(Response response) {
            this.unlikeToken = Utility.safeGetStringFromResponse(response.getGraphObject(), "id");
        }

        @Override // com.facebook.internal.LikeActionController.AbstractRequestWrapper
        protected void processError(FacebookRequestError facebookRequestError) {
            if (facebookRequestError.getErrorCode() == LikeActionController.ERROR_CODE_OBJECT_ALREADY_LIKED) {
                this.error = null;
            } else {
                Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error liking object '%s' : %s", this.objectId, facebookRequestError);
                LikeActionController.this.logAppEventForError("publish_like", facebookRequestError);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class PublishUnlikeRequestWrapper extends AbstractRequestWrapper {
        private String unlikeToken;

        PublishUnlikeRequestWrapper(String str) {
            super(null);
            this.unlikeToken = str;
            setRequest(new Request(LikeActionController.this.session, str, null, HttpMethod.DELETE));
        }

        @Override // com.facebook.internal.LikeActionController.AbstractRequestWrapper
        protected void processSuccess(Response response) {
        }

        @Override // com.facebook.internal.LikeActionController.AbstractRequestWrapper
        protected void processError(FacebookRequestError facebookRequestError) {
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error unliking object with unlike token '%s' : %s", this.unlikeToken, facebookRequestError);
            LikeActionController.this.logAppEventForError("publish_unlike", facebookRequestError);
        }
    }

    /* loaded from: classes.dex */
    class GetOGObjectLikesRequestWrapper extends AbstractRequestWrapper {
        boolean objectIsLiked;
        String unlikeToken;

        GetOGObjectLikesRequestWrapper(String str) {
            super(str);
            this.objectIsLiked = LikeActionController.this.isObjectLiked;
            Bundle bundle = new Bundle();
            bundle.putString("fields", "id,application");
            bundle.putString("object", str);
            setRequest(new Request(LikeActionController.this.session, "me/og.likes", bundle, HttpMethod.GET));
        }

        @Override // com.facebook.internal.LikeActionController.AbstractRequestWrapper
        protected void processSuccess(Response response) {
            JSONArray tryGetJSONArrayFromResponse = Utility.tryGetJSONArrayFromResponse(response.getGraphObject(), "data");
            if (tryGetJSONArrayFromResponse != null) {
                for (int i = 0; i < tryGetJSONArrayFromResponse.length(); i++) {
                    JSONObject optJSONObject = tryGetJSONArrayFromResponse.optJSONObject(i);
                    if (optJSONObject != null) {
                        this.objectIsLiked = true;
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("application");
                        if (optJSONObject2 != null && Utility.areObjectsEqual(LikeActionController.this.session.getApplicationId(), optJSONObject2.optString("id"))) {
                            this.unlikeToken = optJSONObject.optString("id");
                        }
                    }
                }
            }
        }

        @Override // com.facebook.internal.LikeActionController.AbstractRequestWrapper
        protected void processError(FacebookRequestError facebookRequestError) {
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error fetching like status for object '%s' : %s", this.objectId, facebookRequestError);
            LikeActionController.this.logAppEventForError("get_og_object_like", facebookRequestError);
        }
    }

    /* loaded from: classes.dex */
    class GetEngagementRequestWrapper extends AbstractRequestWrapper {
        String likeCountStringWithLike;
        String likeCountStringWithoutLike;
        String socialSentenceStringWithLike;
        String socialSentenceStringWithoutLike;

        GetEngagementRequestWrapper(String str) {
            super(str);
            this.likeCountStringWithLike = LikeActionController.this.likeCountStringWithLike;
            this.likeCountStringWithoutLike = LikeActionController.this.likeCountStringWithoutLike;
            this.socialSentenceStringWithLike = LikeActionController.this.socialSentenceWithLike;
            this.socialSentenceStringWithoutLike = LikeActionController.this.socialSentenceWithoutLike;
            Bundle bundle = new Bundle();
            bundle.putString("fields", "engagement.fields(count_string_with_like,count_string_without_like,social_sentence_with_like,social_sentence_without_like)");
            setRequest(new Request(LikeActionController.this.session, str, bundle, HttpMethod.GET));
        }

        @Override // com.facebook.internal.LikeActionController.AbstractRequestWrapper
        protected void processSuccess(Response response) {
            JSONObject tryGetJSONObjectFromResponse = Utility.tryGetJSONObjectFromResponse(response.getGraphObject(), "engagement");
            if (tryGetJSONObjectFromResponse != null) {
                this.likeCountStringWithLike = tryGetJSONObjectFromResponse.optString("count_string_with_like", this.likeCountStringWithLike);
                this.likeCountStringWithoutLike = tryGetJSONObjectFromResponse.optString("count_string_without_like", this.likeCountStringWithoutLike);
                this.socialSentenceStringWithLike = tryGetJSONObjectFromResponse.optString(LikeActionController.JSON_STRING_SOCIAL_SENTENCE_WITH_LIKE_KEY, this.socialSentenceStringWithLike);
                this.socialSentenceStringWithoutLike = tryGetJSONObjectFromResponse.optString(LikeActionController.JSON_STRING_SOCIAL_SENTENCE_WITHOUT_LIKE_KEY, this.socialSentenceStringWithoutLike);
            }
        }

        @Override // com.facebook.internal.LikeActionController.AbstractRequestWrapper
        protected void processError(FacebookRequestError facebookRequestError) {
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error fetching engagement for object '%s' : %s", this.objectId, facebookRequestError);
            LikeActionController.this.logAppEventForError("get_engagement", facebookRequestError);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class AbstractRequestWrapper {
        FacebookRequestError error;
        protected String objectId;
        private Request request;

        protected abstract void processSuccess(Response response);

        protected AbstractRequestWrapper(String str) {
            this.objectId = str;
        }

        void addToBatch(RequestBatch requestBatch) {
            requestBatch.add(this.request);
        }

        protected void setRequest(Request request) {
            this.request = request;
            request.setVersion(ServerProtocol.GRAPH_API_VERSION);
            request.setCallback(new Request.Callback() { // from class: com.facebook.internal.LikeActionController.AbstractRequestWrapper.1
                @Override // com.facebook.Request.Callback
                public void onCompleted(Response response) {
                    AbstractRequestWrapper.this.error = response.getError();
                    if (AbstractRequestWrapper.this.error != null) {
                        AbstractRequestWrapper.this.processError(AbstractRequestWrapper.this.error);
                    } else {
                        AbstractRequestWrapper.this.processSuccess(response);
                    }
                }
            });
        }

        protected void processError(FacebookRequestError facebookRequestError) {
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error running request for object '%s' : %s", this.objectId, facebookRequestError);
        }
    }

    /* loaded from: classes.dex */
    enum LikeDialogFeature implements FacebookDialog.DialogFeature {
        LIKE_DIALOG(NativeProtocol.PROTOCOL_VERSION_20140701);

        private int minVersion;

        LikeDialogFeature(int i) {
            this.minVersion = i;
        }

        @Override // com.facebook.widget.FacebookDialog.DialogFeature
        public String getAction() {
            return NativeProtocol.ACTION_LIKE_DIALOG;
        }

        @Override // com.facebook.widget.FacebookDialog.DialogFeature
        public int getMinVersion() {
            return this.minVersion;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class LikeDialogBuilder extends FacebookDialog.Builder<LikeDialogBuilder> {
        private String objectId;

        public LikeDialogBuilder(Activity activity, String str) {
            super(activity);
            this.objectId = str;
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected EnumSet<? extends FacebookDialog.DialogFeature> getDialogFeatures() {
            return EnumSet.of(LikeDialogFeature.LIKE_DIALOG);
        }

        @Override // com.facebook.widget.FacebookDialog.Builder
        protected Bundle getMethodArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("object_id", this.objectId);
            return bundle;
        }

        public FacebookDialog.PendingCall getAppCall() {
            return this.appCall;
        }

        public String getApplicationId() {
            return this.applicationId;
        }

        public String getWebFallbackUrl() {
            return getWebFallbackUrlInternal();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MRUCacheWorkItem implements Runnable {
        private static ArrayList<String> mruCachedItems = new ArrayList<>();
        private String cacheItem;
        private boolean shouldTrim;

        MRUCacheWorkItem(String str, boolean z) {
            this.cacheItem = str;
            this.shouldTrim = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.cacheItem != null) {
                mruCachedItems.remove(this.cacheItem);
                mruCachedItems.add(0, this.cacheItem);
            }
            if (this.shouldTrim && mruCachedItems.size() >= 128) {
                while (64 < mruCachedItems.size()) {
                    LikeActionController.cache.remove(mruCachedItems.remove(mruCachedItems.size() - 1));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class SerializeToDiskWorkItem implements Runnable {
        private String cacheKey;
        private String controllerJson;

        SerializeToDiskWorkItem(String str, String str2) {
            this.cacheKey = str;
            this.controllerJson = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            LikeActionController.serializeToDiskSynchronously(this.cacheKey, this.controllerJson);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class CreateLikeActionControllerWorkItem implements Runnable {
        private CreationCallback callback;
        private String objectId;

        CreateLikeActionControllerWorkItem(String str, CreationCallback creationCallback) {
            this.objectId = str;
            this.callback = creationCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            LikeActionController.createControllerForObjectId(this.objectId, this.callback);
        }
    }
}
