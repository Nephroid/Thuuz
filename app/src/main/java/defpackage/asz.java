package defpackage;

import java.util.Collections;
import java.util.Map;

/* loaded from: classes.dex */
public class asz {
    private final Map<String, ww> a;
    private final ww b;

    public Map<String, ww> a() {
        return Collections.unmodifiableMap(this.a);
    }

    public void a(String str, ww wwVar) {
        this.a.put(str, wwVar);
    }

    public ww b() {
        return this.b;
    }

    public String toString() {
        return "Properties: " + a() + " pushAfterEvaluate: " + this.b;
    }
}
