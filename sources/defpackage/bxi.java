package defpackage;

import android.widget.EditText;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bxi implements Runnable {
    final /* synthetic */ EditText a;
    final /* synthetic */ TVProviderFragment b;

    public bxi(TVProviderFragment tVProviderFragment, EditText editText) {
        this.b = tVProviderFragment;
        this.a = editText;
    }

    @Override // java.lang.Runnable
    public void run() {
        Logger logger;
        AbstractActivity fragsActivity;
        try {
            this.a.requestFocus();
            fragsActivity = this.b.getFragsActivity();
            fragsActivity.f(true);
        } catch (Throwable th) {
            logger = this.b.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
