package defpackage;

import android.os.Build;

/* loaded from: classes.dex */
public final class agf {
    public static boolean a() {
        return a(11);
    }

    private static boolean a(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    public static boolean b() {
        return a(13);
    }

    public static boolean c() {
        return a(14);
    }

    public static boolean d() {
        return a(19);
    }
}
