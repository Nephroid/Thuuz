package twitter4j.internal.json;

import java.io.Serializable;
import twitter4j.RateLimitStatus;
import twitter4j.TwitterResponse;
import twitter4j.internal.http.HttpResponse;

/* loaded from: classes.dex */
abstract class TwitterResponseImpl implements Serializable, TwitterResponse {
    private static final long serialVersionUID = -7284708239736552059L;
    private transient int accessLevel;
    private transient RateLimitStatus rateLimitStatus;

    public TwitterResponseImpl() {
        this.rateLimitStatus = null;
        this.accessLevel = 0;
    }

    public TwitterResponseImpl(HttpResponse httpResponse) {
        this.rateLimitStatus = null;
        this.rateLimitStatus = RateLimitStatusJSONImpl.createFromResponseHeader(httpResponse);
        this.accessLevel = z_T4JInternalParseUtil.toAccessLevel(httpResponse);
    }

    public RateLimitStatus getRateLimitStatus() {
        return this.rateLimitStatus;
    }

    public int getAccessLevel() {
        return this.accessLevel;
    }
}
