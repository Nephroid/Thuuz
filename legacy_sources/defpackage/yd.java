package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class yd implements Runnable {
    final /* synthetic */ yh a;
    final /* synthetic */ Intent b;
    final /* synthetic */ yc c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public yd(yc ycVar, yh yhVar, Intent intent) {
        this.c = ycVar;
        this.a = yhVar;
        this.b = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        ym ymVar;
        zd zdVar;
        Context context;
        zd zdVar2;
        Context context2;
        try {
            ymVar = this.c.h;
            if (ymVar.a(this.a.b, -1, this.b)) {
                zdVar2 = this.c.d;
                context2 = this.c.c;
                zdVar2.a(new yi(context2, this.a.c, true, -1, this.b, this.a));
            } else {
                zdVar = this.c.d;
                context = this.c.c;
                zdVar.a(new yi(context, this.a.c, false, -1, this.b, this.a));
            }
        } catch (RemoteException e) {
            adn.e("Fail to verify and dispatch pending transaction");
        }
    }
}
