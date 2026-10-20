package defpackage;

import android.content.Intent;
import android.view.View;
import com.thuuz.gtv.settings.SettingsAccountActivity;
import com.thuuz.gtv.settings.SettingsRegisterActivity;

/* loaded from: classes.dex */
public class brg implements View.OnClickListener {
    final /* synthetic */ SettingsAccountActivity a;

    public brg(SettingsAccountActivity settingsAccountActivity) {
        this.a = settingsAccountActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.startActivityForResult(new Intent(this.a.k(), (Class<?>) SettingsRegisterActivity.class), 571);
        this.a.e().info("launch to reg page");
    }
}
