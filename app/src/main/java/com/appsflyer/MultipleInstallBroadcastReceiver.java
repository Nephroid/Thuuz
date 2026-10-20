package com.appsflyer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.util.Log;

/* loaded from: classes.dex */
public class MultipleInstallBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.i("AppsFlyer_3.0", "MultipleInstallBroadcastReceiver called");
        for (ResolveInfo resolveInfo : context.getPackageManager().queryBroadcastReceivers(new Intent("com.android.vending.INSTALL_REFERRER"), 0)) {
            String action = intent.getAction();
            if (resolveInfo.activityInfo.packageName.equals(context.getPackageName()) && "com.android.vending.INSTALL_REFERRER".equals(action) && !getClass().getName().equals(resolveInfo.activityInfo.name)) {
                Log.i("AppsFlyer_3.0", "trigger onReceive: class: " + resolveInfo.activityInfo.name);
                try {
                    ((BroadcastReceiver) Class.forName(resolveInfo.activityInfo.name).newInstance()).onReceive(context, intent);
                } catch (Throwable th) {
                    Log.e("AppsFlyer_3.0", "error in BroadcastReceiver " + resolveInfo.activityInfo.name, th);
                }
            }
        }
        new AppsFlyerLib().onReceive(context, intent);
    }
}
