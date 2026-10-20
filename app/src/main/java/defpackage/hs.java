package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class hs implements hm {
    final Handler b;
    private final jh f;
    private final int g;
    private final Looper h;
    private gw i;
    private int j;
    private volatile int l;
    private int n;
    private final List<String> r;
    private boolean s;
    private final Lock d = new ReentrantLock();
    private final Condition e = this.d.newCondition();
    final Queue<hz<?>> a = new LinkedList();
    private volatile int k = 4;
    private boolean m = false;
    private long o = 5000;
    private final Bundle p = new Bundle();
    private final Map<hk<?>, hi> q = new HashMap();
    private final Set<ia<?>> t = Collections.newSetFromMap(new WeakHashMap());
    final Set<hz<?>> c = Collections.newSetFromMap(new ConcurrentHashMap());
    private final hx u = new ht(this);
    private final ho v = new hu(this);
    private final jj w = new hv(this);

    public hs(Context context, Looper looper, ClientSettings clientSettings, Map<he<?>, hf> map, Set<ho> set, Set<hp> set2, int i) {
        this.f = new jh(context, looper, this.w);
        this.h = looper;
        this.b = new hy(this, looper);
        this.g = i;
        Iterator<ho> it = set.iterator();
        while (it.hasNext()) {
            this.f.a(it.next());
        }
        Iterator<hp> it2 = set2.iterator();
        while (it2.hasNext()) {
            this.f.a(it2.next());
        }
        for (he<?> heVar : map.keySet()) {
            hj<?, ?> a = heVar.a();
            this.q.put(heVar.c(), a(a, map.get(heVar), context, looper, clientSettings, this.v, new hw(this, a)));
        }
        this.r = Collections.unmodifiableList(clientSettings.b());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <C extends hi, O> C a(hj<C, O> hjVar, Object obj, Context context, Looper looper, ClientSettings clientSettings, ho hoVar, hp hpVar) {
        return hjVar.a(context, looper, clientSettings, obj, hoVar, hpVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.d.lock();
        try {
            if (this.k != 3) {
                if (i == -1) {
                    if (d()) {
                        Iterator<hz<?>> it = this.a.iterator();
                        while (it.hasNext()) {
                            hz<?> next = it.next();
                            if (next.c() != 1) {
                                next.a();
                                it.remove();
                            }
                        }
                    } else {
                        this.a.clear();
                    }
                    Iterator<hz<?>> it2 = this.c.iterator();
                    while (it2.hasNext()) {
                        it2.next().a();
                    }
                    this.c.clear();
                    Iterator<ia<?>> it3 = this.t.iterator();
                    while (it3.hasNext()) {
                        it3.next().a();
                    }
                    this.t.clear();
                    if (this.i == null && !this.a.isEmpty()) {
                        this.m = true;
                        return;
                    }
                }
                boolean d = d();
                boolean c = c();
                this.k = 3;
                if (d) {
                    if (i == -1) {
                        this.i = null;
                    }
                    this.e.signalAll();
                }
                this.s = false;
                for (hi hiVar : this.q.values()) {
                    if (hiVar.c()) {
                        hiVar.b();
                    }
                }
                this.s = true;
                this.k = 4;
                if (c) {
                    if (i != -1) {
                        this.f.a(i);
                    }
                    this.s = false;
                }
            }
        } finally {
            this.d.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <A extends hi> void a(hz<A> hzVar) {
        this.d.lock();
        try {
            kb.b(hzVar.b() != null, "This task can not be executed or enqueued (it's probably a Batch or malformed)");
            this.c.add(hzVar);
            hzVar.a(this.u);
            if (g()) {
                hzVar.a(new Status(8));
            } else {
                hzVar.a((hz<A>) a(hzVar.b()));
            }
        } finally {
            this.d.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.n--;
        if (this.n == 0) {
            if (this.i != null) {
                this.m = false;
                a(3);
                if (g()) {
                    this.b.sendMessageDelayed(this.b.obtainMessage(1), this.o);
                } else {
                    this.f.a(this.i);
                }
                this.s = false;
                return;
            }
            this.k = 2;
            h();
            this.e.signalAll();
            f();
            if (!this.m) {
                this.f.a(this.p.isEmpty() ? null : this.p);
            } else {
                this.m = false;
                a(-1);
            }
        }
    }

    private void f() {
        this.d.lock();
        try {
            kb.a(c() || g(), "GoogleApiClient is not connected yet.");
            while (!this.a.isEmpty()) {
                try {
                    a(this.a.remove());
                } catch (DeadObjectException e) {
                    Log.w("GoogleApiClientImpl", "Service died while flushing queue", e);
                }
            }
        } finally {
            this.d.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        return this.l != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int h(hs hsVar) {
        int i = hsVar.l;
        hsVar.l = i - 1;
        return i;
    }

    private void h() {
        this.d.lock();
        try {
            this.l = 0;
            this.b.removeMessages(1);
        } finally {
            this.d.unlock();
        }
    }

    @Override // defpackage.hm
    public <C extends hi> C a(hk<C> hkVar) {
        C c = (C) this.q.get(hkVar);
        kb.a(c, "Appropriate Api was not requested.");
        return c;
    }

    @Override // defpackage.hm
    public void a() {
        this.d.lock();
        try {
            this.m = false;
            if (c() || d()) {
                return;
            }
            this.s = true;
            this.i = null;
            this.k = 1;
            this.p.clear();
            this.n = this.q.size();
            Iterator<hi> it = this.q.values().iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        } finally {
            this.d.unlock();
        }
    }

    @Override // defpackage.hm
    public void a(ho hoVar) {
        this.f.a(hoVar);
    }

    @Override // defpackage.hm
    public void a(hp hpVar) {
        this.f.a(hpVar);
    }

    @Override // defpackage.hm
    public void b() {
        h();
        a(-1);
    }

    @Override // defpackage.hm
    public void b(ho hoVar) {
        this.f.b(hoVar);
    }

    @Override // defpackage.hm
    public void b(hp hpVar) {
        this.f.b(hpVar);
    }

    @Override // defpackage.hm
    public boolean c() {
        return this.k == 2;
    }

    @Override // defpackage.hm
    public boolean d() {
        return this.k == 1;
    }
}
