package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.game_page.GamePageTvFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bjt implements View.OnClickListener {
    final /* synthetic */ GamePageTvFragment a;

    public bjt(GamePageTvFragment gamePageTvFragment) {
        this.a = gamePageTvFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        try {
            this.a.handleSeeAllTVSchedule(view);
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
