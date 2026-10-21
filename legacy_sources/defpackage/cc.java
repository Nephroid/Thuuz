package defpackage;

import android.util.Log;

/* loaded from: classes.dex */
public class cc {
    private static String a;
    private static String b;

    public static void a(String str) {
        a = str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || i == 1 || i > str.length() - 5) {
                sb.append(str.charAt(i));
            } else {
                sb.append("*");
            }
        }
        b = sb.toString();
    }

    public static void b(String str) {
        if (str.contains(a)) {
            Log.i("AppsFlyer_", str.replace(a, b));
        }
    }
}
