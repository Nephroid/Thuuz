package defpackage;

import com.thuuz.gtv.activity.game_page.GamePageTvFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bjp implements Runnable {
    final /* synthetic */ GamePageTvFragment a;

    public bjp(GamePageTvFragment gamePageTvFragment) {
        this.a = gamePageTvFragment;
    }

    @Override // java.lang.Runnable
    public void run() {
        Logger logger;
        boolean z;
        try {
            this.a.hasFocused = this.a.hasFocus();
            z = this.a.hasFocused;
            if (!z) {
                this.a.requestFocus();
            }
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
