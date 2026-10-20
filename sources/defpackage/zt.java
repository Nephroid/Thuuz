package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
class zt implements zr {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zt(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.zr
    public void a(zl zlVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherInterstitialAdListener");
            obtain.writeStrongBinder(zlVar != null ? zlVar.asBinder() : null);
            this.a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.zr
    public boolean a(String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherInterstitialAdListener");
            obtain.writeString(str);
            obtain.writeString(str2);
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0;
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
