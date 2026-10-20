package defpackage;

import com.facebook.AppEventsConstants;
import com.google.android.gms.internal.dj;
import com.google.android.gms.internal.gv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@zy
/* loaded from: classes.dex */
public final class vl implements vg {
    private final vh a;
    private final ajx b;

    public vl(vh vhVar, ajx ajxVar) {
        this.a = vhVar;
        this.b = ajxVar;
    }

    private static boolean a(Map<String, String> map) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close"));
    }

    private static int b(Map<String, String> map) {
        String str = map.get("o");
        if (str != null) {
            if ("p".equalsIgnoreCase(str)) {
                return acx.c();
            }
            if ("l".equalsIgnoreCase(str)) {
                return acx.b();
            }
        }
        return -1;
    }

    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        String str = map.get("a");
        if (str == null) {
            adn.e("Action missing from an open GMSG.");
            return;
        }
        if (this.b != null && !this.b.b()) {
            this.b.a(map.get("u"));
            return;
        }
        adq f = gvVar.f();
        if ("expand".equalsIgnoreCase(str)) {
            if (gvVar.j()) {
                adn.e("Cannot expand WebView that is already expanded.");
                return;
            } else {
                f.a(a(map), b(map));
                return;
            }
        }
        if ("webapp".equalsIgnoreCase(str)) {
            String str2 = map.get("u");
            if (str2 != null) {
                f.a(a(map), b(map), str2);
                return;
            } else {
                f.a(a(map), b(map), map.get("html"), map.get("baseurl"));
                return;
            }
        }
        if (!"in_app_purchase".equalsIgnoreCase(str)) {
            f.a(new dj(map.get("i"), map.get("u"), map.get("m"), map.get("p"), map.get("c"), map.get("f"), map.get("e")));
            return;
        }
        String str3 = map.get("product_id");
        String str4 = map.get("report_urls");
        if (this.a != null) {
            if (str4 == null || str4.isEmpty()) {
                this.a.a(str3, new ArrayList<>());
            } else {
                this.a.a(str3, new ArrayList<>(Arrays.asList(str4.split(" "))));
            }
        }
    }
}
