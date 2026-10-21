package defpackage;

import com.thuuz.gtv.settings.SettingsAlertsActivity;
import java.util.TimerTask;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bsa extends TimerTask {
    final /* synthetic */ SettingsAlertsActivity a;

    public bsa(SettingsAlertsActivity settingsAlertsActivity) {
        this.a = settingsAlertsActivity;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        SettingsAlertsActivity settingsAlertsActivity;
        try {
            settingsAlertsActivity = this.a.k;
            if (settingsAlertsActivity.d()) {
                this.a.runOnUiThread(new bsb(this));
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
