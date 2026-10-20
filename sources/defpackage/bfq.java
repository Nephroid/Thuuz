package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.fantasy.FantasyTeamActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bfq extends AsyncTask<Void, Void, Integer> {
    IService a;
    private final WeakReference<FantasyTeamActivity> b;

    public bfq(FantasyTeamActivity fantasyTeamActivity) {
        this.b = new WeakReference<>(fantasyTeamActivity);
        this.a = fantasyTeamActivity.F();
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        FantasyTeamActivity fantasyTeamActivity = this.b.get();
        if (fantasyTeamActivity != null && !fantasyTeamActivity.isDestroyed()) {
            fantasyTeamActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        int i2;
        FantasyTeamActivity fantasyTeamActivity = this.b.get();
        if (fantasyTeamActivity == null || fantasyTeamActivity.isDestroyed()) {
            return -1;
        }
        try {
        } catch (Throwable th) {
            fantasyTeamActivity.e().log(Level.SEVERE, "problem: ", th);
        }
        if (this.a != null) {
            App j = fantasyTeamActivity.j();
            IService iService = this.a;
            i2 = fantasyTeamActivity.f;
            i = new byn(j, iService, i2).l();
            return Integer.valueOf(i);
        }
        i = -1;
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        boolean z;
        FantasyTeamActivity fantasyTeamActivity = this.b.get();
        if (fantasyTeamActivity != null) {
            try {
                if (!fantasyTeamActivity.isDestroyed()) {
                    if (fantasyTeamActivity.d() && num.intValue() != 0) {
                        fantasyTeamActivity.a("Failed to Contact Server", bbt.pls_try_again_later);
                    }
                    z = fantasyTeamActivity.j;
                    if (!z && num.intValue() == 0) {
                        fantasyTeamActivity.j = true;
                    }
                }
            } catch (Throwable th) {
                fantasyTeamActivity.e().log(Level.SEVERE, "problem: ", th);
            } finally {
                fantasyTeamActivity.i = null;
                fantasyTeamActivity.e(false);
            }
        }
    }
}
