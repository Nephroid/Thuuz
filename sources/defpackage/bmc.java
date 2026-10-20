package defpackage;

import android.net.http.EventHandler;
import com.thuuz.gtv.activity.sliding.SlidingMenuItemType;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bmc {
    private SlidingMenuItemType a;
    private int b;
    private boolean c = true;
    private boolean d;

    public bmc(SlidingMenuItemType slidingMenuItemType, int i) {
        this.d = false;
        this.a = slidingMenuItemType;
        this.b = i;
        this.d = false;
    }

    public bmc(SlidingMenuItemType slidingMenuItemType, int i, boolean z) {
        this.d = false;
        this.a = slidingMenuItemType;
        this.b = i;
        this.d = z;
    }

    public boolean a() {
        if (this.a == SlidingMenuItemType.SPORT) {
            return true;
        }
        if (this.a == SlidingMenuItemType.LEAGUE) {
            switch (this.b) {
                case EventHandler.ERROR_REDIRECT_LOOP /* -9 */:
                case EventHandler.ERROR_TIMEOUT /* -8 */:
                    return true;
            }
        }
        return false;
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.b);
            jSONObject.put("type", this.a.c());
            jSONObject.put("expanded", this.d);
            return jSONObject;
        } catch (Throwable th) {
            return null;
        }
    }

    public boolean c() {
        return this.c;
    }

    public int d() {
        return this.b;
    }

    public SlidingMenuItemType e() {
        return this.a;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean f() {
        return this.d;
    }

    public void b(boolean z) {
        this.d = z;
    }
}
