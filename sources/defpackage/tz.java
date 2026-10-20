package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.ay;

/* loaded from: classes.dex */
class tz implements tx {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public tz(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.tx
    public IBinder a(od odVar, ay ayVar, String str, wi wiVar, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManagerCreator");
            obtain.writeStrongBinder(odVar != null ? odVar.asBinder() : null);
            if (ayVar != null) {
                obtain.writeInt(1);
                ayVar.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            obtain.writeString(str);
            obtain.writeStrongBinder(wiVar != null ? wiVar.asBinder() : null);
            obtain.writeInt(i);
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readStrongBinder();
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
