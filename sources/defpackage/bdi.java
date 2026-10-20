package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bdi implements Runnable {
    final /* synthetic */ AbstractActivity a;

    public bdi(AbstractActivity abstractActivity) {
        this.a = abstractActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            String str = "";
            View currentFocus = this.a.getCurrentFocus();
            if (currentFocus != null) {
                str = currentFocus.getClass().getSimpleName();
            }
            this.a.e().info("100ms after handledFocusKey, focus is: " + str);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
