package com.thuuz.gtv.activity.fantasy;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.fragments.GameListFragment;
import com.viewpagerindicator.TabPageIndicator;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbs;
import defpackage.bfn;
import defpackage.bfo;
import defpackage.bfp;
import defpackage.bfq;
import defpackage.bfr;
import defpackage.bfx;
import defpackage.bpe;
import defpackage.bpg;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class FantasyTeamActivity extends AbstractLoginRequiredActivity implements bfx {
    private int f;
    private bfp g;
    private ViewPager h;
    private bfq i;
    private boolean j;
    private bfr k;
    private int l;

    /* loaded from: classes.dex */
    public enum FANT_TABS {
        LEAGUE_TAB(0),
        PLAYER_TAB(1),
        SMACK_TAB(2);

        private int tabIndex;

        FANT_TABS(int i) {
            this.tabIndex = i;
        }
    }

    public FantasyTeamActivity() {
        super("Fantasy Team");
        this.f = -1;
        this.l = -1;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.left_tabbed_right);
        if (this.f == -1) {
            this.f = getIntent().getIntExtra("team_id", -1);
        }
        if (this.f == -1) {
            if (bundle != null) {
                this.f = bundle.getInt("team_id", -1);
            }
            if (this.f == -1) {
                this.f = j().F();
            }
            if (this.f == -1) {
                e().info("no TEAM_ID for fantasy team page. bailing.");
                finish();
                return;
            }
        }
        j().j(this.f);
        this.h = (ViewPager) findViewById(bbq.pager);
        this.g = new bfp(this, getSupportFragmentManager());
        this.h.setAdapter(this.g);
        this.h.setOffscreenPageLimit(2);
        int b = b(bundle);
        TabPageIndicator tabPageIndicator = (TabPageIndicator) findViewById(bbq.indicator);
        tabPageIndicator.setViewPager(this.h, b);
        tabPageIndicator.setOnPageChangeListener(new bfn(this));
        aG();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            bundle.putInt("team_id", this.f);
            bundle.putInt("tabindex", this.h.getCurrentItem());
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        try {
            e().info("restoring...");
            super.onRestoreInstanceState(bundle);
            c(bundle);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void c(Bundle bundle) {
        if (bundle != null) {
            try {
                this.f = bundle.getInt("team_id");
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            if (this.k != null) {
                unregisterReceiver(this.k);
            }
            if (this.g != null && bfp.a(this.g) != null) {
                bfp.a(this.g).stopFeedCountPolling();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        try {
            int intExtra = intent.getIntExtra("team_id", -1);
            if (intExtra != -1 && intExtra != this.f) {
                this.f = intExtra;
                this.g.c();
            }
            int currentItem = this.h.getCurrentItem();
            int a = a(currentItem, getIntent());
            if (currentItem != a) {
                this.h.setCurrentItem(a);
            } else {
                c();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        IService F = F();
        if (F != null) {
            if (this.g.b() == null) {
                e().info("onService sets refresh timer");
                aa().postDelayed(new bfo(this), 25L);
            } else {
                e().info("refreshing from onService");
                c();
            }
            bpg l = F.l(this.f);
            if (l == null) {
                j().L();
            }
            if (l != null && l.f()) {
                this.h.setCurrentItem(FANT_TABS.LEAGUE_TAB.tabIndex);
            }
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        try {
            IService F = F();
            if (!d()) {
                e().info("skipping refresh: inactive");
                return;
            }
            if (F == null) {
                e().info("skipping refresh: no service");
                return;
            }
            StringBuilder sb = new StringBuilder();
            bpg l = F.l(this.f);
            if (l != null) {
                String e = l.e();
                if (e.length() == 0) {
                    e = "(unnamed)";
                }
                sb.append(e);
                this.l = l.b();
                bpe j = F.j(this.l);
                if (j != null) {
                    sb.append(" - ");
                    sb.append(j.b());
                }
                a(sb.toString());
            }
            this.g.a();
            if (this.i == null) {
                if (!this.j) {
                    this.i = new bfq(this);
                    this.i.execute(new Void[0]);
                }
            } else {
                e().info("skipping update because ongoing");
            }
            W();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        e().info("refreshing from resume");
        c();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        try {
            MenuInflater supportMenuInflater = getSupportMenuInflater();
            supportMenuInflater.inflate(bbs.invite_rivals, menu);
            supportMenuInflater.inflate(bbs.bell, menu);
            return true;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
            return true;
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == bbq.invite_rivals_option) {
            Intent intent = new Intent(this, (Class<?>) FantasyInviteActivity.class);
            intent.putExtra("teamId", this.f);
            intent.putExtra("compId", this.l);
            startActivity(intent);
            return true;
        }
        if (menuItem.getItemId() == bbq.bell_option) {
            IService F = F();
            if (F != null) {
                bpg l = F.l(this.f);
                if (l != null) {
                    a(this.f, l.e());
                    return true;
                }
                e().warning("no team in bell option for mTeamId: " + this.f);
            } else {
                e().warning("no service in bell option");
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public int b(Bundle bundle) {
        return a(super.b(bundle), getIntent());
    }

    private int a(int i, Intent intent) {
        int intExtra = (intent == null || !intent.hasExtra("tabindex")) ? i : intent.getIntExtra("tabindex", 1);
        if (intExtra < 0 || intExtra >= FANT_TABS.values().length) {
            return 1;
        }
        return intExtra;
    }

    private void aG() {
        try {
            if (this.k == null) {
                e().info("registering fantasy listener");
                IntentFilter intentFilter = new IntentFilter("fantasy_sync");
                this.k = new bfr(this, null);
                registerReceiver(this.k, intentFilter, "com.thuuz.permission.local.broadcast", null);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public Class<? extends AbstractActivity> P() {
        return FantasyActivity.class;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View currentFocus = getCurrentFocus();
        if (currentFocus == null || !(currentFocus instanceof WebView)) {
            return super.dispatchKeyEvent(keyEvent);
        }
        int keyCode = keyEvent.getKeyCode();
        for (int i : a) {
            if (keyCode == i) {
                if (keyEvent.getAction() != 1) {
                    return true;
                }
                try {
                    Integer valueOf = Integer.valueOf(this.h.getCurrentItem());
                    int intValue = valueOf.intValue();
                    if (keyCode == 22 && valueOf.intValue() < this.h.getChildCount() - 1) {
                        intValue = valueOf.intValue() + 1;
                    }
                    if (keyCode == 21 && valueOf.intValue() > 0) {
                        intValue = valueOf.intValue() - 1;
                    }
                    if (valueOf.intValue() == intValue) {
                        return true;
                    }
                    this.h.setCurrentItem(intValue);
                    return true;
                } catch (Throwable th) {
                    e().log(Level.SEVERE, "Cannot handle: ", th);
                    return true;
                }
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    /* renamed from: aF, reason: merged with bridge method [inline-methods] */
    public GameListFragment au() {
        return new GameListFragment(true);
    }
}
