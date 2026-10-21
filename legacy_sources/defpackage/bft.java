package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.fantasy.FantasyTeamWebFragment;

/* loaded from: classes.dex */
public class bft implements View.OnLongClickListener {
    final /* synthetic */ bfw a;
    final /* synthetic */ FantasyTeamWebFragment b;

    public bft(FantasyTeamWebFragment fantasyTeamWebFragment, bfw bfwVar) {
        this.b = fantasyTeamWebFragment;
        this.a = bfwVar;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.a.onClick(view);
        return true;
    }
}
