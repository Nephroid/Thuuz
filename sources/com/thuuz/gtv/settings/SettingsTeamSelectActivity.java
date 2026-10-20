package com.thuuz.gtv.settings;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbarsherlock.view.Menu;
import com.thuuz.gtv.IService;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.bpr;
import defpackage.bps;
import defpackage.bpt;
import defpackage.bpz;
import defpackage.bqa;
import defpackage.bvv;
import defpackage.bvw;
import defpackage.bvx;
import defpackage.bvy;
import defpackage.bwf;
import defpackage.bzt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsTeamSelectActivity extends AbstractSettingsOnboardingActivity {
    private int f;
    private final List<bwf> g;
    private bvx h;
    private final List<bpz> i;
    private ProgressBar j;
    private ListView k;
    private boolean l;
    private View.OnClickListener m;

    public SettingsTeamSelectActivity() {
        super("Add Teams");
        this.g = new ArrayList();
        this.i = new ArrayList();
    }

    @Override // com.thuuz.gtv.settings.AbstractSettingsOnboardingActivity, com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.app.Activity
    public void setContentView(int i) {
        super.setContentView(i);
        if (aF()) {
            TextView textView = (TextView) findViewById(bbq.next_container);
            textView.setText("next");
            textView.setOnClickListener(new bvv(this));
            if (j().at() && b()) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) findViewById(bbq.league_list).getLayoutParams();
                layoutParams.gravity = 1;
                layoutParams.width = k().getResources().getDisplayMetrics().widthPixels / 2;
            }
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_teamlist_layout);
        this.f = getIntent().getIntExtra("league", -1);
        if (this.f == -1) {
            if (bundle != null) {
                this.f = bundle.getInt("league", -1);
            }
            if (this.f == -1) {
                e().warning("no id for league. bailing back to team league selection settings!");
                finish();
                return;
            }
        }
        this.k = (ListView) findViewById(bbq.league_list);
        a(this.k);
        this.k.setItemsCanFocus(true);
        this.h = new bvx(this, this, this.g);
        this.h.a(j().o());
        this.h.b(j().r());
        this.h.c(j().x());
        this.k.setAdapter((ListAdapter) this.h);
        this.j = (ProgressBar) findViewById(bbq.team_progress);
        this.m = new bvw(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        j().k("/prefs/pick_sports_teams");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("league", this.f);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        IService F = F();
        if (F != null) {
            if (!F.g()) {
                new bzt(this).execute(new Void[0]);
            } else {
                c();
            }
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        try {
            if (F() != null) {
                aK();
                new bvy(this, null).execute(new Void[0]);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "cannot handle: ", th);
            if (d()) {
                b(bbt.unable_to_update, bbt.pls_try_again_later);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void aK() {
        bpr c;
        String str;
        try {
            IService F = F();
            if (F != null && (c = F.c(this.f)) != null) {
                if (aF()) {
                    TextView textView = (TextView) findViewById(bbq.onboarding_header);
                    textView.setText(c.h());
                    textView.setVisibility(0);
                } else {
                    a(c.h());
                }
                ArrayList arrayList = new ArrayList();
                bwf bwfVar = new bwf(c);
                bwfVar.a("Add to My Sports");
                List<bps> g = F.g(this.f);
                if (g.size() > 0) {
                    bpr bprVar = new bpr(c);
                    if (c.i()) {
                        str = "Add to My Sports";
                        bprVar.a(getString(bbt.all_major_conferences));
                    } else {
                        str = "Add to My Sports";
                        bprVar.a("All " + c.h());
                    }
                    bwf bwfVar2 = new bwf(bprVar);
                    arrayList.add(bwfVar2);
                    bwfVar2.a(str);
                    Iterator<bps> it = g.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new bwf(it.next()));
                    }
                    bwfVar = bwfVar2;
                } else {
                    arrayList.add(bwfVar);
                }
                bwfVar.b("Learn about any exciting " + c.c().e().toLowerCase());
                String h = c.c().h();
                Iterator it2 = new ArrayList(this.i).iterator();
                int i = 0;
                while (it2.hasNext()) {
                    bwf bwfVar3 = new bwf((bpz) it2.next());
                    if (i == 0) {
                        bwfVar3.a("Add to My " + h);
                    }
                    arrayList.add(bwfVar3);
                    i++;
                }
                this.g.clear();
                this.g.addAll(arrayList);
                this.h.notifyDataSetChanged();
                boolean z = this.g.size() > 80;
                this.k.setFastScrollEnabled(z);
                if (Build.VERSION.SDK_INT >= 11) {
                    this.k.setFastScrollAlwaysVisible(z);
                }
                if (!this.l && this.k.getChildCount() > 0) {
                    this.k.requestFocus();
                    this.l = this.k.hasFocus();
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        try {
            IService F = F();
            if (F != null) {
                if (i < 0 || i >= this.g.size()) {
                    e().severe("league pos " + i + " doesn't exist; skipping click");
                    return;
                }
                bwf bwfVar = this.g.get(i);
                boolean bF = j().bF();
                boolean z = false;
                if (bwfVar.a()) {
                    z = this.h.a().contains(Integer.valueOf(bwfVar.d()));
                } else if (bwfVar.g()) {
                    z = this.h.b().contains(Integer.valueOf(bwfVar.d()));
                } else if (bwfVar.b()) {
                    z = this.h.c().contains(Integer.valueOf(bwfVar.d()));
                }
                if (z) {
                    if (bwfVar.a()) {
                        j().b(bwfVar.a.g());
                        j().a("sports", "delete_league", String.valueOf(bwfVar.a.g()), 0L);
                    } else if (bwfVar.g()) {
                        j().d(bwfVar.b.c());
                        j().a("sports", "delete_league_group", String.valueOf(bwfVar.b.c()), 0L);
                    } else if (bwfVar.b()) {
                        j().h(bwfVar.c.b());
                        j().a("teams", "delete", String.valueOf(bwfVar.c.b()), 0L);
                    }
                } else if (bwfVar.a()) {
                    j().a(bpt.a(Integer.valueOf(bwfVar.a.g()), F.e(bwfVar.a.g())));
                    if (F.g(bwfVar.a.g()).size() > 0) {
                        Iterator<Integer> it = j().r().iterator();
                        while (it.hasNext()) {
                            j().d(it.next().intValue());
                        }
                    }
                    j().a("sports", "add_league", String.valueOf(bwfVar.a.g()), 0L);
                } else if (bwfVar.g()) {
                    j().b(bpt.a(Integer.valueOf(bwfVar.b.c()), F.e(bwfVar.b.c())));
                    j().a("sports", "add_league_group", String.valueOf(bwfVar.b.c()), 0L);
                    bpr aL = aL();
                    if (aL != null && this.h.a().contains(Integer.valueOf(aL.g()))) {
                        j().b(aL.g());
                    }
                } else if (bwfVar.b()) {
                    if (j().x().size() > 100) {
                        c("Too many favorite teams", "Please favorite a sport instead of all the teams; cannot exceed maximum team favorites: 100");
                        return;
                    }
                    bqa b = bqa.b(Integer.valueOf(bwfVar.c.b()), F.e(bwfVar.c.e()));
                    F.a(bwfVar.c);
                    j().a(b);
                    j().a("teams", "add", String.valueOf(bwfVar.c.b()), 0L);
                    if (!this.h.a().contains(Integer.valueOf(bwfVar.c.e()))) {
                        a(bwfVar.c, F(), j());
                    }
                }
                this.h.a(j().o());
                this.h.b(j().r());
                this.h.c(j().x());
                this.h.notifyDataSetChanged();
                j().bm();
                g(bF);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle team click: ", th);
        }
    }

    private bpr aL() {
        if (this.g.size() == 0) {
            return null;
        }
        bwf bwfVar = this.g.get(0);
        if (bwfVar.a != null) {
            IService F = F();
            if (F == null) {
                return null;
            }
            if (F.g(bwfVar.a.g()).size() > 0) {
                return bwfVar.a;
            }
        }
        return null;
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        a(menu);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#006";
    }
}
