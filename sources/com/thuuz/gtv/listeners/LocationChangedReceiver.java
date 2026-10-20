package com.thuuz.gtv.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class LocationChangedReceiver extends BroadcastReceiver {
    private final IService a;

    public LocationChangedReceiver(IService iService) {
        this.a = iService;
    }

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
            a().info("handling loc change");
            this.a.w().g();
            this.a.m().d();
            if (this.a.m().ax().a() != null) {
                this.a.w().b();
            }
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Problem handling received loc-change broadcast: ", th);
        }
    }
}
