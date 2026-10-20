package defpackage;

import com.google.android.gms.internal.Cdo;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class xs implements Runnable {
    final /* synthetic */ Cdo a;
    final /* synthetic */ xr b;
    private final WeakReference<Cdo> c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public xs(xr xrVar, Cdo cdo) {
        this.b = xrVar;
        this.a = cdo;
        this.c = new WeakReference<>(this.a);
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        Cdo cdo = this.c.get();
        z = this.b.b;
        if (z || cdo == null) {
            return;
        }
        cdo.e();
        this.b.b();
    }
}
