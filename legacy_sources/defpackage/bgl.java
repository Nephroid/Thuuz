package defpackage;

import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bgl implements View.OnClickListener {
    final /* synthetic */ SettingsFantasyTeamsActivity a;

    private bgl(SettingsFantasyTeamsActivity settingsFantasyTeamsActivity) {
        this.a = settingsFantasyTeamsActivity;
    }

    public /* synthetic */ bgl(SettingsFantasyTeamsActivity settingsFantasyTeamsActivity, bgf bgfVar) {
        this(settingsFantasyTeamsActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ListView listView;
        List list;
        try {
            listView = this.a.i;
            int positionForView = listView.getPositionForView(view);
            if (positionForView != -1 && positionForView >= 0) {
                list = this.a.k;
                if (positionForView <= list.size()) {
                    this.a.e(positionForView);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
