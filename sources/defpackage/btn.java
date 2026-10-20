package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.settings.SettingsLocalTeamsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class btn extends BroadcastReceiver {
    final /* synthetic */ SettingsLocalTeamsActivity a;

    public btn(SettingsLocalTeamsActivity settingsLocalTeamsActivity) {
        this.a = settingsLocalTeamsActivity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            this.a.e().info("Got gps request reply, refreshing");
            new brf(null, this.a).execute(new Void[0]);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
