package com.thuuz.gtv.task;

import com.thuuz.gtv.App;
import defpackage.bqb;
import defpackage.bya;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FollowersTask extends bya {
    Set<bqb> a;
    Set<bqb> b;
    FOLLOW_TYPE c;

    /* loaded from: classes.dex */
    public enum FOLLOW_TYPE {
        FOLLOW_TYPE_FOLLOWERS("followed"),
        FOLLOW_TYPE_FOLLOWING("following"),
        FOLLOW_TYPE_BOTH("both");

        private String mType;

        FOLLOW_TYPE(String str) {
            this.mType = str;
        }

        public String a() {
            return this.mType;
        }
    }

    public FollowersTask(App app, FOLLOW_TYPE follow_type) {
        super("follows", app, true);
        this.a = new HashSet();
        this.b = new HashSet();
        this.c = follow_type;
        try {
            a("type", this.c.a());
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        a(jSONObject);
        return v();
    }

    private void a(JSONObject jSONObject) {
        try {
            if (jSONObject.has("following")) {
                JSONArray jSONArray = jSONObject.getJSONArray("following");
                for (int i = 0; i < jSONArray.length(); i++) {
                    bqb bqbVar = new bqb(jSONArray.getJSONObject(i));
                    bqbVar.a(true);
                    this.b.add(bqbVar);
                }
            }
            if (jSONObject.has("followed")) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("followed");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    bqb bqbVar2 = new bqb(jSONArray2.getJSONObject(i2));
                    if (this.b.contains(bqbVar2)) {
                        bqbVar2.a(true);
                    }
                    this.a.add(bqbVar2);
                }
            }
        } catch (Throwable th) {
            a(-1);
            getLogger().log(Level.SEVERE, "Problem syncing followers from server: ", th);
        }
    }

    public Set<bqb> b() {
        return this.a;
    }

    public Set<bqb> c() {
        return this.b;
    }
}
