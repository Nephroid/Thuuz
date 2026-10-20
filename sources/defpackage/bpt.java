package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.data.AlertSettings;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpt extends bpq {
    public bpt(JSONObject jSONObject) {
        super(jSONObject);
    }

    public bpt(Integer num, boolean z, Integer num2) {
        super(new JSONObject());
        a("id", num.intValue());
        a("eqa", z);
        a("eqt", num2.intValue());
    }

    public static bpt a(Integer num, AlertSettings alertSettings) {
        return alertSettings != null ? new bpt(num, alertSettings.b(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_EXCITEMENT), Integer.valueOf(alertSettings.e(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_EXCITEMENT))) : new bpt(num, true, Integer.valueOf(App.ALERT_LEVEL.ALERT_LEVEL_ENTHUUZIAST.a()));
    }

    public void a(int i) {
        a("id", i);
    }

    public Integer a() {
        try {
            if (l("id")) {
                return Integer.valueOf(f("id"));
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return null;
    }

    public Boolean b() {
        return Boolean.valueOf(g("eqa"));
    }

    public void a(boolean z) {
        a("eqa", z);
    }

    public Integer c() {
        try {
            if (l("eqt")) {
                return Integer.valueOf(f("eqt"));
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return 0;
    }

    public void a(App.ALERT_LEVEL alert_level) {
        a("eqt", alert_level.a());
    }

    public App.ALERT_LEVEL d() {
        int intValue = c().intValue();
        App.ALERT_LEVEL alert_level = App.ALERT_LEVEL.ALERT_LEVEL_ENTHUUZIAST;
        if (intValue >= App.ALERT_LEVEL.ALERT_LEVEL_CASUAL.a()) {
            return App.ALERT_LEVEL.ALERT_LEVEL_CASUAL;
        }
        if (intValue >= App.ALERT_LEVEL.ALERT_LEVEL_ENTHUUZIAST.a() && intValue < App.ALERT_LEVEL.ALERT_LEVEL_CASUAL.a()) {
            return App.ALERT_LEVEL.ALERT_LEVEL_ENTHUUZIAST;
        }
        if (intValue < App.ALERT_LEVEL.ALERT_LEVEL_ENTHUUZIAST.a()) {
            return App.ALERT_LEVEL.ALERT_LEVEL_FANATIC;
        }
        return alert_level;
    }

    public boolean e() {
        return g("eqa");
    }
}
