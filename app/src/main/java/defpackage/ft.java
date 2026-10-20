package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* loaded from: classes.dex */
class ft extends BroadcastReceiver {
    static final String a = ft.class.getName();
    private final es b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ft(es esVar) {
        this.b = esVar;
    }

    public static void b(Context context) {
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(a, true);
        context.sendBroadcast(intent);
    }

    public void a(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
        intentFilter2.addCategory(context.getPackageName());
        context.registerReceiver(this, intentFilter2);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            this.b.a(intent.getBooleanExtra("noConnectivity", false) ? false : true);
        } else {
            if (!"com.google.analytics.RADIO_POWERED".equals(action) || intent.hasExtra(a)) {
                return;
            }
            this.b.b();
        }
    }
}
