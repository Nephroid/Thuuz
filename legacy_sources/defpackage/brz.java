package defpackage;

import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.settings.SettingsAlertsActivity;
import java.util.List;

/* loaded from: classes.dex */
public class brz implements Runnable {
    final /* synthetic */ Boolean a;
    final /* synthetic */ SettingsAlertsActivity b;

    public brz(SettingsAlertsActivity settingsAlertsActivity, Boolean bool) {
        this.b = settingsAlertsActivity;
        this.a = bool;
    }

    @Override // java.lang.Runnable
    public void run() {
        SettingsAlertsActivity settingsAlertsActivity;
        List list;
        ListView listView;
        bsg bsgVar;
        SettingsAlertsActivity settingsAlertsActivity2;
        settingsAlertsActivity = this.b.k;
        if (settingsAlertsActivity.d()) {
            if (this.b.f != null) {
                this.b.f.dismiss();
            }
            if (this.a.booleanValue()) {
                settingsAlertsActivity2 = this.b.k;
                cad.b(settingsAlertsActivity2, "Registration ID Updated", "Registration ID: " + this.b.j().aT());
            }
            list = this.b.h;
            int size = list.size() - 1;
            listView = this.b.g;
            View childAt = listView.getChildAt(size);
            if (childAt != null && (bsgVar = (bsg) childAt.getTag()) != null) {
                bsgVar.b.setText("Registration ID: " + this.b.j().aT());
            }
        }
    }
}
