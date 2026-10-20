package defpackage;

import android.view.View;

@zy
/* loaded from: classes.dex */
public final class zk extends zm {
    private final rw a;
    private final String b;
    private final String c;

    public zk(rw rwVar, String str, String str2) {
        this.a = rwVar;
        this.b = str;
        this.c = str2;
    }

    @Override // defpackage.zl
    public String a() {
        return this.b;
    }

    @Override // defpackage.zl
    public void a(od odVar) {
        if (odVar == null) {
            return;
        }
        this.a.a((View) og.a(odVar));
    }

    @Override // defpackage.zl
    public String b() {
        return this.c;
    }

    @Override // defpackage.zl
    public void c() {
        this.a.a();
    }

    @Override // defpackage.zl
    public void d() {
        this.a.b();
    }
}
