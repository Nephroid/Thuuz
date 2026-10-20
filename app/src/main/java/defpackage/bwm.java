package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;

/* loaded from: classes.dex */
class bwm implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ bwl b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bwm(bwl bwlVar, AlertDialog alertDialog) {
        this.b = bwlVar;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.dismiss();
    }
}
