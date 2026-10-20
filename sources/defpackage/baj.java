package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class baj implements axe {
    final /* synthetic */ Class a;
    final /* synthetic */ Class b;
    final /* synthetic */ axd c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public baj(Class cls, Class cls2, axd axdVar) {
        this.a = cls;
        this.b = cls2;
        this.c = axdVar;
    }

    @Override // defpackage.axe
    public <T> axd<T> a(awi awiVar, bau<T> bauVar) {
        Class<? super T> a = bauVar.a();
        if (a == this.a || a == this.b) {
            return this.c;
        }
        return null;
    }

    public String toString() {
        return "Factory[type=" + this.b.getName() + "+" + this.a.getName() + ",adapter=" + this.c + "]";
    }
}
