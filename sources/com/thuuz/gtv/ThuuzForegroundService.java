package com.thuuz.gtv;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ThuuzForegroundService extends ThuuzService {
    @Override // com.thuuz.gtv.ThuuzService, android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            if (D() != null) {
                Notification.Builder builder = new Notification.Builder(this, "default");
                builder.setAutoCancel(true).setOnlyAlertOnce(true).setContentTitle("Starting Thuuz background task...").setSmallIcon(m().bA());
                startForeground(99, builder.build());
            } else {
                stopForeground(true);
                stopSelf();
            }
        }
    }

    @Override // com.thuuz.gtv.ThuuzService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String action = intent.getAction();
        HashMap<String, String> hashMap = new HashMap<String, String>() { // from class: com.thuuz.gtv.ThuuzForegroundService.1
            {
                put("com.thuuz.game_alert", "Rendering alert...");
                put("TYPED_ALERT", "Rendering alert...");
                put("com.thuuz.unregister_push", "Unregistering from alerts...");
                put("com.thuuz.register_push", "Registering for alerts...");
            }
        };
        if (Build.VERSION.SDK_INT >= 26) {
            if (D() != null) {
                Notification.Builder builder = new Notification.Builder(this, "default");
                builder.setAutoCancel(true).setOnlyAlertOnce(true).setContentTitle(hashMap.getOrDefault(action, "Starting Thuuz background task...")).setSmallIcon(m().bA());
                startForeground(99, builder.build());
            } else {
                stopForeground(true);
                stopSelf();
            }
        }
        return super.onStartCommand(intent, i, i2);
    }
}
