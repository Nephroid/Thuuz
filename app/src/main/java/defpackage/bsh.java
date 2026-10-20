package defpackage;

import android.widget.CompoundButton;
import android.widget.EditText;
import com.thuuz.gtv.settings.SettingsChangeEmailActivity;

/* loaded from: classes.dex */
public class bsh implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SettingsChangeEmailActivity a;

    public bsh(SettingsChangeEmailActivity settingsChangeEmailActivity) {
        this.a = settingsChangeEmailActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        EditText editText;
        SettingsChangeEmailActivity settingsChangeEmailActivity = this.a;
        editText = this.a.f;
        settingsChangeEmailActivity.a(editText, z);
    }
}
