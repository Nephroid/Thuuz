package defpackage;

import android.view.View;
import com.thuuz.gtv.settings.SettingsTwitterOauthActivity;

/* loaded from: classes.dex */
public class bwd implements View.OnClickListener {
    final /* synthetic */ SettingsTwitterOauthActivity a;

    private bwd(SettingsTwitterOauthActivity settingsTwitterOauthActivity) {
        this.a = settingsTwitterOauthActivity;
    }

    public /* synthetic */ bwd(SettingsTwitterOauthActivity settingsTwitterOauthActivity, bwa bwaVar) {
        this(settingsTwitterOauthActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        new bwe(this).execute(new Void[0]);
    }
}
