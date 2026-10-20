package defpackage;

/* loaded from: classes.dex */
public final class azb extends axd<Object> {
    public static final axe a = new azc();
    private final awi b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ azb(awi awiVar, azc azcVar) {
        this(awiVar);
    }

    private azb(awi awiVar) {
        this.b = awiVar;
    }

    @Override // defpackage.axd
    public void a(bav bavVar, Object obj) {
        if (obj == null) {
            bavVar.f();
            return;
        }
        axd a2 = this.b.a((Class) obj.getClass());
        if (a2 instanceof azb) {
            bavVar.d();
            bavVar.e();
        } else {
            a2.a(bavVar, obj);
        }
    }
}
