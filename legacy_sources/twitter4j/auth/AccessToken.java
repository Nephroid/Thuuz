package twitter4j.auth;

import java.io.Serializable;
import twitter4j.internal.http.HttpResponse;

/* loaded from: classes.dex */
public class AccessToken extends OAuthToken implements Serializable {
    private static final long serialVersionUID = -8344528374458826291L;
    private String screenName;
    private long userId;

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

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccessToken(HttpResponse httpResponse) {
        this(httpResponse.asString());
    }

    AccessToken(String str) {
        super(str);
        this.userId = -1L;
        this.screenName = getParameter("screen_name");
        String parameter = getParameter("user_id");
        if (parameter != null) {
            this.userId = Long.parseLong(parameter);
        }
    }

    public AccessToken(String str, String str2) {
        super(str, str2);
        this.userId = -1L;
        int indexOf = str.indexOf("-");
        if (indexOf != -1) {
            try {
                this.userId = Long.parseLong(str.substring(0, indexOf));
            } catch (NumberFormatException e) {
            }
        }
    }

    public AccessToken(String str, String str2, long j) {
        super(str, str2);
        this.userId = -1L;
        this.userId = j;
    }

    public String getScreenName() {
        return this.screenName;
    }

    public long getUserId() {
        return this.userId;
    }

    @Override // twitter4j.auth.OAuthToken
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        AccessToken accessToken = (AccessToken) obj;
        if (this.userId != accessToken.userId) {
            return false;
        }
        if (this.screenName != null) {
            if (this.screenName.equals(accessToken.screenName)) {
                return true;
            }
        } else if (accessToken.screenName == null) {
            return true;
        }
        return false;
    }

    @Override // twitter4j.auth.OAuthToken
    public int hashCode() {
        return (((this.screenName != null ? this.screenName.hashCode() : 0) + (super.hashCode() * 31)) * 31) + ((int) (this.userId ^ (this.userId >>> 32)));
    }

    @Override // twitter4j.auth.OAuthToken
    public String toString() {
        return "AccessToken{screenName='" + this.screenName + "', userId=" + this.userId + '}';
    }
}
