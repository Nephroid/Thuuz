package defpackage;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class bw {
    private Map<String, Object> d = new HashMap();
    private boolean e;
    private boolean f;
    private String g;
    private boolean h;
    private static bw c = new bw();
    static boolean a = false;
    static boolean b = false;

    private bw() {
    }

    public static bw a() {
        return c;
    }

    public void a(String str, String str2) {
        this.d.put(str, str2);
    }

    public String[] a(String str) {
        return (String[]) this.d.get(str);
    }

    public String b(String str) {
        return (String) this.d.get(str);
    }

    public void a(String str, boolean z) {
        this.d.put(str, Boolean.toString(z));
    }

    public boolean b(String str, boolean z) {
        String b2 = b(str);
        return b2 == null ? z : Boolean.valueOf(b2).booleanValue();
    }

    public int a(String str, int i) {
        String b2 = b(str);
        return b2 == null ? i : Integer.valueOf(b2).intValue();
    }

    public void b() {
        this.e = true;
    }

    public boolean c() {
        return this.f;
    }

    public void d() {
        this.f = true;
    }

    public void c(String str) {
        this.g = str;
    }

    public String a(Context context) {
        if (this.g != null) {
            return this.g;
        }
        return context.getSharedPreferences("appsflyer-data", 0).getString("referrer", null);
    }

    public boolean e() {
        return this.h;
    }
}
