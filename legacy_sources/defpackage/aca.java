package defpackage;

import android.content.Context;
import com.google.android.gms.internal.gv;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class aca implements vg {
    final /* synthetic */ aby a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aca(aby abyVar) {
        this.a = abyVar;
    }

    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        Object obj;
        ada adaVar;
        ada adaVar2;
        String str;
        obj = this.a.c;
        synchronized (obj) {
            adaVar = this.a.f;
            if (adaVar.isDone()) {
                return;
            }
            acc accVar = new acc(-2, map);
            String d = accVar.d();
            if (d == null) {
                adn.e("URL missing in loadAdUrl GMSG.");
                return;
            }
            if (d.contains("%40mediation_adapters%40")) {
                Context context = gvVar.getContext();
                String str2 = map.get("check_adapters");
                str = this.a.e;
                String replaceAll = d.replaceAll("%40mediation_adapters%40", acp.a(context, str2, str));
                accVar.a(replaceAll);
                adn.d("Ad request URL modified to " + replaceAll);
            }
            adaVar2 = this.a.f;
            adaVar2.a(accVar);
        }
    }
}
