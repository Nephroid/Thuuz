package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import com.google.android.gms.internal.hb;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ey implements ex {
    private ServiceConnection a;
    private fa b;
    private fb c;
    private Context d;
    private aeg e;

    public ey(Context context, fa faVar, fb fbVar) {
        this.d = context;
        if (faVar == null) {
            throw new IllegalArgumentException("onConnectedListener cannot be null");
        }
        this.b = faVar;
        if (fbVar == null) {
            throw new IllegalArgumentException("onConnectionFailedListener cannot be null");
        }
        this.c = fbVar;
    }

    private aeg f() {
        d();
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        h();
    }

    private void h() {
        this.b.d();
    }

    @Override // defpackage.ex
    public void a() {
        try {
            f().a();
        } catch (RemoteException e) {
            gn.a("clear hits failed: " + e);
        }
    }

    @Override // defpackage.ex
    public void a(Map<String, String> map, long j, String str, List<hb> list) {
        try {
            f().a(map, j, str, list);
        } catch (RemoteException e) {
            gn.a("sendHit failed: " + e);
        }
    }

    @Override // defpackage.ex
    public void b() {
        Intent intent = new Intent("com.google.android.gms.analytics.service.START");
        intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
        intent.putExtra("app_package_name", this.d.getPackageName());
        if (this.a != null) {
            gn.a("Calling connect() while still connected, missing disconnect().");
            return;
        }
        this.a = new ez(this);
        boolean bindService = this.d.bindService(intent, this.a, 129);
        gn.c("connect: bindService returned " + bindService + " for " + intent);
        if (bindService) {
            return;
        }
        this.a = null;
        this.c.a(1, null);
    }

    @Override // defpackage.ex
    public void c() {
        this.e = null;
        if (this.a != null) {
            try {
                this.d.unbindService(this.a);
            } catch (IllegalArgumentException e) {
            } catch (IllegalStateException e2) {
            }
            this.a = null;
            this.b.e();
        }
    }

    protected void d() {
        if (!e()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public boolean e() {
        return this.e != null;
    }
}
