package defpackage;

import android.content.Intent;
import android.view.View;
import com.thuuz.gtv.settings.loc.SettingsEditLocationActivity;
import com.thuuz.gtv.settings.loc.SettingsLocationsActivity;

/* loaded from: classes.dex */
public class bwy implements View.OnClickListener {
    final /* synthetic */ SettingsLocationsActivity a;

    private bwy(SettingsLocationsActivity settingsLocationsActivity) {
        this.a = settingsLocationsActivity;
    }

    public /* synthetic */ bwy(SettingsLocationsActivity settingsLocationsActivity, bww bwwVar) {
        this(settingsLocationsActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.startActivityForResult(new Intent(this.a.k(), (Class<?>) SettingsEditLocationActivity.class), 291);
    }
}
