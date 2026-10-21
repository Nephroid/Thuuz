package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.thuuz.gtv.settings.SettingsLoginActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class btw implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ SettingsLoginActivity b;

    public btw(SettingsLoginActivity settingsLoginActivity, AlertDialog alertDialog) {
        this.b = settingsLoginActivity;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        bqg aG;
        try {
            bqe aY = this.b.j().aY();
            aG = this.b.aG();
            aY.a(aG, this.b.k());
            this.a.dismiss();
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "Problem with alert: ", th);
        }
    }
}
