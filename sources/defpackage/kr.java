package defpackage;

import com.google.android.gms.internal.pf;

/* loaded from: classes.dex */
public final class kr extends ajd<kr> {
    public int a;
    public String b;
    public long c;
    public long d;

    public kr() {
        a();
    }

    public kr a() {
        this.a = 1;
        this.b = "";
        this.c = -1L;
        this.d = -1L;
        this.m = null;
        this.n = -1;
        return this;
    }

    @Override // defpackage.ajd, defpackage.aji
    public void a(pf pfVar) {
        pfVar.a(1, this.a);
        pfVar.a(2, this.b);
        pfVar.b(3, this.c);
        pfVar.b(4, this.d);
        super.a(pfVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.ajd, defpackage.aji
    public int b() {
        return super.b() + pf.b(1, this.a) + pf.b(2, this.b) + pf.d(3, this.c) + pf.d(4, this.d);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof kr)) {
            return false;
        }
        kr krVar = (kr) obj;
        if (this.a != krVar.a) {
            return false;
        }
        if (this.b == null) {
            if (krVar.b != null) {
                return false;
            }
        } else if (!this.b.equals(krVar.b)) {
            return false;
        }
        if (this.c == krVar.c && this.d == krVar.d) {
            return a(krVar);
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.b == null ? 0 : this.b.hashCode()) + ((this.a + 527) * 31)) * 31) + ((int) (this.c ^ (this.c >>> 32)))) * 31) + ((int) (this.d ^ (this.d >>> 32)))) * 31) + d();
    }
}
