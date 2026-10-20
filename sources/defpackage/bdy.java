package defpackage;

import android.support.v4.view.ViewPager;
import com.thuuz.gtv.activity.FeedActivity;
import com.thuuz.gtv.activity.feed.FeedFragment;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bdy extends ViewPager.SimpleOnPageChangeListener {
    final /* synthetic */ FeedActivity a;

    public bdy(FeedActivity feedActivity) {
        this.a = feedActivity;
    }

    @Override // android.support.v4.view.ViewPager.SimpleOnPageChangeListener, android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        try {
            this.a.j().k(i);
            this.a.e().info("selected page: " + i);
            FeedActivity.FRAGS a = FeedActivity.FRAGS.a(i);
            if (a != null) {
                FeedFragment Y = this.a.Y();
                if (Y != null) {
                    Y.doRefresh(false);
                }
                this.a.e().info("feed view tracking: " + a.b());
                this.a.j().k(a.b());
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
