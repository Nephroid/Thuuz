package com.thuuz.gtv.widget;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.SparseArray;
import android.widget.RemoteViewsService;
import com.thuuz.gtv.App;
import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.data.GameData;
import defpackage.cak;
import defpackage.ccg;
import defpackage.cch;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class WidgetRemoteViewService extends RemoteViewsService {
    private static SparseArray<GameData> a = new SparseArray<>();
    private static List<Integer> b = new ArrayList();
    private static boolean c;
    private static Date d;
    private static boolean e;
    private Handler f;
    private String g = getClass().getSimpleName() + ":thuuz";

    @Override // android.app.Service
    public void onCreate() {
        try {
            super.onCreate();
            if (getPackageManager() != null) {
                this.f = new Handler(Looper.getMainLooper());
            }
        } catch (Throwable th) {
            a().log(Level.SEVERE, "widget service onCreate: ", th);
            j();
        }
    }

    public static File a(File file) {
        if (file == null) {
            return null;
        }
        return new File(file, "widget_data.json");
    }

    @Override // android.widget.RemoteViewsService
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new cch(this, getApplicationContext(), intent);
    }

    public Logger a() {
        Logger logger = Logger.getLogger(this.g);
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    public App b() {
        return (App) getApplication();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (this.f != null) {
            this.f.post(new ccg(this, str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        boolean z = false;
        try {
            File file = new File(getCacheDir(), "man_refresh");
            if (cak.a(file).length() <= 0) {
                return false;
            }
            z = true;
            file.delete();
            return true;
        } catch (Throwable th) {
            a().log(Level.SEVERE, "Cannot handle: ", th);
            j();
            return z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        PendingIntent a2 = a(b());
        if (a2 != null) {
            AlarmManager alarmManager = (AlarmManager) b().getSystemService("alarm");
            alarmManager.cancel(a2);
            long j = 900000;
            if (z) {
                j = 60000;
            }
            alarmManager.setInexactRepeating(3, SystemClock.elapsedRealtime() + j, j, a2);
        }
    }

    public static PendingIntent a(Context context) {
        if (AppWidgetManager.getInstance(context) == null) {
            return null;
        }
        Intent intent = new Intent(context, (Class<?>) WidgetProvider.class);
        intent.setAction("com.thuuz.widget.Refresh");
        intent.putExtra("alarm", 1);
        intent.setData(Uri.parse(intent.toUri(1)));
        return PendingIntent.getBroadcast(context, 0, intent, 134217728);
    }

    public static Date c() {
        return d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        Intent intent = new Intent(this, (Class<?>) ThuuzService.class);
        intent.setAction("wake");
        startService(intent);
    }
}
