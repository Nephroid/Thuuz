package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import java.util.Locale;

@zy
/* loaded from: classes.dex */
public final class acd {
    public final int a;
    public final boolean b;
    public final boolean c;
    public final String d;
    public final String e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final String i;
    public final String j;
    public final int k;
    public final int l;
    public final int m;
    public final int n;
    public final int o;
    public final int p;
    public final float q;
    public final int r;
    public final int s;
    public final double t;
    public final boolean u;
    public final boolean v;
    public final int w;

    public acd(Context context) {
        boolean z = true;
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Locale locale = Locale.getDefault();
        PackageManager packageManager = context.getPackageManager();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        this.a = audioManager.getMode();
        this.b = a(packageManager, "geo:0,0?q=donuts") != null;
        this.c = a(packageManager, "http://www.google.com") != null;
        this.d = telephonyManager.getNetworkOperator();
        this.e = locale.getCountry();
        this.f = adm.a();
        this.g = audioManager.isMusicActive();
        this.h = audioManager.isSpeakerphoneOn();
        this.i = locale.getLanguage();
        this.j = a(packageManager);
        this.k = audioManager.getStreamVolume(3);
        this.l = a(context, connectivityManager, packageManager);
        this.m = telephonyManager.getNetworkType();
        this.n = telephonyManager.getPhoneType();
        this.o = audioManager.getRingerMode();
        this.p = audioManager.getStreamVolume(2);
        this.q = displayMetrics.density;
        this.r = displayMetrics.widthPixels;
        this.s = displayMetrics.heightPixels;
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("status", -1);
            this.t = registerReceiver.getIntExtra("level", -1) / registerReceiver.getIntExtra("scale", -1);
            if (intExtra != 2 && intExtra != 5) {
                z = false;
            }
            this.u = z;
        } else {
            this.t = -1.0d;
            this.u = false;
        }
        if (Build.VERSION.SDK_INT < 16) {
            this.v = false;
            this.w = -1;
            return;
        }
        this.v = connectivityManager.isActiveNetworkMetered();
        if (connectivityManager.getActiveNetworkInfo() != null) {
            this.w = connectivityManager.getActiveNetworkInfo().getDetailedState().ordinal();
        } else {
            this.w = -1;
        }
    }

    private static int a(Context context, ConnectivityManager connectivityManager, PackageManager packageManager) {
        if (!acx.a(packageManager, context.getPackageName(), "android.permission.ACCESS_NETWORK_STATE")) {
            return -2;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getType();
        }
        return -1;
    }

    private static ResolveInfo a(PackageManager packageManager, String str) {
        return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
    }

    private static String a(PackageManager packageManager) {
        ActivityInfo activityInfo;
        ResolveInfo a = a(packageManager, "market://details?id=com.google.android.gms.ads");
        if (a == null || (activityInfo = a.activityInfo) == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(activityInfo.packageName, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode + "." + activityInfo.packageName;
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }
}
