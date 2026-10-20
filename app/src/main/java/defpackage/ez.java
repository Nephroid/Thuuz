package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

/* loaded from: classes.dex */
final class ez implements ServiceConnection {
    final /* synthetic */ ey a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ez(ey eyVar) {
        this.a = eyVar;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        fb fbVar;
        Context context;
        gn.c("service connected, binder: " + iBinder);
        try {
            if ("com.google.android.gms.analytics.internal.IAnalyticsService".equals(iBinder.getInterfaceDescriptor())) {
                gn.c("bound to service");
                this.a.e = aeh.a(iBinder);
                this.a.g();
                return;
            }
        } catch (RemoteException e) {
        }
        try {
            context = this.a.d;
            context.unbindService(this);
        } catch (IllegalArgumentException e2) {
        }
        this.a.a = null;
        fbVar = this.a.c;
        fbVar.a(2, null);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        fa faVar;
        gn.c("service disconnected: " + componentName);
        this.a.a = null;
        faVar = this.a.b;
        faVar.e();
    }
}
