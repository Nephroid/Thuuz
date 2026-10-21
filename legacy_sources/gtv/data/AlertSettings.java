package com.thuuz.gtv.data;

import android.support.v4.os.EnvironmentCompat;
import com.thuuz.gtv.App;
import defpackage.bpq;
import java.util.logging.Level;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AlertSettings extends bpq {

    /* loaded from: classes.dex */
    public enum ALERT_TYPE {
        ALERT_TYPE_UNKNOWN(-1, EnvironmentCompat.MEDIA_UNKNOWN),
        ALERT_TYPE_COMMENT(0, ClientCookie.COMMENT_ATTR),
        ALERT_TYPE_GAME_EXCITEMENT(1, "game_excitement"),
        ALERT_TYPE_GAME_REMINDERS(2, "game_reminders"),
        ALERT_TYPE_SCORING_PLAYS(3, "scoring_plays"),
        ALERT_TYPE_GAME_UPDATES(4, "game_updates");

        private String key;

        ALERT_TYPE(int i, String str) {
            this.key = str;
        }

        public String a() {
            return this.key;
        }
    }

    public AlertSettings(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void a(JSONObject jSONObject) {
        try {
            for (ALERT_TYPE alert_type : ALERT_TYPE.values()) {
                if (alert_type != ALERT_TYPE.ALERT_TYPE_UNKNOWN) {
                    String a = alert_type.a();
                    if (jSONObject.has(a)) {
                        if (alert_type == ALERT_TYPE.ALERT_TYPE_COMMENT) {
                            ar().put(a, jSONObject.getString(a));
                        } else {
                            ar().put(a, jSONObject.getJSONObject(a));
                        }
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
            if (jSONObject != null) {
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
            if (jSONObject != null) {
                return jSONObject.getBoolean("default");
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return false;
    }

    public String c(ALERT_TYPE alert_type) {
        try {
            JSONObject jSONObject = ar().getJSONObject(alert_type.a());
            if (jSONObject != null) {
                return jSONObject.getString("title");
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return "";
    }

    public String d(ALERT_TYPE alert_type) {
        try {
            JSONObject jSONObject = ar().getJSONObject(alert_type.a());
            if (jSONObject != null) {
                return jSONObject.getString("subtitle");
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return "";
    }

    public int e(ALERT_TYPE alert_type) {
        try {
            JSONObject jSONObject = ar().getJSONObject(alert_type.a());
            if (jSONObject != null) {
                return jSONObject.getInt("threshold");
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return App.ALERT_LEVEL.ALERT_LEVEL_ENTHUUZIAST.a();
    }
}
