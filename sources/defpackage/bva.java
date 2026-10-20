package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.settings.SettingsTeamDetailActivity;

/* loaded from: classes.dex */
public class bva implements AdapterView.OnItemClickListener {
    final /* synthetic */ SettingsTeamDetailActivity a;

    public bva(SettingsTeamDetailActivity settingsTeamDetailActivity) {
        this.a = settingsTeamDetailActivity;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.finish();
    }
}
