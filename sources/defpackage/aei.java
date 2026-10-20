package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.hb;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class aei implements aeg {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aei(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.aeg
    public void a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.analytics.internal.IAnalyticsService");
            this.a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.aeg
    public void a(Map map, long j, String str, List<hb> list) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.analytics.internal.IAnalyticsService");
            obtain.writeMap(map);
            obtain.writeLong(j);
            obtain.writeString(str);
            obtain.writeTypedList(list);
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

    @Override // defpackage.aeg
    public String b() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.analytics.internal.IAnalyticsService");
            this.a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
