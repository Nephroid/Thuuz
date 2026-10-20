package twitter4j.internal.json;

import java.util.Arrays;
import twitter4j.IDs;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public final class IDsJSONImpl extends TwitterResponseImpl implements IDs {
    private static final long serialVersionUID = -6585026560164704953L;
    private long[] ids;
    private long nextCursor;
    private long previousCursor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IDsJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        this.previousCursor = -1L;
        this.nextCursor = -1L;
        String asString = httpResponse.asString();
        init(asString);
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, asString);
        }
    }

    IDsJSONImpl(String str) {
        this.previousCursor = -1L;
        this.nextCursor = -1L;
        init(str);
    }

    private void init(String str) {
        int i = 0;
        try {
            if (str.startsWith("{")) {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray jSONArray = jSONObject.getJSONArray("ids");
                this.ids = new long[jSONArray.length()];
                while (i < jSONArray.length()) {
                    try {
                        this.ids[i] = Long.parseLong(jSONArray.getString(i));
                        i++;
                    } catch (NumberFormatException e) {
                        throw new TwitterException("Twitter API returned malformed response: " + jSONObject, e);
                    }
                }
                this.previousCursor = z_T4JInternalParseUtil.getLong("previous_cursor", jSONObject);
                this.nextCursor = z_T4JInternalParseUtil.getLong("next_cursor", jSONObject);
                return;
            }
            JSONArray jSONArray2 = new JSONArray(str);
            this.ids = new long[jSONArray2.length()];
            while (i < jSONArray2.length()) {
                try {
                    this.ids[i] = Long.parseLong(jSONArray2.getString(i));
                    i++;
                } catch (NumberFormatException e2) {
                    throw new TwitterException("Twitter API returned malformed response: " + jSONArray2, e2);
                }
            }
            return;
        } catch (JSONException e3) {
            throw new TwitterException(e3);
        }
        throw new TwitterException(e3);
    }

    @Override // twitter4j.IDs
    public long[] getIDs() {
        return this.ids;
    }

    @Override // twitter4j.IDs, twitter4j.CursorSupport
    public boolean hasPrevious() {
        return 0 != this.previousCursor;
    }

    @Override // twitter4j.IDs, twitter4j.CursorSupport
    public long getPreviousCursor() {
        return this.previousCursor;
    }

    @Override // twitter4j.IDs, twitter4j.CursorSupport
    public boolean hasNext() {
        return 0 != this.nextCursor;
    }

    @Override // twitter4j.IDs, twitter4j.CursorSupport
    public long getNextCursor() {
        return this.nextCursor;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof IDs) && Arrays.equals(this.ids, ((IDs) obj).getIDs());
    }

    public int hashCode() {
        if (this.ids != null) {
            return Arrays.hashCode(this.ids);
        }
        return 0;
    }

    public String toString() {
        return "IDsJSONImpl{ids=" + Arrays.toString(this.ids) + ", previousCursor=" + this.previousCursor + ", nextCursor=" + this.nextCursor + '}';
    }
}
