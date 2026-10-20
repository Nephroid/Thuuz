package twitter4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.AuthorizationFactory;
import twitter4j.auth.BasicAuthorization;
import twitter4j.auth.NullAuthorization;
import twitter4j.auth.OAuth2Authorization;
import twitter4j.auth.OAuth2Support;
import twitter4j.auth.OAuth2Token;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.OAuthSupport;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpClientWrapper;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.http.HttpResponseEvent;
import twitter4j.internal.http.HttpResponseListener;
import twitter4j.internal.http.XAuthAuthorization;
import twitter4j.internal.json.z_T4JInternalFactory;
import twitter4j.internal.json.z_T4JInternalJSONImplFactory;

/* loaded from: classes.dex */
abstract class TwitterBaseImpl implements Serializable, TwitterBase, OAuth2Support, OAuthSupport, HttpResponseListener {
    private static final long serialVersionUID = -3812176145960812140L;
    protected Authorization auth;
    protected Configuration conf;
    protected z_T4JInternalFactory factory;
    protected transient HttpClientWrapper http;
    protected transient String screenName = null;
    protected transient long id = 0;
    private List<RateLimitStatusListener> rateLimitStatusListeners = new ArrayList(0);

    /* JADX INFO: Access modifiers changed from: package-private */
    public TwitterBaseImpl(Configuration configuration, Authorization authorization) {
        this.conf = configuration;
        this.auth = authorization;
        init();
    }

    private void init() {
        if (this.auth == null) {
            String oAuthConsumerKey = this.conf.getOAuthConsumerKey();
            String oAuthConsumerSecret = this.conf.getOAuthConsumerSecret();
            if (oAuthConsumerKey != null && oAuthConsumerSecret != null) {
                if (this.conf.isApplicationOnlyAuthEnabled()) {
                    OAuth2Authorization oAuth2Authorization = new OAuth2Authorization(this.conf);
                    String oAuth2TokenType = this.conf.getOAuth2TokenType();
                    String oAuth2AccessToken = this.conf.getOAuth2AccessToken();
                    if (oAuth2TokenType != null && oAuth2AccessToken != null) {
                        oAuth2Authorization.setOAuth2Token(new OAuth2Token(oAuth2TokenType, oAuth2AccessToken));
                    }
                    this.auth = oAuth2Authorization;
                } else {
                    OAuthAuthorization oAuthAuthorization = new OAuthAuthorization(this.conf);
                    String oAuthAccessToken = this.conf.getOAuthAccessToken();
                    String oAuthAccessTokenSecret = this.conf.getOAuthAccessTokenSecret();
                    if (oAuthAccessToken != null && oAuthAccessTokenSecret != null) {
                        oAuthAuthorization.setOAuthAccessToken(new AccessToken(oAuthAccessToken, oAuthAccessTokenSecret));
                    }
                    this.auth = oAuthAuthorization;
                }
            } else {
                this.auth = NullAuthorization.getInstance();
            }
        }
        this.http = new HttpClientWrapper(this.conf);
        this.http.setHttpResponseListener(this);
        setFactory();
    }

    protected void setFactory() {
        this.factory = new z_T4JInternalJSONImplFactory(this.conf);
    }

    @Override // twitter4j.TwitterBase
    public String getScreenName() {
        if (!this.auth.isEnabled()) {
            throw new IllegalStateException("Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
        }
        if (this.screenName == null) {
            if (this.auth instanceof BasicAuthorization) {
                this.screenName = ((BasicAuthorization) this.auth).getUserId();
                if (-1 != this.screenName.indexOf("@")) {
                    this.screenName = null;
                }
            }
            if (this.screenName == null) {
                fillInIDAndScreenName();
            }
        }
        return this.screenName;
    }

    @Override // twitter4j.TwitterBase
    public long getId() {
        if (!this.auth.isEnabled()) {
            throw new IllegalStateException("Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
        }
        if (0 == this.id) {
            fillInIDAndScreenName();
        }
        return this.id;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public User fillInIDAndScreenName() {
        ensureAuthorizationEnabled();
        User createUser = this.factory.createUser(this.http.get(this.conf.getRestBaseURL() + "account/verify_credentials.json", this.auth));
        this.screenName = createUser.getScreenName();
        this.id = createUser.getId();
        return createUser;
    }

    @Override // twitter4j.TwitterBase
    public void addRateLimitStatusListener(RateLimitStatusListener rateLimitStatusListener) {
        this.rateLimitStatusListeners.add(rateLimitStatusListener);
    }

    @Override // twitter4j.internal.http.HttpResponseListener
    public void httpResponseReceived(HttpResponseEvent httpResponseEvent) {
        RateLimitStatus createRateLimitStatusFromResponseHeader;
        int statusCode;
        if (this.rateLimitStatusListeners.size() != 0) {
            HttpResponse response = httpResponseEvent.getResponse();
            TwitterException twitterException = httpResponseEvent.getTwitterException();
            if (twitterException != null) {
                createRateLimitStatusFromResponseHeader = twitterException.getRateLimitStatus();
                statusCode = twitterException.getStatusCode();
            } else {
                createRateLimitStatusFromResponseHeader = z_T4JInternalJSONImplFactory.createRateLimitStatusFromResponseHeader(response);
                statusCode = response.getStatusCode();
            }
            if (createRateLimitStatusFromResponseHeader != null) {
                RateLimitStatusEvent rateLimitStatusEvent = new RateLimitStatusEvent(this, createRateLimitStatusFromResponseHeader, httpResponseEvent.isAuthenticated());
                if (statusCode == 420 || statusCode == 503) {
                    for (RateLimitStatusListener rateLimitStatusListener : this.rateLimitStatusListeners) {
                        rateLimitStatusListener.onRateLimitStatus(rateLimitStatusEvent);
                        rateLimitStatusListener.onRateLimitReached(rateLimitStatusEvent);
                    }
                    return;
                }
                Iterator<RateLimitStatusListener> it = this.rateLimitStatusListeners.iterator();
                while (it.hasNext()) {
                    it.next().onRateLimitStatus(rateLimitStatusEvent);
                }
            }
        }
    }

    @Override // twitter4j.TwitterBase
    public final Authorization getAuthorization() {
        return this.auth;
    }

    @Override // twitter4j.TwitterBase
    public Configuration getConfiguration() {
        return this.conf;
    }

    @Override // twitter4j.TwitterBase
    public void shutdown() {
        if (this.http != null) {
            this.http.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ensureAuthorizationEnabled() {
        if (!this.auth.isEnabled()) {
            throw new IllegalStateException("Authentication credentials are missing. See http://twitter4j.org/en/configuration.html for the detail.");
        }
    }

    protected final void ensureOAuthEnabled() {
        if (!(this.auth instanceof OAuthAuthorization)) {
            throw new IllegalStateException("OAuth required. Authentication credentials are missing. See http://twitter4j.org/en/configuration.html for the detail.");
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.putFields();
        objectOutputStream.writeFields();
        objectOutputStream.writeObject(this.conf);
        objectOutputStream.writeObject(this.auth);
        ArrayList arrayList = new ArrayList(0);
        for (RateLimitStatusListener rateLimitStatusListener : this.rateLimitStatusListeners) {
            if (rateLimitStatusListener instanceof Serializable) {
                arrayList.add(rateLimitStatusListener);
            }
        }
        objectOutputStream.writeObject(arrayList);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readFields();
        this.conf = (Configuration) objectInputStream.readObject();
        this.auth = (Authorization) objectInputStream.readObject();
        this.rateLimitStatusListeners = (List) objectInputStream.readObject();
        this.http = new HttpClientWrapper(this.conf);
        this.http.setHttpResponseListener(this);
        setFactory();
    }

    @Override // twitter4j.auth.OAuth2Support, twitter4j.auth.OAuthSupport
    public synchronized void setOAuthConsumer(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("consumer key is null");
        }
        if (str2 == null) {
            throw new NullPointerException("consumer secret is null");
        }
        if (this.auth instanceof NullAuthorization) {
            if (this.conf.isApplicationOnlyAuthEnabled()) {
                OAuth2Authorization oAuth2Authorization = new OAuth2Authorization(this.conf);
                oAuth2Authorization.setOAuthConsumer(str, str2);
                this.auth = oAuth2Authorization;
            } else {
                OAuthAuthorization oAuthAuthorization = new OAuthAuthorization(this.conf);
                oAuthAuthorization.setOAuthConsumer(str, str2);
                this.auth = oAuthAuthorization;
            }
        } else if (this.auth instanceof BasicAuthorization) {
            XAuthAuthorization xAuthAuthorization = new XAuthAuthorization((BasicAuthorization) this.auth);
            xAuthAuthorization.setOAuthConsumer(str, str2);
            this.auth = xAuthAuthorization;
        } else if ((this.auth instanceof OAuthAuthorization) || (this.auth instanceof OAuth2Authorization)) {
            throw new IllegalStateException("consumer key/secret pair already set.");
        }
    }

    @Override // twitter4j.auth.OAuthSupport
    public RequestToken getOAuthRequestToken() {
        return getOAuthRequestToken(null);
    }

    @Override // twitter4j.auth.OAuthSupport
    public RequestToken getOAuthRequestToken(String str) {
        return getOAuth().getOAuthRequestToken(str);
    }

    @Override // twitter4j.auth.OAuthSupport
    public RequestToken getOAuthRequestToken(String str, String str2) {
        return getOAuth().getOAuthRequestToken(str, str2);
    }

    @Override // twitter4j.auth.OAuthSupport
    public synchronized AccessToken getOAuthAccessToken() {
        AccessToken oAuthAccessToken;
        Authorization authorization = getAuthorization();
        if (authorization instanceof BasicAuthorization) {
            BasicAuthorization basicAuthorization = (BasicAuthorization) authorization;
            Authorization authorizationFactory = AuthorizationFactory.getInstance(this.conf);
            if (authorizationFactory instanceof OAuthAuthorization) {
                this.auth = authorizationFactory;
                oAuthAccessToken = ((OAuthAuthorization) authorizationFactory).getOAuthAccessToken(basicAuthorization.getUserId(), basicAuthorization.getPassword());
            } else {
                throw new IllegalStateException("consumer key / secret combination not supplied.");
            }
        } else if (authorization instanceof XAuthAuthorization) {
            XAuthAuthorization xAuthAuthorization = (XAuthAuthorization) authorization;
            this.auth = xAuthAuthorization;
            OAuthAuthorization oAuthAuthorization = new OAuthAuthorization(this.conf);
            oAuthAuthorization.setOAuthConsumer(xAuthAuthorization.getConsumerKey(), xAuthAuthorization.getConsumerSecret());
            oAuthAccessToken = oAuthAuthorization.getOAuthAccessToken(xAuthAuthorization.getUserId(), xAuthAuthorization.getPassword());
        } else {
            oAuthAccessToken = getOAuth().getOAuthAccessToken();
        }
        this.screenName = oAuthAccessToken.getScreenName();
        this.id = oAuthAccessToken.getUserId();
        return oAuthAccessToken;
    }

    @Override // twitter4j.auth.OAuthSupport
    public synchronized AccessToken getOAuthAccessToken(String str) {
        AccessToken oAuthAccessToken;
        oAuthAccessToken = getOAuth().getOAuthAccessToken(str);
        this.screenName = oAuthAccessToken.getScreenName();
        return oAuthAccessToken;
    }

    @Override // twitter4j.auth.OAuthSupport
    public synchronized AccessToken getOAuthAccessToken(RequestToken requestToken) {
        AccessToken oAuthAccessToken;
        oAuthAccessToken = getOAuth().getOAuthAccessToken(requestToken);
        this.screenName = oAuthAccessToken.getScreenName();
        return oAuthAccessToken;
    }

    @Override // twitter4j.auth.OAuthSupport
    public synchronized AccessToken getOAuthAccessToken(RequestToken requestToken, String str) {
        return getOAuth().getOAuthAccessToken(requestToken, str);
    }

    @Override // twitter4j.auth.OAuthSupport
    public synchronized void setOAuthAccessToken(AccessToken accessToken) {
        getOAuth().setOAuthAccessToken(accessToken);
    }

    @Override // twitter4j.auth.OAuthSupport
    public synchronized AccessToken getOAuthAccessToken(String str, String str2) {
        return getOAuth().getOAuthAccessToken(str, str2);
    }

    private OAuthSupport getOAuth() {
        if (!(this.auth instanceof OAuthSupport)) {
            throw new IllegalStateException("OAuth consumer key/secret combination not supplied");
        }
        return (OAuthSupport) this.auth;
    }

    @Override // twitter4j.auth.OAuth2Support
    public synchronized OAuth2Token getOAuth2Token() {
        return getOAuth2().getOAuth2Token();
    }

    @Override // twitter4j.auth.OAuth2Support
    public void setOAuth2Token(OAuth2Token oAuth2Token) {
        getOAuth2().setOAuth2Token(oAuth2Token);
    }

    @Override // twitter4j.auth.OAuth2Support
    public synchronized void invalidateOAuth2Token() {
        getOAuth2().invalidateOAuth2Token();
    }

    private OAuth2Support getOAuth2() {
        if (!(this.auth instanceof OAuth2Support)) {
            throw new IllegalStateException("OAuth consumer key/secret combination not supplied");
        }
        return (OAuth2Support) this.auth;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TwitterBaseImpl)) {
            return false;
        }
        TwitterBaseImpl twitterBaseImpl = (TwitterBaseImpl) obj;
        if (this.auth == null ? twitterBaseImpl.auth != null : !this.auth.equals(twitterBaseImpl.auth)) {
            return false;
        }
        if (!this.conf.equals(twitterBaseImpl.conf)) {
            return false;
        }
        if (this.http == null ? twitterBaseImpl.http != null : !this.http.equals(twitterBaseImpl.http)) {
            return false;
        }
        return this.rateLimitStatusListeners.equals(twitterBaseImpl.rateLimitStatusListeners);
    }

    public int hashCode() {
        return (((((this.http != null ? this.http.hashCode() : 0) + (this.conf.hashCode() * 31)) * 31) + this.rateLimitStatusListeners.hashCode()) * 31) + (this.auth != null ? this.auth.hashCode() : 0);
    }

    public String toString() {
        return "TwitterBase{conf=" + this.conf + ", http=" + this.http + ", rateLimitStatusListeners=" + this.rateLimitStatusListeners + ", auth=" + this.auth + '}';
    }
}
