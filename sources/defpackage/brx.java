package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.settings.SettingsActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class brx extends AsyncTask<Void, Void, String> {
    final WeakReference<SettingsActivity> a;
    final /* synthetic */ SettingsActivity b;

    private brx(SettingsActivity settingsActivity) {
        this.b = settingsActivity;
        this.a = new WeakReference<>(this.b);
    }

    public /* synthetic */ brx(SettingsActivity settingsActivity, brm brmVar) {
        this(settingsActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        if (this.a.get() != null) {
            this.b.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(Void... voidArr) {
        IService F;
        String str;
        if (this.a.get() == null || (F = this.b.F()) == null) {
            return null;
        }
        try {
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "Problem: ", th);
            str = null;
        }
        if (!this.b.j().x().isEmpty() && !F.p()) {
            return null;
        }
        str = this.b.h(false);
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        if (this.a.get() != null && this.b.d()) {
            try {
                if (str == null) {
                    if (this.b.d()) {
                        this.b.b(bbt.unable_to_update, bbt.pls_try_again_later);
                    }
                }
                this.b.k = str;
                this.b.aH();
            } catch (Throwable th) {
                this.b.e().log(Level.SEVERE, "problem: ", th);
            } finally {
                this.b.e(false);
            }
        }
    }
}
