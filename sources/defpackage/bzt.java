package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bzt extends AsyncTask<Void, Void, Integer> {
    private final WeakReference<AbstractActivity> a;

    public bzt(AbstractActivity abstractActivity) {
        this.a = new WeakReference<>(abstractActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity a = a();
        if (a != null) {
            a.e().info("spinning in sportinfo");
            a.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i = -1;
        AbstractActivity a = a();
        if (a == null) {
            return -1;
        }
        try {
            IService F = a.F();
            if (F != null) {
                if (F.i()) {
                    i = 0;
                }
            }
        } catch (Throwable th) {
            a.e().log(Level.SEVERE, "problem: ", th);
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractActivity a() {
        return this.a.get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        AbstractActivity a = a();
        if (a != null) {
            try {
                if (num.intValue() == 0) {
                    a.c();
                }
            } catch (Throwable th) {
                a.e().log(Level.SEVERE, "problem: ", th);
            } finally {
                a.e().info("hiding spin in sportinfo");
                a.e(false);
            }
        }
    }
}
