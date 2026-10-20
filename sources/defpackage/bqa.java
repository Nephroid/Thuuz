package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.data.AlertSettings;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bqa extends bpt {
    public bqa(JSONObject jSONObject) {
        super(jSONObject);
    }

    public bqa(Integer num, boolean z, Integer num2, boolean z2, boolean z3, boolean z4) {
        super(new JSONObject());
        a("id", num.intValue());
        a("eqa", z);
        a("eqt", num2.intValue());
        a("grm", z2);
        a("gsp", z3);
        a("gsu", z4);
        a("tfn", true);
    }

    public static bqa b(Integer num, AlertSettings alertSettings) {
        return alertSettings != null ? new bqa(num, alertSettings.b(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_EXCITEMENT), Integer.valueOf(alertSettings.e(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_EXCITEMENT)), alertSettings.b(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_REMINDERS), alertSettings.b(AlertSettings.ALERT_TYPE.ALERT_TYPE_SCORING_PLAYS), alertSettings.b(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_UPDATES)) : new bqa(num, true, Integer.valueOf(App.ALERT_LEVEL.ALERT_LEVEL_ENTHUUZIAST.a()), false, false, false);
    }

    public Boolean f() {
        return Boolean.valueOf(g("grm"));
    }

    public void b(boolean z) {
        a("grm", z);
    }

    public Boolean g() {
        return Boolean.valueOf(g("gsp"));
    }

    public void c(boolean z) {
        a("gsp", z);
    }

    public Boolean h() {
        return Boolean.valueOf(g("gsu"));
    }

    public void d(boolean z) {
        a("gsu", z);
    }

    public boolean i() {
        return b().booleanValue() || f().booleanValue() || g().booleanValue() || h().booleanValue();
    }

    @Override // defpackage.bpt
    public boolean e() {
        return g("eqa");
    }
}
