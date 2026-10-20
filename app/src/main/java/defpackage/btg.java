package defpackage;

import android.widget.CompoundButton;
import android.widget.ListView;
import com.thuuz.gtv.settings.SettingsFriendAlertsActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class btg implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SettingsFriendAlertsActivity a;

    private btg(SettingsFriendAlertsActivity settingsFriendAlertsActivity) {
        this.a = settingsFriendAlertsActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        ListView listView;
        List list;
        try {
            boolean bF = this.a.j().bF();
            listView = this.a.f;
            listView.getPositionForView(compoundButton);
            list = this.a.g;
            switch ((SettingsFriendAlertsActivity.FRIEND_ALERT_INDEX) list.get(r0)) {
                case FRIEND_ALERT_INDEX_THUUZ_IT:
                    this.a.j().g(z);
                    break;
                case FRIEND_ALERT_INDEX_FRIEND_FOLLOWS:
                    this.a.j().h(z);
                    break;
            }
            this.a.j().bm();
            this.a.g(bF);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
