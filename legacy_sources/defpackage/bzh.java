package defpackage;

import android.os.Build;
// [removed legacy tracker import]
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzh extends bya {
    public bzh(App app, IService iService) {
        super("messages", app, iService, false);
        String bq = k().bq();
        if (bq != null) {
            a("last_msg_id", bq);
        }
        a("device_os_vers", Build.VERSION.SDK_INT);
        int bG = k().bG();
        if (bG != -1 && bG < k().ai()) {
            k().ap();
            a("prev_vers", bG);
            k().a("miscellaneous", "update", String.valueOf(bG), 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        d(jSONObject);
        int bG = k().bG();
        int ai = k().ai();
        if (bG != ai) {
            k().t(ai);
        }
        return v();
    }

    private void d(JSONObject jSONObject) {
        try {
            z().a(jSONObject);
            if (jSONObject.has("required")) {
                a(jSONObject.getJSONObject("required"));
                return;
            }
            Set<Integer> bH = k().bH();
            JSONArray jSONArray = jSONObject.getJSONArray("messages");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                int i2 = jSONObject2.getInt("id");
                if (bH.contains(Integer.valueOf(i2))) {
                    String bq = k().bq();
                    if (bq != null && i2 == Integer.parseInt(bq) + 1) {
                        getLogger().info("setting last msg displayed to: " + i2);
                        k().t(i2 + "");
                    }
                    getLogger().info("ignoring old msg: " + i2);
                } else {
                    b(i2, jSONObject2);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
            a(-1);
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0085 -> B:7:0x0044). Please report as a decompilation issue!!! */
    public void b(int i, JSONObject jSONObject) {
        try {
            try {
                try {
                    HttpResponse e = e(jSONObject);
                    if (e.getStatusLine().getStatusCode() != 200) {
                        getLogger().warning("cannot fetch messages: " + e.getStatusLine().getStatusCode() + ": " + e.getStatusLine().getReasonPhrase());
                    } else {
                        z().a(i, jSONObject.getInt("weight"), EntityUtils.toString(e.getEntity()));
                    }
                } catch (IOException e2) {
                    getLogger().info("comm exception getting messages.");
                }
            } catch (Throwable th) {
                getLogger().log(Level.WARNING, "cannot fetch messages: " + th);
            }
        } catch (Throwable th2) {
            getLogger().log(Level.SEVERE, "Error processing messages: ", th2);
        }
    }

    private HttpResponse e(JSONObject jSONObject) {
        DefaultHttpClient aS = k().aS();
        String string = jSONObject.getString(NativeProtocol.IMAGE_URL_KEY);
        HttpGet httpGet = new HttpGet(string);
        getLogger().info("fetching message with url: " + string);
        return aS.execute(httpGet);
    }

    public void a(JSONObject jSONObject) {
        try {
            try {
                try {
                    HttpResponse e = e(jSONObject);
                    if (e.getStatusLine().getStatusCode() != 200) {
                        getLogger().warning("cannot fetch messages: " + e.getStatusLine().getStatusCode() + ": " + e.getStatusLine().getReasonPhrase());
                    } else {
                        z().c(EntityUtils.toString(e.getEntity()));
                    }
                } catch (Throwable th) {
                    getLogger().log(Level.SEVERE, "Error processing messages: ", th);
                }
            } catch (Throwable th2) {
                getLogger().log(Level.WARNING, "cannot fetch messages: " + th2);
            }
        } catch (IOException e2) {
            getLogger().info("comm exception getting messages.");
        }
    }
}
