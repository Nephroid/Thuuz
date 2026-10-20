package defpackage;

import android.content.DialogInterface;
import java.util.logging.Level;

/* loaded from: classes.dex */
class bly implements DialogInterface.OnCancelListener {
    final /* synthetic */ blv a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bly(blv blvVar) {
        this.a = blvVar;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        try {
            if (!this.a.b.isFinishing()) {
                this.a.b.finish();
            }
        } catch (Throwable th) {
            this.a.b.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
