package com.facebook;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.Session;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphUser;
import com.samsung.android.sdk.richnotification.Utilities;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class TestSession extends Session {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final String LOG_TAG = "FacebookSDK.TestSession";
    private static Map<String, TestAccount> appTestAccounts = null;
    private static final long serialVersionUID = 1;
    private static String testApplicationId;
    private static String testApplicationSecret;
    private final Mode mode;
    private final List<String> requestedPermissions;
    private final String sessionUniqueUserTag;
    private String testAccountId;
    private String testAccountUserName;
    private boolean wasAskedToExtendAccessToken;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum Mode {
        PRIVATE,
        SHARED
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface TestAccount extends GraphObject {
        String getAccessToken();

        String getId();

        String getName();

        void setName(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface TestAccountsResponse extends GraphObject {
        GraphObjectList<TestAccount> getData();
    }

    static {
        $assertionsDisabled = !TestSession.class.desiredAssertionStatus();
    }

    TestSession(Activity activity, List<String> list, TokenCachingStrategy tokenCachingStrategy, String str, Mode mode) {
        super(activity, testApplicationId, tokenCachingStrategy);
        Validate.notNull(list, NativeProtocol.RESULT_ARGS_PERMISSIONS);
        Validate.notNullOrEmpty(testApplicationId, "testApplicationId");
        Validate.notNullOrEmpty(testApplicationSecret, "testApplicationSecret");
        this.sessionUniqueUserTag = str;
        this.mode = mode;
        this.requestedPermissions = list;
    }

    public static TestSession createSessionWithPrivateUser(Activity activity, List<String> list) {
        return createTestSession(activity, list, Mode.PRIVATE, null);
    }

    public static TestSession createSessionWithSharedUser(Activity activity, List<String> list) {
        return createSessionWithSharedUser(activity, list, null);
    }

    public static TestSession createSessionWithSharedUser(Activity activity, List<String> list, String str) {
        return createTestSession(activity, list, Mode.SHARED, str);
    }

    public static synchronized String getTestApplicationId() {
        String str;
        synchronized (TestSession.class) {
            str = testApplicationId;
        }
        return str;
    }

    public static synchronized void setTestApplicationId(String str) {
        synchronized (TestSession.class) {
            if (testApplicationId != null && !testApplicationId.equals(str)) {
                throw new FacebookException("Can't have more than one test application ID");
            }
            testApplicationId = str;
        }
    }

    public static synchronized String getTestApplicationSecret() {
        String str;
        synchronized (TestSession.class) {
            str = testApplicationSecret;
        }
        return str;
    }

    public static synchronized void setTestApplicationSecret(String str) {
        synchronized (TestSession.class) {
            if (testApplicationSecret != null && !testApplicationSecret.equals(str)) {
                throw new FacebookException("Can't have more than one test application secret");
            }
            testApplicationSecret = str;
        }
    }

    public final String getTestUserId() {
        return this.testAccountId;
    }

    public final String getTestUserName() {
        return this.testAccountUserName;
    }

    private static synchronized TestSession createTestSession(Activity activity, List<String> list, Mode mode, String str) {
        TestSession testSession;
        synchronized (TestSession.class) {
            if (Utility.isNullOrEmpty(testApplicationId) || Utility.isNullOrEmpty(testApplicationSecret)) {
                throw new FacebookException("Must provide app ID and secret");
            }
            testSession = new TestSession(activity, Utility.isNullOrEmpty(list) ? Arrays.asList("email", "publish_actions") : list, new TestTokenCachingStrategy(), str, mode);
        }
        return testSession;
    }

    private static synchronized void retrieveTestAccountsForAppIfNeeded() {
        synchronized (TestSession.class) {
            if (appTestAccounts == null) {
                appTestAccounts = new HashMap();
                Request.setDefaultBatchApplicationId(testApplicationId);
                Bundle bundle = new Bundle();
                bundle.putString("access_token", getAppAccessToken());
                Request request = new Request(null, "app/accounts/test-users", bundle, null);
                request.setBatchEntryName("testUsers");
                request.setBatchEntryOmitResultOnSuccess(false);
                Bundle bundle2 = new Bundle();
                bundle2.putString("access_token", getAppAccessToken());
                bundle2.putString("ids", "{result=testUsers:$.data.*.id}");
                bundle2.putString("fields", Utilities.DB_KEY_IMAGE_NAME);
                Request request2 = new Request(null, "", bundle2, null);
                request2.setBatchEntryDependsOn("testUsers");
                List<Response> executeBatchAndWait = Request.executeBatchAndWait(request, request2);
                if (executeBatchAndWait == null || executeBatchAndWait.size() != 2) {
                    throw new FacebookException("Unexpected number of results from TestUsers batch query");
                }
                populateTestAccounts(((TestAccountsResponse) executeBatchAndWait.get(0).getGraphObjectAs(TestAccountsResponse.class)).getData(), executeBatchAndWait.get(1).getGraphObject());
            }
        }
    }

    private static synchronized void populateTestAccounts(Collection<TestAccount> collection, GraphObject graphObject) {
        synchronized (TestSession.class) {
            for (TestAccount testAccount : collection) {
                testAccount.setName(((GraphUser) graphObject.getPropertyAs(testAccount.getId(), GraphUser.class)).getName());
                storeTestAccount(testAccount);
            }
        }
    }

    private static synchronized void storeTestAccount(TestAccount testAccount) {
        synchronized (TestSession.class) {
            appTestAccounts.put(testAccount.getId(), testAccount);
        }
    }

    private static synchronized TestAccount findTestAccountMatchingIdentifier(String str) {
        TestAccount testAccount;
        synchronized (TestSession.class) {
            retrieveTestAccountsForAppIfNeeded();
            Iterator<TestAccount> it = appTestAccounts.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    testAccount = null;
                    break;
                }
                testAccount = it.next();
                if (testAccount.getName().contains(str)) {
                    break;
                }
            }
        }
        return testAccount;
    }

    @Override // com.facebook.Session
    public final String toString() {
        return "{TestSession testUserId:" + this.testAccountId + " " + super.toString() + "}";
    }

    @Override // com.facebook.Session
    void authorize(Session.AuthorizationRequest authorizationRequest) {
        if (this.mode == Mode.PRIVATE) {
            createTestAccountAndFinishAuth();
        } else {
            findOrCreateSharedTestAccount();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.facebook.Session
    public void postStateChange(SessionState sessionState, SessionState sessionState2, Exception exc) {
        String str = this.testAccountId;
        super.postStateChange(sessionState, sessionState2, exc);
        if (sessionState2.isClosed() && str != null && this.mode == Mode.PRIVATE) {
            deleteTestAccount(str, getAppAccessToken());
        }
    }

    boolean getWasAskedToExtendAccessToken() {
        return this.wasAskedToExtendAccessToken;
    }

    void forceExtendAccessToken(boolean z) {
        AccessToken tokenInfo = getTokenInfo();
        setTokenInfo(new AccessToken(tokenInfo.getToken(), new Date(), tokenInfo.getPermissions(), tokenInfo.getDeclinedPermissions(), AccessTokenSource.TEST_USER, new Date(0L)));
        setLastAttemptedTokenExtendDate(new Date(0L));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.facebook.Session
    public boolean shouldExtendAccessToken() {
        boolean shouldExtendAccessToken = super.shouldExtendAccessToken();
        this.wasAskedToExtendAccessToken = false;
        return shouldExtendAccessToken;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.facebook.Session
    public void extendAccessToken() {
        this.wasAskedToExtendAccessToken = true;
        super.extendAccessToken();
    }

    void fakeTokenRefreshAttempt() {
        setCurrentTokenRefreshRequest(new Session.TokenRefreshRequest());
    }

    static final String getAppAccessToken() {
        return testApplicationId + "|" + testApplicationSecret;
    }

    private void findOrCreateSharedTestAccount() {
        TestAccount findTestAccountMatchingIdentifier = findTestAccountMatchingIdentifier(getSharedTestAccountIdentifier());
        if (findTestAccountMatchingIdentifier != null) {
            finishAuthWithTestAccount(findTestAccountMatchingIdentifier);
        } else {
            createTestAccountAndFinishAuth();
        }
    }

    private void finishAuthWithTestAccount(TestAccount testAccount) {
        this.testAccountId = testAccount.getId();
        this.testAccountUserName = testAccount.getName();
        finishAuthOrReauth(AccessToken.createFromString(testAccount.getAccessToken(), this.requestedPermissions, AccessTokenSource.TEST_USER), null);
    }

    private TestAccount createTestAccountAndFinishAuth() {
        Bundle bundle = new Bundle();
        bundle.putString("installed", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        bundle.putString(NativeProtocol.RESULT_ARGS_PERMISSIONS, getPermissionsString());
        bundle.putString("access_token", getAppAccessToken());
        if (this.mode == Mode.SHARED) {
            bundle.putString(Utilities.DB_KEY_IMAGE_NAME, String.format("Shared %s Testuser", getSharedTestAccountIdentifier()));
        }
        Response executeAndWait = new Request(null, String.format("%s/accounts/test-users", testApplicationId), bundle, HttpMethod.POST).executeAndWait();
        FacebookRequestError error = executeAndWait.getError();
        TestAccount testAccount = (TestAccount) executeAndWait.getGraphObjectAs(TestAccount.class);
        if (error != null) {
            finishAuthOrReauth(null, error.getException());
            return null;
        }
        if (!$assertionsDisabled && testAccount == null) {
            throw new AssertionError();
        }
        if (this.mode == Mode.SHARED) {
            testAccount.setName(bundle.getString(Utilities.DB_KEY_IMAGE_NAME));
            storeTestAccount(testAccount);
        }
        finishAuthWithTestAccount(testAccount);
        return testAccount;
    }

    private void deleteTestAccount(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("access_token", str2);
        Response executeAndWait = new Request(null, str, bundle, HttpMethod.DELETE).executeAndWait();
        FacebookRequestError error = executeAndWait.getError();
        GraphObject graphObject = executeAndWait.getGraphObject();
        if (error != null) {
            Log.w(LOG_TAG, String.format("Could not delete test account %s: %s", str, error.getException().toString()));
        } else if (graphObject.getProperty(Response.NON_JSON_RESPONSE_PROPERTY) == false || graphObject.getProperty(Response.SUCCESS_KEY) == false) {
            Log.w(LOG_TAG, String.format("Could not delete test account %s: unknown reason", str));
        }
    }

    private String getPermissionsString() {
        return TextUtils.join(",", this.requestedPermissions);
    }

    private String getSharedTestAccountIdentifier() {
        return validNameStringFromInteger((this.sessionUniqueUserTag != null ? this.sessionUniqueUserTag.hashCode() & 4294967295L : 0L) ^ (getPermissionsString().hashCode() & 4294967295L));
    }

    private String validNameStringFromInteger(long j) {
        String l = Long.toString(j);
        StringBuilder sb = new StringBuilder("Perm");
        char[] charArray = l.toCharArray();
        int length = charArray.length;
        int i = 0;
        char c = 0;
        while (i < length) {
            char c2 = charArray[i];
            if (c2 == c) {
                c2 = (char) (c2 + '\n');
            }
            sb.append((char) ((c2 + 'a') - 48));
            i++;
            c = c2;
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class TestTokenCachingStrategy extends TokenCachingStrategy {
        private Bundle bundle;

        private TestTokenCachingStrategy() {
        }

        @Override // com.facebook.TokenCachingStrategy
        public Bundle load() {
            return this.bundle;
        }

        @Override // com.facebook.TokenCachingStrategy
        public void save(Bundle bundle) {
            this.bundle = bundle;
        }

        @Override // com.facebook.TokenCachingStrategy
        public void clear() {
            this.bundle = null;
        }
    }
}
