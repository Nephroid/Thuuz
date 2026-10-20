package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.fantasy.FantasyCompListFrag;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class ben implements View.OnClickListener {
    final /* synthetic */ FantasyCompListFrag a;

    private ben(FantasyCompListFrag fantasyCompListFrag) {
        this.a = fantasyCompListFrag;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view != null) {
            try {
                int positionForView = FantasyCompListFrag.access$1100(this.a).getPositionForView(view);
                if (positionForView != -1 && positionForView >= 0 && positionForView <= FantasyCompListFrag.access$200(this.a).size()) {
                    FantasyCompListFrag.access$1200(this.a, positionForView);
                }
            } catch (Throwable th) {
                FantasyCompListFrag.access$1300(this.a).log(Level.SEVERE, "Cannot click fantasy comp list: ", th);
            }
        }
    }
}
