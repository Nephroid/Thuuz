package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
class aml implements amj {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aml(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.amj
    public od a(apk apkVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IInfoWindowAdapter");
            obtain.writeStrongBinder(apkVar != null ? apkVar.asBinder() : null);
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return oe.a(obtain2.readStrongBinder());
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.a;
    }

    @Override // defpackage.amj
    public od b(apk apkVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IInfoWindowAdapter");
            obtain.writeStrongBinder(apkVar != null ? apkVar.asBinder() : null);
            this.a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            return oe.a(obtain2.readStrongBinder());
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
