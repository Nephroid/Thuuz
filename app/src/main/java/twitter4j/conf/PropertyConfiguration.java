package twitter4j.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import twitter4j.internal.util.z_T4JInternalStringUtil;

/* loaded from: classes.dex */
public final class PropertyConfiguration extends ConfigurationBase implements Serializable {
    public static final String APPLICATION_ONLY_AUTH_ENABLED = "enableApplicationOnlyAuth";
    public static final String ASYNC_DISPATCHER_IMPL = "async.dispatcherImpl";
    public static final String ASYNC_NUM_THREADS = "async.numThreads";
    public static final String CLIENT_URL = "clientURL";
    public static final String CLIENT_VERSION = "clientVersion";
    public static final String CONTRIBUTING_TO = "contributingTo";
    public static final String DEBUG = "debug";
    public static final String HTTP_CONNECTION_TIMEOUT = "http.connectionTimeout";
    public static final String HTTP_DEFAULT_MAX_PER_ROUTE = "http.defaultMaxPerRoute";
    public static final String HTTP_GZIP = "http.gzip";
    public static final String HTTP_MAX_TOTAL_CONNECTIONS = "http.maxTotalConnections";
    public static final String HTTP_PRETTY_DEBUG = "http.prettyDebug";
    public static final String HTTP_PROXY_HOST = "http.proxyHost";
    public static final String HTTP_PROXY_HOST_FALLBACK = "http.proxyHost";
    public static final String HTTP_PROXY_PASSWORD = "http.proxyPassword";
    public static final String HTTP_PROXY_PORT = "http.proxyPort";
    public static final String HTTP_PROXY_PORT_FALLBACK = "http.proxyPort";
    public static final String HTTP_PROXY_USER = "http.proxyUser";
    public static final String HTTP_READ_TIMEOUT = "http.readTimeout";
    public static final String HTTP_RETRY_COUNT = "http.retryCount";
    public static final String HTTP_RETRY_INTERVAL_SECS = "http.retryIntervalSecs";
    public static final String HTTP_STREAMING_READ_TIMEOUT = "http.streamingReadTimeout";
    public static final String HTTP_USER_AGENT = "http.userAgent";
    public static final String HTTP_USE_SSL = "http.useSSL";
    public static final String INCLUDE_ENTITIES = "includeEntities";
    public static final String INCLUDE_MY_RETWEET = "includeMyRetweet";
    public static final String INCLUDE_RTS = "includeRTs";
    public static final String JSON_STORE_ENABLED = "jsonStoreEnabled";
    public static final String LOGGER_FACTORY = "loggerFactory";
    public static final String MBEAN_ENABLED = "mbeanEnabled";
    public static final String MEDIA_PROVIDER = "media.provider";
    public static final String MEDIA_PROVIDER_API_KEY = "media.providerAPIKey";
    public static final String MEDIA_PROVIDER_PARAMETERS = "media.providerParameters";
    public static final String OAUTH2_ACCESS_TOKEN = "oauth2.accessToken";
    public static final String OAUTH2_INVALIDATE_TOKEN_URL = "oauth2.invalidateTokenURL";
    public static final String OAUTH2_TOKEN_TYPE = "oauth2.tokenType";
    public static final String OAUTH2_TOKEN_URL = "oauth2.tokenURL";
    public static final String OAUTH_ACCESS_TOKEN = "oauth.accessToken";
    public static final String OAUTH_ACCESS_TOKEN_SECRET = "oauth.accessTokenSecret";
    public static final String OAUTH_ACCESS_TOKEN_URL = "oauth.accessTokenURL";
    public static final String OAUTH_AUTHENTICATION_URL = "oauth.authenticationURL";
    public static final String OAUTH_AUTHORIZATION_URL = "oauth.authorizationURL";
    public static final String OAUTH_CONSUMER_KEY = "oauth.consumerKey";
    public static final String OAUTH_CONSUMER_SECRET = "oauth.consumerSecret";
    public static final String OAUTH_REQUEST_TOKEN_URL = "oauth.requestTokenURL";
    public static final String PASSWORD = "password";
    public static final String REST_BASE_URL = "restBaseURL";
    public static final String SITE_STREAM_BASE_URL = "siteStreamBaseURL";
    public static final String STREAM_BASE_URL = "streamBaseURL";
    public static final String STREAM_STALL_WARNINGS_ENABLED = "stream.enableStallWarnings";
    public static final String STREAM_USER_REPLIES_ALL = "stream.user.repliesAll";
    public static final String USER = "user";
    public static final String USER_STREAM_BASE_URL = "userStreamBaseURL";
    private static final long serialVersionUID = 6458764415636588373L;

    @Override // twitter4j.conf.ConfigurationBase
    public /* bridge */ /* synthetic */ void dumpConfiguration() {
        super.dumpConfiguration();
    }

    @Override // twitter4j.conf.ConfigurationBase
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getDispatcherImpl() {
        return super.getDispatcherImpl();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ int getHttpStreamingReadTimeout() {
        return super.getHttpStreamingReadTimeout();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getLoggerFactory() {
        return super.getLoggerFactory();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getMediaProvider() {
        return super.getMediaProvider();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getMediaProviderAPIKey() {
        return super.getMediaProviderAPIKey();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ Properties getMediaProviderParameters() {
        return super.getMediaProviderParameters();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public /* bridge */ /* synthetic */ String getOAuth2AccessToken() {
        return super.getOAuth2AccessToken();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getOAuth2InvalidateTokenURL() {
        return super.getOAuth2InvalidateTokenURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public /* bridge */ /* synthetic */ String getOAuth2TokenType() {
        return super.getOAuth2TokenType();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getOAuth2TokenURL() {
        return super.getOAuth2TokenURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public /* bridge */ /* synthetic */ String getOAuthAccessToken() {
        return super.getOAuthAccessToken();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration, twitter4j.auth.AuthorizationConfiguration
    public /* bridge */ /* synthetic */ String getOAuthAccessTokenSecret() {
        return super.getOAuthAccessTokenSecret();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getOAuthAccessTokenURL() {
        return super.getOAuthAccessTokenURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getOAuthAuthenticationURL() {
        return super.getOAuthAuthenticationURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getOAuthAuthorizationURL() {
        return super.getOAuthAuthorizationURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getOAuthRequestTokenURL() {
        return super.getOAuthRequestTokenURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration, twitter4j.internal.http.HttpClientWrapperConfiguration
    public /* bridge */ /* synthetic */ Map getRequestHeaders() {
        return super.getRequestHeaders();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getRestBaseURL() {
        return super.getRestBaseURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getSiteStreamBaseURL() {
        return super.getSiteStreamBaseURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getStreamBaseURL() {
        return super.getStreamBaseURL();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ String getUserStreamBaseURL() {
        return super.getUserStreamBaseURL();
    }

    @Override // twitter4j.conf.ConfigurationBase
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isApplicationOnlyAuthEnabled() {
        return super.isApplicationOnlyAuthEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isGAE() {
        return super.isGAE();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.internal.http.HttpClientConfiguration
    public /* bridge */ /* synthetic */ boolean isGZIPEnabled() {
        return super.isGZIPEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isIncludeEntitiesEnabled() {
        return super.isIncludeEntitiesEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isIncludeMyRetweetEnabled() {
        return super.isIncludeMyRetweetEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isIncludeRTsEnabled() {
        return super.isIncludeRTsEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isJSONStoreEnabled() {
        return super.isJSONStoreEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isMBeanEnabled() {
        return super.isMBeanEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.internal.http.HttpClientConfiguration
    public /* bridge */ /* synthetic */ boolean isPrettyDebugEnabled() {
        return super.isPrettyDebugEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isStallWarningsEnabled() {
        return super.isStallWarningsEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isTrimUserEnabled() {
        return super.isTrimUserEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase, twitter4j.conf.Configuration
    public /* bridge */ /* synthetic */ boolean isUserStreamRepliesAllEnabled() {
        return super.isUserStreamRepliesAllEnabled();
    }

    @Override // twitter4j.conf.ConfigurationBase
    public /* bridge */ /* synthetic */ void setIncludeMyRetweetEnabled(boolean z) {
        super.setIncludeMyRetweetEnabled(z);
    }

    @Override // twitter4j.conf.ConfigurationBase
    public /* bridge */ /* synthetic */ void setTrimUserEnabled(boolean z) {
        super.setTrimUserEnabled(z);
    }

    @Override // twitter4j.conf.ConfigurationBase
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public PropertyConfiguration(InputStream inputStream) {
        Properties properties = new Properties();
        loadProperties(properties, inputStream);
        setFieldsWithTreePath(properties, "/");
    }

    public PropertyConfiguration(Properties properties) {
        this(properties, "/");
    }

    public PropertyConfiguration(Properties properties, String str) {
        setFieldsWithTreePath(properties, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PropertyConfiguration(String str) {
        Properties properties;
        try {
            properties = (Properties) System.getProperties().clone();
            try {
                Map<String, String> map = System.getenv();
                for (String str2 : map.keySet()) {
                    properties.setProperty(str2, map.get(str2));
                }
            } catch (SecurityException e) {
            }
            normalize(properties);
        } catch (SecurityException e2) {
            properties = new Properties();
        }
        loadProperties(properties, "." + File.separatorChar + "twitter4j.properties");
        loadProperties(properties, Configuration.class.getResourceAsStream("/twitter4j.properties"));
        loadProperties(properties, Configuration.class.getResourceAsStream("/WEB-INF/twitter4j.properties"));
        try {
            loadProperties(properties, new FileInputStream("WEB-INF/twitter4j.properties"));
        } catch (FileNotFoundException e3) {
        } catch (SecurityException e4) {
        }
        setFieldsWithTreePath(properties, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PropertyConfiguration() {
        this("/");
    }

    private boolean notNull(Properties properties, String str, String str2) {
        return properties.getProperty(new StringBuilder().append(str).append(str2).toString()) != null;
    }

    private boolean loadProperties(Properties properties, String str) {
        FileInputStream fileInputStream;
        Throwable th;
        File file;
        FileInputStream fileInputStream2 = null;
        try {
            file = new File(str);
        } catch (Exception e) {
        } catch (Throwable th2) {
            fileInputStream = null;
            th = th2;
        }
        if (!file.exists() || !file.isFile()) {
            if (0 != 0) {
                try {
                    fileInputStream2.close();
                } catch (IOException e2) {
                }
            }
            return false;
        }
        fileInputStream = new FileInputStream(file);
        try {
            properties.load(fileInputStream);
            normalize(properties);
            if (fileInputStream == null) {
                return true;
            }
            try {
                fileInputStream.close();
                return true;
            } catch (IOException e3) {
                return true;
            }
        } catch (Exception e4) {
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e5) {
                }
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e6) {
                }
            }
            throw th;
        }
    }

    private boolean loadProperties(Properties properties, InputStream inputStream) {
        try {
            properties.load(inputStream);
            normalize(properties);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void normalize(Properties properties) {
        Set<String> keySet = properties.keySet();
        ArrayList arrayList = new ArrayList(10);
        for (String str : keySet) {
            if (-1 != str.indexOf("twitter4j.")) {
                arrayList.add(str);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            String property = properties.getProperty(str2);
            int indexOf = str2.indexOf("twitter4j.");
            properties.setProperty(str2.substring(0, indexOf) + str2.substring(indexOf + 10), property);
        }
    }

    private void setFieldsWithTreePath(Properties properties, String str) {
        setFieldsWithPrefix(properties, "");
        String str2 = null;
        for (String str3 : z_T4JInternalStringUtil.split(str, "/")) {
            if (!"".equals(str3)) {
                if (str2 == null) {
                    str2 = str3 + ".";
                } else {
                    str2 = str2 + str3 + ".";
                }
                setFieldsWithPrefix(properties, str2);
            }
        }
    }

    private void setFieldsWithPrefix(Properties properties, String str) {
        if (notNull(properties, str, DEBUG)) {
            setDebug(getBoolean(properties, str, DEBUG));
        }
        if (notNull(properties, str, USER)) {
            setUser(getString(properties, str, USER));
        }
        if (notNull(properties, str, PASSWORD)) {
            setPassword(getString(properties, str, PASSWORD));
        }
        if (notNull(properties, str, HTTP_USE_SSL)) {
            setUseSSL(getBoolean(properties, str, HTTP_USE_SSL));
        }
        if (notNull(properties, str, HTTP_PRETTY_DEBUG)) {
            setPrettyDebugEnabled(getBoolean(properties, str, HTTP_PRETTY_DEBUG));
        }
        if (notNull(properties, str, HTTP_GZIP)) {
            setGZIPEnabled(getBoolean(properties, str, HTTP_GZIP));
        }
        if (notNull(properties, str, "http.proxyHost")) {
            setHttpProxyHost(getString(properties, str, "http.proxyHost"));
        } else if (notNull(properties, str, "http.proxyHost")) {
            setHttpProxyHost(getString(properties, str, "http.proxyHost"));
        }
        if (notNull(properties, str, HTTP_PROXY_USER)) {
            setHttpProxyUser(getString(properties, str, HTTP_PROXY_USER));
        }
        if (notNull(properties, str, HTTP_PROXY_PASSWORD)) {
            setHttpProxyPassword(getString(properties, str, HTTP_PROXY_PASSWORD));
        }
        if (notNull(properties, str, "http.proxyPort")) {
            setHttpProxyPort(getIntProperty(properties, str, "http.proxyPort"));
        } else if (notNull(properties, str, "http.proxyPort")) {
            setHttpProxyPort(getIntProperty(properties, str, "http.proxyPort"));
        }
        if (notNull(properties, str, HTTP_CONNECTION_TIMEOUT)) {
            setHttpConnectionTimeout(getIntProperty(properties, str, HTTP_CONNECTION_TIMEOUT));
        }
        if (notNull(properties, str, HTTP_READ_TIMEOUT)) {
            setHttpReadTimeout(getIntProperty(properties, str, HTTP_READ_TIMEOUT));
        }
        if (notNull(properties, str, HTTP_STREAMING_READ_TIMEOUT)) {
            setHttpStreamingReadTimeout(getIntProperty(properties, str, HTTP_STREAMING_READ_TIMEOUT));
        }
        if (notNull(properties, str, HTTP_RETRY_COUNT)) {
            setHttpRetryCount(getIntProperty(properties, str, HTTP_RETRY_COUNT));
        }
        if (notNull(properties, str, HTTP_RETRY_INTERVAL_SECS)) {
            setHttpRetryIntervalSeconds(getIntProperty(properties, str, HTTP_RETRY_INTERVAL_SECS));
        }
        if (notNull(properties, str, HTTP_MAX_TOTAL_CONNECTIONS)) {
            setHttpMaxTotalConnections(getIntProperty(properties, str, HTTP_MAX_TOTAL_CONNECTIONS));
        }
        if (notNull(properties, str, HTTP_DEFAULT_MAX_PER_ROUTE)) {
            setHttpDefaultMaxPerRoute(getIntProperty(properties, str, HTTP_DEFAULT_MAX_PER_ROUTE));
        }
        if (notNull(properties, str, OAUTH_CONSUMER_KEY)) {
            setOAuthConsumerKey(getString(properties, str, OAUTH_CONSUMER_KEY));
        }
        if (notNull(properties, str, OAUTH_CONSUMER_SECRET)) {
            setOAuthConsumerSecret(getString(properties, str, OAUTH_CONSUMER_SECRET));
        }
        if (notNull(properties, str, OAUTH_ACCESS_TOKEN)) {
            setOAuthAccessToken(getString(properties, str, OAUTH_ACCESS_TOKEN));
        }
        if (notNull(properties, str, OAUTH_ACCESS_TOKEN_SECRET)) {
            setOAuthAccessTokenSecret(getString(properties, str, OAUTH_ACCESS_TOKEN_SECRET));
        }
        if (notNull(properties, str, OAUTH2_TOKEN_TYPE)) {
            setOAuth2TokenType(getString(properties, str, OAUTH2_TOKEN_TYPE));
        }
        if (notNull(properties, str, OAUTH2_ACCESS_TOKEN)) {
            setOAuth2AccessToken(getString(properties, str, OAUTH2_ACCESS_TOKEN));
        }
        if (notNull(properties, str, ASYNC_NUM_THREADS)) {
            setAsyncNumThreads(getIntProperty(properties, str, ASYNC_NUM_THREADS));
        }
        if (notNull(properties, str, CONTRIBUTING_TO)) {
            setContributingTo(getLongProperty(properties, str, CONTRIBUTING_TO));
        }
        if (notNull(properties, str, ASYNC_DISPATCHER_IMPL)) {
            setDispatcherImpl(getString(properties, str, ASYNC_DISPATCHER_IMPL));
        }
        if (notNull(properties, str, CLIENT_VERSION)) {
            setClientVersion(getString(properties, str, CLIENT_VERSION));
        }
        if (notNull(properties, str, CLIENT_URL)) {
            setClientURL(getString(properties, str, CLIENT_URL));
        }
        if (notNull(properties, str, HTTP_USER_AGENT)) {
            setUserAgent(getString(properties, str, HTTP_USER_AGENT));
        }
        if (notNull(properties, str, OAUTH_REQUEST_TOKEN_URL)) {
            setOAuthRequestTokenURL(getString(properties, str, OAUTH_REQUEST_TOKEN_URL));
        }
        if (notNull(properties, str, OAUTH_AUTHORIZATION_URL)) {
            setOAuthAuthorizationURL(getString(properties, str, OAUTH_AUTHORIZATION_URL));
        }
        if (notNull(properties, str, OAUTH_ACCESS_TOKEN_URL)) {
            setOAuthAccessTokenURL(getString(properties, str, OAUTH_ACCESS_TOKEN_URL));
        }
        if (notNull(properties, str, OAUTH_AUTHENTICATION_URL)) {
            setOAuthAuthenticationURL(getString(properties, str, OAUTH_AUTHENTICATION_URL));
        }
        if (notNull(properties, str, OAUTH2_TOKEN_URL)) {
            setOAuth2TokenURL(getString(properties, str, OAUTH2_TOKEN_URL));
        }
        if (notNull(properties, str, OAUTH2_INVALIDATE_TOKEN_URL)) {
            setOAuth2InvalidateTokenURL(getString(properties, str, OAUTH2_INVALIDATE_TOKEN_URL));
        }
        if (notNull(properties, str, REST_BASE_URL)) {
            setRestBaseURL(getString(properties, str, REST_BASE_URL));
        }
        if (notNull(properties, str, STREAM_BASE_URL)) {
            setStreamBaseURL(getString(properties, str, STREAM_BASE_URL));
        }
        if (notNull(properties, str, USER_STREAM_BASE_URL)) {
            setUserStreamBaseURL(getString(properties, str, USER_STREAM_BASE_URL));
        }
        if (notNull(properties, str, SITE_STREAM_BASE_URL)) {
            setSiteStreamBaseURL(getString(properties, str, SITE_STREAM_BASE_URL));
        }
        if (notNull(properties, str, INCLUDE_RTS)) {
            setIncludeRTsEnbled(getBoolean(properties, str, INCLUDE_RTS));
        }
        if (notNull(properties, str, INCLUDE_ENTITIES)) {
            setIncludeEntitiesEnbled(getBoolean(properties, str, INCLUDE_ENTITIES));
        }
        if (notNull(properties, str, INCLUDE_MY_RETWEET)) {
            setIncludeMyRetweetEnabled(getBoolean(properties, str, INCLUDE_MY_RETWEET));
        }
        if (notNull(properties, str, LOGGER_FACTORY)) {
            setLoggerFactory(getString(properties, str, LOGGER_FACTORY));
        }
        if (notNull(properties, str, JSON_STORE_ENABLED)) {
            setJSONStoreEnabled(getBoolean(properties, str, JSON_STORE_ENABLED));
        }
        if (notNull(properties, str, MBEAN_ENABLED)) {
            setMBeanEnabled(getBoolean(properties, str, MBEAN_ENABLED));
        }
        if (notNull(properties, str, STREAM_USER_REPLIES_ALL)) {
            setUserStreamRepliesAllEnabled(getBoolean(properties, str, STREAM_USER_REPLIES_ALL));
        }
        if (notNull(properties, str, STREAM_STALL_WARNINGS_ENABLED)) {
            setStallWarningsEnabled(getBoolean(properties, str, STREAM_STALL_WARNINGS_ENABLED));
        }
        if (notNull(properties, str, APPLICATION_ONLY_AUTH_ENABLED)) {
            setApplicationOnlyAuthEnabled(getBoolean(properties, str, APPLICATION_ONLY_AUTH_ENABLED));
        }
        if (notNull(properties, str, MEDIA_PROVIDER)) {
            setMediaProvider(getString(properties, str, MEDIA_PROVIDER));
        }
        if (notNull(properties, str, MEDIA_PROVIDER_API_KEY)) {
            setMediaProviderAPIKey(getString(properties, str, MEDIA_PROVIDER_API_KEY));
        }
        if (notNull(properties, str, MEDIA_PROVIDER_PARAMETERS)) {
            String[] split = z_T4JInternalStringUtil.split(getString(properties, str, MEDIA_PROVIDER_PARAMETERS), "&");
            Properties properties2 = new Properties();
            for (String str2 : split) {
                String[] split2 = z_T4JInternalStringUtil.split(str2, "=");
                properties2.setProperty(split2[0], split2[1]);
            }
            setMediaProviderParameters(properties2);
        }
        cacheInstance();
    }

    protected boolean getBoolean(Properties properties, String str, String str2) {
        return Boolean.valueOf(properties.getProperty(str + str2)).booleanValue();
    }

    protected int getIntProperty(Properties properties, String str, String str2) {
        try {
            return Integer.parseInt(properties.getProperty(str + str2));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    protected long getLongProperty(Properties properties, String str, String str2) {
        try {
            return Long.parseLong(properties.getProperty(str + str2));
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    protected String getString(Properties properties, String str, String str2) {
        return properties.getProperty(str + str2);
    }

    @Override // twitter4j.conf.ConfigurationBase
    protected Object readResolve() {
        return super.readResolve();
    }
}
