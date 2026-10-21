package defpackage;

import com.google.android.gms.internal.fe;

/* loaded from: classes.dex */
public class aax implements Runnable {
    final /* synthetic */ acg a;
    final /* synthetic */ fe b;

    public aax(fe feVar, acg acgVar) {
        this.b = feVar;
        this.a = acgVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        aav aavVar;
        obj = this.b.f;
        synchronized (obj) {
            aavVar = this.b.b;
            aavVar.a(this.a);
        }
    }
}
