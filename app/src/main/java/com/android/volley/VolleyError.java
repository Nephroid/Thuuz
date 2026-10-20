package com.android.volley;

import defpackage.ae;

/* loaded from: classes.dex */
public class VolleyError extends Exception {
    public final ae networkResponse;
    private long networkTimeMs;

    public VolleyError() {
        this.networkResponse = null;
    }

    public VolleyError(ae aeVar) {
        this.networkResponse = aeVar;
    }

    public VolleyError(Throwable th) {
        super(th);
        this.networkResponse = null;
    }

    public void a(long j) {
        this.networkTimeMs = j;
    }
}
