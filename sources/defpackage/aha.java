package defpackage;

import com.google.android.gms.fitness.data.DataSource;

/* loaded from: classes.dex */
public class aha {
    private static final ThreadLocal<String> a = new ThreadLocal<>();

    public static DataSource a(DataSource dataSource) {
        if (dataSource.h()) {
            return (a() || a.get().equals(dataSource.d())) ? dataSource : dataSource.j();
        }
        return dataSource;
    }

    public static String a(String str) {
        return a(str, a.get());
    }

    private static String a(String str, String str2) {
        if (str == null || str2 == null) {
            return str;
        }
        byte[] bArr = new byte[str.length() + str2.length()];
        System.arraycopy(str.getBytes(), 0, bArr, 0, str.length());
        System.arraycopy(str2.getBytes(), 0, bArr, str.length(), str2.length());
        return Integer.toHexString(age.a(bArr, 0, bArr.length, 0));
    }

    public static boolean a() {
        String str = a.get();
        return str == null || str.startsWith("com.google");
    }
}
