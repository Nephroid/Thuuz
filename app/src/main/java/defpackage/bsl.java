package defpackage;

import android.widget.CompoundButton;
import android.widget.EditText;
import com.thuuz.gtv.settings.SettingsChangePassActivity;

/* loaded from: classes.dex */
public class bsl implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SettingsChangePassActivity a;

    public bsl(SettingsChangePassActivity settingsChangePassActivity) {
        this.a = settingsChangePassActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        EditText editText;
        EditText editText2;
        EditText editText3;
        SettingsChangePassActivity settingsChangePassActivity = this.a;
        editText = this.a.f;
        settingsChangePassActivity.a(editText, z);
        SettingsChangePassActivity settingsChangePassActivity2 = this.a;
        editText2 = this.a.g;
        settingsChangePassActivity2.a(editText2, z);
        SettingsChangePassActivity settingsChangePassActivity3 = this.a;
        editText3 = this.a.h;
        settingsChangePassActivity3.a(editText3, z);
    }
}
