package defpackage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import com.thuuz.gtv.settings.SettingsTeamDetailActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bvn implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ SettingsTeamDetailActivity b;

    public bvn(SettingsTeamDetailActivity settingsTeamDetailActivity, AlertDialog alertDialog) {
        this.b = settingsTeamDetailActivity;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.a.dismiss();
            this.b.a((Dialog) null);
            this.b.finish();
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
