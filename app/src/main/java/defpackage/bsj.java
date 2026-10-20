package defpackage;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import com.thuuz.gtv.settings.SettingsChangeEmailActivity;

/* loaded from: classes.dex */
public class bsj implements View.OnClickListener {
    final /* synthetic */ SettingsChangeEmailActivity a;

    private bsj(SettingsChangeEmailActivity settingsChangeEmailActivity) {
        this.a = settingsChangeEmailActivity;
    }

    public /* synthetic */ bsj(SettingsChangeEmailActivity settingsChangeEmailActivity, bsh bshVar) {
        this(settingsChangeEmailActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        EditText editText;
        EditText editText2;
        editText = this.a.f;
        Editable text = editText.getText();
        String str = "";
        if (text != null) {
            str = text.toString().trim();
        }
        editText2 = this.a.g;
        Editable text2 = editText2.getText();
        String str2 = "";
        if (text2 != null) {
            str2 = text2.toString().trim();
        }
        new bsk(this, str2, str).execute(new Void[0]);
    }
}
