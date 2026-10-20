package com.thuuz.gtv.activity.sports;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ActivityChooserView;
import com.facebook.internal.ServerProtocol;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.FetchRequest;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.util.ServerChoice;
import com.thuuz.gtv.view.HorizColumnScroll;
import com.thuuz.gtv.view.ListViewDpad;
import defpackage.ba;
import defpackage.bbl;
import defpackage.bbo;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbs;
import defpackage.bbt;
import defpackage.bmm;
import defpackage.bms;
import defpackage.bnb;
import defpackage.bnc;
import defpackage.bnd;
import defpackage.bne;
import defpackage.bnf;
import defpackage.bng;
import defpackage.bnh;
import defpackage.bni;
import defpackage.bnj;
import defpackage.bnl;
import defpackage.bnm;
import defpackage.bnn;
import defpackage.bno;
import defpackage.bnp;
import defpackage.bnq;
import defpackage.bnr;
import defpackage.bns;
import defpackage.bnt;
import defpackage.bnv;
import defpackage.bnw;
import defpackage.bnx;
import defpackage.bny;
import defpackage.bnz;
import defpackage.boa;
import defpackage.bob;
import defpackage.boz;
import defpackage.bpo;
import defpackage.bpr;
import defpackage.bpu;
import defpackage.bpx;
import defpackage.bzt;
import defpackage.cag;
import defpackage.caj;
import defpackage.cbn;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.logging.Level;
import org.joda.time.DateTime;

/* loaded from: classes.dex */
public class GuideActivity extends AbstractTabActivity implements bbl, caj {
    public static final Integer f = -1;
    public static final Integer h = -2;
    private TextView A;
    private boz B;
    private Bitmap C;
    private boolean D;
    private bpu E;
    private ImageView F;
    private bnw G;
    private bnv H;
    private boolean I;
    private TimeZone J;
    private boolean K;
    private boa L;
    private FetchRequest i;
    private long j;
    private boolean k;
    private HorizColumnScroll l;
    private final Handler m;
    private GameSortType n;
    private Set<Integer> o;
    private Set<Integer> p;
    private Set<Integer> q;
    private final Set<Integer> r;
    private int s;
    private final Object t;
    private Date u;
    private final Object v;
    private final Set<Integer> w;
    private boolean x;
    private String y;
    private View z;

    public GuideActivity() {
        super("Games");
        this.j = 0L;
        this.m = new Handler();
        this.n = GameSortType.SORT_EQ;
        this.r = new HashSet();
        this.s = -1;
        this.t = new Object();
        this.v = new Object();
        this.w = Collections.synchronizedSet(new HashSet());
        this.y = "";
        this.K = true;
        this.L = new boa(this);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.guide_layout);
        bl();
        this.l = (HorizColumnScroll) findViewById(bbq.hscroll);
        this.F = (ImageView) findViewById(bbq.banner);
        this.F.setOnClickListener(new bnb(this));
        aN();
        this.l.setDefaultDisplay(ae());
        s();
        this.o = j().o();
        this.p = j().r();
        this.q = j().x();
        this.s = aH();
        this.n = GameSortType.a(j());
        if (j().ac()) {
            bk();
        }
        this.x = j().aK();
        this.z = findViewById(bbq.column_layout);
        setupPersonalizeButton(this.z);
        D();
        this.A = (TextView) findViewById(bbq.none_found);
        this.E = j().ax().a();
        this.I = c(bundle);
        if (this.l.getChildCount() == 0) {
            e().info("hiding hscroll until child count > 0");
            this.l.setVisibility(4);
        }
        this.J = TimeZone.getDefault();
    }

    private void aN() {
        this.l.setOnNavColumnListener(new bnl(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void E() {
        invalidateOptionsMenu();
        aR();
    }

    @Override // android.support.v4.app.FragmentActivity
    public Object onRetainCustomNonConfigurationInstance() {
        if (aX()) {
            return bm();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.sports.AbstractTabActivity, com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            bl();
            this.s = aH();
            a(FetchRequest.FORCING.RETURN_CACHED_THEN_FETCH);
            bp();
            setupPersonalizeButton(this.z);
            j().k("/filter/" + j().aD());
            if (aM() && this.B != null) {
                j().a("league_sponsor", ServerProtocol.DIALOG_PARAM_DISPLAY, this.B.b(), 0L);
            }
            this.l.d();
            boolean T = T();
            j().c(this).a(T && !j().b((AbstractActivity) this).a(T));
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem findItem;
        View findViewById;
        try {
            MenuInflater supportMenuInflater = getSupportMenuInflater();
            supportMenuInflater.inflate(bbs.sort, menu);
            if (!br() || aP()) {
                supportMenuInflater.inflate(bbs.standings, menu);
                if (j().a((Activity) this) && (findItem = menu.findItem(bbq.standings_option)) != null) {
                    findItem.setIcon((Drawable) null);
                }
            }
            if ((aI() || aJ()) && j().e() && Build.VERSION.SDK_INT > 13) {
                supportMenuInflater.inflate(bbs.add_fav, menu);
            }
            supportMenuInflater.inflate(bbs.refresh, menu);
            int i = bbq.refresh_option;
            if (j().e() && (findViewById = findViewById(i)) != null) {
                findViewById.setNextFocusRightId(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                return true;
            }
            return true;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
            return true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x000c, code lost:
    
        r0 = super.onOptionsItemSelected(r5);
     */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem r5) {
        /*
            r4 = this;
            r0 = 1
            int r1 = r5.getItemId()     // Catch: java.lang.Throwable -> L30
            int r2 = defpackage.bbq.sort_option     // Catch: java.lang.Throwable -> L30
            if (r1 != r2) goto Ld
            r4.bt()     // Catch: java.lang.Throwable -> L30
        Lc:
            return r0
        Ld:
            int r1 = r5.getItemId()     // Catch: java.lang.Throwable -> L30
            int r2 = defpackage.bbq.standings_option     // Catch: java.lang.Throwable -> L30
            if (r1 != r2) goto L45
            boolean r1 = r4.br()     // Catch: java.lang.Throwable -> L30
            if (r1 == 0) goto L41
            boolean r1 = r4.aP()     // Catch: java.lang.Throwable -> L30
            if (r1 == 0) goto L41
            android.content.Intent r1 = new android.content.Intent     // Catch: java.lang.Throwable -> L30
            com.thuuz.gtv.activity.AbstractActivity r2 = r4.k()     // Catch: java.lang.Throwable -> L30
            java.lang.Class<com.thuuz.gtv.activity.sports.StandingsActivity> r3 = com.thuuz.gtv.activity.sports.StandingsActivity.class
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L30
            r4.startActivity(r1)     // Catch: java.lang.Throwable -> L30
            goto Lc
        L30:
            r0 = move-exception
            java.util.logging.Logger r1 = r4.e()
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "Cannot handle: "
            r1.log(r2, r3, r0)
        L3c:
            boolean r0 = super.onOptionsItemSelected(r5)
            goto Lc
        L41:
            r4.bs()     // Catch: java.lang.Throwable -> L30
            goto Lc
        L45:
            int r1 = r5.getItemId()     // Catch: java.lang.Throwable -> L30
            int r2 = defpackage.bbq.add_fav_option     // Catch: java.lang.Throwable -> L30
            if (r1 != r2) goto L51
            r4.V()     // Catch: java.lang.Throwable -> L30
            goto Lc
        L51:
            int r1 = r5.getItemId()     // Catch: java.lang.Throwable -> L30
            int r2 = defpackage.bbq.info_option     // Catch: java.lang.Throwable -> L30
            if (r1 != r2) goto L5d
            r4.R()     // Catch: java.lang.Throwable -> L30
            goto Lc
        L5d:
            int r1 = defpackage.bbq.refresh_option     // Catch: java.lang.Throwable -> L30
            int r2 = r5.getItemId()     // Catch: java.lang.Throwable -> L30
            if (r1 != r2) goto L3c
            com.thuuz.gtv.data.FetchRequest$FORCING r1 = com.thuuz.gtv.data.FetchRequest.FORCING.FORCE     // Catch: java.lang.Throwable -> L30
            r4.a(r1)     // Catch: java.lang.Throwable -> L30
            goto Lc
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.activity.sports.GuideActivity.onOptionsItemSelected(com.actionbarsherlock.view.MenuItem):boolean");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        a(FetchRequest.FORCING.NOT_FORCED);
    }

    private void a(FetchRequest.FORCING forcing) {
        try {
            if (F() != null && d()) {
                boolean z = aR() || bg();
                if (z) {
                    invalidateOptionsMenu();
                    e().info("filter criteria changed");
                    bh();
                }
                boolean z2 = z || !aX() || this.u == null || this.u.before(new Date(System.currentTimeMillis() - 120000)) || aF().d();
                boolean z3 = System.currentTimeMillis() - this.j > 60000;
                boolean a = forcing.a();
                if (z2) {
                    aQ();
                } else if (a || z3) {
                    b(FetchRequest.FORCING.FORCE);
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            if (this.l != null) {
                Integer valueOf = Integer.valueOf(this.l.getLeftVisibleIndex());
                if (valueOf == null) {
                    valueOf = 0;
                }
                bundle.putInt("saved_column", valueOf.intValue());
                Map<Integer, View> bb = bb();
                StringBuilder sb = new StringBuilder();
                Iterator<Integer> it = bb.keySet().iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                    sb.append(",");
                }
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 1);
                    bundle.putString("columns", sb.toString());
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem in OnSaveInstanceState: ", th);
        }
    }

    private boolean c(Bundle bundle) {
        boolean z;
        Throwable th;
        if (bundle == null) {
            return false;
        }
        try {
            String string = bundle.getString("columns");
            if (string != null) {
                ArrayList arrayList = new ArrayList();
                String[] split = string.split(",");
                for (String str : split) {
                    arrayList.add(Integer.valueOf(Integer.parseInt(str)));
                }
                b(arrayList);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    a(((Integer) it.next()).intValue(), (Set<Integer>) null);
                }
            }
            z = e(bundle);
            try {
                d(bundle);
                return z;
            } catch (Throwable th2) {
                th = th2;
                e().log(Level.SEVERE, "problem: ", th);
                return z;
            }
        } catch (Throwable th3) {
            z = false;
            th = th3;
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        boolean z;
        boolean z2;
        IService F = F();
        if (F != null) {
            if (F.g()) {
                aR();
            } else {
                new bzt(this).execute(new Void[0]);
            }
        }
        if (this.I) {
            e().info("onService with wasRestoredFromOrientationChangeServiceFlag true");
            this.I = false;
            s();
            bg();
            if (Build.VERSION.SDK_INT <= 10) {
                aa().postDelayed(new bnm(this), 150L);
                z = true;
            } else {
                z = false;
            }
            bob aF = aF();
            Map<Integer, View> bb = bb();
            int i = aF.a;
            boolean z3 = false;
            while (i <= aF.b) {
                View view = bb.get(Integer.valueOf(i));
                if (view == null) {
                    z2 = z3;
                } else {
                    cbn cbnVar = (cbn) view.getTag();
                    bms bmsVar = (bms) cbnVar.e.getGameListAdapter();
                    if (bmsVar.f()) {
                        bmsVar.clear();
                    }
                    if (bmsVar.isEmpty()) {
                        Set<Integer> i2 = i(cbnVar.a.intValue());
                        if (i2.size() > 0) {
                            e().info("after orientation, filled code: " + cbnVar.a + " with  " + i2.size() + "games");
                            bmsVar.c().addAll(i2);
                            c(bmsVar.c());
                            bmsVar.notifyDataSetChanged();
                            z2 = z3;
                        } else if (aF.a(cbnVar.a)) {
                            z2 = true;
                        }
                    }
                    z2 = z3;
                }
                i++;
                z3 = z2;
            }
            if (z3 && !z) {
                e().info("after orientation, found 0 games for at least one vis column, so starting vis column refresh");
                b(FetchRequest.FORCING.FORCE);
            }
            bd();
            aO();
        } else {
            Map<Integer, View> bb2 = bb();
            for (Integer num : bb2.keySet()) {
                a(((cbn) bb2.get(num).getTag()).e, num, i(num.intValue()));
            }
            aQ();
        }
        bp();
        invalidateOptionsMenu();
    }

    private void aO() {
        aa().postDelayed(new bnn(this), 1500L);
    }

    private boolean aP() {
        try {
            IService F = F();
            if (F != null) {
                List<bpr> d = F.d(bf());
                if (d.size() != 0) {
                    if (d.get(0).j()) {
                        return true;
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        return false;
    }

    private void aQ() {
        if (this.G != null) {
            e().info("skipping gamedates while fetch is in progress");
            return;
        }
        try {
            this.G = new bnw(this, null);
            this.G.execute(new Void[0]);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public defpackage.cbn a(int r8, java.util.Set<java.lang.Integer> r9) {
        /*
            r7 = this;
            r1 = 0
            java.lang.Object r3 = r7.v     // Catch: java.lang.Throwable -> L72
            monitor-enter(r3)     // Catch: java.lang.Throwable -> L72
            com.thuuz.gtv.view.HorizColumnScroll r0 = r7.l     // Catch: java.lang.Throwable -> L44
            java.lang.Integer r2 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L44
            android.view.View r0 = r0.b(r2)     // Catch: java.lang.Throwable -> L44
            if (r0 == 0) goto L41
            r0 = 1
            r2 = r0
        L12:
            if (r2 != 0) goto L7e
            com.thuuz.gtv.view.HorizColumnScroll r0 = r7.l     // Catch: java.lang.Throwable -> L44
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L44
            android.view.View r0 = r0.a(r4)     // Catch: java.lang.Throwable -> L44
            cbn r0 = r7.a(r0, r9)     // Catch: java.lang.Throwable -> L44
        L22:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L79
            if (r2 == 0) goto L40
            com.thuuz.gtv.view.HorizColumnScroll r1 = r7.l     // Catch: java.lang.Throwable -> L77
            com.thuuz.gtv.view.HorizColumnScroll r2 = r7.l     // Catch: java.lang.Throwable -> L77
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L77
            int r2 = r2.d(r3)     // Catch: java.lang.Throwable -> L77
            cbn r0 = r1.d(r2)     // Catch: java.lang.Throwable -> L77
            if (r0 == 0) goto L40
            com.thuuz.gtv.view.ListViewDpad r1 = r0.e     // Catch: java.lang.Throwable -> L77
            java.lang.Integer r2 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L77
            r7.a(r1, r2, r9)     // Catch: java.lang.Throwable -> L77
        L40:
            return r0
        L41:
            r0 = 0
            r2 = r0
            goto L12
        L44:
            r0 = move-exception
        L45:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L44
            throw r0     // Catch: java.lang.Throwable -> L47
        L47:
            r0 = move-exception
            r6 = r0
            r0 = r1
            r1 = r6
        L4b:
            boolean r2 = defpackage.caz.a(r1)
            if (r2 == 0) goto L55
            r7.a(r1)
            goto L40
        L55:
            java.util.logging.Logger r2 = r7.e()
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Cannot add column for code: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.String r4 = r4.toString()
            r2.log(r3, r4, r1)
            goto L40
        L72:
            r0 = move-exception
            r6 = r0
            r0 = r1
            r1 = r6
            goto L4b
        L77:
            r1 = move-exception
            goto L4b
        L79:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L45
        L7e:
            r0 = r1
            goto L22
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.activity.sports.GuideActivity.a(int, java.util.Set):cbn");
    }

    private boolean aR() {
        boolean z;
        Throwable th;
        String str;
        try {
            String aS = aS();
            String str2 = aS == null ? "" : aS;
            String[] aa = j().aa();
            if (aI()) {
                str = "My Sports";
            } else if (aJ()) {
                str = "My Teams";
            } else if (aL()) {
                str = "Friends Games";
            } else if (aK() || aa == null || aa.length < 2) {
                str = "All Sports";
            } else {
                str = a(aa[0], aa[1], F());
            }
            z = str2.equals(str) ? false : true;
            if (z) {
                try {
                    e().info("changed filter old/new: " + str2 + "/" + str);
                } catch (Throwable th2) {
                    th = th2;
                    e().log(Level.SEVERE, "Error setting filter title: ", th);
                    return z;
                }
            }
            g(str);
            a(j().a((Activity) this) ? "Guide: " + str : str);
        } catch (Throwable th3) {
            z = false;
            th = th3;
        }
        return z;
    }

    private void g(String str) {
        this.y = str;
    }

    private String aS() {
        return this.y;
    }

    @SuppressLint({"NewApi"})
    private cbn a(View view, Set<Integer> set) {
        cbn cbnVar = (cbn) view.getTag();
        bms bmsVar = new bms(this, new ArrayList());
        cbnVar.e.setAdapter((ListAdapter) bmsVar);
        cbnVar.e.setItemsCanFocus(true);
        cbnVar.e.setOnKeyListener(this.L);
        if (j().e()) {
            cbnVar.e.setOverScrollMode(2);
        }
        if (this.l.e() == 1) {
            cbnVar.c.setVisibility(0);
            cbnVar.d.setVisibility(0);
        }
        if (set == null) {
            bmsVar.add(h);
        } else {
            a(cbnVar.e, cbnVar.a, set);
        }
        this.m.postDelayed(new bno(this, cbnVar.e), 100L);
        cbnVar.b.setText(h(cbnVar.a));
        return cbnVar;
    }

    private String aT() {
        StringBuilder sb = new StringBuilder(ServerChoice.q);
        sb.append("sponsored_league/");
        sb.append(this.B.b());
        DisplayMetrics ae = ae();
        sb.append("/?w=");
        Integer[] visibleColumnCodes = this.l.getVisibleColumnCodes();
        int columnWidth = this.l.getColumnWidth();
        if (visibleColumnCodes.length > 0) {
            columnWidth *= visibleColumnCodes.length;
        }
        sb.append(columnWidth - (getResources().getDimensionPixelSize(bbo.banner_padding) * 2));
        sb.append("&h=");
        sb.append(j().q(75));
        sb.append("&density=");
        sb.append(ae.density);
        sb.append(e(sb.toString()));
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aU() {
        cbn d;
        try {
            h(true);
            int leftVisibleIndex = ((this.l.getLeftVisibleIndex() + this.l.e()) - 1) + 1;
            if (this.l.getSize() > leftVisibleIndex && (d = this.l.d(leftVisibleIndex)) != null) {
                g(d.a.intValue());
            }
            aW();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i) {
        if (this.k) {
            if (this.l.d(this.l.d(Integer.valueOf(i))) == null) {
                e().severe("no column for code: " + i);
            } else {
                g(i);
            }
        }
    }

    private long c(Integer num) {
        if (e(num)) {
            return 0L;
        }
        if (!d(num) && -2 != num.intValue() && 2 != num.intValue()) {
            return 3600000L;
        }
        return 300000L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(Integer num) {
        return -2 < num.intValue() && num.intValue() < 2;
    }

    private boolean e(Integer num) {
        try {
            cbn d = this.l.d(this.l.d(num));
            if (d != null && d.i != null) {
                if (new DateTime(d.i).getDayOfYear() != new DateTime().getDayOfYear()) {
                    return true;
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aV() {
        cbn d;
        try {
            h(false);
            int leftVisibleIndex = this.l.getLeftVisibleIndex();
            int i = leftVisibleIndex - 1;
            if (leftVisibleIndex >= 0 && (d = this.l.d(i)) != null) {
                g(d.a.intValue());
            }
            aW();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void aW() {
        View childAt;
        bmm bmmVar;
        try {
            ActivityManager activityManager = (ActivityManager) getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            if (memoryInfo.lowMemory) {
                HashSet hashSet = new HashSet(Arrays.asList(this.l.getVisibleColumnCodes()));
                int size = this.l.getSize();
                for (int i = 0; i < size; i++) {
                    cbn d = this.l.d(i);
                    if (!hashSet.contains(d.a)) {
                        e().info("abandoning plots for hidden column: " + i + "(" + d.a + ") to help with RAM memory pressure");
                        bms bmsVar = (bms) d.e.getGameListAdapter();
                        bmsVar.j();
                        int childCount = d.e.getChildCount();
                        for (int i2 = 0; i2 < childCount; i2++) {
                            try {
                                Integer num = bmsVar.c().get(i2);
                                if (!h.equals(num) && !f.equals(num) && (childAt = d.e.getChildAt(i2)) != null && (bmmVar = (bmm) childAt.getTag()) != null) {
                                    bmmVar.w.setImageBitmap(null);
                                    bmmVar.w.setTag(null);
                                }
                            } catch (Throwable th) {
                                e().warning("problem iterating list clear: " + th);
                            }
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            e().log(Level.SEVERE, "problem in abandonPlotsIfNecessary: ", th2);
        }
    }

    private void h(boolean z) {
        try {
            if (z) {
                j().a("Nav", "DateColumn", "right", this.l.getLeftVisibleIndex());
            } else {
                j().a("Nav", "DateColumn", "left", this.l.getLeftVisibleIndex());
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean aX() {
        boolean z;
        boolean z2;
        try {
            if (this.l == null) {
                return false;
            }
            int size = this.l.getSize();
            int i = 0;
            z = false;
            while (i < size) {
                try {
                    ListViewDpad a = this.l.a(i);
                    if (a == null) {
                        z2 = z;
                    } else {
                        bms bmsVar = (bms) a.getGameListAdapter();
                        if (bmsVar == null) {
                            z2 = z;
                        } else {
                            List<Integer> c = bmsVar.c();
                            if (c == null) {
                                z2 = z;
                            } else {
                                boolean z3 = c.size() > 1;
                                if (!z3) {
                                    if (c.size() == 1) {
                                        try {
                                            Integer num = c.get(0);
                                            z2 = (f.equals(num) || h.equals(num)) ? false : true;
                                            if (z2) {
                                                return z2;
                                            }
                                        } catch (Throwable th) {
                                            z2 = z3;
                                        }
                                    } else {
                                        z2 = z3;
                                    }
                                } else {
                                    return z3;
                                }
                            }
                        }
                    }
                    i++;
                    z = z2;
                } catch (Throwable th2) {
                    th = th2;
                    e().log(Level.SEVERE, "Cannot handle: ", th);
                    return z;
                }
            }
            return z;
        } catch (Throwable th3) {
            th = th3;
            z = false;
        }
    }

    @Override // com.thuuz.gtv.activity.sports.AbstractTabActivity, com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            if (this.l != null) {
                int size = this.l.getSize();
                for (int i = 0; i < size; i++) {
                    cbn d = this.l.d(i);
                    if (d != null) {
                        ((bms) d.e.getGameListAdapter()).i();
                    }
                }
            }
            FetchRequest fetchRequest = this.i;
            if (fetchRequest != null) {
                fetchRequest.a((bbl) null);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem destroying: ", th);
        }
        super.onDestroy();
    }

    public bob aF() {
        bob bobVar = new bob(this, null);
        if (this.l != null) {
            bobVar.a(this.l.getVisibleColumnCodes());
            if (bobVar.c.length == 0) {
                bobVar.a = 0;
                bobVar.b = 0;
            } else {
                bobVar.a = bobVar.c[0];
                bobVar.b = bobVar.c[bobVar.c.length - 1];
            }
        }
        return bobVar;
    }

    private Date f(Integer num) {
        DateTime dateTime = new DateTime(I());
        if (d(num)) {
            return dateTime.withTimeAtStartOfDay().toDate();
        }
        return dateTime.plusDays(num.intValue() < 0 ? num.intValue() + 1 : num.intValue() - 1).withTimeAtStartOfDay().toDate();
    }

    public int aH() {
        return new DateTime().getDayOfMonth();
    }

    public boolean aI() {
        return j().m();
    }

    public boolean aJ() {
        return j().ab();
    }

    public boolean aK() {
        return j().J();
    }

    public boolean aL() {
        return j().ac();
    }

    @Override // defpackage.bbl
    public void onConcludeFetch(bpo bpoVar) {
        runOnUiThread(new bnp(this, bpoVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, boolean z) {
        this.m.postDelayed(new bnz(this, z), j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g(int i) {
        return a(i, FetchRequest.FORCING.NOT_FORCED);
    }

    private boolean a(int i, FetchRequest.FORCING forcing) {
        int d;
        cbn d2;
        try {
            if (this.w.contains(Integer.valueOf(i)) || (d2 = this.l.d((d = this.l.d(Integer.valueOf(i))))) == null) {
                return false;
            }
            bms bmsVar = (bms) d2.e.getGameListAdapter();
            if (!(forcing.a() || d2.i == null || bmsVar.e() || bmsVar.c().isEmpty() || d2.i.before(new Date(System.currentTimeMillis() - c(d2.a))))) {
                bmsVar.notifyDataSetChanged();
                return false;
            }
            IService F = F();
            if (F == null) {
                return false;
            }
            e().info("fetching for code " + i + " at index: " + d);
            this.w.add(Integer.valueOf(i));
            return F.a(a(F, i)).a() == 0;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem fetching column: ", th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Integer> b(Map<Integer, Set<Integer>> map) {
        IService F;
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            arrayList.addAll(map.keySet());
            HashSet hashSet = new HashSet();
            for (Integer num : new ArrayList(arrayList)) {
                hashSet.clear();
                Set<Integer> set = map.get(num);
                if (set != null && !set.contains(f) && !set.contains(h) && (F = F()) != null) {
                    F.a(set, hashSet);
                }
                if (hashSet.size() == 0) {
                    arrayList.remove(num);
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00d6, code lost:
    
        if (r15.size() > 0) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00db A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(java.util.ArrayList<java.lang.Integer> r14, java.util.ArrayList<java.lang.Integer> r15, java.util.ArrayList<java.lang.Integer> r16, java.lang.Integer[] r17, java.util.TreeMap<java.lang.Integer, java.util.Set<java.lang.Integer>> r18) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.activity.sports.GuideActivity.a(java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.lang.Integer[], java.util.TreeMap):boolean");
    }

    private void a(Integer num, Integer num2, boolean z) {
        if (num2 != null) {
            Integer valueOf = Integer.valueOf(this.l.c(Integer.valueOf(this.l.c(num2)).intValue()));
            e().info("after changing columns, scroll to: " + valueOf);
            this.l.a(valueOf.intValue(), false);
            if (j().e() && num != null && Math.abs(num.intValue()) != 500 && !ax()) {
                ListViewDpad a = this.l.a(this.l.c(num));
                if (a != null && d() && !aC()) {
                    View childAt = a.getChildAt(0);
                    if (childAt != null) {
                        childAt.requestFocus();
                        e().info("resetting scroll after column change; to: " + valueOf + " (from code: " + num2 + ") ");
                    } else {
                        a.requestFocus();
                        e().info("resetting scroll after column change; to: " + valueOf + " (from code: " + num2 + ") with list focus only");
                    }
                }
            }
        }
        if (z) {
            for (Integer num3 : this.l.getVisibleColumnCodes()) {
                cbn d = this.l.d(this.l.d(num3));
                if (d != null) {
                    bms bmsVar = (bms) d.e.getGameListAdapter();
                    if (!this.w.contains(num3) && (bmsVar.isEmpty() || bmsVar.f())) {
                        e().info("after removing columns, fetching games for vis code: " + num3);
                        g(d.a.intValue());
                    }
                }
            }
        }
    }

    @Override // defpackage.bbl
    public void onStartFetch() {
        runOnUiThread(new bnq(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void aY() {
        g((Integer) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int g(Integer num) {
        int i;
        Throwable th;
        synchronized (this) {
            try {
                int size = this.l.getSize();
                if (num == null) {
                    int[] aZ = aZ();
                    num = Integer.valueOf(aZ[0]);
                    i = aZ[1];
                } else {
                    int intValue = num.intValue();
                    try {
                        if (this.l.e() > 1 && size > 0) {
                            intValue = num.intValue() + 1;
                            if (j().a((Activity) this)) {
                                i = num.intValue() + 2;
                            }
                        }
                        i = intValue;
                    } catch (Throwable th2) {
                        th = th2;
                        i = intValue;
                        e().log(Level.SEVERE, "Cannot handle: ", th);
                        return i;
                    }
                }
                try {
                    if (!this.k) {
                        if (this.l.g()) {
                            this.k = true;
                        } else if (this.l.getWidth() != 0 && this.l.getSize() != 0) {
                            if (size == 0) {
                                e().warning("no columns; cannot focus");
                            } else {
                                Integer valueOf = Integer.valueOf(this.l.c(num.intValue()));
                                e().info("focusInitially scrolls to: " + valueOf);
                                this.l.a(valueOf.intValue(), false);
                                this.k = this.l.getSize() > 0;
                                if (j().e() && !ax()) {
                                    ListViewDpad a = this.l.a(i);
                                    if (a != null) {
                                        View childAt = a.getChildAt(0);
                                        if (childAt == null) {
                                            e().info("no item in focusing list, so requesting list focus for index: " + i);
                                            a.requestFocus();
                                        } else {
                                            e().info("requesting first item focus for index: " + i);
                                            childAt.requestFocus();
                                        }
                                        if (d() && !aC()) {
                                            this.l.setCurrentFocusColumn(i);
                                        }
                                    } else {
                                        e().info("cannot find index for focus: " + i);
                                    }
                                }
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    e().log(Level.SEVERE, "Cannot handle: ", th);
                    return i;
                }
            } catch (Throwable th4) {
                i = 0;
                th = th4;
            }
        }
        return i;
    }

    private int[] aZ() {
        int[] iArr = {0, 0};
        try {
            int size = this.l.getSize();
            if (size != 0 && size != 1) {
                int ba = ba();
                int e = this.l.e();
                if (e != 0) {
                    if (e == 1) {
                        int c = this.l.c(ba);
                        iArr[1] = c;
                        iArr[0] = c;
                    } else if (e == 2) {
                        iArr[0] = this.l.c(ba);
                        iArr[1] = ba;
                    } else if (e >= 3) {
                        iArr[0] = this.l.c(ba - 1);
                        iArr[1] = ba;
                    }
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return iArr;
    }

    private int ba() {
        int i;
        int size = this.l.getSize();
        if (size <= 1) {
            return 0;
        }
        int d = this.l.d((Integer) 0);
        if (d >= 0) {
            return d;
        }
        cbn d2 = this.l.d(0);
        if (d2.a.intValue() > 0) {
            return 0;
        }
        int abs = Math.abs(d2.a.intValue());
        int i2 = 0;
        int i3 = 1;
        while (i3 < size) {
            cbn d3 = this.l.d(i3);
            if (d3.a.intValue() <= 0) {
                int abs2 = Math.abs(d3.a.intValue());
                if (abs2 <= abs) {
                    i = i3;
                } else {
                    abs2 = abs;
                    i = i2;
                }
                i3++;
                i2 = i;
                abs = abs2;
            } else {
                return i3;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<Integer, View> bb() {
        cbn cbnVar;
        HashMap hashMap = new HashMap();
        if (this.l == null) {
            return hashMap;
        }
        ArrayList<View> arrayList = new ArrayList();
        int i = 0;
        int i2 = -500;
        while (true) {
            int i3 = i;
            if (i3 >= this.l.getSize()) {
                break;
            }
            View e = this.l.e(i3);
            if (e != null && (cbnVar = (cbn) e.getTag()) != null) {
                Integer num = cbnVar.a;
                if (num == null) {
                    arrayList.add(e);
                } else {
                    i2 = a(i2, num);
                    hashMap.put(num, e);
                }
            }
            i = i3 + 1;
        }
        for (View view : arrayList) {
            e().severe("removing untagged child.");
            this.l.getContainer().removeView(view);
        }
        return hashMap;
    }

    private void a(List<Integer> list, List<Integer> list2, TreeMap<Integer, Set<Integer>> treeMap) {
        a(list2);
        a(list, treeMap);
        this.l.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer[] bc() {
        Integer num;
        Integer num2;
        cbn cbnVar;
        if (!this.k || this.l.getSize() == 0) {
            return null;
        }
        cbn d = this.l.d(Integer.valueOf(this.l.getLeftVisibleIndex()).intValue());
        if (d == null) {
            num = -500;
        } else {
            num = d.a;
        }
        View focusedChild = this.l.getContainer().getFocusedChild();
        if (focusedChild == null || (cbnVar = (cbn) focusedChild.getTag()) == null) {
            num2 = -500;
        } else {
            num2 = cbnVar.a;
        }
        return new Integer[]{num2, num};
    }

    private void a(List<Integer> list) {
        if (list != null) {
            int availableProcessors = Runtime.getRuntime().availableProcessors();
            Map<Integer, View> bb = bb();
            for (Integer num : list) {
                View view = bb.get(num);
                if (view == null) {
                    e().warning("cannot find column for code to remove: " + num);
                } else {
                    try {
                        this.l.a(view);
                    } catch (Throwable th) {
                        e().log(Level.SEVERE, "Cannot handle: ", th);
                    }
                    if (availableProcessors <= 1) {
                        Thread.yield();
                    }
                }
            }
        }
    }

    private void a(List<Integer> list, TreeMap<Integer, Set<Integer>> treeMap) {
        if (list != null && list.size() != 0) {
            b(list);
            for (Integer num : list) {
                try {
                    if (this.l.b(num) == null) {
                        Set<Integer> set = null;
                        if (treeMap != null) {
                            set = treeMap.get(num);
                        }
                        a(num.intValue(), set);
                    }
                } catch (Throwable th) {
                    e().log(Level.SEVERE, "Cannot handle: ", th);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<Integer> list) {
        Collections.sort(list, new bnr(this));
        Integer num = 0;
        if (list.size() > 1 && num.equals(list.get(0)) && list.remove(num)) {
            list.add(1, num);
        }
    }

    private int a(int i, Integer num) {
        if (num.intValue() < i) {
            e().severe("child has code out of sequence! previous/current: " + i + "/" + num);
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bd() {
        View b;
        if (F() != null && (b = this.l.b((Integer) 0)) != null) {
            cbn cbnVar = (cbn) b.getTag();
            bms bmsVar = (bms) cbnVar.e.getGameListAdapter();
            int size = !bmsVar.f() ? bmsVar.c().size() : 0;
            if (size > 0) {
                String valueOf = String.valueOf(size);
                cbnVar.g.setText(valueOf);
                cbnVar.h.setText(valueOf);
                cbnVar.f.setVisibility(0);
                return;
            }
            cbnVar.f.setVisibility(8);
        }
    }

    private String h(Integer num) {
        if (num == null) {
            return "";
        }
        switch (num.intValue()) {
            case -2:
                return "Yesterday";
            case -1:
                return "Earlier Today";
            case 0:
                return "Live";
            case 1:
                return "Later Today";
            case 2:
                return "Tomorrow";
            default:
                return h(num.intValue());
        }
    }

    private String h(int i) {
        int i2;
        String str = "unknown " + i;
        try {
            Calendar bh = j().bh();
            bh.setTime(I());
            if (i < 0) {
                i2 = i + 1;
            } else {
                i2 = i - 1;
            }
            bh.add(6, i2);
            return new SimpleDateFormat("E, MMM d").format(bh.getTime());
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle " + str, th);
            return str;
        }
    }

    private boolean a(ListViewDpad listViewDpad, Integer num, Set<Integer> set) {
        Throwable th;
        boolean z;
        boolean z2 = true;
        if (listViewDpad == null) {
            e().severe("null lv!");
            return true;
        }
        if (num == null) {
            e().severe("null code in reset!");
            return true;
        }
        bms bmsVar = (bms) listViewDpad.getGameListAdapter();
        if (bmsVar == null) {
            e().severe("null adapter!");
            return true;
        }
        try {
            Set<Integer> hashSet = new HashSet<>();
            IService F = F();
            if (F != null) {
                F.a(set, hashSet);
            }
            e().info("column code " + num + " gets " + hashSet.size());
            ArrayList<Integer> arrayList = new ArrayList<>(hashSet);
            c(arrayList);
            if (!arrayList.equals(bmsVar.c())) {
                if (!bmsVar.a(arrayList)) {
                    z2 = false;
                }
            }
            try {
                bmsVar.notifyDataSetChanged();
                return z2;
            } catch (Throwable th2) {
                z = z2;
                th = th2;
                e().log(Level.SEVERE, "Cannot handle: ", th);
                return z;
            }
        } catch (Throwable th3) {
            th = th3;
            z = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<Integer> list) {
        int i = 0;
        Throwable th = null;
        while (i < 10) {
            try {
                Collections.sort(list, this.n.a(this));
                break;
            } catch (Throwable th2) {
                th = th2;
                i++;
                e().info("looping for sort: " + th);
            }
        }
        if (i >= 10) {
            e().severe("sorting games failed 10 times: " + th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ListViewDpad listViewDpad) {
        if (listViewDpad != null) {
            ((cbn) listViewDpad.getTag()).i = new Date();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 78:
                be();
                return;
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }

    private void be() {
        if (bg()) {
            runOnUiThread(new bns(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(FetchRequest.FORCING forcing) {
        IService F = F();
        if (F != null && d()) {
            if (this.i != null) {
                e().warning("refresh ignored because one is ongoing...");
                return;
            }
            this.l.f();
            bob aF = aF();
            if (aF.c.length == 0) {
                e().warning("no visible game columns yet; skipping refresh");
                return;
            }
            int c = aF.c();
            boolean a = j().a((Activity) this);
            if (c == 0) {
                this.i = new FetchRequest(IService.API_REQUEST_GAME_STATUS.LIVE, this, bf(), forcing, null, null, 0, 0, a);
            } else {
                Date f2 = f(Integer.valueOf(c));
                this.i = new FetchRequest(aF.a(c), this, bf(), forcing, f2, new Date(f2.getTime() + 86400000), c, c, a);
            }
            if (aL()) {
                this.i.a(FetchRequest.REQ_TYPE.SHARED);
            }
            this.i.b(true);
            this.j = System.currentTimeMillis();
            e().info("fetching first vis column code: " + c);
            F.a(this.i);
            if (aF.c.length > 1) {
                for (int i : aF.b()) {
                    Integer valueOf = Integer.valueOf(i);
                    if (c != valueOf.intValue()) {
                        a(valueOf.intValue(), forcing);
                    }
                }
            }
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        try {
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            this.l.setDefaultDisplay(displayMetrics);
            super.onConfigurationChanged(configuration);
            aY();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void d(Bundle bundle) {
        try {
            int intExtra = getIntent().getIntExtra("saved_column", 999);
            if (bundle != null) {
                intExtra = bundle.getInt("saved_column", 999);
            }
            if (intExtra != 999) {
                int i = 1000;
                if (Math.abs(intExtra) > 1000) {
                    e().warning("saved left index: " + intExtra + " too large so using max 1000");
                } else {
                    i = intExtra;
                }
                this.m.postDelayed(new bny(this, 0, i, false), 150L);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    private String bf() {
        String Z = j().Z();
        if (Z == null || "all".equals(Z)) {
            return App.e;
        }
        return Z;
    }

    private boolean bg() {
        boolean z = false;
        int aH = aH();
        if (aH != this.s) {
            this.s = aH;
            e().info("current day changed!");
            z = true;
        }
        if (aI()) {
            Set<Integer> set = this.o;
            Set<Integer> set2 = this.p;
            this.o = j().o();
            this.p = j().r();
            if (!this.o.equals(set) || !this.p.equals(set2)) {
                e().info("league prefs changed!");
                z = true;
            }
        }
        Set<Integer> set3 = this.q;
        this.q = j().x();
        if (aJ() && !this.q.equals(set3)) {
            e().info("team prefs changed!");
            z = true;
        }
        if (j().aK() != this.x) {
            this.x = j().aK();
            e().info("show scores changed!");
            z = true;
        }
        if (!j().ax().c(this.E)) {
            e().info("tv location changed!");
            this.E = j().ax().a();
            z = true;
        }
        TimeZone timeZone = this.J;
        this.J = TimeZone.getDefault();
        if (this.J.equals(timeZone)) {
            return z;
        }
        e().warning("tz change from/to: " + timeZone + "/" + this.J);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bh() {
        ListViewDpad listViewDpad;
        try {
            int size = this.l.getSize();
            for (int i = 0; i < size; i++) {
                cbn d = this.l.d(i);
                if (d != null && (listViewDpad = d.e) != null) {
                    ((bms) listViewDpad.getGameListAdapter()).g();
                }
            }
            FetchRequest fetchRequest = this.i;
            if (fetchRequest != null) {
                fetchRequest.a((bbl) null);
                this.i = null;
            }
            int visibility = this.l.getVisibility();
            this.l.setVisibility(4);
            this.l.c();
            this.l.setVisibility(visibility);
            bi();
            bj();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void bi() {
        e().info("clearing initial scroll along with all columns");
        this.k = false;
        this.l.a(false);
    }

    private void bj() {
        this.B = null;
        if (this.C != null) {
            this.C.recycle();
            this.C = null;
        }
        this.D = false;
        if (this.F != null) {
            this.F.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Set<Integer> i(int i) {
        HashSet hashSet = new HashSet();
        try {
            IService F = F();
            if (F != null) {
                hashSet.addAll(new bpo(a(F, i)).c());
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem with getGames: ", th);
        }
        return hashSet;
    }

    private FetchRequest a(IService iService, int i) {
        Date f2 = f(Integer.valueOf(i));
        Date date = new Date(f2.getTime() + 86400000);
        if (i == 0) {
            f2 = new Date(iService.n().getTime() - 691200000);
            date = new Date(iService.n().getTime() + 691200000);
        }
        FetchRequest fetchRequest = new FetchRequest(IService.API_REQUEST_GAME_STATUS.a(Integer.valueOf(i)), this, bf(), FetchRequest.FORCING.FORCE, f2, date, i, i, j().a((Activity) this));
        fetchRequest.c(true);
        if (aL()) {
            fetchRequest.a(FetchRequest.REQ_TYPE.SHARED);
        }
        return fetchRequest;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public bmm a(View view, Integer num, ba baVar) {
        IService F;
        GameData b;
        bmm bmmVar = (bmm) super.a(view, num, baVar);
        if (bmmVar == null) {
            return null;
        }
        if (aL() && (F = F()) != null && (b = F.b(num)) != null) {
            String X = b.X();
            if (X != null) {
                bmmVar.c(true);
                bmmVar.q.setText(X);
                return bmmVar;
            }
            bmmVar.c(false);
            return bmmVar;
        }
        return bmmVar;
    }

    private void bk() {
        if (this.H != null) {
            e().info("skipping friends query since in progress");
        } else {
            this.H = new bnv(this, null);
            this.H.execute(new Void[0]);
        }
    }

    private void bl() {
        if (aL() && !j().i()) {
            e().info("column view refuses my-friends filter when not logged in, resetting.");
            j().B(App.e);
            return;
        }
        if (aI() && j().o().isEmpty() && j().r().isEmpty()) {
            e().info("column view refuses my-sport filter when no sports, resetting.");
            j().B(App.e);
        } else if (aJ() && !j().aB()) {
            e().info("column view refuses my-teams filter when no teams, resetting.");
            j().B(App.e);
        } else if (String.valueOf(-6).equals(j().Z())) {
            j().B(App.e);
        }
    }

    private bnx bm() {
        ListViewDpad a;
        bms bmsVar;
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        while (true) {
            int i2 = i;
            if (this.l == null || i2 >= this.l.getSize()) {
                break;
            }
            cbn d = this.l.d(i2);
            if (d != null && (a = this.l.a(i2)) != null && (bmsVar = (bms) a.getGameListAdapter()) != null) {
                if (bmsVar.f()) {
                    arrayList = new ArrayList(bmsVar.c());
                } else {
                    arrayList = new ArrayList();
                }
                arrayList2.add(new bnt(d.a.intValue(), arrayList, bmsVar.h()));
            }
            i = i2 + 1;
        }
        return new bnx(arrayList2, this.j, this.u);
    }

    private boolean e(Bundle bundle) {
        boolean z;
        Throwable th;
        int i;
        try {
            Object lastCustomNonConfigurationInstance = getLastCustomNonConfigurationInstance();
            if (!(lastCustomNonConfigurationInstance instanceof bnx)) {
                return false;
            }
            bnx bnxVar = (bnx) lastCustomNonConfigurationInstance;
            try {
                for (bnt bntVar : bnxVar.a) {
                    try {
                        this.l.setVisibility(0);
                        bms bmsVar = (bms) a(bntVar.a, new HashSet()).e.getGameListAdapter();
                        bmsVar.a(bntVar.c);
                        bmsVar.c().clear();
                        bmsVar.c().addAll(bntVar.b);
                        bmsVar.notifyDataSetChanged();
                        e().info("restored " + bntVar.b.size() + " games to code: " + bntVar.a);
                    } catch (Throwable th2) {
                        e().log(Level.SEVERE, "Cannot handle: ", th2);
                    }
                }
                this.j = bnxVar.b;
                this.u = bnxVar.c;
                if (bundle != null && (i = bundle.getInt("saved_column", 999)) != 999) {
                    e().info("after restoring, scrolling to col: " + i);
                    this.l.a(i, false);
                }
                return true;
            } catch (Throwable th3) {
                th = th3;
                z = true;
                e().log(Level.SEVERE, "Cannot recover retained from orientation change: ", th);
                return z;
            }
        } catch (Throwable th4) {
            z = false;
            th = th4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bn() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.l.getSize()) {
                cbn d = this.l.d(i2);
                if (d != null) {
                    bms bmsVar = (bms) d.e.getGameListAdapter();
                    c(bmsVar.c());
                    bmsVar.notifyDataSetChanged();
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public long q() {
        if (!bo()) {
            return 300000L;
        }
        return 30000L;
    }

    private boolean bo() {
        for (Integer num : this.l.getVisibleColumnCodes()) {
            if (num.intValue() == 0) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean t() {
        return true;
    }

    private void bp() {
        IService F = F();
        if (F != null) {
            Map<Integer, List<boz>> t = F.t();
            Iterator<bpr> it = F.d(bf()).iterator();
            while (it.hasNext()) {
                List<boz> list = t.get(Integer.valueOf(it.next().g()));
                if (list != null && list.size() > 0) {
                    this.B = list.get(new Random().nextInt(list.size()));
                    if (!this.D) {
                        new cag(this, aT()).execute(new Void[0]);
                        this.D = true;
                        return;
                    } else {
                        if (!aM()) {
                            bq();
                            return;
                        }
                        return;
                    }
                }
                bj();
            }
        }
    }

    @Override // defpackage.caj
    public void a(Bitmap bitmap) {
        this.C = bitmap;
        bq();
    }

    private void bq() {
        this.F.setVisibility(0);
        this.F.setImageBitmap(this.C);
    }

    public boolean aM() {
        if (this.F == null) {
            return false;
        }
        return this.F.isShown();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.sports.AbstractTabActivity
    public void e(int i) {
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        aW();
        System.gc();
    }

    private boolean br() {
        return (aL() || aI() || aJ() || aK()) ? false : true;
    }

    private void bs() {
        try {
            IService F = F();
            if (F == null) {
                if (d()) {
                    b("Not Initialized", bbt.pls_try_again_later);
                    return;
                }
                return;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<bpx> it = F.b().iterator();
            while (it.hasNext()) {
                for (bpr bprVar : F.a(it.next().a(), false)) {
                    if (bprVar.j()) {
                        arrayList.add(bprVar.h());
                    }
                }
            }
            String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Standings for League:");
            builder.setAdapter(new ArrayAdapter(this, bbr.simple_list_item, bbq.text, strArr), new bnc(this));
            builder.setCancelable(true);
            AlertDialog create = builder.create();
            a(create);
            if (!isDestroyed()) {
                create.show();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    private synchronized void bt() {
        try {
            if (F() == null) {
                if (d()) {
                    b("Not Ready", bbt.pls_try_again_later);
                }
            } else {
                Dialog U = U();
                if (U != null) {
                    U.dismiss();
                    e().warning("multiple dialogs from clicking Guide's sort button; dismissing old");
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, bbr.alert_dialog_singlechoice, bbq.single_choice_text, new String[]{"Excitement rating", "Start time"});
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Sort: ");
                int bI = j().bI();
                if (bI < 0 || bI > GameSortType.SORT_CLOCK.a()) {
                    bI = 0;
                }
                builder.setSingleChoiceItems(arrayAdapter, bI, new bnd(this));
                AlertDialog create = builder.create();
                a(create);
                if (!isDestroyed()) {
                    create.show();
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(k());
        builder.setMessage("You are not following anyone. Find friends?");
        builder.setCancelable(false);
        AlertDialog create = builder.create();
        create.setButton(-1, "Find Friends", new bne(this, create));
        create.setButton(-2, "Cancel", new bnf(this, create));
        create.setOnCancelListener(new bng(this));
        Dialog U = U();
        if (U != null) {
            U.dismiss();
        }
        a(create);
        if (!isDestroyed()) {
            create.show();
        }
    }

    @Override // com.thuuz.gtv.activity.sports.AbstractTabActivity, com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        moveTaskToBack(true);
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bv() {
        AlertDialog.Builder builder = new AlertDialog.Builder(k());
        builder.setMessage("Your friends haven't checked into any games recently. Find more friends?");
        builder.setCancelable(false);
        AlertDialog create = builder.create();
        create.setButton(-1, "Find Friends", new bnh(this, create));
        create.setButton(-2, "Cancel", new bni(this, create));
        create.setOnCancelListener(new bnj(this));
        Dialog U = U();
        if (U != null) {
            U.dismiss();
        }
        a(create);
        if (!isDestroyed()) {
            create.show();
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void av() {
        View currentFocus;
        try {
            if (j().e() && (currentFocus = getCurrentFocus()) != null) {
                currentFocus.clearFocus();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        super.av();
    }
}
