package defpackage;

import java.lang.Enum;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
final class bat<T extends Enum<T>> extends axd<T> {
    private final Map<String, T> a = new HashMap();
    private final Map<T, String> b = new HashMap();

    public bat(Class<T> cls) {
        try {
            for (T t : cls.getEnumConstants()) {
                String name = t.name();
                axg axgVar = (axg) cls.getField(name).getAnnotation(axg.class);
                String a = axgVar != null ? axgVar.a() : name;
                this.a.put(a, t);
                this.b.put(t, a);
            }
        } catch (NoSuchFieldException e) {
            throw new AssertionError();
        }
    }

    @Override // defpackage.axd
    public void a(bav bavVar, T t) {
        bavVar.b(t == null ? null : this.b.get(t));
    }
}
