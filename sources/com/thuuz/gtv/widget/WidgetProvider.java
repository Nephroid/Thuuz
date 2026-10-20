package com.thuuz.gtv.widget;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import android.text.format.DateFormat;
import android.widget.RemoteViews;
import com.facebook.AppEventsConstants;
import com.thuuz.gtv.App;
import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.activity.nobar.InitialActivity;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.cak;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class WidgetProvider extends AppWidgetProvider {
    private String a = getClass().getSimpleName() + ":thuuz";

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int i = 0;
        try {
            super.onReceive(context, intent);
            String str = "";
            if (intent != null) {
                str = intent.getAction();
                i = intent.getIntExtra("appWidgetId", 0);
            }
            a(str, i);
            if ("com.thuuz.widget.Refresh".equals(str)) {
                d(context, intent);
                return;
            }
            if ("com.thuuz.widget.Item".equals(str)) {
                e(context, intent);
                return;
            }
            if ("com.thuuz.widget.Thuuz".equals(str)) {
                c(context, intent);
            } else if ("com.thuuz.widget.Title".equals(str)) {
                a(context, intent);
            } else if ("com.thuuz.widget.Fail".equals(str)) {
                b(context, intent);
            }
        } catch (Throwable th) {
            a().log(Level.SEVERE, "problem in widget receive: ", th);
            e(context);
        }
    }

    private void a(String str, long j) {
        if (a().isLoggable(Level.INFO)) {
            StringBuilder sb = new StringBuilder("widget onReceive");
            if (j != 0) {
                sb.append(", id: ");
                sb.append(j);
            }
            sb.append(", action: ");
            sb.append(str);
            a().info(sb.toString());
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
        try {
            super.onEnabled(context);
            a().info("widget got enabled");
            Intent intent = new Intent(context, (Class<?>) ThuuzService.class);
            intent.setAction("widget_enabled");
            context.startService(intent);
            d(context, null);
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Cannot handle: ", th);
            e(context);
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDeleted(Context context, int[] iArr) {
        try {
            a().info("widget deleted");
            Intent intent = new Intent(context, (Class<?>) ThuuzService.class);
            intent.setAction("widget_deleted");
            context.startService(intent);
            super.onDeleted(context, iArr);
            ScreenOnService.a(context.getCacheDir());
            c(context);
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Cannot handle: ", th);
            e(context);
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(Context context) {
        try {
            super.onDisabled(context);
            c(context);
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Cannot handle: ", th);
            e(context);
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    @SuppressLint({"NewApi"})
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        try {
            for (int i : iArr) {
                if (i != 0) {
                    appWidgetManager.updateAppWidget(i, a(context, iArr, i, "", false));
                }
            }
            if (!((PowerManager) context.getSystemService("power")).isScreenOn() && a(context)) {
                a().info("skipping widget update because screen off and prepared for screen on");
                return;
            }
            if (a().isLoggable(Level.INFO)) {
                a().info("widget updating for num widgets: " + iArr.length);
            }
            for (int i2 : iArr) {
                if (i2 != 0) {
                    appWidgetManager.notifyAppWidgetViewDataChanged(i2, bbq.widget_list);
                }
            }
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Cannot update widget: ", th);
            e(context);
        }
    }

    private boolean a(Context context) {
        return cak.a(new File(context.getCacheDir(), "screen_on_serv")).length() > 0;
    }

    private void a(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        if (appWidgetManager != null) {
            int intExtra = intent.getIntExtra("appWidgetId", 0);
            String stringExtra = intent.getStringExtra("com.thuuz.widget.Title");
            if (intExtra == 0) {
                a(context, appWidgetManager, false, stringExtra);
            } else {
                a(context, appWidgetManager, false, stringExtra, intExtra);
            }
        }
    }

    private void b(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        if (appWidgetManager != null) {
            int intExtra = intent.getIntExtra("appWidgetId", 0);
            if (intExtra == 0) {
                a(context, appWidgetManager, false, null);
            } else {
                a(context, appWidgetManager, false, (String) null, intExtra);
            }
        }
    }

    private void c(Context context, Intent intent) {
        if (intent != null) {
            Intent intent2 = new Intent(context, (Class<?>) InitialActivity.class);
            intent2.setFlags(268435456);
            context.startActivity(intent2);
        }
    }

    @SuppressLint({"NewApi"})
    private void d(Context context, Intent intent) {
        if (intent != null && intent.hasExtra("alarm")) {
            if (!((PowerManager) context.getSystemService("power")).isScreenOn()) {
                if (a().isLoggable(Level.INFO)) {
                    a().info("skipping widget refresh because screen off; starting ScreenOnService");
                }
                Intent intent2 = new Intent(context, (Class<?>) ScreenOnService.class);
                intent2.setAction("watch");
                context.startService(intent2);
                if (a(context)) {
                    if (a().isLoggable(Level.INFO)) {
                        a().info("stopping alarm because ScreenOnService is running");
                    }
                    d(context);
                    return;
                }
                return;
            }
            if (!a().isLoggable(Level.INFO)) {
                a().info("updating from recurring alarm");
            }
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        if (appWidgetManager != null) {
            a(context, appWidgetManager, true, "");
            if (intent != null && intent.hasExtra("com.thuuz.widget.Refresh")) {
                b(context);
            }
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetManager.getAppWidgetIds(new ComponentName(context, (Class<?>) WidgetProvider.class)), bbq.widget_list);
        }
    }

    private void a(Context context, AppWidgetManager appWidgetManager, boolean z, String str) {
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, (Class<?>) WidgetProvider.class));
        for (int i : appWidgetIds) {
            if (i != 0) {
                a(context, appWidgetManager, appWidgetIds, z, str, i);
            }
        }
    }

    private void a(Context context, AppWidgetManager appWidgetManager, boolean z, String str, int i) {
        if (i != 0) {
            a(context, appWidgetManager, appWidgetManager.getAppWidgetIds(new ComponentName(context, (Class<?>) WidgetProvider.class)), z, str, i);
        }
    }

    private void a(Context context, AppWidgetManager appWidgetManager, int[] iArr, boolean z, String str, int i) {
        if (i != 0) {
            appWidgetManager.updateAppWidget(i, a(context, iArr, i, str, z));
        }
    }

    private void b(Context context) {
        try {
            cak.a(new File(context.getCacheDir(), "man_refresh"), AppEventsConstants.EVENT_PARAM_VALUE_YES);
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Cannot handle manual refresh file writing: ", th);
            e(context);
        }
    }

    private void e(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getExtras() != null) {
                int intExtra = intent.getIntExtra("id", -1);
                a().info("widget game click: " + intExtra);
                Intent intent2 = new Intent(context, (Class<?>) ThuuzService.class);
                intent2.setAction("read_widget");
                context.startService(intent2);
                Intent intent3 = new Intent(context, (Class<?>) InitialActivity.class);
                intent3.putExtra("game", intExtra);
                intent3.putExtra("from_widget", 1);
                intent3.setFlags(268435456);
                context.startActivity(intent3);
                return;
            }
            a().severe("widget item click without extras");
        }
    }

    @SuppressLint({"NewApi"})
    private RemoteViews a(Context context, int[] iArr, int i, String str, boolean z) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), bbr.widget_layout);
        try {
            Intent intent = new Intent(context, (Class<?>) WidgetRemoteViewService.class);
            intent.putExtra("appWidgetId", i);
            intent.setData(Uri.parse(intent.toUri(1)));
            remoteViews.setRemoteAdapter(i, bbq.widget_list, intent);
            remoteViews.setEmptyView(bbq.widget_list, bbq.empty_view);
            remoteViews.setOnClickPendingIntent(bbq.refresh, a(context, iArr, i));
            remoteViews.setOnClickPendingIntent(bbq.thuuz_logo, b(context, iArr, i));
            remoteViews.setOnClickPendingIntent(bbq.progress, a(context, iArr, i));
            Intent intent2 = new Intent(context, (Class<?>) WidgetProvider.class);
            intent2.setAction("com.thuuz.widget.Item");
            intent2.putExtra("appWidgetIds", iArr);
            intent2.putExtra("appWidgetId", i);
            intent.setData(Uri.parse(intent.toUri(1)));
            remoteViews.setPendingIntentTemplate(bbq.widget_list, PendingIntent.getBroadcast(context, 2, intent2, 134217728));
            if (str != null && str.length() > 0) {
                remoteViews.setTextViewText(bbq.widget_title, str);
            }
            if (WidgetRemoteViewService.c() != null) {
                remoteViews.setTextViewText(bbq.fetch_date, "updated " + DateFormat.getTimeFormat(context).format(WidgetRemoteViewService.c()));
                remoteViews.setViewVisibility(bbq.fetch_date, 0);
            } else {
                remoteViews.setViewVisibility(bbq.fetch_date, 8);
            }
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Cannot handle widget setup: ", th);
            e(context);
        }
        return remoteViews;
    }

    public PendingIntent a(Context context, int[] iArr, int i) {
        Intent intent = new Intent(context, (Class<?>) WidgetProvider.class);
        intent.setAction("com.thuuz.widget.Refresh");
        intent.putExtra("appWidgetIds", iArr);
        intent.putExtra("appWidgetId", i);
        intent.putExtra("com.thuuz.widget.Refresh", 1);
        return PendingIntent.getBroadcast(context, 1, intent, 134217728);
    }

    public PendingIntent b(Context context, int[] iArr, int i) {
        Intent intent = new Intent(context, (Class<?>) WidgetProvider.class);
        intent.setAction("com.thuuz.widget.Thuuz");
        intent.putExtra("appWidgetIds", iArr);
        intent.putExtra("appWidgetId", i);
        return PendingIntent.getBroadcast(context, 0, intent, 134217728);
    }

    public Logger a() {
        Logger logger = Logger.getLogger(this.a);
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    private void c(Context context) {
        try {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            if (appWidgetManager != null) {
                if (!(appWidgetManager.getAppWidgetIds(new ComponentName(context, (Class<?>) WidgetProvider.class)).length > 0)) {
                    d(context);
                }
            }
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Cannot cancel recurring alarm: ", th);
            e(context);
        }
    }

    private void d(Context context) {
        try {
            a().info("cancelling the recurring alarm for refreshing");
            ((AlarmManager) context.getSystemService("alarm")).cancel(WidgetRemoteViewService.a(context));
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Cannot cancel recurring alarm: ", th);
            e(context);
        }
    }

    private void e(Context context) {
        try {
            Intent intent = new Intent(context, (Class<?>) ThuuzService.class);
            intent.setAction("wake");
            context.startService(intent);
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
