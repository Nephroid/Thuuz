package twitter4j.auth;

import java.io.Serializable;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;
import twitter4j.internal.http.HttpResponse;

/* loaded from: classes.dex */
public final class RequestToken extends OAuthToken implements Serializable {
    private static final long serialVersionUID = -8214365845469757952L;
    private final Configuration conf;
    private OAuthSupport oauth;

    @Override // twitter4j.auth.OAuthToken
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // twitter4j.auth.OAuthToken
    public /* bridge */ /* synthetic */ String getParameter(String str) {
        return super.getParameter(str);
    }

    @Override // twitter4j.auth.OAuthToken
    public /* bridge */ /* synthetic */ String getToken() {
        return super.getToken();
    }

    @Override // twitter4j.auth.OAuthToken
    public /* bridge */ /* synthetic */ String getTokenSecret() {
        return super.getTokenSecret();
    }

    @Override // twitter4j.auth.OAuthToken
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // twitter4j.auth.OAuthToken
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestToken(HttpResponse httpResponse, OAuthSupport oAuthSupport) {
        super(httpResponse);
        this.conf = ConfigurationContext.getInstance();
        this.oauth = oAuthSupport;
    }

    public RequestToken(String str, String str2) {
        super(str, str2);
        this.conf = ConfigurationContext.getInstance();
    }

    RequestToken(String str, String str2, OAuthSupport oAuthSupport) {
        super(str, str2);
        this.conf = ConfigurationContext.getInstance();
        this.oauth = oAuthSupport;
    }

    public String getAuthorizationURL() {
        return this.conf.getOAuthAuthorizationURL() + "?oauth_token=" + getToken();
    }

    public String getAuthenticationURL() {
        return this.conf.getOAuthAuthenticationURL() + "?oauth_token=" + getToken();
    }
}
