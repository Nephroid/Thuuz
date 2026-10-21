package twitter4j.internal.json;

import com.samsung.android.sdk.richnotification.Utilities;
import java.util.Date;
import twitter4j.ResponseList;
import twitter4j.SavedSearch;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public final class SavedSearchJSONImpl extends TwitterResponseImpl implements SavedSearch {
    private static final long serialVersionUID = 3083819860391598212L;
    private Date createdAt;
    private int id;
    private String name;
    private int position;
    private String query;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SavedSearchJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
        }
        JSONObject asJSONObject = httpResponse.asJSONObject();
        init(asJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.registerJSONObject(this, asJSONObject);
        }
    }

    SavedSearchJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResponseList<SavedSearch> createSavedSearchList(HttpResponse httpResponse, Configuration configuration) {
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
        }
        JSONArray asJSONArray = httpResponse.asJSONArray();
        try {
            ResponseListImpl responseListImpl = new ResponseListImpl(asJSONArray.length(), httpResponse);
            for (int i = 0; i < asJSONArray.length(); i++) {
                JSONObject jSONObject = asJSONArray.getJSONObject(i);
                SavedSearchJSONImpl savedSearchJSONImpl = new SavedSearchJSONImpl(jSONObject);
                responseListImpl.add(savedSearchJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(savedSearchJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(responseListImpl, asJSONArray);
            }
            return responseListImpl;
        } catch (JSONException e) {
            throw new TwitterException(e.getMessage() + ":" + httpResponse.asString(), e);
        }
    }

    private void init(JSONObject jSONObject) {
        this.createdAt = z_T4JInternalParseUtil.getDate("created_at", jSONObject, "EEE MMM dd HH:mm:ss z yyyy");
        this.query = z_T4JInternalParseUtil.getUnescapedString("query", jSONObject);
        this.position = z_T4JInternalParseUtil.getInt("position", jSONObject);
        this.name = z_T4JInternalParseUtil.getUnescapedString(Utilities.DB_KEY_IMAGE_NAME, jSONObject);
        this.id = z_T4JInternalParseUtil.getInt("id", jSONObject);
    }

    @Override // java.lang.Comparable
    public int compareTo(SavedSearch savedSearch) {
        return this.id - savedSearch.getId();
    }

    @Override // twitter4j.SavedSearch
    public Date getCreatedAt() {
        return this.createdAt;
    }

    @Override // twitter4j.SavedSearch
    public String getQuery() {
        return this.query;
    }

    @Override // twitter4j.SavedSearch
    public int getPosition() {
        return this.position;
    }

    @Override // twitter4j.SavedSearch
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.SavedSearch
    public int getId() {
        return this.id;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SavedSearch) && this.id == ((SavedSearch) obj).getId();
    }

    public int hashCode() {
        return (((((((this.createdAt.hashCode() * 31) + this.query.hashCode()) * 31) + this.position) * 31) + this.name.hashCode()) * 31) + this.id;
    }

    public String toString() {
        return "SavedSearchJSONImpl{createdAt=" + this.createdAt + ", query='" + this.query + "', position=" + this.position + ", name='" + this.name + "', id=" + this.id + '}';
    }
}
