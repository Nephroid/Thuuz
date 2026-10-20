package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.data.FetchRequest;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bza extends bxz {
    private final Set<Integer> a;
    private String c;
    private String d;

    public bza(App app, FetchRequest fetchRequest, IService iService, boolean z) {
        super("games", app, iService, fetchRequest, z);
        this.a = new HashSet();
        c(true);
    }

    @Override // defpackage.bxz
    protected void b() {
        if (this.b != null) {
            if (this.b.n() > 0) {
                a("limit", this.b.n());
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            if (this.b.j() != null) {
                a("start_dt", simpleDateFormat.format(this.b.j()));
            }
            if (this.b.i() != null) {
                a("end_dt", simpleDateFormat.format(this.b.i()));
            }
            if (this.b.h()) {
                a("schedule", "True");
            }
            if (this.b.d() != null) {
                a("status", this.b.d().a());
            }
            String ay = k().ay();
            if (ay != null) {
                a("tv_provider", ay);
            }
            if (this.b.e() != null && this.b.e().length() > 0) {
                if (App.c.equals(this.b.e())) {
                    a(new BasicNameValuePair("team_ids", k().D()));
                } else if (App.e.equals(this.b.e())) {
                    a(new BasicNameValuePair("league_ids", d()));
                } else if (this.b.p().equals(FetchRequest.REQ_TYPE.NORMAL.a())) {
                    a(new BasicNameValuePair("sport_leagues", this.b.e()));
                }
            } else if (this.b.t() != null) {
                a(new BasicNameValuePair("team_ids", this.b.t()));
            }
            a("type", this.b.p());
            if (FetchRequest.REQ_TYPE.WIDGET == this.b.q()) {
                a("widget", 1);
            }
            if (this.b.o().size() > 0) {
                a(new BasicNameValuePair("game_ids", App.a(this.b.o(), ",")));
            }
            switch (bzb.a[this.b.q().ordinal()]) {
                case 1:
                case 2:
                    f();
                    g();
                    if (k().az() || k().aA()) {
                        h();
                        break;
                    }
                    break;
                case 3:
                    f();
                    g();
                    break;
            }
            if (this.b.b() && k().cb()) {
                a("objects", "highlights");
            }
        }
    }

    @Override // defpackage.bya
    protected void p() {
        FetchRequest.REQ_TYPE q = this.b.q();
        if ((FetchRequest.REQ_TYPE.RIGHTBAR.equals(q) || FetchRequest.REQ_TYPE.WIDGET.equals(q)) && !k().az() && !k().aA()) {
            i();
        }
    }

    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        try {
            IService z = z();
            if (z != null) {
                a(z.a(jSONObject));
            }
            d(jSONObject);
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "problem with parsing games: " + th);
            a(-1);
        }
        return v();
    }

    public void d(JSONObject jSONObject) {
        a(a(jSONObject));
        if (jSONObject.has("ratings_title")) {
            e(jSONObject.getJSONObject("ratings_title"));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(org.json.JSONObject r10) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bza.e(org.json.JSONObject):void");
    }

    private void a(Set<Integer> set) {
        this.a.addAll(set);
    }

    public Set<Integer> H() {
        return this.a;
    }

    @Override // defpackage.bya
    public int n() {
        int i;
        Throwable th;
        IService z;
        try {
            i = super.n();
            try {
                if (y() && (z = z()) != null) {
                    z.a(H(), this.b);
                }
            } catch (Throwable th2) {
                th = th2;
                getLogger().log(Level.SEVERE, "problem: ", th);
                return i;
            }
        } catch (Throwable th3) {
            i = -1;
            th = th3;
        }
        return i;
    }

    @Override // defpackage.bya
    public int o() {
        try {
            bbl c = this.b.c();
            if (c != null) {
                c.onStartFetch();
            }
            return super.o();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    @Override // defpackage.bya
    public void m() {
        try {
            bbl c = this.b.c();
            if (c != null) {
                c.onConcludeFetch(c());
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot callback: ", th);
        }
    }

    @Override // defpackage.bxz
    public bpo c() {
        bpo c = super.c();
        c.a(this.c);
        return c;
    }

    public String toString() {
        return w() + " with request: " + this.b;
    }

    public String I() {
        return this.d;
    }
}
