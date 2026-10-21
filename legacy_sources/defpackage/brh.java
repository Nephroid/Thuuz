package defpackage;

import android.view.View;
import com.thuuz.gtv.settings.SettingsAccountActivity;

/* loaded from: classes.dex */
public class brh implements View.OnClickListener {
    final /* synthetic */ SettingsAccountActivity a;

    public brh(SettingsAccountActivity settingsAccountActivity) {
        this.a = settingsAccountActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.v();
        this.a.e().info("launch to signin page");
    }
}
