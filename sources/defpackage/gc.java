package defpackage;

import com.google.android.gms.analytics.r;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class gc extends TimerTask {
    final /* synthetic */ r a;

    private gc(r rVar) {
        this.a = rVar;
    }

    public /* synthetic */ gc(r rVar, fx fxVar) {
        this(rVar);
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.a.k();
    }
}
