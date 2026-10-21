package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.internal.av;
import com.google.android.gms.internal.ay;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public abstract class tv extends Binder implements tu {
    public tv() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IAdManager");
    }

    public static tu a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof tu)) ? new tw(iBinder) : (tu) queryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                od c = c();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(c != null ? c.asBinder() : null);
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                d();
                parcel2.writeNoException();
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                boolean e = e();
                parcel2.writeNoException();
                parcel2.writeInt(e ? 1 : 0);
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                boolean a = a(parcel.readInt() != 0 ? av.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeInt(a ? 1 : 0);
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                f();
                parcel2.writeNoException();
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                g();
                parcel2.writeNoException();
                return true;
            case 7:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                a(ts.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 8:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                a(ub.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 9:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                h();
                parcel2.writeNoException();
                return true;
            case 10:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                i();
                parcel2.writeNoException();
                return true;
            case 11:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                j();
                parcel2.writeNoException();
                return true;
            case DateTimeConstants.DECEMBER /* 12 */:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                ay k = k();
                parcel2.writeNoException();
                if (k == null) {
                    parcel2.writeInt(0);
                    return true;
                }
                parcel2.writeInt(1);
                k.writeToParcel(parcel2, 1);
                return true;
            case HTTP.CR /* 13 */:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                a(parcel.readInt() != 0 ? ay.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 14:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                a(ys.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case ViewDragHelper.EDGE_ALL /* 15 */:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                a(ze.a(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 16:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                a(zp.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case LangUtils.HASH_SEED /* 17 */:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                a(zs.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 18:
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                String l = l();
                parcel2.writeNoException();
                parcel2.writeString(l);
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.ads.internal.client.IAdManager");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
