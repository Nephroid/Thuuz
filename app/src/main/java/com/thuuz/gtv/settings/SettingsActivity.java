package com.thuuz.gtv.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.http.Headers;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.actionbarsherlock.view.Menu;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.sports.AbstractTabActivity;
import com.thuuz.gtv.data.Locations;
import com.thuuz.gtv.settings.SettingsAccountActivity;
import com.thuuz.gtv.settings.loc.SettingsEditLocationActivity;
import com.thuuz.gtv.settings.loc.SettingsLocationsActivity;
import com.thuuz.gtv.util.ServerChoice;
import defpackage.bbf;
import defpackage.bbp;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bpg;
import defpackage.bph;
import defpackage.bpr;
import defpackage.bps;
import defpackage.bpu;
import defpackage.bpz;
import defpackage.brm;
import defpackage.brn;
import defpackage.bro;
import defpackage.brp;
import defpackage.brq;
import defpackage.brr;
import defpackage.brs;
import defpackage.brw;
import defpackage.brx;
import defpackage.bwg;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class SettingsActivity extends AbstractTabActivity {
    private TreeSet<Integer> f;
    private final List<brr> h;
    private brs i;
    private boolean j;
    private String k;
    private String l;

    /* loaded from: classes.dex */
    public enum SETTING_TYPE {
        SETTING_ACCOUNT,
        SETTING_SPORTS,
        SETTING_LOCATIONS,
        SETTING_ALERTS,
        SETTING_FRIENDS,
        SETTING_DISPLAY_SCORES,
        SETTING_FEEDBACK,
        SETTING_FANTASY,
        SETTING_ABOUT
    }

    public SettingsActivity() {
        super("Profile");
        this.h = new ArrayList();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_activity);
        this.f = j().x();
        aS();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.sports.AbstractTabActivity, com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            c();
            j().k("/prefs");
        } catch (Throwable th) {
            e().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        if (j().a((Activity) this)) {
            return super.onCreateOptionsMenu(menu);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 285:
                try {
                    aH();
                    return;
                } catch (Throwable th) {
                    e().log(Level.SEVERE, "cannot handle: ", th);
                    return;
                }
            case 3300:
                e().info("got login to permit fantasy");
                try {
                    g();
                    return;
                } catch (Throwable th2) {
                    e().log(Level.SEVERE, "cannot handle: ", th2);
                    return;
                }
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        TreeSet<Integer> x = j().x();
        boolean z = !x.equals(this.f);
        this.f = x;
        this.k = h(z);
        this.l = i(false);
        aH();
        W();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        try {
            new bwg(this).execute(new Void[0]);
            this.k = h(false);
            this.l = i(false);
            aH();
            new brm(this).execute(new Void[0]);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view) {
        Intent intent;
        try {
            brw brwVar = (brw) view.getTag();
            switch (brq.a[brwVar.f.a.ordinal()]) {
                case 1:
                    if (j().e()) {
                        brwVar.c.toggle();
                        return;
                    }
                    return;
                case 2:
                    if (j().aC()) {
                        intent = new Intent(k(), (Class<?>) SettingsFavSportsActivity.class);
                    } else {
                        intent = new Intent(k(), (Class<?>) SettingsSportsSelectActivity.class);
                    }
                    startActivity(intent);
                    return;
                case 3:
                    startActivity(new Intent(k(), (Class<?>) SettingsAlertsActivity.class));
                    return;
                case 4:
                    Locations ax = j().ax();
                    if (j().e()) {
                        bpu a = ax.a();
                        Intent intent2 = new Intent(k(), (Class<?>) SettingsEditLocationActivity.class);
                        if (a != null) {
                            intent2.putExtra(Headers.LOCATION, a.a());
                        }
                        startActivityForResult(intent2, 285);
                        return;
                    }
                    if (ax.size() == 0) {
                        startActivityForResult(new Intent(k(), (Class<?>) SettingsEditLocationActivity.class), 285);
                        return;
                    } else {
                        startActivity(new Intent(k(), (Class<?>) SettingsLocationsActivity.class));
                        return;
                    }
                case 5:
                    if (j().i()) {
                        startActivity(new Intent(k(), (Class<?>) SettingsMaintAccountActivity.class));
                        return;
                    } else {
                        a(SettingsAccountActivity.SOURCE.SOURCE_NORMAL_ACCOUNT_INFO);
                        return;
                    }
                case 6:
                    if (this.j) {
                        aF();
                        return;
                    } else {
                        R();
                        return;
                    }
                case 7:
                    h();
                    return;
                case 8:
                    u();
                    return;
                case 9:
                    if (!j().i()) {
                        g();
                        return;
                    } else {
                        C();
                        return;
                    }
                default:
                    return;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void aF() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(k());
            builder.setTitle("Which Server?");
            builder.setAdapter(new ArrayAdapter(this, bbr.simple_list_item, bbq.text, new String[]{"Production", "Web1", "Webtest", "Stage", "Leland", "Jordan", "Tyler", "Ben", "Eshita", "Jon"}), new brn(this, ServerChoice.l));
            AlertDialog create = builder.create();
            a(create);
            if (!isDestroyed()) {
                create.show();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void aH() {
        this.h.clear();
        this.h.add(new brr(SETTING_TYPE.SETTING_ACCOUNT, aJ(), aI(), bbp.profile_account));
        this.h.add(new brr(SETTING_TYPE.SETTING_SPORTS, aM(), aO(), bbp.profile_favorites));
        this.h.add(new brr(SETTING_TYPE.SETTING_FRIENDS, aL(), "", bbp.profile_friends));
        if (!j().e() || j().bZ()) {
            this.h.add(new brr(SETTING_TYPE.SETTING_ALERTS, aK(), "", bbp.profile_alerts));
        }
        this.h.add(new brr(SETTING_TYPE.SETTING_LOCATIONS, aQ(), aR(), bbp.profile_tv));
        this.h.add(new brr(SETTING_TYPE.SETTING_DISPLAY_SCORES, "Scores and Teasers", "", bbp.profile_score));
        this.h.add(new brr(SETTING_TYPE.SETTING_FEEDBACK, "Feedback", "", bbp.profile_feedback));
        this.h.add(new brr(SETTING_TYPE.SETTING_ABOUT, "About", aN(), bbp.profile_about));
        this.i.notifyDataSetChanged();
    }

    private String aI() {
        if (j().i()) {
            return j().bY();
        }
        return "This is optional, but lets you do more cool stuff";
    }

    private String aJ() {
        if (j().i()) {
            return "Account Info";
        }
        return "Register or Sign in";
    }

    private String aK() {
        return "Customize Your Alerts";
    }

    private String aL() {
        return "Find Friends";
    }

    private String aM() {
        if (!j().aC()) {
            return "Pick Favorite Sports & Teams";
        }
        return "Favorite Sports & Teams";
    }

    private String aN() {
        if (ServerChoice.l == ServerChoice.SERVER_TYPE_PROD) {
            return "";
        }
        return " (server -> " + ServerChoice.l.name().substring("SERVER_TYPE_".length()) + ")";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized String h(boolean z) {
        String sb;
        IService F = F();
        if (F == null) {
            sb = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            TreeSet treeSet = new TreeSet();
            List<bpr> y = F.y();
            Iterator it = new TreeSet((SortedSet) this.f).iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                bpz a = F.a((Integer) it.next());
                if (a == null) {
                    if (F.C() && !z2 && z) {
                        new brx(this, null).execute(new Void[0]);
                        z2 = true;
                    }
                } else {
                    bpr a2 = a.a(y);
                    if (a2 != null && a2.l()) {
                        treeSet.add(a.a(F));
                    }
                }
            }
            Iterator it2 = treeSet.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                sb2.append((String) it2.next());
                sb2.append(", ");
                if (sb2.length() > aP()) {
                    sb2.setLength(sb2.length() - 2);
                    sb2.append("...  ");
                    break;
                }
            }
            if (sb2.length() > 0) {
                sb2.setLength(sb2.length() - 2);
            }
            sb = sb2.toString();
        }
        return sb;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String i(boolean z) {
        boolean z2;
        IService F = F();
        if (F == null) {
            return "";
        }
        boolean z3 = false;
        StringBuilder sb = new StringBuilder();
        Iterator<bph> it = j().t().iterator();
        while (true) {
            z2 = z3;
            if (!it.hasNext()) {
                break;
            }
            bpg l = F.l(it.next().a());
            if (l != null) {
                sb.append(l.e());
                sb.append(", ");
                z3 = z2;
            } else {
                z3 = true;
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        if (z2 && z) {
            j().L();
        }
        return sb.toString();
    }

    private String aO() {
        String str;
        IService F;
        try {
            F = F();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
            str = "";
        }
        if (F == null) {
            return "";
        }
        List<bpr> d = F.d();
        List<bps> e = F.e();
        StringBuilder sb = new StringBuilder();
        TreeSet treeSet = new TreeSet();
        if (!d.isEmpty()) {
            Iterator<bpr> it = d.iterator();
            while (it.hasNext()) {
                String h = it.next().h();
                if (h != null && h.length() != 0) {
                    treeSet.add(h);
                }
            }
        }
        if (!e.isEmpty()) {
            for (bps bpsVar : e) {
                String e2 = bpsVar.e();
                String b = (e2 == null || e2.length() == 0) ? bpsVar.b() : e2;
                if (b != null && b.length() != 0) {
                    treeSet.add(b);
                }
            }
        }
        Iterator it2 = treeSet.iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            sb.append(", ");
            if (sb.length() > aP()) {
                break;
            }
        }
        if (sb.length() > 0 && (this.k == null || this.k.length() == 0)) {
            sb.setLength(sb.length() - 2);
        }
        if (sb.length() < aP() && this.k != null) {
            sb.append(this.k);
        }
        str = sb.toString();
        return str;
    }

    private int aP() {
        return j().a((Activity) this) ? 1000 : 100;
    }

    private String aQ() {
        if (!j().ax().isEmpty()) {
            return "TV Info";
        }
        return "Set Your TV Info";
    }

    private String aR() {
        bpu a = j().ax().a();
        if (a == null) {
            return "";
        }
        return a.j();
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
                        this.j = true;
                        break;
                    } else if (action == 1) {
                        this.j = false;
                        break;
                    }
                    break;
            }
            if (Build.VERSION.SDK_INT >= 11 && keyEvent.hasModifiers(0)) {
                if (action == 0) {
                    this.j = true;
                } else if (action == 1) {
                    this.j = false;
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.sports.AbstractTabActivity
    public void e(int i) {
        this.l = i(false);
        aH();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public bbf au() {
        return new WebViewFragment(b("#001"));
    }

    private void aS() {
        ListView listView = (ListView) findViewById(bbq.settings_list);
        this.i = new brs(this, this);
        listView.setAdapter((ListAdapter) this.i);
        listView.setOnItemClickListener(new bro(this));
        listView.setOnFocusChangeListener(new brp(this));
    }
}
