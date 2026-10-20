package defpackage;

import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.hq;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/* loaded from: classes.dex */
public final class aeq {
    private final String a;
    private String b;
    private boolean c;
    private boolean e;
    private String f;
    private BitSet h;
    private String i;
    private int d = 1;
    private final List<hk> g = new ArrayList();

    public aeq(String str) {
        this.a = str;
    }

    public aeq a(String str) {
        this.b = str;
        return this;
    }

    public aeq a(boolean z) {
        this.c = z;
        return this;
    }

    public hq a() {
        int i = 0;
        int[] iArr = null;
        if (this.h != null) {
            iArr = new int[this.h.cardinality()];
            int nextSetBit = this.h.nextSetBit(0);
            while (nextSetBit >= 0) {
                iArr[i] = nextSetBit;
                nextSetBit = this.h.nextSetBit(nextSetBit + 1);
                i++;
            }
        }
        return new hq(this.a, this.b, this.c, this.d, this.e, this.f, (hk[]) this.g.toArray(new hk[this.g.size()]), iArr, this.i);
    }
}
