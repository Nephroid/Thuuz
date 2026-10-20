package defpackage;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class bkh implements DialogInterface.OnClickListener {
    final /* synthetic */ bkg a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bkh(bkg bkgVar) {
        this.a = bkgVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
