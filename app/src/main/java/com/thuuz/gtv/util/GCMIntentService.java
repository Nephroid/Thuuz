package com.thuuz.gtv.util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import com.facebook.internal.ServerProtocol;
import com.google.android.gcm.GCMBaseIntentService;
import com.thuuz.gtv.App;
import com.thuuz.gtv.ThuuzForegroundService;
import defpackage.bqv;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class GCMIntentService extends GCMBaseIntentService {
    private static final Map<String, String> a;
    private static PowerManager.WakeLock b;
    private static final Object c = GCMBaseIntentService.class;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("away", "away");
        hashMap.put("home", "home");
        hashMap.put("at_id", "away_team_id");
        hashMap.put("ht_id", "home_team_id");
        hashMap.put("id", "id");
        hashMap.put("status", "status");
        hashMap.put("rating", "rating");
        hashMap.put("gex", "gex");
        hashMap.put("splg", "splg");
        hashMap.put("tsr", "gex_teaser");
        hashMap.put("rnks", "rankings");
        hashMap.put("scrs", "scores");
        hashMap.put("type", "type");
        hashMap.put("json", "json");
        hashMap.put("msg", "msg");
        hashMap.put("sender", "sender");
        hashMap.put("gid", "gid");
        hashMap.put("msg_id", "msg_id");
        hashMap.put("zone", "zone");
        a = hashMap;
    }

    public App a() {
        return (App) getApplication();
    }

    public Logger b() {
        Logger logger = Logger.getLogger(getClass().getSimpleName() + ":thuuz");
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    public GCMIntentService() {
        super("606114992759");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(Context context, Intent intent, String str) {
        synchronized (c) {
            if (b == null) {
                b = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "GCM_LIB");
            }
        }
        b.acquire();
        intent.setClassName(context, str);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
        context.startService(intent);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        try {
            super.onCreate();
        } catch (Throwable th) {
            b().warning("cannot create gcm service: " + th);
        }
    }

    @Override // com.google.android.gcm.GCMBaseIntentService
    public void a(Context context, Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("zone");
            if (!a(stringExtra)) {
                b().warning("rejecting alert from zone: " + stringExtra);
            } else if (intent.getStringExtra("type") != null) {
                Intent intent2 = new Intent(context, (Class<?>) ThuuzForegroundService.class);
                intent2.setAction("TYPED_ALERT");
                a(intent, intent2);
                b(context, intent2);
            } else {
                Intent intent3 = new Intent(context, (Class<?>) ThuuzForegroundService.class);
                intent3.setAction("com.thuuz.game_alert");
                a(intent, intent3);
                b(context, intent3);
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    private boolean a(String str) {
        if (str != null) {
            switch (ServerChoice.l) {
                case SERVER_TYPE_DEV:
                case SERVER_TYPE_STAGE:
                case SERVER_TYPE_PROD:
                    return ServerChoice.l.name().toLowerCase().endsWith(str);
                default:
                    return "local".equals(str);
            }
        }
        return true;
    }

    private void a(Intent intent, Intent intent2) {
        for (String str : a.keySet()) {
            try {
                String str2 = a.get(str);
                String stringExtra = intent.getStringExtra(str);
                if (stringExtra != null) {
                    intent2.putExtra(str2, stringExtra);
                }
            } catch (Throwable th) {
                b().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    @Override // com.google.android.gcm.GCMBaseIntentService
    public void c(Context context, String str) {
        try {
            b().info("Google push registered: " + str);
            a().c(str);
            a().aN();
            Intent intent = new Intent(context, (Class<?>) ThuuzForegroundService.class);
            intent.setAction("com.thuuz.register_push");
            b(context, intent);
        } catch (Throwable th) {
            b().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gcm.GCMBaseIntentService
    public void b(Context context, String str) {
        try {
            b().info("gcm registration failed: " + str);
        } catch (Throwable th) {
            b().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gcm.GCMBaseIntentService
    public void d(Context context, String str) {
        try {
            b().info("Google push unregistered");
            a().g("gcm_reg");
            bqv.a();
            if (a().bF()) {
                a().b("need_google_reg", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            } else {
                a().g("need_google_reg");
            }
            Intent intent = new Intent(context, (Class<?>) ThuuzForegroundService.class);
            intent.setAction("com.thuuz.unregister_push");
            b(context, intent);
        } catch (Throwable th) {
            b().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public void onStart(Intent intent, int i) {
        try {
            super.onStart(intent, i);
        } catch (Throwable th) {
            b().log(Level.SEVERE, "caught GCM superclass error: ", th);
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            return super.onStartCommand(intent, i, i2);
        } catch (Throwable th) {
            b().log(Level.SEVERE, "caught GCM superclass error", th);
            return 2;
        }
    }

    private void b(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }
}
