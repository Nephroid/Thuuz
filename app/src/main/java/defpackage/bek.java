package defpackage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import com.thuuz.gtv.activity.fantasy.FantasyActivity;
import com.thuuz.gtv.activity.fantasy.FantasyCompListFrag;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bek extends FragmentPagerAdapter {
    final HashMap<Integer, FantasyCompListFrag> a;
    final /* synthetic */ FantasyActivity b;
    private final List<bpe> c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bek(FantasyActivity fantasyActivity, FragmentManager fragmentManager, List<bpe> list) {
        super(fragmentManager);
        this.b = fantasyActivity;
        this.a = new HashMap<>();
        this.c = list;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.c.size();
    }

    @Override // android.support.v4.app.FragmentPagerAdapter, android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        FantasyCompListFrag fantasyCompListFrag = (FantasyCompListFrag) super.instantiateItem(viewGroup, i);
        this.a.put(Integer.valueOf(i), fantasyCompListFrag);
        return fantasyCompListFrag;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        if (i < this.c.size()) {
            return new FantasyCompListFrag(this.c.get(i));
        }
        return null;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter, android.support.v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        this.a.remove(Integer.valueOf(i));
        super.destroyItem(viewGroup, i, obj);
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return i < this.c.size() ? this.c.get(i).b() : "???";
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public long getItemId(int i) {
        if (i >= this.c.size()) {
            return -1L;
        }
        return this.c.get(i).a();
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getItemPosition(Object obj) {
        int indexOf = this.c.indexOf(((FantasyCompListFrag) obj).getCompetition());
        if (indexOf < 0) {
            return -2;
        }
        return indexOf;
    }

    public bbf a(int i) {
        return this.a.get(Integer.valueOf(i));
    }

    public int a() {
        int i;
        Throwable th;
        ViewPager viewPager;
        ViewPager viewPager2;
        bbf a;
        try {
            viewPager = this.b.o;
            i = viewPager.getCurrentItem();
            if (i >= 0) {
                try {
                    viewPager2 = this.b.o;
                    if (i < viewPager2.getChildCount() && (a = a(i)) != null) {
                        a.doRefresh(false);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    this.b.e().log(Level.SEVERE, "Cannot handle: ", th);
                    return i;
                }
            }
        } catch (Throwable th3) {
            i = 0;
            th = th3;
        }
        return i;
    }
}
