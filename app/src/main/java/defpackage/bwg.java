package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bwg extends AsyncTask<Void, Void, Boolean> {
    WeakReference<AbstractActivity> a;

    public bwg(AbstractActivity abstractActivity) {
        this.a = new WeakReference<>(abstractActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != null) {
            try {
                abstractActivity.e(true);
            } catch (Throwable th) {
                abstractActivity.e().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean doInBackground(Void... voidArr) {
        Boolean bool;
        Boolean bool2 = Boolean.TRUE;
        try {
            AbstractActivity abstractActivity = this.a.get();
            if (abstractActivity == null) {
                bool = false;
            } else {
                IService F = abstractActivity.F();
                if (F != null) {
                    F.i();
                    bool = Boolean.valueOf(F.j());
                } else {
                    bool = bool2;
                }
            }
            return bool;
        } catch (Throwable th) {
            return Boolean.FALSE;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Boolean bool) {
        AbstractActivity abstractActivity = this.a.get();
        try {
            if (abstractActivity != null) {
                if (bool.booleanValue()) {
                    abstractActivity.c();
                } else if (abstractActivity.d()) {
                    abstractActivity.c(bbt.unable_to_update, bbt.pls_try_again_later);
                }
            }
        } catch (Throwable th) {
            abstractActivity.e().log(Level.SEVERE, "Cannot handle: ", th);
        } finally {
            abstractActivity.e(false);
        }
    }
}
