package defpackage;

import android.os.Bundle;

@zy
/* loaded from: classes.dex */
public class aco {
    private final Object a;
    private int b;
    private int c;
    private final acl d;
    private final String e;

    aco(acl aclVar, String str) {
        this.a = new Object();
        this.d = aclVar;
        this.e = str;
    }

    public aco(String str) {
        this(acl.a(), str);
    }

    public Bundle a() {
        Bundle bundle;
        synchronized (this.a) {
            bundle = new Bundle();
            bundle.putInt("pmnli", this.b);
            bundle.putInt("pmnll", this.c);
        }
        return bundle;
    }

    public void a(int i, int i2) {
        synchronized (this.a) {
            this.b = i;
            this.c = i2;
            this.d.a(this.e, this);
        }
    }
}
