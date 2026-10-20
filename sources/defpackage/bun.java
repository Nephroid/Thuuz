package defpackage;

import android.widget.CompoundButton;
import android.widget.EditText;
import com.thuuz.gtv.settings.SettingsSocialRegisterActivity;

/* loaded from: classes.dex */
public class bun implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SettingsSocialRegisterActivity a;

    public bun(SettingsSocialRegisterActivity settingsSocialRegisterActivity) {
        this.a = settingsSocialRegisterActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        EditText editText;
        SettingsSocialRegisterActivity settingsSocialRegisterActivity = this.a;
        editText = this.a.j;
        settingsSocialRegisterActivity.a(editText, z);
    }
}
