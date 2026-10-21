package defpackage;

import android.content.Context;
import com.google.android.gms.internal.ay;

/* loaded from: classes.dex */
public final class db {
    public static final db a = new db(320, 50, "320x50_mb");
    public static final db b = new db(468, 60, "468x60_as");
    public static final db c = new db(320, 100, "320x100_as");
    public static final db d = new db(728, 90, "728x90_as");
    public static final db e = new db(300, 250, "300x250_as");
    public static final db f = new db(160, 600, "160x600_as");
    public static final db g = new db(-1, -2, "smart_banner");
    private final int h;
    private final int i;
    private final String j;

    public db(int i, int i2) {
        this(i, i2, (i == -1 ? "FULL" : String.valueOf(i)) + "x" + (i2 == -2 ? "AUTO" : String.valueOf(i2)) + "_as");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public db(int i, int i2, String str) {
        if (i < 0 && i != -1) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + i);
        }
        if (i2 < 0 && i2 != -2) {
            throw new IllegalArgumentException("Invalid height for AdSize: " + i2);
        }
        this.h = i;
        this.i = i2;
        this.j = str;
    }

    public int a() {
        return this.i;
    }

    public int a(Context context) {
        return this.i == -2 ? ay.b(context.getResources().getDisplayMetrics()) : adm.a(context, this.i);
    }

    public int b() {
        return this.h;
    }

    public int b(Context context) {
        return this.h == -1 ? ay.a(context.getResources().getDisplayMetrics()) : adm.a(context, this.h);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof db)) {
            return false;
        }
        db dbVar = (db) obj;
        return this.h == dbVar.h && this.i == dbVar.i && this.j.equals(dbVar.j);
    }

    public int hashCode() {
        return this.j.hashCode();
    }

    public String toString() {
        return this.j;
    }
}
