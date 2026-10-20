package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;

/* loaded from: classes.dex */
public final class dj {
    final Context a;
    hd b;
    ajn c;
    boolean d;

    public dj(Context context) {
        kb.a(context);
        this.a = context;
        this.d = false;
    }

    static ajn a(Context context, hd hdVar) {
        try {
            return ajo.a(hdVar.a());
        } catch (InterruptedException e) {
            throw new IOException("Interrupted exception");
        }
    }

    static hd a(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            try {
                hb.b(context);
                hd hdVar = new hd();
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                if (context.bindService(intent, hdVar, 1)) {
                    return hdVar;
                }
                throw new IOException("Connection failure");
            } catch (GooglePlayServicesNotAvailableException e) {
                throw new IOException(e);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
    }

    public static dk b(Context context) {
        dj djVar = new dj(context);
        try {
            djVar.a();
            return djVar.b();
        } finally {
            djVar.c();
        }
    }

    public void a() {
        kb.c("Calling this from your main thread can lead to deadlock");
        if (this.d) {
            c();
        }
        this.b = a(this.a);
        this.c = a(this.a, this.b);
        this.d = true;
    }

    public dk b() {
        kb.c("Calling this from your main thread can lead to deadlock");
        kb.a(this.b);
        kb.a(this.c);
        if (!this.d) {
            throw new IOException("AdvertisingIdService is not connected.");
        }
        try {
            return new dk(this.c.a(), this.c.a(true));
        } catch (RemoteException e) {
            Log.i("AdvertisingIdClient", "GMS remote exception ", e);
            throw new IOException("Remote exception");
        }
    }

    public void c() {
        kb.c("Calling this from your main thread can lead to deadlock");
        if (this.a == null || this.b == null) {
            return;
        }
        try {
            if (this.d) {
                this.a.unbindService(this.b);
            }
        } catch (IllegalArgumentException e) {
            Log.i("AdvertisingIdClient", "AdvertisingIdClient unbindService failed.", e);
        }
        this.d = false;
        this.c = null;
        this.b = null;
    }
}
