package defpackage;

import android.view.View;
import com.thuuz.gtv.settings.SettingsTeamSelectActivity;

/* loaded from: classes.dex */
public class bvv implements View.OnClickListener {
    final /* synthetic */ SettingsTeamSelectActivity a;

    public bvv(SettingsTeamSelectActivity settingsTeamSelectActivity) {
        this.a = settingsTeamSelectActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.finish();
    }
}
