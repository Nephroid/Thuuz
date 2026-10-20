package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Log;
import com.google.android.gcm.GCMBroadcastReceiver;
import com.yozio.android.Constants;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class cr {
    private static GCMBroadcastReceiver a;
    private static String b;

    public static void a(Context context) {
        int i = Build.VERSION.SDK_INT;
        if (i < 8) {
            throw new UnsupportedOperationException("Device must be at least API Level 8 (instead of " + i + ")");
        }
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gsf", 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new UnsupportedOperationException("Device does not have package com.google.android.gsf");
        }
    }

    public static void b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        String str = packageName + ".permission.C2D_MESSAGE";
        try {
            packageManager.getPermissionInfo(str, 4096);
            try {
                ActivityInfo[] activityInfoArr = packageManager.getPackageInfo(packageName, 2).receivers;
                if (activityInfoArr == null || activityInfoArr.length == 0) {
                    throw new IllegalStateException("No receiver for package " + packageName);
                }
                if (Log.isLoggable("GCMRegistrar", 2)) {
                    Log.v("GCMRegistrar", "number of receivers for " + packageName + ": " + activityInfoArr.length);
                }
                HashSet hashSet = new HashSet();
                for (ActivityInfo activityInfo : activityInfoArr) {
                    if ("com.google.android.c2dm.permission.SEND".equals(activityInfo.permission)) {
                        hashSet.add(activityInfo.name);
                    }
                }
                if (hashSet.isEmpty()) {
                    throw new IllegalStateException("No receiver allowed to receive com.google.android.c2dm.permission.SEND");
                }
                a(context, hashSet, "com.google.android.c2dm.intent.REGISTRATION");
                a(context, hashSet, "com.google.android.c2dm.intent.RECEIVE");
            } catch (PackageManager.NameNotFoundException e) {
                throw new IllegalStateException("Could not get receivers for package " + packageName);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            throw new IllegalStateException("Application does not define permission " + str);
        }
    }

    private static void a(Context context, Set<String> set, String str) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        Intent intent = new Intent(str);
        intent.setPackage(packageName);
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 32);
        if (queryBroadcastReceivers.isEmpty()) {
            throw new IllegalStateException("No receivers for action " + str);
        }
        if (Log.isLoggable("GCMRegistrar", 2)) {
            Log.v("GCMRegistrar", "Found " + queryBroadcastReceivers.size() + " receivers for action " + str);
        }
        Iterator<ResolveInfo> it = queryBroadcastReceivers.iterator();
        while (it.hasNext()) {
            String str2 = it.next().activityInfo.name;
            if (!set.contains(str2)) {
                throw new IllegalStateException("Receiver " + str2 + " is not set with permission com.google.android.c2dm.permission.SEND");
            }
        }
    }

    public static void a(Context context, String... strArr) {
        i(context);
        b(context, strArr);
    }

    public static void b(Context context, String... strArr) {
        String a2 = a(strArr);
        Log.v("GCMRegistrar", "Registering app " + context.getPackageName() + " of senders " + a2);
        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gsf");
        intent.putExtra("app", PendingIntent.getBroadcast(context, 0, new Intent(), 0));
        intent.putExtra("sender", a2);
        context.startService(intent);
    }

    public static String a(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        StringBuilder sb = new StringBuilder(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            sb.append(',').append(strArr[i]);
        }
        return sb.toString();
    }

    public static void c(Context context) {
        i(context);
        d(context);
    }

    public static void d(Context context) {
        Log.v("GCMRegistrar", "Unregistering app " + context.getPackageName());
        Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
        intent.setPackage("com.google.android.gsf");
        intent.putExtra("app", PendingIntent.getBroadcast(context, 0, new Intent(), 0));
        context.startService(intent);
    }

    public static synchronized void e(Context context) {
        synchronized (cr.class) {
            if (a == null) {
                if (b == null) {
                    Log.e("GCMRegistrar", "internal error: retry receiver class not set yet");
                    a = new GCMBroadcastReceiver();
                } else {
                    try {
                        a = (GCMBroadcastReceiver) Class.forName(b).newInstance();
                    } catch (Exception e) {
                        Log.e("GCMRegistrar", "Could not create instance of " + b + ". Using " + GCMBroadcastReceiver.class.getName() + " directly.");
                        a = new GCMBroadcastReceiver();
                    }
                }
                String packageName = context.getPackageName();
                IntentFilter intentFilter = new IntentFilter("com.google.android.gcm.intent.RETRY");
                intentFilter.addCategory(packageName);
                Log.v("GCMRegistrar", "Registering receiver");
                context.registerReceiver(a, intentFilter, packageName + ".permission.C2D_MESSAGE", null);
            }
        }
    }

    public static void a(String str) {
        Log.v("GCMRegistrar", "Setting the name of retry receiver class to " + str);
        b = str;
    }

    public static String f(Context context) {
        SharedPreferences l = l(context);
        String string = l.getString("regId", "");
        int i = l.getInt("appVersion", ExploreByTouchHelper.INVALID_ID);
        int k = k(context);
        if (i != Integer.MIN_VALUE && i != k) {
            Log.v("GCMRegistrar", "App version changed from " + i + " to " + k + "; resetting registration id");
            h(context);
            return "";
        }
        return string;
    }

    public static boolean g(Context context) {
        return f(context).length() > 0;
    }

    public static String h(Context context) {
        return a(context, "");
    }

    public static String a(Context context, String str) {
        SharedPreferences l = l(context);
        String string = l.getString("regId", "");
        int k = k(context);
        Log.v("GCMRegistrar", "Saving regId on app version " + k);
        SharedPreferences.Editor edit = l.edit();
        edit.putString("regId", str);
        edit.putInt("appVersion", k);
        edit.commit();
        return string;
    }

    private static int k(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Coult not get package name: " + e);
        }
    }

    public static void i(Context context) {
        Log.d("GCMRegistrar", "resetting backoff for " + context.getPackageName());
        a(context, Constants.YOZIO_SMS_SEND_LIMIT);
    }

    public static int j(Context context) {
        return l(context).getInt("backoff_ms", Constants.YOZIO_SMS_SEND_LIMIT);
    }

    public static void a(Context context, int i) {
        SharedPreferences.Editor edit = l(context).edit();
        edit.putInt("backoff_ms", i);
        edit.commit();
    }

    private static SharedPreferences l(Context context) {
        return context.getSharedPreferences("com.google.android.gcm", 0);
    }
}
