package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bdg implements View.OnClickListener {
    final /* synthetic */ AbstractActivity a;

    public bdg(AbstractActivity abstractActivity) {
        this.a = abstractActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        bmd bmdVar;
        try {
            bmc bmcVar = (bmc) view.getTag();
            if (bmcVar == null) {
                this.a.e().info("no tag");
            } else if (bmcVar.a()) {
                bmdVar = this.a.F;
                bmdVar.a(bmcVar);
                this.a.ay();
            } else {
                this.a.a(bmcVar);
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
