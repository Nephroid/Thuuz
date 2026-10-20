package com.thuuz.gtv.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.thuuz.gtv.App;
import com.thuuz.gtv.ThuuzService;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class NetworkChangedReceiver extends BroadcastReceiver {
    public Logger a() {
        Logger logger = Logger.getLogger(getClass().getSimpleName() + ":thuuz");
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            a().warning("handling network change: " + intent);
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                Intent intent2 = new Intent(context, (Class<?>) ThuuzService.class);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    if (activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 9) {
                        intent2.setAction("ethernet_conn");
                    } else if (!activeNetworkInfo.isConnected()) {
                        intent2.setAction("network_disconn");
                    }
                } else {
                    intent2.setAction("network_disconn");
                }
                context.startService(intent2);
            }
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Problem handling network-change broadcast: ", th);
        }
    }

    public boolean a(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 9;
    }
}
