package defpackage;

import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class asd {
    private final Set<String> a;

    public abstract ww a(Map<String, ww> map);

    public abstract boolean a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(Set<String> set) {
        return set.containsAll(this.a);
    }

    public Set<String> b() {
        return this.a;
    }
}
