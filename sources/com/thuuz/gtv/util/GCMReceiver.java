package com.thuuz.gtv.util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gcm.GCMBroadcastReceiver;
import defpackage.cr;

/* loaded from: classes.dex */
public class GCMReceiver extends GCMBroadcastReceiver {
    private static boolean a = false;

    @Override // com.google.android.gcm.GCMBroadcastReceiver
    protected String a(Context context) {
        return GCMIntentService.class.getName();
    }

    @Override // com.google.android.gcm.GCMBroadcastReceiver, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            Log.v("GCMReceiver", "onReceive: " + intent.getAction());
            if (!a) {
                a = true;
                String name = getClass().getName();
                if (!name.equals(GCMBroadcastReceiver.class.getName())) {
                    cr.a(name);
                }
            }
            String a2 = a(context);
            Log.v("GCMReceiver", "GCM IntentService class: " + a2);
            GCMIntentService.b(context, intent, a2);
            setResult(-1, (String) null, (Bundle) null);
            return;
        }
        super.onReceive(context, intent);
    }
}
