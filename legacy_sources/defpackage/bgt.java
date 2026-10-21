package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.feed.FeedFragment;
import com.thuuz.gtv.activity.sports.ShareActivity;
import com.thuuz.gtv.settings.SettingsAccountActivity;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bgt implements View.OnClickListener {
    final /* synthetic */ FeedFragment a;
    private final ShareActivity.SHARE_TYPE b;
    private final SettingsAccountActivity.SOURCE c;

    public bgt(FeedFragment feedFragment, ShareActivity.SHARE_TYPE share_type, SettingsAccountActivity.SOURCE source) {
        this.a = feedFragment;
        this.b = share_type;
        this.c = source;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        AbstractActivity fragsActivity;
        try {
            fragsActivity = this.a.getFragsActivity();
            if (fragsActivity != null) {
                if (fragsActivity.j().i()) {
                    fragsActivity.a(this.b, -1, "", "");
                } else {
                    fragsActivity.a(this.c);
                }
            }
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
