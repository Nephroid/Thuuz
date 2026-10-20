package defpackage;

import android.os.Bundle;

@zy
/* loaded from: classes.dex */
public final class uk {
    private static boolean n;
    private static final Bundle m = new Bundle();
    public static aex<String> a = a("gads:sdk_core_location", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/sdk-core-v40.html");
    public static aex<String> b = a("gads:sdk_core_experiment_id", (String) null);
    public static aex<Boolean> c = a("gads:sdk_crash_report_enabled", false);
    public static aex<Boolean> d = a("gads:sdk_crash_report_full_stacktrace", false);
    public static aex<Boolean> e = a("gads:block_autoclicks", false);
    public static aex<String> f = a("gads:block_autoclicks_experiment_id", (String) null);
    public static aex<Boolean> g = a("gads:enable_content_fetching", false);
    public static aex<Integer> h = a("gads:content_length_weight", 1);
    public static aex<Integer> i = a("gads:content_age_weight", 1);
    public static aex<Integer> j = a("gads:min_content_len", 11);
    public static aex<Integer> k = a("gads:fingerprint_number", 10);
    public static aex<Integer> l = a("gads:sleep_sec", 10);

    static {
        n = false;
        n = true;
    }

    private static aex<Integer> a(String str, int i2) {
        m.putInt(str, i2);
        return aex.a(str, Integer.valueOf(i2));
    }

    private static aex<String> a(String str, String str2) {
        m.putString(str, str2);
        return aex.a(str, str2);
    }

    private static aex<Boolean> a(String str, boolean z) {
        m.putBoolean(str, z);
        return aex.a(str, z);
    }

    public static Bundle a() {
        return m;
    }
}
