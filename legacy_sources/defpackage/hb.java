package defpackage;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.util.Arrays;
import twitter4j.conf.PropertyConfiguration;

/* loaded from: classes.dex */
public final class hb {
    public static boolean a = false;
    public static boolean b = false;
    private static int c = -1;
    private static final Object d = new Object();

    public static int a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            context.getResources().getString(cv.common_google_play_services_unknown_issue);
        } catch (Throwable th) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        e(context);
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.gms", 64);
            if (afw.c(packageInfo.versionCode)) {
                char c2 = a() ? (char) 0 : (char) 1;
                if (a(packageInfo, ig.a[c2], ig.f[c2]) == null) {
                    Log.w("GooglePlayServicesUtil", "Google Play Services signature invalid on Glass.");
                    return 9;
                }
                String packageName = context.getPackageName();
                try {
                    PackageInfo packageInfo2 = packageManager.getPackageInfo(packageName, 64);
                    if (!a(packageManager, packageInfo2)) {
                        Log.w("GooglePlayServicesUtil", "Calling package " + packageInfo2.packageName + " signature invalid on Glass.");
                        return 9;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.w("GooglePlayServicesUtil", "Could not get info for calling package: " + packageName);
                    return 9;
                }
            } else if (!afw.a(context)) {
                try {
                    byte[] a2 = a(packageManager.getPackageInfo("com.android.vending", 64), ig.a);
                    if (a2 == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                        return 9;
                    }
                    if (a(packageInfo, a2) == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                        return 9;
                    }
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
                    return 9;
                }
            } else if (a(packageInfo, ig.a) == null) {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            }
            if (afw.a(packageInfo.versionCode) < afw.a(6111000)) {
                Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires 6111000 but found " + packageInfo.versionCode);
                return 2;
            }
            try {
                return !packageManager.getApplicationInfo("com.google.android.gms", 0).enabled ? 3 : 0;
            } catch (PackageManager.NameNotFoundException e3) {
                Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.");
                e3.printStackTrace();
                return 1;
            }
        } catch (PackageManager.NameNotFoundException e4) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
    }

    public static Intent a(int i) {
        switch (i) {
            case 1:
            case 2:
                return jn.b("com.google.android.gms");
            case 3:
                return jn.a("com.google.android.gms");
            case 42:
                return jn.a();
            default:
                return null;
        }
    }

    @Deprecated
    public static Intent a(Context context, int i) {
        return a(i);
    }

    public static boolean a() {
        return a ? b : PropertyConfiguration.USER.equals(Build.TYPE);
    }

    public static boolean a(int i, Activity activity, Fragment fragment, int i2, DialogInterface.OnCancelListener onCancelListener) {
        boolean z = false;
        Dialog b2 = b(i, activity, fragment, i2, onCancelListener);
        if (b2 == null) {
            return false;
        }
        try {
            z = activity instanceof FragmentActivity;
        } catch (NoClassDefFoundError e) {
        }
        if (z) {
            hc.a(b2, onCancelListener).show(((FragmentActivity) activity).getSupportFragmentManager(), "GooglePlayServicesErrorDialog");
        } else {
            if (!agf.a()) {
                throw new RuntimeException("This Activity does not support Fragments.");
            }
            gx.a(b2, onCancelListener).show(activity.getFragmentManager(), "GooglePlayServicesErrorDialog");
        }
        return true;
    }

    public static boolean a(PackageManager packageManager) {
        synchronized (d) {
            if (c == -1) {
                try {
                    if (a(packageManager.getPackageInfo("com.google.android.gms", 64), ig.s[1]) != null) {
                        c = 1;
                    } else {
                        c = 0;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    c = 0;
                }
            }
        }
        return c != 0;
    }

    public static boolean a(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (b(packageManager)) {
            return a(packageInfo, true) != null;
        }
        boolean z = a(packageInfo, false) != null;
        if (z || a(packageInfo, true) == null) {
            return z;
        }
        Log.w("GooglePlayServicesUtil", "Test-keys aren't accepted on this build.");
        return z;
    }

    public static boolean a(Resources resources) {
        if (resources == null) {
            return false;
        }
        return (agf.a() && ((resources.getConfiguration().screenLayout & 15) > 3)) || b(resources);
    }

    private static byte[] a(PackageInfo packageInfo, boolean z) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GooglePlayServicesUtil", "Package has more than one signature.");
            return null;
        }
        byte[] byteArray = packageInfo.signatures[0].toByteArray();
        if ((z ? ig.a() : ig.b()).contains(byteArray)) {
            return byteArray;
        }
        if (Log.isLoggable("GooglePlayServicesUtil", 2)) {
            Log.v("GooglePlayServicesUtil", "Signature not valid.  Found: \n" + Base64.encodeToString(byteArray, 0));
        }
        return null;
    }

    private static byte[] a(PackageInfo packageInfo, byte[]... bArr) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GooglePlayServicesUtil", "Package has more than one signature.");
            return null;
        }
        byte[] byteArray = packageInfo.signatures[0].toByteArray();
        for (byte[] bArr2 : bArr) {
            if (Arrays.equals(bArr2, byteArray)) {
                return bArr2;
            }
        }
        if (Log.isLoggable("GooglePlayServicesUtil", 2)) {
            Log.v("GooglePlayServicesUtil", "Signature not valid.  Found: \n" + Base64.encodeToString(byteArray, 0));
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.app.Dialog b(int r5, android.app.Activity r6, android.support.v4.app.Fragment r7, int r8, android.content.DialogInterface.OnCancelListener r9) {
        /*
            Method dump skipped, instructions count: 336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hb.b(int, android.app.Activity, android.support.v4.app.Fragment, int, android.content.DialogInterface$OnCancelListener):android.app.Dialog");
    }

    public static String b(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return a(context.getResources()) ? resources.getString(cv.common_google_play_services_install_text_tablet) : resources.getString(cv.common_google_play_services_install_text_phone);
            case 2:
                return resources.getString(cv.common_google_play_services_update_text);
            case 3:
                return resources.getString(cv.common_google_play_services_enable_text);
            case 5:
                return resources.getString(cv.common_google_play_services_invalid_account_text);
            case 7:
                return resources.getString(cv.common_google_play_services_network_error_text);
            case 9:
                return resources.getString(cv.common_google_play_services_unsupported_text);
            case 42:
                return resources.getString(cv.common_android_wear_update_text);
            default:
                return resources.getString(cv.common_google_play_services_unknown_issue);
        }
    }

    public static void b(Context context) {
        int a2 = a(context);
        if (a2 != 0) {
            Intent a3 = a(context, a2);
            Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + a2);
            if (a3 != null) {
                throw new GooglePlayServicesRepairableException(a2, "Google Play Services not available", a3);
            }
            throw new GooglePlayServicesNotAvailableException(a2);
        }
    }

    public static boolean b(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 9:
                return true;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            default:
                return false;
        }
    }

    public static boolean b(PackageManager packageManager) {
        return a(packageManager) || !a();
    }

    private static boolean b(Resources resources) {
        Configuration configuration = resources.getConfiguration();
        return agf.b() && (configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600;
    }

    public static Resources c(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static String c(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(cv.common_google_play_services_install_button);
            case 2:
            case 42:
                return resources.getString(cv.common_google_play_services_update_button);
            case 3:
                return resources.getString(cv.common_google_play_services_enable_button);
            default:
                return resources.getString(R.string.ok);
        }
    }

    public static Context d(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private static void e(Context context) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            Log.wtf("GooglePlayServicesUtil", "This should never happen.", e);
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle == null) {
            throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
        }
        int i = bundle.getInt("com.google.android.gms.version");
        if (i != 6111000) {
            throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected 6111000 but found " + i + ".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
        }
    }
}
