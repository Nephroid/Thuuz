package defpackage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
final class aza<K, V> extends axd<Map<K, V>> {
    final /* synthetic */ ayz a;
    private final axd<K> b;
    private final axd<V> c;
    private final ayg<? extends Map<K, V>> d;

    public aza(ayz ayzVar, awi awiVar, Type type, axd<K> axdVar, Type type2, axd<V> axdVar2, ayg<? extends Map<K, V>> aygVar) {
        this.a = ayzVar;
        this.b = new azl(awiVar, axdVar, type);
        this.c = new azl(awiVar, axdVar2, type2);
        this.d = aygVar;
    }

    @Override // defpackage.axd
    public void a(bav bavVar, Map<K, V> map) {
        boolean z;
        int i = 0;
        if (map != null) {
            z = this.a.b;
            if (!z) {
                bavVar.d();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    bavVar.a(String.valueOf(entry.getKey()));
                    this.c.a(bavVar, entry.getValue());
                }
                bavVar.e();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            boolean z2 = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                awu a = this.b.a(entry2.getKey());
                arrayList.add(a);
                arrayList2.add(entry2.getValue());
                z2 = (a.d() || a.e()) | z2;
            }
            if (z2) {
                bavVar.b();
                while (i < arrayList.size()) {
                    bavVar.b();
                    ayi.a((awu) arrayList.get(i), bavVar);
                    this.c.a(bavVar, arrayList2.get(i));
                    bavVar.c();
                    i++;
                }
                bavVar.c();
                return;
            }
            bavVar.d();
            while (i < arrayList.size()) {
                bavVar.a(a((awu) arrayList.get(i)));
                this.c.a(bavVar, arrayList2.get(i));
                i++;
            }
            bavVar.e();
            return;
        }
        bavVar.f();
    }

    private String a(awu awuVar) {
        if (awuVar.f()) {
            awx j = awuVar.j();
            if (j.m()) {
                return String.valueOf(j.a());
            }
            if (j.l()) {
                return Boolean.toString(j.c());
            }
            if (j.n()) {
                return j.b();
            }
            throw new AssertionError();
        }
        if (awuVar.g()) {
            return "null";
        }
        throw new AssertionError();
    }
}
