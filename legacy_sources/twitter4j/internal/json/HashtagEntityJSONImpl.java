package twitter4j.internal.json;

import twitter4j.HashtagEntity;
import twitter4j.SymbolEntity;
import twitter4j.TwitterException;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
class HashtagEntityJSONImpl extends EntityIndex implements HashtagEntity, SymbolEntity {
    private static final long serialVersionUID = 4068992372784813200L;
    private String text;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashtagEntityJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashtagEntityJSONImpl(int i, int i2, String str) {
        setStart(i);
        setEnd(i2);
        this.text = str;
    }

    HashtagEntityJSONImpl() {
    }

    private void init(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("indices");
            setStart(jSONArray.getInt(0));
            setEnd(jSONArray.getInt(1));
            if (!jSONObject.isNull("text")) {
                this.text = jSONObject.getString("text");
            }
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    @Override // twitter4j.HashtagEntity, twitter4j.TweetEntity
    public String getText() {
        return this.text;
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
        HashtagEntityJSONImpl hashtagEntityJSONImpl = (HashtagEntityJSONImpl) obj;
        if (this.text != null) {
            if (this.text.equals(hashtagEntityJSONImpl.text)) {
                return true;
            }
        } else if (hashtagEntityJSONImpl.text == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.text != null) {
            return this.text.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "HashtagEntityJSONImpl{text='" + this.text + "'}";
    }
}
