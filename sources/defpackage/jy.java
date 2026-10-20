package defpackage;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class jy {
    public static int a(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static ka a(Object obj) {
        return new ka(obj);
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
