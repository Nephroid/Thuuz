package defpackage;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.internal.lz;
import com.google.android.gms.internal.mb;
import com.google.android.gms.internal.me;
import com.google.android.gms.internal.mg;
import com.google.android.gms.internal.mi;
import com.google.android.gms.internal.mk;
import com.google.android.gms.internal.mm;
import com.google.android.gms.internal.mq;
import com.google.android.gms.internal.ms;
import com.google.android.gms.internal.mw;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.c;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public abstract class ahm extends Binder implements ahl {
    public static ahl a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof ahl)) ? new ahn(iBinder) : (ahl) queryLocalInterface;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.createTypedArrayList(mb.CREATOR), parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null, ahj.a(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null, ahj.a(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.createStringArray(), ahj.a(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(ahj.a(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readLong(), parcel.readInt() != 0, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 7:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                Location a = a();
                parcel2.writeNoException();
                if (a == null) {
                    parcel2.writeInt(0);
                    return true;
                }
                parcel2.writeInt(1);
                a.writeToParcel(parcel2, 1);
                return true;
            case 8:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? LocationRequest.CREATOR.createFromParcel(parcel) : null, akl.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 9:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? LocationRequest.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 10:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(akl.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 11:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                b(parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case DateTimeConstants.DECEMBER /* 12 */:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case HTTP.CR /* 13 */:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? (Location) Location.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 14:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? LatLngBounds.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? mi.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null, aig.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case ViewDragHelper.EDGE_ALL /* 15 */:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readString(), parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null, aig.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 16:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? LatLng.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? mi.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null, aig.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case LangUtils.HASH_SEED /* 17 */:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? mi.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null, aig.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 18:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? mm.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 20:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? LocationRequest.CREATOR.createFromParcel(parcel) : null, akl.a(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 21:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                Location a2 = a(parcel.readString());
                parcel2.writeNoException();
                if (a2 == null) {
                    parcel2.writeInt(0);
                    return true;
                }
                parcel2.writeInt(1);
                a2.writeToParcel(parcel2, 1);
                return true;
            case 25:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? mk.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 26:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? (Location) Location.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 34:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                c b = b(parcel.readString());
                parcel2.writeNoException();
                if (b == null) {
                    parcel2.writeInt(0);
                    return true;
                }
                parcel2.writeInt(1);
                b.writeToParcel(parcel2, 1);
                return true;
            case 42:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                b(parcel.readString(), parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null, aig.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 46:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? mq.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null, aig.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 47:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? LatLngBounds.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? mi.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null, aig.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 48:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? mg.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 49:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                b(parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 50:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? ms.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? LatLngBounds.CREATOR.createFromParcel(parcel) : null, parcel.createStringArrayList(), parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null, aig.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 51:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                IBinder b2 = b();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(b2);
                return true;
            case 52:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? lz.CREATOR.createFromParcel(parcel) : null, akl.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 53:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readInt() != 0 ? lz.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 54:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                IBinder c = c();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(c);
                return true;
            case 55:
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                a(parcel.readString(), parcel.readInt() != 0 ? LatLngBounds.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? me.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? mw.CREATOR.createFromParcel(parcel) : null, aig.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
