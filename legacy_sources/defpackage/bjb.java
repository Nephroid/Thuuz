package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;

/* loaded from: classes.dex */
class bjb implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ bja b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bjb(bja bjaVar, AlertDialog alertDialog) {
        this.b = bjaVar;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.dismiss();
    }
}
