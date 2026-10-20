package com.thuuz.gtv.settings;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbs;
import defpackage.bbt;
import defpackage.bpr;
import defpackage.bps;
import defpackage.bpt;
import defpackage.bpz;
import defpackage.bqa;
import defpackage.bsv;
import defpackage.bsx;
import defpackage.bsy;
import defpackage.bsz;
import defpackage.bta;
import defpackage.btb;
import defpackage.bwg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsFavSportsActivity extends AbstractSettings {
    private TextView f;
    private ListView g;
    private final List<bsy> h;
    private bsx i;
    private bsz j;
    private bta k;
    private btb l;
    private boolean m;

    public SettingsFavSportsActivity() {
        super("My Favorites");
        this.h = new ArrayList();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        bsv bsvVar = null;
        setContentView(bbr.settings_fav_sports_layout);
        this.f = (TextView) findViewById(bbq.no_sports_text);
        this.g = (ListView) findViewById(bbq.fav_sports_list);
        this.g.setItemsCanFocus(true);
        a(this.g);
        this.i = new bsx(this, this);
        this.g.setAdapter((ListAdapter) this.i);
        Button button = (Button) findViewById(bbq.add_fav);
        button.setOnClickListener(new bsv(this));
        expandWidthByDevice(button);
        this.j = new bsz(this, bsvVar);
        this.l = new btb(this, bsvVar);
        this.k = new bta(this, bsvVar);
        if (getIntent() != null && getIntent().hasExtra("showPleaseSetFavorites")) {
            E_();
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        c();
        new bwg(this).execute(new Void[0]);
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(bbs.add_fav, menu);
        return true;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (bbq.add_fav_option != menuItem.getItemId()) {
            return super.onOptionsItemSelected(menuItem);
        }
        V();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
        j().k("/prefs/my_sports_teams");
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        try {
            if (F() != null) {
                aF();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    private synchronized void aF() {
        String str;
        boolean z;
        boolean z2 = true;
        synchronized (this) {
            try {
                IService F = F();
                if (F != null) {
                    Set<Integer> o = j().o();
                    ArrayList<bpr> arrayList = new ArrayList();
                    Iterator<Integer> it = o.iterator();
                    while (it.hasNext()) {
                        bpr c = F.c(it.next().intValue());
                        if (c != null) {
                            arrayList.add(c);
                        }
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (bpr bprVar : arrayList) {
                        bpt a = j().a(bprVar.g());
                        bsy bsyVar = new bsy(this, (String) null, a != null ? a.b().booleanValue() : false, bprVar);
                        if (bprVar.i()) {
                            bsyVar.c = getString(bbt.all_major_conferences);
                        }
                        if ("ATP".equals(bprVar.h())) {
                            bsyVar.c = "All ATP";
                        }
                        arrayList2.add(bsyVar);
                    }
                    Set<Integer> r = j().r();
                    ArrayList<bps> arrayList3 = new ArrayList();
                    Iterator<Integer> it2 = r.iterator();
                    while (it2.hasNext()) {
                        bps d = F.d(it2.next().intValue());
                        if (d != null) {
                            arrayList3.add(d);
                        }
                    }
                    for (bps bpsVar : arrayList3) {
                        bpt c2 = j().c(bpsVar.c());
                        arrayList2.add(new bsy(this, (String) null, c2 != null ? c2.b().booleanValue() : false, bpsVar));
                    }
                    Collections.sort(arrayList2);
                    if (arrayList2.size() > 0) {
                        ((bsy) arrayList2.get(0)).a = "My Sports";
                    }
                    Map<Integer, bpz> k = F.k();
                    ArrayList<bpz> arrayList4 = new ArrayList();
                    Iterator<Integer> it3 = k.keySet().iterator();
                    boolean z3 = false;
                    while (it3.hasNext()) {
                        bpz a2 = F.a(it3.next());
                        if (a2 != null) {
                            bpr c3 = F.c(a2.e());
                            if (c3 == null) {
                                e().warning("cannot find league for team: " + a2.c());
                            } else {
                                z = c3.c().j() ? true : z3;
                                arrayList4.add(a2);
                            }
                        } else {
                            z = z3;
                        }
                        z3 = z;
                    }
                    Collections.sort(arrayList4);
                    for (bpz bpzVar : arrayList4) {
                        bqa g = j().g(bpzVar.b());
                        if (g != null) {
                            if (z2) {
                                str = z3 ? "My Teams and Players" : "My Teams";
                                z2 = false;
                            } else {
                                str = null;
                            }
                            arrayList2.add(new bsy(this, str, g.i(), bpzVar));
                        }
                        z2 = z2;
                    }
                    if (arrayList2.size() == 0) {
                        this.f.setVisibility(0);
                        this.g.setVisibility(4);
                    } else {
                        this.f.setVisibility(8);
                        this.g.setVisibility(0);
                    }
                    this.h.clear();
                    this.h.addAll(arrayList2);
                    this.i.notifyDataSetChanged();
                    if (!this.m && this.g.getChildCount() > 0) {
                        this.g.requestFocus();
                        this.m = this.g.hasFocus();
                        e().info("requested focus, hasFocused: " + this.m);
                    }
                }
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        bsy bsyVar;
        Intent intent;
        try {
            IService F = F();
            if (F != null && (bsyVar = this.h.get(i)) != null) {
                if (bsyVar.f) {
                    String b = F.d(bsyVar.e).b();
                    Intent intent2 = new Intent(k(), (Class<?>) SettingsLeagueDetailActivity.class);
                    intent2.putExtra("is_league_group", bsyVar.f);
                    intent2.putExtra("league_id", bsyVar.e);
                    intent2.putExtra("full_name", b);
                    intent = intent2;
                } else if (bsyVar.g) {
                    String f = F.c(bsyVar.e).f();
                    Intent intent3 = new Intent(k(), (Class<?>) SettingsLeagueDetailActivity.class);
                    intent3.putExtra("is_league_group", bsyVar.f);
                    intent3.putExtra("league_id", bsyVar.e);
                    intent3.putExtra("full_name", f);
                    intent = intent3;
                } else if (bsyVar.h) {
                    Intent intent4 = new Intent(k(), (Class<?>) SettingsTeamDetailActivity.class);
                    intent4.putExtra("team_id", bsyVar.e);
                    intent = intent4;
                } else {
                    e().severe("cannot determine item type");
                    return;
                }
                startActivity(intent);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#006";
    }
}
