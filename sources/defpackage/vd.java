package defpackage;

import com.google.android.gms.internal.gv;
import java.util.Map;

/* loaded from: classes.dex */
final class vd implements vg {
    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        String str = map.get("u");
        if (str == null) {
            adn.e("URL missing from httpTrack GMSG.");
        } else {
            new adl(gvVar.getContext(), gvVar.i().b, str).e();
        }
    }
}
