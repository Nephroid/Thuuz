package defpackage;

import com.thuuz.gtv.activity.game_page.AbstractGamePageFragment;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import com.thuuz.gtv.activity.game_page.GamePageWebViewFragment;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bjf implements Runnable {
    final /* synthetic */ GamePageActivity a;

    public bjf(GamePageActivity gamePageActivity) {
        this.a = gamePageActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            bbf Y = this.a.Y();
            if (Y != null && (Y instanceof GamePageWebViewFragment)) {
                GamePageWebViewFragment gamePageWebViewFragment = (GamePageWebViewFragment) Y;
                gamePageWebViewFragment.getWebView().requestLayout();
                if (AbstractGamePageFragment.FragType.GRAPH.c() == gamePageWebViewFragment.getFragTypeNum()) {
                    Y.doRefresh(true);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
