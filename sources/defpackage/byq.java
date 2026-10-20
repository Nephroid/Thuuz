package defpackage;

import android.widget.Toast;
import java.util.logging.Level;

/* loaded from: classes.dex */
class byq implements Runnable {
    final /* synthetic */ byp a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public byq(byp bypVar) {
        this.a = bypVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Toast.makeText(this.a.k(), "Cannot Upload Fantasy Preferences\nPlease try again later.", 1).show();
        } catch (Throwable th) {
            this.a.getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
