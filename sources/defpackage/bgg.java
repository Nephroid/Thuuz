package defpackage;

import android.content.DialogInterface;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity;

/* loaded from: classes.dex */
public class bgg implements DialogInterface.OnClickListener {
    final /* synthetic */ SettingsFantasyTeamsActivity a;

    public bgg(SettingsFantasyTeamsActivity settingsFantasyTeamsActivity) {
        this.a = settingsFantasyTeamsActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
