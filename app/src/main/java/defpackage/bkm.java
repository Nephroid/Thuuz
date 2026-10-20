package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import com.thuuz.gtv.activity.game_page.HighlightsFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bkm implements AdapterView.OnItemClickListener {
    final /* synthetic */ HighlightsFragment a;

    public bkm(HighlightsFragment highlightsFragment) {
        this.a = highlightsFragment;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Logger logger;
        try {
            String a = ((bpp) this.a.buttons.get(i).b()).a();
            GamePageActivity fragsActivity = this.a.getFragsActivity();
            if (fragsActivity != null) {
                fragsActivity.f(a);
                fragsActivity.j().a("onlineresource", "watch_highlight", "game_page", fragsActivity.aO());
                view.invalidate();
            }
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
