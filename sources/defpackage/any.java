package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

/* loaded from: classes.dex */
class any implements anw {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public any(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.anw
    public void a(StreetViewPanoramaLocation streetViewPanoramaLocation) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
            if (streetViewPanoramaLocation != null) {
                obtain.writeInt(1);
                streetViewPanoramaLocation.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.a;
    }
}
