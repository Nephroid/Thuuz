package defpackage;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.dv;

@zy
/* loaded from: classes.dex */
public class yf extends yv implements ServiceConnection {
    private final Activity a;
    private final yj b;
    private Context c;
    private zd d;
    private yo e;
    private yb f;
    private yh g;
    private ym h;
    private String i = null;

    public yf(Activity activity) {
        this.a = activity;
        this.b = yj.a(this.a.getApplicationContext());
    }

    public static void a(Context context, boolean z, dv dvVar) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.purchase.InAppPurchaseActivity");
        intent.putExtra("com.google.android.gms.ads.internal.purchase.useClientJar", z);
        dv.a(intent, dvVar);
        context.startActivity(intent);
    }

    private void a(String str, boolean z, int i, Intent intent) {
        try {
            this.d.a(new yi(this.c, str, z, i, intent, this.g));
        } catch (RemoteException e) {
            adn.e("Fail to invoke PlayStorePurchaseListener.");
        }
    }

    @Override // defpackage.yu
    public void a() {
        dv a = dv.a(this.a.getIntent());
        this.d = a.b;
        this.h = a.c;
        this.e = a.d;
        this.f = new yb(this.a.getApplicationContext());
        this.c = a.e;
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        this.a.bindService(intent, this, 1);
    }

    @Override // defpackage.yu
    public void a(int i, int i2, Intent intent) {
        try {
            if (i == 1001) {
                int a = yl.a(intent);
                if (i2 != -1 || a != 0) {
                    this.b.a(this.g);
                    a(this.e.a(), false, i2, intent);
                } else if (this.h.a(this.i, i2, intent)) {
                    a(this.e.a(), true, i2, intent);
                } else {
                    a(this.e.a(), false, i2, intent);
                }
                this.e.c(a);
            }
        } catch (RemoteException e) {
            adn.e("Fail to process purchase result.");
        } finally {
            this.i = null;
            this.a.finish();
        }
    }

    @Override // defpackage.yu
    public void b() {
        this.a.unbindService(this);
        this.f.a();
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f.a(iBinder);
        try {
            this.i = this.h.a();
            Bundle a = this.f.a(this.a.getPackageName(), this.e.a(), this.i);
            PendingIntent pendingIntent = (PendingIntent) a.getParcelable("BUY_INTENT");
            if (pendingIntent == null) {
                int a2 = yl.a(a);
                this.e.c(a2);
                a(this.e.a(), false, a2, null);
                this.a.finish();
            } else {
                this.g = new yh(this.e.a(), this.i);
                this.b.b(this.g);
                Integer num = 0;
                Integer num2 = 0;
                Integer num3 = 0;
                this.a.startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), num.intValue(), num2.intValue(), num3.intValue());
            }
        } catch (IntentSender.SendIntentException | RemoteException e) {
            adn.d("Error when connecting in-app billing service", e);
            this.a.finish();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        adn.c("In-app billing service disconnected.");
        this.f.a();
    }
}
