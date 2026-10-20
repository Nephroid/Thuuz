package twitter4j.internal.json;

import com.samsung.android.sdk.richnotification.Utilities;
import twitter4j.TimeZone;
import twitter4j.TwitterException;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public class TimeZoneJSONImpl implements TimeZone {
    private final String NAME;
    private final String TZINFO_NAME;
    private final int UTC_OFFSET;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeZoneJSONImpl(JSONObject jSONObject) {
        try {
            this.UTC_OFFSET = z_T4JInternalParseUtil.getInt("utc_offset", jSONObject);
            this.NAME = jSONObject.getString(Utilities.DB_KEY_IMAGE_NAME);
            this.TZINFO_NAME = jSONObject.getString("tzinfo_name");
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    @Override // twitter4j.TimeZone
    public String getName() {
        return this.NAME;
    }

    @Override // twitter4j.TimeZone
    public String tzinfoName() {
        return this.TZINFO_NAME;
    }

    @Override // twitter4j.TimeZone
    public int utcOffset() {
        return this.UTC_OFFSET;
    }
}
