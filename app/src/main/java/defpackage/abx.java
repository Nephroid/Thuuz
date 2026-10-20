package defpackage;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
// [removed legacy tracker import]
import com.google.android.gms.internal.av;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.bj;
import com.google.android.gms.internal.fi;
import com.google.android.gms.internal.fk;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public final class abx {
    private static final SimpleDateFormat a = new SimpleDateFormat("yyyyMMdd");

    public static fk a(Context context, fi fiVar, String str) {
        fk fkVar;
        List<String> list;
        List<String> list2;
        List<String> list3;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("ad_base_url", null);
            String optString2 = jSONObject.optString("ad_url", null);
            String optString3 = jSONObject.optString("ad_size", null);
            String optString4 = jSONObject.optString("ad_html", null);
            long j = -1;
            String optString5 = jSONObject.optString("debug_dialog", null);
            long j2 = jSONObject.has("interstitial_timeout") ? (long) (jSONObject.getDouble("interstitial_timeout") * 1000.0d) : -1L;
            String optString6 = jSONObject.optString("orientation", null);
            int i = -1;
            if ("portrait".equals(optString6)) {
                i = acx.c();
            } else if ("landscape".equals(optString6)) {
                i = acx.b();
            }
            if (TextUtils.isEmpty(optString4)) {
                if (TextUtils.isEmpty(optString2)) {
                    adn.e("Could not parse the mediation config: Missing required ad_html or ad_url field.");
                    return new fk(0);
                }
                fk a2 = abu.a(context, fiVar.k.b, optString2, (String) null, (acc) null);
                optString = a2.b;
                optString4 = a2.c;
                j = a2.n;
                fkVar = a2;
            } else {
                if (TextUtils.isEmpty(optString)) {
                    adn.e("Could not parse the mediation config: Missing required ad_base_url field");
                    return new fk(0);
                }
                fkVar = null;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("click_urls");
            List<String> list4 = fkVar == null ? null : fkVar.d;
            if (optJSONArray != null) {
                if (list4 == null) {
                    list4 = new LinkedList<>();
                }
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    list4.add(optJSONArray.getString(i2));
                }
                list = list4;
            } else {
                list = list4;
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("impression_urls");
            List<String> list5 = fkVar == null ? null : fkVar.f;
            if (optJSONArray2 != null) {
                if (list5 == null) {
                    list5 = new LinkedList<>();
                }
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    list5.add(optJSONArray2.getString(i3));
                }
                list2 = list5;
            } else {
                list2 = list5;
            }
            JSONArray optJSONArray3 = jSONObject.optJSONArray("manual_impression_urls");
            List<String> list6 = fkVar == null ? null : fkVar.j;
            if (optJSONArray3 != null) {
                if (list6 == null) {
                    list6 = new LinkedList<>();
                }
                for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                    list6.add(optJSONArray3.getString(i4));
                }
                list3 = list6;
            } else {
                list3 = list6;
            }
            if (fkVar != null) {
                if (fkVar.l != -1) {
                    i = fkVar.l;
                }
                if (fkVar.g > 0) {
                    j2 = fkVar.g;
                }
            }
            String optString7 = jSONObject.optString("active_view");
            boolean optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
            return new fk(optString, optString4, list, list2, j2, false, -1L, list3, -1L, i, optString3, j, optString5, optBoolean, optBoolean ? jSONObject.optString("ad_passback_url", null) : null, optString7, false, false, fiVar.p, false);
        } catch (JSONException e) {
            adn.e("Could not parse the mediation config: " + e.getMessage());
            return new fk(0);
        }
    }

    private static Integer a(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }

    private static String a(int i) {
        return String.format(Locale.US, "#%06x", Integer.valueOf(16777215 & i));
    }

    public static String a(fi fiVar, acd acdVar, Location location, String str, String str2) {
        try {
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
            }
            if (!TextUtils.isEmpty(str2)) {
                arrayList.add(str2);
            }
            if (arrayList.size() > 0) {
                hashMap.put("eid", TextUtils.join(",", arrayList));
            }
            if (fiVar.b != null) {
                hashMap.put("ad_pos", fiVar.b);
            }
            a((HashMap<String, Object>) hashMap, fiVar.c);
            hashMap.put("format", fiVar.d.b);
            if (fiVar.d.f == -1) {
                hashMap.put("smart_w", "full");
            }
            if (fiVar.d.c == -2) {
                hashMap.put("smart_h", "auto");
            }
            if (fiVar.d.h != null) {
                StringBuilder sb = new StringBuilder();
                for (ay ayVar : fiVar.d.h) {
                    if (sb.length() != 0) {
                        sb.append("|");
                    }
                    sb.append(ayVar.f == -1 ? (int) (ayVar.g / acdVar.q) : ayVar.f);
                    sb.append("x");
                    sb.append(ayVar.c == -2 ? (int) (ayVar.d / acdVar.q) : ayVar.c);
                }
                hashMap.put("sz", sb);
            }
            if (fiVar.m != 0) {
                hashMap.put("native_version", Integer.valueOf(fiVar.m));
                hashMap.put("native_templates", fiVar.n);
            }
            hashMap.put("slotname", fiVar.e);
            hashMap.put("pn", fiVar.f.packageName);
            if (fiVar.g != null) {
                hashMap.put("vc", Integer.valueOf(fiVar.g.versionCode));
            }
            hashMap.put("ms", fiVar.h);
            hashMap.put("seq_num", fiVar.i);
            hashMap.put("session_id", fiVar.j);
            hashMap.put("js", fiVar.k.b);
            a((HashMap<String, Object>) hashMap, acdVar);
            if (fiVar.c.a >= 2 && fiVar.c.k != null) {
                a((HashMap<String, Object>) hashMap, fiVar.c.k);
            }
            if (fiVar.a >= 2) {
                hashMap.put("quality_signals", fiVar.l);
            }
            if (fiVar.a >= 4 && fiVar.p) {
                hashMap.put("forceHttps", Boolean.valueOf(fiVar.p));
            }
            if (fiVar.a >= 3 && fiVar.o != null) {
                hashMap.put("content_info", fiVar.o);
            }
            if (adn.a(2)) {
                adn.d("Ad Request JSON: " + acx.a(hashMap).toString(2));
            }
            return acx.a(hashMap).toString();
        } catch (JSONException e) {
            adn.e("Problem serializing ad request to JSON: " + e.getMessage());
            return null;
        }
    }

    private static void a(HashMap<String, Object> hashMap, acd acdVar) {
        hashMap.put("am", Integer.valueOf(acdVar.a));
        hashMap.put("cog", a(acdVar.b));
        hashMap.put("coh", a(acdVar.c));
        if (!TextUtils.isEmpty(acdVar.d)) {
            hashMap.put("carrier", acdVar.d);
        }
        hashMap.put("gl", acdVar.e);
        if (acdVar.f) {
            hashMap.put("simulator", 1);
        }
        hashMap.put("ma", a(acdVar.g));
        hashMap.put("sp", a(acdVar.h));
        hashMap.put("hl", acdVar.i);
        if (!TextUtils.isEmpty(acdVar.j)) {
            hashMap.put("mv", acdVar.j);
        }
        hashMap.put("muv", Integer.valueOf(acdVar.k));
        if (acdVar.l != -2) {
            hashMap.put("cnt", Integer.valueOf(acdVar.l));
        }
        hashMap.put("gnt", Integer.valueOf(acdVar.m));
        hashMap.put("pt", Integer.valueOf(acdVar.n));
        hashMap.put("rm", Integer.valueOf(acdVar.o));
        hashMap.put("riv", Integer.valueOf(acdVar.p));
        hashMap.put("u_sd", Float.valueOf(acdVar.q));
        hashMap.put("sh", Integer.valueOf(acdVar.s));
        hashMap.put("sw", Integer.valueOf(acdVar.r));
        Bundle bundle = new Bundle();
        bundle.putInt("active_network_state", acdVar.w);
        bundle.putBoolean("active_network_metered", acdVar.v);
        hashMap.put("connectivity", bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("is_charging", acdVar.u);
        bundle2.putDouble("battery_level", acdVar.t);
        hashMap.put("battery", bundle2);
    }

    private static void a(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put("lat", valueOf3);
        hashMap2.put("long", valueOf4);
        hashMap2.put("time", valueOf2);
        hashMap.put("uule", hashMap2);
    }

    private static void a(HashMap<String, Object> hashMap, av avVar) {
        String a2 = acp.a();
        if (a2 != null) {
            hashMap.put("abf", a2);
        }
        if (avVar.b != -1) {
            hashMap.put("cust_age", a.format(new Date(avVar.b)));
        }
        if (avVar.c != null) {
            hashMap.put("extras", avVar.c);
        }
        if (avVar.d != -1) {
            hashMap.put("cust_gender", Integer.valueOf(avVar.d));
        }
        if (avVar.e != null) {
            hashMap.put("kw", avVar.e);
        }
        if (avVar.g != -1) {
            hashMap.put("tag_for_child_directed_treatment", Integer.valueOf(avVar.g));
        }
        if (avVar.f) {
            hashMap.put("adtest", "on");
        }
        if (avVar.a >= 2) {
            if (avVar.h) {
                hashMap.put("d_imp_hdr", 1);
            }
            if (!TextUtils.isEmpty(avVar.i)) {
                hashMap.put("ppid", avVar.i);
            }
            if (avVar.j != null) {
                a(hashMap, avVar.j);
            }
        }
        if (avVar.a < 3 || avVar.l == null) {
            return;
        }
        hashMap.put(NativeProtocol.IMAGE_URL_KEY, avVar.l);
    }

    private static void a(HashMap<String, Object> hashMap, bj bjVar) {
        String str;
        String str2 = null;
        if (Color.alpha(bjVar.b) != 0) {
            hashMap.put("acolor", a(bjVar.b));
        }
        if (Color.alpha(bjVar.c) != 0) {
            hashMap.put("bgcolor", a(bjVar.c));
        }
        if (Color.alpha(bjVar.d) != 0 && Color.alpha(bjVar.e) != 0) {
            hashMap.put("gradientto", a(bjVar.d));
            hashMap.put("gradientfrom", a(bjVar.e));
        }
        if (Color.alpha(bjVar.f) != 0) {
            hashMap.put("bcolor", a(bjVar.f));
        }
        hashMap.put("bthick", Integer.toString(bjVar.g));
        switch (bjVar.h) {
            case 0:
                str = "none";
                break;
            case 1:
                str = "dashed";
                break;
            case 2:
                str = "dotted";
                break;
            case 3:
                str = "solid";
                break;
            default:
                str = null;
                break;
        }
        if (str != null) {
            hashMap.put("btype", str);
        }
        switch (bjVar.i) {
            case 0:
                str2 = "light";
                break;
            case 1:
                str2 = "medium";
                break;
            case 2:
                str2 = "dark";
                break;
        }
        if (str2 != null) {
            hashMap.put("callbuttoncolor", str2);
        }
        if (bjVar.j != null) {
            hashMap.put("channel", bjVar.j);
        }
        if (Color.alpha(bjVar.k) != 0) {
            hashMap.put("dcolor", a(bjVar.k));
        }
        if (bjVar.l != null) {
            hashMap.put("font", bjVar.l);
        }
        if (Color.alpha(bjVar.m) != 0) {
            hashMap.put("hcolor", a(bjVar.m));
        }
        hashMap.put("headersize", Integer.toString(bjVar.n));
        if (bjVar.o != null) {
            hashMap.put("q", bjVar.o);
        }
    }
}
