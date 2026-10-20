package defpackage;

import android.content.Intent;
import android.view.View;
import com.thuuz.gtv.settings.SettingsEditAccountActivity;
import com.thuuz.gtv.settings.SettingsMaintAccountActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bue implements View.OnClickListener {
    final /* synthetic */ SettingsMaintAccountActivity a;

    private bue(SettingsMaintAccountActivity settingsMaintAccountActivity) {
        this.a = settingsMaintAccountActivity;
    }

    public /* synthetic */ bue(SettingsMaintAccountActivity settingsMaintAccountActivity, bud budVar) {
        this(settingsMaintAccountActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            this.a.startActivityForResult(new Intent(this.a.k(), (Class<?>) SettingsEditAccountActivity.class), 5290);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
