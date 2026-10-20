package defpackage;

import android.view.View;
import com.thuuz.gtv.settings.SettingsTeamDetailActivity;

/* loaded from: classes.dex */
public class bvk implements View.OnClickListener {
    final /* synthetic */ SettingsTeamDetailActivity a;

    public bvk(SettingsTeamDetailActivity settingsTeamDetailActivity) {
        this.a = settingsTeamDetailActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.p.toggle();
    }
}
