package defpackage;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class bvi implements DialogInterface.OnClickListener {
    final /* synthetic */ bve a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bvi(bve bveVar) {
        this.a = bveVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
