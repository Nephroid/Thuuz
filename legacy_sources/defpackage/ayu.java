package defpackage;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes.dex */
final class ayu<E> extends axd<Collection<E>> {
    private final axd<E> a;
    private final ayg<? extends Collection<E>> b;

    public ayu(awi awiVar, Type type, axd<E> axdVar, ayg<? extends Collection<E>> aygVar) {
        this.a = new azl(awiVar, axdVar, type);
        this.b = aygVar;
    }

    @Override // defpackage.axd
    public void a(bav bavVar, Collection<E> collection) {
        if (collection == null) {
            bavVar.f();
            return;
        }
        bavVar.b();
        Iterator<E> it = collection.iterator();
        while (it.hasNext()) {
            this.a.a(bavVar, it.next());
        }
        bavVar.c();
    }
}
