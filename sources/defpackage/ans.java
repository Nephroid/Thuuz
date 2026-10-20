package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
class ans implements anq {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ans(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.anq
    public void a(od odVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
            obtain.writeStrongBinder(odVar != null ? odVar.asBinder() : null);
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.a;
    }
}
