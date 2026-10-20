package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import com.thuuz.gtv.settings.SettingsSocialRegisterActivity;

/* loaded from: classes.dex */
public class bup implements TextWatcher {
    final /* synthetic */ SettingsSocialRegisterActivity a;
    private final Button b;

    public bup(SettingsSocialRegisterActivity settingsSocialRegisterActivity, Button button) {
        this.a = settingsSocialRegisterActivity;
        this.b = button;
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        boolean aF;
        Button button = this.b;
        aF = this.a.aF();
        button.setEnabled(aF);
    }
}
