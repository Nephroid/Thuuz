package defpackage;

import android.os.RemoteException;
import com.google.ads.mediation.MediationServerParameters;
import java.util.Map;

@zy
/* loaded from: classes.dex */
public final class wh extends wj {
    private Map<Class<? extends Object>, Object> a;

    private <NETWORK_EXTRAS extends cn, SERVER_PARAMETERS extends MediationServerParameters> wl c(String str) {
        try {
            Class<?> cls = Class.forName(str, false, wh.class.getClassLoader());
            if (ch.class.isAssignableFrom(cls)) {
                ch chVar = (ch) cls.newInstance();
                return new wu(chVar, (cn) this.a.get(chVar.b()));
            }
            if (dm.class.isAssignableFrom(cls)) {
                return new ws((dm) cls.newInstance());
            }
            adn.e("Could not instantiate mediation adapter: " + str + " (not a valid adapter).");
            throw new RemoteException();
        } catch (Throwable th) {
            adn.e("Could not instantiate mediation adapter: " + str + ". " + th.getMessage());
            throw new RemoteException();
        }
    }

    @Override // defpackage.wi
    public wl a(String str) {
        return c(str);
    }

    public void a(Map<Class<? extends Object>, Object> map) {
        this.a = map;
    }

    @Override // defpackage.wi
    public boolean b(String str) {
        try {
            return dr.class.isAssignableFrom(Class.forName(str, false, wh.class.getClassLoader()));
        } catch (Throwable th) {
            adn.e("Could not load custom event implementation class: " + str + ", assuming old implementation.");
            return false;
        }
    }
}
