package defpackage;

import android.view.View;
import android.widget.Button;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bxm implements View.OnClickListener {
    final /* synthetic */ TVProviderFragment a;

    public bxm(TVProviderFragment tVProviderFragment) {
        this.a = tVProviderFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        Button button;
        try {
            this.a.showDeviceEditLay(false);
            this.a.showDeviceSpinnerLay(false);
            button = this.a.discover_button;
            button.performClick();
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
