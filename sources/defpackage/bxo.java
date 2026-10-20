package defpackage;

import android.view.View;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bxo implements View.OnClickListener {
    final /* synthetic */ TVProviderFragment a;

    public bxo(TVProviderFragment tVProviderFragment) {
        this.a = tVProviderFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        bpu bpuVar;
        bpu bpuVar2;
        try {
            bpuVar = this.a.selectedLocation;
            if (bpuVar != null) {
                bpuVar2 = this.a.selectedLocation;
                bpuVar2.n();
            }
            this.a.saveSelectedProviderAndFinish();
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
