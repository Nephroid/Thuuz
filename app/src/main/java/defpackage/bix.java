package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.game_page.AbstractTvFragment;
import com.thuuz.gtv.data.OnlineResource;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bix implements View.OnClickListener {
    final /* synthetic */ OnlineResource a;
    final /* synthetic */ AbstractTvFragment b;

    public bix(AbstractTvFragment abstractTvFragment, OnlineResource onlineResource) {
        this.b = abstractTvFragment;
        this.a = onlineResource;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        AbstractActivity fragsActivity;
        try {
            fragsActivity = this.b.getFragsActivity();
            if (fragsActivity != null) {
                fragsActivity.f(this.a.g());
                this.b.trackLinkClick(this.a);
            }
        } catch (Throwable th) {
            logger = this.b.getLogger();
            logger.log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
