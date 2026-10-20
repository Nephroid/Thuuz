package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bcx implements Runnable {
    final /* synthetic */ AbstractActivity a;

    public bcx(AbstractActivity abstractActivity) {
        this.a = abstractActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        View view;
        View view2;
        View view3;
        try {
            view = this.a.B;
            if (view == null) {
                this.a.B = this.a.findViewById(bbq.progress);
            }
            view2 = this.a.B;
            if (view2 != null) {
                view3 = this.a.B;
                view3.setVisibility(4);
            } else {
                this.a.e().info("cannot hide spinner");
            }
        } catch (Throwable th) {
            try {
                this.a.e().log(Level.SEVERE, "problem: ", th);
            } catch (Throwable th2) {
                this.a.e().log(Level.SEVERE, "problem: ", th2);
            }
        }
    }
}
