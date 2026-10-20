package defpackage;

import java.util.Comparator;

/* loaded from: classes.dex */
public class boe implements Comparator<bpr> {
    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(bpr bprVar, bpr bprVar2) {
        if (bprVar == null || bprVar2 == null) {
            return 0;
        }
        return Integer.valueOf(bprVar.b()).compareTo(Integer.valueOf(bprVar2.b()));
    }
}
