package defpackage;

import android.net.http.Headers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public final class vz {
    public final List<vy> a;
    public final long b;
    public final List<String> c;
    public final List<String> d;
    public final List<String> e;
    public final String f;
    public final long g;
    public int h;
    public int i;

    public vz(String str) {
        JSONObject jSONObject = new JSONObject(str);
        if (adn.a(2)) {
            adn.d("Mediation Response JSON: " + jSONObject.toString(2));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("ad_networks");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int i = -1;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            vy vyVar = new vy(jSONArray.getJSONObject(i2));
            arrayList.add(vyVar);
            if (i < 0 && a(vyVar)) {
                i = i2;
            }
        }
        this.h = i;
        this.i = jSONArray.length();
        this.a = Collections.unmodifiableList(arrayList);
        this.f = jSONObject.getString("qdata");
        JSONObject optJSONObject = jSONObject.optJSONObject("settings");
        if (optJSONObject == null) {
            this.b = -1L;
            this.c = null;
            this.d = null;
            this.e = null;
            this.g = -1L;
            return;
        }
        this.b = optJSONObject.optLong("ad_network_timeout_millis", -1L);
        this.c = wg.a(optJSONObject, "click_urls");
        this.d = wg.a(optJSONObject, "imp_urls");
        this.e = wg.a(optJSONObject, "nofill_urls");
        long optLong = optJSONObject.optLong(Headers.REFRESH, -1L);
        this.g = optLong > 0 ? optLong * 1000 : -1L;
    }

    private boolean a(vy vyVar) {
        Iterator<String> it = vyVar.c.iterator();
        while (it.hasNext()) {
            if (it.next().equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                return true;
            }
        }
        return false;
    }
}
