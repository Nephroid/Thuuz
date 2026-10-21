package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bxk implements View.OnClickListener {
    final /* synthetic */ TVProviderFragment a;

    public bxk(TVProviderFragment tVProviderFragment) {
        this.a = tVProviderFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        bpu bpuVar;
        AbstractActivity fragsActivity;
        Integer num;
        bpu bpuVar2;
        bpu bpuVar3;
        bpu bpuVar4;
        try {
            bpuVar = this.a.selectedLocation;
            if (!bpuVar.o()) {
                bpuVar3 = this.a.selectedLocation;
                if (bpuVar3.f()) {
                    bpuVar4 = this.a.selectedLocation;
                    bpuVar4.m("dish");
                }
            }
            view.setEnabled(false);
            fragsActivity = this.a.getFragsActivity();
            num = this.a.location_id;
            int intValue = num.intValue();
            bpuVar2 = this.a.selectedLocation;
            new bwl(fragsActivity, intValue, bpuVar2, this.a).execute(new Void[0]);
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
