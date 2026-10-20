package defpackage;

import android.widget.CompoundButton;
// [removed legacy tracker import]
import com.thuuz.gtv.settings.SettingsAlertsActivity;
import java.util.HashMap;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bse implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SettingsAlertsActivity a;

    private bse(SettingsAlertsActivity settingsAlertsActivity) {
        this.a = settingsAlertsActivity;
    }

    public /* synthetic */ bse(SettingsAlertsActivity settingsAlertsActivity, bry bryVar) {
        this(settingsAlertsActivity);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        bsd bsdVar;
        try {
            this.a.e().info(z ? "Enable alerts" : "Disable global alerts!");
            this.a.j().i(z);
            this.a.j().bm();
            bsdVar = this.a.j;
            bsdVar.notifyDataSetChanged();
            String str = "alert:off";
            if (z) {
                str = "alert:on";
            }
            try {
                HashMap hashMap = new HashMap();
                this.a.j().getClass();
                String str2 = "notifications_off";
                if (z) {
                    this.a.j().getClass();
                    str2 = "notifications_on";
                }
                AppsFlyerLib.a(this.a.getApplicationContext(), str2, hashMap);
            } catch (Throwable th) {
                this.a.e().log(Level.SEVERE, "problem with AppsFlyer event: ", th);
            }
            this.a.j().a("alert", "set", str, 0L);
        } catch (Throwable th2) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th2);
        }
    }
}
