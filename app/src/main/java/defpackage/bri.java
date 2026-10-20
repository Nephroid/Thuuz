package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.thuuz.gtv.settings.SettingsAccountActivity;

/* loaded from: classes.dex */
public class bri implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ SettingsAccountActivity b;

    public bri(SettingsAccountActivity settingsAccountActivity, AlertDialog alertDialog) {
        this.b = settingsAccountActivity;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.dismiss();
    }
}
