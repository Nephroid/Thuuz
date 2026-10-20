package defpackage;

/* loaded from: classes.dex */
final class cp extends cx {
    private final co a;
    private final InterfaceC0005do b;

    public cp(co coVar, InterfaceC0005do interfaceC0005do) {
        this.a = coVar;
        this.b = interfaceC0005do;
    }

    @Override // defpackage.cx
    public void a() {
        this.b.a(this.a);
    }

    @Override // defpackage.cx
    public void a(int i) {
        this.b.a(this.a, i);
    }

    @Override // defpackage.cx
    public void b() {
        this.b.e(this.a);
        this.b.b(this.a);
    }

    @Override // defpackage.cx
    public void c() {
        this.b.c(this.a);
    }

    @Override // defpackage.cx
    public void d() {
        this.b.d(this.a);
    }
}
