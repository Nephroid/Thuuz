package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public abstract class apr extends Binder implements apq {
    public static apq a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof apq)) ? new aps(iBinder) : (apq) queryLocalInterface;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                a();
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                b();
                parcel2.writeNoException();
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                String c = c();
                parcel2.writeNoException();
                parcel2.writeString(c);
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                a(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                float d = d();
                parcel2.writeNoException();
                parcel2.writeFloat(d);
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                a(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 7:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                boolean e = e();
                parcel2.writeNoException();
                parcel2.writeInt(e ? 1 : 0);
                return true;
            case 8:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                boolean a = a(a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(a ? 1 : 0);
                return true;
            case 9:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                int f = f();
                parcel2.writeNoException();
                parcel2.writeInt(f);
                return true;
            case 10:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                b(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 11:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                boolean g = g();
                parcel2.writeNoException();
                parcel2.writeInt(g ? 1 : 0);
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
