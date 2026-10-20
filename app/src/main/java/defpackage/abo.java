package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.facebook.AppEventsConstants;
import com.facebook.internal.NativeProtocol;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public class abo implements Callable<acg> {
    private final Context a;
    private final adg b;
    private final ajr c;
    private final so d;
    private final ach f;
    private final Object e = new Object();
    private boolean g = false;
    private int h = -2;
    private List<String> i = null;

    public abo(Context context, ajr ajrVar, so soVar, adg adgVar, ach achVar) {
        this.a = context;
        this.c = ajrVar;
        this.b = adgVar;
        this.d = soVar;
        this.f = achVar;
    }

    private acg a(uo uoVar) {
        int i;
        synchronized (this.e) {
            i = this.h;
            if (uoVar == null && this.h == -2) {
                i = 0;
            }
        }
        return new acg(this.f.a.c, null, this.f.b.d, i, this.f.b.f, this.i, this.f.b.l, this.f.b.k, this.f.a.i, false, null, null, null, null, null, 0L, this.f.d, this.f.b.g, this.f.f, this.f.g, this.f.b.o, this.f.h, i != -2 ? null : uoVar);
    }

    private JSONObject a(sm smVar) {
        if (b()) {
            return null;
        }
        ada adaVar = new ada();
        smVar.a("/nativeAdPreProcess", new abp(this, smVar, adaVar));
        smVar.a("google.afma.nativeAds.preProcessJsonGmsg", new JSONObject(this.f.b.c));
        return (JSONObject) adaVar.get();
    }

    private uo a(sm smVar, abr abrVar, JSONObject jSONObject) {
        if (b()) {
            return null;
        }
        String[] a = a(jSONObject.getJSONObject("tracking_urls_and_actions"), "impression_tracking_urls");
        this.i = a == null ? null : Arrays.asList(a);
        uo a2 = abrVar.a(this, jSONObject);
        if (a2 == null) {
            adn.b("Failed to retrieve ad assets.");
            return null;
        }
        a2.a(new un(this.c, smVar, jSONObject));
        return a2;
    }

    private String[] a(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        String[] strArr = new String[optJSONArray.length()];
        for (int i = 0; i < optJSONArray.length(); i++) {
            strArr[i] = optJSONArray.getString(i);
        }
        return strArr;
    }

    private sm c() {
        if (b()) {
            return null;
        }
        sm smVar = this.d.a(this.a, this.f.a.k, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/native_ads.html").get();
        smVar.a(this.c, this.c, this.c, this.c, false, this.c);
        return smVar;
    }

    protected abr a(JSONObject jSONObject) {
        if (b()) {
            return null;
        }
        String string = jSONObject.getString("template_id");
        if ("2".equals(string)) {
            return new abs();
        }
        if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(string)) {
            return new abt();
        }
        a(0);
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    @Override // java.util.concurrent.Callable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public defpackage.acg call() {
        /*
            r3 = this;
            sm r0 = r3.c()     // Catch: org.json.JSONException -> L15 java.util.concurrent.TimeoutException -> L29 java.lang.InterruptedException -> L30 java.util.concurrent.ExecutionException -> L32 java.util.concurrent.CancellationException -> L34
            org.json.JSONObject r1 = r3.a(r0)     // Catch: org.json.JSONException -> L15 java.util.concurrent.TimeoutException -> L29 java.lang.InterruptedException -> L30 java.util.concurrent.ExecutionException -> L32 java.util.concurrent.CancellationException -> L34
            abr r2 = r3.a(r1)     // Catch: org.json.JSONException -> L15 java.util.concurrent.TimeoutException -> L29 java.lang.InterruptedException -> L30 java.util.concurrent.ExecutionException -> L32 java.util.concurrent.CancellationException -> L34
            uo r0 = r3.a(r0, r2, r1)     // Catch: org.json.JSONException -> L15 java.util.concurrent.TimeoutException -> L29 java.lang.InterruptedException -> L30 java.util.concurrent.ExecutionException -> L32 java.util.concurrent.CancellationException -> L34
            acg r0 = r3.a(r0)     // Catch: org.json.JSONException -> L15 java.util.concurrent.TimeoutException -> L29 java.lang.InterruptedException -> L30 java.util.concurrent.ExecutionException -> L32 java.util.concurrent.CancellationException -> L34
        L14:
            return r0
        L15:
            r0 = move-exception
            java.lang.String r1 = "Malformed native JSON response."
            defpackage.adn.d(r1, r0)
        L1b:
            boolean r0 = r3.g
            if (r0 != 0) goto L23
            r0 = 0
            r3.a(r0)
        L23:
            r0 = 0
            acg r0 = r3.a(r0)
            goto L14
        L29:
            r0 = move-exception
            java.lang.String r1 = "Timeout when loading native ad."
            defpackage.adn.d(r1, r0)
            goto L1b
        L30:
            r0 = move-exception
            goto L1b
        L32:
            r0 = move-exception
            goto L1b
        L34:
            r0 = move-exception
            goto L1b
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.abo.call():acg");
    }

    public Future<Drawable> a(JSONObject jSONObject, String str, boolean z) {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        String string = z ? jSONObject2.getString(NativeProtocol.IMAGE_URL_KEY) : jSONObject2.optString(NativeProtocol.IMAGE_URL_KEY);
        if (!TextUtils.isEmpty(string)) {
            return this.b.a(string, new abq(this, z));
        }
        a(0, z);
        return new adb(null);
    }

    public void a(int i) {
        synchronized (this.e) {
            this.g = true;
            this.h = i;
        }
    }

    public void a(int i, boolean z) {
        if (z) {
            a(i);
        }
    }

    public boolean b() {
        boolean z;
        synchronized (this.e) {
            z = this.g;
        }
        return z;
    }
}
