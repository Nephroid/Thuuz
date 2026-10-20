package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.lang.ref.WeakReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bxx extends AsyncTask<String, Void, caq> {
    WeakReference<AbstractActivity> a;
    String b = null;
    private final WeakReference<TVProviderFragment> c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bxx(AbstractActivity abstractActivity, TVProviderFragment tVProviderFragment) {
        this.a = new WeakReference<>(abstractActivity);
        this.c = new WeakReference<>(tVProviderFragment);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed()) {
            abstractActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public caq doInBackground(String... strArr) {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity == null || abstractActivity.isDestroyed()) {
            return null;
        }
        if (abstractActivity.F() == null) {
            return null;
        }
        try {
            this.b = strArr[0];
            return a(this.b, abstractActivity.j());
        } catch (Throwable th) {
            abstractActivity.e().log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(caq caqVar) {
        TVProviderFragment tVProviderFragment;
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity == 0 || abstractActivity.isDestroyed() || (tVProviderFragment = this.c.get()) == null) {
            return;
        }
        try {
            if (abstractActivity.d()) {
                if (caqVar != null) {
                    tVProviderFragment.onTVProvidersFetched(caqVar);
                }
            }
        } catch (Throwable th) {
            abstractActivity.e().log(Level.SEVERE, "cannot post tv providers: ", th);
        } finally {
            ((bwr) abstractActivity).aJ();
            abstractActivity.e(false);
        }
    }

    private caq a(String str, App app) {
        Logger b = app.b();
        try {
            bzx bzxVar = new bzx(app, str);
            bzxVar.l();
            return bzxVar.c();
        } catch (Throwable th) {
            b.log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }
}
