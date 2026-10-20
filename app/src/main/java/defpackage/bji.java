package defpackage;

import android.annotation.SuppressLint;
import android.content.ComponentCallbacks;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import com.thuuz.gtv.activity.game_page.AbstractGamePageFragment;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import com.thuuz.gtv.activity.game_page.GamePageGameTabFragment;
import com.thuuz.gtv.activity.game_page.GamePageGameTabMobileFragment;
import com.thuuz.gtv.activity.game_page.GamePageWebViewFragment;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bji extends FragmentPagerAdapter {

    @SuppressLint({"UseSparseArrays"})
    final HashMap<Integer, bbf> a;
    final /* synthetic */ GamePageActivity b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bji(GamePageActivity gamePageActivity, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.b = gamePageActivity;
        this.a = new HashMap<>();
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        List list;
        list = this.b.p;
        return list.size();
    }

    @Override // android.support.v4.app.FragmentPagerAdapter, android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        bbf bbfVar = (bbf) super.instantiateItem(viewGroup, i);
        if (bbfVar != null) {
            try {
                this.a.put(Integer.valueOf(i), bbfVar);
                if (this.b.F() != null) {
                    bbfVar.onServiceConnected();
                }
            } catch (Throwable th) {
                this.b.e().log(Level.SEVERE, "problem: ", th);
            }
        }
        return bbfVar;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        Throwable th;
        Fragment fragment;
        List list;
        List list2;
        int i2;
        int i3;
        int i4;
        int i5;
        if (i >= 0) {
            try {
                list = this.b.p;
                if (i < list.size()) {
                    list2 = this.b.p;
                    bjk bjkVar = (bjk) list2.get(i);
                    switch (bjkVar.b) {
                        case GAME_MOBILE:
                            int c = bjkVar.b.c();
                            i5 = this.b.f;
                            fragment = new GamePageGameTabMobileFragment(c, i5);
                            break;
                        case GAME_TABLET:
                            int c2 = bjkVar.b.c();
                            i4 = this.b.f;
                            fragment = new GamePageGameTabFragment(c2, i4);
                            break;
                        case PREVIEW:
                        case RECAP:
                        case STATS:
                        case GRAPH:
                            String a = this.b.a(bjkVar.b.c(), bjkVar);
                            int c3 = bjkVar.b.c();
                            i3 = this.b.f;
                            fragment = new GamePageWebViewFragment(c3, a, i3, this.b.j());
                            break;
                        case DYNAMIC:
                            String a2 = this.b.a(bjkVar.b.c(), bjkVar);
                            int c4 = bjkVar.b.c();
                            i2 = this.b.f;
                            fragment = new GamePageWebViewFragment(c4, a2, i2, this.b.j(), bjkVar.c);
                            break;
                        default:
                            this.b.e().severe("unknown game frag type: " + i);
                            fragment = null;
                            break;
                    }
                    try {
                        this.b.e().info("created frag type: " + bjkVar.a() + " at pos: " + i);
                        return fragment;
                    } catch (Throwable th2) {
                        th = th2;
                        this.b.e().log(Level.SEVERE, "problem: ", th);
                        return fragment;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                fragment = null;
            }
        }
        this.b.e().severe("asked for item OUTSIDE of known tab types: " + i);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [android.support.v4.app.Fragment, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.StringBuilder] */
    @Override // android.support.v4.app.FragmentPagerAdapter, android.support.v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        bbf bbfVar;
        FragmentManager supportFragmentManager;
        String tag;
        ?? findFragmentByTag;
        try {
            bbf remove = this.a.remove(Integer.valueOf(i));
            if (remove != null && remove != obj) {
                this.b.e().severe("different fragment removed from map vs. superclass calls:  map/super: " + remove + "/" + obj);
            }
            this.b.e().info("destroying frag pos/name: " + i + "/" + remove);
            super.destroyItem(viewGroup, i, obj);
            if (obj != null && (findFragmentByTag = (supportFragmentManager = this.b.getSupportFragmentManager()).findFragmentByTag((tag = (bbfVar = (bbf) obj).getTag()))) != 0) {
                if (findFragmentByTag == bbfVar) {
                    this.b.e().info("removing fragment via manager: " + findFragmentByTag + "/" + tag);
                } else {
                    this.b.e().severe("removing game frag: " + bbfVar + "/" + tag + ", and found: " + findFragmentByTag + " which is not same.  removing found.");
                    bbfVar = findFragmentByTag;
                }
                FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
                beginTransaction.remove(bbfVar);
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem destroying game tab: ", th);
        }
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        List list;
        List list2;
        try {
            list = this.b.p;
            if (i < list.size()) {
                list2 = this.b.p;
                return ((bjk) list2.get(i)).b();
            }
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem: ", th);
        }
        return "???";
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public long getItemId(int i) {
        List list;
        List list2;
        list = this.b.p;
        if (i < list.size()) {
            list2 = this.b.p;
            return ((bjk) list2.get(i)).c();
        }
        return -1L;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getItemPosition(Object obj) {
        bjl bjlVar;
        List list;
        int i;
        try {
            bjlVar = (bjl) obj;
            AbstractGamePageFragment.FragType a = AbstractGamePageFragment.FragType.a(bjlVar.getFragTypeNum());
            int i2 = 0;
            list = this.b.p;
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                bjk bjkVar = (bjk) it.next();
                if (bjkVar.b == a) {
                    if (a != AbstractGamePageFragment.FragType.DYNAMIC) {
                        i = i2;
                        break;
                    }
                    if (bjkVar.c == bjlVar.getDynamicResNum()) {
                        i = i2;
                        break;
                    }
                }
                i2++;
            }
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem: ", th);
        }
        if (i < 0) {
            return -2;
        }
        bbf bbfVar = this.a.get(Integer.valueOf(i));
        if (bbfVar == null || bbfVar != bjlVar) {
            return -2;
        }
        return -1;
    }

    public bbf a(int i) {
        return this.a.get(Integer.valueOf(i));
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0022, code lost:
    
        if (r2 >= r3.size()) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public defpackage.bbf a(boolean r6) {
        /*
            r5 = this;
            r0 = 0
            com.thuuz.gtv.activity.game_page.GamePageActivity r1 = r5.b     // Catch: java.lang.Throwable -> L41
            android.support.v4.view.ViewPager r1 = com.thuuz.gtv.activity.game_page.GamePageActivity.h(r1)     // Catch: java.lang.Throwable -> L41
            int r2 = r1.getCurrentItem()     // Catch: java.lang.Throwable -> L41
            bbf r1 = r5.a(r2)     // Catch: java.lang.Throwable -> L41
            if (r1 == 0) goto L16
            r1.doRefresh(r6)     // Catch: java.lang.Throwable -> L41
        L14:
            r0 = r1
        L15:
            return r0
        L16:
            if (r2 < 0) goto L24
            com.thuuz.gtv.activity.game_page.GamePageActivity r3 = r5.b     // Catch: java.lang.Throwable -> L41
            java.util.List r3 = com.thuuz.gtv.activity.game_page.GamePageActivity.f(r3)     // Catch: java.lang.Throwable -> L41
            int r3 = r3.size()     // Catch: java.lang.Throwable -> L41
            if (r2 < r3) goto L14
        L24:
            com.thuuz.gtv.activity.game_page.GamePageActivity r1 = r5.b     // Catch: java.lang.Throwable -> L41
            java.util.logging.Logger r1 = r1.e()     // Catch: java.lang.Throwable -> L41
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L41
            r3.<init>()     // Catch: java.lang.Throwable -> L41
            java.lang.String r4 = "cannot get current frag for out-of-bounds index: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: java.lang.Throwable -> L41
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch: java.lang.Throwable -> L41
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L41
            r1.severe(r2)     // Catch: java.lang.Throwable -> L41
            goto L15
        L41:
            r1 = move-exception
            com.thuuz.gtv.activity.game_page.GamePageActivity r2 = r5.b
            java.util.logging.Logger r2 = r2.e()
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            java.lang.String r4 = "Cannot refresh current frag: "
            r2.log(r3, r4, r1)
            goto L15
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bji.a(boolean):bbf");
    }

    public String a() {
        ViewPager viewPager;
        viewPager = this.b.m;
        ComponentCallbacks componentCallbacks = (bbf) this.a.get(Integer.valueOf(viewPager.getCurrentItem()));
        if (componentCallbacks == null) {
            return "";
        }
        return ((bjl) componentCallbacks).getFragName();
    }

    public int a(String str) {
        List list;
        int i = 0;
        list = this.b.p;
        Iterator it = list.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                if (!((bjk) it.next()).a().equals(str)) {
                    i = i2 + 1;
                } else {
                    return i2;
                }
            } else {
                return -1;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void b() {
        try {
            for (bbf bbfVar : this.a.values()) {
                bjl bjlVar = (bjl) bbfVar;
                if (AbstractGamePageFragment.FragType.GAME_MOBILE.c() != bjlVar.getFragTypeNum() && AbstractGamePageFragment.FragType.GAME_TABLET.c() != bjlVar.getFragTypeNum() && !a(bjlVar)) {
                    bjlVar.isPrefetch(true);
                    try {
                        try {
                            bbfVar.doRefresh(false);
                            bjlVar.isPrefetch(false);
                        } catch (Throwable th) {
                            bjlVar.isPrefetch(false);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        this.b.e().log(Level.SEVERE, "Cannot refresh during preload: ", th2);
                        bjlVar.isPrefetch(false);
                    }
                }
            }
        } catch (Throwable th3) {
            this.b.e().log(Level.SEVERE, "Cannot preload: ", th3);
        }
    }

    private boolean a(bjl bjlVar) {
        return AbstractGamePageFragment.FragType.GRAPH.c() == bjlVar.getFragTypeNum();
    }
}
