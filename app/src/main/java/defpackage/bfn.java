package defpackage;

import android.support.v4.view.ViewPager;
import com.thuuz.gtv.activity.fantasy.FantasyTeamActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bfn extends ViewPager.SimpleOnPageChangeListener {
    final /* synthetic */ FantasyTeamActivity a;

    public bfn(FantasyTeamActivity fantasyTeamActivity) {
        this.a = fantasyTeamActivity;
    }

    @Override // android.support.v4.view.ViewPager.SimpleOnPageChangeListener, android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        bfp bfpVar;
        try {
            this.a.j().k(i);
            this.a.e().info("selected page: " + i);
            bfpVar = this.a.g;
            bbf b = bfpVar.b();
            if (b != null) {
                b.doRefresh(false);
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
