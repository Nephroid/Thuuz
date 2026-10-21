package defpackage;

import android.content.Intent;
import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.game_page.AbstractTvFragment;
import com.thuuz.gtv.settings.loc.SettingsEditLocationActivity;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class biz implements View.OnClickListener {
    final /* synthetic */ AbstractTvFragment a;

    /* JADX INFO: Access modifiers changed from: protected */
    public biz(AbstractTvFragment abstractTvFragment) {
        this.a = abstractTvFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        AbstractActivity fragsActivity;
        try {
            fragsActivity = this.a.getFragsActivity();
            if (fragsActivity != null) {
                fragsActivity.startActivityForResult(new Intent(fragsActivity, (Class<?>) SettingsEditLocationActivity.class), 291);
            }
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
