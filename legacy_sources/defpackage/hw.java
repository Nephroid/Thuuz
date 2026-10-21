package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class hw implements hp {
    final /* synthetic */ hj a;
    final /* synthetic */ hs b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public hw(hs hsVar, hj hjVar) {
        this.b = hsVar;
        this.a = hjVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001d, code lost:
    
        if (r0 < r1) goto L7;
     */
    @Override // defpackage.hp, defpackage.ha
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(defpackage.gw r3) {
        /*
            r2 = this;
            hs r0 = r2.b
            java.util.concurrent.locks.Lock r0 = defpackage.hs.a(r0)
            r0.lock()
            hs r0 = r2.b     // Catch: java.lang.Throwable -> L3e
            gw r0 = defpackage.hs.i(r0)     // Catch: java.lang.Throwable -> L3e
            if (r0 == 0) goto L1f
            hj r0 = r2.a     // Catch: java.lang.Throwable -> L3e
            int r0 = r0.a()     // Catch: java.lang.Throwable -> L3e
            hs r1 = r2.b     // Catch: java.lang.Throwable -> L3e
            int r1 = defpackage.hs.j(r1)     // Catch: java.lang.Throwable -> L3e
            if (r0 >= r1) goto L2f
        L1f:
            hs r0 = r2.b     // Catch: java.lang.Throwable -> L3e
            defpackage.hs.a(r0, r3)     // Catch: java.lang.Throwable -> L3e
            hs r0 = r2.b     // Catch: java.lang.Throwable -> L3e
            hj r1 = r2.a     // Catch: java.lang.Throwable -> L3e
            int r1 = r1.a()     // Catch: java.lang.Throwable -> L3e
            defpackage.hs.c(r0, r1)     // Catch: java.lang.Throwable -> L3e
        L2f:
            hs r0 = r2.b     // Catch: java.lang.Throwable -> L3e
            defpackage.hs.d(r0)     // Catch: java.lang.Throwable -> L3e
            hs r0 = r2.b
            java.util.concurrent.locks.Lock r0 = defpackage.hs.a(r0)
            r0.unlock()
            return
        L3e:
            r0 = move-exception
            hs r1 = r2.b
            java.util.concurrent.locks.Lock r1 = defpackage.hs.a(r1)
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hw.a(gw):void");
    }
}
