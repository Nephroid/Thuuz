package defpackage;

import android.content.Context;
import android.os.Handler;
import com.google.android.gms.analytics.t;

/* loaded from: classes.dex */
public class fu extends es {
    private static final Object a = new Object();
    private static fu o;
    private Context b;
    private fc c;
    private volatile fh d;
    private boolean g;
    private String h;
    private Handler l;
    private ft m;
    private int e = 1800;
    private boolean f = true;
    private boolean i = true;
    private boolean j = true;
    private fd k = new fv(this);
    private boolean n = false;

    private fu() {
    }

    public static fu c() {
        if (o == null) {
            o = new fu();
        }
        return o;
    }

    private void g() {
        this.m = new ft(this);
        this.m.a(this.b);
    }

    private void h() {
        this.l = new Handler(this.b.getMainLooper(), new fw(this));
        if (this.e > 0) {
            this.l.sendMessageDelayed(this.l.obtainMessage(1, a), this.e * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // defpackage.es
    public synchronized void a() {
        if (this.d == null) {
            gn.c("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.f = true;
        } else {
            t.a().a(t.a.DISPATCH);
            this.d.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // defpackage.es
    public synchronized void a(int i) {
        if (this.l == null) {
            gn.c("Dispatch period set with null handler. Dispatch will run once initialization is complete.");
            this.e = i;
        } else {
            t.a().a(t.a.SET_DISPATCH_PERIOD);
            if (!this.n && this.i && this.e > 0) {
                this.l.removeMessages(1, a);
            }
            this.e = i;
            if (i > 0 && !this.n && this.i) {
                this.l.sendMessageDelayed(this.l.obtainMessage(1, a), i * 1000);
            }
        }
    }

    public synchronized void a(Context context, fh fhVar) {
        if (this.b == null) {
            this.b = context.getApplicationContext();
            if (this.d == null) {
                this.d = fhVar;
                if (this.f) {
                    a();
                    this.f = false;
                }
                if (this.g) {
                    e();
                    this.g = false;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // defpackage.es
    public synchronized void a(boolean z) {
        a(this.n, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a(boolean z, boolean z2) {
        if (this.n != z || this.i != z2) {
            if ((z || !z2) && this.e > 0) {
                this.l.removeMessages(1, a);
            }
            if (!z && z2 && this.e > 0) {
                this.l.sendMessageDelayed(this.l.obtainMessage(1, a), this.e * 1000);
            }
            gn.c("PowerSaveMode " + ((z || !z2) ? "initiated." : "terminated."));
            this.n = z;
            this.i = z2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // defpackage.es
    public synchronized void b() {
        if (!this.n && this.i && this.e > 0) {
            this.l.removeMessages(1, a);
            this.l.sendMessage(this.l.obtainMessage(1, a));
        }
    }

    public synchronized fc d() {
        if (this.c == null) {
            if (this.b == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.c = new eo(this.k, this.b);
            if (this.h != null) {
                this.c.d().a(this.h);
                this.h = null;
            }
        }
        if (this.l == null) {
            h();
        }
        if (this.m == null && this.j) {
            g();
        }
        return this.c;
    }

    void e() {
        if (this.d == null) {
            gn.c("setForceLocalDispatch() queued. It will be called once initialization is complete.");
            this.g = true;
        } else {
            t.a().a(t.a.SET_FORCE_LOCAL_DISPATCH);
            this.d.b();
        }
    }
}
