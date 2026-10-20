package defpackage;

import java.util.Collections;
import java.util.Map;

/* loaded from: classes.dex */
public class v {
    public byte[] a;
    public String b;
    public long c;
    public long d;
    public long e;
    public Map<String, String> f = Collections.emptyMap();

    public boolean a() {
        return this.d < System.currentTimeMillis();
    }

    public boolean b() {
        return this.e < System.currentTimeMillis();
    }
}
