package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bdr implements Runnable {
    final /* synthetic */ AbstractActivity a;

    public bdr(AbstractActivity abstractActivity) {
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
                view3.setVisibility(0);
            } else {
                this.a.e().info("cannot show spinner");
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
