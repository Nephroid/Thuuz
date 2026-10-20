package defpackage;

import android.graphics.drawable.Drawable;

@zy
/* loaded from: classes.dex */
public class ul extends uq implements uo {
    private final String a;
    private final Drawable b;
    private final String c;
    private final Drawable d;
    private final String e;
    private final double f;
    private final String g;
    private final String h;
    private final Object i = new Object();
    private un j;

    public ul(String str, Drawable drawable, String str2, Drawable drawable2, String str3, double d, String str4, String str5) {
        this.a = str;
        this.b = drawable;
        this.c = str2;
        this.d = drawable2;
        this.e = str3;
        this.f = d;
        this.g = str4;
        this.h = str5;
    }

    @Override // defpackage.up
    public String a() {
        return this.a;
    }

    @Override // defpackage.up
    public void a(int i) {
        synchronized (this.i) {
            if (this.j == null) {
                adn.b("Attempt to perform click before app install ad initialized.");
            } else {
                this.j.a("2", i);
            }
        }
    }

    @Override // defpackage.uo
    public void a(un unVar) {
        synchronized (this.i) {
            this.j = unVar;
        }
    }

    @Override // defpackage.up
    public od b() {
        return og.a(this.b);
    }

    @Override // defpackage.up
    public String c() {
        return this.c;
    }

    @Override // defpackage.up
    public od d() {
        return og.a(this.d);
    }

    @Override // defpackage.up
    public String e() {
        return this.e;
    }

    @Override // defpackage.up
    public double f() {
        return this.f;
    }

    @Override // defpackage.up
    public String g() {
        return this.g;
    }

    @Override // defpackage.up
    public String h() {
        return this.h;
    }

    @Override // defpackage.up
    public void i() {
        synchronized (this.i) {
            if (this.j == null) {
                adn.b("Attempt to record impression before app install ad initialized.");
            } else {
                this.j.a();
            }
        }
    }
}
