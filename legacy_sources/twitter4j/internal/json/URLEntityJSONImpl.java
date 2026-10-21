package twitter4j.internal.json;
// [removed legacy tracker import]
import twitter4j.TwitterException;
import twitter4j.URLEntity;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
final class URLEntityJSONImpl extends EntityIndex implements URLEntity {
    private static final long serialVersionUID = -8948472760821379376L;
    private String displayURL;
    private String expandedURL;
    private String url;

    /* JADX INFO: Access modifiers changed from: package-private */
    public URLEntityJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public URLEntityJSONImpl(int i, int i2, String str, String str2, String str3) {
        setStart(i);
        setEnd(i2);
        this.url = str;
        this.expandedURL = str2;
        this.displayURL = str3;
    }

    URLEntityJSONImpl() {
    }

    private void init(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("indices");
            setStart(jSONArray.getInt(0));
            setEnd(jSONArray.getInt(1));
            this.url = jSONObject.getString(NativeProtocol.IMAGE_URL_KEY);
            if (!jSONObject.isNull("expanded_url")) {
                this.expandedURL = jSONObject.getString("expanded_url");
            } else {
                this.expandedURL = this.url;
            }
            if (!jSONObject.isNull("display_url")) {
                this.displayURL = jSONObject.getString("display_url");
            } else {
                this.displayURL = this.url;
            }
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    @Override // twitter4j.URLEntity, twitter4j.TweetEntity
    public String getText() {
        return this.url;
    }

    @Override // twitter4j.URLEntity
    public String getURL() {
        return this.url;
    }

    @Override // twitter4j.URLEntity
    public String getExpandedURL() {
        return this.expandedURL;
    }

    @Override // twitter4j.URLEntity
    public String getDisplayURL() {
        return this.displayURL;
    }

    @Override // twitter4j.internal.json.EntityIndex, twitter4j.HashtagEntity, twitter4j.TweetEntity
    public int getStart() {
        return super.getStart();
    }

    @Override // twitter4j.internal.json.EntityIndex, twitter4j.HashtagEntity, twitter4j.TweetEntity
    public int getEnd() {
        return super.getEnd();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        URLEntityJSONImpl uRLEntityJSONImpl = (URLEntityJSONImpl) obj;
        if (this.displayURL == null ? uRLEntityJSONImpl.displayURL != null : !this.displayURL.equals(uRLEntityJSONImpl.displayURL)) {
            return false;
        }
        if (this.expandedURL == null ? uRLEntityJSONImpl.expandedURL != null : !this.expandedURL.equals(uRLEntityJSONImpl.expandedURL)) {
            return false;
        }
        if (this.url != null) {
            if (this.url.equals(uRLEntityJSONImpl.url)) {
                return true;
            }
        } else if (uRLEntityJSONImpl.url == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.expandedURL != null ? this.expandedURL.hashCode() : 0) + ((this.url != null ? this.url.hashCode() : 0) * 31)) * 31) + (this.displayURL != null ? this.displayURL.hashCode() : 0);
    }

    public String toString() {
        return "URLEntityJSONImpl{url='" + this.url + "', expandedURL='" + this.expandedURL + "', displayURL='" + this.displayURL + "'}";
    }
}
