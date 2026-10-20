package twitter4j.api;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;

/* loaded from: classes.dex */
public interface TimelinesResources {
    ResponseList<Status> getHomeTimeline();

    ResponseList<Status> getHomeTimeline(Paging paging);

    ResponseList<Status> getMentions();

    ResponseList<Status> getMentions(Paging paging);

    ResponseList<Status> getMentionsTimeline();

    ResponseList<Status> getMentionsTimeline(Paging paging);

    ResponseList<Status> getRetweetsOfMe();

    ResponseList<Status> getRetweetsOfMe(Paging paging);

    ResponseList<Status> getUserTimeline();

    ResponseList<Status> getUserTimeline(long j);

    ResponseList<Status> getUserTimeline(long j, Paging paging);

    ResponseList<Status> getUserTimeline(String str);

    ResponseList<Status> getUserTimeline(String str, Paging paging);

    ResponseList<Status> getUserTimeline(Paging paging);
}
