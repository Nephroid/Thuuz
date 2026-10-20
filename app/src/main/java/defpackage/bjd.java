package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.util.ServerChoice;
import com.thuuz.gtv.util.loader.StatefulLoader;
import com.thuuz.tv.TVDeviceManager;
import java.io.File;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bjd extends StatefulLoader<String> {
    private File b;
    private final bqc c;
    private final String d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bjd(AbstractActivity abstractActivity, bqc bqcVar, String str, File file) {
        super(abstractActivity);
        this.c = bqcVar;
        this.d = str;
        this.b = file;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.util.loader.StatefulLoader
    public cbf<String, Throwable> a() {
        cbf<String, Throwable> cbfVar;
        String str;
        try {
            TVDeviceManager tVDeviceManager = new TVDeviceManager(this.b, e(), ServerChoice.f());
            if (!tVDeviceManager.a()) {
                if (tVDeviceManager.c() < 100) {
                    str = "Internal storage is full; please reduce stored apps and files";
                } else {
                    str = "Cannot connect. Please try again later.";
                }
                cbfVar = new cbf<>(a(-1, str));
            } else {
                JSONObject jSONObject = new JSONObject(this.c.h());
                jSONObject.put("channel", this.d);
                e().info("calling changeChannel: " + jSONObject);
                cbfVar = new cbf<>(tVDeviceManager.changeChannel(jSONObject.toString()));
            }
            return cbfVar;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
            return new cbf<>(th);
        }
    }

    private String a(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("code", i);
        jSONObject.put("message", str);
        return jSONObject.toString();
    }

    public String b() {
        return this.d;
    }

    public bqc c() {
        return this.c;
    }
}
