package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class cae implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ boolean b;
    final /* synthetic */ AbstractActivity c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cae(AlertDialog alertDialog, boolean z, AbstractActivity abstractActivity) {
        this.a = alertDialog;
        this.b = z;
        this.c = abstractActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.a.dismiss();
            if (this.b) {
                this.c.finish();
            }
        } catch (Throwable th) {
            this.c.e().log(Level.SEVERE, "Problem with alert: ", th);
        }
    }
}
