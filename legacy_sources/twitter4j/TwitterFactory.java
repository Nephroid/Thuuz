package twitter4j;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.AuthorizationFactory;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

/* loaded from: classes.dex */
public final class TwitterFactory implements Serializable {
    static final Authorization DEFAULT_AUTHORIZATION = AuthorizationFactory.getInstance(ConfigurationContext.getInstance());
    private static final Twitter SINGLETON;
    private static final Constructor<Twitter> TWITTER_CONSTRUCTOR;
    private static final long serialVersionUID = 5193900138477709155L;
    private final Configuration conf;

    static {
        String str = null;
        if (ConfigurationContext.getInstance().isGAE()) {
            try {
                Class.forName("twitter4j.AppEngineTwitterImpl");
                str = "twitter4j.AppEngineTwitterImpl";
            } catch (ClassNotFoundException e) {
            }
        }
        if (str == null) {
            str = "twitter4j.TwitterImpl";
        }
        try {
            TWITTER_CONSTRUCTOR = Class.forName(str).getDeclaredConstructor(Configuration.class, Authorization.class);
            try {
                SINGLETON = TWITTER_CONSTRUCTOR.newInstance(ConfigurationContext.getInstance(), DEFAULT_AUTHORIZATION);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InstantiationException e3) {
                throw new AssertionError(e3);
            } catch (InvocationTargetException e4) {
                throw new AssertionError(e4);
            }
        } catch (ClassNotFoundException e5) {
            throw new AssertionError(e5);
        } catch (NoSuchMethodException e6) {
            throw new AssertionError(e6);
        }
    }

    public TwitterFactory() {
        this(ConfigurationContext.getInstance());
    }

    public TwitterFactory(Configuration configuration) {
        if (configuration == null) {
            throw new NullPointerException("configuration cannot be null");
        }
        this.conf = configuration;
    }

    public TwitterFactory(String str) {
        this(ConfigurationContext.getInstance(str));
    }

    public Twitter getInstance() {
        return getInstance(AuthorizationFactory.getInstance(this.conf));
    }

    public Twitter getInstance(AccessToken accessToken) {
        String oAuthConsumerKey = this.conf.getOAuthConsumerKey();
        String oAuthConsumerSecret = this.conf.getOAuthConsumerSecret();
        if (oAuthConsumerKey == null && oAuthConsumerSecret == null) {
            throw new IllegalStateException("Consumer key and Consumer secret not supplied.");
        }
        OAuthAuthorization oAuthAuthorization = new OAuthAuthorization(this.conf);
        oAuthAuthorization.setOAuthAccessToken(accessToken);
        return getInstance(oAuthAuthorization);
    }

    public Twitter getInstance(Authorization authorization) {
        try {
            return TWITTER_CONSTRUCTOR.newInstance(this.conf, authorization);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new AssertionError(e2);
        } catch (InvocationTargetException e3) {
            throw new AssertionError(e3);
        }
    }

    public static Twitter getSingleton() {
        return SINGLETON;
    }
}
