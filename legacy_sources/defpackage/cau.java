package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class cau implements Runnable {
    final /* synthetic */ bpv a;
    final /* synthetic */ cat b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cau(cat catVar, bpv bpvVar) {
        this.b = catVar;
        this.a = bpvVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        WeakReference weakReference;
        weakReference = this.b.a;
        AbstractActivity abstractActivity = (AbstractActivity) weakReference.get();
        if (abstractActivity != null) {
            abstractActivity.a(this.a);
        }
    }
}
