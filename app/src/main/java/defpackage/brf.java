package defpackage;

import android.app.PendingIntent;
import android.os.AsyncTask;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.TreeSet;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class brf extends AsyncTask<Void, Void, Integer> {
    private final PendingIntent a;
    private final TreeSet<bpz> b = new TreeSet<>();
    private final WeakReference<AbstractActivity> c;

    public brf(PendingIntent pendingIntent, AbstractActivity abstractActivity) {
        this.a = pendingIntent;
        this.c = new WeakReference<>(abstractActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity abstractActivity = this.c.get();
        if (abstractActivity != null) {
            abstractActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        Throwable th;
        int i2;
        bzf bzfVar;
        AbstractActivity abstractActivity = this.c.get();
        if (abstractActivity == null) {
            return -1;
        }
        try {
            bzfVar = new bzf(abstractActivity.j(), this.a);
            i = bzfVar.l();
        } catch (Throwable th2) {
            i = -1;
            th = th2;
        }
        try {
        } catch (Throwable th3) {
            th = th3;
            abstractActivity.e().log(Level.SEVERE, "problem: ", th);
            i2 = i;
            return Integer.valueOf(i2);
        }
        if (bzfVar.y()) {
            this.b.addAll(bzfVar.c());
            return Integer.valueOf(bzfVar.v());
        }
        i2 = i;
        return Integer.valueOf(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        AbstractActivity abstractActivity = this.c.get();
        if (abstractActivity != 0 && !abstractActivity.isDestroyed()) {
            ((bre) abstractActivity).a(new bmb(this.b, num.intValue()));
            ((bre) abstractActivity).c(num);
            abstractActivity.e(false);
        }
    }
}
