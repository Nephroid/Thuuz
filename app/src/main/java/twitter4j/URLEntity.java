package twitter4j;

import java.io.Serializable;

/* loaded from: classes.dex */
public interface URLEntity extends Serializable, TweetEntity {
    String getDisplayURL();

    @Override // twitter4j.TweetEntity
    int getEnd();

    String getExpandedURL();

    @Override // twitter4j.TweetEntity
    int getStart();

    @Override // twitter4j.TweetEntity
    String getText();

    String getURL();
}
