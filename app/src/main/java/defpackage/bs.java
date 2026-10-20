package defpackage;

import android.content.Context;
import android.util.Log;
import com.appsflyer.AppsFlyerLib;
import java.lang.ref.WeakReference;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
public class bs implements Runnable {
    private WeakReference<Context> a;

    public bs(Context context) {
        this.a = null;
        this.a = new WeakReference<>(context);
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        ScheduledExecutorService scheduledExecutorService;
        boolean g;
        z = AppsFlyerLib.e;
        if (!z) {
            long unused = AppsFlyerLib.f = System.currentTimeMillis();
            if (this.a != null) {
                boolean unused2 = AppsFlyerLib.e = true;
                try {
                    String a = AppsFlyerLib.a("AppsFlyerKey");
                    synchronized (this.a) {
                        for (ce ceVar : cd.a().b(this.a.get())) {
                            g = AppsFlyerLib.g(this.a.get());
                            if (g) {
                                Log.i("AppsFlyer_3.0", "resending request: " + ceVar.c());
                            }
                            try {
                                AppsFlyerLib.b(ceVar.c() + "&isCachedRequest=true&timeincache=" + Long.toString((System.currentTimeMillis() - Long.parseLong(ceVar.d(), 10)) / 1000), ceVar.b(), a, this.a, ceVar.d(), "AppsFlyer_3.0", false);
                            } catch (Exception e) {
                                Log.i("AppsFlyer_3.0", "Failed to resend cached request");
                            }
                        }
                    }
                } catch (Exception e2) {
                    Log.e("AppsFlyer_3.0", "failed to check cache.");
                } finally {
                    boolean unused3 = AppsFlyerLib.e = false;
                }
                scheduledExecutorService = AppsFlyerLib.g;
                scheduledExecutorService.shutdown();
                ScheduledExecutorService unused4 = AppsFlyerLib.g = null;
            }
        }
    }
}
