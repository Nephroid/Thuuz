package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.data.FetchRequest;
import com.thuuz.gtv.data.GameData;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class bxz extends bya {
    private final List<Integer> a;
    protected final FetchRequest b;
    private GameData c;
    private boolean d;
    private Date e;

    abstract void b();

    public bxz(String str, App app, IService iService, FetchRequest fetchRequest, boolean z) {
        super(str, app, iService, z);
        this.a = new ArrayList();
        this.d = true;
        this.b = fetchRequest;
        try {
            b();
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    public bxz(String str, App app, IService iService, FetchRequest fetchRequest) {
        this(str, app, iService, fetchRequest, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Set<Integer> a(JSONObject jSONObject) {
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("ratings");
        getLogger().info("received raw games: " + jSONArray.length());
        String string = jSONObject.getString("timestamp");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            Integer valueOf = Integer.valueOf(d(jSONObject2));
            jSONObject2.put("request_utc_timestamp", string);
            GameData gameData = new GameData(jSONObject2, k());
            gameData.a(this.e);
            a(valueOf, gameData);
            if (gameData.al()) {
                hashSet.add(valueOf);
            }
            this.a.add(valueOf);
            if (i == 0) {
                this.c = gameData;
            }
        }
        return hashSet;
    }

    private int d(JSONObject jSONObject) {
        return jSONObject.getInt("id");
    }

    public bpo c() {
        bpo bpoVar = new bpo(this.b);
        bpoVar.a(v());
        bpoVar.c().addAll(this.a);
        bpoVar.a(this.c);
        return bpoVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String d() {
        StringBuilder sb = new StringBuilder();
        IService z = z();
        if (z != null) {
            Iterator<bpx> it = z.b().iterator();
            while (it.hasNext()) {
                Iterator<bpr> it2 = z.f(it.next().a()).iterator();
                while (it2.hasNext()) {
                    sb.append(it2.next().g());
                    sb.append(",");
                }
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    @Override // defpackage.bya
    protected boolean e() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f() {
        a("utc_offset", DateTimeFormat.forPattern("Z").print(System.currentTimeMillis()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DateTimeFormatter g() {
        DateTimeFormatter forPattern = DateTimeFormat.forPattern("yyyy-MM-dd");
        a("today", forPattern.print(new Date().getTime()));
        return forPattern;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void h() {
        boolean z = true;
        boolean z2 = false;
        String B = k().B();
        if (B.length() > 0) {
            b("league_ids", B);
            z2 = true;
        }
        String C = k().C();
        if (C.length() > 0) {
            b("league_group_ids", C);
        } else {
            z = z2;
        }
        if (!z) {
            getLogger().warning("my sports filter has 0 leagues/league_groups; using 'all'");
            a(new BasicNameValuePair("league_ids", d()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void i() {
        String d = d();
        if (d.length() > 0) {
            b("league_ids", d);
        } else {
            getLogger().severe("cannot find any leagues during all_sports query!");
        }
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean j() {
        return this.d;
    }

    public void a(Integer num, GameData gameData) {
        IService z;
        if (j() && (z = z()) != null) {
            z.a(num, gameData);
        }
    }

    public void a(Date date) {
        this.e = date;
    }
}
