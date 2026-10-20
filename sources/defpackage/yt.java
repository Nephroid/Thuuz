package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
class yt implements yr {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public yt(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.yr
    public void a(yo yoVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseListener");
            obtain.writeStrongBinder(yoVar != null ? yoVar.asBinder() : null);
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
