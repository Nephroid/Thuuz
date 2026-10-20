package twitter4j.internal.json;

import java.io.Serializable;
import twitter4j.Relationship;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public class RelationshipJSONImpl extends TwitterResponseImpl implements Serializable, Relationship {
    private static final long serialVersionUID = 7725021608907856360L;
    private final boolean sourceBlockingTarget;
    private final boolean sourceCanDm;
    private final boolean sourceFollowedByTarget;
    private final boolean sourceFollowingTarget;
    private final boolean sourceNotificationsEnabled;
    private final long sourceUserId;
    private final String sourceUserScreenName;
    private final long targetUserId;
    private final String targetUserScreenName;
    private boolean wantRetweets;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RelationshipJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        this(httpResponse, httpResponse.asJSONObject());
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, httpResponse.asJSONObject());
        }
    }

    RelationshipJSONImpl(JSONObject jSONObject) {
        this((HttpResponse) null, jSONObject);
    }

    RelationshipJSONImpl(HttpResponse httpResponse, JSONObject jSONObject) {
        super(httpResponse);
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("relationship");
            JSONObject jSONObject3 = jSONObject2.getJSONObject("source");
            JSONObject jSONObject4 = jSONObject2.getJSONObject("target");
            this.sourceUserId = z_T4JInternalParseUtil.getLong("id", jSONObject3);
            this.targetUserId = z_T4JInternalParseUtil.getLong("id", jSONObject4);
            this.sourceUserScreenName = z_T4JInternalParseUtil.getUnescapedString("screen_name", jSONObject3);
            this.targetUserScreenName = z_T4JInternalParseUtil.getUnescapedString("screen_name", jSONObject4);
            this.sourceBlockingTarget = z_T4JInternalParseUtil.getBoolean("blocking", jSONObject3);
            this.sourceFollowingTarget = z_T4JInternalParseUtil.getBoolean("following", jSONObject3);
            this.sourceFollowedByTarget = z_T4JInternalParseUtil.getBoolean("followed_by", jSONObject3);
            this.sourceCanDm = z_T4JInternalParseUtil.getBoolean("can_dm", jSONObject3);
            this.sourceNotificationsEnabled = z_T4JInternalParseUtil.getBoolean("notifications_enabled", jSONObject3);
            this.wantRetweets = z_T4JInternalParseUtil.getBoolean("want_retweets", jSONObject3);
        } catch (JSONException e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    static ResponseList<Relationship> createRelationshipList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONArray asJSONArray = httpResponse.asJSONArray();
            int length = asJSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = asJSONArray.getJSONObject(i);
                RelationshipJSONImpl relationshipJSONImpl = new RelationshipJSONImpl(jSONObject);
                if (configuration.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(relationshipJSONImpl, jSONObject);
                }
                responseListImpl.add(relationshipJSONImpl);
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

    @Override // twitter4j.Relationship
    public long getSourceUserId() {
        return this.sourceUserId;
    }

    @Override // twitter4j.Relationship
    public long getTargetUserId() {
        return this.targetUserId;
    }

    @Override // twitter4j.Relationship
    public boolean isSourceBlockingTarget() {
        return this.sourceBlockingTarget;
    }

    @Override // twitter4j.Relationship
    public String getSourceUserScreenName() {
        return this.sourceUserScreenName;
    }

    @Override // twitter4j.Relationship
    public String getTargetUserScreenName() {
        return this.targetUserScreenName;
    }

    @Override // twitter4j.Relationship
    public boolean isSourceFollowingTarget() {
        return this.sourceFollowingTarget;
    }

    @Override // twitter4j.Relationship
    public boolean isTargetFollowingSource() {
        return this.sourceFollowedByTarget;
    }

    @Override // twitter4j.Relationship
    public boolean isSourceFollowedByTarget() {
        return this.sourceFollowedByTarget;
    }

    @Override // twitter4j.Relationship
    public boolean isTargetFollowedBySource() {
        return this.sourceFollowingTarget;
    }

    @Override // twitter4j.Relationship
    public boolean canSourceDm() {
        return this.sourceCanDm;
    }

    @Override // twitter4j.Relationship
    public boolean isSourceNotificationsEnabled() {
        return this.sourceNotificationsEnabled;
    }

    @Override // twitter4j.Relationship
    public boolean isSourceWantRetweets() {
        return this.wantRetweets;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Relationship)) {
            return false;
        }
        Relationship relationship = (Relationship) obj;
        return this.sourceUserId == relationship.getSourceUserId() && this.targetUserId == relationship.getTargetUserId() && this.sourceUserScreenName.equals(relationship.getSourceUserScreenName()) && this.targetUserScreenName.equals(relationship.getTargetUserScreenName());
    }

    public int hashCode() {
        return (((((((this.sourceFollowedByTarget ? 1 : 0) + (((this.sourceFollowingTarget ? 1 : 0) + (((this.sourceNotificationsEnabled ? 1 : 0) + (((this.sourceBlockingTarget ? 1 : 0) + (((this.targetUserScreenName != null ? this.targetUserScreenName.hashCode() : 0) + (((int) (this.targetUserId ^ (this.targetUserId >>> 32))) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + (this.sourceCanDm ? 1 : 0)) * 31) + ((int) (this.sourceUserId ^ (this.sourceUserId >>> 32)))) * 31) + (this.sourceUserScreenName != null ? this.sourceUserScreenName.hashCode() : 0);
    }

    public String toString() {
        return "RelationshipJSONImpl{sourceUserId=" + this.sourceUserId + ", targetUserId=" + this.targetUserId + ", sourceUserScreenName='" + this.sourceUserScreenName + "', targetUserScreenName='" + this.targetUserScreenName + "', sourceFollowingTarget=" + this.sourceFollowingTarget + ", sourceFollowedByTarget=" + this.sourceFollowedByTarget + ", sourceCanDm=" + this.sourceCanDm + ", sourceNotificationsEnabled=" + this.sourceNotificationsEnabled + '}';
    }
}
