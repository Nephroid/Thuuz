package defpackage;

import android.view.View;
import com.thuuz.gtv.settings.SettingsLoginActivity;

/* loaded from: classes.dex */
public class bts implements View.OnClickListener {
    final /* synthetic */ SettingsLoginActivity a;

    public bts(SettingsLoginActivity settingsLoginActivity) {
        this.a = settingsLoginActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.aK();
    }
}
