package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public abstract class zm extends Binder implements zl {
    public zm() {
        attachInterface(this, "com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
    }

    public static zl a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof zl)) ? new zn(iBinder) : (zl) queryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                String a = a();
                parcel2.writeNoException();
                parcel2.writeString(a);
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                String b = b();
                parcel2.writeNoException();
                parcel2.writeString(b);
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                a(oe.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                c();
                parcel2.writeNoException();
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                d();
                parcel2.writeNoException();
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
