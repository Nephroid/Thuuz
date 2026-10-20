package twitter4j.internal.json;

import com.samsung.android.sdk.richnotification.Utilities;
import twitter4j.Friendship;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
class FriendshipJSONImpl implements Friendship {
    private static final long serialVersionUID = 7724410837770709741L;
    private boolean followedBy;
    private boolean following;
    private final long id;
    private final String name;
    private final String screenName;

    FriendshipJSONImpl(JSONObject jSONObject) {
        this.following = false;
        this.followedBy = false;
        try {
            this.id = z_T4JInternalParseUtil.getLong("id", jSONObject);
            this.name = jSONObject.getString(Utilities.DB_KEY_IMAGE_NAME);
            this.screenName = jSONObject.getString("screen_name");
            JSONArray jSONArray = jSONObject.getJSONArray("connections");
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                if ("following".equals(string)) {
                    this.following = true;
                } else if ("followed_by".equals(string)) {
                    this.followedBy = true;
                }
            }
        } catch (JSONException e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResponseList<Friendship> createFriendshipList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONArray asJSONArray = httpResponse.asJSONArray();
            int length = asJSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = asJSONArray.getJSONObject(i);
                FriendshipJSONImpl friendshipJSONImpl = new FriendshipJSONImpl(jSONObject);
                if (configuration.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(friendshipJSONImpl, jSONObject);
                }
                responseListImpl.add(friendshipJSONImpl);
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

    @Override // twitter4j.Friendship
    public long getId() {
        return this.id;
    }

    @Override // twitter4j.Friendship
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.Friendship
    public String getScreenName() {
        return this.screenName;
    }

    @Override // twitter4j.Friendship
    public boolean isFollowing() {
        return this.following;
    }

    @Override // twitter4j.Friendship
    public boolean isFollowedBy() {
        return this.followedBy;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FriendshipJSONImpl friendshipJSONImpl = (FriendshipJSONImpl) obj;
        return this.followedBy == friendshipJSONImpl.followedBy && this.following == friendshipJSONImpl.following && this.id == friendshipJSONImpl.id && this.name.equals(friendshipJSONImpl.name) && this.screenName.equals(friendshipJSONImpl.screenName);
    }

    public int hashCode() {
        return (((this.following ? 1 : 0) + (((this.screenName != null ? this.screenName.hashCode() : 0) + (((this.name != null ? this.name.hashCode() : 0) + (((int) (this.id ^ (this.id >>> 32))) * 31)) * 31)) * 31)) * 31) + (this.followedBy ? 1 : 0);
    }

    public String toString() {
        return "FriendshipJSONImpl{id=" + this.id + ", name='" + this.name + "', screenName='" + this.screenName + "', following=" + this.following + ", followedBy=" + this.followedBy + '}';
    }
}
