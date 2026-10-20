package defpackage;

import com.google.android.gms.internal.pf;

/* loaded from: classes.dex */
public final class ww extends ajd<ww> {
    private static volatile ww[] o;
    public int a;
    public String b;
    public ww[] c;
    public ww[] d;
    public ww[] e;
    public String f;
    public String g;
    public long h;
    public boolean i;
    public ww[] j;
    public int[] k;
    public boolean l;

    public ww() {
        c();
    }

    public static ww[] a() {
        if (o == null) {
            synchronized (ajh.a) {
                if (o == null) {
                    o = new ww[0];
                }
            }
        }
        return o;
    }

    @Override // defpackage.ajd, defpackage.aji
    public void a(pf pfVar) {
        pfVar.a(1, this.a);
        if (!this.b.equals("")) {
            pfVar.a(2, this.b);
        }
        if (this.c != null && this.c.length > 0) {
            for (int i = 0; i < this.c.length; i++) {
                ww wwVar = this.c[i];
                if (wwVar != null) {
                    pfVar.a(3, wwVar);
                }
            }
        }
        if (this.d != null && this.d.length > 0) {
            for (int i2 = 0; i2 < this.d.length; i2++) {
                ww wwVar2 = this.d[i2];
                if (wwVar2 != null) {
                    pfVar.a(4, wwVar2);
                }
            }
        }
        if (this.e != null && this.e.length > 0) {
            for (int i3 = 0; i3 < this.e.length; i3++) {
                ww wwVar3 = this.e[i3];
                if (wwVar3 != null) {
                    pfVar.a(5, wwVar3);
                }
            }
        }
        if (!this.f.equals("")) {
            pfVar.a(6, this.f);
        }
        if (!this.g.equals("")) {
            pfVar.a(7, this.g);
        }
        if (this.h != 0) {
            pfVar.a(8, this.h);
        }
        if (this.l) {
            pfVar.a(9, this.l);
        }
        if (this.k != null && this.k.length > 0) {
            for (int i4 = 0; i4 < this.k.length; i4++) {
                pfVar.a(10, this.k[i4]);
            }
        }
        if (this.j != null && this.j.length > 0) {
            for (int i5 = 0; i5 < this.j.length; i5++) {
                ww wwVar4 = this.j[i5];
                if (wwVar4 != null) {
                    pfVar.a(11, wwVar4);
                }
            }
        }
        if (this.i) {
            pfVar.a(12, this.i);
        }
        super.a(pfVar);
    }

    @Override // defpackage.ajd, defpackage.aji
    protected int b() {
        int b = super.b() + pf.b(1, this.a);
        if (!this.b.equals("")) {
            b += pf.b(2, this.b);
        }
        if (this.c != null && this.c.length > 0) {
            int i = b;
            for (int i2 = 0; i2 < this.c.length; i2++) {
                ww wwVar = this.c[i2];
                if (wwVar != null) {
                    i += pf.c(3, wwVar);
                }
            }
            b = i;
        }
        if (this.d != null && this.d.length > 0) {
            int i3 = b;
            for (int i4 = 0; i4 < this.d.length; i4++) {
                ww wwVar2 = this.d[i4];
                if (wwVar2 != null) {
                    i3 += pf.c(4, wwVar2);
                }
            }
            b = i3;
        }
        if (this.e != null && this.e.length > 0) {
            int i5 = b;
            for (int i6 = 0; i6 < this.e.length; i6++) {
                ww wwVar3 = this.e[i6];
                if (wwVar3 != null) {
                    i5 += pf.c(5, wwVar3);
                }
            }
            b = i5;
        }
        if (!this.f.equals("")) {
            b += pf.b(6, this.f);
        }
        if (!this.g.equals("")) {
            b += pf.b(7, this.g);
        }
        if (this.h != 0) {
            b += pf.c(8, this.h);
        }
        if (this.l) {
            b += pf.b(9, this.l);
        }
        if (this.k != null && this.k.length > 0) {
            int i7 = 0;
            for (int i8 = 0; i8 < this.k.length; i8++) {
                i7 += pf.b(this.k[i8]);
            }
            b = b + i7 + (this.k.length * 1);
        }
        if (this.j != null && this.j.length > 0) {
            for (int i9 = 0; i9 < this.j.length; i9++) {
                ww wwVar4 = this.j[i9];
                if (wwVar4 != null) {
                    b += pf.c(11, wwVar4);
                }
            }
        }
        return this.i ? b + pf.b(12, this.i) : b;
    }

    public ww c() {
        this.a = 1;
        this.b = "";
        this.c = a();
        this.d = a();
        this.e = a();
        this.f = "";
        this.g = "";
        this.h = 0L;
        this.i = false;
        this.j = a();
        this.k = ajl.a;
        this.l = false;
        this.m = null;
        this.n = -1;
        return this;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ww)) {
            return false;
        }
        ww wwVar = (ww) obj;
        if (this.a != wwVar.a) {
            return false;
        }
        if (this.b == null) {
            if (wwVar.b != null) {
                return false;
            }
        } else if (!this.b.equals(wwVar.b)) {
            return false;
        }
        if (!ajh.a(this.c, wwVar.c) || !ajh.a(this.d, wwVar.d) || !ajh.a(this.e, wwVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (wwVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(wwVar.f)) {
            return false;
        }
        if (this.g == null) {
            if (wwVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(wwVar.g)) {
            return false;
        }
        if (this.h == wwVar.h && this.i == wwVar.i && ajh.a(this.j, wwVar.j) && ajh.a(this.k, wwVar.k) && this.l == wwVar.l) {
            return a(wwVar);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((this.i ? 1231 : 1237) + (((((((this.f == null ? 0 : this.f.hashCode()) + (((((((((this.b == null ? 0 : this.b.hashCode()) + ((this.a + 527) * 31)) * 31) + ajh.a(this.c)) * 31) + ajh.a(this.d)) * 31) + ajh.a(this.e)) * 31)) * 31) + (this.g != null ? this.g.hashCode() : 0)) * 31) + ((int) (this.h ^ (this.h >>> 32)))) * 31)) * 31) + ajh.a(this.j)) * 31) + ajh.a(this.k)) * 31) + (this.l ? 1231 : 1237)) * 31) + d();
    }
}
