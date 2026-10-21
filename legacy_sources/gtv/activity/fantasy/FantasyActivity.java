package com.thuuz.gtv.activity.fantasy;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.fragments.GameListFragment;
import com.thuuz.gtv.activity.sports.AbstractTabActivity;
import com.thuuz.gtv.data.FantasySite;
import com.thuuz.gtv.settings.WebViewFragment;
import com.thuuz.gtv.util.ServerChoice;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import com.viewpagerindicator.TabPageIndicator;
import defpackage.bbf;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbs;
import defpackage.bbt;
import defpackage.beg;
import defpackage.beh;
import defpackage.bei;
import defpackage.bej;
import defpackage.bek;
import defpackage.bpe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class FantasyActivity extends AbstractTabActivity {
    boolean f;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private RelativeLayout l;
    private RelativeLayout m;
    private WebView n;
    private ViewPager o;
    private bek p;
    private TabPageIndicator q;
    private final List<bpe> r;
    private Button s;

    public FantasyActivity() {
        super("Fantasy");
        this.h = false;
        this.i = false;
        this.r = new ArrayList();
        this.f = false;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.fantasy_activity);
        this.l = (RelativeLayout) findViewById(bbq.fant_intro);
        this.m = (RelativeLayout) findViewById(bbq.fantasy_teams_layout);
        boolean aH = aH();
        this.l.setVisibility(aH ? 8 : 0);
        this.m.setVisibility(aH ? 0 : 8);
        this.s = (Button) findViewById(bbq.get_started);
        this.s.setOnClickListener(new beg(this));
        ((Button) findViewById(bbq.learn_more)).setOnClickListener(new beh(this));
        this.n = (WebView) findViewById(bbq.fantasy_intro_webview);
        this.n.setWebViewClient(new bei(this, this));
        this.n.setBackgroundColor(0);
        boolean z = z();
        this.n.getSettings().setDatabaseEnabled(z);
        if (!z) {
            this.n.getSettings().setCacheMode(2);
        }
        WebView webView = (WebView) findViewById(bbq.advert);
        if (j().e()) {
            webView.setVisibility(8);
        } else {
            webView.setWebViewClient(new ThuuzWebViewClient(this));
            webView.getSettings().setDatabaseEnabled(z);
            if (!z) {
                webView.getSettings().setCacheMode(2);
            }
            StringBuilder append = new StringBuilder(ServerChoice.q).append("app/").append("2.3/").append("fantasy/help/import/");
            append.append(e(append.toString()));
            String sb = append.toString();
            e().info("loading advert url: " + sb);
            webView.loadUrl(sb);
        }
        this.o = (ViewPager) findViewById(bbq.pager);
        this.p = new bek(this, getSupportFragmentManager(), this.r);
        this.o.setAdapter(this.p);
        this.q = (TabPageIndicator) findViewById(bbq.indicator);
        this.q.setViewPager(this.o, 0);
        this.q.setOnPageChangeListener(new bej(this));
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(bbs.add_fantasy, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (bbq.add_fant_option != menuItem.getItemId()) {
            return super.onOptionsItemSelected(menuItem);
        }
        g();
        return true;
    }

    @Override // com.thuuz.gtv.activity.sports.AbstractTabActivity, com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        h(false);
        j().k("/fantasy");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        h(true);
    }

    public void h(boolean z) {
        int T;
        try {
            if (d()) {
                W();
                boolean aH = aH();
                boolean isShown = this.m.isShown();
                this.l.setVisibility(aH ? 8 : 0);
                this.m.setVisibility(aH ? 0 : 8);
                if (aH != isShown) {
                    invalidateOptionsMenu();
                    i(aH);
                }
                aF();
                if (!aH) {
                    if (this.k) {
                        return;
                    }
                    if (!this.j || z) {
                        StringBuilder append = new StringBuilder(ServerChoice.q).append("app/").append("2.3/").append("fantasy/help/intro/");
                        append.append(e(append.toString()));
                        String sb = append.toString();
                        e().info("loading: " + sb);
                        this.k = true;
                        this.n.loadUrl(sb);
                        if (!this.f && j().e()) {
                            this.s.requestFocus();
                            this.f = this.s.hasFocus();
                        }
                        if (this.h) {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                IService F = F();
                if (F != null) {
                    TreeSet<bpe> r = F.r();
                    int size = this.r.size();
                    this.r.clear();
                    this.r.addAll(r);
                    this.p.notifyDataSetChanged();
                    this.q.a();
                    if (size == 0 && this.r.size() > 0 && (T = j().T()) < this.r.size()) {
                        this.q.setCurrentItem(T);
                    }
                }
                this.p.a();
                if (j().i()) {
                    if (z || !this.i) {
                        e(true);
                        j().L();
                    }
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    private void aF() {
        bbf X = X();
        if (X != null) {
            if (X instanceof WebViewFragment) {
                if (j().i() && j().z()) {
                    as();
                    return;
                }
                return;
            }
            if (X instanceof GameListFragment) {
                if (!j().i() || !j().z()) {
                    as();
                }
            }
        }
    }

    private void i(boolean z) {
        if (z) {
            a("Fantasy Teams");
        } else {
            a("Fantasy");
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        e().info("refreshing after onService");
        c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.sports.AbstractTabActivity
    public void e(int i) {
        try {
            e(false);
            this.i = true;
            if (i == 0) {
                this.h = true;
            } else if (i != -404 && d()) {
                a("Cannot Contact Server", bbt.pls_try_refresh_later);
            }
            h(false);
        } catch (Exception e) {
            e().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public bbf au() {
        return j().z() ? new GameListFragment(true) : new WebViewFragment(b("#022"));
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public bbf Y() {
        return this.p.a(Integer.valueOf(this.o.getCurrentItem()).intValue());
    }

    private boolean aH() {
        IService F;
        boolean z = j().i() && j().z();
        if (!z && (F = F()) != null) {
            Iterator<bpe> it = F.r().iterator();
            while (it.hasNext()) {
                TreeSet<FantasySite> k = F.k(it.next().a());
                if (k != null) {
                    Iterator<FantasySite> it2 = k.iterator();
                    while (it2.hasNext()) {
                        FantasySite next = it2.next();
                        if (next.f() || next.g()) {
                            return true;
                        }
                    }
                }
            }
        }
        return z;
    }
}
