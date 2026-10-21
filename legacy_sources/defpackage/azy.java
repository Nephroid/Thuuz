package defpackage;

import java.util.BitSet;

/* loaded from: classes.dex */
final class azy extends axd<BitSet> {
    @Override // defpackage.axd
    public void a(bav bavVar, BitSet bitSet) {
        if (bitSet == null) {
            bavVar.f();
            return;
        }
        bavVar.b();
        for (int i = 0; i < bitSet.length(); i++) {
            bavVar.a(bitSet.get(i) ? 1 : 0);
        }
        bavVar.c();
    }
}
