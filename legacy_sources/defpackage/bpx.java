package defpackage;

import java.util.logging.Level;

/* loaded from: classes.dex */
public class bpx extends bzy implements Comparable<bpx> {
    private int a;
    private String b;
    private String c;
    private int d;
    private String g;
    private int i;
    private String e = "Game";
    private String f = "Teams";
    private int h = 0;

    public bpx(String str, int i, String str2, int i2, String str3, int i3) {
        this.g = "at";
        this.b = str2;
        this.c = str;
        this.a = i;
        this.d = i2;
        this.i = i3;
        this.g = str3;
        if (str3 == null) {
            this.g = "at";
        } else {
            this.g = str3.trim();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bpx bpxVar = (bpx) obj;
        if (this.a == bpxVar.a() && this.d == bpxVar.d) {
            if (this.b == null ? bpxVar.c() != null : !this.b.equals(bpxVar.c())) {
                return false;
            }
            if (this.c != null) {
                if (this.c.equals(bpxVar.b())) {
                    return true;
                }
            } else if (bpxVar.b() == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.b != null ? this.b.hashCode() : 0) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31) + this.a) * 31) + this.d;
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.b;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bpx bpxVar) {
        if (bpxVar == null) {
            return 1;
        }
        int i = 0;
        try {
            if (g() == bpxVar.g()) {
                i = this.b.compareTo(bpxVar.b);
            } else {
                i = Integer.valueOf(g()).compareTo(Integer.valueOf(bpxVar.g()));
            }
            return i;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
            return i;
        }
    }

    public String toString() {
        return this.b;
    }

    public boolean d() {
        return b().equalsIgnoreCase("soccer") || b().equalsIgnoreCase("cricket") || b().equalsIgnoreCase("rugby") || b().equalsIgnoreCase("tennis");
    }

    public void a(String str) {
        this.e = str;
    }

    public String e() {
        return this.e == null ? "Game" : this.e;
    }

    public void a(int i) {
        this.h = i;
    }

    public int f() {
        return this.h;
    }

    public int g() {
        return this.d;
    }

    public String h() {
        return i();
    }

    public String i() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public boolean j() {
        return this.i == 1;
    }

    public String k() {
        return this.g;
    }
}
