package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.fi;
import com.google.android.gms.internal.fk;

/* loaded from: classes.dex */
public abstract class abk extends Binder implements abj {
    public abk() {
        attachInterface(this, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    public static abj a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof abj)) ? new abl(iBinder) : (abj) queryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
                fk a = a(parcel.readInt() != 0 ? fi.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                if (a != null) {
                    parcel2.writeInt(1);
                    a.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.ads.internal.request.IAdRequestService");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
