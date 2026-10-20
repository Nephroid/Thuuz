package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
class uc implements ua {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public uc(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.ua
    public void a(String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAppEventListener");
            obtain.writeString(str);
            obtain.writeString(str2);
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
