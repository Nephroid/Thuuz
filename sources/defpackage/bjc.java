package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.game_page.AbstractTvFragment;

/* loaded from: classes.dex */
class bjc implements bqz {
    final /* synthetic */ bja a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bjc(bja bjaVar) {
        this.a = bjaVar;
    }

    @Override // defpackage.bqz
    public void a() {
        AbstractActivity access$800 = AbstractTvFragment.access$800(this.a.a);
        if (access$800 != null) {
            this.a.a(access$800);
        }
    }
}
