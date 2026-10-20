package com.flurry.sdk;

import android.content.Context;
import java.io.File;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class by {
    private static final String b = by.class.getSimpleName();
    boolean a;
    private final bz c;
    private final File d;
    private String e;

    public by() {
        this(eg.a().b());
    }

    public by(Context context) {
        this.c = new bz();
        this.d = context.getFileStreamPath(".flurryinstallreceiver.");
        ex.a(3, b, "Referrer file name if it exists:  " + this.d);
    }

    public synchronized void a() {
        this.d.delete();
        this.e = null;
        this.a = true;
    }

    public synchronized Map<String, List<String>> a(boolean z) {
        Map<String, List<String>> a;
        b();
        a = this.c.a(this.e);
        if (z) {
            a();
        }
        return a;
    }

    public synchronized void a(String str) {
        this.a = true;
        b(str);
        c();
    }

    private void b(String str) {
        if (str != null) {
            this.e = str;
        }
    }

    private void b() {
        if (!this.a) {
            this.a = true;
            ex.a(4, b, "Loading referrer info from file: " + this.d.getAbsolutePath());
            String b2 = et.b(this.d);
            ex.a(b, "Referrer file contents: " + b2);
            b(b2);
        }
    }

    private void c() {
        et.a(this.d, this.e);
    }
}
