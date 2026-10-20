package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* loaded from: classes.dex */
public class akr extends oa<akq> {
    protected oh<akq> a;
    private final ViewGroup b;
    private final Context c;
    private final GoogleMapOptions d;

    public akr(ViewGroup viewGroup, Context context, GoogleMapOptions googleMapOptions) {
        this.b = viewGroup;
        this.c = context;
        this.d = googleMapOptions;
    }

    public void b() {
        if (this.a == null || a() != null) {
            return;
        }
        try {
            this.a.a(new akq(this.b, aof.a(this.c).a(og.a(this.c), this.d)));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        } catch (GooglePlayServicesNotAvailableException e2) {
        }
    }
}
