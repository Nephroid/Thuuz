package defpackage;

import com.facebook.FacebookRequestError;
import com.facebook.model.GraphUser;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.SettingsMaintAccountActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class buh extends bqg {
    public buh(AbstractActivity abstractActivity) {
        super(abstractActivity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bqg
    public void a(GraphUser graphUser) {
        super.a(graphUser);
        getLogger().info("attempting associate for fb user: " + graphUser);
        String bd = c().bd();
        if (bd == null || bd.length() == 0) {
            getLogger().log(Level.WARNING, "Attempting account associate with invalid social id");
            return;
        }
        SettingsMaintAccountActivity settingsMaintAccountActivity = (SettingsMaintAccountActivity) b();
        if (settingsMaintAccountActivity != null) {
            settingsMaintAccountActivity.c(bd);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bqg
    public void a(FacebookRequestError facebookRequestError) {
        super.a(facebookRequestError);
        AbstractActivity b = b();
        if (b != null) {
            b.c("Failed to Connect", "Facebook says: " + b.a(facebookRequestError));
        }
    }

    @Override // defpackage.bqg
    public void a(boolean z) {
    }
}
