package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
class cay implements Runnable {
    final /* synthetic */ cat a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cay(cat catVar) {
        this.a = catVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        WeakReference weakReference;
        try {
            System.gc();
            System.gc();
            weakReference = this.a.a;
            AbstractActivity abstractActivity = (AbstractActivity) weakReference.get();
            if (abstractActivity != null) {
                abstractActivity.j().bX();
            }
        } catch (Throwable th) {
            this.a.getLogger().log(Level.SEVERE, "disconnect problem: ", th);
        }
    }
}
