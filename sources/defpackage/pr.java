package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.fitness.data.BleDevice;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class pr implements pp {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public pr(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.pp
    public void a() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.fitness.request.IBleScanCallback");
            this.a.transact(2, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // defpackage.pp
    public void a(BleDevice bleDevice) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.fitness.request.IBleScanCallback");
            if (bleDevice != null) {
                obtain.writeInt(1);
                bleDevice.writeToParcel(obtain, 0);
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
