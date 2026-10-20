package defpackage;

import android.content.DialogInterface;
import com.thuuz.gtv.settings.SettingsLoginActivity;

/* loaded from: classes.dex */
public class btx implements DialogInterface.OnCancelListener {
    final /* synthetic */ SettingsLoginActivity a;

    public btx(SettingsLoginActivity settingsLoginActivity) {
        this.a = settingsLoginActivity;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.a.h(false);
    }
}
