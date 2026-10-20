package defpackage;

import java.util.Map;

/* loaded from: classes.dex */
public final class azf<T> extends axd<T> {
    private final ayg<T> a;
    private final Map<String, azg> b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ azf(ayg aygVar, Map map, aze azeVar) {
        this(aygVar, map);
    }

    private azf(ayg<T> aygVar, Map<String, azg> map) {
        this.a = aygVar;
        this.b = map;
    }

    @Override // defpackage.axd
    public void a(bav bavVar, T t) {
        if (t == null) {
            bavVar.f();
            return;
        }
        bavVar.d();
        try {
            for (azg azgVar : this.b.values()) {
                if (azgVar.h) {
                    bavVar.a(azgVar.g);
                    azgVar.a(bavVar, t);
                }
            }
            bavVar.e();
        } catch (IllegalAccessException e) {
            throw new AssertionError();
        }
    }
}
