package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bxf implements DialogInterface.OnClickListener {
    final /* synthetic */ bqc a;
    final /* synthetic */ AlertDialog b;
    final /* synthetic */ TVProviderFragment c;

    public bxf(TVProviderFragment tVProviderFragment, bqc bqcVar, AlertDialog alertDialog) {
        this.c = tVProviderFragment;
        this.a = bqcVar;
        this.b = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Logger logger;
        AbstractActivity fragsActivity;
        Integer num;
        try {
            fragsActivity = this.c.getFragsActivity();
            bqc bqcVar = this.a;
            num = this.c.location_id;
            new bwk(fragsActivity, bqcVar, num.intValue(), this.c).execute(new Void[0]);
            this.b.dismiss();
        } catch (Throwable th) {
            logger = this.c.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
