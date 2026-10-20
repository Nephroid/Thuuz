package com.android.volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import defpackage.ae;
import defpackage.af;
import defpackage.ag;
import defpackage.ah;
import defpackage.ai;
import defpackage.al;
import defpackage.am;
import defpackage.an;
import defpackage.v;
import defpackage.y;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public abstract class Request<T> implements Comparable<Request<T>> {
    private final an a;
    private final int b;
    private final String c;
    private final int d;
    private final ai e;
    private Integer f;
    private ag g;
    private boolean h;
    private boolean i;
    private boolean j;
    private long k;
    private al l;
    private v m;

    /* loaded from: classes.dex */
    public enum Priority {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    public abstract ah<T> a(ae aeVar);

    public abstract void a(T t);

    public Request(int i, String str, ai aiVar) {
        this.a = an.a ? new an() : null;
        this.h = true;
        this.i = false;
        this.j = false;
        this.k = 0L;
        this.m = null;
        this.b = i;
        this.c = str;
        this.e = aiVar;
        a((al) new y());
        this.d = c(str);
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.d;
    }

    private static int c(String str) {
        Uri parse;
        String host;
        if (TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null || (host = parse.getHost()) == null) {
            return 0;
        }
        return host.hashCode();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> a(al alVar) {
        this.l = alVar;
        return this;
    }

    public void a(String str) {
        if (an.a) {
            this.a.a(str, Thread.currentThread().getId());
        } else if (this.k == 0) {
            this.k = SystemClock.elapsedRealtime();
        }
    }

    public void b(String str) {
        if (this.g != null) {
            this.g.b(this);
        }
        if (an.a) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new af(this, str, id));
                return;
            } else {
                this.a.a(str, id);
                this.a.a(toString());
                return;
            }
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.k;
        if (elapsedRealtime >= 3000) {
            am.b("%d ms: %s", Long.valueOf(elapsedRealtime), toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> a(ag agVar) {
        this.g = agVar;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Request<?> a(int i) {
        this.f = Integer.valueOf(i);
        return this;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> a(v vVar) {
        this.m = vVar;
        return this;
    }

    public v e() {
        return this.m;
    }

    public void f() {
        this.i = true;
    }

    public boolean g() {
        return this.i;
    }

    public Map<String, String> h() {
        return Collections.emptyMap();
    }

    @Deprecated
    protected Map<String, String> i() {
        return m();
    }

    @Deprecated
    protected String j() {
        return n();
    }

    @Deprecated
    public String k() {
        return o();
    }

    @Deprecated
    public byte[] l() {
        Map<String, String> i = i();
        if (i == null || i.size() <= 0) {
            return null;
        }
        return a(i, j());
    }

    public Map<String, String> m() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String n() {
        return HTTP.UTF_8;
    }

    public String o() {
        return "application/x-www-form-urlencoded; charset=" + n();
    }

    public byte[] p() {
        Map<String, String> m = m();
        if (m == null || m.size() <= 0) {
            return null;
        }
        return a(m, n());
    }

    private byte[] a(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append(URLEncoder.encode(entry.getKey(), str));
                sb.append('=');
                sb.append(URLEncoder.encode(entry.getValue(), str));
                sb.append('&');
            }
            return sb.toString().getBytes(str);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }

    public final boolean q() {
        return this.h;
    }

    public Priority r() {
        return Priority.NORMAL;
    }

    public final int s() {
        return this.l.a();
    }

    public al t() {
        return this.l;
    }

    public void u() {
        this.j = true;
    }

    public boolean v() {
        return this.j;
    }

    public VolleyError a(VolleyError volleyError) {
        return volleyError;
    }

    public void b(VolleyError volleyError) {
        if (this.e != null) {
            this.e.a(volleyError);
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(Request<T> request) {
        Priority r = r();
        Priority r2 = request.r();
        return r == r2 ? this.f.intValue() - request.f.intValue() : r2.ordinal() - r.ordinal();
    }

    public String toString() {
        return (this.i ? "[X] " : "[ ] ") + c() + " " + ("0x" + Integer.toHexString(b())) + " " + r() + " " + this.f;
    }
}
