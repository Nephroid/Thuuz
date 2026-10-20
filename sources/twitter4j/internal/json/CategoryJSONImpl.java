package twitter4j.internal.json;

import com.samsung.android.sdk.richnotification.Utilities;
import java.io.Serializable;
import twitter4j.Category;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public final class CategoryJSONImpl implements Serializable, Category {
    private static final long serialVersionUID = -6703617743623288566L;
    private String name;
    private int size;
    private String slug;

    CategoryJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    void init(JSONObject jSONObject) {
        this.name = jSONObject.getString(Utilities.DB_KEY_IMAGE_NAME);
        this.slug = jSONObject.getString("slug");
        this.size = z_T4JInternalParseUtil.getInt("size", jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResponseList<Category> createCategoriesList(HttpResponse httpResponse, Configuration configuration) {
        return createCategoriesList(httpResponse.asJSONArray(), httpResponse, configuration);
    }

    static ResponseList<Category> createCategoriesList(JSONArray jSONArray, HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            ResponseListImpl responseListImpl = new ResponseListImpl(jSONArray.length(), httpResponse);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                CategoryJSONImpl categoryJSONImpl = new CategoryJSONImpl(jSONObject);
                responseListImpl.add(categoryJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(categoryJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(responseListImpl, jSONArray);
            }
            return responseListImpl;
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    @Override // twitter4j.Category
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.Category
    public String getSlug() {
        return this.slug;
    }

    @Override // twitter4j.Category
    public int getSize() {
        return this.size;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CategoryJSONImpl categoryJSONImpl = (CategoryJSONImpl) obj;
        if (this.size != categoryJSONImpl.size) {
            return false;
        }
        if (this.name == null ? categoryJSONImpl.name != null : !this.name.equals(categoryJSONImpl.name)) {
            return false;
        }
        if (this.slug != null) {
            if (this.slug.equals(categoryJSONImpl.slug)) {
                return true;
            }
        } else if (categoryJSONImpl.slug == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.name != null ? this.name.hashCode() : 0) * 31) + (this.slug != null ? this.slug.hashCode() : 0)) * 31) + this.size;
    }

    public String toString() {
        return "CategoryJSONImpl{name='" + this.name + "', slug='" + this.slug + "', size=" + this.size + '}';
    }
}
