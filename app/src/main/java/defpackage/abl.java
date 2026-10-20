package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.fi;
import com.google.android.gms.internal.fk;

/* loaded from: classes.dex */
class abl implements abj {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abl(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.abj
    public fk a(fi fiVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.request.IAdRequestService");
            if (fiVar != null) {
                obtain.writeInt(1);
                fiVar.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0 ? fk.CREATOR.createFromParcel(obtain2) : null;
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
