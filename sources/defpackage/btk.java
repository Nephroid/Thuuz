package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.settings.SettingsLeagueDetailActivity;

/* loaded from: classes.dex */
public class btk implements AdapterView.OnItemClickListener {
    final /* synthetic */ SettingsLeagueDetailActivity a;

    public btk(SettingsLeagueDetailActivity settingsLeagueDetailActivity) {
        this.a = settingsLeagueDetailActivity;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.finish();
    }
}
