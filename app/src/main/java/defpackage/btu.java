package defpackage;

import android.widget.CompoundButton;
import android.widget.EditText;
import com.thuuz.gtv.settings.SettingsLoginActivity;

/* loaded from: classes.dex */
public class btu implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SettingsLoginActivity a;

    public btu(SettingsLoginActivity settingsLoginActivity) {
        this.a = settingsLoginActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        EditText editText;
        SettingsLoginActivity settingsLoginActivity = this.a;
        editText = this.a.g;
        settingsLoginActivity.a(editText, z);
    }
}
