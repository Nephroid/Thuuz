package twitter4j.internal.json;

import android.support.v4.widget.ExploreByTouchHelper;
import com.actionbarsherlock.widget.ActivityChooserView;
import java.io.Serializable;

/* loaded from: classes.dex */
abstract class EntityIndex implements Serializable, Comparable<EntityIndex> {
    private static final long serialVersionUID = 3864336402689899384L;
    private int start = -1;
    private int end = -1;

    @Override // java.lang.Comparable
    public int compareTo(EntityIndex entityIndex) {
        long j = this.start - entityIndex.start;
        if (j < -2147483648L) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        if (j > 2147483647L) {
            return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        return (int) j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStart(int i) {
        this.start = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEnd(int i) {
        this.end = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getStart() {
        return this.start;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getEnd() {
        return this.end;
    }
}
