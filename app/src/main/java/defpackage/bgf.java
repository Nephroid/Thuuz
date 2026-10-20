package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity;

/* loaded from: classes.dex */
public class bgf implements View.OnClickListener {
    final /* synthetic */ SettingsFantasyTeamsActivity a;

    public bgf(SettingsFantasyTeamsActivity settingsFantasyTeamsActivity) {
        this.a = settingsFantasyTeamsActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.g();
    }
}
