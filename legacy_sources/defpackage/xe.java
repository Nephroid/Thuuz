package defpackage;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class xe implements DialogInterface.OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ xd c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public xe(xd xdVar, String str, String str2) {
        this.c = xdVar;
        this.a = str;
        this.b = str2;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Context context;
        context = this.c.c;
        try {
            ((DownloadManager) context.getSystemService("download")).enqueue(this.c.a(this.a, this.b));
        } catch (IllegalStateException e) {
            adn.c("Could not store picture.");
        }
    }
}
