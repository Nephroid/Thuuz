package defpackage;

import android.widget.CompoundButton;
import com.thuuz.gtv.settings.SettingsTeamDetailActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bvb implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SettingsTeamDetailActivity a;

    public bvb(SettingsTeamDetailActivity settingsTeamDetailActivity) {
        this.a = settingsTeamDetailActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        try {
            this.a.D.d(z);
            this.a.aF();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
