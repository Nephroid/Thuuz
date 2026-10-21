package com.thuuz.gtv.data;

import defpackage.bpq;
import java.util.logging.Level;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FantasySettingsCustomizations extends bpq {

    /* loaded from: classes.dex */
    public enum ALERT_TYPE {
        ALERT_TYPE_COMMENT(0, ClientCookie.COMMENT_ATTR, "", ""),
        ALERT_TYPE_COMPETITION(1, "competition", "Competition Alerts", "Updates on your Fantasy match ups and opportunities to talk smack"),
        ALERT_TYPE_SCORING_POSITION(2, "scoring_position", "Scoring Position Alerts", "When your players are in scoring position"),
        ALERT_TYPE_HOT_PLAYER(3, "hot_player", "Hot Player Alerts", "When your players score or reach statistical milestones"),
        ALERT_TYPE_INJURY(4, "injury", "Injury Alerts", "Changes to the injury status of your players");

        private String defaultTitle;
        private String key;
        private String subtitleDefault;
        private int val;

        ALERT_TYPE(int i, String str, String str2, String str3) {
            this.val = i;
            this.key = str;
            this.defaultTitle = str2;
            this.subtitleDefault = str3;
        }

        public String a() {
            return this.key;
        }

        public String b() {
            return this.defaultTitle;
        }

        public String c() {
            return this.subtitleDefault;
        }
    }

    public FantasySettingsCustomizations(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void a(JSONObject jSONObject) {
        try {
            for (ALERT_TYPE alert_type : ALERT_TYPE.values()) {
                String a = alert_type.a();
                if (jSONObject.has(a)) {
                    if (alert_type == ALERT_TYPE.ALERT_TYPE_COMMENT) {
                        ar().put(a, jSONObject.getString(a));
                    } else {
                        ar().put(a, jSONObject.getJSONObject(a));
                    }
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public boolean a(ALERT_TYPE alert_type) {
        try {
            JSONObject jSONObject = ar().getJSONObject(alert_type.a());
            if (jSONObject != null && jSONObject.has("visible")) {
                return jSONObject.getBoolean("visible");
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return false;
    }

    public boolean b(ALERT_TYPE alert_type) {
        try {
            JSONObject jSONObject = ar().getJSONObject(alert_type.a());
            if (jSONObject != null && jSONObject.has("default")) {
                return jSONObject.getBoolean("default");
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return false;
    }

    public String c(ALERT_TYPE alert_type) {
        String str;
        Throwable th;
        try {
            JSONObject jSONObject = ar().getJSONObject(alert_type.a());
            if (jSONObject == null || !jSONObject.has("title")) {
                str = "";
            } else {
                str = jSONObject.getString("title");
            }
            try {
                if (str.length() == 0) {
                    return alert_type.defaultTitle;
                }
                return str;
            } catch (Throwable th2) {
                th = th2;
                getLogger().log(Level.SEVERE, "Cannot handle: ", th);
                return str;
            }
        } catch (Throwable th3) {
            str = "";
            th = th3;
        }
    }

    public String d(ALERT_TYPE alert_type) {
        String str;
        Throwable th;
        try {
            JSONObject jSONObject = ar().getJSONObject(alert_type.a());
            if (jSONObject == null || !jSONObject.has("subtitle")) {
                str = "";
            } else {
                str = jSONObject.getString("subtitle");
            }
            try {
                if (str.length() == 0) {
                    return alert_type.subtitleDefault;
                }
                return str;
            } catch (Throwable th2) {
                th = th2;
                getLogger().log(Level.SEVERE, "Cannot handle: ", th);
                return str;
            }
        } catch (Throwable th3) {
            str = "";
            th = th3;
        }
    }
}
