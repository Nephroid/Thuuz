package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ja extends Handler {
    final /* synthetic */ iz a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ja(iz izVar, Looper looper) {
        super(looper);
        this.a = izVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        jh jhVar;
        jh jhVar2;
        if (message.what == 1 && !this.a.h()) {
            jb jbVar = (jb) message.obj;
            jbVar.a();
            jbVar.c();
            return;
        }
        if (message.what == 3) {
            jhVar2 = this.a.k;
            jhVar2.a(new gw(((Integer) message.obj).intValue(), null));
            return;
        }
        if (message.what == 4) {
            this.a.a(1);
            this.a.f = null;
            jhVar = this.a.k;
            jhVar.a(((Integer) message.obj).intValue());
            return;
        }
        if (message.what == 2 && !this.a.c()) {
            jb jbVar2 = (jb) message.obj;
            jbVar2.a();
            jbVar2.c();
        } else if (message.what == 2 || message.what == 1) {
            ((jb) message.obj).b();
        } else {
            Log.wtf("GmsClient", "Don't know how to handle this message.");
        }
    }
}
