package defpackage;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import com.thuuz.gtv.settings.SettingsChangePassActivity;

/* loaded from: classes.dex */
public class bsn implements View.OnClickListener {
    final /* synthetic */ SettingsChangePassActivity a;

    private bsn(SettingsChangePassActivity settingsChangePassActivity) {
        this.a = settingsChangePassActivity;
    }

    public /* synthetic */ bsn(SettingsChangePassActivity settingsChangePassActivity, bsl bslVar) {
        this(settingsChangePassActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        EditText editText;
        EditText editText2;
        EditText editText3;
        EditText editText4;
        EditText editText5;
        EditText editText6;
        editText = this.a.h;
        Editable text = editText.getText();
        editText2 = this.a.g;
        Editable text2 = editText2.getText();
        editText3 = this.a.f;
        Editable text3 = editText3.getText();
        if (text != null && text2 != null && text3 != null) {
            editText4 = this.a.f;
            String trim = editText4.getText().toString().trim();
            editText5 = this.a.g;
            String trim2 = editText5.getText().toString().trim();
            editText6 = this.a.h;
            if (!trim2.equals(editText6.getText().toString().trim())) {
                this.a.c("Password confirmation doesn't match", "Please try again.");
            } else if (trim2.length() < 6) {
                this.a.c("Password must be at least 6 chars long", "Please try again.");
            } else {
                new bso(this, trim, trim2).execute(new Void[0]);
            }
        }
    }
}
