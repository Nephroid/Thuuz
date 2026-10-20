package defpackage;

import android.content.Context;
import android.content.SharedPreferences;

@zy
/* loaded from: classes.dex */
public final class acs {
    public static void a(Context context, boolean z) {
        SharedPreferences.Editor edit = b(context).edit();
        edit.putBoolean("use_https", z);
        edit.commit();
    }

    public static boolean a(Context context) {
        return b(context).getBoolean("use_https", true);
    }

    private static SharedPreferences b(Context context) {
        return context.getSharedPreferences("admob", 0);
    }
}
