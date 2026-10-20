package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity;

/* loaded from: classes.dex */
public class bgn implements View.OnLongClickListener {
    final /* synthetic */ SettingsFantasyTeamsActivity a;

    private bgn(SettingsFantasyTeamsActivity settingsFantasyTeamsActivity) {
        this.a = settingsFantasyTeamsActivity;
    }

    public /* synthetic */ bgn(SettingsFantasyTeamsActivity settingsFantasyTeamsActivity, bgf bgfVar) {
        this(settingsFantasyTeamsActivity);
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        new bgl(this.a, null).onClick(view);
        return true;
    }
}
