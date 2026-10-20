package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class bam implements axe {
    final /* synthetic */ Class a;
    final /* synthetic */ axd b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bam(Class cls, axd axdVar) {
        this.a = cls;
        this.b = axdVar;
    }

    @Override // defpackage.axe
    public <T> axd<T> a(awi awiVar, bau<T> bauVar) {
        if (this.a.isAssignableFrom(bauVar.a())) {
            return this.b;
        }
        return null;
    }

    public String toString() {
        return "Factory[typeHierarchy=" + this.a.getName() + ",adapter=" + this.b + "]";
    }
}
