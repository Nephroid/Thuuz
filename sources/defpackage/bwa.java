package defpackage;

import android.view.View;
import com.thuuz.gtv.settings.SettingsTwitterOauthActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bwa implements View.OnClickListener {
    final /* synthetic */ SettingsTwitterOauthActivity a;

    public bwa(SettingsTwitterOauthActivity settingsTwitterOauthActivity) {
        this.a = settingsTwitterOauthActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            this.a.e().info("canceling from user click");
            this.a.finish();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem", th);
        }
    }
}
