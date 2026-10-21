package twitter4j;

import java.io.Serializable;

/* loaded from: classes.dex */
public interface Relationship extends Serializable, TwitterResponse {
    boolean canSourceDm();

    long getSourceUserId();

    String getSourceUserScreenName();

    long getTargetUserId();

    String getTargetUserScreenName();

    boolean isSourceBlockingTarget();

    boolean isSourceFollowedByTarget();

    boolean isSourceFollowingTarget();

    boolean isSourceNotificationsEnabled();

    boolean isSourceWantRetweets();

    boolean isTargetFollowedBySource();

    boolean isTargetFollowingSource();
}
