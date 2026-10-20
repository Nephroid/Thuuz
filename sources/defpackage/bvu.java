package defpackage;

import android.widget.CompoundButton;
import com.thuuz.gtv.App;
import com.thuuz.gtv.settings.SettingsTeamDetailActivity;

/* loaded from: classes.dex */
public class bvu implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SettingsTeamDetailActivity a;

    private bvu(SettingsTeamDetailActivity settingsTeamDetailActivity) {
        this.a = settingsTeamDetailActivity;
    }

    public /* synthetic */ bvu(SettingsTeamDetailActivity settingsTeamDetailActivity, bva bvaVar) {
        this(settingsTeamDetailActivity);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            if (compoundButton.equals(this.a.q)) {
                this.a.r.setChecked(false);
                this.a.s.setChecked(false);
            } else if (compoundButton.equals(this.a.r)) {
                this.a.q.setChecked(false);
                this.a.s.setChecked(false);
            } else if (compoundButton.equals(this.a.s)) {
                this.a.q.setChecked(false);
                this.a.r.setChecked(false);
            }
            this.a.B = (App.ALERT_LEVEL) compoundButton.getTag();
            this.a.D.a(this.a.B);
            this.a.aF();
        }
    }
}
