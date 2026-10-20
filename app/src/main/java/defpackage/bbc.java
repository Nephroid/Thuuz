package defpackage;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class bbc implements DialogInterface.OnDismissListener {
    final /* synthetic */ bbb a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bbc(bbb bbbVar) {
        this.a = bbbVar;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.a.a = false;
    }
}
