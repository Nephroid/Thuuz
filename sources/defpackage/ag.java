package defpackage;

import android.os.Handler;
import android.os.Looper;
import com.android.volley.Request;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class ag {
    private AtomicInteger a;
    private final Map<String, Queue<Request<?>>> b;
    private final Set<Request<?>> c;
    private final PriorityBlockingQueue<Request<?>> d;
    private final PriorityBlockingQueue<Request<?>> e;
    private final u f;
    private final ac g;
    private final ak h;
    private ad[] i;
    private w j;

    public ag(u uVar, ac acVar, int i, ak akVar) {
        this.a = new AtomicInteger();
        this.b = new HashMap();
        this.c = new HashSet();
        this.d = new PriorityBlockingQueue<>();
        this.e = new PriorityBlockingQueue<>();
        this.f = uVar;
        this.g = acVar;
        this.i = new ad[i];
        this.h = akVar;
    }

    public ag(u uVar, ac acVar, int i) {
        this(uVar, acVar, i, new z(new Handler(Looper.getMainLooper())));
    }

    public ag(u uVar, ac acVar) {
        this(uVar, acVar, 4);
    }

    public void a() {
        b();
        this.j = new w(this.d, this.e, this.f, this.h);
        this.j.start();
        for (int i = 0; i < this.i.length; i++) {
            ad adVar = new ad(this.e, this.g, this.f, this.h);
            this.i[i] = adVar;
            adVar.start();
        }
    }

    public void b() {
        if (this.j != null) {
            this.j.a();
        }
        for (int i = 0; i < this.i.length; i++) {
            if (this.i[i] != null) {
                this.i[i].a();
            }
        }
    }

    public int c() {
        return this.a.incrementAndGet();
    }

    public <T> Request<T> a(Request<T> request) {
        request.a(this);
        synchronized (this.c) {
            this.c.add(request);
        }
        request.a(c());
        request.a("add-to-queue");
        if (!request.q()) {
            this.e.add(request);
        } else {
            synchronized (this.b) {
                String d = request.d();
                if (this.b.containsKey(d)) {
                    Queue<Request<?>> queue = this.b.get(d);
                    if (queue == null) {
                        queue = new LinkedList<>();
                    }
                    queue.add(request);
                    this.b.put(d, queue);
                    if (am.b) {
                        am.a("Request for cacheKey=%s is in flight, putting on hold.", d);
                    }
                } else {
                    this.b.put(d, null);
                    this.d.add(request);
                }
            }
        }
        return request;
    }

    public void b(Request<?> request) {
        synchronized (this.c) {
            this.c.remove(request);
        }
        if (request.q()) {
            synchronized (this.b) {
                String d = request.d();
                Queue<Request<?>> remove = this.b.remove(d);
                if (remove != null) {
                    if (am.b) {
                        am.a("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(remove.size()), d);
                    }
                    this.d.addAll(remove);
                }
            }
        }
    }
}
