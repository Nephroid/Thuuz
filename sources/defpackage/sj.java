package defpackage;

import com.google.android.gms.internal.gv;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class sj implements vg {
    final /* synthetic */ sd a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public sj(sd sdVar) {
        this.a = sdVar;
    }

    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        sb sbVar;
        if (this.a.a(map)) {
            StringBuilder append = new StringBuilder().append("Received request to untrack: ");
            sbVar = this.a.e;
            adn.a(append.append(sbVar.d()).toString());
            this.a.c();
        }
    }
}
