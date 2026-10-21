package defpackage;

import com.thuuz.gtv.ThuuzService;
import java.util.Comparator;

/* loaded from: classes.dex */
public class bcu implements Comparator<bpr> {
    final /* synthetic */ ThuuzService a;

    public bcu(ThuuzService thuuzService) {
        this.a = thuuzService;
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(bpr bprVar, bpr bprVar2) {
        if (bprVar == null || bprVar2 == null) {
            return 0;
        }
        int compareTo = Boolean.valueOf(bprVar2.a(this.a.n())).compareTo(Boolean.valueOf(bprVar.a(this.a.n())));
        if (compareTo == 0) {
            return Integer.valueOf(bprVar.b()).compareTo(Integer.valueOf(bprVar2.b()));
        }
        return compareTo;
    }
}
