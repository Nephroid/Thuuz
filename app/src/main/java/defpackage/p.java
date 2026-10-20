package defpackage;

import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [TResult] */
/* loaded from: classes.dex */
public class p<TResult> implements k<TResult, Void> {
    final /* synthetic */ t a;
    final /* synthetic */ k b;
    final /* synthetic */ Executor c;
    final /* synthetic */ l d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(l lVar, t tVar, k kVar, Executor executor) {
        this.d = lVar;
        this.a = tVar;
        this.b = kVar;
        this.c = executor;
    }

    @Override // defpackage.k
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void then(l<TResult> lVar) {
        l.c(this.a, this.b, lVar, this.c);
        return null;
    }
}
