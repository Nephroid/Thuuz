package defpackage;

import android.view.View;
import com.thuuz.gtv.widget.NetworkListenableImageView;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
class bkd implements ccd {
    final /* synthetic */ bjy a;

    private bkd(bjy bjyVar) {
        this.a = bjyVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ bkd(bjy bjyVar, bjp bjpVar) {
        this(bjyVar);
    }

    @Override // defpackage.ccd
    public void a(NetworkListenableImageView networkListenableImageView) {
        Logger logger;
        try {
            networkListenableImageView.setVisibility(0);
            View view = (View) networkListenableImageView.getParent();
            if (view != null) {
                ((bjw) view.getTag()).d.setVisibility(8);
            }
        } catch (Throwable th) {
            logger = this.a.a.getLogger();
            logger.log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
