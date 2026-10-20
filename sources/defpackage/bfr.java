package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.activity.fantasy.FantasyTeamActivity;

/* loaded from: classes.dex */
public class bfr extends BroadcastReceiver {
    final /* synthetic */ FantasyTeamActivity a;

    private bfr(FantasyTeamActivity fantasyTeamActivity) {
        this.a = fantasyTeamActivity;
    }

    public /* synthetic */ bfr(FantasyTeamActivity fantasyTeamActivity, bfn bfnVar) {
        this(fantasyTeamActivity);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.a.e().info("received fantasy sync broadcast");
        this.a.runOnUiThread(new bfs(this));
    }
}
