package defpackage;

import android.support.v4.view.ViewPager;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bje extends ViewPager.SimpleOnPageChangeListener {
    final /* synthetic */ GamePageActivity a;

    public bje(GamePageActivity gamePageActivity) {
        this.a = gamePageActivity;
    }

    @Override // android.support.v4.view.ViewPager.SimpleOnPageChangeListener, android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        bji bjiVar;
        try {
            bjiVar = this.a.n;
            bbf a = bjiVar.a(false);
            this.a.e().info("selected tab: " + i);
            this.a.a(a);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
