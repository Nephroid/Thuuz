package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import com.thuuz.gtv.settings.SettingsEditAccountActivity;

/* loaded from: classes.dex */
public class bss implements TextWatcher {
    final /* synthetic */ SettingsEditAccountActivity a;
    private final Button b;

    public bss(SettingsEditAccountActivity settingsEditAccountActivity, Button button) {
        this.a = settingsEditAccountActivity;
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
