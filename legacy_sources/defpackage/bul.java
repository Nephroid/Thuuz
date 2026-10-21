package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import com.thuuz.gtv.settings.SettingsLoginActivity;
import com.thuuz.gtv.settings.SettingsRegisterActivity;

/* loaded from: classes.dex */
class bul implements DialogInterface.OnClickListener {
    final /* synthetic */ SettingsRegisterActivity a;
    final /* synthetic */ AlertDialog b;
    final /* synthetic */ buk c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bul(buk bukVar, SettingsRegisterActivity settingsRegisterActivity, AlertDialog alertDialog) {
        this.c = bukVar;
        this.a = settingsRegisterActivity;
        this.b = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        EditText editText;
        Intent intent = new Intent(this.a, (Class<?>) SettingsLoginActivity.class);
        editText = this.a.f;
        intent.putExtra("email", editText.getText().toString().trim());
        this.a.startActivity(intent);
        this.a.finish();
        this.b.dismiss();
    }
}
