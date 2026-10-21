package defpackage;

import com.google.android.gms.internal.av;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.gv;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public final class acg {
    public final av a;
    public final gv b;
    public final List<String> c;
    public final int d;
    public final List<String> e;
    public final List<String> f;
    public final int g;
    public final long h;
    public final String i;
    public final JSONObject j;
    public final boolean k;
    public final vy l;
    public final wl m;
    public final String n;
    public final vz o;
    public final wb p;
    public final long q;
    public final ay r;
    public final long s;
    public final long t;
    public final long u;
    public final String v;
    public final uo w;

    public acg(ach achVar, gv gvVar, vy vyVar, wl wlVar, String str, wb wbVar, uo uoVar) {
        this(achVar.a.c, gvVar, achVar.b.d, achVar.e, achVar.b.f, achVar.b.j, achVar.b.l, achVar.b.k, achVar.a.i, achVar.b.h, vyVar, wlVar, str, achVar.c, wbVar, achVar.b.i, achVar.d, achVar.b.g, achVar.f, achVar.g, achVar.b.o, achVar.h, uoVar);
    }

    public acg(av avVar, gv gvVar, List<String> list, int i, List<String> list2, List<String> list3, int i2, long j, String str, boolean z, vy vyVar, wl wlVar, String str2, vz vzVar, wb wbVar, long j2, ay ayVar, long j3, long j4, long j5, String str3, JSONObject jSONObject, uo uoVar) {
        this.a = avVar;
        this.b = gvVar;
        this.c = list != null ? Collections.unmodifiableList(list) : null;
        this.d = i;
        this.e = list2 != null ? Collections.unmodifiableList(list2) : null;
        this.f = list3 != null ? Collections.unmodifiableList(list3) : null;
        this.g = i2;
        this.h = j;
        this.i = str;
        this.k = z;
        this.l = vyVar;
        this.m = wlVar;
        this.n = str2;
        this.o = vzVar;
        this.p = wbVar;
        this.q = j2;
        this.r = ayVar;
        this.s = j3;
        this.t = j4;
        this.u = j5;
        this.v = str3;
        this.j = jSONObject;
        this.w = uoVar;
    }
}
