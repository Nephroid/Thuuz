package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.fitness.data.BleDevice;

/* loaded from: classes.dex */
public abstract class pq extends Binder implements pp {
    public static pp a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.request.IBleScanCallback");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof pp)) ? new pr(iBinder) : (pp) queryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.fitness.request.IBleScanCallback");
                a(parcel.readInt() != 0 ? BleDevice.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.fitness.request.IBleScanCallback");
                a();
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.fitness.request.IBleScanCallback");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
