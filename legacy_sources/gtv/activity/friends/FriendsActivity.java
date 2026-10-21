package com.thuuz.gtv.activity.friends;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.view.Menu;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.AbstractSettings;
import com.thuuz.gtv.settings.SettingsActivity;
import com.viewpagerindicator.TabPageIndicator;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bih;
import defpackage.bii;
import defpackage.bij;
import defpackage.bik;
import defpackage.bil;
import defpackage.bim;
import defpackage.bqb;
import defpackage.bqk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class FriendsActivity extends AbstractSettings {
    private final List<bqb> f;
    private final List<bqb> g;
    private final Handler h;
    private final bqk i;
    private bil j;
    private boolean k;
    private boolean l;
    private bim m;
    private ViewPager n;
    private int o;
    private BroadcastReceiver p;
    private int q;
    private TabPageIndicator r;

    public FriendsActivity() {
        super("Friends");
        this.f = Collections.synchronizedList(new ArrayList());
        this.g = Collections.synchronizedList(new ArrayList());
        this.h = new Handler();
        this.i = new bqk(this.h);
        this.k = false;
        this.l = false;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.left_tabbed_right);
        this.n = (ViewPager) findViewById(bbq.pager);
        this.m = new bim(this, getSupportFragmentManager());
        if (j().i()) {
            this.q = FRAGS.values().length;
        } else {
            this.q = 1;
        }
        this.n.setAdapter(this.m);
        this.n.setOffscreenPageLimit(3);
        this.r = (TabPageIndicator) findViewById(bbq.indicator);
        this.r.setViewPager(this.n, this.o);
        this.r.setOnPageChangeListener(new bih(this));
        this.o = b(bundle);
        e().info("incoming tab: " + this.o);
        f(this.o);
        aO();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            this.i.a();
            if (this.p != null) {
                unregisterReceiver(this.p);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            aP();
            if (getIntent() != null && getIntent().hasExtra("sender") && !this.l) {
                f(FRAGS.FOLLOWERS.a());
                this.l = true;
            }
            c();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void aP() {
        int i = this.q;
        if (j().i()) {
            this.q = FRAGS.values().length;
        } else {
            this.q = 1;
        }
        if (i != this.q) {
            this.m.notifyDataSetChanged();
            this.r.a();
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        if (d()) {
            try {
                if (this.j != null) {
                    e().info("already refreshing; ignoring");
                } else {
                    this.j = new bil(this, null);
                    this.j.execute(new Void[0]);
                }
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("tabindex", this.n.getCurrentItem());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int aQ() {
        return this.q;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z = getSupportActionBar().getSelectedNavigationIndex() != FRAGS.ADD.a();
        if (z) {
            if (menu == null || menu.findItem(bbq.refresh_option) == null) {
                a(menu);
            }
        } else if (menu != null && menu.findItem(bbq.refresh_option) != null) {
            menu.removeItem(bbq.refresh_option);
        }
        return z;
    }

    public void aF() {
        try {
            f(FRAGS.ADD.a());
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public void aG() {
        this.m.a();
    }

    public bqk aK() {
        return this.i;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void ac() {
        runOnUiThread(new bii(this));
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void ad() {
        runOnUiThread(new bij(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aR() {
        j().q(j().aY().b());
        j().aY().a(true);
    }

    public void aL() {
        startActivity(new Intent(k(), (Class<?>) AddFbFriendsActivity.class));
    }

    public void aM() {
        startActivity(new Intent(k(), (Class<?>) SearchFriendsActivity.class));
    }

    public void aN() {
        startActivity(new Intent(k(), (Class<?>) ExpertsActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public int b(Bundle bundle) {
        int b = super.b(bundle);
        if (b == -1) {
            b = j().E();
        }
        if (b < 0) {
            return FRAGS.ADD.a();
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i) {
        try {
            this.n.setCurrentItem(i);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* loaded from: classes.dex */
    public enum FRAGS {
        ADD(0, "/friends/social"),
        FOLLOWERS(1, "/friends/followers"),
        INCOMING(2, "/friends/following");

        private final int index;
        private final String track;

        FRAGS(int i, String str) {
            this.index = i;
            this.track = str;
        }

        public int a() {
            return this.index;
        }

        public static FRAGS a(int i) {
            for (FRAGS frags : values()) {
                if (frags.index == i) {
                    return frags;
                }
            }
            return null;
        }

        public String b() {
            return this.track;
        }
    }

    protected void aO() {
        try {
            if (this.p == null) {
                e().info("registering fantasy listener");
                IntentFilter intentFilter = new IntentFilter("fantasy_sync");
                this.p = new bik(this, null);
                registerReceiver(this.p, intentFilter, "com.thuuz.permission.local.broadcast", null);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void e(int i) {
        e().info("got fantasy sync broadcast, result: " + i);
        if (i == 0) {
            if (!j().i()) {
                f(0);
            }
            aS();
        }
    }

    private void aS() {
        this.m.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#008";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public Class<? extends AbstractActivity> P() {
        return SettingsActivity.class;
    }
}
