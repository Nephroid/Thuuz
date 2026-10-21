package defpackage;

import android.content.Intent;

@zy
/* loaded from: classes.dex */
public class ym {
    private final String a;

    public ym(String str) {
        this.a = str;
    }

    public String a() {
        return acx.e();
    }

    public boolean a(String str, int i, Intent intent) {
        if (str == null || intent == null) {
            return false;
        }
        String b = yl.b(intent);
        String c = yl.c(intent);
        if (b == null || c == null) {
            return false;
        }
        if (!str.equals(yl.a(b))) {
            adn.e("Developer payload not match.");
            return false;
        }
        if (this.a == null || yn.a(this.a, b, c)) {
            return true;
        }
        adn.e("Fail to verify signature.");
        return false;
    }
}
