package defpackage;

import com.google.android.gms.internal.av;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ry implements Runnable {
    final /* synthetic */ ajr a;
    final /* synthetic */ rx b;
    private final WeakReference<ajr> c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ry(rx rxVar, ajr ajrVar) {
        this.b = rxVar;
        this.a = ajrVar;
        this.c = new WeakReference<>(this.a);
    }

    @Override // java.lang.Runnable
    public void run() {
        av avVar;
        this.b.d = false;
        ajr ajrVar = this.c.get();
        if (ajrVar != null) {
            avVar = this.b.c;
            ajrVar.b(avVar);
        }
    }
}
