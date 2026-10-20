package defpackage;

/* loaded from: classes.dex */
public class gn {
    private static dz a;

    public static void a(String str) {
        eh b = b();
        if (b != null) {
            b.d(str);
        }
    }

    public static boolean a() {
        return b() != null && b().a() == 0;
    }

    private static eh b() {
        if (a == null) {
            a = dz.a();
        }
        if (a != null) {
            return a.d();
        }
        return null;
    }

    public static void b(String str) {
        eh b = b();
        if (b != null) {
            b.b(str);
        }
    }

    public static void c(String str) {
        eh b = b();
        if (b != null) {
            b.a(str);
        }
    }

    public static void d(String str) {
        eh b = b();
        if (b != null) {
            b.c(str);
        }
    }
}
