package defpackage;

import com.google.android.gms.analytics.r;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class ga extends TimerTask {
    final /* synthetic */ r a;

    private ga(r rVar) {
        this.a = rVar;
    }

    public /* synthetic */ ga(r rVar, fx fxVar) {
        this(rVar);
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        r.a aVar;
        aVar = this.a.b;
        if (aVar == r.a.CONNECTING) {
            this.a.j();
        }
    }
}
