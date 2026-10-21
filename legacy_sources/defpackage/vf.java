package defpackage;

import com.google.android.gms.internal.gv;
import java.util.Map;

/* loaded from: classes.dex */
final class vf implements vg {
    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        String str = map.get("tx");
        String str2 = map.get("ty");
        String str3 = map.get("td");
        try {
            int parseInt = Integer.parseInt(str);
            int parseInt2 = Integer.parseInt(str2);
            int parseInt3 = Integer.parseInt(str3);
            agc h = gvVar.h();
            if (h != null) {
                h.a().a(parseInt, parseInt2, parseInt3);
            }
        } catch (NumberFormatException e) {
            adn.e("Could not parse touch parameters from gmsg.");
        }
    }
}
