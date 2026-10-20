package defpackage;

/* loaded from: classes.dex */
public final class fr {
    public static String a(int i) {
        return a("&pr", i);
    }

    private static String a(String str, int i) {
        if (i >= 1) {
            return str + i;
        }
        gn.a("index out of range for " + str + " (" + i + ")");
        return "";
    }

    public static String b(int i) {
        return a("&promo", i);
    }

    public static String c(int i) {
        return a("pi", i);
    }

    public static String d(int i) {
        return a("&il", i);
    }
}
