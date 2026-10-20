package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

@zy
/* loaded from: classes.dex */
public final class yi extends zb implements ServiceConnection {
    private boolean a;
    private Context b;
    private int c;
    private Intent d;
    private yb e;
    private yh f;
    private String g;

    public yi(Context context, String str, boolean z, int i, Intent intent, yh yhVar) {
        this.a = false;
        this.g = str;
        this.c = i;
        this.d = intent;
        this.a = z;
        this.b = context;
        this.f = yhVar;
    }

    @Override // defpackage.za
    public boolean a() {
        return this.a;
    }

    @Override // defpackage.za
    public String b() {
        return this.g;
    }

    @Override // defpackage.za
    public Intent c() {
        return this.d;
    }

    @Override // defpackage.za
    public int d() {
        return this.c;
    }

    @Override // defpackage.za
    public void e() {
        int a = yl.a(this.d);
        if (this.c == -1 && a == 0) {
            this.e = new yb(this.b);
            Context context = this.b;
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            Context context2 = this.b;
            context.bindService(intent, this, 1);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        adn.c("In-app billing service connected.");
        this.e.a(iBinder);
        String b = yl.b(yl.b(this.d));
        if (b == null) {
            return;
        }
        if (this.e.a(this.b.getPackageName(), b) == 0) {
            yj.a(this.b).a(this.f);
        }
        this.b.unbindService(this);
        this.e.a();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        adn.c("In-app billing service disconnected.");
        this.e.a();
    }
}
