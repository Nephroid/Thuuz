package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [TResult] */
/* loaded from: classes.dex */
public class m<TResult> implements k<TResult, l<Void>> {
    @Override // defpackage.k
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public l<Void> then(l<TResult> lVar) {
        if (lVar.c()) {
            return l.g();
        }
        if (lVar.d()) {
            return l.a(lVar.f());
        }
        return l.a((Object) null);
    }
}
