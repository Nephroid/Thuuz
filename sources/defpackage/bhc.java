package defpackage;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.friends.AbstractFriendFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bhc implements View.OnKeyListener {
    final /* synthetic */ AbstractFriendFragment a;

    public bhc(AbstractFriendFragment abstractFriendFragment) {
        this.a = abstractFriendFragment;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Logger logger;
        int positionForView;
        AbstractActivity fragsActivity;
        if (keyEvent.getAction() == 0) {
            return false;
        }
        if (i == 23 || i == 66) {
            try {
                ListView listView = (ListView) view;
                View selectedView = listView.getSelectedView();
                if (selectedView != null && this.a.friends.size() > (positionForView = listView.getPositionForView(selectedView))) {
                    fragsActivity = this.a.getFragsActivity();
                    if (fragsActivity == null) {
                        return false;
                    }
                    if (fragsActivity.j().e()) {
                        this.a.getButtonClickHandler().onClick(selectedView);
                    } else {
                        fragsActivity.d(this.a.friends.get(positionForView).a());
                    }
                    return true;
                }
            } catch (Throwable th) {
                logger = this.a.getLogger();
                logger.log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
        return false;
    }
}
