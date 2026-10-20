package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public abstract class apo extends Binder implements apn {
    public static apn a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof apn)) ? new app(iBinder) : (apn) queryLocalInterface;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                a();
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                String b = b();
                parcel2.writeNoException();
                parcel2.writeString(b);
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                a(parcel.createTypedArrayList(LatLng.CREATOR));
                parcel2.writeNoException();
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                List<LatLng> c = c();
                parcel2.writeNoException();
                parcel2.writeTypedList(c);
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                b(parcel.readArrayList(getClass().getClassLoader()));
                parcel2.writeNoException();
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                List d = d();
                parcel2.writeNoException();
                parcel2.writeList(d);
                return true;
            case 7:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                a(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 8:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                float e = e();
                parcel2.writeNoException();
                parcel2.writeFloat(e);
                return true;
            case 9:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                a(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 10:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                int f = f();
                parcel2.writeNoException();
                parcel2.writeInt(f);
                return true;
            case 11:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                b(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case DateTimeConstants.DECEMBER /* 12 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                int g = g();
                parcel2.writeNoException();
                parcel2.writeInt(g);
                return true;
            case HTTP.CR /* 13 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                b(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 14:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                float h = h();
                parcel2.writeNoException();
                parcel2.writeFloat(h);
                return true;
            case ViewDragHelper.EDGE_ALL /* 15 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                a(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 16:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                boolean i3 = i();
                parcel2.writeNoException();
                parcel2.writeInt(i3 ? 1 : 0);
                return true;
            case LangUtils.HASH_SEED /* 17 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                b(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 18:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                boolean j = j();
                parcel2.writeNoException();
                parcel2.writeInt(j ? 1 : 0);
                return true;
            case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                boolean a = a(a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(a ? 1 : 0);
                return true;
            case 20:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                int k = k();
                parcel2.writeNoException();
                parcel2.writeInt(k);
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
