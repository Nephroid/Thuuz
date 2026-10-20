package defpackage;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class arp implements art {
    final /* synthetic */ arn a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public arp(arn arnVar) {
        this.a = arnVar;
    }

    @Override // defpackage.art
    public void a(List<arq> list) {
        CountDownLatch countDownLatch;
        for (arq arqVar : list) {
            this.a.b(this.a.a(arqVar.a, arqVar.b));
        }
        countDownLatch = this.a.i;
        countDownLatch.countDown();
    }
}
