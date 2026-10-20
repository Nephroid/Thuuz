package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.av;
import com.google.android.gms.internal.ay;

/* loaded from: classes.dex */
public abstract class wm extends Binder implements wl {
    public wm() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    public static wl a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof wl)) ? new wn(iBinder) : (wl) queryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                a(oe.a(parcel.readStrongBinder()), parcel.readInt() != 0 ? ay.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? av.CREATOR.createFromParcel(parcel) : null, parcel.readString(), wp.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                od a = a();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a != null ? a.asBinder() : null);
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                a(oe.a(parcel.readStrongBinder()), parcel.readInt() != 0 ? av.CREATOR.createFromParcel(parcel) : null, parcel.readString(), wp.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                b();
                parcel2.writeNoException();
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                c();
                parcel2.writeNoException();
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                a(oe.a(parcel.readStrongBinder()), parcel.readInt() != 0 ? ay.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? av.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString(), wp.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 7:
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                a(oe.a(parcel.readStrongBinder()), parcel.readInt() != 0 ? av.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString(), wp.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 8:
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                d();
                parcel2.writeNoException();
                return true;
            case 9:
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                e();
                parcel2.writeNoException();
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
