package defpackage;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* loaded from: classes.dex */
final class azl<T> extends axd<T> {
    private final awi a;
    private final axd<T> b;
    private final Type c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public azl(awi awiVar, axd<T> axdVar, Type type) {
        this.a = awiVar;
        this.b = axdVar;
        this.c = type;
    }

    @Override // defpackage.axd
    public void a(bav bavVar, T t) {
        axd<T> axdVar = this.b;
        Type a = a(this.c, t);
        if (a != this.c) {
            axdVar = this.a.a((bau) bau.a(a));
            if ((axdVar instanceof azf) && !(this.b instanceof azf)) {
                axdVar = this.b;
            }
        }
        axdVar.a(bavVar, t);
    }

    private Type a(Type type, Object obj) {
        if (obj == null) {
            return type;
        }
        if (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) {
            return obj.getClass();
        }
        return type;
    }
}
