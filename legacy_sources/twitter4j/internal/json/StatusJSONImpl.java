package twitter4j.internal.json;

import android.support.v4.widget.ExploreByTouchHelper;
import com.actionbarsherlock.widget.ActivityChooserView;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Place;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.SymbolEntity;
import twitter4j.TwitterException;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import twitter4j.conf.Configuration;
import twitter4j.conf.PropertyConfiguration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.logging.Logger;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public final class StatusJSONImpl extends TwitterResponseImpl implements Serializable, Status {
    private static final Logger logger = Logger.getLogger(StatusJSONImpl.class);
    private static final long serialVersionUID = 7548618898682727465L;
    private long[] contributorsIDs;
    private Date createdAt;
    private long currentUserRetweetId;
    private int favoriteCount;
    private GeoLocation geoLocation;
    private HashtagEntity[] hashtagEntities;
    private long id;
    private String inReplyToScreenName;
    private long inReplyToStatusId;
    private long inReplyToUserId;
    private boolean isFavorited;
    private boolean isPossiblySensitive;
    private boolean isRetweeted;
    private boolean isTruncated;
    private String isoLanguageCode;
    private MediaEntity[] mediaEntities;
    private Place place;
    private long retweetCount;
    private Status retweetedStatus;
    private String source;
    private SymbolEntity[] symbolEntities;
    private String text;
    private URLEntity[] urlEntities;
    private User user;
    private UserMentionEntity[] userMentionEntities;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StatusJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        this.geoLocation = null;
        this.place = null;
        this.currentUserRetweetId = -1L;
        this.user = null;
        JSONObject asJSONObject = httpResponse.asJSONObject();
        init(asJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, asJSONObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StatusJSONImpl(JSONObject jSONObject, Configuration configuration) {
        this.geoLocation = null;
        this.place = null;
        this.currentUserRetweetId = -1L;
        this.user = null;
        init(jSONObject);
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.registerJSONObject(this, jSONObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StatusJSONImpl(JSONObject jSONObject) {
        this.geoLocation = null;
        this.place = null;
        this.currentUserRetweetId = -1L;
        this.user = null;
        init(jSONObject);
    }

    StatusJSONImpl() {
        this.geoLocation = null;
        this.place = null;
        this.currentUserRetweetId = -1L;
        this.user = null;
    }

    private void init(JSONObject jSONObject) {
        this.id = z_T4JInternalParseUtil.getLong("id", jSONObject);
        this.source = z_T4JInternalParseUtil.getUnescapedString("source", jSONObject);
        this.createdAt = z_T4JInternalParseUtil.getDate("created_at", jSONObject);
        this.isTruncated = z_T4JInternalParseUtil.getBoolean("truncated", jSONObject);
        this.inReplyToStatusId = z_T4JInternalParseUtil.getLong("in_reply_to_status_id", jSONObject);
        this.inReplyToUserId = z_T4JInternalParseUtil.getLong("in_reply_to_user_id", jSONObject);
        this.isFavorited = z_T4JInternalParseUtil.getBoolean("favorited", jSONObject);
        this.isRetweeted = z_T4JInternalParseUtil.getBoolean("retweeted", jSONObject);
        this.inReplyToScreenName = z_T4JInternalParseUtil.getUnescapedString("in_reply_to_screen_name", jSONObject);
        this.retweetCount = z_T4JInternalParseUtil.getLong("retweet_count", jSONObject);
        this.favoriteCount = z_T4JInternalParseUtil.getInt("favorite_count", jSONObject);
        this.isPossiblySensitive = z_T4JInternalParseUtil.getBoolean("possibly_sensitive", jSONObject);
        try {
            if (!jSONObject.isNull(PropertyConfiguration.USER)) {
                this.user = new UserJSONImpl(jSONObject.getJSONObject(PropertyConfiguration.USER));
            }
            this.geoLocation = z_T4JInternalJSONImplFactory.createGeoLocation(jSONObject);
            if (!jSONObject.isNull("place")) {
                this.place = new PlaceJSONImpl(jSONObject.getJSONObject("place"));
            }
            if (!jSONObject.isNull("retweeted_status")) {
                this.retweetedStatus = new StatusJSONImpl(jSONObject.getJSONObject("retweeted_status"));
            }
            if (!jSONObject.isNull("contributors")) {
                JSONArray jSONArray = jSONObject.getJSONArray("contributors");
                this.contributorsIDs = new long[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.contributorsIDs[i] = Long.parseLong(jSONArray.getString(i));
                }
            } else {
                this.contributorsIDs = new long[0];
            }
            if (!jSONObject.isNull("entities")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("entities");
                if (!jSONObject2.isNull("user_mentions")) {
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("user_mentions");
                    int length = jSONArray2.length();
                    this.userMentionEntities = new UserMentionEntity[length];
                    for (int i2 = 0; i2 < length; i2++) {
                        this.userMentionEntities[i2] = new UserMentionEntityJSONImpl(jSONArray2.getJSONObject(i2));
                    }
                }
                if (!jSONObject2.isNull("urls")) {
                    JSONArray jSONArray3 = jSONObject2.getJSONArray("urls");
                    int length2 = jSONArray3.length();
                    this.urlEntities = new URLEntity[length2];
                    for (int i3 = 0; i3 < length2; i3++) {
                        this.urlEntities[i3] = new URLEntityJSONImpl(jSONArray3.getJSONObject(i3));
                    }
                }
                if (!jSONObject2.isNull("hashtags")) {
                    JSONArray jSONArray4 = jSONObject2.getJSONArray("hashtags");
                    int length3 = jSONArray4.length();
                    this.hashtagEntities = new HashtagEntity[length3];
                    for (int i4 = 0; i4 < length3; i4++) {
                        this.hashtagEntities[i4] = new HashtagEntityJSONImpl(jSONArray4.getJSONObject(i4));
                    }
                }
                if (!jSONObject2.isNull("symbols")) {
                    JSONArray jSONArray5 = jSONObject2.getJSONArray("symbols");
                    int length4 = jSONArray5.length();
                    this.symbolEntities = new SymbolEntity[length4];
                    for (int i5 = 0; i5 < length4; i5++) {
                        this.symbolEntities[i5] = new HashtagEntityJSONImpl(jSONArray5.getJSONObject(i5));
                    }
                }
                if (!jSONObject2.isNull("media")) {
                    JSONArray jSONArray6 = jSONObject2.getJSONArray("media");
                    int length5 = jSONArray6.length();
                    this.mediaEntities = new MediaEntity[length5];
                    for (int i6 = 0; i6 < length5; i6++) {
                        this.mediaEntities[i6] = new MediaEntityJSONImpl(jSONArray6.getJSONObject(i6));
                    }
                }
            }
            this.isoLanguageCode = z_T4JInternalParseUtil.getRawString("lang", jSONObject);
            this.userMentionEntities = this.userMentionEntities == null ? new UserMentionEntity[0] : this.userMentionEntities;
            this.urlEntities = this.urlEntities == null ? new URLEntity[0] : this.urlEntities;
            this.hashtagEntities = this.hashtagEntities == null ? new HashtagEntity[0] : this.hashtagEntities;
            this.symbolEntities = this.symbolEntities == null ? new SymbolEntity[0] : this.symbolEntities;
            this.mediaEntities = this.mediaEntities == null ? new MediaEntity[0] : this.mediaEntities;
            this.text = HTMLEntity.unescapeAndSlideEntityIncdices(jSONObject.getString("text"), this.userMentionEntities, this.urlEntities, this.hashtagEntities, this.mediaEntities);
            if (!jSONObject.isNull("current_user_retweet")) {
                this.currentUserRetweetId = jSONObject.getJSONObject("current_user_retweet").getLong("id");
            }
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Status status) {
        long id = this.id - status.getId();
        if (id < -2147483648L) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        if (id > 2147483647L) {
            return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        return (int) id;
    }

    @Override // twitter4j.Status
    public Date getCreatedAt() {
        return this.createdAt;
    }

    @Override // twitter4j.Status
    public long getId() {
        return this.id;
    }

    @Override // twitter4j.Status
    public String getText() {
        return this.text;
    }

    @Override // twitter4j.Status
    public String getSource() {
        return this.source;
    }

    @Override // twitter4j.Status
    public boolean isTruncated() {
        return this.isTruncated;
    }

    @Override // twitter4j.Status
    public long getInReplyToStatusId() {
        return this.inReplyToStatusId;
    }

    @Override // twitter4j.Status
    public long getInReplyToUserId() {
        return this.inReplyToUserId;
    }

    @Override // twitter4j.Status
    public String getInReplyToScreenName() {
        return this.inReplyToScreenName;
    }

    @Override // twitter4j.Status
    public GeoLocation getGeoLocation() {
        return this.geoLocation;
    }

    @Override // twitter4j.Status
    public Place getPlace() {
        return this.place;
    }

    @Override // twitter4j.Status
    public long[] getContributors() {
        return this.contributorsIDs;
    }

    @Override // twitter4j.Status
    public boolean isFavorited() {
        return this.isFavorited;
    }

    @Override // twitter4j.Status
    public boolean isRetweeted() {
        return this.isRetweeted;
    }

    @Override // twitter4j.Status
    public int getFavoriteCount() {
        return this.favoriteCount;
    }

    @Override // twitter4j.Status
    public User getUser() {
        return this.user;
    }

    @Override // twitter4j.Status
    public boolean isRetweet() {
        return this.retweetedStatus != null;
    }

    @Override // twitter4j.Status
    public Status getRetweetedStatus() {
        return this.retweetedStatus;
    }

    @Override // twitter4j.Status
    public int getRetweetCount() {
        return (int) this.retweetCount;
    }

    @Override // twitter4j.Status
    public boolean isRetweetedByMe() {
        return this.currentUserRetweetId != -1;
    }

    @Override // twitter4j.Status
    public long getCurrentUserRetweetId() {
        return this.currentUserRetweetId;
    }

    @Override // twitter4j.Status
    public boolean isPossiblySensitive() {
        return this.isPossiblySensitive;
    }

    @Override // twitter4j.EntitySupport
    public UserMentionEntity[] getUserMentionEntities() {
        return this.userMentionEntities;
    }

    @Override // twitter4j.EntitySupport
    public URLEntity[] getURLEntities() {
        return this.urlEntities;
    }

    @Override // twitter4j.EntitySupport
    public HashtagEntity[] getHashtagEntities() {
        return this.hashtagEntities;
    }

    @Override // twitter4j.EntitySupport
    public MediaEntity[] getMediaEntities() {
        return this.mediaEntities;
    }

    @Override // twitter4j.EntitySupport
    public SymbolEntity[] getSymbolEntities() {
        return this.symbolEntities;
    }

    @Override // twitter4j.Status
    public String getIsoLanguageCode() {
        return this.isoLanguageCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResponseList<Status> createStatusList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONArray asJSONArray = httpResponse.asJSONArray();
            int length = asJSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = asJSONArray.getJSONObject(i);
                StatusJSONImpl statusJSONImpl = new StatusJSONImpl(jSONObject);
                if (configuration.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(statusJSONImpl, jSONObject);
                }
                responseListImpl.add(statusJSONImpl);
            }
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(responseListImpl, asJSONArray);
            }
            return responseListImpl;
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    public int hashCode() {
        return (int) this.id;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this != obj) {
            return (obj instanceof Status) && ((Status) obj).getId() == this.id;
        }
        return true;
    }

    public String toString() {
        return "StatusJSONImpl{createdAt=" + this.createdAt + ", id=" + this.id + ", text='" + this.text + "', source='" + this.source + "', isTruncated=" + this.isTruncated + ", inReplyToStatusId=" + this.inReplyToStatusId + ", inReplyToUserId=" + this.inReplyToUserId + ", isFavorited=" + this.isFavorited + ", isRetweeted=" + this.isRetweeted + ", favoriteCount=" + this.favoriteCount + ", inReplyToScreenName='" + this.inReplyToScreenName + "', geoLocation=" + this.geoLocation + ", place=" + this.place + ", retweetCount=" + this.retweetCount + ", isPossiblySensitive=" + this.isPossiblySensitive + ", isoLanguageCode=" + this.isoLanguageCode + ", contributorsIDs=" + this.contributorsIDs + ", retweetedStatus=" + this.retweetedStatus + ", userMentionEntities=" + (this.userMentionEntities == null ? null : Arrays.asList(this.userMentionEntities)) + ", urlEntities=" + (this.urlEntities == null ? null : Arrays.asList(this.urlEntities)) + ", hashtagEntities=" + (this.hashtagEntities == null ? null : Arrays.asList(this.hashtagEntities)) + ", mediaEntities=" + (this.mediaEntities != null ? Arrays.asList(this.mediaEntities) : null) + ", currentUserRetweetId=" + this.currentUserRetweetId + ", user=" + this.user + '}';
    }
}
