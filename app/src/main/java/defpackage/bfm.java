package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.FantasySite;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bfm extends AsyncTask<Void, Void, Integer> {
    protected boolean a;
    private final FantasySite b;
    private final WeakReference<AbstractActivity> c;

    public bfm(FantasySite fantasySite, AbstractActivity abstractActivity) {
        this.b = fantasySite;
        this.c = new WeakReference<>(abstractActivity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPreExecute() {
        AbstractActivity abstractActivity = this.c.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed()) {
            abstractActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.thuuz.gtv.activity.AbstractActivity] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.thuuz.gtv.activity.AbstractActivity] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Integer] */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        AbstractActivity abstractActivity = this.c.get();
        if (abstractActivity == 0 || abstractActivity.isDestroyed()) {
            return -1;
        }
        try {
            IService F = abstractActivity.F();
            if (F == null) {
                abstractActivity = -1;
            } else if (this.b == null) {
                abstractActivity = -1;
            } else {
                byt bytVar = new byt(abstractActivity.j(), this.b.a(), F, this.b.b());
                bytVar.l();
                this.a = bytVar.b();
                abstractActivity = Integer.valueOf(bytVar.l());
            }
            return abstractActivity;
        } catch (Throwable th) {
            abstractActivity.e().log(Level.SEVERE, "problem in FantasySiteTeamsUpdateAsync bg: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        AbstractActivity abstractActivity = this.c.get();
        if (abstractActivity != null) {
            try {
                if (!abstractActivity.isDestroyed()) {
                    if (num.intValue() == 0) {
                        if (this.a) {
                            abstractActivity.d("Updated info for " + this.b.c(), "");
                        } else {
                            abstractActivity.d(this.b.c() + " is up to date (no changes)", "");
                        }
                    } else if (abstractActivity.d()) {
                        abstractActivity.a("Cannot Update", bbt.pls_try_again_later);
                    }
                }
            } catch (Throwable th) {
                abstractActivity.e().log(Level.SEVERE, "problem: ", th);
            } finally {
                abstractActivity.e(false);
            }
        }
    }
}
