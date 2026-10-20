package com.yozio.android.helpers;

import android.os.Build;

/* loaded from: classes.dex */
public class Utils {
    private Utils() {
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= 9;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= 11;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= 12;
    }

    public static boolean hasICS() {
        return Build.VERSION.SDK_INT >= 14;
    }
}
