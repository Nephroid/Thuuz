package defpackage;

/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes.dex */
class axy<T> extends axd<T> {
    final /* synthetic */ boolean a;
    final /* synthetic */ boolean b;
    final /* synthetic */ awi c;
    final /* synthetic */ bau d;
    final /* synthetic */ axx e;
    private axd<T> f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public axy(axx axxVar, boolean z, boolean z2, awi awiVar, bau bauVar) {
        this.e = axxVar;
        this.a = z;
        this.b = z2;
        this.c = awiVar;
        this.d = bauVar;
    }

    @Override // defpackage.axd
    public void a(bav bavVar, T t) {
        if (this.b) {
            bavVar.f();
        } else {
            a().a(bavVar, t);
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
}
