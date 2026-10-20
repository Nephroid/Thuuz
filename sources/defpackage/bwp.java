package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bwp implements Runnable {
    final /* synthetic */ bwo a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bwp(bwo bwoVar) {
        this.a = bwoVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        WeakReference weakReference;
        try {
            z = this.a.d;
            if (z) {
                weakReference = this.a.b;
                AbstractActivity abstractActivity = (AbstractActivity) weakReference.get();
                if (abstractActivity != null && !abstractActivity.isDestroyed()) {
                    abstractActivity.e().info("turning off spinner in timeout handler");
                    this.a.c(abstractActivity);
                }
            }
        } catch (Throwable th) {
            bzy.getStaticLogger(bwo.class).log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
