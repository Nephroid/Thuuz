package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bxn implements View.OnClickListener {
    final /* synthetic */ TVProviderFragment a;

    public bxn(TVProviderFragment tVProviderFragment) {
        this.a = tVProviderFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        AbstractActivity fragsActivity;
        try {
            fragsActivity = this.a.getFragsActivity();
            cad.b(fragsActivity, "Hopper Troubleshooting", this.a.getResources().getString(bbt.hopper_trouble));
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
