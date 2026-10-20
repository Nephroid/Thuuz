package defpackage;

import android.view.View;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.game_page.GamePageGameTabFragment;

/* loaded from: classes.dex */
public class bjo implements View.OnClickListener {
    final /* synthetic */ GamePageGameTabFragment a;

    public bjo(GamePageGameTabFragment gamePageGameTabFragment) {
        this.a = gamePageGameTabFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        IService service;
        IService service2;
        if (this.a.getFragsActivity().aJ()) {
            service = this.a.getService();
            if (service != null) {
                service2 = this.a.getService();
                service2.h(this.a.gameId);
            }
        }
    }
}
