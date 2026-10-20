package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.settings.SettingsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class brm extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ SettingsActivity a;

    public brm(SettingsActivity settingsActivity) {
        this.a = settingsActivity;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        this.a.e(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void doInBackground(Void... voidArr) {
        try {
            IService F = this.a.F();
            if (F != null) {
                F.j();
                if (this.a.j().i()) {
                    F.A();
                    return null;
                }
                return null;
            }
            return null;
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Void r6) {
        String h;
        String i;
        try {
            SettingsActivity settingsActivity = this.a;
            h = this.a.h(false);
            settingsActivity.k = h;
            SettingsActivity settingsActivity2 = this.a;
            i = this.a.i(true);
            settingsActivity2.l = i;
            this.a.aH();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
        this.a.e(false);
    }
}
