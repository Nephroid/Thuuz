package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import defpackage.akp;
import defpackage.akr;

/* loaded from: classes.dex */
public class MapView extends FrameLayout {
    private final akr a;
    private akp b;

    public MapView(Context context) {
        super(context);
        this.a = new akr(this, context, null);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new akr(this, context, GoogleMapOptions.a(context, attributeSet));
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new akr(this, context, GoogleMapOptions.a(context, attributeSet));
    }

    public final akp getMap() {
        if (this.b != null) {
            return this.b;
        }
        this.a.b();
        if (this.a.a() == null) {
            return null;
        }
        try {
            this.b = new akp(this.a.a().a().a());
            return this.b;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
