package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.activity.friends.FriendsActivity;
import com.thuuz.gtv.task.FollowersTask;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bil extends AsyncTask<Void, Void, Integer> {
    final /* synthetic */ FriendsActivity a;

    private bil(FriendsActivity friendsActivity) {
        this.a = friendsActivity;
    }

    public /* synthetic */ bil(FriendsActivity friendsActivity, bih bihVar) {
        this(friendsActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        this.a.e(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        List list;
        List list2;
        List list3;
        List list4;
        int i = -1;
        try {
            FollowersTask followersTask = new FollowersTask(this.a.j(), FollowersTask.FOLLOW_TYPE.FOLLOW_TYPE_BOTH);
            followersTask.l();
            if (followersTask.y()) {
                list = this.a.g;
                list.clear();
                list2 = this.a.g;
                list2.addAll(followersTask.b());
                list3 = this.a.f;
                list3.clear();
                list4 = this.a.f;
                list4.addAll(followersTask.c());
            }
            i = followersTask.v();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        try {
            if (this.a.d()) {
                if (num.intValue() == 0) {
                    this.a.k = true;
                    this.a.aG();
                } else if (this.a.d()) {
                    this.a.b("Cannot Contact Server", bbt.pls_try_again_later);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.j = null;
            this.a.e(false);
        }
    }
}
