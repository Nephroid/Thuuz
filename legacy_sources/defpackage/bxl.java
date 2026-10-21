package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.Locations;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bxl implements View.OnClickListener {
    final /* synthetic */ TVProviderFragment a;

    public bxl(TVProviderFragment tVProviderFragment) {
        this.a = tVProviderFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        bpu bpuVar;
        bpu bpuVar2;
        AbstractActivity fragsActivity;
        bpu bpuVar3;
        try {
            bpuVar = this.a.selectedLocation;
            if (bpuVar != null) {
                bpuVar2 = this.a.selectedLocation;
                bpuVar2.n();
                fragsActivity = this.a.getFragsActivity();
                Locations ax = fragsActivity.j().ax();
                bpuVar3 = this.a.selectedLocation;
                ax.b(bpuVar3);
            }
            this.a.showDeviceEditLay(false);
            this.a.showDeviceSpinnerLay(false);
            this.a.showDiscoverySection(true);
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
