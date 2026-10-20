package defpackage;

import android.os.Handler;
import android.os.Message;
import com.google.android.gms.analytics.t;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class fw implements Handler.Callback {
    final /* synthetic */ fu a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public fw(fu fuVar) {
        this.a = fuVar;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        Object obj;
        int i;
        boolean z;
        Handler handler;
        Handler handler2;
        Object obj2;
        int i2;
        if (1 == message.what) {
            obj = fu.a;
            if (obj.equals(message.obj)) {
                t.a().a(true);
                this.a.a();
                t.a().a(false);
                i = this.a.e;
                if (i > 0) {
                    z = this.a.n;
                    if (!z) {
                        handler = this.a.l;
                        handler2 = this.a.l;
                        obj2 = fu.a;
                        Message obtainMessage = handler2.obtainMessage(1, obj2);
                        i2 = this.a.e;
                        handler.sendMessageDelayed(obtainMessage, i2 * 1000);
                    }
                }
            }
        }
        return true;
    }
}
