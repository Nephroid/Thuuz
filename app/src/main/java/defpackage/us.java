package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
public abstract class us extends Binder implements ur {
    public us() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeContentAd");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                a(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                g();
                parcel2.writeNoException();
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                String a = a();
                parcel2.writeNoException();
                parcel2.writeString(a);
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                od b = b();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(b != null ? b.asBinder() : null);
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                String c = c();
                parcel2.writeNoException();
                parcel2.writeString(c);
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                od d = d();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(d != null ? d.asBinder() : null);
                return true;
            case 7:
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                String e = e();
                parcel2.writeNoException();
                parcel2.writeString(e);
                return true;
            case 8:
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                String f = f();
                parcel2.writeNoException();
                parcel2.writeString(f);
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
