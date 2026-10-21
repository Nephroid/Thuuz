package twitter4j.internal.json;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import twitter4j.RateLimitStatus;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public final class RateLimitStatusJSONImpl implements Serializable, RateLimitStatus {
    private static final long serialVersionUID = 1625565652687304084L;
    private int limit;
    private int remaining;
    private int resetTimeInSeconds;
    private int secondsUntilReset;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, RateLimitStatus> createRateLimitStatuses(HttpResponse httpResponse, Configuration configuration) {
        JSONObject asJSONObject = httpResponse.asJSONObject();
        Map<String, RateLimitStatus> createRateLimitStatuses = createRateLimitStatuses(asJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(createRateLimitStatuses, asJSONObject);
        }
        return createRateLimitStatuses;
    }

    static Map<String, RateLimitStatus> createRateLimitStatuses(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("resources");
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject((String) keys.next());
                Iterator keys2 = jSONObject3.keys();
                while (keys2.hasNext()) {
                    String str = (String) keys2.next();
                    hashMap.put(str, new RateLimitStatusJSONImpl(jSONObject3.getJSONObject(str)));
                }
            }
            return Collections.unmodifiableMap(hashMap);
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    private RateLimitStatusJSONImpl(int i, int i2, int i3) {
        this.limit = i;
        this.remaining = i2;
        this.resetTimeInSeconds = i3;
        this.secondsUntilReset = (int) (((i3 * 1000) - System.currentTimeMillis()) / 1000);
    }

    RateLimitStatusJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    void init(JSONObject jSONObject) {
        this.limit = z_T4JInternalParseUtil.getInt("limit", jSONObject);
        this.remaining = z_T4JInternalParseUtil.getInt("remaining", jSONObject);
        this.resetTimeInSeconds = z_T4JInternalParseUtil.getInt("reset", jSONObject);
        this.secondsUntilReset = (int) (((this.resetTimeInSeconds * 1000) - System.currentTimeMillis()) / 1000);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RateLimitStatus createFromResponseHeader(HttpResponse httpResponse) {
        String responseHeader;
        if (httpResponse == null || (responseHeader = httpResponse.getResponseHeader("X-Rate-Limit-Limit")) == null) {
            return null;
        }
        int parseInt = Integer.parseInt(responseHeader);
        String responseHeader2 = httpResponse.getResponseHeader("X-Rate-Limit-Remaining");
        if (responseHeader2 == null) {
            return null;
        }
        int parseInt2 = Integer.parseInt(responseHeader2);
        String responseHeader3 = httpResponse.getResponseHeader("X-Rate-Limit-Reset");
        if (responseHeader3 != null) {
            return new RateLimitStatusJSONImpl(parseInt, parseInt2, (int) Long.parseLong(responseHeader3));
        }
        return null;
    }

    @Override // twitter4j.RateLimitStatus
    public int getRemaining() {
        return this.remaining;
    }

    @Override // twitter4j.RateLimitStatus
    public int getRemainingHits() {
        return getRemaining();
    }

    @Override // twitter4j.RateLimitStatus
    public int getLimit() {
        return this.limit;
    }

    @Override // twitter4j.RateLimitStatus
    public int getResetTimeInSeconds() {
        return this.resetTimeInSeconds;
    }

    @Override // twitter4j.RateLimitStatus
    public int getSecondsUntilReset() {
        return this.secondsUntilReset;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RateLimitStatusJSONImpl rateLimitStatusJSONImpl = (RateLimitStatusJSONImpl) obj;
        return this.limit == rateLimitStatusJSONImpl.limit && this.remaining == rateLimitStatusJSONImpl.remaining && this.resetTimeInSeconds == rateLimitStatusJSONImpl.resetTimeInSeconds && this.secondsUntilReset == rateLimitStatusJSONImpl.secondsUntilReset;
    }

    public int hashCode() {
        return (((((this.remaining * 31) + this.limit) * 31) + this.resetTimeInSeconds) * 31) + this.secondsUntilReset;
    }

    public String toString() {
        return "RateLimitStatusJSONImpl{remaining=" + this.remaining + ", limit=" + this.limit + ", resetTimeInSeconds=" + this.resetTimeInSeconds + ", secondsUntilReset=" + this.secondsUntilReset + '}';
    }
}
