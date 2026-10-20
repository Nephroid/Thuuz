package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
class jr implements jp {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public jr(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.jp
    public void a(int i, IBinder iBinder, Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsCallbacks");
            obtain.writeInt(i);
            obtain.writeStrongBinder(iBinder);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
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
