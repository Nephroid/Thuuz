package defpackage;

import android.util.Log;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class jb<TListener> {
    final /* synthetic */ iz a;
    private TListener b;
    private boolean c = false;

    public jb(iz izVar, TListener tlistener) {
        this.a = izVar;
        this.b = tlistener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void a();

    protected abstract void a(TListener tlistener);

    public void b() {
        TListener tlistener;
        synchronized (this) {
            tlistener = this.b;
            if (this.c) {
                Log.w("GmsClient", "Callback proxy " + this + " being reused. This is not safe.");
            }
        }
        if (tlistener != null) {
            try {
                a(tlistener);
            } catch (RuntimeException e) {
                a();
                throw e;
            }
        } else {
            a();
        }
        synchronized (this) {
            this.c = true;
        }
        c();
    }

    public void c() {
        ArrayList arrayList;
        ArrayList arrayList2;
        d();
        arrayList = this.a.g;
        synchronized (arrayList) {
            arrayList2 = this.a.g;
            arrayList2.remove(this);
        }
    }

    public void d() {
        synchronized (this) {
            this.b = null;
        }
    }
}
