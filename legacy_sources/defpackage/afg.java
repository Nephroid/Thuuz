package defpackage;

/* loaded from: classes.dex */
public final class afg {
    public final int a;
    public final int b;

    public afg(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof afg)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        afg afgVar = (afg) obj;
        return afgVar.a == this.a && afgVar.b == this.b;
    }

    public int hashCode() {
        return jy.a(Integer.valueOf(this.a), Integer.valueOf(this.b));
    }
}
