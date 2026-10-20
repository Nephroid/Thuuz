package defpackage;

import org.json.JSONObject;

/* loaded from: classes.dex */
public class bph extends bpq {
    private int a;

    public bph(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void a(int i) {
        this.a = i;
    }

    public int a() {
        return this.a;
    }

    public boolean b() {
        return g("air");
    }

    public void a(boolean z) {
        a("air", z);
    }

    public boolean c() {
        return g("acm");
    }

    public void b(boolean z) {
        a("acm", z);
    }

    public boolean d() {
        return g("asp");
    }

    public void c(boolean z) {
        a("asp", z);
    }

    public boolean e() {
        return g("ahp");
    }

    public void d(boolean z) {
        a("ahp", z);
    }

    public boolean f() {
        return b() || c() || d() || e();
    }

    @Override // defpackage.bpq
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bph bphVar = (bph) obj;
        return this.a == bphVar.a && c() == bphVar.c() && e() == bphVar.e() && b() == bphVar.b() && d() == bphVar.d();
    }

    @Override // defpackage.bpq
    public int hashCode() {
        return (((d() ? 1 : 0) + (((c() ? 1 : 0) + (((b() ? 1 : 0) + (this.a * 31)) * 31)) * 31)) * 31) + (e() ? 1 : 0);
    }
}
