package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public abstract class alr extends Binder implements alq {
    public static alq a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof alq)) ? new als(iBinder) : (alq) queryLocalInterface;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                a(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                b(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                c(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                d(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                boolean a = a();
                parcel2.writeNoException();
                parcel2.writeInt(a ? 1 : 0);
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                boolean b = b();
                parcel2.writeNoException();
                parcel2.writeInt(b ? 1 : 0);
                return true;
            case 7:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                boolean c = c();
                parcel2.writeNoException();
                parcel2.writeInt(c ? 1 : 0);
                return true;
            case 8:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                boolean d = d();
                parcel2.writeNoException();
                parcel2.writeInt(d ? 1 : 0);
                return true;
            case 9:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                a(parcel.readInt() != 0 ? StreetViewPanoramaCamera.CREATOR.createFromParcel(parcel) : null, parcel.readLong());
                parcel2.writeNoException();
                return true;
            case 10:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                StreetViewPanoramaCamera e = e();
                parcel2.writeNoException();
                if (e == null) {
                    parcel2.writeInt(0);
                    return true;
                }
                parcel2.writeInt(1);
                e.writeToParcel(parcel2, 1);
                return true;
            case 11:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                a(parcel.readString());
                parcel2.writeNoException();
                return true;
            case DateTimeConstants.DECEMBER /* 12 */:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                a(parcel.readInt() != 0 ? LatLng.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case HTTP.CR /* 13 */:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                a(parcel.readInt() != 0 ? LatLng.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 14:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                StreetViewPanoramaLocation f = f();
                parcel2.writeNoException();
                if (f == null) {
                    parcel2.writeInt(0);
                    return true;
                }
                parcel2.writeInt(1);
                f.writeToParcel(parcel2, 1);
                return true;
            case ViewDragHelper.EDGE_ALL /* 15 */:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                a(anx.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 16:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                a(anu.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case LangUtils.HASH_SEED /* 17 */:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                a(aoa.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 18:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                StreetViewPanoramaOrientation a2 = a(oe.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                if (a2 == null) {
                    parcel2.writeInt(0);
                    return true;
                }
                parcel2.writeInt(1);
                a2.writeToParcel(parcel2, 1);
                return true;
            case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                od a3 = a(parcel.readInt() != 0 ? StreetViewPanoramaOrientation.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a3 != null ? a3.asBinder() : null);
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
