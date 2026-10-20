package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.settings.loc.SettingsEditLocationActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bwv extends BroadcastReceiver {
    final /* synthetic */ SettingsEditLocationActivity a;

    private bwv(SettingsEditLocationActivity settingsEditLocationActivity) {
        this.a = settingsEditLocationActivity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        TVProviderFragment tVProviderFragment;
        try {
            this.a.e().info("setting zip from zipset broadcast");
            tVProviderFragment = this.a.j;
            tVProviderFragment.setZip(this.a.j().bp());
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
