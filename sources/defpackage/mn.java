package defpackage;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class mn {
    private static Map<String, ly<?>> a = new HashMap();

    static {
        a(agg.a);
        a(agg.A);
        a(agg.r);
        a(agg.y);
        a(agg.B);
        a(agg.l);
        a(agg.m);
        a(agg.j);
        a(agg.o);
        a(agg.w);
        a(agg.b);
        a(agg.t);
        a(agg.d);
        a(agg.k);
        a(agg.e);
        a(agg.f);
        a(agg.g);
        a(agg.q);
        a(agg.n);
        a(agg.s);
        a(agg.u);
        a(agg.v);
        a(agg.x);
        a(agg.C);
        a(agg.D);
        a(agg.i);
        a(agg.h);
        a(agg.z);
        a(agg.p);
        a(agg.c);
        a(agg.E);
        a(agg.F);
        a(agg.G);
        a(agr.a);
        a(agr.c);
        a(agr.d);
        a(agr.e);
        a(agr.b);
        a(agy.a);
        a(agy.b);
    }

    public static ly<?> a(String str) {
        return a.get(str);
    }

    private static void a(ly<?> lyVar) {
        if (a.containsKey(lyVar.a())) {
            throw new IllegalArgumentException("Duplicate field name registered: " + lyVar.a());
        }
        a.put(lyVar.a(), lyVar);
    }
}
