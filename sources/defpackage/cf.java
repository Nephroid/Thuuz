package defpackage;

@Deprecated
/* loaded from: classes.dex */
public final class cf {
    public static final cf a = new cf(-1, -2, "mb");
    public static final cf b = new cf(320, 50, "mb");
    public static final cf c = new cf(300, 250, "as");
    public static final cf d = new cf(468, 60, "as");
    public static final cf e = new cf(728, 90, "as");
    public static final cf f = new cf(160, 600, "as");
    private final db g;

    private cf(int i, int i2, String str) {
        this(new db(i, i2));
    }

    public cf(db dbVar) {
        this.g = dbVar;
    }

    public int a() {
        return this.g.b();
    }

    public int b() {
        return this.g.a();
    }

    public boolean equals(Object obj) {
        if (obj instanceof cf) {
            return this.g.equals(((cf) obj).g);
        }
        return false;
    }

    public int hashCode() {
        return this.g.hashCode();
    }

    public String toString() {
        return this.g.toString();
    }
}
