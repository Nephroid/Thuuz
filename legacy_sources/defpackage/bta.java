package defpackage;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.settings.SettingsFavSportsActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bta implements View.OnKeyListener {
    final /* synthetic */ SettingsFavSportsActivity a;

    private bta(SettingsFavSportsActivity settingsFavSportsActivity) {
        this.a = settingsFavSportsActivity;
    }

    public /* synthetic */ bta(SettingsFavSportsActivity settingsFavSportsActivity, bsv bsvVar) {
        this(settingsFavSportsActivity);
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        ListView listView;
        List list;
        try {
            if (keyEvent.getAction() != 1 || i != 22 || this.a.F() == null) {
                return false;
            }
            listView = this.a.g;
            int positionForView = listView.getPositionForView(view);
            if (positionForView == -1 || positionForView < 0) {
                return false;
            }
            list = this.a.h;
            if (positionForView > list.size()) {
                return false;
            }
            this.a.e(positionForView);
            return true;
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
            return false;
        }
    }
}
