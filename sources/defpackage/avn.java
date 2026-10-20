package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.internal.ah;
import com.google.android.gms.wearable.internal.ak;

/* loaded from: classes.dex */
public abstract class avn extends Binder implements avm {
    public static avm a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableListener");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof avm)) ? new avo(iBinder) : (avm) queryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                a(parcel.readInt() != 0 ? DataHolder.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                a(parcel.readInt() != 0 ? ah.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                a(parcel.readInt() != 0 ? ak.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                b(parcel.readInt() != 0 ? ak.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.wearable.internal.IWearableListener");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
