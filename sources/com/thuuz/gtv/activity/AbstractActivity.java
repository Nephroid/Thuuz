package com.thuuz.gtv.activity;

import android.R;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDiskIOException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.http.EventHandler;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Telephony;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.media.TransportMediator;
import android.support.v4.util.TimeUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ActivityChooserView;
import com.facebook.FacebookRequestError;
import com.facebook.internal.NativeProtocol;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.activity.fantasy.FantasyActivity;
import com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity;
import com.thuuz.gtv.activity.fantasy.FantasyImportSiteActivity;
import com.thuuz.gtv.activity.fantasy.FantasySiteSelectActivity;
import com.thuuz.gtv.activity.fantasy.FantasyTeamActivity;
import com.thuuz.gtv.activity.fantasy.PlayerActivity;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamDetailActivity;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity;
import com.thuuz.gtv.activity.fragments.GameListFragment;
import com.thuuz.gtv.activity.friends.FriendsActivity;
import com.thuuz.gtv.activity.friends.UserProfileActivity;
import com.thuuz.gtv.activity.friends.WebviewActivity;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity;
import com.thuuz.gtv.activity.nobar.MP4VideoActivity;
import com.thuuz.gtv.activity.nobar.MessageActivity;
import com.thuuz.gtv.activity.nobar.SplashActivity;
import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.activity.sports.ShareActivity;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.settings.FeedbackActivity;
import com.thuuz.gtv.settings.SettingsAccountActivity;
import com.thuuz.gtv.settings.SettingsActivity;
import com.thuuz.gtv.settings.SettingsLoginActivity;
import com.thuuz.gtv.settings.SettingsSocialRegisterActivity;
import com.thuuz.gtv.settings.SettingsSportsSelectActivity;
import com.thuuz.gtv.settings.WebViewFragment;
import com.thuuz.gtv.util.ServerChoice;
import defpackage.ba;
import defpackage.bbf;
import defpackage.bbk;
import defpackage.bbn;
import defpackage.bbp;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbs;
import defpackage.bbt;
import defpackage.bcw;
import defpackage.bcx;
import defpackage.bcy;
import defpackage.bcz;
import defpackage.bda;
import defpackage.bdb;
import defpackage.bdc;
import defpackage.bdd;
import defpackage.bde;
import defpackage.bdf;
import defpackage.bdg;
import defpackage.bdh;
import defpackage.bdi;
import defpackage.bdj;
import defpackage.bdk;
import defpackage.bdm;
import defpackage.bdn;
import defpackage.bdo;
import defpackage.bdp;
import defpackage.bdq;
import defpackage.bdr;
import defpackage.bfx;
import defpackage.bmc;
import defpackage.bmd;
import defpackage.bmh;
import defpackage.bmm;
import defpackage.bmn;
import defpackage.bpp;
import defpackage.bpr;
import defpackage.bpv;
import defpackage.bpx;
import defpackage.bqn;
import defpackage.bqy;
import defpackage.cat;
import defpackage.caz;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public abstract class AbstractActivity extends SherlockFragmentActivity {
    private boolean A;
    private View B;
    private ActionBarDrawerToggle C;
    private DrawerLayout D;
    private ExpandableListView E;
    private bmd F;
    private Map<Integer, Integer> G;
    private AsyncTask<Void, Void, Integer> H;
    private boolean J;
    protected Date c;
    private AbstractActivity m;
    private boolean n;
    private IService o;
    private ServiceConnection p;
    private Timer q;
    private boolean t;
    private int u;
    private Dialog v;
    private int x;
    private final String y;
    private BroadcastReceiver z;
    private static final bpx f = new bpx("my_sports", -1, "My Sports Fake Sport", 0, "at", 0);
    private static final bpx g = new bpx("my_teams", -2, "My Teams Fake Sport", 0, "at", 0);
    private static final bpx h = new bpx("friends_games", -5, "My Friends Games Fake Sport", 0, "at", 0);
    private static final bpx i = new bpx("all_sports", -7, "All Sports Fake Sport", 0, "at", 0);
    private static final bpx j = new bpx("off_season", -8, "Off Season Fake Sport", 0, "at", 0);
    private static final bpx k = new bpx("all_sports", -9, "Other Sports Fake Sport", 0, "at", 0);
    public static final int[] a = {21, 22};
    public static final Pattern b = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", 2);
    private static final Object l = new Object();
    private boolean r = false;
    private boolean s = false;
    private final Set<bbk> w = Collections.synchronizedSet(new HashSet());
    private final Handler I = new Handler();
    public final int d = 2000;
    public final int e = 2001;
    private String K = getClass().getSimpleName() + ":thuuz";

    public abstract void a(Bundle bundle);

    public abstract void c();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractActivity(String str) {
        this.y = str;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this instanceof AbstractNoActionBarActivity) {
            requestWindowFeature(1);
        }
        this.m = this;
        try {
            a();
            WindowManager windowManager = getWindowManager();
            if (windowManager != null) {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                defaultDisplay.getMetrics(displayMetrics);
                j().a(displayMetrics.widthPixels, displayMetrics.heightPixels);
            }
        } catch (Throwable th) {
            c("Initialization Problem", "This app install seems to be damaged. Please reinstall from a trusted source like Google Play.");
            e().log(Level.SEVERE, "problem initializing activity; warned user: ", th);
        }
        try {
            a(bundle);
        } catch (Throwable th2) {
            if (caz.a(th2)) {
                a(th2);
            } else {
                e().log(Level.SEVERE, "problem in doCreate: ", th2);
            }
        }
        aA();
        if (!isDestroyed() && !isFinishing()) {
            aF();
        }
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        try {
            if (j().e() && a(keyEvent) && a(keyEvent, false)) {
                return true;
            }
            return super.dispatchKeyEvent(keyEvent);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
            return false;
        }
    }

    private void aF() {
        new Thread(new bcw(this)).start();
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onPrepareOptionsMenu(Menu menu) {
        try {
            if (this.D != null) {
                boolean ax = ax();
                for (int i2 = 0; i2 < menu.size(); i2++) {
                    menu.getItem(i2).setVisible(!ax);
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        try {
            super.onConfigurationChanged(configuration);
            if (this.C != null) {
                this.C.onConfigurationChanged(configuration);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle onConfigurationChanged: ", th);
        }
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        try {
            super.onPostCreate(bundle);
            if (this.C != null) {
                this.C.syncState();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem in AbstractActivity.onPostCreate(): ", th);
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        bbf X;
        super.onNewIntent(intent);
        if (at() && (X = X()) != null && (X instanceof WebViewFragment)) {
            e().info("onNewIntent calling for right sidebar frag refresh (forced)");
            X.doRefresh(true);
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        try {
            if (ax()) {
                aw();
            } else {
                super.onBackPressed();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem w/ back, finishing: ", th);
            finish();
        }
    }

    public void a() {
        if ((getIntent().getFlags() & AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START) != 0) {
            Class aE = j().aE();
            if (!getClass().equals(aE)) {
                e().info("changing activity because of history launch");
                startActivity(new Intent(this, (Class<?>) aE));
                finish();
            }
        }
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.t = true;
        try {
            if (this.q != null) {
                this.q.cancel();
            }
            aj();
            if (this.v != null && this.v.isShowing()) {
                this.v.dismiss();
            }
            if (this.z != null) {
                unregisterReceiver(this.z);
            }
            if (this.w.size() > 0) {
                Iterator it = new HashSet(this.w).iterator();
                while (it.hasNext()) {
                    ((bbk) it.next()).a(this);
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        try {
            super.onDestroy();
        } catch (Throwable th2) {
            e().log(Level.SEVERE, "problem: ", th2);
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            this.n = true;
            this.J = false;
            super.onResume();
            if (!H() && aK() && F() == null) {
                aJ();
            }
            if (G()) {
                j().b(getClass().getName());
            }
            if (j().e() && !this.A) {
                this.A = aD();
                if (!this.A) {
                    View childAt = ((ViewGroup) findViewById(R.id.content)).getChildAt(0);
                    if (childAt != null) {
                        if (!childAt.hasFocus()) {
                            e().info("requesting focus for content view");
                            childAt.requestFocus();
                        }
                        aa().postDelayed(new bdh(this), 500L);
                    }
                } else {
                    return;
                }
            }
            W();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.J = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.n = true;
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        this.n = false;
        super.onStop();
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.app.Activity
    public void setContentView(int i2) {
        super.setContentView(i2);
        try {
            K();
            ActionBarSherlock sherlock = getSherlock();
            if (aG()) {
                sherlock.setProgressBarIndeterminateVisibility(false);
            }
            as();
        } catch (Throwable th) {
            e().log(Level.WARNING, "cannot hide progress spinner: ", th);
        }
    }

    private boolean aG() {
        try {
            ActionBarSherlock sherlock = getSherlock();
            if (sherlock == null || !sherlock.hasFeature(5)) {
                return false;
            }
            return !AbstractNoActionBarActivity.class.isAssignableFrom(getClass());
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
            return false;
        }
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            a(menu);
            return true;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b() {
        try {
            Resources resources = getResources();
            if (resources != null) {
                return 2 == resources.getConfiguration().orientation;
            }
            return false;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
            return false;
        }
    }

    public boolean d() {
        boolean z;
        try {
            z = ((PowerManager) getSystemService("power")).isScreenOn();
        } catch (Throwable th) {
            z = true;
        }
        return this.n && z;
    }

    public Logger e() {
        Logger logger = Logger.getLogger(this.K);
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    public void f() {
        startActivity(new Intent(this, (Class<?>) FantasyActivity.class));
    }

    public void g() {
        startActivity(new Intent(this, (Class<?>) FantasyCompetitionsActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i2) {
        Intent intent = new Intent(this, (Class<?>) FantasySiteSelectActivity.class);
        intent.putExtra("competition_id", i2);
        startActivity(intent);
    }

    public void a(int i2, int i3) {
        Intent intent = new Intent(this, (Class<?>) FantasyImportSiteActivity.class);
        intent.putExtra("competition_id", i2);
        intent.putExtra("site_id", i3);
        startActivity(intent);
    }

    public void a(int i2, String str) {
        Intent intent = new Intent(this, (Class<?>) SettingsFantasyTeamDetailActivity.class);
        intent.putExtra("team_id", i2);
        intent.putExtra("team_name", str);
        startActivity(intent);
    }

    public void b(int i2) {
        Intent intent = new Intent(this, (Class<?>) FantasyTeamActivity.class);
        intent.putExtra("team_id", i2);
        startActivity(intent);
    }

    public void b(int i2, String str) {
        Intent intent = new Intent(this, (Class<?>) PlayerActivity.class);
        intent.putExtra("playerId", i2);
        intent.putExtra("params", str);
        startActivity(intent);
    }

    public void h() {
        Intent intent = new Intent(this, (Class<?>) FeedbackActivity.class);
        intent.addFlags(131072);
        startActivity(intent);
    }

    public void i() {
        startActivity(new Intent(this, (Class<?>) SettingsActivity.class));
    }

    public void a(ShareActivity.SHARE_TYPE share_type, int i2, String str, String str2) {
        try {
            Intent intent = new Intent(k(), (Class<?>) ShareActivity.class);
            intent.putExtra("share_type", share_type.a());
            intent.putExtra("share_game_id", i2);
            intent.putExtra("share_game_title", str);
            intent.putExtra("share_game_link", str2);
            startActivityForResult(intent, 3260);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem launching share page: ", th);
        }
    }

    public App j() {
        return (App) getApplication();
    }

    public AbstractActivity k() {
        return this.m;
    }

    public bmh a(View view, Integer num, ba baVar) {
        bmh bmhVar;
        Throwable th;
        OutOfMemoryError e;
        try {
            bmhVar = (bmm) view.getTag();
            try {
                if (bmhVar == null) {
                    e().warning("renderGameView() - NULL ViewHolder.");
                    bmhVar = null;
                } else {
                    IService F = F();
                    if (F == null) {
                        bmhVar = null;
                    } else {
                        GameData b2 = F.b(num);
                        if (b2 == null) {
                            bmhVar = null;
                        } else {
                            bmn a2 = a(b2);
                            a2.a(baVar);
                            bmhVar = a2.a(view);
                        }
                    }
                }
            } catch (OutOfMemoryError e2) {
                e = e2;
                System.gc();
                System.gc();
                a(e);
                return bmhVar;
            } catch (Throwable th2) {
                th = th2;
                e().log(Level.SEVERE, "AbstractActivity.renderGameView() cannot handle content: ", th);
                return bmhVar;
            }
        } catch (OutOfMemoryError e3) {
            bmhVar = null;
            e = e3;
        } catch (Throwable th3) {
            bmhVar = null;
            th = th3;
        }
        return bmhVar;
    }

    public bmn a(GameData gameData) {
        return gameData.a(this);
    }

    public void a(int i2, bpp bppVar) {
        f(bppVar.a());
        j().a("onlineresource", "watch_highlight", "games_list", i2);
    }

    public View a(ViewGroup viewGroup) {
        return ((LayoutInflater) k().getSystemService("layout_inflater")).inflate(bbr.game_layout, viewGroup, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View l() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            return actionBar.getCustomView();
        }
        return null;
    }

    public void m() {
    }

    public void n() {
        try {
            if (!isDestroyed()) {
                if (j().S()) {
                    e().info("service already started. proceeding to bind...");
                } else {
                    aH();
                }
                this.r = true;
                this.p = new cat(this);
                bindService(new Intent(k(), (Class<?>) ThuuzService.class), this.p, 1);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "cannot start service: ", th);
        }
    }

    private void aH() {
        int i2 = 0;
        while (!j().S() && i2 < 50) {
            try {
                j().a();
                i2++;
                Thread.sleep(40L);
            } catch (InterruptedException e) {
            } catch (Throwable th) {
                e().log(Level.SEVERE, "Cannot wake/sleep for binding service: ", th);
            }
        }
        if (i2 >= 50) {
            e().warning("Cannot get service start after " + i2 + " wake calls, waiting a total secs: " + (((float) (40 * i2)) / 1000.0f));
        }
    }

    public boolean o() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0075 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0081 A[Catch: Throwable -> 0x0085, TRY_LEAVE, TryCatch #2 {Throwable -> 0x0085, blocks: (B:32:0x0077, B:37:0x0081), top: B:31:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.Class r9) {
        /*
            r8 = this;
            r2 = 0
            r0 = 1
            r1 = 0
            if (r9 != 0) goto L16
            java.util.logging.Logger r3 = r8.e()     // Catch: java.lang.Throwable -> L54
            java.lang.String r4 = "null target in launch recent"
            r3.severe(r4)     // Catch: java.lang.Throwable -> L54
            com.thuuz.gtv.App r3 = r8.j()     // Catch: java.lang.Throwable -> L54
            java.lang.Class r9 = r3.aF()     // Catch: java.lang.Throwable -> L54
        L16:
            java.lang.Class<com.thuuz.gtv.activity.sports.AbstractTabActivity> r3 = com.thuuz.gtv.activity.sports.AbstractTabActivity.class
            boolean r3 = r3.isAssignableFrom(r9)     // Catch: java.lang.Throwable -> L54
            if (r3 != 0) goto L50
            r4 = r0
        L1f:
            if (r4 == 0) goto Lc0
            java.lang.Class r3 = r8.b(r9)     // Catch: java.lang.Throwable -> Lb3
        L25:
            com.thuuz.gtv.IService r5 = r8.F()     // Catch: java.lang.Throwable -> Lb7
            if (r4 == 0) goto L34
            if (r5 == 0) goto L34
            boolean r4 = r5.a(r3)     // Catch: java.lang.Throwable -> Lb7
            if (r4 != 0) goto L52
        L33:
            r4 = r0
        L34:
            android.support.v4.app.TaskStackBuilder r0 = android.support.v4.app.TaskStackBuilder.create(r8)     // Catch: java.lang.Throwable -> Lb7
            if (r4 == 0) goto L44
            if (r3 == 0) goto L44
            android.content.Intent r1 = new android.content.Intent     // Catch: java.lang.Throwable -> Lb7
            r1.<init>(r8, r3)     // Catch: java.lang.Throwable -> Lb7
            r0.addNextIntent(r1)     // Catch: java.lang.Throwable -> Lb7
        L44:
            android.content.Intent r1 = new android.content.Intent     // Catch: java.lang.Throwable -> Lb7
            r1.<init>(r8, r9)     // Catch: java.lang.Throwable -> Lb7
            r0.addNextIntent(r1)     // Catch: java.lang.Throwable -> Lbc
            r0.startActivities()     // Catch: java.lang.Throwable -> Lbc
        L4f:
            return
        L50:
            r4 = r1
            goto L1f
        L52:
            r0 = r1
            goto L33
        L54:
            r0 = move-exception
            r3 = r1
            r1 = r2
        L57:
            java.util.logging.Logger r4 = r8.e()
            java.util.logging.Level r5 = java.util.logging.Level.WARNING
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Cannot launch stack, will attempt simple launch: "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            r4.log(r5, r6)
            if (r3 == 0) goto L7f
            if (r2 == 0) goto L7f
            android.content.Intent r3 = new android.content.Intent     // Catch: java.lang.Throwable -> L85
            r3.<init>(r8, r2)     // Catch: java.lang.Throwable -> L85
            r8.startActivity(r3)     // Catch: java.lang.Throwable -> L85
        L7f:
            if (r1 == 0) goto L4f
            r8.startActivity(r1)     // Catch: java.lang.Throwable -> L85
            goto L4f
        L85:
            r1 = move-exception
            java.util.logging.Logger r1 = r8.e()
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "failed launch failover, will attempt simple launch: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            r1.log(r2, r0)
            com.thuuz.gtv.App r0 = r8.j()
            java.lang.Class r0 = r0.aF()
            android.content.Intent r1 = new android.content.Intent
            r1.<init>(r8, r0)
            r8.startActivity(r1)
            goto L4f
        Lb3:
            r0 = move-exception
            r1 = r2
            r3 = r4
            goto L57
        Lb7:
            r0 = move-exception
            r1 = r2
            r2 = r3
            r3 = r4
            goto L57
        Lbc:
            r0 = move-exception
            r2 = r3
            r3 = r4
            goto L57
        Lc0:
            r3 = r2
            goto L25
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.activity.AbstractActivity.a(java.lang.Class):void");
    }

    public Class<? extends AbstractActivity> b(Class cls) {
        if (cls == null) {
            return j().aF();
        }
        if (AbstractSettings.class.isAssignableFrom(cls)) {
            return SettingsActivity.class;
        }
        if (bfx.class.isAssignableFrom(cls)) {
            return FantasyActivity.class;
        }
        return j().aF();
    }

    public void expandWidthByDevice(View view) {
        ViewGroup.LayoutParams layoutParams;
        if (view != null) {
            try {
                if (!j().a((Activity) this) && (layoutParams = view.getLayoutParams()) != null) {
                    layoutParams.width = -1;
                }
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(boolean z) {
        Class<? extends AbstractActivity> aF = j().aF();
        IService F = F();
        if (F == null || !F.a(aF)) {
            startActivity(j().b((Activity) this));
        }
        if (F == null || !F.a(GuideActivity.class)) {
            Intent intent = new Intent(getApplicationContext(), (Class<?>) GuideActivity.class);
            if (z) {
                intent.putExtra("isNotification", Boolean.TRUE);
            }
            startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Intent intent) {
        int b2 = b(intent);
        if (b2 == -1) {
            e().info("cannot launch notification game because missing ID");
            return;
        }
        boolean z = false;
        int intExtra = intent.getIntExtra("game", -1);
        try {
            int parseInt = Integer.parseInt(intent.getAction());
            if (intExtra != -1 && intExtra == parseInt) {
                z = true;
            }
        } catch (Throwable th) {
        }
        Intent a2 = a(b2, z);
        String str = "launching game: " + b2;
        e().info(z ? str + " which is notification" : str);
        String stringExtra = intent.getStringExtra("tab");
        if (stringExtra != null) {
            a2.putExtra("tab", stringExtra);
        }
        startActivity(a2);
    }

    private Intent a(int i2, boolean z) {
        Intent b2 = b(Integer.valueOf(i2));
        if (z) {
            b2.putExtra("isNotification", Boolean.TRUE);
        }
        b2.addFlags(402653184);
        return b2;
    }

    protected int b(Intent intent) {
        int intExtra = intent.getIntExtra("game", -1);
        if (intExtra != -1) {
            return intExtra;
        }
        try {
            return Integer.parseInt(intent.getAction());
        } catch (Throwable th) {
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Intent p() {
        int b2;
        Intent intent = new Intent(k(), (Class<?>) SplashActivity.class);
        intent.setFlags(1073741824);
        Intent intent2 = getIntent();
        if (intent2 != null && (b2 = b(intent2)) != -1) {
            intent.putExtra("game", b2);
            intent.putExtra("isNotification", Boolean.TRUE);
        }
        return intent;
    }

    protected String a(String str, String str2) {
        String str3 = "";
        IService F = F();
        if (F != null) {
            str3 = F.a(str + "_" + str2);
        }
        if (str3.length() == 0) {
            return str2 + " " + str;
        }
        return str3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String a(String str, String str2, IService iService) {
        bpr a2;
        String str3 = "";
        if (iService != null && (a2 = iService.a(str, str2)) != null) {
            str3 = j().a((Activity) this) ? a2.f() : a2.h();
        }
        if (str3 == null || str3.length() == 0) {
            str3 = a(str, str2);
        }
        if (str3 == null) {
            return "";
        }
        return str3;
    }

    public bmm a(View view, boolean z) {
        return new bmm(view, z);
    }

    public long q() {
        if (j().e()) {
            return 300000L;
        }
        NetworkInfo networkInfo = ((ConnectivityManager) getSystemService("connectivity")).getNetworkInfo(1);
        boolean z = false;
        if (networkInfo != null) {
            z = networkInfo.isConnectedOrConnecting();
        }
        return z ? 300000L : 43200000L;
    }

    public void r() {
        try {
            c();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void s() {
        try {
            if (t()) {
                long q = q();
                if (this.q != null) {
                    this.q.cancel();
                }
                this.q = new Timer(getClass().getSimpleName() + " refresh");
                this.q.scheduleAtFixedRate(new bdk(this, q), q, q);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public boolean t() {
        return false;
    }

    public void a(Integer num) {
        startActivity(b(num));
    }

    public void a(Integer num, String str) {
        if (str == null) {
            a(num);
            return;
        }
        Intent b2 = b(num);
        b2.putExtra("tab", str);
        startActivity(b2);
    }

    public Intent b(Integer num) {
        Intent intent = new Intent(k(), (Class<?>) GamePageActivity.class);
        intent.putExtra("game", num);
        return intent;
    }

    public void b(boolean z) {
        this.r = z;
    }

    public void c(boolean z) {
        this.s = z;
    }

    public void u() {
        try {
            startActivity(new Intent(k(), (Class<?>) FriendsActivity.class));
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void v() {
        startActivityForResult(new Intent(k(), (Class<?>) SettingsLoginActivity.class), 351);
    }

    private boolean aI() {
        return this.c == null || this.c.getTime() < System.currentTimeMillis() - 5000;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(SettingsAccountActivity.SOURCE source, int i2) {
        Intent intent = new Intent(k(), (Class<?>) SettingsAccountActivity.class);
        if (source != null) {
            intent.putExtra("context", source.a());
        }
        if (i2 > 0) {
            startActivityForResult(intent, i2);
        } else {
            startActivity(intent);
        }
    }

    public void a(SettingsAccountActivity.SOURCE source) {
        a(source, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(IService iService, String str) {
        Activity a2;
        try {
            String[] split = str.split("/");
            if (split.length < 2) {
                e().warning("cannot find game from url: " + str);
                return;
            }
            String[] split2 = split[1].split("\\?");
            int parseInt = Integer.parseInt(split2[0]);
            String str2 = null;
            if (split2.length > 1) {
                String[] split3 = split2[1].split("=");
                if (split3.length > 1 && "tab".equals(split3[0])) {
                    str2 = split3[1];
                }
            }
            if (iService != null && (a2 = iService.a(GamePageActivity.class, parseInt)) != null) {
                a2.finish();
            }
            if (str2 != null) {
                a(Integer.valueOf(parseInt), str2);
            } else {
                a(Integer.valueOf(parseInt));
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem launching game from hostpath: " + str, th);
        }
    }

    protected boolean w() {
        return j().e() || j().a((Activity) this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean x() {
        ViewGroup viewGroup;
        try {
            if (w() && (viewGroup = (ViewGroup) ((ViewGroup) findViewById(R.id.content)).getChildAt(0)) != null) {
                ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
                if (layoutParams instanceof FrameLayout.LayoutParams) {
                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                    layoutParams2.width = y();
                    layoutParams2.gravity = 1;
                    return true;
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return false;
    }

    public int y() {
        return (int) (ae().density * 400.0f);
    }

    public synchronized boolean z() {
        WebViewDatabase webViewDatabase;
        try {
            webViewDatabase = WebViewDatabase.getInstance(this);
            webViewDatabase.clearFormData();
        } catch (Throwable th) {
            if (th instanceof SQLiteDiskIOException) {
                e().log(Level.WARNING, "webview.db instance failure: " + th.toString());
            } else {
                e().log(Level.SEVERE, "webview.db instance failure: " + th.toString());
            }
            webViewDatabase = null;
        }
        return webViewDatabase != null;
    }

    public int A() {
        int i2 = this.x + 1;
        this.x = i2;
        if (i2 >= 2130706432) {
            this.x = 1;
            return 1;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean B() {
        return !j().e();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int b(Bundle bundle) {
        int i2 = -1;
        try {
            if (getIntent() != null && getIntent().hasExtra("tabindex")) {
                i2 = getIntent().getIntExtra("tabindex", -1);
                e().info("got extra tab: " + i2);
            } else if (bundle != null) {
                i2 = bundle.getInt("tabindex", -1);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        return i2;
    }

    public void a(String str, String str2, String str3) {
    }

    public void c(int i2) {
        Intent intent = new Intent(this, (Class<?>) FantasyTeamActivity.class);
        intent.putExtra("team_id", i2);
        intent.putExtra("tabindex", 2);
        startActivity(intent);
    }

    public void b(String str, String str2) {
        try {
            Intent intent = new Intent(this, (Class<?>) WebviewActivity.class);
            intent.putExtra(NativeProtocol.IMAGE_URL_KEY, str);
            if (str2 != null) {
                intent.putExtra("title", str2);
            }
            startActivity(intent);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void C() {
        startActivity(new Intent(this, (Class<?>) SettingsFantasyTeamsActivity.class));
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.app.Activity
    public void invalidateOptionsMenu() {
        try {
            super.invalidateOptionsMenu();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void D() {
        if (!isFinishing() && !isDestroyed()) {
            IntentFilter intentFilter = new IntentFilter("SportInfo");
            this.z = new bdm(this);
            registerReceiver(this.z, intentFilter, "com.thuuz.permission.local.broadcast", null);
        }
    }

    public void E() {
        c();
    }

    public void setupPersonalizeButton(View view) {
        if (view != null) {
            try {
                View findViewById = view.findViewById(bbq.personalize_layout);
                if (findViewById != null) {
                    boolean z = !j().aC();
                    findViewById.setVisibility(z ? 0 : 8);
                    if (z) {
                        View findViewById2 = findViewById.findViewById(bbq.personalize_now);
                        if (!j().a((Activity) this)) {
                            findViewById.findViewById(bbq.label).setVisibility(8);
                            TextView textView = (TextView) findViewById.findViewById(bbq.blue_btn_text);
                            textView.setText("Personalize Thuuz - Pick Favorites");
                            expandWidthByDevice(findViewById);
                            expandWidthByDevice(findViewById2);
                            expandWidthByDevice(textView);
                        }
                        findViewById2.setOnClickListener(new bdn(this));
                    }
                }
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    public IService F() {
        return this.o;
    }

    public void a(IService iService) {
        this.o = iService;
    }

    public boolean G() {
        return true;
    }

    public void a(bpv bpvVar) {
        try {
            Intent intent = new Intent(this, (Class<?>) MessageActivity.class);
            intent.putExtra("MSG_ID", bpvVar.b());
            intent.putExtra("MSG_STR", bpvVar.a());
            startActivity(intent);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public boolean H() {
        return this.r;
    }

    private void aJ() {
        if (!H() && aK() && F() == null) {
            e().severe("rebinding!");
            try {
                this.r = true;
                this.p = new bdo(this, this);
                bindService(new Intent(k(), (Class<?>) ThuuzService.class), this.p, 1);
            } catch (Throwable th) {
                e().log(Level.SEVERE, "cannot start service: ", th);
            }
        }
    }

    private boolean aK() {
        return this.s;
    }

    public Date I() {
        IService F = F();
        if (F != null) {
            return F.n();
        }
        return new Date();
    }

    @Override // android.app.Activity
    public boolean isDestroyed() {
        return this.t;
    }

    protected TextView J() {
        View customView;
        com.actionbarsherlock.app.ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null || (customView = supportActionBar.getCustomView()) == null) {
            return null;
        }
        return (TextView) customView.findViewById(bbq.title);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str) {
        try {
            TextView J = J();
            if (J != null) {
                J.setText(str);
            }
            com.actionbarsherlock.app.ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle(str);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem setting AB title: ", th);
        }
    }

    public void d(boolean z) {
        try {
            TextView J = J();
            if (J != null) {
                J.setVisibility(z ? 0 : 4);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void K() {
        com.actionbarsherlock.app.ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle("");
            supportActionBar.setDisplayShowTitleEnabled(false);
            supportActionBar.setDisplayHomeAsUpEnabled(false);
            supportActionBar.setDisplayUseLogoEnabled(false);
            supportActionBar.setDisplayShowHomeEnabled(false);
            L();
            a(this.y);
            supportActionBar.show();
            M();
        }
    }

    public void L() {
        View customView;
        com.actionbarsherlock.app.ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null && (customView = supportActionBar.getCustomView()) != null) {
            customView.setTag(l);
            customView.setOnClickListener(new bdp(this));
            customView.setOnKeyListener(new bdq(this));
            customView.setNextFocusLeftId(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            customView.setNextFocusUpId(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        }
    }

    protected void M() {
    }

    protected void N() {
        startActivity(new Intent(this, (Class<?>) GuideActivity.class));
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x000c, code lost:
    
        r0 = super.onOptionsItemSelected(r5);
     */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem r5) {
        /*
            r4 = this;
            r0 = 1
            int r1 = defpackage.bbq.refresh_option     // Catch: java.lang.Throwable -> L19
            int r2 = r5.getItemId()     // Catch: java.lang.Throwable -> L19
            if (r1 != r2) goto Ld
            r4.c()     // Catch: java.lang.Throwable -> L19
        Lc:
            return r0
        Ld:
            int r1 = defpackage.bbq.settings_option     // Catch: java.lang.Throwable -> L19
            int r2 = r5.getItemId()     // Catch: java.lang.Throwable -> L19
            if (r1 != r2) goto L2a
            r4.i()     // Catch: java.lang.Throwable -> L19
            goto Lc
        L19:
            r0 = move-exception
            java.util.logging.Logger r1 = r4.e()
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "Cannot handle: "
            r1.log(r2, r3, r0)
        L25:
            boolean r0 = super.onOptionsItemSelected(r5)
            goto Lc
        L2a:
            r1 = 16908332(0x102002c, float:2.3877352E-38)
            int r2 = r5.getItemId()     // Catch: java.lang.Throwable -> L19
            if (r1 != r2) goto L25
            r4.O()     // Catch: java.lang.Throwable -> L19
            goto Lc
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.activity.AbstractActivity.onOptionsItemSelected(com.actionbarsherlock.view.MenuItem):boolean");
    }

    public void O() {
        try {
            if (getClass() != j().aF()) {
                Class<? extends AbstractActivity> P = P();
                Intent intent = new Intent(this, P);
                if (NavUtils.shouldUpRecreateTask(this, intent)) {
                    e().info("recreating app stack for: " + P);
                    TaskStackBuilder create = TaskStackBuilder.create(this);
                    create.addNextIntent(intent);
                    create.startActivities();
                    finish();
                    return;
                }
                IService F = F();
                if (F != null) {
                    if (F.b(P) == null) {
                        Class<? extends AbstractActivity> P2 = P();
                        if (F.b(P2) == null) {
                            e().info("nav up is launching activity: " + P2.getSimpleName());
                            startActivity(intent);
                        } else {
                            e().info("nav up is bringing activity to front: " + P2.getSimpleName());
                            intent.addFlags(131072);
                            startActivity(intent);
                        }
                    } else {
                        e().info("nav up is bringing activity to front: " + P.getSimpleName());
                        intent.addFlags(131072);
                        startActivity(intent);
                    }
                } else {
                    startActivity(intent);
                }
                finish();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public Class<? extends AbstractActivity> P() {
        return b(getClass());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MenuItem a(Menu menu, int i2) {
        MenuItem findItem = menu.findItem(i2);
        if (findItem == null) {
            e().severe("cannot load menu item");
        } else {
            findItem.setShowAsAction(1);
        }
        return findItem;
    }

    public void e(boolean z) {
        if (!isDestroyed()) {
            try {
                if (z) {
                    this.u++;
                    if (this.u <= 1) {
                        runOnUiThread(new bdr(this));
                    }
                } else {
                    this.u--;
                    if (this.u <= 0) {
                        this.u = 0;
                        runOnUiThread(new bcx(this));
                    }
                }
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    public void a(Dialog dialog) {
        this.v = dialog;
    }

    public void Q() {
        startActivityForResult(new Intent(k(), (Class<?>) SettingsSocialRegisterActivity.class), 511);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void R() {
        try {
            b(S(), "About");
            j().k("/prefs/about");
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    protected String S() {
        StringBuilder sb = new StringBuilder(String.format("%sapps/about/?version=%s&build=%d&server=%s", ServerChoice.q, j().ah(), Integer.valueOf(j().ai()), ServerChoice.l.b()));
        sb.append(e(sb.toString()));
        return sb.toString();
    }

    public boolean T() {
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) getSystemService("connectivity")).getNetworkInfo(1);
            if (networkInfo != null) {
                if (networkInfo.isConnected()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
            return false;
        }
    }

    public Dialog U() {
        return this.v;
    }

    public void V() {
        try {
            startActivity(new Intent(k(), (Class<?>) SettingsSportsSelectActivity.class));
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String a(int i2, boolean z, boolean z2) {
        StringBuilder append = new StringBuilder(ServerChoice.q).append("2dgraph/").append(String.valueOf(i2)).append("/?");
        if (z) {
            append.append("&away_team_fav=1");
        }
        if (z2) {
            append.append("&home_team_fav=1");
        }
        try {
            append.append("&background=").append(Integer.toHexString(Integer.valueOf(getResources().getColor(bbn.game_cell_bg)).intValue()).substring(2));
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        append.append(e(append.toString()));
        return append.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void W() {
        try {
            bbf X = X();
            if (X != null) {
                X.doRefresh(false);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public bbf X() {
        return (bbf) getSupportFragmentManager().findFragmentByTag("right_sidebar");
    }

    public bbf Y() {
        return X();
    }

    public bmd Z() {
        return this.F;
    }

    public Handler aa() {
        return this.I;
    }

    public boolean ab() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String b(String str) {
        StringBuilder sb = new StringBuilder(String.format("%sapps/faq/sidebar/", ServerChoice.q));
        try {
            String b2 = ServerChoice.l.b();
            if (b2.endsWith("/")) {
                b2 = b2.substring(0, b2.length() - 1);
            }
            sb.append(e(sb.toString()));
            sb.append(String.format("&version=%s&build=%d&server=%s", j().ah(), Integer.valueOf(j().ai()), b2));
            sb.append(str);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        return sb.toString();
    }

    public String a(FacebookRequestError facebookRequestError) {
        int userActionMessageId = facebookRequestError.getUserActionMessageId();
        if (userActionMessageId != 0) {
            return getResources().getString(userActionMessageId);
        }
        return facebookRequestError.getErrorMessage();
    }

    public bqn a(String str, boolean z) {
        bqn bqnVar;
        Throwable th;
        try {
            bqnVar = new bqn(str, this, z);
            try {
                bqnVar.execute(new Void[0]);
            } catch (Throwable th2) {
                th = th2;
                e().log(Level.SEVERE, "problem: ", th);
                return bqnVar;
            }
        } catch (Throwable th3) {
            bqnVar = null;
            th = th3;
        }
        return bqnVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public bqn c(String str) {
        bqn bqnVar;
        Throwable th;
        try {
            bqnVar = new bqn(str, this);
            try {
                bqnVar.execute(new Void[0]);
            } catch (Throwable th2) {
                th = th2;
                e().log(Level.SEVERE, "problem: ", th);
                return bqnVar;
            }
        } catch (Throwable th3) {
            bqnVar = null;
            th = th3;
        }
        return bqnVar;
    }

    public void ac() {
    }

    public void ad() {
    }

    public void c(String str, String str2) {
        a(str, str2, false);
    }

    public void a(String str, String str2, boolean z) {
        if (!z || aI()) {
            this.c = new Date();
            b(str, str2, false);
        }
    }

    public void b(int i2, int i3) {
        c(getResources().getString(i2), getResources().getString(i3));
    }

    public void c(int i2, int i3) {
        a(getResources().getString(i2), getResources().getString(i3), true);
    }

    public void a(String str, int i2) {
        a(str, getResources().getString(i2), true);
    }

    public void b(String str, int i2) {
        c(str, getResources().getString(i2));
    }

    public void d(String str, String str2) {
        b(str, str2, true);
    }

    public void c(int i2, String str) {
        b(getResources().getString(i2), str, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(String str) {
        a(true, str, "", true);
    }

    private void b(String str, String str2, boolean z) {
        a(false, str, str2, z);
    }

    private void a(boolean z, String str, String str2, boolean z2) {
        try {
            Toast toast = new Toast(this);
            View inflate = getLayoutInflater().inflate(bbr.toast_result, (ViewGroup) findViewById(bbq.toast_layout_root));
            if (inflate != null) {
                ImageView imageView = (ImageView) inflate.findViewById(bbq.toast_img);
                if (z2) {
                    imageView.setImageResource(bbp.success_icon);
                } else {
                    imageView.setImageResource(bbp.fail_icon);
                }
                if (str != null) {
                    ((TextView) inflate.findViewById(bbq.line1)).setText(str);
                }
                TextView textView = (TextView) inflate.findViewById(bbq.line2);
                if (str2 != null && str2.length() > 0) {
                    textView.setText(str2);
                } else {
                    textView.setVisibility(8);
                }
                toast.setView(inflate);
            }
            if (z2) {
                toast.setGravity(17, 0, 0);
            }
            if (z) {
                toast.setDuration(0);
            } else {
                toast.setDuration(1);
            }
            toast.show();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DisplayMetrics ae() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public float af() {
        DisplayMetrics ae = ae();
        return ae.density == 0.0f ? ae.heightPixels : ae.heightPixels / ae.density;
    }

    public void d(int i2) {
        Intent intent = new Intent(this, (Class<?>) UserProfileActivity.class);
        intent.putExtra("userId", i2);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean ag() {
        boolean z;
        Throwable th;
        ActivityInfo activityInfo;
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("plain/text");
            PackageManager packageManager = getPackageManager();
            if (packageManager == null) {
                return false;
            }
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            boolean z2 = queryIntentActivities.size() > 0;
            if (z2) {
                try {
                    if (j().e() && queryIntentActivities.size() == 1 && (activityInfo = queryIntentActivities.get(0).activityInfo) != null) {
                        if (activityInfo.packageName.startsWith("com.google.android.tv.frameworkpackagestubs")) {
                            return false;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    z = z2;
                    e().log(Level.SEVERE, "Cannot handle: ", th);
                    return z;
                }
            }
            return z2;
        } catch (Throwable th3) {
            z = false;
            th = th3;
        }
    }

    public void a(String[] strArr) {
        boolean z = false;
        if (ag()) {
            try {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/html");
                PackageManager packageManager = getPackageManager();
                if (packageManager == null || packageManager.queryIntentActivities(intent, 0).size() <= 0) {
                    z = true;
                } else {
                    intent.putExtra("android.intent.extra.TEXT", Html.fromHtml("<p>" + getResources().getString(bbt.email_invite) + "</p><p><a href='http://www.thuuz.com/apps/get/'>Get the free Thuuz Sports app.</a></p>"));
                }
                if (z) {
                    intent.setType("plain/text");
                    intent.putExtra("android.intent.extra.TEXT", getResources().getString(bbt.email_invite) + "\nhttp://www.thuuz.com/apps/get/");
                }
                if (strArr != null) {
                    intent.putExtra("android.intent.extra.EMAIL", strArr);
                }
                intent.putExtra("android.intent.extra.SUBJECT", "What sports to watch?");
                k().startActivity(Intent.createChooser(intent, "Send Email"));
                j().k("/friends/add/email");
                return;
            } catch (ActivityNotFoundException e) {
                e().warning("Emailer activity NOT found!");
                b(bbt.no_email_app, bbt.configure_for_email);
                return;
            }
        }
        b(bbt.no_email_app, bbt.configure_for_email);
    }

    public void ah() {
        if (ag()) {
            try {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("plain/text");
                intent.putExtra("android.intent.extra.SUBJECT", "Using a new app - Thuuz Sports");
                intent.putExtra("android.intent.extra.TEXT", aM());
                k().startActivity(Intent.createChooser(intent, "Send Email"));
                j().k("/friends/add/referral");
                return;
            } catch (ActivityNotFoundException e) {
                b(bbt.no_email_app, bbt.configure_for_email);
                return;
            }
        }
        b(bbt.no_email_app, bbt.configure_for_email);
    }

    private String aL() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) "");
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append((CharSequence) "http://www.thuuz.com/apps/get/");
        spannableStringBuilder.setSpan(new URLSpan("http://www.thuuz.com/apps/get/"), length, spannableStringBuilder.length(), 33);
        return spannableStringBuilder.toString();
    }

    private String aM() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) "Hey! I've been using a new sports app that will let us know when there's a great game going on involving our favorite teams and sports. We can even recommend games to each other using 'Thuuz Alerts'.\n\n");
        spannableStringBuilder.append((CharSequence) "Download it for free and let me know what you think.\n");
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append((CharSequence) "http://www.thuuz.com/apps/get/");
        spannableStringBuilder.setSpan(new URLSpan("http://www.thuuz.com/apps/get/"), length, spannableStringBuilder.length(), 33);
        return spannableStringBuilder.toString();
    }

    public void ai() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = false;
        String str = null;
        try {
            if (Build.VERSION.SDK_INT > 18) {
                String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(this);
                e().info("default sms: " + defaultSmsPackage);
                g(defaultSmsPackage);
                j().k("/friends/add/sms");
                return;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("sms:"));
            String aL = aL();
            intent.putExtra("sms_body", aL);
            intent.putExtra("android.intent.extra.TEXT", aL);
            PackageManager packageManager = getPackageManager();
            if (packageManager != null) {
                List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
                boolean z5 = queryIntentActivities.size() > 0;
                z2 = false;
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    e().info("sms app: " + resolveInfo);
                    if (resolveInfo.toString().contains("com.google.android.apps.babel.phone")) {
                        str = resolveInfo.activityInfo != null ? resolveInfo.activityInfo.name : str;
                        z3 = true;
                    } else {
                        z3 = z2;
                    }
                    z2 = z3;
                }
                z = z2 && queryIntentActivities.size() == 1;
                z4 = z5;
            } else {
                z = false;
                z2 = false;
            }
            if (z4) {
                if (z2) {
                    if (z && str != null) {
                        g(str);
                    } else {
                        k().startActivity(Intent.createChooser(intent, "Choose SMS (texting) app:"));
                    }
                } else {
                    k().startActivity(Intent.createChooser(intent, "Choose SMS (texting) app:"));
                }
                j().k("/friends/add/sms");
                return;
            }
            b(bbt.no_sms_app, bbt.configure_for_sms);
        } catch (ActivityNotFoundException e) {
            b(bbt.no_sms_app, bbt.configure_for_sms);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    private void g(String str) {
        e().info("sending sms request via ACTION_SEND to: " + str);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(HTTP.PLAIN_TEXT_TYPE);
        intent.putExtra("android.intent.extra.TEXT", aL());
        if (str != null) {
            intent.setPackage(str);
        }
        startActivity(intent);
    }

    public void aj() {
        try {
            IService F = F();
            if (F != null) {
                F.a(this);
            }
            a((IService) null);
            if (this.p != null) {
                unbindService(this.p);
                this.p = null;
            }
        } catch (Throwable th) {
            if (!th.getMessage().contains("Service not registered")) {
                e().log(Level.SEVERE, "problem: ", th);
            } else {
                e().info("problem unbinding: " + th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MenuInflater a(Menu menu) {
        MenuInflater supportMenuInflater = getSupportMenuInflater();
        supportMenuInflater.inflate(bbs.refresh, menu);
        return supportMenuInflater;
    }

    public void f(boolean z) {
        try {
            e().info("showKeyboard: " + String.valueOf(z));
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
            View currentFocus = getCurrentFocus();
            if (z) {
                inputMethodManager.showSoftInput(currentFocus, 1);
            } else if (currentFocus != null) {
                inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
            }
        } catch (Throwable th) {
            e().log(Level.WARNING, "Problem in showKeyboard: ", th);
        }
    }

    public void g(boolean z) {
        if (z != j().bF()) {
            e().info("changed notification prefs");
            j().ap();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        try {
            return super.onKeyUp(i2, keyEvent);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
            return true;
        }
    }

    public void a(Map<Integer, Integer> map) {
        this.G = map;
    }

    public void a(ListView listView) {
        a(listView, false);
    }

    public void a(ListView listView, boolean z) {
        try {
            View inflate = getLayoutInflater().inflate(bbr.list_footer, (ViewGroup) null);
            if (inflate != null) {
                inflate.setId(A());
                listView.addFooterView(inflate, null, z);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem adding list footer: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean ak() {
        return Looper.getMainLooper().equals(Looper.myLooper());
    }

    public void al() {
        try {
            if (this.F != null && this.E != null) {
                aN();
                this.F.c();
                this.E.requestFocus();
                az();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem with sliding drawer open: ", th);
        }
    }

    public bpr am() {
        return new bcy(this, f, "my_sports", -1, "", "My Sports", 0, "My Sports", false);
    }

    public bpr an() {
        return new bcz(this, i, "all_sports", -7, "", "All Sports", 0, "All Sports", false);
    }

    public bpr ao() {
        return new bda(this, g, "my_teams", -2, "", "My Teams", 0, "My Teams", false);
    }

    public bpr ap() {
        return new bdb(this, h, "friends_games", -5, "", "Friends Games", 0, "Friends Games", false);
    }

    public bpr aq() {
        return new bdc(this, j, "off_season", -8, "", "Off-season", 0, "Off-season", false);
    }

    public bpr ar() {
        return new bdd(this, k, "other_sports", -9, "", "Other Sports", 0, "Other Sports", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(bmc bmcVar) {
        try {
            if (bmcVar.e().a() != getClass()) {
                switch (bdj.a[bmcVar.e().ordinal()]) {
                    case 1:
                        N();
                        break;
                    case 2:
                        i();
                        break;
                    case 3:
                        b(bmcVar);
                        break;
                }
            }
            aw();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void b(bmc bmcVar) {
        bpr c;
        String str = null;
        try {
            switch (bmcVar.d()) {
                case EventHandler.ERROR_REDIRECT_LOOP /* -9 */:
                case EventHandler.ERROR_TIMEOUT /* -8 */:
                    break;
                case EventHandler.ERROR_IO /* -7 */:
                    str = App.e;
                    break;
                case EventHandler.ERROR_CONNECT /* -6 */:
                case -4:
                case -3:
                default:
                    IService F = F();
                    if (F != null && (c = F.c(bmcVar.d())) != null) {
                        str = "pref_" + c.d();
                        break;
                    }
                    break;
                case EventHandler.ERROR_PROXYAUTH /* -5 */:
                    str = App.d;
                    break;
                case -2:
                    if (!j().aB()) {
                        V();
                        c("Please add favorite teams", "");
                        return;
                    } else {
                        str = App.c;
                        break;
                    }
                case -1:
                    if (!j().az() && !j().aA()) {
                        V();
                        c("Please add favorite sports", "");
                        return;
                    } else {
                        str = App.b;
                        break;
                    }
                    break;
            }
            if (str != null) {
                j().B(str);
                N();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void aN() {
        IService F = F();
        if (F != null) {
            this.G = F.o();
            if (d()) {
                aO();
            }
        }
    }

    private synchronized void aO() {
        try {
            if (this.H == null) {
                this.H = new bde(this);
                this.H.execute(new Void[0]);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public int a(bmc bmcVar, IService iService) {
        int i2 = 0;
        if (this.G != null && bmcVar != null) {
            try {
                switch (bdj.a[bmcVar.e().ordinal()]) {
                    case 3:
                        switch (bmcVar.d()) {
                            case EventHandler.ERROR_REDIRECT_LOOP /* -9 */:
                                if (iService != null) {
                                    i2 = iService.a(new String[]{String.valueOf(-9)});
                                    break;
                                }
                                break;
                            case EventHandler.ERROR_TIMEOUT /* -8 */:
                                if (iService != null) {
                                    i2 = iService.a(new String[]{String.valueOf(-8)});
                                    break;
                                }
                                break;
                            case EventHandler.ERROR_IO /* -7 */:
                                if (iService != null) {
                                    i2 = iService.a(new String[]{String.valueOf(-7)});
                                    break;
                                }
                                break;
                            default:
                                Integer num = this.G.get(Integer.valueOf(bmcVar.d()));
                                if (num != null) {
                                    i2 = num.intValue();
                                    break;
                                }
                                break;
                        }
                        break;
                    case 4:
                        if (iService != null) {
                            Iterator<bpr> it = iService.f(bmcVar.d()).iterator();
                            int i3 = 0;
                            while (it.hasNext()) {
                                try {
                                    Integer num2 = this.G.get(Integer.valueOf(it.next().g()));
                                    i3 += num2 == null ? 0 : num2.intValue();
                                } catch (Throwable th) {
                                    th = th;
                                    i2 = i3;
                                    break;
                                }
                            }
                            i2 = i3;
                        }
                        break;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            e().log(Level.SEVERE, "problem getting live count: ", th);
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public bbf as() {
        bbf bbfVar;
        Throwable th;
        bbf bbfVar2 = null;
        bbfVar2 = null;
        bbfVar2 = null;
        bbfVar2 = null;
        try {
            FrameLayout frameLayout = (FrameLayout) findViewById(bbq.right_side_layout);
            if (frameLayout == null) {
                bbfVar = null;
            } else if (!at()) {
                frameLayout.setVisibility(8);
                bbfVar = null;
            } else {
                frameLayout.setVisibility(0);
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                bbfVar = (bbf) supportFragmentManager.findFragmentByTag("right_sidebar");
                bbf au = au();
                if (bbfVar == null && au == null) {
                    bbfVar = null;
                } else {
                    try {
                        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
                        if (bbfVar == null) {
                            bbfVar = au;
                        } else if (au != null && !bbfVar.getClass().equals(au.getClass())) {
                            beginTransaction.remove(bbfVar);
                            bbfVar = au;
                        }
                        try {
                            if (bbfVar.isAdded()) {
                                beginTransaction.show(bbfVar);
                            } else {
                                beginTransaction.add(bbq.right_side_layout, bbfVar, "right_sidebar");
                            }
                            beginTransaction.commit();
                            bbfVar2 = beginTransaction;
                        } catch (Throwable th2) {
                            th = th2;
                            e().log(Level.SEVERE, "problem: ", th);
                            return bbfVar;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bbfVar = au;
                    }
                }
            }
        } catch (Throwable th4) {
            bbfVar = bbfVar2;
            th = th4;
        }
        return bbfVar;
    }

    public boolean at() {
        return j().a((Activity) this) && !j().g();
    }

    public bbf au() {
        return new GameListFragment();
    }

    public String e(String str) {
        StringBuilder sb = new StringBuilder("");
        if (str != null) {
            if (!str.contains("?")) {
                sb.append("?");
            } else if (!str.endsWith("&")) {
                sb.append("&");
            }
        }
        sb.append("device_type");
        sb.append("=");
        sb.append(j().ao());
        sb.append("&");
        sb.append("thuuz_vers");
        sb.append("=");
        sb.append(j().ai());
        sb.append("&");
        sb.append("device_size");
        sb.append("=");
        sb.append(j().a(this));
        sb.append("&");
        sb.append("aos");
        sb.append("=");
        sb.append(j().Q());
        if (j().e() && !j().f()) {
            sb.append("&amzn=true");
        }
        return sb.toString();
    }

    public void f(String str) {
        try {
            String path = Uri.parse(str).getPath();
            if (path != null) {
                path = path.toLowerCase();
            }
            if (j().A(path)) {
                e().info("launching internal viewer for: " + str);
                Intent intent = new Intent(this, (Class<?>) MP4VideoActivity.class);
                intent.putExtra(NativeProtocol.IMAGE_URL_KEY, str);
                startActivity(intent);
                return;
            }
            e().info("launching browser for: " + str);
            e(str, "");
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void av() {
        if (this.D != null) {
            az();
            this.D.openDrawer(this.E);
            this.E.requestFocus();
        }
    }

    public void aw() {
        if (this.D != null) {
            ay();
            this.D.closeDrawer(this.E);
        }
    }

    public boolean ax() {
        if (this.D != null) {
            return this.D.isDrawerOpen(this.E);
        }
        return false;
    }

    public void ay() {
        try {
            int groupCount = this.F.getGroupCount();
            ArrayList<bmc> arrayList = new ArrayList<>();
            for (int i2 = 0; i2 < groupCount; i2++) {
                bmc bmcVar = (bmc) this.F.getGroup(i2);
                if (bmcVar != null) {
                    if (this.E.isGroupExpanded(i2)) {
                        bmcVar.b(true);
                    } else {
                        bmcVar.b(false);
                    }
                    arrayList.add(bmcVar);
                }
            }
            j().a(arrayList);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem saving drawer state", th);
        }
    }

    public void az() {
        try {
            if (this.F != null) {
                int groupCount = this.F.getGroupCount();
                Iterator<bmc> it = j().w().iterator();
                while (it.hasNext()) {
                    bmc next = it.next();
                    for (int i2 = 0; i2 < groupCount; i2++) {
                        bmc bmcVar = (bmc) this.F.getGroup(i2);
                        if (bmcVar != null && next.d() == bmcVar.d() && next.e() == bmcVar.e()) {
                            if (next.f()) {
                                this.E.expandGroup(i2);
                            } else {
                                this.E.collapseGroup(i2);
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem restoring drawer", th);
        }
    }

    public void aA() {
        try {
            this.D = (DrawerLayout) findViewById(bbq.drawer_layout);
            if (this.D != null) {
                this.C = new bdf(this, this, this.D, bbp.nav_home, bbt.drawer_open, bbt.drawer_close);
                this.D.setDrawerListener(this.C);
                this.D.setDrawerShadow(bbp.drawer_shadow, GravityCompat.START);
                this.E = (ExpandableListView) findViewById(bbq.sliding_list);
                this.E.setGroupIndicator(null);
                this.E.setItemsCanFocus(true);
                this.E.setNextFocusRightId(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                this.E.setNextFocusLeftId(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                this.E.setNextFocusDownId(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                this.F = new bmd(this.E, this, new bdg(this));
                this.E.setAdapter(this.F);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean aB() {
        return getResources().getConfiguration().keyboard == 1;
    }

    public void e(String str, String str2) {
        if (j().cb()) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            startActivity(intent);
            return;
        }
        b(str, str2);
    }

    public boolean aC() {
        return this.J;
    }

    public void a(Throwable th) {
        try {
            System.gc();
            e().log(Level.SEVERE, "OoM: ", th);
            IService F = F();
            if (F == null) {
                j().bX();
            } else {
                F.z();
            }
        } catch (Throwable th2) {
        }
    }

    public boolean a(KeyEvent keyEvent, boolean z) {
        String str;
        if (keyEvent == null || !j().e()) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() == 1) {
            return false;
        }
        View view = null;
        View currentFocus = getCurrentFocus();
        if (currentFocus == null) {
            e().info("no current focus");
            return false;
        }
        switch (keyCode) {
            case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                if ((currentFocus instanceof WebView) && a((WebView) currentFocus, 33)) {
                    return false;
                }
                int nextFocusUpId = currentFocus.getNextFocusUpId();
                if (nextFocusUpId == Integer.MAX_VALUE) {
                    return true;
                }
                view = a(33, currentFocus, nextFocusUpId, z);
                break;
            case 20:
                if ((currentFocus instanceof WebView) && a((WebView) currentFocus, TransportMediator.KEYCODE_MEDIA_RECORD)) {
                    return false;
                }
                int nextFocusDownId = currentFocus.getNextFocusDownId();
                if (nextFocusDownId == Integer.MAX_VALUE) {
                    return true;
                }
                view = a(TransportMediator.KEYCODE_MEDIA_RECORD, currentFocus, nextFocusDownId, z);
                break;
                break;
            case 21:
                int nextFocusLeftId = currentFocus.getNextFocusLeftId();
                if (nextFocusLeftId == Integer.MAX_VALUE) {
                    return true;
                }
                if (currentFocus instanceof WebView) {
                    z = true;
                }
                view = a(17, currentFocus, nextFocusLeftId, z);
                break;
            case 22:
                int nextFocusRightId = currentFocus.getNextFocusRightId();
                if (nextFocusRightId == Integer.MAX_VALUE) {
                    return true;
                }
                if (currentFocus instanceof WebView) {
                    z = true;
                }
                view = a(66, currentFocus, nextFocusRightId, z);
                break;
        }
        if (view != null) {
            view.requestFocus();
            if (!e().isLoggable(Level.INFO)) {
                return true;
            }
            String str2 = " focusing to: " + view.getId() + " class: " + view.getClass();
            if (z) {
                str = "manually" + str2;
            } else {
                str = "force" + str2;
            }
            e().info(str);
            aa().postDelayed(new bdi(this), 100L);
            return true;
        }
        e().info("no focus available");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(WebView webView, int i2) {
        if (i2 == 33) {
            i2 = -33;
        }
        if (Build.VERSION.SDK_INT >= 14) {
            return webView.canScrollVertically(i2);
        }
        return true;
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent == null || keyEvent.getAction() == 1) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
            case 20:
            case 21:
            case 22:
                return true;
            default:
                return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.view.View a(int r9, android.view.View r10, int r11, boolean r12) {
        /*
            r8 = this;
            r0 = 0
            r1 = 1
            r3 = 0
            r2 = -1
            if (r11 == r2) goto L91
            android.view.View r4 = r8.findViewById(r11)
            if (r4 == 0) goto L91
            boolean r2 = r4.isShown()
            if (r2 == 0) goto L88
        L12:
            if (r4 != 0) goto L9c
            java.util.logging.Logger r2 = r8.e()
            java.util.logging.Level r5 = java.util.logging.Level.INFO
            boolean r2 = r2.isLoggable(r5)
            if (r2 == 0) goto L9a
            android.view.View r2 = r10.focusSearch(r9)
            java.lang.String r0 = ""
            if (r2 == 0) goto L30
            java.lang.Class r0 = r2.getClass()
            java.lang.String r0 = r0.getSimpleName()
        L30:
            java.util.logging.Logger r5 = r8.e()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "focus search finds: "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r0 = r6.append(r0)
            java.lang.String r0 = r0.toString()
            r5.info(r0)
            r0 = r2
            r2 = r1
        L4c:
            if (r12 == 0) goto L97
            if (r2 != 0) goto L95
            android.view.View r0 = r10.focusSearch(r9)
        L54:
            r2 = r1
            r1 = r0
        L56:
            if (r0 != 0) goto L93
            r4 = 130(0x82, float:1.82E-43)
            if (r9 != r4) goto L93
            if (r2 != 0) goto L62
            android.view.View r1 = r10.focusSearch(r9)
        L62:
            if (r1 == 0) goto L93
            java.lang.Object r2 = com.thuuz.gtv.activity.AbstractActivity.l
            java.lang.Object r4 = r10.getTag()
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L93
            boolean r2 = r1 instanceof android.support.v4.widget.DrawerLayout
            if (r2 == 0) goto L93
            android.view.View r0 = r1.focusSearch(r9)
            if (r0 != 0) goto L93
            r0 = r1
            android.support.v4.widget.DrawerLayout r0 = (android.support.v4.widget.DrawerLayout) r0
            int r2 = r0.getChildCount()
            if (r2 <= 0) goto L87
            android.view.View r1 = r0.getChildAt(r3)
        L87:
            return r1
        L88:
            java.util.logging.Logger r2 = r8.e()
            java.lang.String r4 = "manually associated nav target not visible; ignoring association, using normal search"
            r2.info(r4)
        L91:
            r4 = r0
            goto L12
        L93:
            r1 = r0
            goto L87
        L95:
            r1 = r2
            goto L54
        L97:
            r1 = r0
            r0 = r4
            goto L56
        L9a:
            r2 = r3
            goto L4c
        L9c:
            r1 = r0
            r2 = r3
            r0 = r4
            goto L56
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.activity.AbstractActivity.a(int, android.view.View, int, boolean):android.view.View");
    }

    public boolean aD() {
        try {
            View childAt = ((ViewGroup) findViewById(R.id.content)).getChildAt(0);
            if (childAt != null) {
                if (childAt.hasFocus()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
            return false;
        }
    }

    public ba aE() {
        return null;
    }

    public boolean b(String str, boolean z) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        e().log(Level.INFO, Integer.toString(checkSelfPermission(str)));
        if (checkSelfPermission(str) == 0) {
            return false;
        }
        if (!z) {
            return true;
        }
        requestPermissions(new String[]{str}, 2000);
        return true;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        bqy.a();
    }
}
