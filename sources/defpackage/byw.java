package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.data.FetchRequest;
import com.thuuz.gtv.data.GameData;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byw extends bxz {
    private bpn a;

    public byw(App app, FetchRequest fetchRequest, IService iService) {
        super("gamedates", app, iService, fetchRequest);
    }

    public byw(App app, FetchRequest fetchRequest, IService iService, boolean z) {
        super("gamedates", app, iService, fetchRequest, z);
    }

    @Override // defpackage.bxz
    protected void b() {
        if (this.b == null) {
            throw new IllegalArgumentException("bad values");
        }
        f();
        DateTimeFormatter g = g();
        a("start_date", g.print(this.b.j().getTime()));
        a("end_date", g.print(this.b.i().getTime()));
        if (k().m()) {
            h();
        } else if (k().ab()) {
            String D = k().D();
            if (D != null && D.length() > 0) {
                a(new BasicNameValuePair("team_ids", D));
            }
        } else if (!k().J() && !k().ac()) {
            String[] aa = k().aa();
            if (aa == null || aa.length < 2) {
                getLogger().severe("bad splg: " + k().Z());
            } else {
                bpr a = z().a(aa[0], aa[1]);
                if (a == null) {
                    String b = z().b(k().Z());
                    if (b == null) {
                        throw new Exception("cannot get splg from filter: " + k().Z());
                    }
                    a("sport_leagues", b);
                } else {
                    a("league_ids", a.g());
                }
            }
        }
        a("type", this.b.p());
    }

    @Override // defpackage.bya
    protected void p() {
        if (k().J()) {
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        try {
            this.a = d(jSONObject);
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "problem with parsing gamedates: " + th);
            a(-1);
        }
        return v();
    }

    private bpn d(JSONObject jSONObject) {
        bpn bpnVar = new bpn();
        z().a(jSONObject);
        DateTimeFormatter forPattern = DateTimeFormat.forPattern("yyyy-MM-dd");
        if (jSONObject.has("gamedates")) {
            JSONArray jSONArray = jSONObject.getJSONArray("gamedates");
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    bpnVar.a.add(forPattern.parseDateTime(jSONArray.getString(i)).toDate());
                } catch (Throwable th) {
                    getLogger().log(Level.INFO, "Cannot handle parsing datetime: " + th);
                }
            }
        }
        if (jSONObject.has("today_status")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("today_status");
            String valueOf = String.valueOf(GameData.GAME_STATUS.COMPLETED.a());
            if (jSONObject2.has(valueOf)) {
                bpnVar.b = jSONObject2.getInt(valueOf) > 0;
            }
            String valueOf2 = String.valueOf(GameData.GAME_STATUS.UPCOMING.a());
            if (jSONObject2.has(valueOf2)) {
                bpnVar.d = jSONObject2.getInt(valueOf2) > 0;
            }
            String valueOf3 = String.valueOf(GameData.GAME_STATUS.IN_PROGRESS.a());
            if (jSONObject2.has(valueOf3)) {
                bpnVar.c = jSONObject2.getInt(valueOf3) > 0;
            }
        }
        return bpnVar;
    }

    public bpn E() {
        this.a.e = v();
        return this.a;
    }
}
