package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.fitness.data.DataPoint;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ot implements or {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ot(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.or
    public void a(DataPoint dataPoint) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.fitness.data.IDataSourceListener");
            if (dataPoint != null) {
                obtain.writeInt(1);
                dataPoint.writeToParcel(obtain, 0);
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
