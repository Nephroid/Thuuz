package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.logging.Level;

/* loaded from: classes.dex */
class blw implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ blv b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public blw(blv blvVar, AlertDialog alertDialog) {
        this.b = blvVar;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.a.dismiss();
            this.b.b.aF();
        } catch (Throwable th) {
            this.b.b.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
