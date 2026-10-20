package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes.dex */
public abstract class akz extends Binder implements aky {
    public static aky a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof aky)) ? new ala(iBinder) : (aky) queryLocalInterface;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                od a = a();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a != null ? a.asBinder() : null);
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                od b = b();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(b != null ? b.asBinder() : null);
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                od a2 = a(parcel.readFloat(), parcel.readFloat());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a2 != null ? a2.asBinder() : null);
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                od a3 = a(parcel.readFloat());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a3 != null ? a3.asBinder() : null);
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                od b2 = b(parcel.readFloat());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(b2 != null ? b2.asBinder() : null);
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                od a4 = a(parcel.readFloat(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a4 != null ? a4.asBinder() : null);
                return true;
            case 7:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                od a5 = a(parcel.readInt() != 0 ? CameraPosition.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a5 != null ? a5.asBinder() : null);
                return true;
            case 8:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                od a6 = a(parcel.readInt() != 0 ? LatLng.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a6 != null ? a6.asBinder() : null);
                return true;
            case 9:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                od a7 = a(parcel.readInt() != 0 ? LatLng.CREATOR.createFromParcel(parcel) : null, parcel.readFloat());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a7 != null ? a7.asBinder() : null);
                return true;
            case 10:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                od a8 = a(parcel.readInt() != 0 ? LatLngBounds.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a8 != null ? a8.asBinder() : null);
                return true;
            case 11:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                od a9 = a(parcel.readInt() != 0 ? LatLngBounds.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a9 != null ? a9.asBinder() : null);
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
