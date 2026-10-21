package twitter4j;

import java.io.Serializable;

/* loaded from: classes.dex */
public interface SymbolEntity extends Serializable, TweetEntity {
    @Override // twitter4j.TweetEntity
    int getEnd();

    @Override // twitter4j.TweetEntity
    int getStart();
}
