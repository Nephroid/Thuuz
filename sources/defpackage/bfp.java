package defpackage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import com.thuuz.gtv.activity.fantasy.FantasyTeamActivity;
import com.thuuz.gtv.activity.fantasy.FantasyTeamWebFragment;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bfp extends FragmentPagerAdapter {
    final /* synthetic */ FantasyTeamActivity a;
    private FantasyTeamWebFragment b;
    private FantasyTeamWebFragment c;
    private FantasyTeamWebFragment d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bfp(FantasyTeamActivity fantasyTeamActivity, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.a = fantasyTeamActivity;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter, android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        this.a.e().info("instantiating " + i);
        bbf bbfVar = (bbf) super.instantiateItem(viewGroup, i);
        try {
            switch (i) {
                case 0:
                    this.b = (FantasyTeamWebFragment) bbfVar;
                    break;
                case 1:
                    this.c = (FantasyTeamWebFragment) bbfVar;
                    break;
                case 2:
                    this.d = (FantasyTeamWebFragment) bbfVar;
                    break;
            }
            if (this.a.F() != null) {
                bbfVar.onServiceConnected();
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
        return bbfVar;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        int i2;
        int i3;
        int i4;
        switch (i) {
            case 0:
                FantasyTeamWebFragment.PageType pageType = FantasyTeamWebFragment.PageType.PAGE_TYPE_LEAGUE;
                i4 = this.a.f;
                return new FantasyTeamWebFragment(pageType, i4);
            case 1:
                FantasyTeamWebFragment.PageType pageType2 = FantasyTeamWebFragment.PageType.PAGE_TYPE_PLAYER;
                i3 = this.a.f;
                return new FantasyTeamWebFragment(pageType2, i3);
            case 2:
                FantasyTeamWebFragment.PageType pageType3 = FantasyTeamWebFragment.PageType.PAGE_TYPE_TALK;
                i2 = this.a.f;
                return new FantasyTeamWebFragment(pageType3, i2);
            default:
                this.a.e().severe("unexpected tab call for: " + i);
                return null;
        }
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return FantasyTeamActivity.FANT_TABS.values().length;
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        switch (i) {
            case 0:
                return "LEAGUE";
            case 1:
                return "PLAYERS";
            case 2:
                return "SMACK TALK";
            default:
                return "???";
        }
    }

    public void a() {
        ViewPager viewPager;
        viewPager = this.a.h;
        Integer valueOf = Integer.valueOf(viewPager.getCurrentItem());
        this.a.e().info("fantasy team page calling frag refresh: " + valueOf);
        bbf b = b();
        if (b != null) {
            b.doRefresh(false);
        } else {
            this.a.e().info("no frag refresh--not found: " + valueOf);
        }
    }

    public bbf b() {
        ViewPager viewPager;
        viewPager = this.a.h;
        return a(Integer.valueOf(viewPager.getCurrentItem()));
    }

    private bbf a(Integer num) {
        switch (num.intValue()) {
            case 0:
                return this.b;
            case 1:
                return this.c;
            case 2:
                return this.d;
            default:
                this.a.e().severe("unknown fant team index: " + num);
                return null;
        }
    }

    public void c() {
        if (this.b != null) {
            this.b.hasRefreshedOnce(false);
        }
        if (this.c != null) {
            this.c.hasRefreshedOnce(false);
        }
        if (this.d != null) {
            this.d.hasRefreshedOnce(false);
        }
    }
}
