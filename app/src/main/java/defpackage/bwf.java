package defpackage;

/* loaded from: classes.dex */
public class bwf {
    public bpr a;
    public bps b;
    public bpz c;
    public String d;
    public String e;

    public bwf(bpr bprVar) {
        this.a = bprVar;
    }

    public bwf(bps bpsVar) {
        this.b = bpsVar;
    }

    public bwf(bpz bpzVar) {
        this.c = bpzVar;
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean b() {
        return this.c != null;
    }

    public String c() {
        if (a()) {
            return this.a.f();
        }
        if (b()) {
            return this.c.c();
        }
        if (g()) {
            return this.b.b();
        }
        return "???";
    }

    public int d() {
        if (a()) {
            return this.a.g();
        }
        if (b()) {
            return this.c.b();
        }
        if (g()) {
            return this.b.c();
        }
        return -1;
    }

    public void a(String str) {
        this.d = str;
    }

    public String e() {
        return this.d;
    }

    public void b(String str) {
        this.e = str;
    }

    public String f() {
        return this.e;
    }

    public boolean g() {
        return this.b != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bwf bwfVar = (bwf) obj;
        if (this.a == null ? bwfVar.a != null : !this.a.equals(bwfVar.a)) {
            return false;
        }
        if (this.b == null ? bwfVar.b != null : !this.b.equals(bwfVar.b)) {
            return false;
        }
        if (this.d == null ? bwfVar.d != null : !this.d.equals(bwfVar.d)) {
            return false;
        }
        if (this.c != null) {
            if (this.c.equals(bwfVar.c)) {
                return true;
            }
        } else if (bwfVar.c == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.c != null ? this.c.hashCode() : 0) + (((this.b != null ? this.b.hashCode() : 0) + ((this.a != null ? this.a.hashCode() : 0) * 31)) * 31)) * 31) + (this.d != null ? this.d.hashCode() : 0);
    }
}
