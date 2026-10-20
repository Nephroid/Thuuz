package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class jh {
    private final jj b;
    private final Handler f;
    private final ArrayList<ho> c = new ArrayList<>();
    final ArrayList<ho> a = new ArrayList<>();
    private boolean d = false;
    private final ArrayList<ha> e = new ArrayList<>();

    public jh(Context context, Looper looper, jj jjVar) {
        this.b = jjVar;
        this.f = new ji(this, looper);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a() {
        synchronized (this.c) {
            a(this.b.a_());
        }
    }

    public void a(int i) {
        this.f.removeMessages(1);
        synchronized (this.c) {
            this.d = true;
            Iterator it = new ArrayList(this.c).iterator();
            while (it.hasNext()) {
                ho hoVar = (ho) it.next();
                if (!this.b.b_()) {
                    break;
                } else if (this.c.contains(hoVar)) {
                    hoVar.a(i);
                }
            }
            this.d = false;
        }
    }

    public void a(Bundle bundle) {
        synchronized (this.c) {
            kb.a(!this.d);
            this.f.removeMessages(1);
            this.d = true;
            kb.a(this.a.size() == 0);
            Iterator it = new ArrayList(this.c).iterator();
            while (it.hasNext()) {
                ho hoVar = (ho) it.next();
                if (!this.b.b_() || !this.b.c()) {
                    break;
                } else if (!this.a.contains(hoVar)) {
                    hoVar.a(bundle);
                }
            }
            this.a.clear();
            this.d = false;
        }
    }

    public void a(gw gwVar) {
        this.f.removeMessages(1);
        synchronized (this.e) {
            Iterator it = new ArrayList(this.e).iterator();
            while (it.hasNext()) {
                ha haVar = (ha) it.next();
                if (!this.b.b_()) {
                    return;
                }
                if (this.e.contains(haVar)) {
                    haVar.a(gwVar);
                }
            }
        }
    }

    public void a(ha haVar) {
        kb.a(haVar);
        synchronized (this.e) {
            if (this.e.contains(haVar)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + haVar + " is already registered");
            } else {
                this.e.add(haVar);
            }
        }
    }

    public void a(ho hoVar) {
        kb.a(hoVar);
        synchronized (this.c) {
            if (this.c.contains(hoVar)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + hoVar + " is already registered");
            } else {
                this.c.add(hoVar);
            }
        }
        if (this.b.c()) {
            this.f.sendMessage(this.f.obtainMessage(1, hoVar));
        }
    }

    public void b(ha haVar) {
        kb.a(haVar);
        synchronized (this.e) {
            if (this.e != null && !this.e.remove(haVar)) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + haVar + " not found");
            }
        }
    }

    public void b(ho hoVar) {
        kb.a(hoVar);
        synchronized (this.c) {
            if (this.c != null) {
                if (!this.c.remove(hoVar)) {
                    Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + hoVar + " not found");
                } else if (this.d) {
                    this.a.add(hoVar);
                }
            }
        }
    }
}
