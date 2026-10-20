package defpackage;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class baf extends axd<awu> {
    @Override // defpackage.axd
    public void a(bav bavVar, awu awuVar) {
        if (awuVar == null || awuVar.g()) {
            bavVar.f();
            return;
        }
        if (awuVar.f()) {
            awx j = awuVar.j();
            if (j.m()) {
                bavVar.a(j.a());
                return;
            } else if (j.l()) {
                bavVar.a(j.c());
                return;
            } else {
                bavVar.b(j.b());
                return;
            }
        }
        if (awuVar.d()) {
            bavVar.b();
            Iterator<awu> it = awuVar.i().iterator();
            while (it.hasNext()) {
                a(bavVar, it.next());
            }
            bavVar.c();
            return;
        }
        if (awuVar.e()) {
            bavVar.d();
            for (Map.Entry<String, awu> entry : awuVar.h().l()) {
                bavVar.a(entry.getKey());
                a(bavVar, entry.getValue());
            }
            bavVar.e();
            return;
        }
        throw new IllegalArgumentException("Couldn't write " + awuVar.getClass());
    }
}
