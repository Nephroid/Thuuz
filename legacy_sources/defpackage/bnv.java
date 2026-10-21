package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.task.FollowersTask;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bnv extends AsyncTask<Void, Void, Integer> {
    final /* synthetic */ GuideActivity a;

    private bnv(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    public /* synthetic */ bnv(GuideActivity guideActivity, bnb bnbVar) {
        this(guideActivity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        Set set;
        Set set2;
        try {
            FollowersTask followersTask = new FollowersTask(this.a.j(), FollowersTask.FOLLOW_TYPE.FOLLOW_TYPE_FOLLOWING);
            followersTask.l();
            if (followersTask.y()) {
                Set<bqb> c = followersTask.c();
                HashSet hashSet = new HashSet();
                Iterator<bqb> it = c.iterator();
                while (it.hasNext()) {
                    hashSet.add(Integer.valueOf(it.next().a()));
                }
                set = this.a.r;
                set.clear();
                set2 = this.a.r;
                set2.addAll(hashSet);
            }
            return Integer.valueOf(followersTask.v());
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        Set set;
        try {
            if (this.a.isDestroyed()) {
                return;
            }
            if (num.intValue() == 0) {
                set = this.a.r;
                if (set.size() > 0) {
                    this.a.K = true;
                } else {
                    this.a.K = false;
                    this.a.bu();
                }
            } else {
                this.a.e().warning("cannot get num followers; ignoring");
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.H = null;
        }
    }
}
