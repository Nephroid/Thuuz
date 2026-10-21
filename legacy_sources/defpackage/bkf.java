package defpackage;

import android.content.Intent;
import android.view.View;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
class bkf implements bjx {
    final /* synthetic */ bke a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bkf(bke bkeVar) {
        this.a = bkeVar;
    }

    @Override // defpackage.bjx
    public void a(bpa bpaVar, View view) {
        Logger logger;
        try {
            GamePageActivity fragsActivity = this.a.a.a.getFragsActivity();
            if (fragsActivity != null) {
                Intent aS = fragsActivity.aS();
                if (aS == null) {
                    fragsActivity.c("Cannot find Sling app", "");
                } else {
                    String valueOf = String.valueOf(bpaVar.i());
                    aS.putExtra("android.intent.extra.TEXT", valueOf);
                    fragsActivity.startActivity(aS);
                    fragsActivity.j().a("launch", "sling", valueOf, this.a.a.a.getGame().i());
                }
            }
        } catch (Throwable th) {
            logger = this.a.a.a.getLogger();
            logger.log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
