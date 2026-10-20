package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class iz<T extends IInterface> implements hi, jj {
    public static final String[] c = {"service_esmobile", "service_googleme"};
    final Handler a;
    boolean b;
    private final Context d;
    private final Looper e;
    private T f;
    private final ArrayList<iz<T>.jb<?>> g;

    /* JADX WARN: Incorrect inner types in field signature: Liz<TT;>.je; */
    private je h;
    private volatile int i;
    private final String[] j;
    private final jh k;

    /* JADX INFO: Access modifiers changed from: protected */
    public iz(Context context, Looper looper, ho hoVar, hp hpVar, String... strArr) {
        this.g = new ArrayList<>();
        this.i = 1;
        this.b = false;
        this.d = (Context) kb.a(context);
        this.e = (Looper) kb.a(looper, "Looper must not be null");
        this.k = new jh(context, looper, this);
        this.a = new ja(this, looper);
        a(strArr);
        this.j = strArr;
        a((ho) kb.a(hoVar));
        a((hp) kb.a(hpVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public iz(Context context, gz gzVar, ha haVar, String... strArr) {
        this(context, context.getMainLooper(), new jc(gzVar), new jf(haVar), strArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        int i2 = this.i;
        this.i = i;
        if (i2 != i) {
            if (i == 3) {
                f();
            } else if (i2 == 3 && i == 1) {
                g();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract T a(IBinder iBinder);

    @Override // defpackage.hi
    public void a() {
        this.b = true;
        a(2);
        int a = hb.a(this.d);
        if (a != 0) {
            a(1);
            this.a.sendMessage(this.a.obtainMessage(3, Integer.valueOf(a)));
            return;
        }
        if (this.h != null) {
            Log.e("GmsClient", "Calling connect() while still connected, missing disconnect().");
            this.f = null;
            jk.a(this.d).b(d(), this.h);
        }
        this.h = new je(this);
        if (jk.a(this.d).a(d(), this.h)) {
            return;
        }
        Log.e("GmsClient", "unable to connect to service: " + d());
        this.a.sendMessage(this.a.obtainMessage(3, 9));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i, IBinder iBinder, Bundle bundle) {
        this.a.sendMessage(this.a.obtainMessage(1, new jg(this, i, iBinder, bundle)));
    }

    public void a(ho hoVar) {
        this.k.a(hoVar);
    }

    public void a(hp hpVar) {
        this.k.a(hpVar);
    }

    protected abstract void a(js jsVar, jd jdVar);

    protected void a(String... strArr) {
    }

    @Override // defpackage.jj
    public Bundle a_() {
        return null;
    }

    @Override // defpackage.hi
    public void b() {
        this.b = false;
        synchronized (this.g) {
            int size = this.g.size();
            for (int i = 0; i < size; i++) {
                this.g.get(i).d();
            }
            this.g.clear();
        }
        a(1);
        this.f = null;
        if (this.h != null) {
            jk.a(this.d).b(d(), this.h);
            this.h = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(IBinder iBinder) {
        try {
            a(jt.a(iBinder), new jd(this));
        } catch (RemoteException e) {
            Log.w("GmsClient", "service died");
        }
    }

    @Override // defpackage.jj
    public boolean b_() {
        return this.b;
    }

    @Override // defpackage.hi, defpackage.jj
    public boolean c() {
        return this.i == 3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract String d();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract String e();

    protected void f() {
    }

    protected void g() {
    }

    public boolean h() {
        return this.i == 2;
    }

    public final Context i() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void j() {
        if (!c()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T k() {
        j();
        return this.f;
    }
}
