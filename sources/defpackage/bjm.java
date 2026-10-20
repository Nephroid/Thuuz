package defpackage;

import com.thuuz.gtv.activity.game_page.GamePageGameTabFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bjm implements Runnable {
    final /* synthetic */ GamePageGameTabFragment a;

    public bjm(GamePageGameTabFragment gamePageGameTabFragment) {
        this.a = gamePageGameTabFragment;
    }

    @Override // java.lang.Runnable
    public void run() {
        Logger logger;
        Logger logger2;
        try {
            logger2 = this.a.getLogger();
            logger2.info("rerunning setNextFocuses() since some frags were not rendered");
            this.a.setNextFocuses(this.a.getChildFragmentManager());
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
