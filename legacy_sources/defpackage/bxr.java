package defpackage;

import android.widget.ListView;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bxr implements Runnable {
    final /* synthetic */ TVProviderFragment a;

    public bxr(TVProviderFragment tVProviderFragment) {
        this.a = tVProviderFragment;
    }

    @Override // java.lang.Runnable
    public void run() {
        Logger logger;
        ListView listView;
        int scrollAnimation;
        try {
            listView = this.a.tvProviderList;
            listView.setVisibility(8);
            scrollAnimation = this.a.scrollAnimation();
            this.a.waitForScrollAnimation(scrollAnimation);
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem running hide in handler: ", th);
        }
    }
}
