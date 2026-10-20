package com.thuuz.gtv;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Display;
import android.webkit.WebView;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity;
import com.thuuz.gtv.activity.nobar.OnboardPage1Activity;
import com.thuuz.gtv.activity.sliding.SlidingMenuItemType;
import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.data.Locations;
import com.thuuz.gtv.settings.SettingsSoundsActivity;
import com.thuuz.gtv.util.ServerChoice;
import defpackage.ag;
import defpackage.ay;
import defpackage.baw;
import defpackage.bbb;
import defpackage.bbg;
import defpackage.bbh;
import defpackage.bbi;
import defpackage.bbj;
import defpackage.bbp;
import defpackage.bmc;
import defpackage.bph;
import defpackage.bpt;
import defpackage.bpu;
import defpackage.bpx;
import defpackage.bqa;
import defpackage.bqd;
import defpackage.bqe;
import defpackage.bqv;
import defpackage.bzy;
import defpackage.caz;
import defpackage.cby;
import defpackage.cr;
import defpackage.dz;
import defpackage.ed;
import defpackage.ee;
import defpackage.ej;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.http.HttpVersion;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/* loaded from: classes.dex */
public class App extends Application {
    private Locations A;
    private bqe B;
    private boolean G;
    private boolean H;
    private volatile ag I;
    private boolean J;
    private SparseArray<bpx> K;
    private Boolean L;
    private ej y;
    private PlatformOperations z;
    private static final String p = System.getProperty("line.separator");
    private static final String q = ServerChoice.m + "severe_logging";
    private static final String r = App.class.getSimpleName() + ":thuuz";
    public static String[] a = {"pref_baseball_mlb", "pref_basketball_nba", "pref_basketball_ncaa", "pref_football_nfl", "pref_football_ncaa", "pref_hockey_nhl", "pref_soccer_chlg", "pref_soccer_epl", "pref_soccer_seri", "pref_soccer_liga", "pref_soccer_bund", "pref_soccer_mls", "pref_cricket_ipl", "pref_cricket_icc", "pref_rugby_irb"};
    public static final String b = String.valueOf(-1);
    public static final String c = String.valueOf(-2);
    public static final String d = String.valueOf(-5);
    public static final String e = String.valueOf(-7);
    private static Level s = Level.WARNING;
    private static final Integer[] t = {21, 22, 23, 20, 19, 4, 3};
    private static final Set<Integer> u = Collections.unmodifiableSet(new HashSet(Arrays.asList(t)));
    public final String f = "app_opened";
    public final String g = "tv_on";
    public final String h = "tv_off";
    public final String i = "notifications_on";
    public final String j = "notifications_off";
    public final String k = "prefs_set";
    public final String l = "prefs_off";
    public final String m = "facebook_on";
    public final String n = "facebook_off";
    public final String o = "twitter_on";
    private final Map<LogRecord, String> v = Collections.synchronizedMap(new Cache(5));
    private final Handler w = new bbi(this, null);
    private final Rect x = new Rect();
    private final Object C = new Object();
    private volatile baw D = null;
    private final Object E = new Object();
    private volatile bbb F = null;
    private boolean M = true;
    private Boolean N = null;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        try {
            ch();
            ServerChoice.a(bP(), null);
            ad();
            b().info("is large no-touch: " + e() + ", screen: " + cn());
            cm();
            cg();
        } catch (Throwable th) {
            Log.e("thuuz_init", "cannot initialize app: ", th);
        }
    }

    public void a() {
        Intent intent = new Intent(this, (Class<?>) ThuuzService.class);
        intent.setAction("wake");
        b().info("sending service wakeup");
        startService(intent);
    }

    private synchronized ag cf() {
        ag agVar;
        Throwable th;
        if (this.I != null) {
            agVar = this.I;
        } else {
            try {
                File file = new File(getCacheDir(), "volley");
                try {
                    String packageName = getPackageName();
                    PackageManager packageManager = getPackageManager();
                    if (packageManager != null) {
                        String str = packageName + "/" + packageManager.getPackageInfo(packageName, 0).versionCode;
                    }
                } catch (Throwable th2) {
                }
                ay ayVar = new ay();
                if (Build.VERSION.SDK_INT <= 23) {
                    b().log(Level.INFO, "using NoSSLv3 factory");
                    ayVar = new ay(null, new cby());
                }
                agVar = new ag(new bbh(this, file), new bbg(this, ayVar));
                try {
                    agVar.a();
                } catch (Throwable th3) {
                    th = th3;
                    b().log(Level.SEVERE, "problem: ", th);
                    return agVar;
                }
            } catch (Throwable th4) {
                agVar = null;
                th = th4;
            }
        }
        return agVar;
    }

    private void cg() {
        String[] a2;
        try {
            String string = X().getString(ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN, null);
            if (string != null && (a2 = a(Base64.decode(string, 0))) != null) {
                String d2 = d(a2[0], a2[1]);
                if (d2.length() > 0) {
                    b().info("converted old token");
                    s(d2);
                } else {
                    b().severe("cannot convert old token");
                }
                g(ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN);
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void ch() {
        s = Level.WARNING;
        try {
            String name = bzy.class.getName();
            Class<?> cls = Class.forName(("com.thuuz.") + "gtv.util.BaseLogger");
            if (cls != null && cls.getName().equals(name)) {
                s = Level.FINE;
                Log.i("thuuz", "logging level set to 'fine' after finding un-obfuscated class.");
            }
        } catch (Throwable th) {
        }
        try {
            for (Handler handler : Logger.getLogger("").getHandlers()) {
                handler.setFormatter(new bbj(this, null));
                handler.setLevel(s);
                Log.d("thuuz_init", "handler: " + handler.getClass().getName() + " at level: " + handler.getLevel());
            }
            Logger.getLogger("").addHandler(this.w);
            Thread.setDefaultUncaughtExceptionHandler(new caz(Thread.getDefaultUncaughtExceptionHandler(), this));
        } catch (Throwable th2) {
            Log.e("thuuz_init", "cannot initialize logging: ", th2);
        }
    }

    public Logger b() {
        Logger logger = Logger.getLogger(r);
        if (logger.getLevel() != s) {
            logger.setLevel(s);
        }
        return logger;
    }

    public boolean c() {
        return this.v.size() > 0;
    }

    public void d() {
        Intent intent = new Intent(this, (Class<?>) ThuuzService.class);
        intent.setAction("CLEAR_TV_SCHEDULES");
        startService(intent);
    }

    public boolean e() {
        if (this.N == null) {
            this.N = Boolean.valueOf(Build.VERSION.SDK_INT >= 12 && ci() && as());
        }
        return this.N.booleanValue();
    }

    public boolean f() {
        try {
            PackageManager packageManager = getPackageManager();
            if (packageManager == null) {
                return false;
            }
            return packageManager.hasSystemFeature("com.google.android.tv");
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Cannot handle isGtv test for version name: ", th);
            return false;
        }
    }

    private boolean ci() {
        return getResources().getConfiguration().touchscreen == 1;
    }

    public boolean g() {
        return getResources().getConfiguration().orientation == 1;
    }

    public void a(String str, boolean z) {
        try {
            if (str == null) {
                b().severe("null name!  ignoring...");
            } else {
                SharedPreferences.Editor edit = X().edit();
                edit.putBoolean(str, z);
                edit.commit();
            }
        } catch (OutOfMemoryError e2) {
            bX();
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public boolean h() {
        return W().getBoolean("pref_show_notifications", true);
    }

    public void a(String str) {
        try {
            SharedPreferences.Editor edit = W().edit();
            edit.remove(str);
            edit.commit();
        } catch (OutOfMemoryError e2) {
            bX();
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public boolean i() {
        return bi() != SIGN_IN_TYPE.SIGN_IN_TYPE_ANONYMOUS;
    }

    public void a(String str, String str2) {
        try {
            SharedPreferences.Editor edit = W().edit();
            edit.putString(str, str2);
            edit.commit();
        } catch (OutOfMemoryError e2) {
            bX();
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public int j() {
        try {
            int parseInt = Integer.parseInt(W().getString("pref_min_gec", "-1"));
            if (parseInt == -1) {
                return 90;
            }
            return parseInt;
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Cannot handle: ", th);
            return 90;
        }
    }

    public void a(Integer num) {
        a("pref_min_gec", num + "");
    }

    public ALERTS_FILTER k() {
        ALERTS_FILTER alerts_filter = ALERTS_FILTER.ALERTS_FILTER_ALL;
        String string = X().getString("alerts_filter", null);
        if (string != null && ALERTS_FILTER.ALERTS_FILTER_TEAM.a().equals(string)) {
            return ALERTS_FILTER.ALERTS_FILTER_TEAM;
        }
        return alerts_filter;
    }

    public void l() {
        Intent intent = new Intent(this, (Class<?>) ThuuzService.class);
        intent.setAction("updateTeams");
        startService(intent);
    }

    public boolean m() {
        return b.equals(Z());
    }

    public JSONObject n() {
        try {
            return new JSONObject(X().getString("my_fav_leagues", "{}"));
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }

    public Set<Integer> o() {
        HashSet hashSet = new HashSet();
        try {
            JSONObject n = n();
            if (n != null) {
                Iterator<String> keys = n.keys();
                while (keys.hasNext()) {
                    hashSet.add(Integer.valueOf(keys.next()));
                }
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
        return hashSet;
    }

    public bpt a(int i) {
        try {
            JSONObject n = n();
            if (n != null) {
                String string = n.getString(String.valueOf(i));
                if (string.length() > 0) {
                    return new bpt(new JSONObject(string));
                }
            }
        } catch (Throwable th) {
            b().log(Level.WARNING, "no fav league setting: ", th);
        }
        return null;
    }

    public void a(bpt bptVar) {
        JSONObject n;
        try {
            Set<Integer> o = o();
            if (bptVar.a() != null && (n = n()) != null) {
                n.put(String.valueOf(bptVar.a()), bptVar.ar());
                b("my_fav_leagues", n.toString());
            }
            if (o.size() == 0) {
                Y();
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void b(int i) {
        try {
            JSONObject n = n();
            if (n != null) {
                n.remove(String.valueOf(i));
                b("my_fav_leagues", n.toString());
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
    }

    public boolean p() {
        Iterator<Integer> it = o().iterator();
        while (it.hasNext()) {
            bpt a2 = a(it.next().intValue());
            if (a2 != null && a2.b().booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public JSONObject q() {
        try {
            return new JSONObject(X().getString("my_fav_league_groups", "{}"));
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }

    public Set<Integer> r() {
        HashSet hashSet = new HashSet();
        try {
            JSONObject q2 = q();
            if (q2 != null) {
                Iterator<String> keys = q2.keys();
                while (keys.hasNext()) {
                    hashSet.add(Integer.valueOf(keys.next()));
                }
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
        return hashSet;
    }

    public bpt c(int i) {
        try {
            JSONObject q2 = q();
            if (q2 != null) {
                String string = q2.getString(String.valueOf(i));
                if (string.length() > 0) {
                    return new bpt(new JSONObject(string));
                }
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
        return null;
    }

    public void b(bpt bptVar) {
        JSONObject q2;
        try {
            Set<Integer> r2 = r();
            if (bptVar.a() != null && (q2 = q()) != null) {
                q2.put(String.valueOf(bptVar.a()), bptVar.ar());
                b("my_fav_league_groups", q2.toString());
            }
            if (r2.size() == 0) {
                Y();
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void d(int i) {
        try {
            JSONObject q2 = q();
            if (q2 != null) {
                q2.remove(String.valueOf(i));
                b("my_fav_league_groups", q2.toString());
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
    }

    public boolean s() {
        Iterator<Integer> it = r().iterator();
        while (it.hasNext()) {
            bpt c2 = c(it.next().intValue());
            if (c2 != null && c2.b().booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            g("my_fantasy_team_prefs");
        } else {
            b("my_fantasy_team_prefs", jSONObject.toString());
        }
    }

    public void e(int i) {
        try {
            JSONObject jSONObject = new JSONObject(X().getString("my_fantasy_team_prefs", "{}"));
            String valueOf = String.valueOf(i);
            if (jSONObject.has(valueOf)) {
                jSONObject.remove(valueOf);
                a(jSONObject);
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
    }

    public Set<bph> t() {
        HashSet hashSet = new HashSet();
        try {
            JSONObject jSONObject = new JSONObject(X().getString("my_fantasy_team_prefs", "{}"));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                if (jSONObject2 != null) {
                    bph bphVar = new bph(jSONObject2);
                    bphVar.a(Integer.valueOf(next).intValue());
                    hashSet.add(bphVar);
                }
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
        return hashSet;
    }

    public bph f(int i) {
        JSONObject jSONObject;
        try {
            JSONObject jSONObject2 = new JSONObject(X().getString("my_fantasy_team_prefs", "{}"));
            if (jSONObject2.has(String.valueOf(i)) && (jSONObject = jSONObject2.getJSONObject(String.valueOf(i))) != null) {
                bph bphVar = new bph(jSONObject);
                bphVar.a(i);
                return bphVar;
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
        return null;
    }

    public void a(bph bphVar) {
        if (bphVar == null) {
            g("my_fantasy_team_prefs");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(X().getString("my_fantasy_team_prefs", "{}"));
            jSONObject.put(String.valueOf(bphVar.a()), bphVar.ar());
            b("my_fantasy_team_prefs", jSONObject.toString());
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
    }

    public JSONObject u() {
        try {
            return new JSONObject(X().getString("my_fav_teams", "{}"));
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem getting fav teams pref: ", th);
            return null;
        }
    }

    public void a(ArrayList<bmc> arrayList) {
        JSONArray jSONArray = new JSONArray();
        try {
            Iterator<bmc> it = arrayList.iterator();
            while (it.hasNext()) {
                JSONObject b2 = it.next().b();
                if (b2 != null) {
                    jSONArray.put(b2);
                }
            }
            b("my_sliding_drawer_state", jSONArray.toString());
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem setting drawer state: " + th.getMessage());
        }
    }

    public JSONArray v() {
        JSONArray jSONArray = new JSONArray();
        try {
            return new JSONArray(X().getString("my_sliding_drawer_state", "[]"));
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
            return jSONArray;
        }
    }

    public ArrayList<bmc> w() {
        ArrayList<bmc> arrayList = new ArrayList<>();
        JSONArray v = v();
        for (int i = 0; i < v.length(); i++) {
            try {
                JSONObject jSONObject = v.getJSONObject(i);
                SlidingMenuItemType a2 = SlidingMenuItemType.a(jSONObject.getInt("type"));
                if (a2 != SlidingMenuItemType.UNKNOWN) {
                    arrayList.add(new bmc(a2, jSONObject.getInt("id"), jSONObject.getBoolean("expanded")));
                }
            } catch (Throwable th) {
                b().log(Level.SEVERE, "problem: ", th);
            }
        }
        return arrayList;
    }

    public TreeSet<Integer> x() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        try {
            JSONObject u2 = u();
            if (u2 != null) {
                Iterator<String> keys = u2.keys();
                while (keys.hasNext()) {
                    treeSet.add(Integer.valueOf(keys.next()));
                }
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
        return treeSet;
    }

    public bqa g(int i) {
        try {
            JSONObject u2 = u();
            if (u2 != null) {
                String valueOf = String.valueOf(i);
                if (u2.has(valueOf)) {
                    String string = u2.getString(valueOf);
                    if (string.length() > 0) {
                        return new bqa(new JSONObject(string));
                    }
                }
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
        return null;
    }

    public void a(bqa bqaVar) {
        JSONObject u2;
        try {
            TreeSet<Integer> x = x();
            Integer a2 = bqaVar.a();
            if (a2 != null && (u2 = u()) != null) {
                u2.put(String.valueOf(a2), bqaVar.ar());
                b("my_fav_teams", u2.toString());
            }
            if (x.size() == 0) {
                Y();
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem addOrUpdateFavTeam: ", th);
        }
    }

    public void h(int i) {
        try {
            JSONObject u2 = u();
            if (u2 != null) {
                u2.remove(String.valueOf(i));
                b("my_fav_teams", u2.toString());
                Intent intent = new Intent(this, (Class<?>) ThuuzService.class);
                intent.setAction("upd_teams");
                startService(intent);
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
    }

    public boolean y() {
        Iterator<Integer> it = x().iterator();
        while (it.hasNext()) {
            bqa g = g(it.next().intValue());
            if (g != null && (g.b().booleanValue() || g.f().booleanValue() || g.g().booleanValue() || g.h().booleanValue())) {
                return true;
            }
        }
        return false;
    }

    public boolean z() {
        return i() && !t().isEmpty();
    }

    public boolean A() {
        Iterator<bph> it = t().iterator();
        while (it.hasNext()) {
            if (it.next().f()) {
                return true;
            }
        }
        return false;
    }

    public String a(Set<Integer> set) {
        if (set != null) {
            try {
                if (!set.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    boolean z = true;
                    for (Integer num : set) {
                        if (!z) {
                            sb.append(",");
                        } else {
                            z = false;
                        }
                        sb.append(num.toString());
                    }
                    return sb.toString();
                }
            } catch (Throwable th) {
                b().log(Level.SEVERE, "problem: ", th);
            }
        }
        return "";
    }

    public String B() {
        return a(o());
    }

    public String C() {
        return a(r());
    }

    public String D() {
        return a(x());
    }

    public void b(String str) {
        b().info("set last activity: " + str);
        b("lastActivity", str);
    }

    public void i(int i) {
        a("friendstab", i);
    }

    public int E() {
        return X().getInt("friendstab", -1);
    }

    public void j(int i) {
        a("LastFTeam", i);
    }

    public int F() {
        return X().getInt("LastFTeam", -1);
    }

    public void c(String str) {
        b("gcm_reg", str);
        bqv.a();
    }

    public String G() {
        return X().getString("alert_snd", null);
    }

    public void d(String str) {
        b("alert_snd", str);
    }

    public SettingsSoundsActivity.VIB_TYPE H() {
        return SettingsSoundsActivity.VIB_TYPE.a(X().getInt("alert_vib", SettingsSoundsActivity.VIB_TYPE.USE_DEFAULT.a()));
    }

    public void a(SettingsSoundsActivity.VIB_TYPE vib_type) {
        a("alert_vib", vib_type.a());
    }

    public SettingsSoundsActivity.TOAST_TYPE I() {
        return SettingsSoundsActivity.TOAST_TYPE.a(X().getInt("alert_toast", SettingsSoundsActivity.TOAST_TYPE.LONG.b()));
    }

    public void a(SettingsSoundsActivity.TOAST_TYPE toast_type) {
        a("alert_toast", toast_type.b());
    }

    public boolean J() {
        return e.equals(Z());
    }

    public void k(int i) {
        a("feed_tab", i);
    }

    public int K() {
        return X().getInt("feed_tab", -1);
    }

    public void L() {
        Intent intent = new Intent(this, (Class<?>) ThuuzService.class);
        intent.setAction("SYNC_FANTASY_TEAMS");
        startService(intent);
    }

    public void M() {
        b("need_google_reg", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
    }

    public void a(boolean z) {
        a("has_fant_accts", z);
    }

    public boolean l(int i) {
        return h("lg_auto_sel").contains(Integer.valueOf(i));
    }

    public void m(int i) {
        b("lg_auto_sel", i);
    }

    public boolean n(int i) {
        return h("league_sel").contains(Integer.valueOf(i));
    }

    public void o(int i) {
        b("league_sel", i);
    }

    public String N() {
        try {
            Account[] accountsByType = AccountManager.get(this).getAccountsByType("com.google");
            LinkedList linkedList = new LinkedList();
            for (Account account : accountsByType) {
                linkedList.add(account.name);
            }
            if (!linkedList.isEmpty() && linkedList.get(0) != null) {
                String[] split = ((String) linkedList.get(0)).split("@");
                if (split.length > 0 && split[0] != null) {
                    return split[0];
                }
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
        return "";
    }

    public Set<Integer> O() {
        return u;
    }

    public boolean P() {
        return X().getBoolean("hasManuallyOpenedDrawer", false);
    }

    public void b(boolean z) {
        a("hasManuallyOpenedDrawer", z);
    }

    public String a(AbstractActivity abstractActivity) {
        if (!a((Activity) abstractActivity)) {
            return "normal";
        }
        if ((getResources().getConfiguration().screenLayout & 15) < 4) {
            return "large";
        }
        return "xlarge";
    }

    public boolean e(String str) {
        try {
            return checkCallingOrSelfPermission(str) == 0;
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
            return false;
        }
    }

    public String Q() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    public void a(long j) {
        a("last_alert_ts", j);
    }

    public long R() {
        return X().getLong("last_alert_ts", 0L);
    }

    public void c(boolean z) {
        this.J = z;
    }

    public boolean S() {
        return this.J;
    }

    public void p(int i) {
        a("fant_tab_index", i);
    }

    public int T() {
        return X().getInt("fant_tab_index", 0);
    }

    public void U() {
        cl().a((Map<String, String>) ((ed) new ed().b()).a());
    }

    public void a(SparseArray<bpx> sparseArray) {
        this.K = new SparseArray<>(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            int keyAt = sparseArray.keyAt(i);
            this.K.put(keyAt, sparseArray.get(keyAt));
        }
    }

    public List<bpx> V() {
        ArrayList arrayList = new ArrayList();
        if (this.K != null) {
            for (int i = 0; i < this.K.size(); i++) {
                arrayList.add(this.K.get(this.K.keyAt(i)));
            }
        }
        return arrayList;
    }

    public boolean f(String str) {
        if (this.K == null) {
            return false;
        }
        for (int i = 0; i < this.K.size(); i++) {
            bpx bpxVar = this.K.get(this.K.keyAt(i));
            if (bpxVar != null && bpxVar.b() != null && bpxVar.b().equals(str)) {
                return bpxVar.f() == 1;
            }
        }
        return false;
    }

    /* loaded from: classes.dex */
    class Cache extends LinkedHashMap<LogRecord, String> {
        private final int capacity;

        public Cache(int i) {
            super(i + 1, 1.1f, true);
            this.capacity = i;
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<LogRecord, String> entry) {
            return size() > this.capacity;
        }
    }

    public SharedPreferences W() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }

    public SharedPreferences X() {
        return getSharedPreferences("THUUZ_PREFS_INVIS", 0);
    }

    public void b(String str, String str2) {
        try {
            if (str == null) {
                b().severe("null name!  ignoring...");
                return;
            }
            SharedPreferences.Editor edit = X().edit();
            if (str2 == null) {
                edit.remove(str);
            } else {
                edit.putString(str, str2);
            }
            edit.commit();
        } catch (OutOfMemoryError e2) {
            bX();
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public void g(String str) {
        if (str == null) {
            b().severe("null name!  ignoring...");
            return;
        }
        SharedPreferences.Editor edit = X().edit();
        edit.remove(str);
        edit.commit();
    }

    public void a(String str, long j) {
        try {
            if (str == null) {
                b().severe("null name!  ignoring...");
            } else {
                SharedPreferences.Editor edit = X().edit();
                edit.putLong(str, j);
                edit.commit();
            }
        } catch (OutOfMemoryError e2) {
            bX();
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public void a(String str, int i) {
        try {
            if (str == null) {
                b().severe("null name!  ignoring...");
            } else {
                SharedPreferences.Editor edit = X().edit();
                edit.putInt(str, i);
                edit.commit();
            }
        } catch (OutOfMemoryError e2) {
            bX();
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public static String a(Set<Integer> set, String str) {
        if (set.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(str);
        }
        sb.setLength(sb.length() - str.length());
        return sb.toString();
    }

    public void Y() {
        try {
            TreeSet<Integer> x = x();
            Set<Integer> o = o();
            Set<Integer> r2 = r();
            if (x.size() + o.size() + r2.size() == 1) {
                try {
                    sendBroadcast(new Intent("has_prefs"), "com.thuuz.permission.local.broadcast");
                    B(b);
                } catch (Throwable th) {
                    b().log(Level.SEVERE, "problem: ", th);
                }
            }
            if (aJ() || x.size() <= 0) {
                return;
            }
            if (o.size() > 0 || r2.size() > 0) {
                if (this.z != null) {
                    this.z.sendEvent(this, 1);
                }
                d(true);
            }
        } catch (Throwable th2) {
            b().log(Level.SEVERE, "problem: ", th2);
        }
    }

    public void b(String str, int i) {
        if (str == null) {
            b().severe("empty value!");
            return;
        }
        Set<Integer> h = h(str);
        h.add(Integer.valueOf(i));
        b(str, a(h, ","));
    }

    public Set<Integer> h(String str) {
        return i(X().getString(str, null));
    }

    public Set<Integer> i(String str) {
        HashSet hashSet = new HashSet();
        if (str != null && str.length() > 0) {
            for (String str2 : Arrays.asList(str.split(","))) {
                if (str2.length() > 0) {
                    try {
                        hashSet.add(Integer.valueOf(Integer.parseInt(str2)));
                    } catch (Throwable th) {
                        b().log(Level.SEVERE, "problem: ", th);
                    }
                }
            }
        }
        return hashSet;
    }

    public String Z() {
        return X().getString("filter", e);
    }

    public String[] aa() {
        String Z = Z();
        if (Z == null) {
            return null;
        }
        return j(Z);
    }

    public boolean ab() {
        return c.equals(Z());
    }

    public boolean ac() {
        return d.equals(Z());
    }

    public String[] j(String str) {
        if (str == null) {
            return null;
        }
        if (b.equals(str)) {
            return new String[]{b, b};
        }
        if (d.equals(str)) {
            return new String[]{d, d};
        }
        if (c.equals(str)) {
            return new String[]{c, c};
        }
        if (e.equals(str)) {
            return new String[]{e, e};
        }
        String[] split = str.split("\\_");
        if (split.length < 3) {
            split = str.split("\\.");
            if (split.length < 3) {
                b().warning("cannot parse internal name: " + str);
                return null;
            }
        }
        return new String[]{split[1], split[2]};
    }

    public String ad() {
        UUID uuid = null;
        String string = X().getString("guid", null);
        if (string != null) {
            return string;
        }
        try {
            String string2 = Settings.Secure.getString(getContentResolver(), "android_id");
            if (string2 != null && !"9774d56d682e549c".equals(string2)) {
                uuid = UUID.nameUUIDFromBytes(string2.getBytes("utf8"));
                b().info("got uuid: " + uuid);
            }
        } catch (Throwable th) {
        }
        if (uuid == null) {
            b().info("creating and storing random uuid");
            uuid = UUID.randomUUID();
        }
        String uuid2 = uuid.toString();
        if (uuid2.length() > 40) {
            uuid2 = uuid2.substring(0, 40);
        }
        b("guid", uuid2);
        return uuid2;
    }

    public String ae() {
        ArrayList arrayList = new ArrayList(this.v.keySet());
        String str = "";
        if (b().isLoggable(Level.INFO)) {
            str = "info-level logging ON\n";
        }
        StringBuilder sb = new StringBuilder(str);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append(simpleFormatter.format((LogRecord) it.next()));
            sb.append("\n");
        }
        return sb.toString();
    }

    public void af() {
        this.v.clear();
    }

    public String ag() {
        try {
            String packageName = getPackageName();
            PackageManager packageManager = getPackageManager();
            if (packageManager == null) {
                return "?";
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo.versionName + " (" + packageInfo.versionCode + ")";
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
            return "?";
        }
    }

    public String ah() {
        try {
            String packageName = getPackageName();
            PackageManager packageManager = getPackageManager();
            if (packageManager == null) {
                return "?";
            }
            return packageManager.getPackageInfo(packageName, 0).versionName;
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
            return "?";
        }
    }

    public int ai() {
        try {
            String packageName = getPackageName();
            PackageManager packageManager = getPackageManager();
            if (packageManager == null) {
                return -1;
            }
            return packageManager.getPackageInfo(packageName, 0).versionCode;
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    public String aj() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n");
        sb.append("Thuuz ");
        if (aQ()) {
            sb.append("GTV: ");
        } else if (aR()) {
            sb.append("Tablet: ");
        } else if (ak()) {
            sb.append("Amzn: ");
        } else {
            sb.append("Mobile: ");
            if (f()) {
                sb.append(" with .tv feature TRUE! ; isGtv() fn: ");
                sb.append(f());
                sb.append(" ");
            } else if (e()) {
                sb.append(" with no-touch feature TRUE! ; isLargeNoTouchDevice() fn: ");
                sb.append(e());
                sb.append(" ");
            }
        }
        sb.append(ag());
        sb.append("\n");
        sb.append("android.version: ").append(Build.VERSION.RELEASE);
        sb.append("\n");
        sb.append("brand/model: ").append(Build.BRAND);
        sb.append("/").append(Build.MODEL);
        sb.append("\n");
        if (aU().length() > 0) {
            sb.append("user: ");
            sb.append(aU());
            sb.append("\n");
        } else if (by() != null && by().length() > 0) {
            sb.append("email: ");
            sb.append(by());
            sb.append("\n");
        } else {
            sb.append("g acct: ");
            sb.append(N());
            sb.append("\n");
        }
        sb.append("uuid: ");
        sb.append(ad());
        sb.append("\n");
        sb.append("rooted: ");
        sb.append(bQ());
        sb.append("\n");
        return sb.toString();
    }

    public boolean ak() {
        return "com.thuuz.amzn".equals(getPackageName());
    }

    public String al() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("FB connected: ");
        sb.append(aZ());
        sb.append("\n");
        sb.append("Notify Global: ");
        sb.append(bD());
        sb.append("\n");
        sb.append("Notify share: ");
        sb.append(bB());
        sb.append("\n");
        sb.append("Notify invite: ");
        sb.append(bC());
        sb.append("\n");
        sb.append("Display scores: ");
        sb.append(aK());
        sb.append("\n");
        sb.append("Last game id: ");
        sb.append(bL());
        sb.append("\n");
        bpu a2 = ax().a();
        if (a2 != null) {
            sb.append("TV provider: ");
            sb.append(a2.d());
            sb.append(" ");
            sb.append(a2.e());
            sb.append("\n");
        }
        sb.append("GCM Registration ID: ");
        sb.append(aT());
        sb.append("\n");
        sb.append("\n");
        Iterator<Integer> it = o().iterator();
        while (it.hasNext()) {
            sb.append("league: ").append(a(it.next().intValue()));
            sb.append("\n");
        }
        sb.append("\n");
        Iterator<Integer> it2 = r().iterator();
        while (it2.hasNext()) {
            sb.append("l_group: ").append(c(it2.next().intValue()));
            sb.append("\n");
        }
        sb.append("\n");
        Iterator<Integer> it3 = x().iterator();
        while (it3.hasNext()) {
            sb.append("team: ").append(g(it3.next().intValue()));
            sb.append("\n");
        }
        sb.append("\n");
        for (bph bphVar : t()) {
            sb.append("fantasy team: ").append(bphVar.a()).append(" ").append(bphVar);
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

    public String am() {
        StringBuilder sb = new StringBuilder(aj());
        sb.append("\n");
        a(sb);
        return sb.toString();
    }

    private void a(StringBuilder sb) {
        try {
            ActivityManager activityManager = (ActivityManager) getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            sb.append("  ");
            sb.append("mem:");
            sb.append(memoryInfo.availMem);
            sb.append("  ");
            if (memoryInfo.lowMemory) {
                sb.append("isLowMem:");
                sb.append(memoryInfo.lowMemory);
                sb.append("!!");
            }
            sb.append("  ");
            sb.append("screen: ");
            sb.append(an().width());
            sb.append("x");
            sb.append(an().height());
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
    }

    public String a(String[] strArr) {
        return strArr[0] + "." + strArr[1];
    }

    public Rect an() {
        return this.x;
    }

    public void a(int i, int i2) {
        this.x.set(0, 0, i, i2);
    }

    public String ao() {
        String str;
        Throwable th;
        String str2 = "androphone";
        try {
            if (aR()) {
                str2 = "andropad";
            }
            str = e() ? "gtv" : str2;
            try {
                if (cj()) {
                    return "amazonpad";
                }
                return str;
            } catch (Throwable th2) {
                th = th2;
                b().log(Level.SEVERE, "cannot handle: ", th);
                return str;
            }
        } catch (Throwable th3) {
            str = str2;
            th = th3;
        }
    }

    private boolean cj() {
        return Build.MODEL.contains("Kindle Fire");
    }

    public synchronized void ap() {
        try {
            try {
                if (!bZ()) {
                    b().warning("skipping google push registration because no perms");
                    aN();
                    aM();
                } else {
                    try {
                        cr.a(this);
                        cr.b(this);
                        if (!bD()) {
                            b().info("global alerts off; unregistering with Google");
                            aN();
                            cr.c(this);
                        } else if (System.currentTimeMillis() - 600000 < aq()) {
                            b().info("ignoring gcm reg request that is w/i 10 min. of last attempt");
                        } else {
                            b().info("registering for Google Push, need push is: " + bF());
                            cr.a(this, "606114992759");
                            ck();
                        }
                    } catch (Throwable th) {
                        b().info("skipping google push because this device is not capable of gcm: " + th);
                        aN();
                        aM();
                    }
                }
            } catch (SecurityException e2) {
                b().log(Level.WARNING, "security error updating google about push; assuming rooted and ignoring: ", (Throwable) e2);
            }
        } catch (Throwable th2) {
            b("need_google_reg", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            b().log(Level.SEVERE, "problem updating google about push: ", th2);
        }
    }

    public long aq() {
        return X().getLong("last_gcm_reg_attempt", 0L);
    }

    private void ck() {
        a("last_gcm_reg_attempt", System.currentTimeMillis());
    }

    public synchronized void ar() {
        try {
            Intent intent = new Intent(this, (Class<?>) ThuuzService.class);
            intent.setAction("UPDATE_THUUZ_PUSH");
            startService(intent);
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
    }

    public boolean as() {
        boolean at = at();
        if (at || this.x.width() <= 0) {
            return at;
        }
        return this.x.width() >= 720 && this.x.height() >= 720;
    }

    public boolean at() {
        return (getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public boolean a(Activity activity) {
        boolean z = false;
        try {
            if (activity == null) {
                z = as();
            } else {
                boolean at = at();
                if (at) {
                    z = at;
                } else {
                    try {
                        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        defaultDisplay.getMetrics(displayMetrics);
                        if (this.x.width() / displayMetrics.density >= 720.0f) {
                            if (this.x.height() / displayMetrics.density >= 720.0f) {
                                z = true;
                            }
                        }
                    } catch (Throwable th) {
                        z = at;
                        th = th;
                        b().log(Level.SEVERE, "Cannot handle: ", th);
                        return z;
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void au() {
        /*
            r7 = this;
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            r1.<init>()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            java.lang.String r2 = r7.ae()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            org.apache.http.message.BasicNameValuePair r3 = new org.apache.http.message.BasicNameValuePair     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            java.lang.String r4 = "logs"
            r3.<init>(r4, r2)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            r1.add(r3)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            org.apache.http.message.BasicNameValuePair r2 = new org.apache.http.message.BasicNameValuePair     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            java.lang.String r3 = "sys"
            java.lang.String r4 = r7.am()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            r2.<init>(r3, r4)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            r1.add(r2)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            org.apache.http.client.entity.UrlEncodedFormEntity r2 = new org.apache.http.client.entity.UrlEncodedFormEntity     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            java.lang.String r3 = "UTF-8"
            r2.<init>(r1, r3)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            org.apache.http.Header r1 = r2.getContentType()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            java.lang.String r1 = r1.getValue()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            java.lang.String r3 = "UTF-8"
            boolean r1 = r1.endsWith(r3)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            if (r1 != 0) goto L3e
            java.lang.String r1 = "application/x-www-form-urlencoded; charset=UTF-8"
            r2.setContentType(r1)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
        L3e:
            org.apache.http.client.methods.HttpPost r1 = new org.apache.http.client.methods.HttpPost     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            java.lang.String r3 = com.thuuz.gtv.App.q     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            r1.<init>(r3)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            r1.setEntity(r2)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            org.apache.http.impl.client.DefaultHttpClient r0 = r7.aS()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Laa java.lang.Throwable -> Lbf
            java.util.logging.Logger r2 = r7.b()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
            java.lang.String r3 = "mailing logs..."
            r2.info(r3)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
            org.apache.http.HttpResponse r1 = r0.execute(r1)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
            org.apache.http.StatusLine r2 = r1.getStatusLine()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
            int r2 = r2.getStatusCode()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 == r3) goto L94
            java.lang.String r3 = "thuuz"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
            r4.<init>()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
            java.lang.String r5 = "problem mailing logs, exit code: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
            java.lang.String r2 = r2.toString()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
            android.util.Log.e(r3, r2)     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
        L7d:
            org.apache.http.HttpEntity r2 = r1.getEntity()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
            if (r2 == 0) goto L8a
            org.apache.http.HttpEntity r1 = r1.getEntity()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
            r1.consumeContent()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
        L8a:
            if (r0 == 0) goto L93
            org.apache.http.conn.ClientConnectionManager r0 = r0.getConnectionManager()
            r0.shutdown()
        L93:
            return
        L94:
            r7.af()     // Catch: java.net.UnknownHostException -> L98 java.lang.Throwable -> Lcd java.lang.Throwable -> Ld4
            goto L7d
        L98:
            r1 = move-exception
            java.lang.String r1 = "thuuz"
            java.lang.String r2 = "cannot connect to send feedback"
            android.util.Log.e(r1, r2)     // Catch: java.lang.Throwable -> Lcd
            if (r0 == 0) goto L93
            org.apache.http.conn.ClientConnectionManager r0 = r0.getConnectionManager()
            r0.shutdown()
            goto L93
        Laa:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        Lae:
            java.lang.String r2 = "thuuz"
            java.lang.String r3 = "problem: "
            android.util.Log.e(r2, r3, r0)     // Catch: java.lang.Throwable -> Ld2
            if (r1 == 0) goto L93
            org.apache.http.conn.ClientConnectionManager r0 = r1.getConnectionManager()
            r0.shutdown()
            goto L93
        Lbf:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        Lc3:
            if (r1 == 0) goto Lcc
            org.apache.http.conn.ClientConnectionManager r1 = r1.getConnectionManager()
            r1.shutdown()
        Lcc:
            throw r0
        Lcd:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto Lc3
        Ld2:
            r0 = move-exception
            goto Lc3
        Ld4:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto Lae
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.App.au():void");
    }

    public boolean a(Uri uri) {
        if (uri == null) {
            return false;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(uri);
            PackageManager packageManager = getPackageManager();
            if (packageManager != null) {
                return packageManager.queryIntentActivities(intent, 0).size() > 0;
            }
            return false;
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Cannot handle: ", th);
            return false;
        }
    }

    private ej cl() {
        if (this.y == null) {
            cm();
        }
        return this.y;
    }

    public void k(String str) {
        try {
            cl().a(str);
            cl().a((Map<String, String>) new ed().a());
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public void a(String str, String str2, String str3, long j) {
        try {
            cl().a((Map<String, String>) new ee().a(str).b(str2).c(str3).a(j).a());
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Cannot track: ", th);
        }
    }

    public void av() {
        try {
            aw();
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Error stopping GA tracker: ", th);
        }
        if (this.z != null) {
            try {
                this.z.appOnStop(this);
            } catch (Throwable th2) {
                b().log(Level.SEVERE, "Error handling appOnStop: ", th2);
            }
        }
    }

    public void aw() {
        try {
            dz.a((Context) this).e();
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Error dispatching analytics: ", th);
        }
    }

    private synchronized void cm() {
        ApplicationInfo applicationInfo;
        if (this.y == null) {
            try {
                this.z = PlatformOperations.getPlatformOperationsInstance(getPackageName());
                if (this.z != null) {
                    this.z.appOnStart(this);
                }
            } catch (Throwable th) {
                b().log(Level.SEVERE, "Error handling appOnStart: ", th);
            }
            try {
                dz a2 = dz.a((Context) this);
                String str = "UA-23809591-5";
                if (aQ()) {
                    str = "UA-23809591-2";
                } else if (ak()) {
                    str = "UA-23809591-6";
                }
                this.y = a2.a(str);
                this.y.b("thuuz_ga_tracker");
                this.y.b(false);
                this.y.a(false);
                this.y.a(1800L);
                if (Build.VERSION.SDK_INT >= 19 && (applicationInfo = getApplicationInfo()) != null && (applicationInfo.flags & 2) != 0) {
                    WebView.setWebContentsDebuggingEnabled(true);
                }
            } catch (Throwable th2) {
                b().log(Level.SEVERE, "Error instantiating google analytics: ", th2);
            }
        }
    }

    public Locations ax() {
        if (this.A == null) {
            this.A = new Locations(X().getString("locations", ""), this);
        }
        return this.A;
    }

    public String ay() {
        try {
            bpu a2 = ax().a(Integer.valueOf(X().getInt("my_location", -1)));
            if (a2 != null) {
                String d2 = a2.d();
                if (d2 != null) {
                    return d2;
                }
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
        return null;
    }

    private String cn() {
        int i = getResources().getConfiguration().screenLayout & 15;
        if (i > 3) {
            return "xlarge";
        }
        if (i == 3) {
            return "large";
        }
        if (i == 2) {
            return "normal";
        }
        if (i != 1) {
            return "undefined";
        }
        return "small";
    }

    public boolean az() {
        return !o().isEmpty();
    }

    public boolean aA() {
        return !r().isEmpty();
    }

    public boolean aB() {
        return !x().isEmpty();
    }

    public boolean aC() {
        return az() || aA() || aB();
    }

    public String aD() {
        String Z = Z();
        if (b.equals(Z)) {
            return "my";
        }
        if (c.equals(Z)) {
            return "teams";
        }
        if (d.equals(Z)) {
            return NativeProtocol.AUDIENCE_FRIENDS;
        }
        if (Z == null || e.equals(Z)) {
            return "all";
        }
        String[] aa = aa();
        if (aa == null) {
            return null;
        }
        return aa[0] + "/" + aa[1];
    }

    public Class aE() {
        Class<?> cls = null;
        if (!cc() && !aC() && this.M) {
            return OnboardPage1Activity.class;
        }
        try {
            String string = X().getString("lastActivity", null);
            if (string != null) {
                cls = Class.forName(string);
            }
        } catch (Throwable th) {
        }
        if (cls == null || AbstractNoActionBarActivity.class.isAssignableFrom(cls)) {
            return aF();
        }
        return cls;
    }

    public Class<? extends AbstractActivity> aF() {
        return (cc() || aC() || !this.M) ? GuideActivity.class : OnboardPage1Activity.class;
    }

    public Intent b(Activity activity) {
        return new Intent(activity, aF());
    }

    public void aG() {
        g("my_fav_leagues");
    }

    public void aH() {
        g("my_fav_league_groups");
    }

    public void aI() {
        g("my_fav_teams");
    }

    public boolean aJ() {
        return X().getBoolean("my_fav_configured", false);
    }

    public void d(boolean z) {
        a("my_fav_configured", z);
    }

    public boolean aK() {
        return X().getBoolean("display_scores", true);
    }

    public void e(boolean z) {
        a("display_scores", z);
    }

    public int q(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    public void aL() {
        b("need_thuuz_reg", "needed");
    }

    public void aM() {
        g("need_thuuz_reg");
    }

    public void aN() {
        g("need_google_reg");
    }

    public boolean aO() {
        return X().getString("need_thuuz_reg", null) != null;
    }

    public int aP() {
        return X().getInt("lastNotifyId", 1);
    }

    public void r(int i) {
        a("lastNotifyId", i);
    }

    public boolean aQ() {
        return "com.thuuz.gtv".equals(getPackageName());
    }

    public boolean aR() {
        return "com.thuuz.tablet".equals(getPackageName());
    }

    public DefaultHttpClient aS() {
        try {
            BasicHttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, HTTP.UTF_8);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
            return new DefaultHttpClient(basicHttpParams);
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
            return new DefaultHttpClient();
        }
    }

    public String aT() {
        return X().getString("gcm_reg", null);
    }

    public String aU() {
        return X().getString("username", "");
    }

    public void l(String str) {
        b("username", str);
    }

    public static Level aV() {
        return s;
    }

    public static void a(Level level) {
        s = level;
    }

    public boolean aW() {
        bpu a2 = ax().a();
        if (a2 != null) {
            return a2.f();
        }
        return false;
    }

    public boolean aX() {
        return bF() && (X().getString("need_google_reg", null) != null || aT() == null || aT().equals(""));
    }

    private synchronized void co() {
        if (this.B == null) {
            this.B = new bqe(this);
        }
    }

    public bqe aY() {
        if (this.B == null) {
            co();
        }
        return this.B;
    }

    public boolean aZ() {
        try {
            String bc = bc();
            if (i()) {
                return bc.length() > 0;
            }
            return false;
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
            return false;
        }
    }

    public boolean ba() {
        try {
            String bd = bd();
            String bc = bc();
            if (bc.length() <= 0 || bd.length() <= 0) {
                return false;
            }
            return !bc.equalsIgnoreCase(bd);
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
            return false;
        }
    }

    public String m(String str) {
        return "http://graph.facebook.com/" + str + "/picture";
    }

    public String n(String str) {
        return ServerChoice.q + "user/" + str + "/picture/";
    }

    public String bb() {
        return n(new bqd(bj()).a());
    }

    public String bc() {
        try {
            String be = be();
            if (be != null) {
                JSONArray jSONArray = new JSONArray(be);
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= jSONArray.length()) {
                        break;
                    }
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    String string = jSONObject.getString("prov");
                    String string2 = jSONObject.getString("sid");
                    if (!string.equalsIgnoreCase("facebook")) {
                        i = i2 + 1;
                    } else {
                        return string2;
                    }
                }
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
        return "";
    }

    public void o(String str) {
        b("facebook_social_id", str);
    }

    public String bd() {
        return X().getString("facebook_social_id", "");
    }

    public void p(String str) {
        b("thuuz_associated_social_ids", str);
    }

    public String be() {
        return X().getString("thuuz_associated_social_ids", "[]");
    }

    public void c(String str, String str2) {
        try {
            q(str);
            String be = be();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("prov", str);
            jSONObject.put("sid", str2);
            JSONArray jSONArray = new JSONArray(be);
            jSONArray.put(jSONArray.length(), jSONObject);
            p(jSONArray.toString());
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void q(String str) {
        try {
            String be = be();
            if (be != null) {
                JSONArray jSONArray = new JSONArray();
                JSONArray jSONArray2 = new JSONArray(be);
                for (int i = 0; i < jSONArray2.length(); i++) {
                    JSONObject jSONObject = jSONArray2.getJSONObject(i);
                    if (!jSONObject.has("prov") || !jSONObject.getString("prov").equalsIgnoreCase(str)) {
                        jSONArray.put(jSONObject);
                    }
                }
                p(jSONArray.toString());
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
    }

    public baw b(AbstractActivity abstractActivity) {
        synchronized (this.C) {
            if (this.D == null) {
                this.D = new baw(abstractActivity);
            }
        }
        return this.D;
    }

    public bbb c(AbstractActivity abstractActivity) {
        synchronized (this.E) {
            if (this.F == null) {
                this.F = new bbb(abstractActivity);
            }
        }
        return this.F;
    }

    public long bf() {
        return X().getLong("LAST_PING_TIMESTAMP", 0L);
    }

    public void bg() {
        a("LAST_PING_TIMESTAMP", System.currentTimeMillis());
    }

    public void f(boolean z) {
        this.G = z;
    }

    public Calendar bh() {
        try {
            return Calendar.getInstance();
        } catch (Throwable th) {
            return Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        }
    }

    public void a(SIGN_IN_TYPE sign_in_type) {
        a("signin_type", sign_in_type.a());
    }

    public SIGN_IN_TYPE bi() {
        return SIGN_IN_TYPE.a(X().getInt("signin_type", 0));
    }

    public void r(String str) {
        b().info("saving cookie");
        b("cookie", str);
    }

    public String bj() {
        return X().getString("cookie", null);
    }

    public String bk() {
        return X().getString("token2", null);
    }

    public void s(String str) {
        b("token2", str);
    }

    public void bl() {
        g("token2");
    }

    public void bm() {
        Intent intent = new Intent(this, (Class<?>) ThuuzService.class);
        intent.setAction("UPLOAD_PREFS");
        startService(intent);
    }

    public void s(int i) {
        Intent intent = new Intent(this, (Class<?>) ThuuzService.class);
        intent.setAction("UPLOAD_FANTASY_PREFS");
        intent.putExtra("team_id", i);
        startService(intent);
    }

    public boolean bn() {
        return bj() != null;
    }

    public void bo() {
        b().info("clearing cookie");
        g("cookie");
    }

    public String bp() {
        return X().getString("pref_zipcode", null);
    }

    public String bq() {
        return X().getString("last_msg", null);
    }

    public void t(String str) {
        b("last_msg", str);
    }

    public File br() {
        if (getCacheDir() == null) {
            return null;
        }
        return new File(getCacheDir().getAbsolutePath(), "thuuz_sport");
    }

    public File bs() {
        if (getCacheDir() == null) {
            return null;
        }
        return new File(getCacheDir().getAbsolutePath(), "thuuz_teams");
    }

    public void bt() {
        a("NEED_PREFS_UPLOADED", true);
    }

    public boolean bu() {
        return X().getBoolean("NEED_PREFS_UPLOADED", false);
    }

    public void bv() {
        g("NEED_PREFS_UPLOADED");
    }

    public void u(String str) {
        b("fname", str);
    }

    public void v(String str) {
        b("lname", str);
    }

    public void w(String str) {
        b("email", str);
    }

    public String bw() {
        return X().getString("fname", "");
    }

    public String bx() {
        return X().getString("lname", "");
    }

    public String by() {
        return X().getString("email", "");
    }

    public void bz() {
        g("token2");
    }

    public String d(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("u", str);
            jSONObject.put("p", str2);
            return "B" + Base64.encodeToString(jSONObject.toString().getBytes("utf-8"), 8);
        } catch (Throwable th) {
            b().log(Level.SEVERE, "Cannot handle: ", th);
            return "";
        }
    }

    public void x(String str) {
        b("pref_zipcode", str);
    }

    private String[] a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        String[] split = new String(bArr).split(" ");
        if (split.length < 2) {
            return null;
        }
        String str = split[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            sb.append(split[i]);
        }
        return new String[]{str, sb.toString()};
    }

    public int bA() {
        int i = bbp.notification_icon;
        if (e()) {
            return bbp.thuuzlogo;
        }
        return i;
    }

    public void g(boolean z) {
        a("notify_share", z);
    }

    public boolean bB() {
        return X().getBoolean("notify_share", h());
    }

    public void h(boolean z) {
        a("notify_invite", z);
    }

    public boolean bC() {
        return X().getBoolean("notify_invite", h());
    }

    public void i(boolean z) {
        a("global_alerts", z);
    }

    public boolean bD() {
        return X().getBoolean("global_alerts", h());
    }

    public void j(boolean z) {
        a("alert_my_friends", z);
    }

    public void k(boolean z) {
        a("post_twit", z);
    }

    public boolean bE() {
        return X().getBoolean("post_twit", false);
    }

    public boolean bF() {
        if (!bD()) {
            return false;
        }
        if (bB() || bC() || p() || s() || y()) {
            return true;
        }
        return A();
    }

    public int bG() {
        return X().getInt("PREV_APP_VERS", -1);
    }

    public void t(int i) {
        a("PREV_APP_VERS", i);
    }

    public Set<Integer> bH() {
        return h("MSGS_DISPLAYED");
    }

    public int bI() {
        return X().getInt("gameSort", -1);
    }

    public void u(int i) {
        a("gameSort", i);
    }

    public void y(String str) {
        b("twit_token", str);
    }

    public String bJ() {
        return X().getString("twit_token", null);
    }

    public void z(String str) {
        b("twit_secret", str);
    }

    public String bK() {
        return X().getString("twit_secret", null);
    }

    public int bL() {
        return X().getInt("last_game_id", -1);
    }

    public void v(int i) {
        a("last_game_id", i);
    }

    public void bM() {
        b().info("clearing twitter credentials");
        z(null);
        y(null);
        bN();
    }

    public Twitter bN() {
        try {
            ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
            configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("AECID7AOehPKlpgoht1w").setOAuthConsumerSecret("0s0hwBGxnL7Gm9nHWzHNDqK5FKrYca7ME1810AcO1eg").setOAuthAccessToken(bJ()).setOAuthAccessTokenSecret(bK());
            return new TwitterFactory(configurationBuilder.build()).getInstance();
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }

    public boolean bO() {
        return bJ() != null;
    }

    public void w(int i) {
        a("server", i);
    }

    public int bP() {
        return X().getInt("server", ServerChoice.l.e());
    }

    /* loaded from: classes.dex */
    public enum ALERT_LEVEL {
        ALERT_LEVEL_FANATIC(75),
        ALERT_LEVEL_ENTHUUZIAST(90),
        ALERT_LEVEL_CASUAL(99);

        private final int alert_level;

        ALERT_LEVEL(int i) {
            this.alert_level = i;
        }

        public int a() {
            return this.alert_level;
        }
    }

    /* loaded from: classes.dex */
    public enum ALERTS_FILTER {
        ALERTS_FILTER_ALL("A"),
        ALERTS_FILTER_TEAM("T");

        private final String filter;

        ALERTS_FILTER(String str) {
            this.filter = str;
        }

        public String a() {
            return this.filter;
        }
    }

    /* loaded from: classes.dex */
    public enum SIGN_IN_TYPE {
        SIGN_IN_TYPE_ANONYMOUS(0),
        SIGN_IN_TYPE_USER(1),
        SIGN_IN_TYPE_FACEBOOK(2),
        SIGN_IN_TYPE_TWITTER(3);

        private final int sign_in_type;

        SIGN_IN_TYPE(int i) {
            this.sign_in_type = i;
        }

        public int a() {
            return this.sign_in_type;
        }

        public static SIGN_IN_TYPE a(int i) {
            for (SIGN_IN_TYPE sign_in_type : values()) {
                if (sign_in_type.a() == i) {
                    return sign_in_type;
                }
            }
            return SIGN_IN_TYPE_ANONYMOUS;
        }
    }

    public boolean bQ() {
        try {
            if (!bS() && !bT()) {
                if (!bR()) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public boolean bR() {
        try {
            return new ExecShell(null).a(ExecShell.SHELL_CMD.check_su_binary) != null;
        } catch (Throwable th) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ExecShell {
        private ExecShell() {
        }

        /* synthetic */ ExecShell(bbg bbgVar) {
            this();
        }

        /* loaded from: classes.dex */
        public enum SHELL_CMD {
            check_su_binary(new String[]{"/system/xbin/which", "su"});

            final String[] command;

            SHELL_CMD(String[] strArr) {
                this.command = strArr;
            }
        }

        public ArrayList<String> a(SHELL_CMD shell_cmd) {
            ArrayList<String> arrayList = new ArrayList<>();
            try {
                Process exec = Runtime.getRuntime().exec(shell_cmd.command);
                new BufferedWriter(new OutputStreamWriter(exec.getOutputStream()));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        arrayList.add(readLine);
                    } else {
                        return arrayList;
                    }
                }
            } catch (Exception e) {
                return null;
            }
        }
    }

    public boolean bS() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    public boolean bT() {
        if (new File("/system/app/Superuser.apk").exists()) {
            return true;
        }
        return false;
    }

    public boolean A(String str) {
        if (str == null) {
            return false;
        }
        return str.endsWith(".mp4") || str.endsWith(".m4v") || str.endsWith(".3gp");
    }

    public void B(String str) {
        b("filter", str);
    }

    public boolean bU() {
        bpu a2 = ax().a();
        if (a2 == null) {
            return false;
        }
        boolean z = e() && f();
        return (z && aW()) || (!z && a2.m() != null);
    }

    public boolean bV() {
        return this.H;
    }

    public void l(boolean z) {
        this.H = z;
    }

    public ag bW() {
        if (this.I == null) {
            this.I = cf();
        }
        return this.I;
    }

    public void bX() {
        try {
            Runtime.getRuntime().gc();
            Runtime.getRuntime().gc();
            b().info("sending service ToastOom");
            Intent intent = new Intent(this, (Class<?>) ThuuzService.class);
            intent.setAction("ToastOom");
            startService(intent);
            if (c()) {
                au();
                af();
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "cannot handle OoM problem: ", th);
        }
    }

    public String bY() {
        StringBuilder sb = new StringBuilder();
        String bw = bw();
        if (bw != null && bw.length() > 0) {
            sb.append(bw);
            sb.append(" ");
        }
        String bx = bx();
        if (bx != null && bx.length() > 0) {
            sb.append(bx);
        }
        return sb.toString().trim();
    }

    public boolean bZ() {
        return e("com.google.android.c2dm.permission.RECEIVE");
    }

    public boolean ca() {
        try {
            return ((TelephonyManager) getSystemService("phone")).getPhoneType() != 0;
        } catch (Throwable th) {
            b().log(Level.INFO, "Cannot query telephony: " + th);
            return false;
        }
    }

    public boolean cb() {
        if (this.L == null) {
            if (ak()) {
                this.L = false;
                return false;
            }
            this.L = Boolean.valueOf(a(Uri.parse("http://google.com")));
        }
        return this.L.booleanValue();
    }

    public boolean cc() {
        return X().getBoolean("hasOnboarded", false);
    }

    public void m(boolean z) {
        a("hasOnboarded", z);
    }

    public String cd() {
        return "4aKwJqG3BoxET2hd3JUGk4";
    }
}
