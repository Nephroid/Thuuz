package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import defpackage.aks;
import defpackage.akt;

/* loaded from: classes.dex */
public class StreetViewPanoramaView extends FrameLayout {
    private final akt a;
    private aks b;

    public StreetViewPanoramaView(Context context) {
        super(context);
        this.a = new akt(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new akt(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new akt(this, context, null);
    }

    public final aks getStreetViewPanorama() {
        if (this.b != null) {
            return this.b;
        }
        this.a.b();
        if (this.a.a() == null) {
            return null;
        }
        try {
            this.b = new aks(this.a.a().a().a());
            return this.b;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
