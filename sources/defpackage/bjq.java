package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.game_page.GamePageTvFragment;

/* loaded from: classes.dex */
public class bjq implements bjx {
    final /* synthetic */ GamePageTvFragment a;

    public bjq(GamePageTvFragment gamePageTvFragment) {
        this.a = gamePageTvFragment;
    }

    @Override // defpackage.bjx
    public void a(bpa bpaVar, View view) {
        this.a.handleChangeChannel(bpaVar, view);
    }
}
