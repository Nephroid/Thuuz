package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;

/* loaded from: classes.dex */
public abstract class api extends Binder implements aph {
    public static aph a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof aph)) ? new apj(iBinder) : (aph) queryLocalInterface;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                int a = a();
                parcel2.writeNoException();
                parcel2.writeInt(a);
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                int b = b();
                parcel2.writeNoException();
                parcel2.writeInt(b);
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                List<IBinder> c = c();
                parcel2.writeNoException();
                parcel2.writeBinderList(c);
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                boolean d = d();
                parcel2.writeNoException();
                parcel2.writeInt(d ? 1 : 0);
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                boolean a2 = a(a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(a2 ? 1 : 0);
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                int e = e();
                parcel2.writeNoException();
                parcel2.writeInt(e);
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
