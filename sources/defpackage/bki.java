package defpackage;

import android.view.View;
import android.widget.Button;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import com.thuuz.gtv.activity.game_page.GamePageWebViewFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bki implements View.OnClickListener {
    final /* synthetic */ GamePageWebViewFragment a;

    public bki(GamePageWebViewFragment gamePageWebViewFragment) {
        this.a = gamePageWebViewFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        Button button;
        try {
            GamePageActivity fragsActivity = this.a.getFragsActivity();
            if (fragsActivity != null) {
                fragsActivity.aM();
                button = this.a.spoilersButton;
                button.setVisibility(8);
                this.a.doRefresh(false);
            }
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
