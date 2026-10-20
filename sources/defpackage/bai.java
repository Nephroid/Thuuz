package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class bai implements axe {
    final /* synthetic */ Class a;
    final /* synthetic */ axd b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bai(Class cls, axd axdVar) {
        this.a = cls;
        this.b = axdVar;
    }

    @Override // defpackage.axe
    public <T> axd<T> a(awi awiVar, bau<T> bauVar) {
        if (bauVar.a() == this.a) {
            return this.b;
        }
        return null;
    }

    public String toString() {
        return "Factory[type=" + this.a.getName() + ",adapter=" + this.b + "]";
    }
}
