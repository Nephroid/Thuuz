package defpackage;

import android.graphics.drawable.Drawable;
// [removed legacy tracker import]
@zy
/* loaded from: classes.dex */
public class um extends us implements uo {
    private final String a;
    private final Drawable b;
    private final String c;
    private final Drawable d;
    private final String e;
    private final String f;
    private final Object g = new Object();
    private un h;

    public um(String str, Drawable drawable, String str2, Drawable drawable2, String str3, String str4) {
        this.a = str;
        this.b = drawable;
        this.c = str2;
        this.d = drawable2;
        this.e = str3;
        this.f = str4;
    }

    @Override // defpackage.ur
    public String a() {
        return this.a;
    }

    @Override // defpackage.ur
    public void a(int i) {
        synchronized (this.g) {
            if (this.h == null) {
                adn.b("Attempt to perform click before content ad initialized.");
            } else {
                this.h.a(AppEventsConstants.EVENT_PARAM_VALUE_YES, i);
            }
        }
    }

    @Override // defpackage.uo
    public void a(un unVar) {
        synchronized (this.g) {
            this.h = unVar;
        }
    }

    @Override // defpackage.ur
    public od b() {
        return og.a(this.b);
    }

    @Override // defpackage.ur
    public String c() {
        return this.c;
    }

    @Override // defpackage.ur
    public od d() {
        return og.a(this.d);
    }

    @Override // defpackage.ur
    public String e() {
        return this.e;
    }

    @Override // defpackage.ur
    public String f() {
        return this.f;
    }

    @Override // defpackage.ur
    public void g() {
        synchronized (this.g) {
            if (this.h == null) {
                adn.b("Attempt to record impression before content ad initialized.");
            } else {
                this.h.a();
            }
        }
    }
}
