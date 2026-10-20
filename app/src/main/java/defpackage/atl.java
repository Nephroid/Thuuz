package defpackage;

/* loaded from: classes.dex */
class atl implements Runnable {
    final /* synthetic */ atj a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public atl(atj atjVar) {
        this.a = atjVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        asg asgVar;
        asgVar = this.a.b;
        asgVar.a();
    }
}
