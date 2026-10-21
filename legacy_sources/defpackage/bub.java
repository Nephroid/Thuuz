package defpackage;
// [removed legacy tracker import]
// [removed legacy tracker import]
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.SettingsLoginActivity;

/* loaded from: classes.dex */
public class bub extends bqg {
    public bub(AbstractActivity abstractActivity) {
        super(abstractActivity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bqg
    public void a(GraphUser graphUser) {
        String id = graphUser.getId();
        String bc = c().bc();
        SettingsLoginActivity settingsLoginActivity = (SettingsLoginActivity) b();
        if (settingsLoginActivity != null) {
            if (bc != null && bc.length() > 0 && id.length() > 0 && !bc.equalsIgnoreCase(id)) {
                settingsLoginActivity.h(true);
                return;
            }
            super.a(graphUser);
            if (c().aZ()) {
                settingsLoginActivity.aN();
            } else {
                getLogger().info("attempting associate for fb user: " + graphUser);
                settingsLoginActivity.a(id, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bqg
    public void a(FacebookRequestError facebookRequestError) {
        SettingsLoginActivity settingsLoginActivity = (SettingsLoginActivity) b();
        if (settingsLoginActivity != null) {
            settingsLoginActivity.c("Failed to Connect", "Facebook says: " + settingsLoginActivity.a(facebookRequestError));
            settingsLoginActivity.aN();
        }
    }
}
