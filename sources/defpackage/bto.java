package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.thuuz.gtv.settings.SettingsLocalTeamsActivity;

/* loaded from: classes.dex */
public class bto implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ SettingsLocalTeamsActivity b;

    public bto(SettingsLocalTeamsActivity settingsLocalTeamsActivity, AlertDialog alertDialog) {
        this.b = settingsLocalTeamsActivity;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.dismiss();
        this.b.finish();
    }
}
