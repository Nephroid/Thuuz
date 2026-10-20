package com.thuuz.gtv.activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.thuuz.gtv.activity.feed.FeedFragment;
import com.thuuz.gtv.activity.sports.AbstractTabActivity;
import com.viewpagerindicator.TabPageIndicator;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bdy;
import defpackage.bdz;
import defpackage.bea;
import defpackage.beb;
import defpackage.bec;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class FeedActivity extends AbstractTabActivity {
    private bec f;
    private ViewPager h;
    private int i;
    private BroadcastReceiver j;
    private View k;

    public FeedActivity() {
        super("Feed");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.feed_activity);
        this.i = b(bundle);
        this.h = (ViewPager) findViewById(bbq.pager);
        this.h.setOffscreenPageLimit(2);
        this.f = new bec(this, getSupportFragmentManager());
        this.h.setAdapter(this.f);
        TabPageIndicator tabPageIndicator = (TabPageIndicator) findViewById(bbq.indicator);
        tabPageIndicator.setViewPager(this.h, this.i);
        if (this.f.getCount() == 1) {
            tabPageIndicator.setVisibility(8);
            View findViewById = findViewById(bbq.line_separator);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
        }
        tabPageIndicator.setOnPageChangeListener(new bdy(this));
        e().info("incoming tab: " + this.i);
        f(this.i);
        FRAGS a = FRAGS.a(this.i);
        if (a != null) {
            e().info("feed view tracking: " + a.b());
            j().k(a.b());
        }
        this.k = findViewById(bbq.personalize_layout);
        setupPersonalizeButton(this.k);
        if (!j().aC()) {
            aH();
        }
    }

    private void aH() {
        if (!isFinishing() && !isDestroyed()) {
            IntentFilter intentFilter = new IntentFilter("has_prefs");
            this.j = new bdz(this);
            registerReceiver(this.j, intentFilter, "com.thuuz.permission.local.broadcast", null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.sports.AbstractTabActivity, com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            if (this.j != null) {
                unregisterReceiver(this.j);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        super.onDestroy();
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.sports.AbstractTabActivity, com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            c();
            setupPersonalizeButton(this.k);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        e().info("pausing feed activity");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        try {
            super.onNewIntent(intent);
            int i = -1;
            if (intent != null && intent.hasExtra("tabindex")) {
                i = intent.getIntExtra("tabindex", 0);
                e().info("got tab extra from intent: " + i);
                if (i >= 0) {
                    j().k(i);
                }
            }
            if (i < 0) {
                i = b((Bundle) null);
            }
            f(i);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        try {
            super.onRestoreInstanceState(bundle);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        try {
            FeedFragment Y = Y();
            if (Y != null) {
                Y.doRefresh(false);
            } else {
                e().info("no current fragment in FeedActivity");
            }
            W();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 3300:
                e().info("got login to permit fantasy");
                try {
                    g();
                    return;
                } catch (Throwable th) {
                    e().log(Level.SEVERE, "cannot handle: ", th);
                    return;
                }
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        try {
            if (j().e()) {
                aI();
            }
            if (Y() != null) {
                e().info("refreshing in onService");
                c();
            } else {
                aa().postDelayed(new bea(this), 140L);
            }
            W();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    private void aI() {
        int i = 0;
        int childCount = this.h.getChildCount();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.h.getChildAt(i2);
            if (childAt != null) {
                arrayList.add(childAt);
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            int i3 = i;
            if (it.hasNext()) {
                View view = (View) it.next();
                if (i3 > 0) {
                    view.setNextFocusLeftId(((View) arrayList.get(i3 - 1)).getId());
                    e().info("added left focus id to " + i3);
                }
                if (i3 < childCount - 1) {
                    view.setNextFocusRightId(((View) arrayList.get(i3 + 1)).getId());
                    e().info("added right focus id to " + i3);
                }
                i = i3 + 1;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i) {
        int K = i < 0 ? j().K() : i;
        if (K < 0) {
            K = FRAGS.FEED.a();
        }
        if (K >= FRAGS.values().length) {
            K = FRAGS.FEED.a();
        }
        if (this.h != null) {
            this.h.setCurrentItem(K);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public long q() {
        return 300000L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean t() {
        return true;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(String str, String str2, String str3) {
        try {
            if ("request_success".equals(str2)) {
                c(str, 0);
            }
            if ("newItemsCount".equals(str2)) {
                runOnUiThread(new beb(this, str3, str));
            } else {
                super.a(str, str2, str3);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, int i) {
        try {
            int currentItem = this.h.getCurrentItem();
            if (String.valueOf(currentItem).equals(str) && this.f.a(currentItem) != null) {
                this.f.notifyDataSetChanged();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* loaded from: classes.dex */
    public enum FRAGS {
        FEED(0, "/feed/my", "my"),
        HIGHLIGHTS(1, "/feed/highlights", "highlights");

        private final int index;
        private final String track;
        private final String typeParam;

        FRAGS(int i, String str, String str2) {
            this.index = i;
            this.track = str;
            this.typeParam = str2;
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

        public String c() {
            return this.typeParam;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.sports.AbstractTabActivity
    public void e(int i) {
        e().info("calling notify for feed pager adapter");
        this.f.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public int b(Bundle bundle) {
        int b = super.b(bundle);
        if (b < 0) {
            b = j().K();
        }
        if (b < 0) {
            b = FRAGS.FEED.a();
        }
        if (b >= FRAGS.values().length) {
            return FRAGS.FEED.a();
        }
        return b;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    /* renamed from: aF, reason: merged with bridge method [inline-methods] */
    public FeedFragment Y() {
        return this.f.a(Integer.valueOf(this.h.getCurrentItem()).intValue());
    }
}
