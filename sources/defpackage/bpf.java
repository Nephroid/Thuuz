package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpf extends bpq {
    private final int a;

    public bpf(JSONObject jSONObject, int i) {
        super(jSONObject);
        this.a = i;
    }

    public int a() {
        return f("id");
    }

    public List<bpi> b() {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject h = h("users");
            if (h != null) {
                Iterator<String> keys = h.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    JSONObject jSONObject = h.getJSONObject(next);
                    if (jSONObject != null) {
                        arrayList.add(new bpi(Integer.valueOf(next).intValue(), jSONObject.getString("manager_name"), jSONObject.getString("thuuz_name")));
                    }
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
        return arrayList;
    }

    public int c() {
        return this.a;
    }
}
