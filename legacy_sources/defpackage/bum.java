package defpackage;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.SettingsRegisterActivity;

/* loaded from: classes.dex */
public class bum implements View.OnClickListener {
    final /* synthetic */ SettingsRegisterActivity a;

    private bum(SettingsRegisterActivity settingsRegisterActivity) {
        this.a = settingsRegisterActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        EditText editText;
        EditText editText2;
        editText = this.a.f;
        Editable text = editText.getText();
        editText2 = this.a.g;
        Editable text2 = editText2.getText();
        if (text2 != null && text2.toString().trim().length() < 6) {
            cad.b(this.a, "Invalid Entry", "Please enter a password of at least 6 characters in length");
            return;
        }
        if (text != null && !AbstractActivity.b.matcher(text.toString().trim()).matches()) {
            cad.b(this.a, "Invalid Entry", "Please enter a valid email address");
        } else if (this.a.F() == null) {
            this.a.e().severe("cannot get service!");
        } else {
            new buk(this.a).execute(new Void[0]);
        }
    }
}
