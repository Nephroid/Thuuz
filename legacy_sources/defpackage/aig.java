package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;

/* loaded from: classes.dex */
public abstract class aig extends Binder implements aif {
    public static aif a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof aif)) ? new aih(iBinder) : (aif) queryLocalInterface;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                a(parcel.readInt() != 0 ? DataHolder.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                b(parcel.readInt() != 0 ? DataHolder.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                c(parcel.readInt() != 0 ? DataHolder.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
