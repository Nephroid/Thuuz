package defpackage;

import android.os.Handler;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class z implements ak {
    private final Executor a;

    public z(Handler handler) {
        this.a = new aa(this, handler);
    }

    @Override // defpackage.ak
    public void a(Request<?> request, ah<?> ahVar) {
        a(request, ahVar, null);
    }

    @Override // defpackage.ak
    public void a(Request<?> request, ah<?> ahVar, Runnable runnable) {
        request.u();
        request.a("post-response");
        this.a.execute(new ab(this, request, ahVar, runnable));
    }

    @Override // defpackage.ak
    public void a(Request<?> request, VolleyError volleyError) {
        request.a("post-error");
        this.a.execute(new ab(this, request, ah.a(volleyError), null));
    }
}
