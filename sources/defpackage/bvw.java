package defpackage;

import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.settings.SettingsTeamSelectActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bvw implements View.OnClickListener {
    final /* synthetic */ SettingsTeamSelectActivity a;

    public bvw(SettingsTeamSelectActivity settingsTeamSelectActivity) {
        this.a = settingsTeamSelectActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ListView listView;
        ListView listView2;
        if (view != null) {
            try {
                listView = this.a.k;
                if (listView != null) {
                    View view2 = (View) view.getTag();
                    if (view2 != null) {
                        view = view2;
                    }
                    listView2 = this.a.k;
                    this.a.e(listView2.getPositionForView(view));
                }
            } catch (Throwable th) {
                this.a.e().log(Level.SEVERE, "Cannot handle team select click; can happen when team list is refreshed during click: ", th);
                this.a.b("Problem finding team", bbt.pls_try_again_later);
            }
        }
    }
}
