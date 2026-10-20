package defpackage;

import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byz implements aj<JSONObject> {
    private final bza a;

    public byz(bza bzaVar) {
        this.a = bzaVar;
    }

    @Override // defpackage.aj
    public void a(JSONObject jSONObject) {
        try {
            this.a.c(jSONObject);
            this.a.n();
        } catch (Throwable th) {
            this.a.getLogger().log(Level.SEVERE, "cannot get games: " + th, th);
            this.a.a(-1);
        } finally {
            this.a.m();
        }
    }
}
