package defpackage;

import android.R;
import android.view.View;
import android.view.ViewGroup;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bdh implements Runnable {
    final /* synthetic */ AbstractActivity a;

    public bdh(AbstractActivity abstractActivity) {
        this.a = abstractActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            View childAt = ((ViewGroup) this.a.findViewById(R.id.content)).getChildAt(0);
            if (childAt != null) {
                this.a.A = childAt.hasFocus();
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
