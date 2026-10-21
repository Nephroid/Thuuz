package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.fantasy.FantasyTeamActivity;
import com.thuuz.gtv.activity.fantasy.FantasyTeamWebFragment;
import com.thuuz.gtv.activity.sports.ShareActivity;

/* loaded from: classes.dex */
public class bfw implements View.OnClickListener {
    final /* synthetic */ FantasyTeamWebFragment a;

    private bfw(FantasyTeamWebFragment fantasyTeamWebFragment) {
        this.a = fantasyTeamWebFragment;
    }

    public /* synthetic */ bfw(FantasyTeamWebFragment fantasyTeamWebFragment, bft bftVar) {
        this(fantasyTeamWebFragment);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i;
        FantasyTeamActivity fantasyTeamAct = this.a.getFantasyTeamAct();
        if (fantasyTeamAct != null) {
            ShareActivity.SHARE_TYPE share_type = ShareActivity.SHARE_TYPE.SHARE_TYPE_FANTASY;
            i = this.a.teamId;
            fantasyTeamAct.a(share_type, i, "", "");
        }
    }
}
