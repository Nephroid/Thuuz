package defpackage;

import defpackage.hf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class he<O extends hf> {
    private final hj<?, O> a;
    private final hk<?> b;
    private final ArrayList<hq> c;

    /* JADX WARN: Multi-variable type inference failed */
    public <C extends hi> he(hj<C, O> hjVar, hk<C> hkVar, hq... hqVarArr) {
        this.a = hjVar;
        this.b = hkVar;
        this.c = new ArrayList<>(Arrays.asList(hqVarArr));
    }

    public hj<?, O> a() {
        return this.a;
    }

    public List<hq> b() {
        return this.c;
    }

    public hk<?> c() {
        return this.b;
    }
}
