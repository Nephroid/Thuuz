package defpackage;

import android.content.Intent;
import android.util.SparseArray;
import com.samsung.android.sdk.richnotification.Utilities;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.data.AlertSettings;
import com.thuuz.gtv.data.FantasySettingsCustomizations;
import com.thuuz.gtv.data.OnlineResourceCategory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzu extends bya {
    private SparseArray<bpx> a;
    private Map<String, bpr> b;
    private Map<Integer, bpr> c;
    private Map<Integer, bps> d;
    private Map<Integer, OnlineResourceCategory> e;
    private Map<Integer, AlertSettings> f;
    private Map<Integer, List<boz>> g;
    private Map<Integer, FantasySettingsCustomizations> h;

    public bzu(App app) {
        super("sportinfo", app, false);
        this.a = new SparseArray<>();
        this.b = new HashMap();
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = new HashMap();
        this.f = new HashMap();
        this.g = new HashMap();
        this.h = new HashMap();
        a("objects", k().e() ? "sport,league,league_group,competition,onlineresourcecategory,alert_settings,sponsored_leagues,dashboard" : "sport,league,league_group,competition,onlineresourcecategory,alert_settings,sponsored_leagues");
        a("locale", Locale.getDefault().toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        try {
            a(jSONObject);
            F();
            getLogger().info("replacing sportinfo with new version");
            b(jSONObject.toString());
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "problem with parsing sportinfo: " + th);
            a(-1);
        }
        return v();
    }

    public boolean a(String str) {
        try {
            a(new JSONObject(str));
            return true;
        } catch (Throwable th) {
            getLogger().info("problem parsing sport info: " + th);
            return false;
        }
    }

    private void a(JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        JSONObject jSONObject4;
        JSONObject jSONObject5;
        JSONArray jSONArray = jSONObject.getJSONArray("sport");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject6 = jSONArray.getJSONObject(i);
            String string = jSONObject6.getString("slug");
            if (string == null || string.length() == 0) {
                getLogger().severe("ignoring sport lacking slug: " + jSONObject6);
            } else {
                int i2 = jSONObject6.getInt("id");
                int i3 = jSONObject6.getInt("display_order");
                if (i3 < 0) {
                    this.a.remove(i2);
                } else {
                    int i4 = jSONObject6.has("team_entity_type") ? jSONObject6.getInt("team_entity_type") : 0;
                    String str = "at";
                    if (jSONObject6.has("game_preposition")) {
                        str = jSONObject6.getString("game_preposition");
                    }
                    bpx bpxVar = new bpx(string, i2, jSONObject6.getString(Utilities.DB_KEY_IMAGE_NAME), i3, str, i4);
                    try {
                        int identifier = k().getResources().getIdentifier("team_entity_" + i4, "string", k().getPackageName());
                        if (identifier != 0) {
                            bpxVar.b(k().getResources().getString(identifier));
                        }
                        if (jSONObject6.has("game_title_type")) {
                            int identifier2 = k().getResources().getIdentifier("game_entity_" + jSONObject6.getInt("game_title_type"), "string", k().getPackageName());
                            if (identifier2 != 0) {
                                bpxVar.a(k().getResources().getString(identifier2));
                            }
                        }
                        if (jSONObject6.has("team_order")) {
                            bpxVar.a(jSONObject6.getInt("team_order"));
                        }
                    } catch (Throwable th) {
                        getLogger().log(Level.SEVERE, "problem: ", th);
                    }
                    this.a.put(bpxVar.a(), bpxVar);
                }
            }
        }
        k().a(this.a);
        JSONArray jSONArray2 = jSONObject.getJSONArray("league");
        for (int i5 = 0; i5 < jSONArray2.length(); i5++) {
            JSONObject jSONObject7 = jSONArray2.getJSONObject(i5);
            int i6 = jSONObject7.getInt("sport_id");
            int i7 = jSONObject7.getInt("id");
            String string2 = jSONObject7.getString("slug");
            bpx bpxVar2 = this.a.get(i6);
            if (bpxVar2 == null) {
                getLogger().warning("cannot find sport for id: " + i6 + " for league: " + jSONObject7);
                this.c.remove(Integer.valueOf(i7));
            } else {
                String c = c(bpxVar2.b(), string2);
                bpr bprVar = new bpr(jSONObject7, c, bpxVar2);
                if (bprVar.b() < 0) {
                    this.c.remove(Integer.valueOf(bprVar.g()));
                    this.b.remove(c);
                } else {
                    this.b.put(c, bprVar);
                    this.c.put(Integer.valueOf(bprVar.g()), bprVar);
                }
            }
        }
        DateTimeFormatter forPattern = DateTimeFormat.forPattern("yyyy-MM-dd");
        Map<Integer, bpb> E = E();
        JSONArray jSONArray3 = jSONObject.getJSONArray("competition");
        for (int i8 = 0; i8 < jSONArray3.length(); i8++) {
            try {
                JSONObject jSONObject8 = jSONArray3.getJSONObject(i8);
                String string3 = jSONObject8.getString(Utilities.DB_KEY_IMAGE_NAME);
                int i9 = jSONObject8.getInt("league_id");
                bpr bprVar2 = this.c.get(Integer.valueOf(i9));
                if (bprVar2 != null) {
                    int i10 = jSONObject8.getInt("id");
                    String string4 = jSONObject8.getString("start_date");
                    DateTime plusHours = forPattern.parseDateTime(jSONObject8.getString("end_date")).plusHours(23);
                    DateTime parseDateTime = forPattern.parseDateTime(string4);
                    bpb bpbVar = E.get(Integer.valueOf(i10));
                    bpb bpbVar2 = new bpb(i9, parseDateTime, string3, i10, plusHours);
                    if (bpbVar != null) {
                        bprVar2.a().remove(bpbVar);
                    }
                    bprVar2.a().add(bpbVar2);
                    E.put(Integer.valueOf(i10), bpbVar2);
                }
            } catch (Throwable th2) {
                getLogger().log(Level.INFO, "Cannot parse competition: " + th2);
            }
        }
        if (jSONObject.has("league_group")) {
            JSONArray jSONArray4 = jSONObject.getJSONArray("league_group");
            for (int i11 = 0; i11 < jSONArray4.length(); i11++) {
                bpq bpqVar = new bpq(jSONArray4.getJSONObject(i11));
                String e = bpqVar.e("slug");
                if (e != null && e.length() != 0) {
                    int f = bpqVar.f("id");
                    int f2 = bpqVar.f("display_order");
                    if (f2 < 0) {
                        this.d.remove(Integer.valueOf(f));
                    } else {
                        String e2 = bpqVar.e(Utilities.DB_KEY_IMAGE_NAME);
                        String e3 = bpqVar.e("short_name");
                        String e4 = bpqVar.e("super_short_name");
                        int f3 = bpqVar.f("league_id");
                        boolean g = bpqVar.g("rule_global_auto_select");
                        bpr bprVar3 = this.c.get(Integer.valueOf(f3));
                        if (bprVar3 == null) {
                            getLogger().warning("cannot find league for id: " + f3 + " for league_group: " + e2);
                            this.d.remove(Integer.valueOf(f));
                        } else {
                            bps bpsVar = new bps(bprVar3, f, e, e2, e3, e4, f2, g);
                            this.d.put(Integer.valueOf(bpsVar.c()), bpsVar);
                        }
                    }
                }
            }
            if (jSONObject.has("onlineresourcecategory")) {
                JSONArray jSONArray5 = jSONObject.getJSONArray("onlineresourcecategory");
                for (int i12 = 0; i12 < jSONArray5.length(); i12++) {
                    OnlineResourceCategory onlineResourceCategory = new OnlineResourceCategory(jSONArray5.getJSONObject(i12));
                    this.e.put(onlineResourceCategory.a(), onlineResourceCategory);
                }
                if (jSONObject.has("alert_settings")) {
                    JSONObject jSONObject9 = jSONObject.getJSONObject("alert_settings");
                    if (jSONObject9 != null && (jSONObject4 = jSONObject9.getJSONObject("default")) != null) {
                        for (Integer num : this.c.keySet()) {
                            AlertSettings alertSettings = new AlertSettings(new JSONObject(jSONObject4.toString()));
                            String num2 = num.toString();
                            if (jSONObject9.has(num2) && (jSONObject5 = jSONObject9.getJSONObject(num2)) != null) {
                                alertSettings.a(jSONObject5);
                            }
                            this.f.put(num, alertSettings);
                        }
                    }
                } else if (!"2.0/".equals("2.3/")) {
                    throw new Exception("alert_settings missing");
                }
                if (jSONObject.has("sponsored_leagues")) {
                    JSONObject jSONObject10 = jSONObject.getJSONObject("sponsored_leagues");
                    Iterator<String> keys = jSONObject10.keys();
                    while (keys.hasNext()) {
                        try {
                            String next = keys.next();
                            JSONArray jSONArray6 = jSONObject10.getJSONArray(next);
                            int parseInt = Integer.parseInt(next);
                            ArrayList arrayList = new ArrayList();
                            for (int i13 = 0; i13 < jSONArray6.length(); i13++) {
                                arrayList.add(new boz(jSONArray6.getJSONObject(i13)));
                            }
                            this.g.put(Integer.valueOf(parseInt), arrayList);
                        } catch (Throwable th3) {
                            getLogger().log(Level.SEVERE, "problem: ", th3);
                        }
                    }
                }
                if (jSONObject.has("fantasy_settings")) {
                    JSONObject jSONObject11 = jSONObject.getJSONObject("fantasy_settings");
                    if (jSONObject11.has("alerts") && (jSONObject3 = (jSONObject2 = jSONObject11.getJSONObject("alerts")).getJSONObject("default")) != null) {
                        Iterator<String> keys2 = jSONObject2.keys();
                        while (keys2.hasNext()) {
                            try {
                                String next2 = keys2.next();
                                if (!"default".equals(next2)) {
                                    int parseInt2 = Integer.parseInt(next2);
                                    FantasySettingsCustomizations fantasySettingsCustomizations = new FantasySettingsCustomizations(new JSONObject(jSONObject3.toString()));
                                    fantasySettingsCustomizations.a(jSONObject2.getJSONObject(next2));
                                    this.h.put(Integer.valueOf(parseInt2), fantasySettingsCustomizations);
                                }
                            } catch (Throwable th4) {
                                getLogger().log(Level.SEVERE, "problem: ", th4);
                            }
                        }
                    }
                }
                k().sendBroadcast(new Intent("SportInfo"), "com.thuuz.permission.local.broadcast");
                return;
            }
            throw new Exception("online resource categories missing");
        }
        throw new Exception("league_groups missing");
    }

    public static String c(String str, String str2) {
        return str + "_" + str2;
    }

    private Map<Integer, bpb> E() {
        HashMap hashMap = new HashMap();
        Iterator<String> it = this.b.keySet().iterator();
        while (it.hasNext()) {
            for (bpb bpbVar : this.b.get(it.next()).a()) {
                hashMap.put(Integer.valueOf(bpbVar.b()), bpbVar);
            }
        }
        return hashMap;
    }

    public Map<Integer, bps> b() {
        return this.d;
    }

    public Map<Integer, bpr> c() {
        return this.c;
    }

    public Map<String, bpr> d() {
        return this.b;
    }

    public Map<Integer, OnlineResourceCategory> f() {
        return this.e;
    }

    public SparseArray<bpx> g() {
        return this.a;
    }

    public Map<Integer, AlertSettings> h() {
        return this.f;
    }

    public void a(IService iService) {
        for (bpr bprVar : this.c.values()) {
            if (bprVar.a(iService.n())) {
                getLogger().info(bprVar.d() + " is in season");
            }
        }
    }

    private void F() {
        boolean z;
        int i;
        try {
            String[] strArr = App.a;
            int length = strArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                String str = strArr[i2];
                if (k().W().contains(str)) {
                    if (k().W().getBoolean(str, false)) {
                        bpr bprVar = this.b.get(str.substring("pref_".length()));
                        if (bprVar != null) {
                            k().a(bpt.a(Integer.valueOf(bprVar.g()), (AlertSettings) null));
                            i = i3 + 1;
                            k().a(str);
                        } else {
                            getLogger().warning("cannot find league for pref: " + str);
                        }
                    }
                    i = i3;
                    k().a(str);
                } else {
                    i = i3;
                }
                i2++;
                i3 = i;
            }
            if (i3 > 0) {
                getLogger().warning("converted " + i3 + " sport prefs");
            }
            String string = k().X().getString("pref_tv_provider_id", null);
            if (string != null) {
                Iterator<bpu> it = k().ax().values().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (string.equals(it.next().d())) {
                        getLogger().warning("not converting tv_provider because found (already) defined location");
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    new bzv(this, string).execute(k().X().getString("pref_zipcode", ""));
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void b(String str) {
        cak.a(k().br(), str);
    }

    public Map<Integer, List<boz>> i() {
        return this.g;
    }

    public Map<Integer, FantasySettingsCustomizations> j() {
        return this.h;
    }
}
