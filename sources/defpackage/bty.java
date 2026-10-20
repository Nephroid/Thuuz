package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.SettingsLoginActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bty extends AsyncTask<Void, Void, Integer> {
    private final boolean a;
    private final WeakReference<AbstractActivity> b;
    private final App c;

    public bty(AbstractActivity abstractActivity, boolean z) {
        this.a = z;
        this.b = new WeakReference<>(abstractActivity);
        this.c = abstractActivity.j();
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity != null) {
            try {
                abstractActivity.e(true);
                if (this.a) {
                    cad.a(abstractActivity, "Facebook Mismatch", "This device has a different Facebook login than the one previously stored with your Thuuz account, so that old association will be removed from your Thuuz account. Please use the Account Info settings to explicitly connect to your current Facebook login if that is what you want.");
                }
            } catch (Throwable th) {
                abstractActivity.e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        try {
            bzr bzrVar = new bzr(this.c, this.c.aY().a(this.c.bc()), true);
            int l = bzrVar.l();
            if (bzrVar.y()) {
                this.c.q(this.c.aY().b());
            }
            return Integer.valueOf(l);
        } catch (Throwable th) {
            bzy.getStaticLogger(SettingsLoginActivity.class).log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        SettingsLoginActivity settingsLoginActivity = (SettingsLoginActivity) this.b.get();
        if (settingsLoginActivity == null) {
            return;
        }
        try {
            if (settingsLoginActivity.isDestroyed() || !settingsLoginActivity.d()) {
                return;
            }
            if (num.intValue() != 0 && num.intValue() != -23) {
                settingsLoginActivity.c("Facebook Problem", "Disconnect failed. Please check Facebook associations later.");
            }
        } catch (Throwable th) {
            settingsLoginActivity.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            settingsLoginActivity.e(false);
            settingsLoginActivity.finish();
        }
    }
}
