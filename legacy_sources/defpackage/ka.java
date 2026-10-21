package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class ka {
    private final List<String> a;
    private final Object b;

    private ka(Object obj) {
        this.b = kb.a(obj);
        this.a = new ArrayList();
    }

    public ka a(String str, Object obj) {
        this.a.add(((String) kb.a(str)) + "=" + String.valueOf(obj));
        return this;
    }

    public String toString() {
        StringBuilder append = new StringBuilder(100).append(this.b.getClass().getSimpleName()).append('{');
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            append.append(this.a.get(i));
            if (i < size - 1) {
                append.append(", ");
            }
        }
        return append.append('}').toString();
    }
}
