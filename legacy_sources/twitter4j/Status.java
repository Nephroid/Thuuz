package twitter4j;

import java.io.Serializable;
import java.util.Date;

/* loaded from: classes.dex */
public interface Status extends Serializable, Comparable<Status>, EntitySupport, TwitterResponse {
    long[] getContributors();

    Date getCreatedAt();

    long getCurrentUserRetweetId();

    int getFavoriteCount();

    GeoLocation getGeoLocation();

    long getId();

    String getInReplyToScreenName();

    long getInReplyToStatusId();

    long getInReplyToUserId();

    String getIsoLanguageCode();

    Place getPlace();

    int getRetweetCount();

    Status getRetweetedStatus();

    String getSource();

    String getText();

    User getUser();

    boolean isFavorited();

    boolean isPossiblySensitive();

    boolean isRetweet();

    boolean isRetweeted();

    boolean isRetweetedByMe();

    boolean isTruncated();
}
