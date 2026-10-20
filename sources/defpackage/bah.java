package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class bah implements axe {
    final /* synthetic */ bau a;
    final /* synthetic */ axd b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bah(bau bauVar, axd axdVar) {
        this.a = bauVar;
        this.b = axdVar;
    }

    @Override // defpackage.axe
    public <T> axd<T> a(awi awiVar, bau<T> bauVar) {
        if (bauVar.equals(this.a)) {
            return this.b;
        }
        return null;
    }
}
