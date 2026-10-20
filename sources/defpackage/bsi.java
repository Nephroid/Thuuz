package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import com.thuuz.gtv.settings.SettingsChangeEmailActivity;

/* loaded from: classes.dex */
public class bsi implements TextWatcher {
    final /* synthetic */ SettingsChangeEmailActivity a;
    private final Button b;

    public bsi(SettingsChangeEmailActivity settingsChangeEmailActivity, Button button) {
        this.a = settingsChangeEmailActivity;
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
