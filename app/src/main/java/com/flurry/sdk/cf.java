package com.flurry.sdk;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public abstract class cf implements fd {
    protected static String d;
    protected ExecutorService f;
    protected ExecutorService g;
    ci h;
    Set<String> e = new HashSet();
    protected String i = "defaultDataKey_";

    /* loaded from: classes.dex */
    public interface a {
        void a();
    }

    protected abstract void a(byte[] bArr, String str, String str2);

    public cf(String str, String str2) {
        d = str2;
        fe.a().a(this);
        this.f = Executors.newSingleThreadExecutor(new ff("FlurryAgent", 1));
        this.g = Executors.newCachedThreadPool(new ff("FlurryAgent", 1));
        a(str);
    }

    protected void a(final String str) {
        this.f.submit(new Runnable() { // from class: com.flurry.sdk.cf.1
            @Override // java.lang.Runnable
            public void run() {
                cf.this.f();
                try {
                    cf.this.h = new ci(str);
                } catch (Exception e) {
                    ex.a(6, cf.d, "initialization of FlurryDataSenderIndex error", e);
                }
            }
        });
    }

    @Override // com.flurry.sdk.fd
    public void b(boolean z) {
        ex.a(4, d, "onNetworkStateChanged : isNetworkEnable = " + z);
        if (z) {
            d();
        }
    }

    public void b(byte[] bArr, String str, String str2) {
        a(bArr, str, str2, null);
    }

    protected void a(byte[] bArr, String str, String str2, a aVar) {
        if (bArr == null || bArr.length == 0) {
            ex.a(6, d, "Report that has to be sent is EMPTY or NULL");
        } else {
            c(bArr, str, str2);
            a(aVar);
        }
    }

    protected int c() {
        return this.e.size();
    }

    protected void c(final byte[] bArr, final String str, final String str2) {
        this.f.submit(new Runnable() { // from class: com.flurry.sdk.cf.2
            @Override // java.lang.Runnable
            public void run() {
                cf.this.f();
                try {
                    cf.this.d(bArr, str, str2);
                } catch (Exception e) {
                    ex.a(6, cf.d, "storeData error", e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d() {
        a((a) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(final a aVar) {
        this.f.submit(new Runnable() { // from class: com.flurry.sdk.cf.3
            @Override // java.lang.Runnable
            public void run() {
                cf.this.f();
                try {
                    cf.this.g();
                    if (aVar != null) {
                        aVar.a();
                    }
                } catch (Exception e) {
                    ex.a(6, cf.d, "retransmitNotSentBlocks error", e);
                }
            }
        });
    }

    protected boolean e() {
        return c() <= 8;
    }

    protected void f() {
        Thread.currentThread().setName("DataSender Main Single Thread , id = " + Thread.currentThread().getId());
    }

    protected String a(String str, String str2) {
        return this.i + str + "_" + str2;
    }

    protected String d(byte[] bArr, String str, String str2) {
        String a2 = a(str, str2);
        cg cgVar = new cg();
        cgVar.a(bArr);
        String a3 = cgVar.a();
        this.h.a(cgVar, a2);
        return a3;
    }

    protected void g() {
        if (!fe.a().c()) {
            ex.a(5, d, "Reports were not sent! No Internet connection!");
            return;
        }
        List<String> a2 = this.h.a();
        if (a2 == null || a2.isEmpty()) {
            ex.a(4, d, "No more reports to send.");
            return;
        }
        for (String str : a2) {
            if (e()) {
                List<String> c = this.h.c(str);
                ex.a(4, d, "Number of not sent blocks = " + c.size());
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 < c.size()) {
                        String str2 = c.get(i2);
                        if (!this.e.contains(str2)) {
                            if (e()) {
                                byte[] b = new cg(str2).b();
                                if (b == null || b.length == 0) {
                                    ex.a(6, d, "Internal ERROR! Report is empty!");
                                    this.h.a(str2, str);
                                } else {
                                    this.e.add(str2);
                                    a(b, str2, str);
                                }
                            }
                        }
                        i = i2 + 1;
                    }
                }
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(final String str, final String str2, int i) {
        this.f.submit(new Runnable() { // from class: com.flurry.sdk.cf.4
            @Override // java.lang.Runnable
            public void run() {
                cf.this.f();
                if (!cf.this.h.a(str, str2)) {
                    ex.a(6, cf.d, "Internal error. Block wasn't deleted with id = " + str);
                }
                if (!cf.this.e.remove(str)) {
                    ex.a(6, cf.d, "Internal error. Block with id = " + str + " was not in progress state");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(final String str, String str2) {
        this.f.submit(new Runnable() { // from class: com.flurry.sdk.cf.5
            @Override // java.lang.Runnable
            public void run() {
                cf.this.f();
                if (!cf.this.e.remove(str)) {
                    ex.a(6, cf.d, "Internal error. Block with id = " + str + " was not in progress state");
                }
            }
        });
    }
}
