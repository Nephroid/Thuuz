package defpackage;

import android.widget.CompoundButton;
import com.thuuz.gtv.settings.SettingsTeamDetailActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bvr implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SettingsTeamDetailActivity a;

    public bvr(SettingsTeamDetailActivity settingsTeamDetailActivity) {
        this.a = settingsTeamDetailActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        try {
            this.a.D.c(z);
            this.a.aF();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
