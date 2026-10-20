package defpackage;

import android.content.Intent;
import android.view.View;
import com.thuuz.gtv.settings.SettingsChangeEmailActivity;
import com.thuuz.gtv.settings.SettingsEditAccountActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bsq implements View.OnClickListener {
    final /* synthetic */ SettingsEditAccountActivity a;

    private bsq(SettingsEditAccountActivity settingsEditAccountActivity) {
        this.a = settingsEditAccountActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            this.a.startActivityForResult(new Intent(this.a.k(), (Class<?>) SettingsChangeEmailActivity.class), 1015);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
