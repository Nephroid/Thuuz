package com.thuuz.gtv.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.App;
import com.thuuz.gtv.ThuuzService;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class OnSelectMediaDeviceReceiver extends BroadcastReceiver {
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
            a().info("got media device selected intent " + intent);
            Intent intent2 = new Intent(context, (Class<?>) ThuuzService.class);
            intent2.setAction("media_device_sel");
            context.startService(intent2);
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Error receiving selected media: ", th);
        }
    }
}
