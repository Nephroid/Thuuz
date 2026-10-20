package defpackage;

import com.thuuz.gtv.App;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byh extends bya {
    private TreeSet<bpd> a;

    public byh(App app) {
        super("expert_thuuzers", app, true);
        this.a = new TreeSet<>();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        a(jSONObject);
        return 0;
    }

    private void a(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("experts");
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bpd bpdVar = new bpd(jSONObject2.getJSONObject(next));
                bpdVar.a(Integer.parseInt(next));
                this.a.add(bpdVar);
                TreeSet treeSet = new TreeSet();
                JSONObject h = bpdVar.h("users");
                Iterator<String> keys2 = h.keys();
                while (keys2.hasNext()) {
                    String next2 = keys2.next();
                    bpc bpcVar = new bpc(h.getJSONObject(next2));
                    bpcVar.a(Integer.parseInt(next2));
                    treeSet.add(bpcVar);
                    bpcVar.a(bpdVar);
                }
                if (treeSet.size() > 0) {
                    ((bpc) treeSet.first()).a(true);
                }
                bpdVar.a(treeSet);
            }
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
    }

    public List<bpc> b() {
        ArrayList arrayList = new ArrayList();
        Iterator<bpd> it = this.a.iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().b());
        }
        return arrayList;
    }
}
