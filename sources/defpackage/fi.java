package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class fi implements fp {
    private static fi e;
    private static Object f = new Object();
    protected String a;
    protected String b;
    protected String c;
    protected String d;

    protected fi() {
    }

    private fi(Context context) {
        PackageManager packageManager = context.getPackageManager();
        this.c = context.getPackageName();
        this.d = packageManager.getInstallerPackageName(this.c);
        String str = this.c;
        String str2 = null;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            if (packageInfo != null) {
                str = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                str2 = packageInfo.versionName;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            gn.a("Error retrieving package info: appName set to " + str);
        }
        this.a = str;
        this.b = str2;
    }

    public static fi a() {
        return e;
    }

    public static void a(Context context) {
        synchronized (f) {
            if (e == null) {
                e = new fi(context);
            }
        }
    }

    @Override // defpackage.fp
    public String a(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("&an")) {
            return this.a;
        }
        if (str.equals("&av")) {
            return this.b;
        }
        if (str.equals("&aid")) {
            return this.c;
        }
        if (str.equals("&aiid")) {
            return this.d;
        }
        return null;
    }
}
