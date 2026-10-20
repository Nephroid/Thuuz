package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bgk extends AsyncTask<Void, Void, Integer> {
    private final WeakReference<SettingsFantasyTeamsActivity> a;

    public bgk(SettingsFantasyTeamsActivity settingsFantasyTeamsActivity) {
        this.a = new WeakReference<>(settingsFantasyTeamsActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        SettingsFantasyTeamsActivity settingsFantasyTeamsActivity = this.a.get();
        if (settingsFantasyTeamsActivity != null && !settingsFantasyTeamsActivity.isDestroyed()) {
            settingsFantasyTeamsActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        SettingsFantasyTeamsActivity settingsFantasyTeamsActivity = this.a.get();
        if (settingsFantasyTeamsActivity == null || settingsFantasyTeamsActivity.isDestroyed()) {
            return -1;
        }
        App j = settingsFantasyTeamsActivity.j();
        Logger e = settingsFantasyTeamsActivity.e();
        IService F = settingsFantasyTeamsActivity.F();
        if (F == null) {
            return -1;
        }
        try {
            if (F.r().size() == 0) {
                byi byiVar = new byi(j, F);
                byiVar.l();
                if (!byiVar.y()) {
                    return -1;
                }
            }
            TreeSet<bpe> r = F.r();
            car carVar = new car();
            Iterator<bpe> it = r.iterator();
            while (it.hasNext()) {
                bpe next = it.next();
                if (next.f()) {
                    carVar.a(new byr(j, F, next.a()));
                    carVar.a(new byl(j));
                }
            }
            try {
                try {
                    i = Integer.valueOf(carVar.a(60000L));
                } finally {
                    carVar.a();
                }
            } catch (InterruptedException e2) {
                e.log(Level.SEVERE, "Cannot handle: ", (Throwable) e2);
                i = -1;
                carVar.a();
            }
            return i;
        } catch (Throwable th) {
            e.log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        SettingsFantasyTeamsActivity settingsFantasyTeamsActivity = this.a.get();
        if (settingsFantasyTeamsActivity != null) {
            try {
                if (!settingsFantasyTeamsActivity.isDestroyed()) {
                    if (num.intValue() == 0) {
                        settingsFantasyTeamsActivity.h(true);
                    }
                    settingsFantasyTeamsActivity.aF();
                }
            } catch (Throwable th) {
                settingsFantasyTeamsActivity.e().log(Level.SEVERE, "problem: ", th);
            } finally {
                settingsFantasyTeamsActivity.e(false);
                settingsFantasyTeamsActivity.g = null;
            }
        }
    }
}
