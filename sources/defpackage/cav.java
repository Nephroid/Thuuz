package defpackage;

import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
class cav implements Runnable {
    final /* synthetic */ cat a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cav(cat catVar) {
        this.a = catVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        WeakReference weakReference;
        try {
            weakReference = this.a.a;
            AbstractActivity abstractActivity = (AbstractActivity) weakReference.get();
            if (abstractActivity != null) {
                if (abstractActivity.isDestroyed()) {
                    abstractActivity.aj();
                    return;
                }
                abstractActivity.m();
                bmd Z = abstractActivity.Z();
                if (Z != null) {
                    IService F = abstractActivity.F();
                    if (F != null) {
                        abstractActivity.a(F.o());
                    }
                    Z.d();
                }
                this.a.b(abstractActivity);
            }
        } catch (Throwable th) {
            this.a.getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }
}
