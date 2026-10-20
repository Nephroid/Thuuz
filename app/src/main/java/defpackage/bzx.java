package defpackage;

import com.thuuz.gtv.App;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzx extends bya {
    private List<bpy> a;

    public bzx(App app, String str) {
        super("tv_providers", app, false);
        this.a = new ArrayList();
        a("postal_code", str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        a(jSONObject);
        return v();
    }

    private void a(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("providers");
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.a.add(new bpy(jSONArray.getJSONObject(i)));
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    public List<bpy> b() {
        return this.a;
    }

    public caq c() {
        return new caq(b(), y());
    }
}
