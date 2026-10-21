package defpackage;

/* loaded from: classes.dex */
class awo<T> extends axd<T> {
    private axd<T> a;

    public void a(axd<T> axdVar) {
        if (this.a != null) {
            throw new AssertionError();
        }
        this.a = axdVar;
    }

    @Override // defpackage.axd
    public void a(bav bavVar, T t) {
        if (this.a == null) {
            throw new IllegalStateException();
        }
        this.a.a(bavVar, t);
    }
}
