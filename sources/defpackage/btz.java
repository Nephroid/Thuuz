package defpackage;

import android.view.View;
import android.widget.EditText;
import com.thuuz.gtv.settings.SettingsLoginActivity;

/* loaded from: classes.dex */
public class btz implements View.OnClickListener {
    final /* synthetic */ SettingsLoginActivity a;

    private btz(SettingsLoginActivity settingsLoginActivity) {
        this.a = settingsLoginActivity;
    }

    public /* synthetic */ btz(SettingsLoginActivity settingsLoginActivity, bts btsVar) {
        this(settingsLoginActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        EditText editText;
        EditText editText2;
        editText = this.a.g;
        if (editText.getText() != null) {
            editText2 = this.a.g;
            if (editText2.getText().length() < 6) {
                cad.b(this.a, "Password Too Short", "Please enter a password of at least 6 characters");
                return;
            }
        }
        if (this.a.F() == null) {
            this.a.e().severe("cannot get service!");
            if (this.a.d()) {
                this.a.b("Cannot contact service", bbt.pls_try_again_later);
                return;
            }
            return;
        }
        new bua(this).execute(new Void[0]);
    }
}
