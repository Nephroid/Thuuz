package defpackage;

import android.view.KeyEvent;
import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;

/* loaded from: classes.dex */
public class bdq implements View.OnKeyListener {
    final /* synthetic */ AbstractActivity a;

    public bdq(AbstractActivity abstractActivity) {
        this.a = abstractActivity;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if ((21 == i || 22 == i) && this.a.ax()) {
            this.a.aw();
            return false;
        }
        return false;
    }
}
