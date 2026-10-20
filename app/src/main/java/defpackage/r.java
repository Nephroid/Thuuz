package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [TContinuationResult, TResult] */
/* loaded from: classes.dex */
public class r<TContinuationResult, TResult> implements k<TResult, l<TContinuationResult>> {
    final /* synthetic */ k a;
    final /* synthetic */ l b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(l lVar, k kVar) {
        this.b = lVar;
        this.a = kVar;
    }

    @Override // defpackage.k
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public l<TContinuationResult> then(l<TResult> lVar) {
        if (lVar.d()) {
            return l.a(lVar.f());
        }
        if (lVar.c()) {
            return l.g();
        }
        return lVar.a((k) this.a);
    }
}
