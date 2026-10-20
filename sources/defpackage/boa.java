package defpackage;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.activity.sports.GuideActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class boa implements View.OnKeyListener {
    final /* synthetic */ GuideActivity a;

    public boa(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == 23 || i == 66) {
            try {
                View selectedView = ((ListView) view).getSelectedView();
                if (selectedView != null && selectedView.getTag() != null && (selectedView.getTag() instanceof bmm)) {
                    this.a.a(((bmm) selectedView.getTag()).a);
                    return true;
                }
            } catch (Throwable th) {
                this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
        return false;
    }
}
