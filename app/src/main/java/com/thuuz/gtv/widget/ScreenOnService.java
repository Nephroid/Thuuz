package com.thuuz.gtv.widget;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import com.facebook.AppEventsConstants;
import com.thuuz.gtv.App;
import com.thuuz.gtv.ThuuzService;
import defpackage.bbp;
import defpackage.bzy;
import defpackage.cak;
import defpackage.ccf;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class ScreenOnService extends Service {
    private static String b = ScreenOnService.class.getSimpleName() + ":thuuz";
    private ccf a;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            a().info("creating ScreenOnService");
            try {
                this.a = new ccf(this);
                registerReceiver(this.a, new IntentFilter("android.intent.action.SCREEN_ON"));
                a().info("widget registered screen-on receiver");
                Intent intent = new Intent(this, getClass());
                intent.setAction("kill");
                intent.putExtra("from_notification", 1);
                PendingIntent service = PendingIntent.getService(this, 0, intent, 0);
                if (Build.VERSION.SDK_INT < 21) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                    builder.setWhen(System.currentTimeMillis()).setAutoCancel(true).setOnlyAlertOnce(true).setSmallIcon(bbp.notification_icon_screen_on).setContentIntent(service).setSound(null).setLights(0, 0, 0).setVibrate(new long[0]).setContentTitle("Thuuz Widget Updater");
                    startForeground(88, builder.build());
                }
                cak.a(new File(getCacheDir(), "screen_on_serv"), AppEventsConstants.EVENT_PARAM_VALUE_YES);
            } catch (Throwable th) {
                App app = (App) getApplication();
                if (app != null && !app.bQ()) {
                    a().log(Level.SEVERE, "Cannot create screen-on listener: ", th);
                }
                b();
                stopSelf();
            }
        } catch (Throwable th2) {
            a().log(Level.SEVERE, "Cannot register for screen on: ", th2);
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Intent intent = new Intent(this, (Class<?>) ThuuzService.class);
        intent.setAction("wake");
        startService(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        try {
            if (this.a != null) {
                unregisterReceiver(this.a);
                a().info("widget unregistered screen-on receiver");
            }
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Cannot unregister for screen on: ", th);
            b();
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            try {
                if ("kill".equals(intent.getAction())) {
                    a().warning("screenOnService got kill from user manipulating notification");
                    Intent intent2 = new Intent(this, (Class<?>) ThuuzService.class);
                    intent2.setAction("widget_notif_killed");
                    startService(intent2);
                    stopSelf();
                    return 2;
                }
                return 2;
            } catch (Throwable th) {
                a().log(Level.SEVERE, "widget cannot handle start in ScreenOn: ", th);
                b();
                return 2;
            }
        }
        return 2;
    }

    public static void a(File file) {
        try {
            cak.a(new File(file, "screen_on_serv"), "");
        } catch (Throwable th) {
            bzy.getStaticLogger(ScreenOnService.class).log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public Logger a() {
        Logger logger = Logger.getLogger(b);
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }
}
