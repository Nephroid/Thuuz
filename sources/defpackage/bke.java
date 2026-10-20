package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
class bke implements View.OnClickListener {
    final /* synthetic */ bjy a;

    private bke(bjy bjyVar) {
        this.a = bjyVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ bke(bjy bjyVar, bjp bjpVar) {
        this(bjyVar);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        try {
            GamePageActivity fragsActivity = this.a.a.getFragsActivity();
            if (fragsActivity != null) {
                ArrayList<bpa> d = this.a.a.getGame().d(fragsActivity.I());
                if (fragsActivity.aS() == null) {
                    fragsActivity.c("Cannot find Sling app", "");
                } else if (d.size() == 0) {
                    fragsActivity.c("Cannot find broadcast", "");
                } else {
                    bkf bkfVar = new bkf(this);
                    if (d.size() > 1) {
                        this.a.a.handleMultipleWatchNow(d, bkfVar);
                    } else {
                        bkfVar.a(d.get(0), null);
                    }
                }
            }
        } catch (Throwable th) {
            logger = this.a.a.getLogger();
            logger.log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
