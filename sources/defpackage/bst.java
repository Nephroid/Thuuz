package defpackage;

import android.text.Editable;
import android.view.View;
import com.thuuz.gtv.settings.SettingsEditAccountActivity;

/* loaded from: classes.dex */
public class bst implements View.OnClickListener {
    final /* synthetic */ SettingsEditAccountActivity a;

    private bst(SettingsEditAccountActivity settingsEditAccountActivity) {
        this.a = settingsEditAccountActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Editable text = SettingsEditAccountActivity.a(this.a).getText();
        Editable text2 = SettingsEditAccountActivity.b(this.a).getText();
        if (text != null && text2 != null) {
            a(SettingsEditAccountActivity.a(this.a).getText().toString().trim(), SettingsEditAccountActivity.b(this.a).getText().toString().trim());
        }
    }

    private void a(String str, String str2) {
        new bsu(this, str, str2).execute(new Void[0]);
    }
}
