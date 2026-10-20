package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
class jx implements jv {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public jx(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.jv
    public od a(od odVar, int i, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.ISignInButtonCreator");
            obtain.writeStrongBinder(odVar != null ? odVar.asBinder() : null);
            obtain.writeInt(i);
            obtain.writeInt(i2);
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
}
