package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.fantasy.FantasySiteSelectActivity;
import com.thuuz.gtv.data.FantasySite;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bfk extends AsyncTask<Void, Void, Integer> {
    private final List<FantasySite> a;
    private final WeakReference<FantasySiteSelectActivity> b;

    public /* synthetic */ bfk(FantasySiteSelectActivity fantasySiteSelectActivity, bfh bfhVar) {
        this(fantasySiteSelectActivity);
    }

    private bfk(FantasySiteSelectActivity fantasySiteSelectActivity) {
        this.a = new ArrayList();
        this.b = new WeakReference<>(fantasySiteSelectActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        FantasySiteSelectActivity fantasySiteSelectActivity = this.b.get();
        if (fantasySiteSelectActivity != null && !fantasySiteSelectActivity.isDestroyed()) {
            fantasySiteSelectActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        Throwable th;
        int i2;
        bys bysVar;
        int i3;
        FantasySiteSelectActivity fantasySiteSelectActivity = this.b.get();
        if (fantasySiteSelectActivity != null && !fantasySiteSelectActivity.isDestroyed()) {
            try {
                App j = fantasySiteSelectActivity.j();
                i3 = fantasySiteSelectActivity.h;
                bysVar = new bys(j, i3, fantasySiteSelectActivity.F());
                i = bysVar.l();
            } catch (Throwable th2) {
                i = -1;
                th = th2;
            }
            try {
                if (bysVar.y()) {
                    this.a.addAll(bysVar.b());
                }
                i2 = i;
            } catch (Throwable th3) {
                th = th3;
                fantasySiteSelectActivity.e().log(Level.SEVERE, "problem: ", th);
                i2 = i;
                return Integer.valueOf(i2);
            }
            return Integer.valueOf(i2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        List list;
        List list2;
        FantasySiteSelectActivity fantasySiteSelectActivity = this.b.get();
        if (fantasySiteSelectActivity != null) {
            try {
                if (fantasySiteSelectActivity.isDestroyed()) {
                    return;
                }
                if (fantasySiteSelectActivity.d()) {
                    if (num.intValue() == 0) {
                        list = fantasySiteSelectActivity.f;
                        list.clear();
                        list2 = fantasySiteSelectActivity.f;
                        list2.addAll(this.a);
                    } else {
                        fantasySiteSelectActivity.b("Cannot Contact Server", bbt.pls_try_again_later);
                    }
                    fantasySiteSelectActivity.aF();
                }
            } catch (Throwable th) {
                fantasySiteSelectActivity.e().log(Level.SEVERE, "problem: ", th);
            } finally {
                fantasySiteSelectActivity.e(false);
                fantasySiteSelectActivity.i = null;
            }
        }
    }
}
