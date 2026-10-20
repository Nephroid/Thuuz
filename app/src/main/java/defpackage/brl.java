package defpackage;
// [removed legacy tracker import]
// [removed legacy tracker import]
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.SettingsAccountActivity;

/* loaded from: classes.dex */
public class brl extends bqg {
    public brl(AbstractActivity abstractActivity) {
        super(abstractActivity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bqg
    public void a(GraphUser graphUser) {
        bqg aG;
        super.a(graphUser);
        getLogger().info("attempting thuuz login for fb user: " + graphUser);
        SettingsAccountActivity settingsAccountActivity = (SettingsAccountActivity) b();
        if (settingsAccountActivity != null) {
            aG = settingsAccountActivity.aG();
            SettingsAccountActivity.a((AbstractActivity) settingsAccountActivity, aG, true);
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
