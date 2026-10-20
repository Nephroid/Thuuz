package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.thuuz.gtv.activity.sports.GuideActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bne implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ GuideActivity b;

    public bne(GuideActivity guideActivity, AlertDialog alertDialog) {
        this.b = guideActivity;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.a.dismiss();
            this.b.u();
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
