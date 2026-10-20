package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import com.thuuz.gtv.settings.SettingsRegisterActivity;

/* loaded from: classes.dex */
public class buj implements TextWatcher {
    final /* synthetic */ SettingsRegisterActivity a;
    private final Button b;

    public buj(SettingsRegisterActivity settingsRegisterActivity, Button button) {
        this.a = settingsRegisterActivity;
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
