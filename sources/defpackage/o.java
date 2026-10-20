package defpackage;

/* JADX INFO: Add missing generic type declarations: [TContinuationResult] */
/* loaded from: classes.dex */
class o<TContinuationResult> implements k<TContinuationResult, Void> {
    final /* synthetic */ n a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(n nVar) {
        this.a = nVar;
    }

    @Override // defpackage.k
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void then(l<TContinuationResult> lVar) {
        if (lVar.c()) {
            this.a.c.c();
            return null;
        }
        if (lVar.d()) {
            this.a.c.b(lVar.f());
            return null;
        }
        this.a.c.b((t) lVar.e());
        return null;
    }
}
