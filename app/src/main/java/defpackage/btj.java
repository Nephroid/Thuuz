package defpackage;

import android.widget.CompoundButton;
import com.thuuz.gtv.settings.SettingsLeagueDetailActivity;

/* loaded from: classes.dex */
public class btj implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SettingsLeagueDetailActivity a;

    public btj(SettingsLeagueDetailActivity settingsLeagueDetailActivity) {
        this.a = settingsLeagueDetailActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.a.p.a(z);
        this.a.i.setVisibility(z ? 0 : 8);
        this.a.aF();
        if (!this.a.j().bD() && this.a.p.b().booleanValue()) {
            this.a.F_();
        }
    }
}
