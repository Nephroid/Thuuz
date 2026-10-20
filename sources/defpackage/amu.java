package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
class amu implements ams {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public amu(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.ams
    public void a(apk apkVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
            obtain.writeStrongBinder(apkVar != null ? apkVar.asBinder() : null);
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
