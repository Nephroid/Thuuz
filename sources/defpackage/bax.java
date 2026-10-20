package defpackage;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class bax implements DialogInterface.OnDismissListener {
    final /* synthetic */ baw a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bax(baw bawVar) {
        this.a = bawVar;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.a.a = false;
    }
}
