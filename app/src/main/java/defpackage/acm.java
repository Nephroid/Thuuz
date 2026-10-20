package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.internal.av;

@zy
/* loaded from: classes.dex */
public class acm {
    private final String b;
    private final Object a = new Object();
    private int c = 0;
    private long d = -1;
    private long e = -1;
    private int f = 0;
    private int g = -1;

    public acm(String str) {
        this.b = str;
    }

    public static boolean a(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, "android");
        if (identifier == 0) {
            adn.c("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), "com.google.android.gms.ads.AdActivity"), 0).theme) {
                return true;
            }
            adn.c("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            adn.e("Fail to fetch AdActivity theme");
            adn.c("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }

    public long a() {
        return this.e;
    }

    public Bundle a(Context context, String str) {
        Bundle bundle;
        synchronized (this.a) {
            bundle = new Bundle();
            bundle.putString("session_id", this.b);
            bundle.putLong("basets", this.e);
            bundle.putLong("currts", this.d);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.g);
            bundle.putInt("pclick", this.c);
            bundle.putInt("pimp", this.f);
            bundle.putBoolean("support_transparent_background", a(context));
        }
        return bundle;
    }

    public void a(av avVar, long j) {
        synchronized (this.a) {
            if (this.e == -1) {
                this.e = j;
                this.d = this.e;
            } else {
                this.d = j;
            }
            if (avVar.c == null || avVar.c.getInt("gw", 2) != 1) {
                this.g++;
            }
        }
    }

    public void b() {
        synchronized (this.a) {
            this.c++;
        }
    }

    public void c() {
        synchronized (this.a) {
            this.f++;
        }
    }
}
