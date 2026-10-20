package defpackage;

import android.content.Context;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class asi {
    static Map<String, String> a = new HashMap();
    private static String b;

    asi() {
    }

    static String a(String str, String str2) {
        if (str2 != null) {
            return Uri.parse("http://hostname/?" + str).getQueryParameter(str2);
        }
        if (str.length() > 0) {
            return str;
        }
        return null;
    }

    public static void a(Context context, String str) {
        atm.a(context, "gtm_install_referrer", "referrer", str);
        b(context, str);
    }

    public static void a(String str) {
        synchronized (asi.class) {
            b = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(Context context, String str) {
        String a2 = a(str, "conv");
        if (a2 == null || a2.length() <= 0) {
            return;
        }
        a.put(a2, str);
        atm.a(context, "gtm_click_referrers", a2, str);
    }
}
