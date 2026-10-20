package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.settings.SettingsLocalTeamsActivity;

/* loaded from: classes.dex */
public class btp implements AdapterView.OnItemClickListener {
    final /* synthetic */ SettingsLocalTeamsActivity a;

    public btp(SettingsLocalTeamsActivity settingsLocalTeamsActivity) {
        this.a = settingsLocalTeamsActivity;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.e(i);
    }
}
