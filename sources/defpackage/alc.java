package defpackage;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public abstract class alc extends Binder implements alb {
    public static alb a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof alb)) ? new ald(iBinder) : (alb) queryLocalInterface;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                CameraPosition a = a();
                parcel2.writeNoException();
                if (a == null) {
                    parcel2.writeInt(0);
                    return true;
                }
                parcel2.writeInt(1);
                a.writeToParcel(parcel2, 1);
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                float b = b();
                parcel2.writeNoException();
                parcel2.writeFloat(b);
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                float c = c();
                parcel2.writeNoException();
                parcel2.writeFloat(c);
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(oe.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                b(oe.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(oe.a(parcel.readStrongBinder()), ame.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 7:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(oe.a(parcel.readStrongBinder()), parcel.readInt(), ame.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 8:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                d();
                parcel2.writeNoException();
                return true;
            case 9:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                aov a2 = a(parcel.readInt() != 0 ? PolylineOptions.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a2 != null ? a2.asBinder() : null);
                return true;
            case 10:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                apn a3 = a(parcel.readInt() != 0 ? PolygonOptions.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a3 != null ? a3.asBinder() : null);
                return true;
            case 11:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                apk a4 = a(parcel.readInt() != 0 ? MarkerOptions.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a4 != null ? a4.asBinder() : null);
                return true;
            case DateTimeConstants.DECEMBER /* 12 */:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                ape a5 = a(parcel.readInt() != 0 ? GroundOverlayOptions.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a5 != null ? a5.asBinder() : null);
                return true;
            case HTTP.CR /* 13 */:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                apq a6 = a(parcel.readInt() != 0 ? TileOverlayOptions.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a6 != null ? a6.asBinder() : null);
                return true;
            case 14:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                e();
                parcel2.writeNoException();
                return true;
            case ViewDragHelper.EDGE_ALL /* 15 */:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                int f = f();
                parcel2.writeNoException();
                parcel2.writeInt(f);
                return true;
            case 16:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case LangUtils.HASH_SEED /* 17 */:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean g = g();
                parcel2.writeNoException();
                parcel2.writeInt(g ? 1 : 0);
                return true;
            case 18:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean h = h();
                parcel2.writeNoException();
                parcel2.writeInt(h ? 1 : 0);
                return true;
            case 20:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean b2 = b(parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeInt(b2 ? 1 : 0);
                return true;
            case 21:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean i3 = i();
                parcel2.writeNoException();
                parcel2.writeInt(i3 ? 1 : 0);
                return true;
            case 22:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                c(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 23:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                Location j = j();
                parcel2.writeNoException();
                if (j == null) {
                    parcel2.writeInt(0);
                    return true;
                }
                parcel2.writeInt(1);
                j.writeToParcel(parcel2, 1);
                return true;
            case DateTimeConstants.HOURS_PER_DAY /* 24 */:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(alf.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 25:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                alz k = k();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(k != null ? k.asBinder() : null);
                return true;
            case 26:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                aln l = l();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(l != null ? l.asBinder() : null);
                return true;
            case 27:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(amn.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 28:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(amz.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 29:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(anf.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 30:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(ani.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 31:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(anl.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 32:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(amt.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 33:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(amk.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 35:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                apb a7 = a(parcel.readInt() != 0 ? CircleOptions.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a7 != null ? a7.asBinder() : null);
                return true;
            case 36:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(anr.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case LangUtils.HASH_OFFSET /* 37 */:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(ano.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 38:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(aod.a(parcel.readStrongBinder()), oe.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 39:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 40:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean m = m();
                parcel2.writeNoException();
                parcel2.writeInt(m ? 1 : 0);
                return true;
            case 41:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                d(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 42:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(anc.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 44:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                aph n = n();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(n != null ? n.asBinder() : null);
                return true;
            case 45:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                a(amq.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
