package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bxe implements Runnable {
    final /* synthetic */ TVProviderFragment a;

    public bxe(TVProviderFragment tVProviderFragment) {
        this.a = tVProviderFragment;
    }

    @Override // java.lang.Runnable
    public void run() {
        Logger logger;
        bpu bpuVar;
        AbstractActivity fragsActivity;
        try {
            this.a.hideListAndShowSpinner();
            bpuVar = this.a.selectedLocation;
            if (bpuVar.f()) {
                this.a.showRemoteInstructions(true);
            }
            this.a.showZipPrompt(false);
            this.a.showDiscoverySection(true);
            fragsActivity = this.a.getFragsActivity();
            if (!fragsActivity.T()) {
                this.a.showNeedWifiMessage(true);
            }
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem running hide in handler: ", th);
        }
    }
}
