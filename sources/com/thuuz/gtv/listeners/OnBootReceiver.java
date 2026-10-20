package com.thuuz.gtv.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.App;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class OnBootReceiver extends BroadcastReceiver {
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
            a().warning("handling boot: " + intent);
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Problem handling boot: ", th);
        }
    }
}
