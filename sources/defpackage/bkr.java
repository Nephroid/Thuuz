package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.util.ServerChoice;
import com.thuuz.gtv.util.loader.StatefulLoader;
import com.thuuz.tv.TVDeviceManager;
import java.io.File;
import java.util.Date;
import java.util.logging.Level;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bkr extends StatefulLoader<String> {
    private File b;
    private final bqc c;
    private final bpa d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bkr(AbstractActivity abstractActivity, bqc bqcVar, bpa bpaVar, File file) {
        super(abstractActivity);
        this.c = bqcVar;
        this.d = bpaVar;
        this.b = file;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.util.loader.StatefulLoader
    public cbf<String, Throwable> a() {
        String str;
        try {
            TVDeviceManager tVDeviceManager = new TVDeviceManager(this.b, e(), ServerChoice.f());
            if (!tVDeviceManager.a()) {
                if (tVDeviceManager.c() < 100) {
                    str = "Internal storage is full; please reduce stored apps and files";
                } else {
                    str = "Cannot remote control. Please try again later.";
                }
                return new cbf<>(a(-1, str));
            }
            JSONObject jSONObject = new JSONObject(this.c.h());
            jSONObject.put("channel", String.valueOf(this.d.i()));
            int f = this.d.f() * 60;
            Date e = this.d.e();
            long time = e.getTime() / 1000;
            if (e.before(new Date(DateTime.now(DateTimeZone.UTC).getMillis()))) {
                time = 0;
                f = (int) (f - ((new DateTime(DateTimeZone.UTC).getMillis() - e.getTime()) / 1000));
                if (f <= 0) {
                    e().warning("extending remaining time to record " + this.d);
                    f += 60;
                }
            }
            jSONObject.put("start_time", time);
            jSONObject.put("duration", f);
            jSONObject.put("min_late", 60);
            e().info("calling record channel: " + jSONObject);
            return new cbf<>(tVDeviceManager.recordChannel(jSONObject.toString()));
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

    public bqc b() {
        return this.c;
    }

    public bpa c() {
        return this.d;
    }
}
