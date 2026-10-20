package defpackage;

import android.view.View;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.game_page.GamePageGameTabFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bjn implements View.OnClickListener {
    final /* synthetic */ GamePageGameTabFragment a;

    public bjn(GamePageGameTabFragment gamePageGameTabFragment) {
        this.a = gamePageGameTabFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Level level;
        Logger logger;
        if (this.a.getFragsActivity().aJ()) {
            if (App.aV() == Level.WARNING) {
                level = Level.INFO;
            } else {
                level = Level.WARNING;
            }
            logger = this.a.getLogger();
            logger.warning("setting log level to: " + level);
            App.a(level);
        }
    }
}
