package defpackage;

import android.support.v4.app.Fragment;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.Date;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public abstract class bbf extends Fragment {
    private String logName = getClass().getSimpleName() + ":thuuz";

    public abstract void doRefresh(boolean z);

    /* JADX INFO: Access modifiers changed from: protected */
    public Logger getLogger() {
        Logger logger = Logger.getLogger(this.logName);
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    public AbstractActivity getFragsActivity() {
        return (AbstractActivity) getActivity();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Date getCorrectedNow() {
        AbstractActivity fragsActivity = getFragsActivity();
        return fragsActivity != null ? fragsActivity.I() : new Date();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized IService getService() {
        AbstractActivity fragsActivity;
        fragsActivity = getFragsActivity();
        return fragsActivity == null ? null : fragsActivity.F();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isActive() {
        boolean z = true;
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity == null || !fragsActivity.d()) {
            return false;
        }
        if (isTabbedView() && this != fragsActivity.Y()) {
            z = false;
        }
        return z;
    }

    public void onServiceConnected() {
    }

    public boolean isTabbedView() {
        return true;
    }

    public boolean requestFocus() {
        return false;
    }

    public boolean hasFocus() {
        return false;
    }
}
