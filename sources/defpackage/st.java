package defpackage;

import com.google.android.gms.internal.gv;

/* loaded from: classes.dex */
class st implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ sr b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public st(sr srVar, String str) {
        this.b = srVar;
        this.a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        gv gvVar;
        gvVar = this.b.a;
        gvVar.loadUrl(this.a);
    }
}
