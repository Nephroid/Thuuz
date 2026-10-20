package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bjz implements View.OnClickListener {
    final /* synthetic */ bjy a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bjz(bjy bjyVar) {
        this.a = bjyVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        try {
            if (this.a.a.getFragsActivity() != null) {
                this.a.a((ArrayList<bpa>) this.a.a.getBroadcastsFiltered());
            }
        } catch (Throwable th) {
            logger = this.a.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
