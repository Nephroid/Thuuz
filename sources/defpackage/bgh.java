package defpackage;

import android.content.DialogInterface;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bgh implements DialogInterface.OnClickListener {
    final /* synthetic */ bpg a;
    final /* synthetic */ SettingsFantasyTeamsActivity b;

    public bgh(SettingsFantasyTeamsActivity settingsFantasyTeamsActivity, bpg bpgVar) {
        this.b = settingsFantasyTeamsActivity;
        this.a = bpgVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            new bef(this.a.c(), this.b, this.a.b()).execute(new Void[0]);
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
