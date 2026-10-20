package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* loaded from: classes.dex */
public class akt extends oa<aku> {
    protected oh<aku> a;
    private final ViewGroup b;
    private final Context c;
    private final StreetViewPanoramaOptions d;

    public akt(ViewGroup viewGroup, Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
        this.b = viewGroup;
        this.c = context;
        this.d = streetViewPanoramaOptions;
    }

    public void b() {
        if (this.a == null || a() != null) {
            return;
        }
        try {
            this.a.a(new aku(this.b, aof.a(this.c).a(og.a(this.c), this.d)));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        } catch (GooglePlayServicesNotAvailableException e2) {
        }
    }
}
