package defpackage;

import android.text.TextUtils;
import com.google.android.gms.analytics.t;
import defpackage.eg;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class eg<T extends eg> {
    ff a;
    private Map<String, String> e = new HashMap();
    Map<String, List<fe>> b = new HashMap();
    List<fg> c = new ArrayList();
    List<fe> d = new ArrayList();

    public final T a(String str, String str2) {
        t.a().a(t.a.MAP_BUILDER_SET);
        if (str != null) {
            this.e.put(str, str2);
        } else {
            gn.d(" HitBuilder.set() called with a null paramName.");
        }
        return this;
    }

    public Map<String, String> a() {
        HashMap hashMap = new HashMap(this.e);
        if (this.a != null) {
            hashMap.putAll(this.a.a());
        }
        Iterator<fg> it = this.c.iterator();
        int i = 1;
        while (it.hasNext()) {
            hashMap.putAll(it.next().a(fr.b(i)));
            i++;
        }
        Iterator<fe> it2 = this.d.iterator();
        int i2 = 1;
        while (it2.hasNext()) {
            hashMap.putAll(it2.next().a(fr.a(i2)));
            i2++;
        }
        int i3 = 1;
        for (Map.Entry<String, List<fe>> entry : this.b.entrySet()) {
            List<fe> value = entry.getValue();
            String d = fr.d(i3);
            Iterator<fe> it3 = value.iterator();
            int i4 = 1;
            while (it3.hasNext()) {
                hashMap.putAll(it3.next().a(d + fr.c(i4)));
                i4++;
            }
            if (!TextUtils.isEmpty(entry.getKey())) {
                hashMap.put(d + "nm", entry.getKey());
            }
            i3++;
        }
        return hashMap;
    }

    public T b() {
        a("&sc", "start");
        return this;
    }

    public T d(String str) {
        t.a().a(t.a.MAP_BUILDER_SET_CAMPAIGN_PARAMS);
        String b = ew.b(str);
        if (!TextUtils.isEmpty(b)) {
            Map<String, String> a = ew.a(b);
            a("&cc", a.get("utm_content"));
            a("&cm", a.get("utm_medium"));
            a("&cn", a.get("utm_campaign"));
            a("&cs", a.get("utm_source"));
            a("&ck", a.get("utm_term"));
            a("&ci", a.get("utm_id"));
            a("&gclid", a.get("gclid"));
            a("&dclid", a.get("dclid"));
            a("&gmob_t", a.get("gmob_t"));
        }
        return this;
    }
}
