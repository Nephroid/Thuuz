package twitter4j.auth;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.http.protocol.HTTP;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.json.z_T4JInternalParseUtil;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public class OAuth2Token implements Serializable {
    private static final long serialVersionUID = 358222644448390610L;
    private String accessToken;
    private String tokenType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OAuth2Token(HttpResponse httpResponse) {
        JSONObject asJSONObject = httpResponse.asJSONObject();
        this.tokenType = z_T4JInternalParseUtil.getRawString("token_type", asJSONObject);
        try {
            this.accessToken = URLDecoder.decode(z_T4JInternalParseUtil.getRawString("access_token", asJSONObject), HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
    }

    public OAuth2Token(String str, String str2) {
        this.tokenType = str;
        this.accessToken = str2;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String generateAuthorizationHeader() {
        String str = "";
        try {
            str = URLEncoder.encode(this.accessToken, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
        return "Bearer " + str;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof OAuth2Token)) {
            return false;
        }
        OAuth2Token oAuth2Token = (OAuth2Token) obj;
        if (this.tokenType != null) {
            if (!this.tokenType.equals(oAuth2Token.tokenType)) {
                return false;
            }
        } else if (oAuth2Token.tokenType != null) {
            return false;
        }
        if (this.accessToken != null) {
            if (!this.accessToken.equals(oAuth2Token.accessToken)) {
                return false;
            }
        } else if (oAuth2Token.accessToken != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.tokenType != null ? this.tokenType.hashCode() : 0) * 31) + (this.accessToken != null ? this.accessToken.hashCode() : 0);
    }

    public String toString() {
        return "OAuth2Token{tokenType='" + this.tokenType + "', accessToken='" + this.accessToken + "'}";
    }
}
