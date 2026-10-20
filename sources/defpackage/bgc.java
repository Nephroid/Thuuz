package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamDetailActivity;

/* loaded from: classes.dex */
public class bgc implements AdapterView.OnItemClickListener {
    final /* synthetic */ SettingsFantasyTeamDetailActivity a;

    private bgc(SettingsFantasyTeamDetailActivity settingsFantasyTeamDetailActivity) {
        this.a = settingsFantasyTeamDetailActivity;
    }

    public /* synthetic */ bgc(SettingsFantasyTeamDetailActivity settingsFantasyTeamDetailActivity, bfy bfyVar) {
        this(settingsFantasyTeamDetailActivity);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.e(i);
    }
}
