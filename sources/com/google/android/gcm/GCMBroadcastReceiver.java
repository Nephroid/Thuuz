package com.google.android.gcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import defpackage.cr;

/* loaded from: classes.dex */
public class GCMBroadcastReceiver extends BroadcastReceiver {
    private static boolean a = false;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.v("GCMBroadcastReceiver", "onReceive: " + intent.getAction());
        if (!a) {
            a = true;
            String name = getClass().getName();
            if (!name.equals(GCMBroadcastReceiver.class.getName())) {
                cr.a(name);
            }
        }
        String a2 = a(context);
        Log.v("GCMBroadcastReceiver", "GCM IntentService class: " + a2);
        GCMBaseIntentService.a(context, intent, a2);
        setResult(-1, null, null);
    }

    protected String a(Context context) {
        return b(context);
    }

    static final String b(Context context) {
        return context.getPackageName() + ".GCMIntentService";
    }
}
