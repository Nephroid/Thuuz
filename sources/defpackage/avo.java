package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.internal.ah;
import com.google.android.gms.wearable.internal.ak;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class avo implements avm {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public avo(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.avm
    public void a(DataHolder dataHolder) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableListener");
            if (dataHolder != null) {
                obtain.writeInt(1);
                dataHolder.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // defpackage.avm
    public void a(ah ahVar) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableListener");
            if (ahVar != null) {
                obtain.writeInt(1);
                ahVar.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(2, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // defpackage.avm
    public void a(ak akVar) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableListener");
            if (akVar != null) {
                obtain.writeInt(1);
                akVar.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(3, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.a;
    }

    @Override // defpackage.avm
    public void b(ak akVar) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableListener");
            if (akVar != null) {
                obtain.writeInt(1);
                akVar.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(4, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }
}
