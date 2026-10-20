package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bxg implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ TVProviderFragment b;

    public bxg(TVProviderFragment tVProviderFragment, AlertDialog alertDialog) {
        this.b = tVProviderFragment;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Logger logger;
        Button button;
        try {
            this.a.dismiss();
            button = this.b.discover_button;
            button.setEnabled(true);
            this.b.showDiscoveryInProgress(false);
            this.b.showDiscoverySection(true);
            this.b.showRemoteInstructions(true);
        } catch (Throwable th) {
            logger = this.b.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
