package defpackage;

import android.content.Intent;
import com.thuuz.gtv.App;
import com.thuuz.gtv.data.Locations;
import java.util.Iterator;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzj extends bya {
    private boolean a;

    public bzj(App app) {
        super("prefs", app, true);
        this.a = true;
    }

    public bzj(boolean z, App app) {
        this(app);
        this.a = z;
    }

    public bzj(App app, boolean z) {
        this(app);
        b(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        if (this.a) {
            a(jSONObject);
        }
        return v();
    }

    private void a(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("prefs")) {
                getLogger().severe("cannot find 'prefs' key in downloaded prefs");
                return;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("prefs");
            if (jSONObject2.has("min_gex")) {
                k().a(Integer.valueOf(jSONObject2.getInt("min_gex")));
            }
            if (jSONObject2.has("leagues")) {
                try {
                    k().aG();
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("leagues");
                    if (jSONObject3 != null) {
                        Iterator<String> keys = jSONObject3.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            bpt bptVar = new bpt(jSONObject3.getJSONObject(next));
                            bptVar.a(Integer.parseInt(next));
                            k().a(bptVar);
                        }
                    }
                } catch (Throwable th) {
                    getLogger().severe("cannot parse leagues: " + th);
                }
            }
            if (jSONObject2.has("league_groups")) {
                try {
                    k().aH();
                    JSONObject jSONObject4 = jSONObject2.getJSONObject("league_groups");
                    if (jSONObject4 != null) {
                        Iterator<String> keys2 = jSONObject4.keys();
                        while (keys2.hasNext()) {
                            String next2 = keys2.next();
                            bpt bptVar2 = new bpt(jSONObject4.getJSONObject(next2));
                            bptVar2.a(Integer.parseInt(next2));
                            k().b(bptVar2);
                        }
                    }
                } catch (Throwable th2) {
                    getLogger().severe("cannot parse league groups: " + th2);
                }
            }
            if (jSONObject2.has("teams")) {
                try {
                    k().aI();
                    JSONObject jSONObject5 = jSONObject2.getJSONObject("teams");
                    if (jSONObject5 != null) {
                        Iterator<String> keys3 = jSONObject5.keys();
                        while (keys3.hasNext()) {
                            String next3 = keys3.next();
                            bqa bqaVar = new bqa(jSONObject5.getJSONObject(next3));
                            bqaVar.a(Integer.parseInt(next3));
                            k().a(bqaVar);
                        }
                    }
                } catch (Throwable th3) {
                    getLogger().severe("cannot parse teams: " + th3);
                }
            }
            if (jSONObject2.has("tv_provider") && jSONObject2.has("zipcode") && jSONObject2.has("tv_provider_name")) {
                Locations ax = k().ax();
                if (ax.size() == 0) {
                    String string = jSONObject2.getString("tv_provider");
                    String string2 = jSONObject2.getString("tv_provider_name");
                    String string3 = jSONObject2.getString("zipcode");
                    if (string.length() > 0 && string2.length() > 0 && string3.length() > 0) {
                        getLogger().info("syncing tv_provider: " + string);
                        ax.a(ax.a("home", string3, string, string2, ""), false);
                        if (k().bp() == null) {
                            k().x(string3);
                            k().sendBroadcast(new Intent("set_zip"), "com.thuuz.permission.local.broadcast");
                        }
                    }
                }
            }
            if (jSONObject2.has("notify_share")) {
                k().g(jSONObject2.getBoolean("notify_share"));
            }
            if (jSONObject2.has("notify_invite")) {
                k().h(jSONObject2.getBoolean("notify_invite"));
            }
            if (jSONObject2.has("push_alerts")) {
                k().i(jSONObject2.getBoolean("push_alerts"));
            }
        } catch (Throwable th4) {
            a(-1);
            getLogger().log(Level.SEVERE, "Problem syncing prefs from server: ", th4);
        }
    }
}
