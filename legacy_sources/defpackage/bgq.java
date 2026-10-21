package defpackage;

import android.view.View;
import android.widget.Button;
import com.thuuz.gtv.activity.feed.AbstractFeedFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bgq implements View.OnClickListener {
    final /* synthetic */ AbstractFeedFragment a;

    public bgq(AbstractFeedFragment abstractFeedFragment) {
        this.a = abstractFeedFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        Button button;
        try {
            this.a.setShowSpoilersOk();
            button = this.a.spoilersButton;
            button.setVisibility(8);
            this.a.doRefresh(false);
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
