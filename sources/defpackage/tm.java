package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.g;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.gt;

@zy
/* loaded from: classes.dex */
public final class tm extends g<tx> {
    private static final tm a = new tm();

    private tm() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    public static tu a(Context context, ay ayVar, String str, wh whVar) {
        tu b;
        if (hb.a(context) == 0 && (b = a.b(context, ayVar, str, whVar)) != null) {
            return b;
        }
        adn.a("Using AdManager from the client jar.");
        return new ajr(context, ayVar, str, whVar, new gt(6111000, 6111000, true));
    }

    private tu b(Context context, ay ayVar, String str, wh whVar) {
        try {
            return tv.a(a(context).a(og.a(context), ayVar, str, whVar, 6111000));
        } catch (RemoteException e) {
            adn.d("Could not create remote AdManager.", e);
            return null;
        } catch (g.a e2) {
            adn.d("Could not create remote AdManager.", e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.dynamic.g
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public tx b(IBinder iBinder) {
        return ty.a(iBinder);
    }
}
