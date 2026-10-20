package defpackage;

import com.google.android.gms.internal.dk;
import com.google.android.gms.internal.gv;
import java.util.Map;

/* loaded from: classes.dex */
final class vb implements vg {
    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        dk d = gvVar.d();
        if (d == null) {
            adn.e("A GMSG tried to close something that wasn't an overlay.");
        } else {
            d.a();
        }
    }
}
