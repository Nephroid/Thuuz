package defpackage;

import android.view.KeyEvent;
import android.view.View;
import com.thuuz.gtv.settings.SettingsLoginActivity;

/* loaded from: classes.dex */
public class btt implements View.OnKeyListener {
    final /* synthetic */ SettingsLoginActivity a;

    public btt(SettingsLoginActivity settingsLoginActivity) {
        this.a = settingsLoginActivity;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || (i != 23 && i != 66)) {
            return false;
        }
        this.a.aK();
        return true;
    }
}
