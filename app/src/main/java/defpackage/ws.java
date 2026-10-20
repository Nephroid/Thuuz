package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.av;
import com.google.android.gms.internal.ay;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public final class ws extends wm {
    private final dm a;

    public ws(dm dmVar) {
        this.a = dmVar;
    }

    private Bundle a(String str, int i, String str2) {
        adn.e("Server parameters: " + str);
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    bundle2.putString(next, jSONObject.getString(next));
                }
                bundle = bundle2;
            }
            if (this.a instanceof co) {
                bundle.putString("adJson", str2);
                bundle.putInt("tagForChildDirectedTreatment", i);
            }
            return bundle;
        } catch (Throwable th) {
            adn.d("Could not get Server Parameters Bundle.", th);
            throw new RemoteException();
        }
    }

    @Override // defpackage.wl
    public od a() {
        if (!(this.a instanceof dn)) {
            adn.e("MediationAdapter is not a MediationBannerAdapter: " + this.a.getClass().getCanonicalName());
            throw new RemoteException();
        }
        try {
            return og.a(((dn) this.a).d());
        } catch (Throwable th) {
            adn.d("Could not get banner view from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // defpackage.wl
    public void a(od odVar, av avVar, String str, String str2, wo woVar) {
        if (!(this.a instanceof dp)) {
            adn.e("MediationAdapter is not a MediationInterstitialAdapter: " + this.a.getClass().getCanonicalName());
            throw new RemoteException();
        }
        adn.a("Requesting interstitial ad from adapter.");
        try {
            dp dpVar = (dp) this.a;
            dpVar.a((Context) og.a(odVar), new wt(woVar), a(str, avVar.g, str2), new wr(new Date(avVar.b), avVar.d, avVar.e != null ? new HashSet(avVar.e) : null, avVar.k, avVar.f, avVar.g), avVar.m != null ? avVar.m.getBundle(dpVar.getClass().getName()) : null);
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
        if (!(this.a instanceof dn)) {
            adn.e("MediationAdapter is not a MediationBannerAdapter: " + this.a.getClass().getCanonicalName());
            throw new RemoteException();
        }
        adn.a("Requesting banner ad from adapter.");
        try {
            dn dnVar = (dn) this.a;
            dnVar.a((Context) og.a(odVar), new wt(woVar), a(str, avVar.g, str2), dd.a(ayVar.f, ayVar.c, ayVar.b), new wr(new Date(avVar.b), avVar.d, avVar.e != null ? new HashSet(avVar.e) : null, avVar.k, avVar.f, avVar.g), avVar.m != null ? avVar.m.getBundle(dnVar.getClass().getName()) : null);
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
        if (!(this.a instanceof dp)) {
            adn.e("MediationAdapter is not a MediationInterstitialAdapter: " + this.a.getClass().getCanonicalName());
            throw new RemoteException();
        }
        adn.a("Showing interstitial from adapter.");
        try {
            ((dp) this.a).e();
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
        try {
            this.a.b();
        } catch (Throwable th) {
            adn.d("Could not pause adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // defpackage.wl
    public void e() {
        try {
            this.a.c();
        } catch (Throwable th) {
            adn.d("Could not resume adapter.", th);
            throw new RemoteException();
        }
    }
}
