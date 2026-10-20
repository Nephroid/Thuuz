package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class caw extends bzt {
    final /* synthetic */ cat a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public caw(cat catVar, AbstractActivity abstractActivity) {
        super(abstractActivity);
        this.a = catVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bzt, android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(Integer num) {
        bmd Z;
        try {
            try {
                if (num.intValue() != 0) {
                    AbstractActivity a = a();
                    if (a != null) {
                        a.e().info("hiding spin in sportinfo");
                        a.e(false);
                        return;
                    }
                    return;
                }
                AbstractActivity a2 = a();
                if (a2 != null && (Z = a2.Z()) != null) {
                    Z.c();
                }
                AbstractActivity a3 = a();
                if (a3 != null) {
                    a3.e().info("hiding spin in sportinfo");
                    a3.e(false);
                }
            } catch (Throwable th) {
                this.a.getLogger().log(Level.SEVERE, "sportinfo async post problem", th);
                AbstractActivity a4 = a();
                if (a4 != null) {
                    a4.e().info("hiding spin in sportinfo");
                    a4.e(false);
                }
            }
        } catch (Throwable th2) {
            AbstractActivity a5 = a();
            if (a5 != null) {
                a5.e().info("hiding spin in sportinfo");
                a5.e(false);
            }
            throw th2;
        }
    }
}
