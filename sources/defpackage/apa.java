package defpackage;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
class apa implements aoy {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public apa(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // defpackage.aoy
    public od a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
            this.a.transact(4, obtain, obtain2, 0);
            obtain2.readException();
            return oe.a(obtain2.readStrongBinder());
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.aoy
    public od a(float f) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
            obtain.writeFloat(f);
            this.a.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            return oe.a(obtain2.readStrongBinder());
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.aoy
    public od a(int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
            obtain.writeInt(i);
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return oe.a(obtain2.readStrongBinder());
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.aoy
    public od a(Bitmap bitmap) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
            if (bitmap != null) {
                obtain.writeInt(1);
                bitmap.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(6, obtain, obtain2, 0);
            obtain2.readException();
            return oe.a(obtain2.readStrongBinder());
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.aoy
    public od a(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
            obtain.writeString(str);
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

    @Override // defpackage.aoy
    public od b(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
            obtain.writeString(str);
            this.a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return oe.a(obtain2.readStrongBinder());
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.aoy
    public od c(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
            obtain.writeString(str);
            this.a.transact(7, obtain, obtain2, 0);
            obtain2.readException();
            return oe.a(obtain2.readStrongBinder());
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
