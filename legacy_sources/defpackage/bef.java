package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
class bef extends AsyncTask<Void, Void, Integer> {
    private final int a;
    private final WeakReference<AbstractActivity> b;
    private final int c;

    public bef(int i, AbstractActivity abstractActivity, int i2) {
        this.a = i;
        this.b = new WeakReference<>(abstractActivity);
        this.c = i2;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed()) {
            abstractActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        Throwable th;
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity == null || abstractActivity.isDestroyed()) {
            return -1;
        }
        App j = abstractActivity.j();
        IService F = abstractActivity.F();
        Logger e = abstractActivity.e();
        try {
            byk bykVar = new byk(j, this.a, this.c);
            i = bykVar.l();
            try {
                if (bykVar.y() && F != null) {
                    F.c(this.a, this.c);
                    j.L();
                }
            } catch (Throwable th2) {
                th = th2;
                e.log(Level.SEVERE, "problem: ", th);
                return Integer.valueOf(i);
            }
        } catch (Throwable th3) {
            i = -1;
            th = th3;
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed()) {
            try {
                if (num.intValue() != 0 && abstractActivity.d()) {
                    abstractActivity.b("Cannot Contact Server", bbt.pls_try_again_later);
                }
                abstractActivity.c();
                abstractActivity.e(false);
            } catch (Throwable th) {
                abstractActivity.e().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
    }
}
