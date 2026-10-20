package defpackage;

import com.google.android.gms.internal.fk;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@zy
/* loaded from: classes.dex */
public final class acb {
    private String a;
    private String b;
    private String c;
    private List<String> d;
    private String e;
    private String f;
    private List<String> g;
    private List<String> k;
    private long h = -1;
    private boolean i = false;
    private final long j = -1;
    private long l = -1;
    private int m = -1;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;

    static String a(Map<String, List<String>> map, String str) {
        List<String> list = map.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    static long b(Map<String, List<String>> map, String str) {
        List<String> list = map.get(str);
        if (list != null && !list.isEmpty()) {
            try {
                return Float.parseFloat(r0) * 1000.0f;
            } catch (NumberFormatException e) {
                adn.e("Could not parse float from " + str + " header: " + list.get(0));
            }
        }
        return -1L;
    }

    private void b(Map<String, List<String>> map) {
        this.a = a(map, "X-Afma-Ad-Size");
    }

    static List<String> c(Map<String, List<String>> map, String str) {
        String str2;
        List<String> list = map.get(str);
        if (list == null || list.isEmpty() || (str2 = list.get(0)) == null) {
            return null;
        }
        return Arrays.asList(str2.trim().split("\\s+"));
    }

    private void c(Map<String, List<String>> map) {
        List<String> c = c(map, "X-Afma-Click-Tracking-Urls");
        if (c != null) {
            this.d = c;
        }
    }

    private void d(Map<String, List<String>> map) {
        List<String> list = map.get("X-Afma-Debug-Dialog");
        if (list == null || list.isEmpty()) {
            return;
        }
        this.e = list.get(0);
    }

    private boolean d(Map<String, List<String>> map, String str) {
        List<String> list = map.get(str);
        return (list == null || list.isEmpty() || !Boolean.valueOf(list.get(0)).booleanValue()) ? false : true;
    }

    private void e(Map<String, List<String>> map) {
        List<String> c = c(map, "X-Afma-Tracking-Urls");
        if (c != null) {
            this.g = c;
        }
    }

    private void f(Map<String, List<String>> map) {
        long b = b(map, "X-Afma-Interstitial-Timeout");
        if (b != -1) {
            this.h = b;
        }
    }

    private void g(Map<String, List<String>> map) {
        this.f = a(map, "X-Afma-ActiveView");
    }

    private void h(Map<String, List<String>> map) {
        this.o |= d(map, "X-Afma-Native");
    }

    private void i(Map<String, List<String>> map) {
        this.n |= d(map, "X-Afma-Custom-Rendering-Allowed");
    }

    private void j(Map<String, List<String>> map) {
        this.i |= d(map, "X-Afma-Mediation");
    }

    private void k(Map<String, List<String>> map) {
        List<String> c = c(map, "X-Afma-Manual-Tracking-Urls");
        if (c != null) {
            this.k = c;
        }
    }

    private void l(Map<String, List<String>> map) {
        long b = b(map, "X-Afma-Refresh-Rate");
        if (b != -1) {
            this.l = b;
        }
    }

    private void m(Map<String, List<String>> map) {
        List<String> list = map.get("X-Afma-Orientation");
        if (list == null || list.isEmpty()) {
            return;
        }
        String str = list.get(0);
        if ("portrait".equalsIgnoreCase(str)) {
            this.m = acx.c();
        } else if ("landscape".equalsIgnoreCase(str)) {
            this.m = acx.b();
        }
    }

    private void n(Map<String, List<String>> map) {
        List<String> list = map.get("X-Afma-Use-HTTPS");
        if (list == null || list.isEmpty()) {
            return;
        }
        this.p = Boolean.valueOf(list.get(0)).booleanValue();
    }

    private void o(Map<String, List<String>> map) {
        List<String> list = map.get("X-Afma-Content-Url-Opted-Out");
        if (list == null || list.isEmpty()) {
            return;
        }
        this.q = Boolean.valueOf(list.get(0)).booleanValue();
    }

    public fk a(long j) {
        return new fk(this.b, this.c, this.d, this.g, this.h, this.i, -1L, this.k, this.l, this.m, this.a, j, this.e, this.f, this.n, this.o, this.p, this.q);
    }

    public void a(String str, Map<String, List<String>> map, String str2) {
        this.b = str;
        this.c = str2;
        a(map);
    }

    public void a(Map<String, List<String>> map) {
        b(map);
        c(map);
        d(map);
        e(map);
        f(map);
        j(map);
        k(map);
        l(map);
        m(map);
        g(map);
        n(map);
        i(map);
        h(map);
        o(map);
    }
}
