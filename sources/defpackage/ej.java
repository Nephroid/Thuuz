package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.actionbarsherlock.widget.ActivityChooserView;
import com.facebook.AppEventsConstants;
import com.google.android.gms.analytics.t;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* loaded from: classes.dex */
public class ej {
    private final el a;
    private Context b;
    private final Map<String, String> c;
    private eq d;
    private final fj e;
    private final er f;
    private final fi g;
    private boolean h;
    private ek i;
    private ev j;
    private dy k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ej(String str, el elVar, Context context) {
        this(str, elVar, fj.a(), er.a(), fi.a(), new gm("tracking"), context);
    }

    ej(String str, el elVar, fj fjVar, er erVar, fi fiVar, eq eqVar, Context context) {
        this.c = new HashMap();
        this.a = elVar;
        if (context != null) {
            this.b = context.getApplicationContext();
        }
        if (str != null) {
            this.c.put("&tid", str);
        }
        this.c.put("useSecure", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        this.e = fjVar;
        this.f = erVar;
        this.g = fiVar;
        this.c.put("&a", Integer.toString(new Random().nextInt(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) + 1));
        this.d = eqVar;
        this.i = new ek(this);
        c(false);
    }

    public void a(long j) {
        this.i.a(1000 * j);
    }

    public void a(String str) {
        a("&cd", str);
    }

    public void a(String str, String str2) {
        kb.a(str, "Key should be non-null");
        t.a().a(t.a.SET);
        this.c.put(str, str2);
    }

    public void a(Map<String, String> map) {
        t.a().a(t.a.SEND);
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.c);
        if (map != null) {
            hashMap.putAll(map);
        }
        if (TextUtils.isEmpty((CharSequence) hashMap.get("&tid"))) {
            gn.d(String.format("Missing tracking id (%s) parameter.", "&tid"));
        }
        String str = (String) hashMap.get("&t");
        if (TextUtils.isEmpty(str)) {
            gn.d(String.format("Missing hit type (%s) parameter.", "&t"));
            str = "";
        }
        if (this.i.a()) {
            hashMap.put("&sc", "start");
        }
        String lowerCase = str.toLowerCase();
        if ("screenview".equals(lowerCase) || "pageview".equals(lowerCase) || "appview".equals(lowerCase) || TextUtils.isEmpty(lowerCase)) {
            int parseInt = Integer.parseInt(this.c.get("&a")) + 1;
            if (parseInt >= Integer.MAX_VALUE) {
                parseInt = 1;
            }
            this.c.put("&a", Integer.toString(parseInt));
        }
        if (lowerCase.equals("transaction") || lowerCase.equals("item") || this.d.a()) {
            this.a.a(hashMap);
        } else {
            gn.d("Too many hits sent too quickly, rate limiting invoked.");
        }
    }

    public void a(boolean z) {
        if (this.h == z) {
            return;
        }
        this.h = z;
        if (z) {
            this.k = new dy(this, Thread.getDefaultUncaughtExceptionHandler(), this.b);
            Thread.setDefaultUncaughtExceptionHandler(this.k);
            gn.c("Uncaught exceptions will be reported to Google Analytics.");
        } else {
            if (this.k != null) {
                Thread.setDefaultUncaughtExceptionHandler(this.k.a());
            } else {
                Thread.setDefaultUncaughtExceptionHandler(null);
            }
            gn.c("Uncaught exceptions will not be reported to Google Analytics.");
        }
    }

    public void b(String str) {
        a("&an", str);
    }

    public void b(boolean z) {
        this.i.a(z);
    }

    public void c(boolean z) {
        if (!z) {
            this.c.put("&ate", null);
            this.c.put("&adid", null);
            return;
        }
        if (this.c.containsKey("&ate")) {
            this.c.remove("&ate");
        }
        if (this.c.containsKey("&adid")) {
            this.c.remove("&adid");
        }
    }
}
