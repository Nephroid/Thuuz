package defpackage;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.fantasy.FantasyCompListFrag;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class beo implements View.OnKeyListener {
    final /* synthetic */ FantasyCompListFrag a;

    private beo(FantasyCompListFrag fantasyCompListFrag) {
        this.a = fantasyCompListFrag;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Logger logger;
        IService service;
        ListView listView;
        List list;
        try {
            if (keyEvent.getAction() != 1 || i != 22) {
                return false;
            }
            service = this.a.getService();
            if (service == null) {
                return false;
            }
            listView = this.a.mListView;
            int positionForView = listView.getPositionForView(view);
            if (positionForView == -1 || positionForView < 0) {
                return false;
            }
            list = this.a.mTeamItems;
            if (positionForView > list.size()) {
                return false;
            }
            this.a.handleItemSelection(positionForView);
            return true;
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "Cannot handle: ", th);
            return false;
        }
    }
}
