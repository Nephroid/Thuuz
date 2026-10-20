package com.flurry.sdk;

import android.text.TextUtils;

/* loaded from: classes.dex */
public final class fa {
    private static final String a = fa.class.getSimpleName();

    public static fc a(String str) {
        fc fcVar;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            fcVar = (fc) Class.forName(str).getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            ex.a(5, a, "FlurryModule " + str + " is not available:", e);
            fcVar = null;
        }
        return fcVar;
    }
}
