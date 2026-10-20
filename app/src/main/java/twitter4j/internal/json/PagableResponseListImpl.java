package twitter4j.internal.json;

import twitter4j.PagableResponseList;
import twitter4j.RateLimitStatus;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class PagableResponseListImpl<T> extends ResponseListImpl implements PagableResponseList {
    private static final long serialVersionUID = 1531950333538983361L;
    private final long nextCursor;
    private final long previousCursor;

    PagableResponseListImpl(RateLimitStatus rateLimitStatus, int i) {
        super(rateLimitStatus, i);
        this.previousCursor = 0L;
        this.nextCursor = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PagableResponseListImpl(int i, JSONObject jSONObject, HttpResponse httpResponse) {
        super(i, httpResponse);
        this.previousCursor = z_T4JInternalParseUtil.getLong("previous_cursor", jSONObject);
        this.nextCursor = z_T4JInternalParseUtil.getLong("next_cursor", jSONObject);
    }

    @Override // twitter4j.PagableResponseList, twitter4j.CursorSupport
    public boolean hasPrevious() {
        return 0 != this.previousCursor;
    }

    @Override // twitter4j.PagableResponseList, twitter4j.CursorSupport
    public long getPreviousCursor() {
        return this.previousCursor;
    }

    @Override // twitter4j.PagableResponseList, twitter4j.CursorSupport
    public boolean hasNext() {
        return 0 != this.nextCursor;
    }

    @Override // twitter4j.PagableResponseList, twitter4j.CursorSupport
    public long getNextCursor() {
        return this.nextCursor;
    }
}
