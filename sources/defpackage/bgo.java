package defpackage;

import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity;

/* loaded from: classes.dex */
public class bgo {
    final boolean a;
    public final bpg b;
    final /* synthetic */ SettingsFantasyTeamsActivity c;

    public bgo(SettingsFantasyTeamsActivity settingsFantasyTeamsActivity, boolean z, bpg bpgVar) {
        this.c = settingsFantasyTeamsActivity;
        this.a = z;
        this.b = bpgVar;
    }

    public boolean a() {
        return this.b != null && (this.b.a() == -1 || this.b.a() == -2);
    }
}
