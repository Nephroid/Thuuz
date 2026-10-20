package twitter4j;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public interface QueryResult extends Serializable, TwitterResponse {
    double getCompletedIn();

    int getCount();

    long getMaxId();

    String getQuery();

    String getRefreshURL();

    String getRefreshUrl();

    long getSinceId();

    List<Status> getTweets();

    boolean hasNext();

    Query nextQuery();
}
