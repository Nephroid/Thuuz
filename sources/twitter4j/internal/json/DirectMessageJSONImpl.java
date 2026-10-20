package twitter4j.internal.json;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import twitter4j.DirectMessage;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.ResponseList;
import twitter4j.SymbolEntity;
import twitter4j.TwitterException;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public final class DirectMessageJSONImpl extends TwitterResponseImpl implements Serializable, DirectMessage {
    private static final long serialVersionUID = -7104233663827757577L;
    private Date createdAt;
    private HashtagEntity[] hashtagEntities;
    private long id;
    private MediaEntity[] mediaEntities;
    private User recipient;
    private long recipientId;
    private String recipientScreenName;
    private User sender;
    private long senderId;
    private String senderScreenName;
    private SymbolEntity[] symbolEntities;
    private String text;
    private URLEntity[] urlEntities;
    private UserMentionEntity[] userMentionEntities;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DirectMessageJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        JSONObject asJSONObject = httpResponse.asJSONObject();
        init(asJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, asJSONObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DirectMessageJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    private void init(JSONObject jSONObject) {
        this.id = z_T4JInternalParseUtil.getLong("id", jSONObject);
        this.senderId = z_T4JInternalParseUtil.getLong("sender_id", jSONObject);
        this.recipientId = z_T4JInternalParseUtil.getLong("recipient_id", jSONObject);
        this.createdAt = z_T4JInternalParseUtil.getDate("created_at", jSONObject);
        this.senderScreenName = z_T4JInternalParseUtil.getUnescapedString("sender_screen_name", jSONObject);
        this.recipientScreenName = z_T4JInternalParseUtil.getUnescapedString("recipient_screen_name", jSONObject);
        try {
            this.sender = new UserJSONImpl(jSONObject.getJSONObject("sender"));
            this.recipient = new UserJSONImpl(jSONObject.getJSONObject("recipient"));
            if (!jSONObject.isNull("entities")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("entities");
                if (!jSONObject2.isNull("user_mentions")) {
                    JSONArray jSONArray = jSONObject2.getJSONArray("user_mentions");
                    int length = jSONArray.length();
                    this.userMentionEntities = new UserMentionEntity[length];
                    for (int i = 0; i < length; i++) {
                        this.userMentionEntities[i] = new UserMentionEntityJSONImpl(jSONArray.getJSONObject(i));
                    }
                }
                if (!jSONObject2.isNull("urls")) {
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("urls");
                    int length2 = jSONArray2.length();
                    this.urlEntities = new URLEntity[length2];
                    for (int i2 = 0; i2 < length2; i2++) {
                        this.urlEntities[i2] = new URLEntityJSONImpl(jSONArray2.getJSONObject(i2));
                    }
                }
                if (!jSONObject2.isNull("hashtags")) {
                    JSONArray jSONArray3 = jSONObject2.getJSONArray("hashtags");
                    int length3 = jSONArray3.length();
                    this.hashtagEntities = new HashtagEntity[length3];
                    for (int i3 = 0; i3 < length3; i3++) {
                        this.hashtagEntities[i3] = new HashtagEntityJSONImpl(jSONArray3.getJSONObject(i3));
                    }
                }
                if (!jSONObject2.isNull("symbols")) {
                    JSONArray jSONArray4 = jSONObject2.getJSONArray("symbols");
                    int length4 = jSONArray4.length();
                    this.symbolEntities = new SymbolEntity[length4];
                    for (int i4 = 0; i4 < length4; i4++) {
                        this.symbolEntities[i4] = new HashtagEntityJSONImpl(jSONArray4.getJSONObject(i4));
                    }
                }
                if (!jSONObject2.isNull("media")) {
                    JSONArray jSONArray5 = jSONObject2.getJSONArray("media");
                    int length5 = jSONArray5.length();
                    this.mediaEntities = new MediaEntity[length5];
                    for (int i5 = 0; i5 < length5; i5++) {
                        this.mediaEntities[i5] = new MediaEntityJSONImpl(jSONArray5.getJSONObject(i5));
                    }
                }
            }
            this.userMentionEntities = this.userMentionEntities == null ? new UserMentionEntity[0] : this.userMentionEntities;
            this.urlEntities = this.urlEntities == null ? new URLEntity[0] : this.urlEntities;
            this.hashtagEntities = this.hashtagEntities == null ? new HashtagEntity[0] : this.hashtagEntities;
            this.symbolEntities = this.symbolEntities == null ? new SymbolEntity[0] : this.symbolEntities;
            this.mediaEntities = this.mediaEntities == null ? new MediaEntity[0] : this.mediaEntities;
            this.text = HTMLEntity.unescapeAndSlideEntityIncdices(jSONObject.getString("text"), this.userMentionEntities, this.urlEntities, this.hashtagEntities, this.mediaEntities);
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    @Override // twitter4j.DirectMessage
    public long getId() {
        return this.id;
    }

    @Override // twitter4j.DirectMessage
    public String getText() {
        return this.text;
    }

    @Override // twitter4j.DirectMessage
    public long getSenderId() {
        return this.senderId;
    }

    @Override // twitter4j.DirectMessage
    public long getRecipientId() {
        return this.recipientId;
    }

    @Override // twitter4j.DirectMessage
    public Date getCreatedAt() {
        return this.createdAt;
    }

    @Override // twitter4j.DirectMessage
    public String getSenderScreenName() {
        return this.senderScreenName;
    }

    @Override // twitter4j.DirectMessage
    public String getRecipientScreenName() {
        return this.recipientScreenName;
    }

    @Override // twitter4j.DirectMessage
    public User getSender() {
        return this.sender;
    }

    @Override // twitter4j.DirectMessage
    public User getRecipient() {
        return this.recipient;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ResponseList<DirectMessage> createDirectMessageList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONArray asJSONArray = httpResponse.asJSONArray();
            int length = asJSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = asJSONArray.getJSONObject(i);
                DirectMessageJSONImpl directMessageJSONImpl = new DirectMessageJSONImpl(jSONObject);
                responseListImpl.add(directMessageJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(directMessageJSONImpl, jSONObject);
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
        return (int) this.id;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this != obj) {
            return (obj instanceof DirectMessage) && ((DirectMessage) obj).getId() == this.id;
        }
        return true;
    }

    public String toString() {
        return "DirectMessageJSONImpl{id=" + this.id + ", text='" + this.text + "', sender_id=" + this.senderId + ", recipient_id=" + this.recipientId + ", created_at=" + this.createdAt + ", userMentionEntities=" + (this.userMentionEntities == null ? null : Arrays.asList(this.userMentionEntities)) + ", urlEntities=" + (this.urlEntities == null ? null : Arrays.asList(this.urlEntities)) + ", hashtagEntities=" + (this.hashtagEntities == null ? null : Arrays.asList(this.hashtagEntities)) + ", sender_screen_name='" + this.senderScreenName + "', recipient_screen_name='" + this.recipientScreenName + "', sender=" + this.sender + ", recipient=" + this.recipient + ", userMentionEntities=" + (this.userMentionEntities == null ? null : Arrays.asList(this.userMentionEntities)) + ", urlEntities=" + (this.urlEntities == null ? null : Arrays.asList(this.urlEntities)) + ", hashtagEntities=" + (this.hashtagEntities != null ? Arrays.asList(this.hashtagEntities) : null) + '}';
    }
}
