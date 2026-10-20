package defpackage;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class acy implements Runnable {
    final /* synthetic */ Context a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public acy(Context context) {
        this.a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        String f;
        Object obj2;
        obj = acx.a;
        synchronized (obj) {
            f = acx.f(this.a);
            String unused = acx.d = f;
            obj2 = acx.a;
            obj2.notifyAll();
        }
    }
}
