package defpackage;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bgm implements View.OnKeyListener {
    final /* synthetic */ SettingsFantasyTeamsActivity a;

    private bgm(SettingsFantasyTeamsActivity settingsFantasyTeamsActivity) {
        this.a = settingsFantasyTeamsActivity;
    }

    public /* synthetic */ bgm(SettingsFantasyTeamsActivity settingsFantasyTeamsActivity, bgf bgfVar) {
        this(settingsFantasyTeamsActivity);
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        ListView listView;
        List list;
        try {
            if (keyEvent.getAction() != 1 || i != 23) {
                return false;
            }
            listView = this.a.i;
            int positionForView = listView.getPositionForView(view);
            if (positionForView != -1 && positionForView >= 0) {
                list = this.a.k;
                if (positionForView <= list.size()) {
                    this.a.e(positionForView);
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
            return false;
        }
    }
}
