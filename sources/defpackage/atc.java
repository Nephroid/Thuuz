package defpackage;

import com.google.android.gms.internal.b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
class atc {
    private static final ast<ww> a = new ast<>(atp.a(), true);
    private final asc b;
    private final Map<String, asd> c;
    private final Map<String, asd> d;
    private final Map<String, asd> e;
    private final ats<asz, ast<ww>> f;
    private final ats<String, atg> g;
    private final Set<ata> h;
    private final arn i;
    private final Map<String, ath> j;
    private volatile String k;
    private int l;

    private ast<ww> a(String str, Set<String> set, asl aslVar) {
        asz next;
        this.l++;
        atg a2 = this.g.a(str);
        if (a2 != null && !this.b.a()) {
            a(a2.b(), set);
            this.l--;
            return a2.a();
        }
        ath athVar = this.j.get(str);
        if (athVar == null) {
            asj.a(a() + "Invalid macro: " + str);
            this.l--;
            return a;
        }
        ast<Set<asz>> a3 = a(str, athVar.a(), athVar.b(), athVar.c(), athVar.e(), athVar.d(), set, aslVar.b());
        if (a3.a().isEmpty()) {
            next = athVar.f();
        } else {
            if (a3.a().size() > 1) {
                asj.b(a() + "Multiple macros active for macroName " + str);
            }
            next = a3.a().iterator().next();
        }
        if (next == null) {
            this.l--;
            return a;
        }
        ast<ww> a4 = a(this.e, next, set, aslVar.a());
        ast<ww> astVar = a4 == a ? a : new ast<>(a4.a(), a3.b() && a4.b());
        ww b = next.b();
        if (astVar.b()) {
            this.g.a(str, new atg(astVar, b));
        }
        a(b, set);
        this.l--;
        return astVar;
    }

    private ast<ww> a(Map<String, asd> map, asz aszVar, Set<String> set, asu asuVar) {
        boolean z;
        ww wwVar = aszVar.a().get(b.FUNCTION.toString());
        if (wwVar == null) {
            asj.a("No function id in properties");
            return a;
        }
        String str = wwVar.g;
        asd asdVar = map.get(str);
        if (asdVar == null) {
            asj.a(str + " has no backing implementation.");
            return a;
        }
        ast<ww> a2 = this.f.a(aszVar);
        if (a2 != null && !this.b.a()) {
            return a2;
        }
        HashMap hashMap = new HashMap();
        boolean z2 = true;
        for (Map.Entry<String, ww> entry : aszVar.a().entrySet()) {
            ast<ww> a3 = a(entry.getValue(), set, asuVar.a(entry.getKey()).a(entry.getValue()));
            if (a3 == a) {
                return a;
            }
            if (a3.b()) {
                aszVar.a(entry.getKey(), a3.a());
                z = z2;
            } else {
                z = false;
            }
            hashMap.put(entry.getKey(), a3.a());
            z2 = z;
        }
        if (!asdVar.a(hashMap.keySet())) {
            asj.a("Incorrect keys for function " + str + " required " + asdVar.b() + " had " + hashMap.keySet());
            return a;
        }
        boolean z3 = z2 && asdVar.a();
        ast<ww> astVar = new ast<>(asdVar.a(hashMap), z3);
        if (z3) {
            this.f.a(aszVar, astVar);
        }
        asuVar.a(astVar.a());
        return astVar;
    }

    private ast<Set<asz>> a(Set<ata> set, Set<String> set2, atf atfVar, atb atbVar) {
        Set<asz> hashSet = new HashSet<>();
        Set<asz> hashSet2 = new HashSet<>();
        boolean z = true;
        for (ata ataVar : set) {
            asx a2 = atbVar.a();
            ast<Boolean> a3 = a(ataVar, set2, a2);
            if (a3.a().booleanValue()) {
                atfVar.a(ataVar, hashSet, hashSet2, a2);
            }
            z = z && a3.b();
        }
        hashSet.removeAll(hashSet2);
        atbVar.a(hashSet);
        return new ast<>(hashSet, z);
    }

    private ast<ww> a(ww wwVar, Set<String> set, atq atqVar) {
        if (!wwVar.l) {
            return new ast<>(wwVar, true);
        }
        switch (wwVar.a) {
            case 2:
                ww a2 = asy.a(wwVar);
                a2.c = new ww[wwVar.c.length];
                for (int i = 0; i < wwVar.c.length; i++) {
                    ast<ww> a3 = a(wwVar.c[i], set, atqVar.a(i));
                    if (a3 == a) {
                        return a;
                    }
                    a2.c[i] = a3.a();
                }
                return new ast<>(a2, false);
            case 3:
                ww a4 = asy.a(wwVar);
                if (wwVar.d.length != wwVar.e.length) {
                    asj.a("Invalid serving value: " + wwVar.toString());
                    return a;
                }
                a4.d = new ww[wwVar.d.length];
                a4.e = new ww[wwVar.d.length];
                for (int i2 = 0; i2 < wwVar.d.length; i2++) {
                    ast<ww> a5 = a(wwVar.d[i2], set, atqVar.b(i2));
                    ast<ww> a6 = a(wwVar.e[i2], set, atqVar.c(i2));
                    if (a5 == a || a6 == a) {
                        return a;
                    }
                    a4.d[i2] = a5.a();
                    a4.e[i2] = a6.a();
                }
                return new ast<>(a4, false);
            case 4:
                if (set.contains(wwVar.f)) {
                    asj.a("Macro cycle detected.  Current macro reference: " + wwVar.f + ".  Previous macro references: " + set.toString() + ".");
                    return a;
                }
                set.add(wwVar.f);
                ast<ww> a7 = atr.a(a(wwVar.f, set, atqVar.a()), wwVar.k);
                set.remove(wwVar.f);
                return a7;
            case 5:
            case 6:
            default:
                asj.a("Unknown type: " + wwVar.a);
                return a;
            case 7:
                ww a8 = asy.a(wwVar);
                a8.j = new ww[wwVar.j.length];
                for (int i3 = 0; i3 < wwVar.j.length; i3++) {
                    ast<ww> a9 = a(wwVar.j[i3], set, atqVar.d(i3));
                    if (a9 == a) {
                        return a;
                    }
                    a8.j[i3] = a9.a();
                }
                return new ast<>(a8, false);
        }
    }

    private String a() {
        if (this.l <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.l));
        for (int i = 2; i < this.l; i++) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }

    private void a(ww wwVar, Set<String> set) {
        ast<ww> a2;
        if (wwVar == null || (a2 = a(wwVar, set, new ass())) == a) {
            return;
        }
        Object c = atp.c(a2.a());
        if (c instanceof Map) {
            this.i.a((Map<String, Object>) c);
            return;
        }
        if (!(c instanceof List)) {
            asj.b("pushAfterEvaluate: value not a Map or List");
            return;
        }
        for (Object obj : (List) c) {
            if (obj instanceof Map) {
                this.i.a((Map<String, Object>) obj);
            } else {
                asj.b("pushAfterEvaluate: value not a Map");
            }
        }
    }

    ast<Boolean> a(asz aszVar, Set<String> set, asu asuVar) {
        ast<ww> a2 = a(this.d, aszVar, set, asuVar);
        Boolean b = atp.b(a2.a());
        asuVar.a(atp.c(b));
        return new ast<>(b, a2.b());
    }

    ast<Boolean> a(ata ataVar, Set<String> set, asx asxVar) {
        Iterator<asz> it = ataVar.b().iterator();
        boolean z = true;
        while (it.hasNext()) {
            ast<Boolean> a2 = a(it.next(), set, asxVar.a());
            if (a2.a().booleanValue()) {
                asxVar.a(atp.c((Object) false));
                return new ast<>(false, a2.b());
            }
            z = z && a2.b();
        }
        Iterator<asz> it2 = ataVar.a().iterator();
        while (it2.hasNext()) {
            ast<Boolean> a3 = a(it2.next(), set, asxVar.b());
            if (!a3.a().booleanValue()) {
                asxVar.a(atp.c((Object) false));
                return new ast<>(false, a3.b());
            }
            z = z && a3.b();
        }
        asxVar.a(atp.c((Object) true));
        return new ast<>(true, z);
    }

    ast<Set<asz>> a(String str, Set<ata> set, Map<ata, List<asz>> map, Map<ata, List<String>> map2, Map<ata, List<asz>> map3, Map<ata, List<String>> map4, Set<String> set2, atb atbVar) {
        return a(set, set2, new atd(this, map, map2, map3, map4), atbVar);
    }

    ast<Set<asz>> a(Set<ata> set, atb atbVar) {
        return a(set, new HashSet(), new ate(this), atbVar);
    }

    public synchronized void a(String str) {
        b(str);
        asb a2 = this.b.a(str);
        atw a3 = a2.a();
        Iterator<asz> it = a(this.h, a3.b()).a().iterator();
        while (it.hasNext()) {
            a(this.c, it.next(), new HashSet(), a3.a());
        }
        a2.b();
        b(null);
    }

    synchronized void b(String str) {
        this.k = str;
    }
}
