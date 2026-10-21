package com.thuuz.gtv.util.loader;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.Loader;
import android.util.Log;
import defpackage.bzy;
import defpackage.cbf;
import defpackage.cbh;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public abstract class StatefulLoader<D> extends Loader<cbf<D, Throwable>> {
    private static final String b = StatefulLoader.class.getSimpleName();
    private static volatile ExecutorService c;
    public volatile State a;
    private final Handler d;
    private final ExecutorService e;
    private cbf<D, Throwable> f;
    private Future<Void> g;

    /* loaded from: classes.dex */
    public enum State {
        Init,
        Loading,
        Finished
    }

    public abstract cbf<D, Throwable> a();

    public StatefulLoader(Context context) {
        this(context, null);
    }

    public StatefulLoader(Context context, ExecutorService executorService) {
        super(context);
        this.d = new Handler();
        this.a = State.Init;
        if (executorService == null) {
            synchronized (StatefulLoader.class) {
                if (c == null) {
                    c = Executors.newFixedThreadPool(1);
                }
            }
            this.e = c;
            return;
        }
        this.e = executorService;
    }

    @Override // android.support.v4.content.Loader
    public void onStartLoading() {
        this.a = State.Loading;
        super.onStartLoading();
        if (this.f != null) {
            deliverResult(this.f);
        } else if (takeContentChanged() || this.g == null) {
            forceLoad();
        }
    }

    @Override // android.support.v4.content.Loader
    public void onForceLoad() {
        super.onForceLoad();
        try {
            if (this.g != null) {
                this.g.cancel(true);
            }
            this.g = this.e.submit(new cbh(this));
        } catch (RejectedExecutionException e) {
            deliverResult(new cbf(e));
            Log.e(b, "", e);
        }
    }

    @Override // android.support.v4.content.Loader
    public void onStopLoading() {
        super.onStopLoading();
    }

    @Override // android.support.v4.content.Loader
    public void onAbandon() {
        super.onAbandon();
    }

    @Override // android.support.v4.content.Loader
    public void onReset() {
        super.onReset();
        if (this.g != null) {
            this.g.cancel(true);
        }
        this.f = null;
        this.g = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(cbf<D, Throwable> cbfVar) {
        this.g = null;
        if (cbfVar == 0) {
            throw new NullPointerException("Loader returned null Result");
        }
        this.f = cbfVar;
        if (!isAbandoned() && isStarted()) {
            deliverResult(cbfVar);
        }
    }

    public State d() {
        return this.a;
    }

    public Logger e() {
        return bzy.getStaticLogger(getClass());
    }
}
