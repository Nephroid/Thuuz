package twitter4j;

import java.io.Serializable;

/* loaded from: classes.dex */
public interface UserMentionEntity extends Serializable, TweetEntity {
    @Override // twitter4j.TweetEntity
    int getEnd();

    long getId();

    String getName();

    String getScreenName();

    @Override // twitter4j.TweetEntity
    int getStart();

    @Override // twitter4j.TweetEntity
    String getText();
}
