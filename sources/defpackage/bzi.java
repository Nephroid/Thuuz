package defpackage;

import com.thuuz.gtv.App;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzi extends bya {
    public bzi(App app) {
        super("ping", app, false);
    }

    @Override // defpackage.bya
    protected void a(int i, JSONObject jSONObject) {
        if (i == -11) {
            k().aL();
        }
    }
}
