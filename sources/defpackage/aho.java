package defpackage;

import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.RemoteException;
import java.util.HashMap;

/* loaded from: classes.dex */
public class aho {
    private final ahw<ahl> a;
    private final Context b;
    private ContentProviderClient c = null;
    private boolean d = false;
    private HashMap<Object, ahp> e = new HashMap<>();

    public aho(Context context, ahw<ahl> ahwVar) {
        this.b = context;
        this.a = ahwVar;
    }

    public Location a() {
        this.a.a();
        try {
            return this.a.c().a(this.b.getPackageName());
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public void a(boolean z) {
        this.a.a();
        this.a.c().a(z);
        this.d = z;
    }

    public void b() {
        try {
            synchronized (this.e) {
                for (ahp ahpVar : this.e.values()) {
                    if (ahpVar != null) {
                        this.a.c().a(ahpVar);
                    }
                }
                this.e.clear();
            }
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public void c() {
        if (this.d) {
            try {
                a(false);
            } catch (RemoteException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
