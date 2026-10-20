package defpackage;

import android.support.v4.view.ViewPager;
import com.thuuz.gtv.activity.fantasy.FantasyActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bej extends ViewPager.SimpleOnPageChangeListener {
    final /* synthetic */ FantasyActivity a;

    public bej(FantasyActivity fantasyActivity) {
        this.a = fantasyActivity;
    }

    @Override // android.support.v4.view.ViewPager.SimpleOnPageChangeListener, android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        bek bekVar;
        try {
            bekVar = this.a.p;
            int a = bekVar.a();
            this.a.e().info("selected page: " + i);
            this.a.j().p(a);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
