package defpackage;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
class akm implements akk {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public akm(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.akk
    public void a(Location location) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.ILocationListener");
            if (location != null) {
                obtain.writeInt(1);
                location.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.a;
    }
}
