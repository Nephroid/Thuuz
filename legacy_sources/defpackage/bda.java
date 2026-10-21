package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import java.util.Date;

/* loaded from: classes.dex */
public class bda extends bpr {
    final /* synthetic */ AbstractActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bda(AbstractActivity abstractActivity, bpx bpxVar, String str, int i, String str2, String str3, int i2, String str4, boolean z) {
        super(bpxVar, str, i, str2, str3, i2, str4, z);
        this.a = abstractActivity;
    }

    @Override // defpackage.bpr
    public boolean a(Date date) {
        return true;
    }
}
