package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.maps.model.LatLng;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public abstract class apl extends Binder implements apk {
    public static apk a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof apk)) ? new apm(iBinder) : (apk) queryLocalInterface;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                a();
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                String b = b();
                parcel2.writeNoException();
                parcel2.writeString(b);
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                a(parcel.readInt() != 0 ? LatLng.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
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
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                a(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                String d = d();
                parcel2.writeNoException();
                parcel2.writeString(d);
                return true;
            case 7:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                b(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 8:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                String e = e();
                parcel2.writeNoException();
                parcel2.writeString(e);
                return true;
            case 9:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                a(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 10:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                boolean f = f();
                parcel2.writeNoException();
                parcel2.writeInt(f ? 1 : 0);
                return true;
            case 11:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                g();
                parcel2.writeNoException();
                return true;
            case DateTimeConstants.DECEMBER /* 12 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                h();
                parcel2.writeNoException();
                return true;
            case HTTP.CR /* 13 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                boolean i3 = i();
                parcel2.writeNoException();
                parcel2.writeInt(i3 ? 1 : 0);
                return true;
            case 14:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                b(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case ViewDragHelper.EDGE_ALL /* 15 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                boolean j = j();
                parcel2.writeNoException();
                parcel2.writeInt(j ? 1 : 0);
                return true;
            case 16:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                boolean a = a(a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(a ? 1 : 0);
                return true;
            case LangUtils.HASH_SEED /* 17 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                int k = k();
                parcel2.writeNoException();
                parcel2.writeInt(k);
                return true;
            case 18:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                a(oe.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                a(parcel.readFloat(), parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 20:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                c(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 21:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                boolean l = l();
                parcel2.writeNoException();
                parcel2.writeInt(l ? 1 : 0);
                return true;
            case 22:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                a(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 23:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                float m = m();
                parcel2.writeNoException();
                parcel2.writeFloat(m);
                return true;
            case DateTimeConstants.HOURS_PER_DAY /* 24 */:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                b(parcel.readFloat(), parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 25:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                b(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 26:
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                float n = n();
                parcel2.writeNoException();
                parcel2.writeFloat(n);
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
