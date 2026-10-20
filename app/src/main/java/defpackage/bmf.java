package defpackage;

import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.Comparator;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bmf implements Comparator<bmc> {
    final /* synthetic */ bmd a;

    private bmf(bmd bmdVar) {
        this.a = bmdVar;
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(bmc bmcVar, bmc bmcVar2) {
        WeakReference weakReference;
        int i;
        Throwable th;
        IService F;
        weakReference = this.a.e;
        AbstractActivity abstractActivity = (AbstractActivity) weakReference.get();
        if (abstractActivity == null) {
            return 0;
        }
        try {
            F = abstractActivity.F();
        } catch (Throwable th2) {
            i = 0;
            th = th2;
        }
        if (F != null) {
            bpr c = F.c(bmcVar.d());
            bpr c2 = F.c(bmcVar2.d());
            if (c != null && c2 != null) {
                i = Boolean.valueOf(c2.a(abstractActivity.I())).compareTo(Boolean.valueOf(c.a(abstractActivity.I())));
                if (i == 0) {
                    try {
                        i = Integer.valueOf(c.b()).compareTo(Integer.valueOf(c2.b()));
                    } catch (Throwable th3) {
                        th = th3;
                        this.a.b().log(Level.SEVERE, "problem: ", th);
                        return i;
                    }
                }
                return i;
            }
        }
        i = 0;
        return i;
    }
}
