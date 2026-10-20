package defpackage;

import com.thuuz.gtv.util.loader.StatefulLoader;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class cbh implements Callable<Void> {
    final /* synthetic */ StatefulLoader a;

    private cbh(StatefulLoader statefulLoader) {
        this.a = statefulLoader;
    }

    @Override // java.util.concurrent.Callable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void call() {
        cbf cbfVar;
        try {
            cbfVar = this.a.a();
        } catch (Throwable th) {
            cbfVar = new cbf(th);
        }
        this.a.a = StatefulLoader.State.Finished;
        StatefulLoader.a(this.a).post(new cbi(this, cbfVar));
        return null;
    }
}
