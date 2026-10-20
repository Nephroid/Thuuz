package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
class alg implements ale {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public alg(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.ale
    public void a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ILocationSourceDelegate");
            this.a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.ale
    public void a(amv amvVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ILocationSourceDelegate");
            obtain.writeStrongBinder(amvVar != null ? amvVar.asBinder() : null);
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
