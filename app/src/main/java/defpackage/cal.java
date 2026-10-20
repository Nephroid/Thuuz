package defpackage;

import android.widget.ListView;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class cal implements Runnable {
    final /* synthetic */ AbstractActivity a;
    final /* synthetic */ ListView b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cal(AbstractActivity abstractActivity, ListView listView) {
        this.a = abstractActivity;
        this.b = listView;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            cak.b(this.a, this.b, 0);
        } catch (Throwable th) {
            bzy.getStaticLogger(cak.class).log(Level.SEVERE, "problem: ", th);
        }
    }
}
