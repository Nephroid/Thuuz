package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class cax implements Runnable {
    final /* synthetic */ WeakReference a;
    final /* synthetic */ cat b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cax(cat catVar, WeakReference weakReference) {
        this.b = catVar;
        this.a = weakReference;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            AbstractActivity abstractActivity = (AbstractActivity) this.a.get();
            if (abstractActivity != null && abstractActivity.ax()) {
                abstractActivity.aw();
            }
        } catch (Throwable th) {
            this.b.getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }
}
