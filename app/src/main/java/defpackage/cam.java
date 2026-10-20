package defpackage;

import android.widget.ListView;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class cam implements Runnable {
    final /* synthetic */ AbstractActivity a;
    final /* synthetic */ ListView b;
    final /* synthetic */ int c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cam(AbstractActivity abstractActivity, ListView listView, int i) {
        this.a = abstractActivity;
        this.b = listView;
        this.c = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (!this.a.isDestroyed()) {
                cak.b(this.a, this.b, this.c);
            }
        } catch (Throwable th) {
            bzy.getStaticLogger(cak.class).log(Level.SEVERE, "problem: ", th);
        }
    }
}
