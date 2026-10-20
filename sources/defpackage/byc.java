package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.data.FetchRequest;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.widget.WidgetRemoteViewService;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byc extends bza {
    private final File a;
    private Map<Integer, GameData> c;
    private Set<Integer> d;
    private boolean e;
    private boolean f;

    public byc(FetchRequest fetchRequest, App app) {
        super(app, fetchRequest, null, true);
        this.c = new HashMap();
        this.a = app.getCacheDir();
    }

    @Override // defpackage.bxz
    public void a(Integer num, GameData gameData) {
        this.c.put(num, gameData);
        super.a(num, gameData);
    }

    public Map<Integer, GameData> E() {
        return this.c;
    }

    @Override // defpackage.bza, defpackage.bya
    public int b(JSONObject jSONObject) {
        super.b(jSONObject);
        if (y()) {
            this.e = true;
            cak.a(WidgetRemoteViewService.a(this.a), x().toString());
        }
        return v();
    }

    public void a(Set<Integer> set) {
        this.d = set;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bxz
    public String d() {
        if (this.d != null) {
            return k().a(this.d);
        }
        getLogger().severe("empty league IDs in widget");
        return "";
    }

    public boolean F() {
        return this.e;
    }

    public void e(boolean z) {
        this.f = z;
    }

    public boolean G() {
        return this.f;
    }
}
