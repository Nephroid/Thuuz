package defpackage;

import android.support.v4.view.ViewPager;
import com.thuuz.gtv.activity.friends.FriendsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bih extends ViewPager.SimpleOnPageChangeListener {
    final /* synthetic */ FriendsActivity a;

    public bih(FriendsActivity friendsActivity) {
        this.a = friendsActivity;
    }

    @Override // android.support.v4.view.ViewPager.SimpleOnPageChangeListener, android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        try {
            this.a.invalidateOptionsMenu();
            this.a.j().i(i);
            this.a.e().info("selected page: " + i);
            FriendsActivity.FRAGS a = FriendsActivity.FRAGS.a(i);
            if (a != null) {
                this.a.e().info("tracking " + a.b());
                this.a.j().k(a.b());
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
