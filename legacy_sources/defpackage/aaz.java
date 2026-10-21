package defpackage;

import com.google.android.gms.internal.fe;
import com.google.android.gms.internal.fk;
import com.google.android.gms.internal.gv;

/* loaded from: classes.dex */
public class aaz implements Runnable {
    final /* synthetic */ aas a;
    final /* synthetic */ fe b;

    public aaz(fe feVar, aas aasVar) {
        this.b = feVar;
        this.a = aasVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        fk fkVar;
        gv gvVar;
        fk fkVar2;
        obj = this.b.f;
        synchronized (obj) {
            fkVar = this.b.h;
            if (fkVar.e != -2) {
                return;
            }
            gvVar = this.b.c;
            gvVar.f().a(this.b);
            aas aasVar = this.a;
            fkVar2 = this.b.h;
            aasVar.a(fkVar2);
        }
    }
}
