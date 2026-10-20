package twitter4j.internal.json;

import com.samsung.android.sdk.richnotification.Utilities;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.conf.Configuration;
import twitter4j.conf.PropertyConfiguration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public class UserListJSONImpl extends TwitterResponseImpl implements Serializable, UserList {
    private static final long serialVersionUID = -6345893237975349030L;
    private String description;
    private boolean following;
    private String fullName;
    private int id;
    private int memberCount;
    private boolean mode;
    private String name;
    private String slug;
    private int subscriberCount;
    private String uri;
    private User user;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserListJSONImpl(HttpResponse httpResponse, Configuration configuration) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserListJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    private void init(JSONObject jSONObject) {
        this.id = z_T4JInternalParseUtil.getInt("id", jSONObject);
        this.name = z_T4JInternalParseUtil.getRawString(Utilities.DB_KEY_IMAGE_NAME, jSONObject);
        this.fullName = z_T4JInternalParseUtil.getRawString("full_name", jSONObject);
        this.slug = z_T4JInternalParseUtil.getRawString("slug", jSONObject);
        this.description = z_T4JInternalParseUtil.getRawString("description", jSONObject);
        this.subscriberCount = z_T4JInternalParseUtil.getInt("subscriber_count", jSONObject);
        this.memberCount = z_T4JInternalParseUtil.getInt("member_count", jSONObject);
        this.uri = z_T4JInternalParseUtil.getRawString("uri", jSONObject);
        this.mode = "public".equals(z_T4JInternalParseUtil.getRawString("mode", jSONObject));
        this.following = z_T4JInternalParseUtil.getBoolean("following", jSONObject);
        try {
            if (!jSONObject.isNull(PropertyConfiguration.USER)) {
                this.user = new UserJSONImpl(jSONObject.getJSONObject(PropertyConfiguration.USER));
            }
        } catch (JSONException e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(UserList userList) {
        return this.id - userList.getId();
    }

    @Override // twitter4j.UserList
    public int getId() {
        return this.id;
    }

    @Override // twitter4j.UserList
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.UserList
    public String getFullName() {
        return this.fullName;
    }

    @Override // twitter4j.UserList
    public String getSlug() {
        return this.slug;
    }

    @Override // twitter4j.UserList
    public String getDescription() {
        return this.description;
    }

    @Override // twitter4j.UserList
    public int getSubscriberCount() {
        return this.subscriberCount;
    }

    @Override // twitter4j.UserList
    public int getMemberCount() {
        return this.memberCount;
    }

    @Override // twitter4j.UserList
    public URI getURI() {
        try {
            return new URI(this.uri);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Override // twitter4j.UserList
    public boolean isPublic() {
        return this.mode;
    }

    @Override // twitter4j.UserList
    public boolean isFollowing() {
        return this.following;
    }

    @Override // twitter4j.UserList
    public User getUser() {
        return this.user;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PagableResponseList<UserList> createPagableUserListList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject asJSONObject = httpResponse.asJSONObject();
            JSONArray jSONArray = asJSONObject.getJSONArray("lists");
            int length = jSONArray.length();
            PagableResponseListImpl pagableResponseListImpl = new PagableResponseListImpl(length, asJSONObject, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                UserListJSONImpl userListJSONImpl = new UserListJSONImpl(jSONObject);
                pagableResponseListImpl.add(userListJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(userListJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(pagableResponseListImpl, asJSONObject);
            }
            return pagableResponseListImpl;
        } catch (TwitterException e) {
            throw e;
        } catch (JSONException e2) {
            throw new TwitterException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResponseList<UserList> createUserListList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONArray asJSONArray = httpResponse.asJSONArray();
            int length = asJSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = asJSONArray.getJSONObject(i);
                UserListJSONImpl userListJSONImpl = new UserListJSONImpl(jSONObject);
                responseListImpl.add(userListJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(userListJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(responseListImpl, asJSONArray);
            }
            return responseListImpl;
        } catch (TwitterException e) {
            throw e;
        } catch (JSONException e2) {
            throw new TwitterException(e2);
        }
    }

    public int hashCode() {
        return this.id;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this != obj) {
            return (obj instanceof UserList) && ((UserList) obj).getId() == this.id;
        }
        return true;
    }

    public String toString() {
        return "UserListJSONImpl{id=" + this.id + ", name='" + this.name + "', fullName='" + this.fullName + "', slug='" + this.slug + "', description='" + this.description + "', subscriberCount=" + this.subscriberCount + ", memberCount=" + this.memberCount + ", uri='" + this.uri + "', mode=" + this.mode + ", user=" + this.user + ", following=" + this.following + '}';
    }
}
