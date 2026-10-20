package twitter4j.internal.json;

import java.util.ArrayList;
import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;
import twitter4j.internal.http.HttpResponse;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ResponseListImpl<T> extends ArrayList<T> implements ResponseList<T> {
    private static final long serialVersionUID = 5646617841989265312L;
    private transient int accessLevel;
    private transient RateLimitStatus rateLimitStatus;

    ResponseListImpl(HttpResponse httpResponse) {
        this.rateLimitStatus = null;
        init(httpResponse);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResponseListImpl(int i, HttpResponse httpResponse) {
        super(i);
        this.rateLimitStatus = null;
        init(httpResponse);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResponseListImpl(RateLimitStatus rateLimitStatus, int i) {
        this.rateLimitStatus = null;
        this.rateLimitStatus = rateLimitStatus;
        this.accessLevel = i;
    }

    private void init(HttpResponse httpResponse) {
        this.rateLimitStatus = RateLimitStatusJSONImpl.createFromResponseHeader(httpResponse);
        this.accessLevel = z_T4JInternalParseUtil.toAccessLevel(httpResponse);
    }

    @Override // twitter4j.ResponseList, twitter4j.TwitterResponse
    public RateLimitStatus getRateLimitStatus() {
        return this.rateLimitStatus;
    }

    @Override // twitter4j.TwitterResponse
    public int getAccessLevel() {
        return this.accessLevel;
    }
}
