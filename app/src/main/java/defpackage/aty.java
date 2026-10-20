package defpackage;

import java.util.List;

/* loaded from: classes.dex */
class aty implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ long b;
    final /* synthetic */ atx c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aty(atx atxVar, List list, long j) {
        this.c = atxVar;
        this.a = list;
        this.b = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.b(this.a, this.b);
    }
}
