package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.activity.nobar.OnboardPage6TeamsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class blj extends BroadcastReceiver {
    final /* synthetic */ OnboardPage6TeamsActivity a;

    public blj(OnboardPage6TeamsActivity onboardPage6TeamsActivity) {
        this.a = onboardPage6TeamsActivity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            this.a.e().info("Got gps request reply, refreshing");
            this.a.aF();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
