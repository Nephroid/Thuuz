package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.settings.SettingsTwitterOauthActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bwc extends BroadcastReceiver {
    final /* synthetic */ SettingsTwitterOauthActivity a;

    private bwc(SettingsTwitterOauthActivity settingsTwitterOauthActivity) {
        this.a = settingsTwitterOauthActivity;
    }

    public /* synthetic */ bwc(SettingsTwitterOauthActivity settingsTwitterOauthActivity, bwa bwaVar) {
        this(settingsTwitterOauthActivity);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean z = false;
        try {
            String action = intent.getAction();
            if (action != null && action.equalsIgnoreCase("TWIT_OAUTH_ACTION")) {
                this.a.e().info("got broadcast twit oauth in SettingsTwitter");
                if (this.a.j().bJ() != null && this.a.j().bK() != null) {
                    z = true;
                }
                if (z) {
                    this.a.setResult(-1);
                } else {
                    if (intent.hasExtra("oauth_verifier") && this.a.d()) {
                        this.a.b("Cannot complete Twitter signin", bbt.pls_try_again_later);
                    }
                    this.a.setResult(0);
                }
                this.a.finish();
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
