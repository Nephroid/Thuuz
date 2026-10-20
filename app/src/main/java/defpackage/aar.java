package defpackage;

import com.google.android.gms.internal.fb;

/* loaded from: classes.dex */
public class aar implements Runnable {
    final /* synthetic */ ach a;
    final /* synthetic */ fb b;

    public aar(fb fbVar, ach achVar) {
        this.b = fbVar;
        this.a = achVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        aap aapVar;
        obj = this.b.d;
        synchronized (obj) {
            aapVar = this.b.a;
            aapVar.a(this.a);
        }
    }
}
