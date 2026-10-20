package defpackage;

import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bdf extends ActionBarDrawerToggle {
    final /* synthetic */ AbstractActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bdf(AbstractActivity abstractActivity, Activity activity, DrawerLayout drawerLayout, int i, int i2, int i3) {
        super(activity, drawerLayout, i, i2, i3);
        this.a = abstractActivity;
    }

    @Override // android.support.v4.app.ActionBarDrawerToggle, android.support.v4.widget.DrawerLayout.DrawerListener
    public void onDrawerClosed(View view) {
        try {
            this.a.d(true);
            this.a.invalidateOptionsMenu();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // android.support.v4.app.ActionBarDrawerToggle, android.support.v4.widget.DrawerLayout.DrawerListener
    public void onDrawerOpened(View view) {
        try {
            this.a.d(false);
            this.a.invalidateOptionsMenu();
            this.a.al();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
