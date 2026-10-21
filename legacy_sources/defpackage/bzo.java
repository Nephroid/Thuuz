package defpackage;

import com.thuuz.gtv.App;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzo extends bya {
    private List<bqb> a;

    public bzo(App app, String str) {
        super("search_users", app, true);
        this.a = new ArrayList();
        if (str != null) {
            try {
                a(new BasicNameValuePair("email", str));
            } catch (Exception e) {
                getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
                a(-1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("users");
            for (int i = 0; i < jSONArray.length(); i++) {
                this.a.add(new bqb(jSONArray.getJSONObject(i)));
            }
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "problem with parsing search users: " + th);
            a(-1);
        }
        return v();
    }

    public List<bqb> b() {
        return this.a;
    }
}
