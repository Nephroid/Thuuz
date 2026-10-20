package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.thuuz.gtv.settings.SettingsLoginActivity;

/* loaded from: classes.dex */
public class btv implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ SettingsLoginActivity b;

    public btv(SettingsLoginActivity settingsLoginActivity, AlertDialog alertDialog) {
        this.b = settingsLoginActivity;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.h(false);
        this.a.dismiss();
    }
}
