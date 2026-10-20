package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class awl extends axd<Number> {
    final /* synthetic */ awi a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public awl(awi awiVar) {
        this.a = awiVar;
    }

    @Override // defpackage.axd
    public void a(bav bavVar, Number number) {
        if (number == null) {
            bavVar.f();
            return;
        }
        this.a.a(number.doubleValue());
        bavVar.a(number);
    }
}
