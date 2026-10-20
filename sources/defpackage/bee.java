package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bee extends AsyncTask<Void, Void, Integer> {
    final WeakReference<bbl> a;
    final WeakReference<AbstractActivity> b;
    private final IService c;

    public bee(bbl bblVar, IService iService, AbstractActivity abstractActivity) {
        this.a = new WeakReference<>(bblVar);
        this.b = new WeakReference<>(abstractActivity);
        this.c = iService;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        AbstractActivity a = a();
        if (a == null || a.isDestroyed()) {
            return -1;
        }
        try {
            return Integer.valueOf(this.c.B());
        } catch (Throwable th) {
            a.e().log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    public AbstractActivity a() {
        return this.b.get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        AbstractActivity a = a();
        if (a != null) {
            try {
                bbl bblVar = this.a.get();
                if (bblVar != null) {
                    bpo bpoVar = new bpo(null);
                    bpoVar.a(num.intValue());
                    bblVar.onConcludeFetch(bpoVar);
                }
                if (num.intValue() == 0) {
                    a.j().f(true);
                }
            } catch (Throwable th) {
                a.e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }
}
