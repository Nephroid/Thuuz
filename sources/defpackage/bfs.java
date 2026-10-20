package defpackage;

/* loaded from: classes.dex */
class bfs implements Runnable {
    final /* synthetic */ bfr a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bfs(bfr bfrVar) {
        this.a = bfrVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.a.c();
    }
}
