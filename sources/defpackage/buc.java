package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import com.thuuz.gtv.settings.SettingsLoginActivity;

/* loaded from: classes.dex */
public class buc implements TextWatcher {
    final /* synthetic */ SettingsLoginActivity a;

    private buc(SettingsLoginActivity settingsLoginActivity) {
        this.a = settingsLoginActivity;
    }

    public /* synthetic */ buc(SettingsLoginActivity settingsLoginActivity, bts btsVar) {
        this(settingsLoginActivity);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        Button button;
        boolean aL;
        button = this.a.i;
        aL = this.a.aL();
        button.setEnabled(aL);
    }
}
