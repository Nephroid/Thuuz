package defpackage;

/* loaded from: classes.dex */
public class bps implements Comparable<bps> {
    private int a;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f;
    private bpr g;
    private boolean h;

    public bps(bpr bprVar, int i, String str, String str2, String str3, String str4, int i2, boolean z) {
        this.g = bprVar;
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = i2;
        this.h = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bps bpsVar = (bps) obj;
        if (this.a == bpsVar.a && this.f == bpsVar.f && this.h == bpsVar.h) {
            if (this.g == null ? bpsVar.g != null : !this.g.equals(bpsVar.g)) {
                return false;
            }
            if (this.c == null ? bpsVar.c != null : !this.c.equals(bpsVar.c)) {
                return false;
            }
            if (this.d == null ? bpsVar.d != null : !this.d.equals(bpsVar.d)) {
                return false;
            }
            if (this.b == null ? bpsVar.b != null : !this.b.equals(bpsVar.b)) {
                return false;
            }
            if (this.e != null) {
                if (this.e.equals(bpsVar.e)) {
                    return true;
                }
            } else if (bpsVar.e == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return (((this.g != null ? this.g.hashCode() : 0) + (((((this.e != null ? this.e.hashCode() : 0) + (((this.d != null ? this.d.hashCode() : 0) + (((this.c != null ? this.c.hashCode() : 0) + (((this.b != null ? this.b.hashCode() : 0) + (this.a * 31)) * 31)) * 31)) * 31)) * 31) + this.f) * 31)) * 31) + (this.h ? 1 : 0);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bps bpsVar) {
        int compareTo = Integer.valueOf(this.f).compareTo(Integer.valueOf(bpsVar.f));
        return compareTo != 0 ? compareTo : this.c.compareTo(bpsVar.c);
    }

    public String toString() {
        return this.d;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public int c() {
        return this.a;
    }

    public bpr d() {
        return this.g;
    }

    public String e() {
        return this.d;
    }

    public boolean f() {
        return this.f < 0;
    }

    public boolean g() {
        return d().i();
    }

    public boolean h() {
        return this.h;
    }
}
