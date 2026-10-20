package defpackage;

import android.net.Uri;
import com.google.android.gms.internal.gv;
import com.google.android.gms.internal.l;
import java.util.Map;

/* loaded from: classes.dex */
final class va implements vg {
    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        Uri uri;
        agc h;
        String str = map.get("u");
        if (str == null) {
            adn.e("URL missing from click GMSG.");
            return;
        }
        Uri parse = Uri.parse(str);
        try {
            h = gvVar.h();
        } catch (l e) {
            adn.e("Unable to append parameter to URL: " + str);
        }
        if (h != null && h.b(parse)) {
            uri = h.a(parse, gvVar.getContext());
            new adl(gvVar.getContext(), gvVar.i().b, uri.toString()).e();
        }
        uri = parse;
        new adl(gvVar.getContext(), gvVar.i().b, uri.toString()).e();
    }
}
