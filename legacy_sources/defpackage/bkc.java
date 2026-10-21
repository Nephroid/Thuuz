package defpackage;

import android.view.View;
import com.android.volley.VolleyError;
import com.thuuz.gtv.widget.NetworkListenableImageView;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
class bkc implements ccc {
    final /* synthetic */ bjy a;

    private bkc(bjy bjyVar) {
        this.a = bjyVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ bkc(bjy bjyVar, bjp bjpVar) {
        this(bjyVar);
    }

    @Override // defpackage.ccc
    public void a(NetworkListenableImageView networkListenableImageView, VolleyError volleyError) {
        Logger logger;
        try {
            networkListenableImageView.setVisibility(8);
            View view = (View) networkListenableImageView.getParent();
            if (view != null) {
                ((bjw) view.getTag()).d.setVisibility(0);
            }
        } catch (Throwable th) {
            logger = this.a.a.getLogger();
            logger.log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
