package defpackage;

import android.os.Bundle;

@zy
/* loaded from: classes.dex */
public class ajx {
    private ajy a;
    private boolean b;
    private boolean c;

    public ajx() {
        boolean z = false;
        Bundle m = acl.m();
        if (m != null && m.getBoolean("gads:block_autoclicks", false)) {
            z = true;
        }
        this.c = z;
    }

    public ajx(boolean z) {
        this.c = z;
    }

    public void a() {
        this.b = true;
    }

    public void a(ajy ajyVar) {
        this.a = ajyVar;
    }

    public void a(String str) {
        adn.a("Action was blocked because no click was detected.");
        if (this.a != null) {
            this.a.a(str);
        }
    }

    public boolean b() {
        return !this.c || this.b;
    }
}
