package defpackage;

import com.google.android.gms.internal.fb;

/* loaded from: classes.dex */
public class aaq implements Runnable {
    final /* synthetic */ fb a;

    public aaq(fb fbVar) {
        this.a = fbVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.b();
    }
}
