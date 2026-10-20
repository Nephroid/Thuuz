package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ji extends Handler {
    final /* synthetic */ jh a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ji(jh jhVar, Looper looper) {
        super(looper);
        this.a = jhVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        ArrayList arrayList;
        jj jjVar;
        jj jjVar2;
        ArrayList arrayList2;
        jj jjVar3;
        if (message.what != 1) {
            Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
            return;
        }
        arrayList = this.a.c;
        synchronized (arrayList) {
            jjVar = this.a.b;
            if (jjVar.b_()) {
                jjVar2 = this.a.b;
                if (jjVar2.c()) {
                    arrayList2 = this.a.c;
                    if (arrayList2.contains(message.obj)) {
                        jjVar3 = this.a.b;
                        ((ho) message.obj).a(jjVar3.a_());
                    }
                }
            }
        }
    }
}
