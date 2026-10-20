package twitter4j.internal.util;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class z_T4JInternalStringUtil {
    private z_T4JInternalStringUtil() {
        throw new AssertionError();
    }

    public static String maskString(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            sb.append("*");
        }
        return sb.toString();
    }

    public static String[] split(String str, String str2) {
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return new String[]{str};
        }
        ArrayList arrayList = new ArrayList();
        int i = indexOf;
        int i2 = 0;
        while (i != -1) {
            arrayList.add(str.substring(i2, i));
            i2 = str2.length() + i;
            i = str.indexOf(str2, i2);
        }
        if (i2 != str.length()) {
            arrayList.add(str.substring(i2));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String join(int[] iArr) {
        StringBuilder sb = new StringBuilder(iArr.length * 11);
        for (int i : iArr) {
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append(i);
        }
        return sb.toString();
    }

    public static String join(long[] jArr) {
        StringBuilder sb = new StringBuilder(jArr.length * 11);
        for (long j : jArr) {
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append(j);
        }
        return sb.toString();
    }

    public static String join(String[] strArr) {
        StringBuilder sb = new StringBuilder(strArr.length * 11);
        for (String str : strArr) {
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public static String join(List<String> list) {
        StringBuilder sb = new StringBuilder(list.size() * 11);
        for (String str : list) {
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append(str);
        }
        return sb.toString();
    }
}
