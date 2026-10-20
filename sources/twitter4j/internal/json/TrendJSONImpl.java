package twitter4j.internal.json;

import com.facebook.internal.NativeProtocol;
import com.samsung.android.sdk.richnotification.Utilities;
import java.io.Serializable;
import twitter4j.Trend;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public final class TrendJSONImpl implements Serializable, Trend {
    private static final long serialVersionUID = 1925956704460743946L;
    private String name;
    private String query;
    private String url;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrendJSONImpl(JSONObject jSONObject, boolean z) {
        this.url = null;
        this.query = null;
        this.name = z_T4JInternalParseUtil.getRawString(Utilities.DB_KEY_IMAGE_NAME, jSONObject);
        this.url = z_T4JInternalParseUtil.getRawString(NativeProtocol.IMAGE_URL_KEY, jSONObject);
        this.query = z_T4JInternalParseUtil.getRawString("query", jSONObject);
        if (z) {
            DataObjectFactoryUtil.registerJSONObject(this, jSONObject);
        }
    }

    TrendJSONImpl(JSONObject jSONObject) {
        this(jSONObject, false);
    }

    @Override // twitter4j.Trend
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.Trend
    public String getUrl() {
        return getURL();
    }

    @Override // twitter4j.Trend
    public String getURL() {
        return this.url;
    }

    @Override // twitter4j.Trend
    public String getQuery() {
        return this.query;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Trend)) {
            return false;
        }
        Trend trend = (Trend) obj;
        if (!this.name.equals(trend.getName())) {
            return false;
        }
        if (this.query == null ? trend.getQuery() != null : !this.query.equals(trend.getQuery())) {
            return false;
        }
        if (this.url != null) {
            if (this.url.equals(trend.getURL())) {
                return true;
            }
        } else if (trend.getURL() == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.url != null ? this.url.hashCode() : 0) + (this.name.hashCode() * 31)) * 31) + (this.query != null ? this.query.hashCode() : 0);
    }

    public String toString() {
        return "TrendJSONImpl{name='" + this.name + "', url='" + this.url + "', query='" + this.query + "'}";
    }
}
