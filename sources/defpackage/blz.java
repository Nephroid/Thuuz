package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.nobar.SplashActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class blz extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ SplashActivity a;

    private blz(SplashActivity splashActivity) {
        this.a = splashActivity;
    }

    public /* synthetic */ blz(SplashActivity splashActivity, blu bluVar) {
        this(splashActivity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0077 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Void doInBackground(java.lang.Void... r12) {
        /*
            Method dump skipped, instructions count: 304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.blz.doInBackground(java.lang.Void[]):java.lang.Void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Void r5) {
        try {
            IService F = this.a.F();
            if (F != null && !this.a.isDestroyed()) {
                this.a.e().info("Splash: getting live game count in async request...");
                new bma(this, this.a, F, this.a).execute(new Void[0]);
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
