package defpackage;

import com.google.android.gms.internal.gv;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class abz implements vg {
    final /* synthetic */ aby a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abz(aby abyVar) {
        this.a = abyVar;
    }

    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        Object obj;
        ada adaVar;
        ada adaVar2;
        obj = this.a.c;
        synchronized (obj) {
            adaVar = this.a.f;
            if (adaVar.isDone()) {
                return;
            }
            acc accVar = new acc(1, map);
            adn.e("Invalid " + accVar.e() + " request error: " + accVar.b());
            adaVar2 = this.a.f;
            adaVar2.a(accVar);
        }
    }
}
