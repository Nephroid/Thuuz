package defpackage;

import android.app.Activity;
import android.os.RemoteException;
import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.internal.av;
import com.google.android.gms.internal.ay;
import defpackage.cn;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public final class wu<NETWORK_EXTRAS extends cn, SERVER_PARAMETERS extends MediationServerParameters> extends wm {
    private final ch<NETWORK_EXTRAS, SERVER_PARAMETERS> a;
    private final NETWORK_EXTRAS b;

    public wu(ch<NETWORK_EXTRAS, SERVER_PARAMETERS> chVar, NETWORK_EXTRAS network_extras) {
        this.a = chVar;
        this.b = network_extras;
    }

    private SERVER_PARAMETERS a(String str, int i, String str2) {
        HashMap hashMap;
        try {
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = new HashMap(jSONObject.length());
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.getString(next));
                }
            } else {
                hashMap = new HashMap(0);
            }
            Class<SERVER_PARAMETERS> c = this.a.c();
            if (c == null) {
                return null;
            }
            SERVER_PARAMETERS newInstance = c.newInstance();
            newInstance.a(hashMap);
            return newInstance;
        } catch (Throwable th) {
            adn.d("Could not get MediationServerParameters.", th);
            throw new RemoteException();
        }
    }

    @Override // defpackage.wl
    public od a() {
        if (!(this.a instanceof ci)) {
            adn.e("MediationAdapter is not a MediationBannerAdapter: " + this.a.getClass().getCanonicalName());
            throw new RemoteException();
        }
        try {
            return og.a(((ci) this.a).d());
        } catch (Throwable th) {
            adn.d("Could not get banner view from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // defpackage.wl
    public void a(od odVar, av avVar, String str, String str2, wo woVar) {
        if (!(this.a instanceof ck)) {
            adn.e("MediationAdapter is not a MediationInterstitialAdapter: " + this.a.getClass().getCanonicalName());
            throw new RemoteException();
        }
        adn.a("Requesting interstitial ad from adapter.");
        try {
            ((ck) this.a).a(new wx(woVar), (Activity) og.a(odVar), a(str, avVar.g, str2), wy.a(avVar), this.b);
        } catch (Throwable th) {
            adn.d("Could not request interstitial ad from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // defpackage.wl
    public void a(od odVar, av avVar, String str, wo woVar) {
        a(odVar, avVar, str, (String) null, woVar);
    }

    @Override // defpackage.wl
    public void a(od odVar, ay ayVar, av avVar, String str, String str2, wo woVar) {
        if (!(this.a instanceof ci)) {
            adn.e("MediationAdapter is not a MediationBannerAdapter: " + this.a.getClass().getCanonicalName());
            throw new RemoteException();
        }
        adn.a("Requesting banner ad from adapter.");
        try {
            ((ci) this.a).a(new wx(woVar), (Activity) og.a(odVar), a(str, avVar.g, str2), wy.a(ayVar), wy.a(avVar), this.b);
        } catch (Throwable th) {
            adn.d("Could not request banner ad from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // defpackage.wl
    public void a(od odVar, ay ayVar, av avVar, String str, wo woVar) {
        a(odVar, ayVar, avVar, str, null, woVar);
    }

    @Override // defpackage.wl
    public void b() {
        if (!(this.a instanceof ck)) {
            adn.e("MediationAdapter is not a MediationInterstitialAdapter: " + this.a.getClass().getCanonicalName());
            throw new RemoteException();
        }
        adn.a("Showing interstitial from adapter.");
        try {
            ((ck) this.a).d();
        } catch (Throwable th) {
            adn.d("Could not show interstitial from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // defpackage.wl
    public void c() {
        try {
            this.a.a();
        } catch (Throwable th) {
            adn.d("Could not destroy adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // defpackage.wl
    public void d() {
        throw new RemoteException();
    }

    @Override // defpackage.wl
    public void e() {
        throw new RemoteException();
    }
}
