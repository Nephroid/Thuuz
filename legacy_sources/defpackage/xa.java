package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class xa implements DialogInterface.OnClickListener {
    final /* synthetic */ wz a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public xa(wz wzVar) {
        this.a = wzVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Context context;
        Intent b = this.a.b();
        context = this.a.c;
        context.startActivity(b);
    }
}
