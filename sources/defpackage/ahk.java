package defpackage;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
class ahk implements ahi {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ahk(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.ahi
    public void a(int i, PendingIntent pendingIntent) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
            obtain.writeInt(i);
            if (pendingIntent != null) {
                obtain.writeInt(1);
                pendingIntent.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.ahi
    public void a(int i, String[] strArr) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
            obtain.writeInt(i);
            obtain.writeStringArray(strArr);
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

    @Override // defpackage.ahi
    public void b(int i, String[] strArr) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
            obtain.writeInt(i);
            obtain.writeStringArray(strArr);
            this.a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
