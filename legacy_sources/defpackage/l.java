package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class l<TResult> {
    private boolean e;
    private boolean f;
    private TResult g;
    private Exception h;
    public static final ExecutorService a = h.a();
    private static final Executor c = h.b();
    public static final Executor b = b.b();
    private final Object d = new Object();
    private List<k<TResult, Void>> i = new ArrayList();

    private l() {
    }

    /* JADX WARN: Incorrect inner types in method signature: <TResult:Ljava/lang/Object;>()Ll<TTResult;>.t; */
    public static t a() {
        l lVar = new l();
        lVar.getClass();
        return new t(lVar, null);
    }

    public boolean b() {
        boolean z;
        synchronized (this.d) {
            z = this.e;
        }
        return z;
    }

    public boolean c() {
        boolean z;
        synchronized (this.d) {
            z = this.f;
        }
        return z;
    }

    public boolean d() {
        boolean z;
        synchronized (this.d) {
            z = this.h != null;
        }
        return z;
    }

    public TResult e() {
        TResult tresult;
        synchronized (this.d) {
            tresult = this.g;
        }
        return tresult;
    }

    public Exception f() {
        Exception exc;
        synchronized (this.d) {
            exc = this.h;
        }
        return exc;
    }

    public static <TResult> l<TResult> a(TResult tresult) {
        t a2 = a();
        a2.b((t) tresult);
        return a2.a();
    }

    public static <TResult> l<TResult> a(Exception exc) {
        t a2 = a();
        a2.b(exc);
        return a2.a();
    }

    public static <TResult> l<TResult> g() {
        t a2 = a();
        a2.c();
        return a2.a();
    }

    public <TContinuationResult> l<TContinuationResult> a(k<TResult, TContinuationResult> kVar, Executor executor) {
        boolean b2;
        t a2 = a();
        synchronized (this.d) {
            b2 = b();
            if (!b2) {
                this.i.add(new p(this, a2, kVar, executor));
            }
        }
        if (b2) {
            c(a2, kVar, this, executor);
        }
        return a2.a();
    }

    public <TContinuationResult> l<TContinuationResult> a(k<TResult, TContinuationResult> kVar) {
        return a(kVar, c);
    }

    public <TContinuationResult> l<TContinuationResult> b(k<TResult, l<TContinuationResult>> kVar, Executor executor) {
        boolean b2;
        t a2 = a();
        synchronized (this.d) {
            b2 = b();
            if (!b2) {
                this.i.add(new q(this, a2, kVar, executor));
            }
        }
        if (b2) {
            d(a2, kVar, this, executor);
        }
        return a2.a();
    }

    public <TContinuationResult> l<TContinuationResult> c(k<TResult, TContinuationResult> kVar, Executor executor) {
        return b(new r(this, kVar), executor);
    }

    public <TContinuationResult> l<TContinuationResult> b(k<TResult, TContinuationResult> kVar) {
        return c(kVar, c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Incorrect inner types in method signature: <TContinuationResult:Ljava/lang/Object;TResult:Ljava/lang/Object;>(Ll<TTContinuationResult;>.t;Lk<TTResult;TTContinuationResult;>;Ll<TTResult;>;Ljava/util/concurrent/Executor;)V */
    public static void c(t tVar, k kVar, l lVar, Executor executor) {
        executor.execute(new s(kVar, lVar, tVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Incorrect inner types in method signature: <TContinuationResult:Ljava/lang/Object;TResult:Ljava/lang/Object;>(Ll<TTContinuationResult;>.t;Lk<TTResult;Ll<TTContinuationResult;>;>;Ll<TTResult;>;Ljava/util/concurrent/Executor;)V */
    public static void d(t tVar, k kVar, l lVar, Executor executor) {
        executor.execute(new n(kVar, lVar, tVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        synchronized (this.d) {
            Iterator<k<TResult, Void>> it = this.i.iterator();
            while (it.hasNext()) {
                try {
                    it.next().then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.i = null;
        }
    }
}
