package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bdn implements View.OnClickListener {
    final /* synthetic */ AbstractActivity a;

    public bdn(AbstractActivity abstractActivity) {
        this.a = abstractActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            this.a.V();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
