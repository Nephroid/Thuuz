package twitter4j;

import android.support.v4.widget.ExploreByTouchHelper;
import com.actionbarsherlock.widget.ActivityChooserView;
import java.io.Serializable;
import twitter4j.internal.json.z_T4JInternalParseUtil;
import twitter4j.internal.org.json.JSONObject;

/* loaded from: classes.dex */
public class StatusDeletionNoticeImpl implements Serializable, StatusDeletionNotice {
    private static final long serialVersionUID = 1723338404242596062L;
    private long statusId;
    private long userId;

    StatusDeletionNoticeImpl(JSONObject jSONObject) {
        this.statusId = z_T4JInternalParseUtil.getLong("id", jSONObject);
        this.userId = z_T4JInternalParseUtil.getLong("user_id", jSONObject);
    }

    @Override // twitter4j.StatusDeletionNotice
    public long getStatusId() {
        return this.statusId;
    }

    @Override // twitter4j.StatusDeletionNotice
    public long getUserId() {
        return this.userId;
    }

    @Override // java.lang.Comparable
    public int compareTo(StatusDeletionNotice statusDeletionNotice) {
        long statusId = this.statusId - statusDeletionNotice.getStatusId();
        if (statusId < -2147483648L) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        if (statusId > 2147483647L) {
            return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        return (int) statusId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StatusDeletionNoticeImpl statusDeletionNoticeImpl = (StatusDeletionNoticeImpl) obj;
        return this.statusId == statusDeletionNoticeImpl.statusId && this.userId == statusDeletionNoticeImpl.userId;
    }

    public int hashCode() {
        return (((int) (this.statusId ^ (this.statusId >>> 32))) * 31) + ((int) (this.userId ^ (this.userId >>> 32)));
    }

    public String toString() {
        return "StatusDeletionNoticeImpl{statusId=" + this.statusId + ", userId=" + this.userId + '}';
    }
}
