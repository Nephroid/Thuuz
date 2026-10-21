package defpackage;

import com.google.android.gms.internal.dk;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class adr implements Runnable {
    final /* synthetic */ dk a;
    final /* synthetic */ adq b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public adr(adq adqVar, dk dkVar) {
        this.b = adqVar;
        this.a = dkVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.k();
    }
}
