package defpackage;

import com.thuuz.gtv.activity.fantasy.FantasyTeamActivity;

/* loaded from: classes.dex */
public class bfo implements Runnable {
    final /* synthetic */ FantasyTeamActivity a;

    public bfo(FantasyTeamActivity fantasyTeamActivity) {
        this.a = fantasyTeamActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.c();
    }
}
