package defpackage;

/* loaded from: classes.dex */
final class axa<T> extends axd<T> {
    private final awz<T> a;
    private final awt<T> b;
    private final awi c;
    private final bau<T> d;
    private final axe e;
    private axd<T> f;

    private axa(awz<T> awzVar, awt<T> awtVar, awi awiVar, bau<T> bauVar, axe axeVar) {
        this.a = awzVar;
        this.b = awtVar;
        this.c = awiVar;
        this.d = bauVar;
        this.e = axeVar;
    }

    @Override // defpackage.axd
    public void a(bav bavVar, T t) {
        if (this.a == null) {
            a().a(bavVar, t);
        } else if (t == null) {
            bavVar.f();
        } else {
            ayi.a(this.a.serialize(t, this.d.b(), this.c.b), bavVar);
        }
    }

    private axd<T> a() {
        axd<T> axdVar = this.f;
        if (axdVar != null) {
            return axdVar;
        }
        axd<T> a = this.c.a(this.e, this.d);
        this.f = a;
        return a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static axe a(bau<?> bauVar, Object obj) {
        return new axc(obj, bauVar, false, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static axe b(bau<?> bauVar, Object obj) {
        return new axc(obj, bauVar, bauVar.b() == bauVar.a(), null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static axe a(Class<?> cls, Object obj) {
        return new axc(obj, null, false, cls);
    }
}
