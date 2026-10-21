package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.maps.model.LatLng;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public abstract class apc extends Binder implements apb {
    public static apb a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof apb)) ? new apd(iBinder) : (apb) queryLocalInterface;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                a();
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                String b = b();
                parcel2.writeNoException();
                parcel2.writeString(b);
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                a(parcel.readInt() != 0 ? LatLng.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                LatLng c = c();
                parcel2.writeNoException();
                if (c == null) {
                    parcel2.writeInt(0);
                    return true;
                }
                parcel2.writeInt(1);
                c.writeToParcel(parcel2, 1);
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                a(parcel.readDouble());
                parcel2.writeNoException();
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                double d = d();
                parcel2.writeNoException();
                parcel2.writeDouble(d);
                return true;
            case 7:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                a(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 8:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                float e = e();
                parcel2.writeNoException();
                parcel2.writeFloat(e);
                return true;
            case 9:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                a(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 10:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                int f = f();
                parcel2.writeNoException();
                parcel2.writeInt(f);
                return true;
            case 11:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                b(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case DateTimeConstants.DECEMBER /* 12 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                int g = g();
                parcel2.writeNoException();
                parcel2.writeInt(g);
                return true;
            case HTTP.CR /* 13 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                b(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 14:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                float h = h();
                parcel2.writeNoException();
                parcel2.writeFloat(h);
                return true;
            case ViewDragHelper.EDGE_ALL /* 15 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                a(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 16:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                boolean i3 = i();
                parcel2.writeNoException();
                parcel2.writeInt(i3 ? 1 : 0);
                return true;
            case LangUtils.HASH_SEED /* 17 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                boolean a = a(a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(a ? 1 : 0);
                return true;
            case 18:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                int j = j();
                parcel2.writeNoException();
                parcel2.writeInt(j);
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.maps.model.internal.ICircleDelegate");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
