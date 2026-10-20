package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class cat extends bzy implements ServiceConnection {
    private WeakReference<AbstractActivity> a;

    public cat(AbstractActivity abstractActivity) {
        this.a = new WeakReference<>(abstractActivity);
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        bpv K;
        try {
            AbstractActivity abstractActivity = this.a.get();
            if (abstractActivity != null) {
                abstractActivity.b(false);
                if (!abstractActivity.isDestroyed()) {
                    ThuuzService a = ((bcv) iBinder).a();
                    abstractActivity.a(a);
                    a.a(abstractActivity);
                    if (a.J() && (K = a.K()) != null && abstractActivity.o()) {
                        abstractActivity.runOnUiThread(new cau(this, K));
                    }
                    bmd Z = abstractActivity.Z();
                    if (Z != null) {
                        Z.e();
                        if (!a.g()) {
                            a(abstractActivity);
                        }
                    }
                    abstractActivity.runOnUiThread(new cav(this));
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "service connect problem: ", th);
        }
    }

    private void a(AbstractActivity abstractActivity) {
        try {
            new caw(this, abstractActivity).execute(new Void[0]);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "service connect problem starting sportinfo async: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(AbstractActivity abstractActivity) {
        if (abstractActivity != null && !abstractActivity.j().bV() && abstractActivity.ab() && !abstractActivity.j().P()) {
            abstractActivity.av();
            if (!abstractActivity.j().a((Activity) abstractActivity)) {
                abstractActivity.aa().postDelayed(new cax(this, new WeakReference(abstractActivity)), 3000L);
            }
            abstractActivity.j().l(true);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != null) {
            abstractActivity.a((IService) null);
            System.gc();
            System.gc();
            getLogger().severe("service unbound from " + abstractActivity.getClass().getSimpleName());
            abstractActivity.runOnUiThread(new cay(this));
        }
    }
}
