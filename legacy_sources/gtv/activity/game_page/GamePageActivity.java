package com.thuuz.gtv.activity.game_page;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import com.actionbarsherlock.view.Menu;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.game_page.AbstractGamePageFragment;
import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.activity.sports.ShareActivity;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.data.OnlineResource;
import com.thuuz.gtv.data.OnlineResourceCategory;
import com.thuuz.gtv.settings.SettingsAccountActivity;
import com.thuuz.gtv.util.ServerChoice;
import com.viewpagerindicator.TabPageIndicator;
import defpackage.ba;
import defpackage.bbf;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbs;
import defpackage.bbt;
import defpackage.bje;
import defpackage.bjf;
import defpackage.bjg;
import defpackage.bjh;
import defpackage.bji;
import defpackage.bjj;
import defpackage.bjk;
import defpackage.bjl;
import defpackage.bmm;
import defpackage.bmn;
import defpackage.bms;
import defpackage.bpa;
import defpackage.bpu;
import defpackage.cao;
import defpackage.cbd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import org.joda.time.DateTimeConstants;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class GamePageActivity extends AbstractActivity {
    private int f;
    private GameData g;
    private bjh h;
    private boolean i;
    private boolean j;
    private String k;
    private bpu l;
    private ViewPager m;
    private bji n;
    private TabPageIndicator o;
    private final List<bjk> p;
    private View q;
    private boolean r;
    private bms s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private Boolean x;
    private ba y;
    private final LruCache<String, Bitmap> z;

    public GamePageActivity() {
        super("Game");
        this.f = -1;
        this.h = null;
        this.p = new ArrayList();
        this.r = true;
        this.t = true;
        this.z = new LruCache<>(10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
        } catch (ArrayIndexOutOfBoundsException e) {
            e().log(Level.SEVERE, "handling restore index problem by re-launching fresh: " + e);
            if (c(bundle)) {
                Intent intent = new Intent(this, (Class<?>) GamePageActivity.class);
                intent.putExtra("game", this.f);
                intent.setFlags(268435456);
                startActivity(intent);
                if (!isFinishing()) {
                    finish();
                }
            }
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        if (bundle != null) {
            d(bundle);
        }
        setContentView(bbr.left_tabbed_right);
        if (c(bundle)) {
            j().v(this.f);
            this.u = j().aK();
            this.y = new ba(j().bW(), new cao(this.z));
            this.m = (ViewPager) findViewById(bbq.pager);
            this.m.setOffscreenPageLimit(AbstractGamePageFragment.FragType.b());
            this.n = new bji(this, getSupportFragmentManager());
            this.m.setAdapter(this.n);
            this.o = (TabPageIndicator) findViewById(bbq.indicator);
            this.o.setViewPager(this.m, 0);
            this.o.setOnPageChangeListener(new bje(this));
            if (getIntent() != null && getIntent().hasExtra("tab")) {
                this.k = getIntent().getStringExtra("tab");
            }
            this.q = findViewById(bbq.line_separator);
            if (this.q != null) {
                this.q.setVisibility(8);
            }
            if (getIntent() != null && getIntent().getBooleanExtra("isNotification", Boolean.FALSE.booleanValue())) {
                e().info("game was notification: " + this.f);
                j().a("alert", "game", String.valueOf(this.f), 0L);
            }
            s();
            this.l = j().ax().a();
            this.s = new bms(this);
            Object lastCustomNonConfigurationInstance = getLastCustomNonConfigurationInstance();
            if (lastCustomNonConfigurationInstance != null && (lastCustomNonConfigurationInstance instanceof bjj)) {
                bjj bjjVar = (bjj) lastCustomNonConfigurationInstance;
                if (bjjVar.a.length() > 0) {
                    this.k = bjjVar.a;
                }
            }
            j().a("game", AbstractGamePageFragment.FragType.GAME_MOBILE.f(), String.valueOf(this.f), 0L);
        }
    }

    private boolean c(Bundle bundle) {
        this.f = getIntent().getIntExtra("game", -1);
        if (this.f == -1) {
            if (bundle != null) {
                this.f = bundle.getInt("game");
                if (this.f == 0) {
                    this.f = -1;
                }
            }
            if (this.f <= 0) {
                this.f = j().bL();
            }
            if (this.f <= 0) {
                e().info("no id for game page. bailing to dash");
                finish();
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void a(bbf bbfVar) {
        if (bbfVar != 0) {
            try {
                AbstractGamePageFragment.FragType a = AbstractGamePageFragment.FragType.a(((bjl) bbfVar).getFragTypeNum());
                if (a != null) {
                    j().a("game", a.f(), String.valueOf(this.f), 0L);
                }
            } catch (Throwable th) {
                e().log(Level.SEVERE, "Cannot track current frag: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            if (this.f <= 0) {
                e().info("no id for game page. bailing to dash");
                aV();
            } else {
                c();
                j().k("/game/" + this.f);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem resuming gameActivity: ", th);
        }
    }

    private void aU() {
        bbf a;
        if (j().e() && !this.v) {
            this.v = aD();
            if (!this.v) {
                AbstractGamePageFragment.FragType fragType = AbstractGamePageFragment.FragType.GAME_TABLET;
                int i = 0;
                Iterator<bjk> it = this.p.iterator();
                while (true) {
                    int i2 = i;
                    if (it.hasNext()) {
                        if (it.next().b == fragType && (a = this.n.a(i2)) != null && a.isVisible()) {
                            this.v = a.hasFocus();
                            if (!this.v) {
                                e().info("init focus requesting focus for: " + a);
                                a.requestFocus();
                                return;
                            }
                        }
                        i = i2 + 1;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("game", this.f);
        if (this.g != null) {
            bundle.putString("content", this.g.toString());
        }
        String a = this.n.a();
        if (a != null) {
            bundle.putString("tab", a);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        try {
            e().info("restoring instance state");
            super.onRestoreInstanceState(bundle);
            d(bundle);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void d(Bundle bundle) {
        if (bundle != null) {
            try {
                int i = bundle.getInt("game");
                int intExtra = getIntent().getIntExtra("game", -1);
                if (intExtra >= 0 && intExtra != i) {
                    e().info("intent game id: " + intExtra + " overrides saved id: " + i);
                    return;
                }
                String string = bundle.getString("content");
                if (string != null) {
                    this.g = new GameData(new JSONObject(string), j());
                }
                String string2 = bundle.getString("tab");
                if (string2 != null) {
                    this.k = string2;
                }
                aa().postDelayed(new bjf(this), 500L);
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            if (this.h != null) {
                this.h.cancel(true);
                this.h = null;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean aF() {
        /*
            r6 = this;
            r1 = 0
            com.thuuz.gtv.App r0 = r6.j()
            boolean r0 = r0.e()
            if (r0 == 0) goto L14
            com.thuuz.gtv.App r0 = r6.j()
            boolean r0 = r0.f()
        L13:
            return r0
        L14:
            com.thuuz.gtv.App r0 = r6.j()     // Catch: java.lang.Throwable -> L5e
            com.thuuz.gtv.data.Locations r0 = r0.ax()     // Catch: java.lang.Throwable -> L5e
            bpu r3 = r0.a()     // Catch: java.lang.Throwable -> L5e
            if (r3 == 0) goto L41
            boolean r0 = r3.l()     // Catch: java.lang.Throwable -> L5e
            if (r0 == 0) goto L41
            r0 = 1
        L29:
            if (r0 == 0) goto L13
            boolean r2 = r6.T()     // Catch: java.lang.Throwable -> L6e
            if (r2 == 0) goto L74
            java.lang.String r0 = "wifi"
            java.lang.Object r0 = r6.getSystemService(r0)     // Catch: java.lang.Throwable -> L70
            android.net.wifi.WifiManager r0 = (android.net.wifi.WifiManager) r0     // Catch: java.lang.Throwable -> L70
            android.net.wifi.WifiInfo r0 = r0.getConnectionInfo()     // Catch: java.lang.Throwable -> L70
            if (r0 != 0) goto L43
            r0 = r1
            goto L13
        L41:
            r0 = r1
            goto L29
        L43:
            java.lang.String r0 = r0.getSSID()     // Catch: java.lang.Throwable -> L70
            if (r0 == 0) goto L4f
            int r4 = r0.length()     // Catch: java.lang.Throwable -> L70
            if (r4 != 0) goto L51
        L4f:
            r0 = r1
            goto L13
        L51:
            bqc r1 = r3.m()     // Catch: java.lang.Throwable -> L70
            java.lang.String r1 = r1.g()     // Catch: java.lang.Throwable -> L70
            boolean r0 = r0.equals(r1)     // Catch: java.lang.Throwable -> L70
            goto L13
        L5e:
            r0 = move-exception
            r5 = r0
            r0 = r1
            r1 = r5
        L62:
            java.util.logging.Logger r2 = r6.e()
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            java.lang.String r4 = "problem: "
            r2.log(r3, r4, r1)
            goto L13
        L6e:
            r1 = move-exception
            goto L62
        L70:
            r0 = move-exception
            r1 = r0
            r0 = r2
            goto L62
        L74:
            r0 = r2
            goto L13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.activity.game_page.GamePageActivity.aF():boolean");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            switch (i) {
                case 78:
                case 291:
                    IService F = F();
                    if (F != null) {
                        F.w().g();
                        bpu a = j().ax().a();
                        if (a != null && a.l() && aF()) {
                            F.w().b();
                        }
                    }
                    c();
                    return;
                case 254:
                    if (i2 != 0) {
                        e().info("channel change result: " + i2);
                    }
                    if (intent != null) {
                        e().info("channel change result info: ");
                        Bundle extras = intent.getExtras();
                        if (extras != null) {
                            for (String str : extras.keySet()) {
                                e().info(str + ": " + extras.get(str).toString());
                            }
                            return;
                        }
                        return;
                    }
                    return;
                case 3260:
                default:
                    super.onActivityResult(i, i2, intent);
                    return;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            getSupportMenuInflater().inflate(bbs.checkin, menu);
            a(menu);
            return true;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
            return true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0010, code lost:
    
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
            com.thuuz.gtv.data.GameData r1 = r4.g     // Catch: java.lang.Throwable -> L1e
            if (r1 == 0) goto L11
            int r1 = defpackage.bbq.checkin_option     // Catch: java.lang.Throwable -> L1e
            int r2 = r5.getItemId()     // Catch: java.lang.Throwable -> L1e
            if (r1 != r2) goto L11
            r4.aK()     // Catch: java.lang.Throwable -> L1e
        L10:
            return r0
        L11:
            int r1 = defpackage.bbq.refresh_option     // Catch: java.lang.Throwable -> L1e
            int r2 = r5.getItemId()     // Catch: java.lang.Throwable -> L1e
            if (r1 != r2) goto L2a
            r1 = 1
            r4.h(r1)     // Catch: java.lang.Throwable -> L1e
            goto L10
        L1e:
            r0 = move-exception
            java.util.logging.Logger r1 = r4.e()
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "problem: "
            r1.log(r2, r3, r0)
        L2a:
            boolean r0 = super.onOptionsItemSelected(r5)
            goto L10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.activity.game_page.GamePageActivity.onOptionsItemSelected(com.actionbarsherlock.view.MenuItem):boolean");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        boolean z;
        int a;
        super.onNewIntent(intent);
        int intExtra = intent.getIntExtra("game", -1);
        if (intExtra <= -1 || this.f == intExtra) {
            z = false;
        } else {
            this.f = intExtra;
            this.g = null;
            bjh bjhVar = this.h;
            if (bjhVar != null) {
                bjhVar.cancel(true);
                this.h = null;
            }
            z = true;
        }
        if (intent.getBooleanExtra("isNotification", Boolean.FALSE.booleanValue())) {
            j().a("alert", "game", String.valueOf(this.f), 0L);
            IService F = F();
            if (F != null) {
                F.a(this.f);
            }
        }
        String stringExtra = intent.getStringExtra("tab");
        if (stringExtra != null && (a = this.n.a(stringExtra)) >= 0) {
            e(a);
        }
        if (z) {
            r();
        }
    }

    public void h(boolean z) {
        if (d() && this.f != -1) {
            boolean aK = j().aK();
            if (this.u != aK) {
                z = true;
                this.u = aK;
            }
            if (this.t && this.g != null) {
                l(false);
                if (this.k != null) {
                    g(this.k);
                }
            }
            j(z);
            W();
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        h(false);
        if (j().e() && !this.v) {
            aU();
            this.v = aD();
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        try {
            int action = keyEvent.getAction();
            switch (keyEvent.getKeyCode()) {
                case DateTimeConstants.HOURS_PER_DAY /* 24 */:
                case 113:
                case 115:
                    if (action == 0) {
                        this.i = true;
                        break;
                    } else if (action == 1) {
                        this.i = false;
                        break;
                    }
                    break;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void K() {
        super.K();
        aZ();
    }

    private void j(boolean z) {
        try {
            if (d() && !isDestroyed() && F() != null) {
                this.c = null;
                if (this.h == null) {
                    this.h = new bjh(this, z, null);
                    this.h.execute(Integer.valueOf(this.f));
                } else {
                    e().warning("refresh ignored because one is ongoing...");
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        GameData b;
        IService F = F();
        if (F != null) {
            if (aF()) {
                F.w().b();
            }
            try {
                if (this.g == null && (b = F.b(Integer.valueOf(this.f))) != null) {
                    this.g = b;
                    l(false);
                }
                c();
            } catch (Throwable th) {
                e().log(Level.SEVERE, "Problem: " + this.f, th);
                if (d()) {
                    c(bbt.unable_to_update, bbt.pls_try_again_later);
                }
            }
            try {
                if (getIntent() != null && getIntent().getBooleanExtra("isNotification", Boolean.FALSE.booleanValue())) {
                    F.a(this.f);
                }
                if (getIntent() != null && getIntent().hasExtra("share_type")) {
                    aL();
                }
            } catch (Throwable th2) {
                e().log(Level.SEVERE, "problem: ", th2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(boolean z) {
        if (this.f > -1 && !isDestroyed() && this.g != null) {
            l(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aV() {
        try {
            a(false);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        finish();
    }

    public boolean aG() {
        return this.w;
    }

    public int aH() {
        return this.o.getId();
    }

    public LruCache<String, Bitmap> aI() {
        return this.z;
    }

    private void e(int i) {
        if (!isDestroyed()) {
            try {
                this.m.setCurrentItem(i);
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    private synchronized void aW() {
        try {
            if (!isDestroyed()) {
                ArrayList arrayList = new ArrayList();
                if (j().a((Activity) this)) {
                    arrayList.add(new bjk(this, AbstractGamePageFragment.FragType.GAME_TABLET));
                } else {
                    arrayList.add(new bjk(this, AbstractGamePageFragment.FragType.GAME_MOBILE));
                    if (this.g != null) {
                        this.g.ap();
                    }
                }
                arrayList.addAll(aX());
                if (arrayList.size() > AbstractGamePageFragment.FragType.a()) {
                    ArrayList arrayList2 = new ArrayList();
                    Iterator it = new ArrayList(arrayList).iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        bjk bjkVar = (bjk) it.next();
                        if (!arrayList2.contains(bjkVar)) {
                            arrayList.remove(i);
                            e().severe("removing tab: " + bjkVar + " which is dup");
                        } else {
                            arrayList2.add(bjkVar);
                        }
                        i++;
                    }
                    if (arrayList.size() > AbstractGamePageFragment.FragType.a()) {
                        e().log(Level.SEVERE, "Loading too many fragments");
                    }
                }
                this.p.clear();
                this.p.addAll(arrayList);
                this.n.notifyDataSetChanged();
                this.o.a();
                if (this.q != null) {
                    this.q.setVisibility(0);
                }
                if (this.k != null && g(this.k) >= 0) {
                    this.k = null;
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot reset game tabs: ", th);
        }
    }

    public int g(String str) {
        int a = this.n.a(str);
        if (a >= 0) {
            this.m.setCurrentItem(a);
        }
        return a;
    }

    private List<bjk> aX() {
        ArrayList<bjk> arrayList = new ArrayList<>();
        if (this.g != null) {
            GameData.GAME_STATUS j = this.g.j();
            switch (bjg.a[j.ordinal()]) {
                case 1:
                    if (this.g.a(j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.PREVIEW).size() > 0) {
                        arrayList.add(new bjk(this, AbstractGamePageFragment.FragType.PREVIEW));
                        break;
                    }
                    break;
                case 3:
                    if (this.g.a(j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.RECAP).size() > 0) {
                        arrayList.add(new bjk(this, AbstractGamePageFragment.FragType.RECAP));
                        break;
                    }
                    break;
            }
            a(arrayList);
            if (j != GameData.GAME_STATUS.UPCOMING) {
                arrayList.add(new bjk(this, AbstractGamePageFragment.FragType.GRAPH));
            }
        }
        return arrayList;
    }

    private void a(ArrayList<bjk> arrayList) {
        if (this.g.a(j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.STATS).size() > 0) {
            arrayList.add(new bjk(this, AbstractGamePageFragment.FragType.STATS));
        } else if (this.g.a(j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.SUMMARY).size() > 0) {
            arrayList.add(new bjk(this, AbstractGamePageFragment.FragType.STATS));
        }
        int i = 0;
        Iterator<OnlineResource> it = this.g.a(j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.DYN_TAB).iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                arrayList.add(new bjk(this, AbstractGamePageFragment.FragType.DYNAMIC, it.next().i().toUpperCase(), i2));
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public boolean aJ() {
        return this.i;
    }

    protected void aK() {
        try {
            if (j().i()) {
                aL();
            } else {
                a(SettingsAccountActivity.SOURCE.SOURCE_GAME_PAGE_THUUZES, 3256);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void aL() {
        try {
            if (this.g == null) {
                e().warning("cannot launch sharing because content not filled yet.");
            } else {
                a(ShareActivity.SHARE_TYPE.SHARE_TYPE_GAME, this.f, this.g.K(), this.g.W());
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public void aM() {
        e().info("setSpoilersOk");
        this.j = true;
        k(false);
    }

    public boolean aN() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public long q() {
        if (this.g == null || this.g.al() || aY()) {
            return 60000L;
        }
        if (!this.g.ak()) {
            return 43200000L;
        }
        return 300000L;
    }

    private boolean aY() {
        return j().a((Activity) this) && !j().g();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean t() {
        return true;
    }

    private void aZ() {
        try {
            if (this.g != null) {
                a(this.g.K());
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Error setting filter title: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public Class<? extends AbstractActivity> P() {
        return GuideActivity.class;
    }

    public bmm a(View view, Integer num) {
        bmm bmmVar = (bmm) super.a(view, num, aE());
        try {
            if (this.g != null && bmmVar != null) {
                this.g.a(this).a(bmmVar, view, this.g);
                if (j().e()) {
                    view.setPadding(0, 0, 0, 0);
                    view.requestLayout();
                }
            }
            if (this.g != null) {
                this.s.a(this, bmmVar, this.g);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        return bmmVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public bmn a(GameData gameData) {
        bmn a = super.a(gameData);
        a.a(false);
        a.b(aN());
        return a;
    }

    public int aO() {
        return this.f;
    }

    public boolean aP() {
        IService F;
        return (!aF() || (F = F()) == null || F.w().d()) ? false : true;
    }

    public boolean a(bpa bpaVar) {
        IService F = F();
        if (F == null) {
            return false;
        }
        cbd w = F.w();
        if (w.d()) {
            return true;
        }
        Iterator<String> it = bpaVar.j().iterator();
        while (it.hasNext()) {
            if (w.c(it.next())) {
                return true;
            }
        }
        return false;
    }

    private void l(boolean z) {
        try {
            aZ();
            if (d()) {
                aW();
                this.n.a(z);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public String a(int i, bjk bjkVar) {
        OnlineResource onlineResource;
        try {
            if (this.g != null) {
                AbstractGamePageFragment.FragType a = AbstractGamePageFragment.FragType.a(i);
                switch (bjg.b[a.ordinal()]) {
                    case 3:
                        List<OnlineResource> a2 = this.g.a(j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.PREVIEW);
                        return a(a2.size() > 0 ? a2.get(0) : null);
                    case 4:
                        List<OnlineResource> a3 = this.g.a(j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.RECAP);
                        return a(a3.size() > 0 ? a3.get(0) : null);
                    case 5:
                        List<OnlineResource> a4 = this.g.a(j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.STATS);
                        if (a4.size() > 0) {
                            onlineResource = a4.get(0);
                        } else {
                            List<OnlineResource> a5 = this.g.a(j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.SUMMARY);
                            onlineResource = a5.size() > 0 ? a5.get(0) : null;
                        }
                        return a(onlineResource);
                    case 6:
                        TreeSet<Integer> x = j().x();
                        return a(this.g.i(), this.g.d(x), this.g.c(x));
                    case 7:
                        List<OnlineResource> a6 = this.g.a(j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.DYN_TAB);
                        int size = a6.size();
                        if (size > 0) {
                            OnlineResource onlineResource2 = a6.get(0);
                            if (bjkVar != null && size > bjkVar.c) {
                                onlineResource2 = a6.get(bjkVar.c);
                            }
                            return a(onlineResource2);
                        }
                        break;
                }
                e().severe("unexpected game page tab: " + a);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return null;
    }

    private String a(OnlineResource onlineResource) {
        if (onlineResource == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(onlineResource.g());
        if (sb.charAt(0) == '/') {
            String substring = sb.substring(1);
            sb = new StringBuilder(ServerChoice.q);
            sb.append(substring);
        }
        sb.append(e(sb.toString()));
        return sb.toString();
    }

    public GameData aQ() {
        return this.g;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public bbf Y() {
        return this.n.a(Integer.valueOf(this.m.getCurrentItem()).intValue());
    }

    public boolean aR() {
        return this.r;
    }

    public void i(boolean z) {
        this.r = z;
    }

    @Override // android.support.v4.app.FragmentActivity
    public Object onRetainCustomNonConfigurationInstance() {
        return new bjj(this);
    }

    public Intent aS() {
        PackageManager packageManager;
        boolean z = true;
        try {
            packageManager = getPackageManager();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        if (packageManager == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("spm://com.sling.slingPlayer"));
        intent.putExtra("sling-partner", "thuuz");
        boolean z2 = intent.resolveActivity(packageManager) != null;
        if (!z2) {
            intent.setData(Uri.parse("spm://com.slingmedia.slingPlayerTablet"));
            z2 = intent.resolveActivity(packageManager) != null;
        }
        if (z2) {
            z = z2;
        } else {
            intent.setData(Uri.parse("spm://com.slingmedia.slingPlayerKindle"));
            if (intent.resolveActivity(packageManager) == null) {
                z = false;
            }
        }
        if (z) {
            return intent;
        }
        return null;
    }

    public boolean aT() {
        try {
            if (this.x == null) {
                this.x = Boolean.valueOf(aS() != null);
            }
            return this.x.booleanValue();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
            return false;
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public ba aE() {
        return this.y;
    }
}
