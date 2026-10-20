package defpackage;

import android.widget.CompoundButton;
import com.thuuz.gtv.App;
import com.thuuz.gtv.settings.SettingsLeagueDetailActivity;

/* loaded from: classes.dex */
public class btm implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SettingsLeagueDetailActivity a;

    private btm(SettingsLeagueDetailActivity settingsLeagueDetailActivity) {
        this.a = settingsLeagueDetailActivity;
    }

    public /* synthetic */ btm(SettingsLeagueDetailActivity settingsLeagueDetailActivity, btj btjVar) {
        this(settingsLeagueDetailActivity);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            if (compoundButton.equals(this.a.k)) {
                this.a.l.setChecked(false);
                this.a.m.setChecked(false);
            } else if (compoundButton.equals(this.a.l)) {
                this.a.k.setChecked(false);
                this.a.m.setChecked(false);
            } else if (compoundButton.equals(this.a.m)) {
                this.a.k.setChecked(false);
                this.a.l.setChecked(false);
            }
            this.a.n = (App.ALERT_LEVEL) compoundButton.getTag();
            this.a.p.a(this.a.n);
            this.a.aF();
        }
    }
}
