package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

/* loaded from: classes.dex */
public abstract class amh extends Binder implements amg {
    public static amg a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof amg)) ? new ami(iBinder) : (amg) queryLocalInterface;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                a(oe.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                alh b = b(oe.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeStrongBinder(b != null ? b.asBinder() : null);
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                alk a = a(oe.a(parcel.readStrongBinder()), parcel.readInt() != 0 ? GoogleMapOptions.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a != null ? a.asBinder() : null);
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                aky a2 = a();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a2 != null ? a2.asBinder() : null);
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                aoy b2 = b();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(b2 != null ? b2.asBinder() : null);
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                a(oe.a(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 7:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                alw a3 = a(oe.a(parcel.readStrongBinder()), parcel.readInt() != 0 ? StreetViewPanoramaOptions.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a3 != null ? a3.asBinder() : null);
                return true;
            case 8:
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                alt c = c(oe.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeStrongBinder(c != null ? c.asBinder() : null);
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.maps.internal.ICreator");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
