package twitter4j.internal.json;

import com.samsung.android.sdk.richnotification.Utilities;
import twitter4j.TwitterException;
import twitter4j.UserMentionEntity;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
class UserMentionEntityJSONImpl extends EntityIndex implements UserMentionEntity {
    private static final long serialVersionUID = 6580431141350059702L;
    private long id;
    private String name;
    private String screenName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserMentionEntityJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserMentionEntityJSONImpl(int i, int i2, String str, String str2, long j) {
        setStart(i);
        setEnd(i2);
        this.name = str;
        this.screenName = str2;
        this.id = j;
    }

    UserMentionEntityJSONImpl() {
    }

    private void init(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("indices");
            setStart(jSONArray.getInt(0));
            setEnd(jSONArray.getInt(1));
            if (!jSONObject.isNull(Utilities.DB_KEY_IMAGE_NAME)) {
                this.name = jSONObject.getString(Utilities.DB_KEY_IMAGE_NAME);
            }
            if (!jSONObject.isNull("screen_name")) {
                this.screenName = jSONObject.getString("screen_name");
            }
            this.id = z_T4JInternalParseUtil.getLong("id", jSONObject);
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    @Override // twitter4j.UserMentionEntity, twitter4j.TweetEntity
    public String getText() {
        return this.screenName;
    }

    @Override // twitter4j.UserMentionEntity
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.UserMentionEntity
    public String getScreenName() {
        return this.screenName;
    }

    @Override // twitter4j.UserMentionEntity
    public long getId() {
        return this.id;
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
        UserMentionEntityJSONImpl userMentionEntityJSONImpl = (UserMentionEntityJSONImpl) obj;
        if (this.id != userMentionEntityJSONImpl.id) {
            return false;
        }
        if (this.name == null ? userMentionEntityJSONImpl.name != null : !this.name.equals(userMentionEntityJSONImpl.name)) {
            return false;
        }
        if (this.screenName != null) {
            if (this.screenName.equals(userMentionEntityJSONImpl.screenName)) {
                return true;
            }
        } else if (userMentionEntityJSONImpl.screenName == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.name != null ? this.name.hashCode() : 0) * 31) + (this.screenName != null ? this.screenName.hashCode() : 0)) * 31) + ((int) (this.id ^ (this.id >>> 32)));
    }

    public String toString() {
        return "UserMentionEntityJSONImpl{name='" + this.name + "', screenName='" + this.screenName + "', id=" + this.id + '}';
    }
}
