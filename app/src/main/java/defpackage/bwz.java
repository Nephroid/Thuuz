package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.settings.loc.SettingsLocationsActivity;

/* loaded from: classes.dex */
public class bwz implements AdapterView.OnItemClickListener {
    final /* synthetic */ SettingsLocationsActivity a;

    private bwz(SettingsLocationsActivity settingsLocationsActivity) {
        this.a = settingsLocationsActivity;
    }

    public /* synthetic */ bwz(SettingsLocationsActivity settingsLocationsActivity, bww bwwVar) {
        this(settingsLocationsActivity);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.f(i);
    }
}
