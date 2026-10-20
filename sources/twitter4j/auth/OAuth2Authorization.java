package twitter4j.auth;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.protocol.HTTP;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.BASE64Encoder;
import twitter4j.internal.http.HttpClientWrapper;
import twitter4j.internal.http.HttpParameter;
import twitter4j.internal.http.HttpRequest;
import twitter4j.internal.http.HttpResponse;

/* loaded from: classes.dex */
public class OAuth2Authorization implements Serializable, Authorization, OAuth2Support {
    private static final long serialVersionUID = 4274784415515174129L;
    private final Configuration conf;
    private String consumerKey;
    private String consumerSecret;
    private HttpClientWrapper http;
    private OAuth2Token token;

    public OAuth2Authorization(Configuration configuration) {
        this.conf = configuration;
        setOAuthConsumer(configuration.getOAuthConsumerKey(), configuration.getOAuthConsumerSecret());
        this.http = new HttpClientWrapper(configuration);
    }

    @Override // twitter4j.auth.OAuth2Support, twitter4j.auth.OAuthSupport
    public void setOAuthConsumer(String str, String str2) {
        if (str == null) {
            str = "";
        }
        this.consumerKey = str;
        if (str2 == null) {
            str2 = "";
        }
        this.consumerSecret = str2;
    }

    @Override // twitter4j.auth.OAuth2Support
    public OAuth2Token getOAuth2Token() {
        if (this.token != null) {
            throw new IllegalStateException("OAuth 2 Bearer Token is already available.");
        }
        HttpResponse post = this.http.post(this.conf.getOAuth2TokenURL(), new HttpParameter[]{new HttpParameter("grant_type", "client_credentials")}, this);
        if (post.getStatusCode() != 200) {
            throw new TwitterException("Obtaining OAuth 2 Bearer Token failed.", post);
        }
        this.token = new OAuth2Token(post);
        return this.token;
    }

    @Override // twitter4j.auth.OAuth2Support
    public void setOAuth2Token(OAuth2Token oAuth2Token) {
        this.token = oAuth2Token;
    }

    @Override // twitter4j.auth.OAuth2Support
    public void invalidateOAuth2Token() {
        if (this.token == null) {
            throw new IllegalStateException("OAuth 2 Bearer Token is not available.");
        }
        HttpParameter[] httpParameterArr = {new HttpParameter("access_token", this.token.getAccessToken())};
        OAuth2Token oAuth2Token = this.token;
        try {
            this.token = null;
            HttpResponse post = this.http.post(this.conf.getOAuth2InvalidateTokenURL(), httpParameterArr, this);
            if (post.getStatusCode() != 200) {
                throw new TwitterException("Invalidating OAuth 2 Bearer Token failed.", post);
            }
        } catch (Throwable th) {
            this.token = oAuth2Token;
            throw th;
        }
    }

    @Override // twitter4j.auth.Authorization
    public String getAuthorizationHeader(HttpRequest httpRequest) {
        if (this.token != null) {
            return this.token.generateAuthorizationHeader();
        }
        String str = "";
        try {
            str = URLEncoder.encode(this.consumerKey, HTTP.UTF_8) + ":" + URLEncoder.encode(this.consumerSecret, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
        return "Basic " + BASE64Encoder.encode(str.getBytes());
    }

    @Override // twitter4j.auth.Authorization
    public boolean isEnabled() {
        return this.token != null;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof OAuth2Authorization)) {
            return false;
        }
        OAuth2Authorization oAuth2Authorization = (OAuth2Authorization) obj;
        if (this.consumerKey != null) {
            if (!this.consumerKey.equals(oAuth2Authorization.consumerKey)) {
                return false;
            }
        } else if (oAuth2Authorization.consumerKey != null) {
            return false;
        }
        if (this.consumerSecret != null) {
            if (!this.consumerSecret.equals(oAuth2Authorization.consumerSecret)) {
                return false;
            }
        } else if (oAuth2Authorization.consumerSecret != null) {
            return false;
        }
        if (this.token != null) {
            if (!this.token.equals(oAuth2Authorization.token)) {
                return false;
            }
        } else if (oAuth2Authorization.token != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((this.consumerSecret != null ? this.consumerSecret.hashCode() : 0) + ((this.consumerKey != null ? this.consumerKey.hashCode() : 0) * 31)) * 31) + (this.token != null ? this.token.hashCode() : 0);
    }

    public String toString() {
        return "OAuth2Authorization{consumerKey='" + this.consumerKey + "', consumerSecret='******************************************', token=" + (this.token == null ? "null" : this.token.toString()) + '}';
    }
}
