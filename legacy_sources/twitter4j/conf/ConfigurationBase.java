package twitter4j.conf;
// [removed legacy tracker import]
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.http.protocol.HTTP;
import twitter4j.Version;
import twitter4j.internal.logging.Logger;
import twitter4j.internal.util.z_T4JInternalStringUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ConfigurationBase implements Serializable, Configuration {
    public static final String DALVIK = "twitter4j.dalvik";
    private static final String DEFAULT_OAUTH2_INVALIDATE_TOKEN_URL = "https://api.twitter.com/oauth2/invalidate_token";
    private static final String DEFAULT_OAUTH2_TOKEN_URL = "https://api.twitter.com/oauth2/token";
    private static final String DEFAULT_OAUTH_ACCESS_TOKEN_URL = "http://api.twitter.com/oauth/access_token";
    private static final String DEFAULT_OAUTH_AUTHENTICATION_URL = "http://api.twitter.com/oauth/authenticate";
    private static final String DEFAULT_OAUTH_AUTHORIZATION_URL = "http://api.twitter.com/oauth/authorize";
    private static final String DEFAULT_OAUTH_REQUEST_TOKEN_URL = "http://api.twitter.com/oauth/request_token";
    private static final String DEFAULT_REST_BASE_URL = "http://api.twitter.com/1.1/";
    private static final String DEFAULT_SITE_STREAM_BASE_URL = "https://sitestream.twitter.com/1.1/";
    private static final String DEFAULT_STREAM_BASE_URL = "https://stream.twitter.com/1.1/";
    private static final String DEFAULT_USER_STREAM_BASE_URL = "https://userstream.twitter.com/1.1/";
    public static final String GAE = "twitter4j.gae";
    static String dalvikDetected = null;
    static String gaeDetected = null;
    private static final List<ConfigurationBase> instances;
    private static final long serialVersionUID = -6610497517837844232L;
    private boolean IS_DALVIK;
    private boolean IS_GAE;
    private int asyncNumThreads;
    private String clientURL;
    private String clientVersion;
    private long contributingTo;
    private boolean debug;
    private int defaultMaxPerRoute;
    private String dispatcherImpl;
    private boolean gzipEnabled;
    private int httpConnectionTimeout;
    private String httpProxyHost;
    private String httpProxyPassword;
    private int httpProxyPort;
    private String httpProxyUser;
    private int httpReadTimeout;
    private int httpRetryCount;
    private int httpRetryIntervalSeconds;
    private int httpStreamingReadTimeout;
    private boolean jsonStoreEnabled;
    private String loggerFactory;
    private int maxTotalConnections;
    private boolean mbeanEnabled;
    private String mediaProvider;
    private String mediaProviderAPIKey;
    private Properties mediaProviderParameters;
    private String oAuth2AccessToken;
    private String oAuth2InvalidateTokenURL;
    private String oAuth2TokenType;
    private String oAuth2TokenURL;
    private String oAuthAccessToken;
    private String oAuthAccessTokenSecret;
    private String oAuthAccessTokenURL;
    private String oAuthAuthenticationURL;
    private String oAuthAuthorizationURL;
    private String oAuthConsumerKey;
    private String oAuthConsumerSecret;
    private String oAuthRequestTokenURL;
    private String password;
    private boolean prettyDebug;
    Map<String, String> requestHeaders;
    private String restBaseURL;
    private String siteStreamBaseURL;
    private boolean stallWarningsEnabled;
    private String streamBaseURL;
    private boolean useSSL;
    private String user;
    private String userAgent;
    private String userStreamBaseURL;
    private boolean userStreamRepliesAllEnabled;
    private boolean includeRTsEnabled = true;
    private boolean includeEntitiesEnabled = true;
    private boolean includeMyRetweetEnabled = true;
    private boolean trimUserEnabled = false;
    private boolean applicationOnlyAuthEnabled = false;

    static {
        try {
            Class.forName("dalvik.system.VMRuntime");
            dalvikDetected = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
        } catch (ClassNotFoundException e) {
            dalvikDetected = "false";
        }
        try {
            Class.forName("com.google.appengine.api.urlfetch.URLFetchService");
            gaeDetected = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
        } catch (ClassNotFoundException e2) {
            gaeDetected = "false";
        }
        instances = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ConfigurationBase() {
        String str;
        String str2;
        setDebug(false);
        setUser(null);
        setPassword(null);
        setUseSSL(true);
        setPrettyDebugEnabled(false);
        setGZIPEnabled(true);
        setHttpProxyHost(null);
        setHttpProxyUser(null);
        setHttpProxyPassword(null);
        setHttpProxyPort(-1);
        setHttpConnectionTimeout(20000);
        setHttpReadTimeout(120000);
        setHttpStreamingReadTimeout(40000);
        setHttpRetryCount(0);
        setHttpRetryIntervalSeconds(5);
        setHttpMaxTotalConnections(20);
        setHttpDefaultMaxPerRoute(2);
        setOAuthConsumerKey(null);
        setOAuthConsumerSecret(null);
        setOAuthAccessToken(null);
        setOAuthAccessTokenSecret(null);
        setAsyncNumThreads(1);
        setContributingTo(-1L);
        setClientVersion(Version.getVersion());
        setClientURL("http://twitter4j.org/en/twitter4j-" + Version.getVersion() + ".xml");
        setUserAgent("twitter4j http://twitter4j.org/ /" + Version.getVersion());
        setJSONStoreEnabled(false);
        setMBeanEnabled(false);
        setOAuthRequestTokenURL(DEFAULT_OAUTH_REQUEST_TOKEN_URL);
        setOAuthAuthorizationURL(DEFAULT_OAUTH_AUTHORIZATION_URL);
        setOAuthAccessTokenURL(DEFAULT_OAUTH_ACCESS_TOKEN_URL);
        setOAuthAuthenticationURL(DEFAULT_OAUTH_AUTHENTICATION_URL);
        setOAuth2TokenURL(DEFAULT_OAUTH2_TOKEN_URL);
        setOAuth2InvalidateTokenURL(DEFAULT_OAUTH2_INVALIDATE_TOKEN_URL);
        setRestBaseURL(DEFAULT_REST_BASE_URL);
        setStreamBaseURL(DEFAULT_STREAM_BASE_URL);
        setUserStreamBaseURL(DEFAULT_USER_STREAM_BASE_URL);
        setSiteStreamBaseURL(DEFAULT_SITE_STREAM_BASE_URL);
        setDispatcherImpl("twitter4j.internal.async.DispatcherImpl");
        setLoggerFactory(null);
        setUserStreamRepliesAllEnabled(false);
        setStallWarningsEnabled(true);
        try {
            str = System.getProperty(DALVIK, dalvikDetected);
        } catch (SecurityException e) {
            str = dalvikDetected;
        }
        this.IS_DALVIK = Boolean.valueOf(str).booleanValue();
        try {
            str2 = System.getProperty(GAE, gaeDetected);
        } catch (SecurityException e2) {
            str2 = gaeDetected;
        }
        this.IS_GAE = Boolean.valueOf(str2).booleanValue();
        setMediaProvider("TWITTER");
        setMediaProviderAPIKey(null);
        setMediaProviderParameters(null);
    }

    public void dumpConfiguration() {
        Logger logger = Logger.getLogger(ConfigurationBase.class);
        if (this.debug) {
            for (Field field : ConfigurationBase.class.getDeclaredFields()) {
                try {
                    Object obj = field.get(this);
                    String valueOf = String.valueOf(obj);
                    if (obj != null && field.getName().matches("oAuthConsumerSecret|oAuthAccessTokenSecret|password")) {
                        valueOf = z_T4JInternalStringUtil.maskString(String.valueOf(obj));
                    }
                    logger.debug(field.getName() + ": " + valueOf);
                } catch (IllegalAccessException e) {
                }
            }
        }
        if (!this.includeRTsEnabled) {
            logger.warn("includeRTsEnabled is set to false. This configuration may not take effect after May 14th, 2012. https://dev.twitter.com/blog/api-housekeeping");
        }
        if (!this.includeEntitiesEnabled) {
            logger.warn("includeEntitiesEnabled is set to false. This configuration may not take effect after May 14th, 2012. https://dev.twitter.com/blog/api-housekeeping");
        }
    }

    @Override // twitter4j.conf.Configuration
    public final boolean isDalvik() {
        return this.IS_DALVIK;
    }

    @Override // twitter4j.conf.Configuration
    public boolean isGAE() {
        return this.IS_GAE;
    }

    @Override // twitter4j.conf.Configuration
    public final boolean isDebugEnabled() {
        return this.debug;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setDebug(boolean z) {
        this.debug = z;
    }

    @Override // twitter4j.conf.Configuration
    public final String getUserAgent() {
        return this.userAgent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setUserAgent(String str) {
        this.userAgent = str;
        initRequestHeaders();
    }

    @Override // twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public final String getUser() {
        return this.user;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setUser(String str) {
        this.user = str;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public final String getPassword() {
        return this.password;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setPassword(String str) {
        this.password = str;
    }

    @Override // twitter4j.internal.http.HttpClientConfiguration
    public boolean isPrettyDebugEnabled() {
        return this.prettyDebug;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setUseSSL(boolean z) {
        this.useSSL = z;
        fixRestBaseURL();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setPrettyDebugEnabled(boolean z) {
        this.prettyDebug = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setGZIPEnabled(boolean z) {
        this.gzipEnabled = z;
        initRequestHeaders();
    }

    @Override // twitter4j.internal.http.HttpClientConfiguration
    public boolean isGZIPEnabled() {
        return this.gzipEnabled;
    }

    private void initRequestHeaders() {
        this.requestHeaders = new HashMap();
        this.requestHeaders.put("X-Twitter-Client-Version", getClientVersion());
        this.requestHeaders.put("X-Twitter-Client-URL", getClientURL());
        this.requestHeaders.put("X-Twitter-Client", "Twitter4J");
        this.requestHeaders.put(HTTP.USER_AGENT, getUserAgent());
        if (this.gzipEnabled) {
            this.requestHeaders.put("Accept-Encoding", "gzip");
        }
        if (this.IS_DALVIK) {
            this.requestHeaders.put(HTTP.CONN_DIRECTIVE, "close");
        }
    }

    @Override // twitter4j.conf.Configuration, twitter4j.internal.http.HttpClientWrapperConfiguration
    public Map<String, String> getRequestHeaders() {
        return this.requestHeaders;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.internal.http.HttpClientConfiguration
    public final String getHttpProxyHost() {
        return this.httpProxyHost;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setHttpProxyHost(String str) {
        this.httpProxyHost = str;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.internal.http.HttpClientConfiguration
    public final String getHttpProxyUser() {
        return this.httpProxyUser;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setHttpProxyUser(String str) {
        this.httpProxyUser = str;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.internal.http.HttpClientConfiguration
    public final String getHttpProxyPassword() {
        return this.httpProxyPassword;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setHttpProxyPassword(String str) {
        this.httpProxyPassword = str;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.internal.http.HttpClientConfiguration
    public final int getHttpProxyPort() {
        return this.httpProxyPort;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setHttpProxyPort(int i) {
        this.httpProxyPort = i;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.internal.http.HttpClientConfiguration
    public final int getHttpConnectionTimeout() {
        return this.httpConnectionTimeout;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setHttpConnectionTimeout(int i) {
        this.httpConnectionTimeout = i;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.internal.http.HttpClientConfiguration
    public final int getHttpReadTimeout() {
        return this.httpReadTimeout;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setHttpReadTimeout(int i) {
        this.httpReadTimeout = i;
    }

    @Override // twitter4j.conf.Configuration
    public int getHttpStreamingReadTimeout() {
        return this.httpStreamingReadTimeout;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setHttpStreamingReadTimeout(int i) {
        this.httpStreamingReadTimeout = i;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.internal.http.HttpClientConfiguration
    public final int getHttpRetryCount() {
        return this.httpRetryCount;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setHttpRetryCount(int i) {
        this.httpRetryCount = i;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.internal.http.HttpClientConfiguration
    public final int getHttpRetryIntervalSeconds() {
        return this.httpRetryIntervalSeconds;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setHttpRetryIntervalSeconds(int i) {
        this.httpRetryIntervalSeconds = i;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.internal.http.HttpClientConfiguration
    public final int getHttpMaxTotalConnections() {
        return this.maxTotalConnections;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setHttpMaxTotalConnections(int i) {
        this.maxTotalConnections = i;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.internal.http.HttpClientConfiguration
    public final int getHttpDefaultMaxPerRoute() {
        return this.defaultMaxPerRoute;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setHttpDefaultMaxPerRoute(int i) {
        this.defaultMaxPerRoute = i;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public final String getOAuthConsumerKey() {
        return this.oAuthConsumerKey;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOAuthConsumerKey(String str) {
        this.oAuthConsumerKey = str;
        fixRestBaseURL();
    }

    @Override // twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public final String getOAuthConsumerSecret() {
        return this.oAuthConsumerSecret;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOAuthConsumerSecret(String str) {
        this.oAuthConsumerSecret = str;
        fixRestBaseURL();
    }

    @Override // twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public String getOAuthAccessToken() {
        return this.oAuthAccessToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOAuthAccessToken(String str) {
        this.oAuthAccessToken = str;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public String getOAuthAccessTokenSecret() {
        return this.oAuthAccessTokenSecret;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOAuthAccessTokenSecret(String str) {
        this.oAuthAccessTokenSecret = str;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public String getOAuth2TokenType() {
        return this.oAuth2TokenType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOAuth2TokenType(String str) {
        this.oAuth2TokenType = str;
    }

    @Override // twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public String getOAuth2AccessToken() {
        return this.oAuth2AccessToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOAuth2AccessToken(String str) {
        this.oAuth2AccessToken = str;
    }

    @Override // twitter4j.conf.Configuration
    public final int getAsyncNumThreads() {
        return this.asyncNumThreads;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setAsyncNumThreads(int i) {
        this.asyncNumThreads = i;
    }

    @Override // twitter4j.conf.Configuration
    public final long getContributingTo() {
        return this.contributingTo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setContributingTo(long j) {
        this.contributingTo = j;
    }

    @Override // twitter4j.conf.Configuration
    public final String getClientVersion() {
        return this.clientVersion;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setClientVersion(String str) {
        this.clientVersion = str;
        initRequestHeaders();
    }

    @Override // twitter4j.conf.Configuration
    public final String getClientURL() {
        return this.clientURL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setClientURL(String str) {
        this.clientURL = str;
        initRequestHeaders();
    }

    @Override // twitter4j.conf.Configuration
    public String getRestBaseURL() {
        return this.restBaseURL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setRestBaseURL(String str) {
        this.restBaseURL = str;
        fixRestBaseURL();
    }

    private void fixRestBaseURL() {
        if (DEFAULT_REST_BASE_URL.equals(fixURL(false, this.restBaseURL))) {
            this.restBaseURL = fixURL(this.useSSL, this.restBaseURL);
        }
        if (DEFAULT_OAUTH_ACCESS_TOKEN_URL.equals(fixURL(false, this.oAuthAccessTokenURL))) {
            this.oAuthAccessTokenURL = fixURL(this.useSSL, this.oAuthAccessTokenURL);
        }
        if (DEFAULT_OAUTH_AUTHENTICATION_URL.equals(fixURL(false, this.oAuthAuthenticationURL))) {
            this.oAuthAuthenticationURL = fixURL(this.useSSL, this.oAuthAuthenticationURL);
        }
        if (DEFAULT_OAUTH_AUTHORIZATION_URL.equals(fixURL(false, this.oAuthAuthorizationURL))) {
            this.oAuthAuthorizationURL = fixURL(this.useSSL, this.oAuthAuthorizationURL);
        }
        if (DEFAULT_OAUTH_REQUEST_TOKEN_URL.equals(fixURL(false, this.oAuthRequestTokenURL))) {
            this.oAuthRequestTokenURL = fixURL(this.useSSL, this.oAuthRequestTokenURL);
        }
    }

    @Override // twitter4j.conf.Configuration
    public String getStreamBaseURL() {
        return this.streamBaseURL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setStreamBaseURL(String str) {
        this.streamBaseURL = str;
    }

    @Override // twitter4j.conf.Configuration
    public String getUserStreamBaseURL() {
        return this.userStreamBaseURL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setUserStreamBaseURL(String str) {
        this.userStreamBaseURL = str;
    }

    @Override // twitter4j.conf.Configuration
    public String getSiteStreamBaseURL() {
        return this.siteStreamBaseURL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setSiteStreamBaseURL(String str) {
        this.siteStreamBaseURL = str;
    }

    @Override // twitter4j.conf.Configuration
    public String getOAuthRequestTokenURL() {
        return this.oAuthRequestTokenURL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOAuthRequestTokenURL(String str) {
        this.oAuthRequestTokenURL = str;
        fixRestBaseURL();
    }

    @Override // twitter4j.conf.Configuration
    public String getOAuthAuthorizationURL() {
        return this.oAuthAuthorizationURL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOAuthAuthorizationURL(String str) {
        this.oAuthAuthorizationURL = str;
        fixRestBaseURL();
    }

    @Override // twitter4j.conf.Configuration
    public String getOAuthAccessTokenURL() {
        return this.oAuthAccessTokenURL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOAuthAccessTokenURL(String str) {
        this.oAuthAccessTokenURL = str;
        fixRestBaseURL();
    }

    @Override // twitter4j.conf.Configuration
    public String getOAuthAuthenticationURL() {
        return this.oAuthAuthenticationURL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOAuthAuthenticationURL(String str) {
        this.oAuthAuthenticationURL = str;
        fixRestBaseURL();
    }

    @Override // twitter4j.conf.Configuration
    public String getOAuth2TokenURL() {
        return this.oAuth2TokenURL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOAuth2TokenURL(String str) {
        this.oAuth2TokenURL = str;
        fixRestBaseURL();
    }

    @Override // twitter4j.conf.Configuration
    public String getOAuth2InvalidateTokenURL() {
        return this.oAuth2InvalidateTokenURL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOAuth2InvalidateTokenURL(String str) {
        this.oAuth2InvalidateTokenURL = str;
        fixRestBaseURL();
    }

    @Override // twitter4j.conf.Configuration
    public String getDispatcherImpl() {
        return this.dispatcherImpl;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setDispatcherImpl(String str) {
        this.dispatcherImpl = str;
    }

    @Override // twitter4j.conf.Configuration
    public String getLoggerFactory() {
        return this.loggerFactory;
    }

    @Override // twitter4j.conf.Configuration
    public boolean isIncludeRTsEnabled() {
        return this.includeRTsEnabled;
    }

    @Override // twitter4j.conf.Configuration
    public boolean isIncludeEntitiesEnabled() {
        return this.includeEntitiesEnabled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setLoggerFactory(String str) {
        this.loggerFactory = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setIncludeRTsEnbled(boolean z) {
        this.includeRTsEnabled = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setIncludeEntitiesEnbled(boolean z) {
        this.includeEntitiesEnabled = z;
    }

    @Override // twitter4j.conf.Configuration
    public boolean isIncludeMyRetweetEnabled() {
        return this.includeMyRetweetEnabled;
    }

    public void setIncludeMyRetweetEnabled(boolean z) {
        this.includeMyRetweetEnabled = z;
    }

    @Override // twitter4j.conf.Configuration
    public boolean isTrimUserEnabled() {
        return this.trimUserEnabled;
    }

    public void setTrimUserEnabled(boolean z) {
        this.trimUserEnabled = z;
    }

    @Override // twitter4j.conf.Configuration
    public boolean isJSONStoreEnabled() {
        return this.jsonStoreEnabled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setJSONStoreEnabled(boolean z) {
        this.jsonStoreEnabled = z;
    }

    @Override // twitter4j.conf.Configuration
    public boolean isMBeanEnabled() {
        return this.mbeanEnabled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setMBeanEnabled(boolean z) {
        this.mbeanEnabled = z;
    }

    @Override // twitter4j.conf.Configuration
    public boolean isUserStreamRepliesAllEnabled() {
        return this.userStreamRepliesAllEnabled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setUserStreamRepliesAllEnabled(boolean z) {
        this.userStreamRepliesAllEnabled = z;
    }

    @Override // twitter4j.conf.Configuration
    public boolean isStallWarningsEnabled() {
        return this.stallWarningsEnabled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setStallWarningsEnabled(boolean z) {
        this.stallWarningsEnabled = z;
    }

    @Override // twitter4j.conf.Configuration
    public boolean isApplicationOnlyAuthEnabled() {
        return this.applicationOnlyAuthEnabled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setApplicationOnlyAuthEnabled(boolean z) {
        this.applicationOnlyAuthEnabled = z;
    }

    @Override // twitter4j.conf.Configuration
    public String getMediaProvider() {
        return this.mediaProvider;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setMediaProvider(String str) {
        this.mediaProvider = str;
    }

    @Override // twitter4j.conf.Configuration
    public String getMediaProviderAPIKey() {
        return this.mediaProviderAPIKey;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setMediaProviderAPIKey(String str) {
        this.mediaProviderAPIKey = str;
    }

    @Override // twitter4j.conf.Configuration
    public Properties getMediaProviderParameters() {
        return this.mediaProviderParameters;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setMediaProviderParameters(Properties properties) {
        this.mediaProviderParameters = properties;
    }

    static String fixURL(boolean z, String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf("://");
        if (-1 == indexOf) {
            throw new IllegalArgumentException("url should contain '://'");
        }
        String substring = str.substring(indexOf + 3);
        if (z) {
            return "https://" + substring;
        }
        return "http://" + substring;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ConfigurationBase configurationBase = (ConfigurationBase) obj;
        if (this.IS_DALVIK == configurationBase.IS_DALVIK && this.IS_GAE == configurationBase.IS_GAE && this.asyncNumThreads == configurationBase.asyncNumThreads && this.contributingTo == configurationBase.contributingTo && this.debug == configurationBase.debug && this.defaultMaxPerRoute == configurationBase.defaultMaxPerRoute && this.gzipEnabled == configurationBase.gzipEnabled && this.httpConnectionTimeout == configurationBase.httpConnectionTimeout && this.httpProxyPort == configurationBase.httpProxyPort && this.httpReadTimeout == configurationBase.httpReadTimeout && this.httpRetryCount == configurationBase.httpRetryCount && this.httpRetryIntervalSeconds == configurationBase.httpRetryIntervalSeconds && this.httpStreamingReadTimeout == configurationBase.httpStreamingReadTimeout && this.includeEntitiesEnabled == configurationBase.includeEntitiesEnabled && this.includeMyRetweetEnabled == configurationBase.includeMyRetweetEnabled && this.trimUserEnabled == configurationBase.trimUserEnabled && this.includeRTsEnabled == configurationBase.includeRTsEnabled && this.jsonStoreEnabled == configurationBase.jsonStoreEnabled && this.maxTotalConnections == configurationBase.maxTotalConnections && this.mbeanEnabled == configurationBase.mbeanEnabled && this.prettyDebug == configurationBase.prettyDebug && this.stallWarningsEnabled == configurationBase.stallWarningsEnabled && this.applicationOnlyAuthEnabled == configurationBase.applicationOnlyAuthEnabled && this.useSSL == configurationBase.useSSL && this.userStreamRepliesAllEnabled == configurationBase.userStreamRepliesAllEnabled) {
            if (this.clientURL == null ? configurationBase.clientURL != null : !this.clientURL.equals(configurationBase.clientURL)) {
                return false;
            }
            if (this.clientVersion == null ? configurationBase.clientVersion != null : !this.clientVersion.equals(configurationBase.clientVersion)) {
                return false;
            }
            if (this.dispatcherImpl == null ? configurationBase.dispatcherImpl != null : !this.dispatcherImpl.equals(configurationBase.dispatcherImpl)) {
                return false;
            }
            if (this.httpProxyHost == null ? configurationBase.httpProxyHost != null : !this.httpProxyHost.equals(configurationBase.httpProxyHost)) {
                return false;
            }
            if (this.httpProxyPassword == null ? configurationBase.httpProxyPassword != null : !this.httpProxyPassword.equals(configurationBase.httpProxyPassword)) {
                return false;
            }
            if (this.httpProxyUser == null ? configurationBase.httpProxyUser != null : !this.httpProxyUser.equals(configurationBase.httpProxyUser)) {
                return false;
            }
            if (this.loggerFactory == null ? configurationBase.loggerFactory != null : !this.loggerFactory.equals(configurationBase.loggerFactory)) {
                return false;
            }
            if (this.mediaProvider == null ? configurationBase.mediaProvider != null : !this.mediaProvider.equals(configurationBase.mediaProvider)) {
                return false;
            }
            if (this.mediaProviderAPIKey == null ? configurationBase.mediaProviderAPIKey != null : !this.mediaProviderAPIKey.equals(configurationBase.mediaProviderAPIKey)) {
                return false;
            }
            if (this.mediaProviderParameters == null ? configurationBase.mediaProviderParameters != null : !this.mediaProviderParameters.equals(configurationBase.mediaProviderParameters)) {
                return false;
            }
            if (this.oAuthAccessToken == null ? configurationBase.oAuthAccessToken != null : !this.oAuthAccessToken.equals(configurationBase.oAuthAccessToken)) {
                return false;
            }
            if (this.oAuthAccessTokenSecret == null ? configurationBase.oAuthAccessTokenSecret != null : !this.oAuthAccessTokenSecret.equals(configurationBase.oAuthAccessTokenSecret)) {
                return false;
            }
            if (this.oAuth2TokenType == null ? configurationBase.oAuth2TokenType != null : !this.oAuth2TokenType.equals(configurationBase.oAuth2TokenType)) {
                return false;
            }
            if (this.oAuth2AccessToken == null ? configurationBase.oAuth2AccessToken != null : !this.oAuth2AccessToken.equals(configurationBase.oAuth2AccessToken)) {
                return false;
            }
            if (this.oAuthAccessTokenURL == null ? configurationBase.oAuthAccessTokenURL != null : !this.oAuthAccessTokenURL.equals(configurationBase.oAuthAccessTokenURL)) {
                return false;
            }
            if (this.oAuthAuthenticationURL == null ? configurationBase.oAuthAuthenticationURL != null : !this.oAuthAuthenticationURL.equals(configurationBase.oAuthAuthenticationURL)) {
                return false;
            }
            if (this.oAuthAuthorizationURL == null ? configurationBase.oAuthAuthorizationURL != null : !this.oAuthAuthorizationURL.equals(configurationBase.oAuthAuthorizationURL)) {
                return false;
            }
            if (this.oAuth2TokenURL == null ? configurationBase.oAuth2TokenURL != null : !this.oAuth2TokenURL.equals(configurationBase.oAuth2TokenURL)) {
                return false;
            }
            if (this.oAuth2InvalidateTokenURL == null ? configurationBase.oAuth2InvalidateTokenURL != null : !this.oAuth2InvalidateTokenURL.equals(configurationBase.oAuth2InvalidateTokenURL)) {
                return false;
            }
            if (this.oAuthConsumerKey == null ? configurationBase.oAuthConsumerKey != null : !this.oAuthConsumerKey.equals(configurationBase.oAuthConsumerKey)) {
                return false;
            }
            if (this.oAuthConsumerSecret == null ? configurationBase.oAuthConsumerSecret != null : !this.oAuthConsumerSecret.equals(configurationBase.oAuthConsumerSecret)) {
                return false;
            }
            if (this.oAuthRequestTokenURL == null ? configurationBase.oAuthRequestTokenURL != null : !this.oAuthRequestTokenURL.equals(configurationBase.oAuthRequestTokenURL)) {
                return false;
            }
            if (this.password == null ? configurationBase.password != null : !this.password.equals(configurationBase.password)) {
                return false;
            }
            if (this.requestHeaders == null ? configurationBase.requestHeaders != null : !this.requestHeaders.equals(configurationBase.requestHeaders)) {
                return false;
            }
            if (this.restBaseURL == null ? configurationBase.restBaseURL != null : !this.restBaseURL.equals(configurationBase.restBaseURL)) {
                return false;
            }
            if (this.siteStreamBaseURL == null ? configurationBase.siteStreamBaseURL != null : !this.siteStreamBaseURL.equals(configurationBase.siteStreamBaseURL)) {
                return false;
            }
            if (this.streamBaseURL == null ? configurationBase.streamBaseURL != null : !this.streamBaseURL.equals(configurationBase.streamBaseURL)) {
                return false;
            }
            if (this.user == null ? configurationBase.user != null : !this.user.equals(configurationBase.user)) {
                return false;
            }
            if (this.userAgent == null ? configurationBase.userAgent != null : !this.userAgent.equals(configurationBase.userAgent)) {
                return false;
            }
            if (this.userStreamBaseURL != null) {
                if (this.userStreamBaseURL.equals(configurationBase.userStreamBaseURL)) {
                    return true;
                }
            } else if (configurationBase.userStreamBaseURL == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.IS_DALVIK ? 1 : 0) + (((this.clientURL != null ? this.clientURL.hashCode() : 0) + (((this.clientVersion != null ? this.clientVersion.hashCode() : 0) + (((this.mediaProviderParameters != null ? this.mediaProviderParameters.hashCode() : 0) + (((this.mediaProviderAPIKey != null ? this.mediaProviderAPIKey.hashCode() : 0) + (((this.mediaProvider != null ? this.mediaProvider.hashCode() : 0) + (((this.applicationOnlyAuthEnabled ? 1 : 0) + (((this.stallWarningsEnabled ? 1 : 0) + (((this.userStreamRepliesAllEnabled ? 1 : 0) + (((this.mbeanEnabled ? 1 : 0) + (((this.jsonStoreEnabled ? 1 : 0) + (((this.trimUserEnabled ? 1 : 0) + (((this.includeMyRetweetEnabled ? 1 : 0) + (((this.includeEntitiesEnabled ? 1 : 0) + (((this.includeRTsEnabled ? 1 : 0) + (((((((this.loggerFactory != null ? this.loggerFactory.hashCode() : 0) + (((this.dispatcherImpl != null ? this.dispatcherImpl.hashCode() : 0) + (((this.siteStreamBaseURL != null ? this.siteStreamBaseURL.hashCode() : 0) + (((this.userStreamBaseURL != null ? this.userStreamBaseURL.hashCode() : 0) + (((this.streamBaseURL != null ? this.streamBaseURL.hashCode() : 0) + (((this.restBaseURL != null ? this.restBaseURL.hashCode() : 0) + (((this.oAuth2InvalidateTokenURL != null ? this.oAuth2InvalidateTokenURL.hashCode() : 0) + (((this.oAuth2TokenURL != null ? this.oAuth2TokenURL.hashCode() : 0) + (((this.oAuthAuthenticationURL != null ? this.oAuthAuthenticationURL.hashCode() : 0) + (((this.oAuthAccessTokenURL != null ? this.oAuthAccessTokenURL.hashCode() : 0) + (((this.oAuthAuthorizationURL != null ? this.oAuthAuthorizationURL.hashCode() : 0) + (((this.oAuthRequestTokenURL != null ? this.oAuthRequestTokenURL.hashCode() : 0) + (((this.oAuth2AccessToken != null ? this.oAuth2AccessToken.hashCode() : 0) + (((this.oAuth2TokenType != null ? this.oAuth2TokenType.hashCode() : 0) + (((this.oAuthAccessTokenSecret != null ? this.oAuthAccessTokenSecret.hashCode() : 0) + (((this.oAuthAccessToken != null ? this.oAuthAccessToken.hashCode() : 0) + (((this.oAuthConsumerSecret != null ? this.oAuthConsumerSecret.hashCode() : 0) + (((this.oAuthConsumerKey != null ? this.oAuthConsumerKey.hashCode() : 0) + (((((((((((((((((((this.httpProxyPassword != null ? this.httpProxyPassword.hashCode() : 0) + (((this.httpProxyUser != null ? this.httpProxyUser.hashCode() : 0) + (((this.httpProxyHost != null ? this.httpProxyHost.hashCode() : 0) + (((this.gzipEnabled ? 1 : 0) + (((this.prettyDebug ? 1 : 0) + (((this.useSSL ? 1 : 0) + (((this.password != null ? this.password.hashCode() : 0) + (((this.user != null ? this.user.hashCode() : 0) + (((this.userAgent != null ? this.userAgent.hashCode() : 0) + ((this.debug ? 1 : 0) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + this.httpProxyPort) * 31) + this.httpConnectionTimeout) * 31) + this.httpReadTimeout) * 31) + this.httpStreamingReadTimeout) * 31) + this.httpRetryCount) * 31) + this.httpRetryIntervalSeconds) * 31) + this.maxTotalConnections) * 31) + this.defaultMaxPerRoute) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + this.asyncNumThreads) * 31) + ((int) (this.contributingTo ^ (this.contributingTo >>> 32)))) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + (this.IS_GAE ? 1 : 0)) * 31) + (this.requestHeaders != null ? this.requestHeaders.hashCode() : 0);
    }

    public String toString() {
        return "ConfigurationBase{debug=" + this.debug + ", userAgent='" + this.userAgent + "', user='" + this.user + "', password='" + this.password + "', useSSL=" + this.useSSL + ", prettyDebug=" + this.prettyDebug + ", gzipEnabled=" + this.gzipEnabled + ", httpProxyHost='" + this.httpProxyHost + "', httpProxyUser='" + this.httpProxyUser + "', httpProxyPassword='" + this.httpProxyPassword + "', httpProxyPort=" + this.httpProxyPort + ", httpConnectionTimeout=" + this.httpConnectionTimeout + ", httpReadTimeout=" + this.httpReadTimeout + ", httpStreamingReadTimeout=" + this.httpStreamingReadTimeout + ", httpRetryCount=" + this.httpRetryCount + ", httpRetryIntervalSeconds=" + this.httpRetryIntervalSeconds + ", maxTotalConnections=" + this.maxTotalConnections + ", defaultMaxPerRoute=" + this.defaultMaxPerRoute + ", oAuthConsumerKey='" + this.oAuthConsumerKey + "', oAuthConsumerSecret='" + this.oAuthConsumerSecret + "', oAuthAccessToken='" + this.oAuthAccessToken + "', oAuthAccessTokenSecret='" + this.oAuthAccessTokenSecret + "', oAuth2TokenType='" + this.oAuth2TokenType + "', oAuth2AccessToken='" + this.oAuth2AccessToken + "', oAuthRequestTokenURL='" + this.oAuthRequestTokenURL + "', oAuthAuthorizationURL='" + this.oAuthAuthorizationURL + "', oAuthAccessTokenURL='" + this.oAuthAccessTokenURL + "', oAuthAuthenticationURL='" + this.oAuthAuthenticationURL + "', oAuth2TokenURL='" + this.oAuth2TokenURL + "', oAuth2InvalidateTokenURL='" + this.oAuth2InvalidateTokenURL + "', restBaseURL='" + this.restBaseURL + "', streamBaseURL='" + this.streamBaseURL + "', userStreamBaseURL='" + this.userStreamBaseURL + "', siteStreamBaseURL='" + this.siteStreamBaseURL + "', dispatcherImpl='" + this.dispatcherImpl + "', loggerFactory='" + this.loggerFactory + "', asyncNumThreads=" + this.asyncNumThreads + ", contributingTo=" + this.contributingTo + ", includeRTsEnabled=" + this.includeRTsEnabled + ", includeEntitiesEnabled=" + this.includeEntitiesEnabled + ", includeMyRetweetEnabled=" + this.includeMyRetweetEnabled + ", trimUserEnabled=" + this.trimUserEnabled + ", jsonStoreEnabled=" + this.jsonStoreEnabled + ", mbeanEnabled=" + this.mbeanEnabled + ", userStreamRepliesAllEnabled=" + this.userStreamRepliesAllEnabled + ", stallWarningsEnabled=" + this.stallWarningsEnabled + ", applicationOnlyAuthEnabled=" + this.applicationOnlyAuthEnabled + ", mediaProvider='" + this.mediaProvider + "', mediaProviderAPIKey='" + this.mediaProviderAPIKey + "', mediaProviderParameters=" + this.mediaProviderParameters + ", clientVersion='" + this.clientVersion + "', clientURL='" + this.clientURL + "', IS_DALVIK=" + this.IS_DALVIK + ", IS_GAE=" + this.IS_GAE + ", requestHeaders=" + this.requestHeaders + '}';
    }

    private static void cacheInstance(ConfigurationBase configurationBase) {
        if (!instances.contains(configurationBase)) {
            instances.add(configurationBase);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void cacheInstance() {
        cacheInstance(this);
    }

    private static ConfigurationBase getInstance(ConfigurationBase configurationBase) {
        int indexOf = instances.indexOf(configurationBase);
        if (indexOf != -1) {
            return instances.get(indexOf);
        }
        instances.add(configurationBase);
        return configurationBase;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object readResolve() {
        return getInstance(this);
    }
}
