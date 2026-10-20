package defpackage;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
public final class ayr<E> extends axd<Object> {
    public static final axe a = new ays();
    private final Class<E> b;
    private final axd<E> c;

    public ayr(awi awiVar, axd<E> axdVar, Class<E> cls) {
        this.c = new azl(awiVar, axdVar, cls);
        this.b = cls;
    }

    @Override // defpackage.axd
    public void a(bav bavVar, Object obj) {
        if (obj == null) {
            bavVar.f();
            return;
        }
        bavVar.b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.c.a(bavVar, Array.get(obj, i));
        }
        bavVar.c();
    }
}
