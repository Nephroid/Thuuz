package defpackage;

import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamDetailActivity;
import com.thuuz.gtv.data.FantasySettingsCustomizations;

/* loaded from: classes.dex */
public class bga {
    final FantasySettingsCustomizations.ALERT_TYPE a;
    final String b;
    final String c;
    final /* synthetic */ SettingsFantasyTeamDetailActivity d;

    public bga(SettingsFantasyTeamDetailActivity settingsFantasyTeamDetailActivity, FantasySettingsCustomizations.ALERT_TYPE alert_type, FantasySettingsCustomizations fantasySettingsCustomizations) {
        this.d = settingsFantasyTeamDetailActivity;
        this.a = alert_type;
        if (fantasySettingsCustomizations != null) {
            this.b = fantasySettingsCustomizations.c(alert_type);
            this.c = fantasySettingsCustomizations.d(alert_type);
        } else {
            this.b = alert_type.b();
            this.c = alert_type.c();
        }
    }
}
