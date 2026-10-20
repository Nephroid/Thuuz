package defpackage;

import android.content.Intent;
import android.view.View;
import com.thuuz.gtv.settings.SettingsChangePassActivity;
import com.thuuz.gtv.settings.SettingsEditAccountActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bsr implements View.OnClickListener {
    final /* synthetic */ SettingsEditAccountActivity a;

    private bsr(SettingsEditAccountActivity settingsEditAccountActivity) {
        this.a = settingsEditAccountActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            this.a.startActivityForResult(new Intent(this.a.k(), (Class<?>) SettingsChangePassActivity.class), 5260);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
