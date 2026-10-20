package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class wd implements Runnable {
    final /* synthetic */ wb a;
    final /* synthetic */ wc b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public wd(wc wcVar, wb wbVar) {
        this.b = wcVar;
        this.a = wbVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        int i;
        wl c;
        wl wlVar;
        obj = this.b.h;
        synchronized (obj) {
            i = this.b.k;
            if (i != -2) {
                return;
            }
            wc wcVar = this.b;
            c = this.b.c();
            wcVar.j = c;
            wlVar = this.b.j;
            if (wlVar == null) {
                this.b.a(4);
            } else {
                this.a.a(this.b);
                this.b.a(this.a);
            }
        }
    }
}
