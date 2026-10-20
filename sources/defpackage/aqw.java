package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class aqw implements aqu {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aqw(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.aqu
    public od a(od odVar, int i, int i2, String str, int i3) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
            obtain.writeStrongBinder(odVar != null ? odVar.asBinder() : null);
            obtain.writeInt(i);
            obtain.writeInt(i2);
            obtain.writeString(str);
            obtain.writeInt(i3);
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return oe.a(obtain2.readStrongBinder());
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.aqu
    public od a(od odVar, int i, int i2, String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
            obtain.writeStrongBinder(odVar != null ? odVar.asBinder() : null);
            obtain.writeInt(i);
            obtain.writeInt(i2);
            obtain.writeString(str);
            obtain.writeString(str2);
            this.a.transact(2, obtain, obtain2, 0);
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
