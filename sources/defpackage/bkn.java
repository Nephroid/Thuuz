package defpackage;

import android.view.ViewTreeObserver;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import com.thuuz.gtv.activity.game_page.HighlightsFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bkn implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ HighlightsFragment a;

    public bkn(HighlightsFragment highlightsFragment) {
        this.a = highlightsFragment;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        Logger logger;
        try {
            GamePageActivity fragsActivity = this.a.getFragsActivity();
            if (fragsActivity != null) {
                fragsActivity.aa().postDelayed(new bko(this), 60L);
            }
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
