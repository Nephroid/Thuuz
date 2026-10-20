package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.CompoundButton;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class brv implements DialogInterface.OnClickListener {
    final /* synthetic */ CompoundButton a;
    final /* synthetic */ AlertDialog b;
    final /* synthetic */ brt c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public brv(brt brtVar, CompoundButton compoundButton, AlertDialog alertDialog) {
        this.c = brtVar;
        this.a = compoundButton;
        this.b = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.a.setOnCheckedChangeListener(null);
            this.a.setChecked(true);
            this.a.setOnCheckedChangeListener(new brt(this.c.a, null));
            this.b.dismiss();
        } catch (Throwable th) {
            this.c.a.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
