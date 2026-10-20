package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.maps.model.Tile;

/* loaded from: classes.dex */
public abstract class apu extends Binder implements apt {
    public static apt a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof apt)) ? new apv(iBinder) : (apt) queryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
                Tile a = a(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                if (a == null) {
                    parcel2.writeInt(0);
                    return true;
                }
                parcel2.writeInt(1);
                a.writeToParcel(parcel2, 1);
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
