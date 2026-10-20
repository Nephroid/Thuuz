package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bru implements DialogInterface.OnClickListener {
    final /* synthetic */ boolean a;
    final /* synthetic */ brw b;
    final /* synthetic */ AlertDialog c;
    final /* synthetic */ brt d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bru(brt brtVar, boolean z, brw brwVar, AlertDialog alertDialog) {
        this.d = brtVar;
        this.a = z;
        this.b = brwVar;
        this.c = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.d.a(this.a, this.b);
            this.c.dismiss();
        } catch (Throwable th) {
            this.d.a.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
