package defpackage;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class aww extends awu {
    private final LinkedTreeMap<String, awu> a = new LinkedTreeMap<>();

    public void a(String str, awu awuVar) {
        if (awuVar == null) {
            awuVar = awv.a;
        }
        this.a.put(str, awuVar);
    }

    public void a(String str, String str2) {
        a(str, a(str2));
    }

    public void a(String str, Number number) {
        a(str, a(number));
    }

    private awu a(Object obj) {
        return obj == null ? awv.a : new awx(obj);
    }

    public Set<Map.Entry<String, awu>> l() {
        return this.a.entrySet();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof aww) && ((aww) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
