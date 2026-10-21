package com.thuuz.gtv.activity.nobar;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractSettings;
import com.thuuz.gtv.settings.SettingsSportsSelectActivity;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.blh;
import defpackage.bli;
import defpackage.blj;
import defpackage.blk;
import defpackage.bll;
import defpackage.bmb;
import defpackage.bpz;
import defpackage.bqa;
import defpackage.bre;
import defpackage.brf;
import defpackage.bzt;
import defpackage.cak;
import defpackage.caz;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class OnboardPage6TeamsActivity extends AbstractOnboard implements bre {
    private ListView f;
    private bll g;
    private BroadcastReceiver h;
    private final List<bpz> i = new ArrayList();
    private boolean j;
    private volatile boolean k;
    private volatile boolean l;
    private Set<bpz> m;
    private Set<bpz> n;
    private String o;
    private boolean p;

    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        try {
            setContentView(bbr.onboard_page_6);
        } catch (Throwable th) {
            if (caz.a(th)) {
                a(th);
            } else {
                c("Problem Showing Splash Screen", "Please quit all apps and try again later.");
                e().log(Level.SEVERE, "problem displaying splash screen; caught and warned: ", th);
            }
        }
        View findViewById = findViewById(bbq.next_container);
        findViewById.setVisibility(0);
        findViewById.setOnClickListener(new blh(this));
        this.f = (ListView) findViewById(bbq.team_list);
        this.g = new bll(this, this, this.i);
        this.f.setAdapter((ListAdapter) this.g);
        this.f.setItemsCanFocus(false);
        this.f.setOnItemClickListener(new bli(this));
        IntentFilter intentFilter = new IntentFilter("single_loc_local");
        this.h = new blj(this);
        registerReceiver(this.h, intentFilter, "com.thuuz.permission.local.broadcast", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aF() {
        this.l = false;
        new brf(null, this).execute(new Void[0]);
        String stringExtra = getIntent().getStringExtra("SOCIAL_ID");
        if (stringExtra != null) {
            this.k = false;
            new blk(stringExtra, this).execute(new Void[0]);
        } else {
            this.k = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aG() {
        try {
            Intent intent = new Intent(k(), (Class<?>) OnboardPage7LeaguesActivity.class);
            if (this.k) {
                intent.putExtra("SOCIAL_PREFS_JSON", this.o);
            }
            startActivity(intent);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            this.g.a(j().x());
            this.g.notifyDataSetChanged();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            if (this.h != null) {
                unregisterReceiver(this.h);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        super.onDestroy();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        try {
            if (isDestroyed()) {
                aj();
            }
            if (!F().g()) {
                new bzt(this).execute(new Void[0]);
            }
            aF();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // defpackage.bre
    public void a(bmb bmbVar) {
        if (bmbVar.b == 0) {
            this.m = bmbVar.a;
            IService F = F();
            if (F != null && bmbVar.a != null) {
                for (bpz bpzVar : bmbVar.a) {
                    j().a(bqa.b(Integer.valueOf(bpzVar.b()), F.e(bpzVar.e())));
                    F.a(bpzVar);
                }
            }
        }
        this.g.a(j().x());
        this.p = bmbVar.b == -666;
        this.l = true;
        b(bmbVar);
    }

    private List<bpz> aH() {
        ArrayList arrayList = new ArrayList();
        if (this.n != null) {
            arrayList.addAll(this.n);
        }
        if (this.m != null) {
            if (this.n != null) {
                this.m.removeAll(this.n);
            }
            arrayList.addAll(this.m);
        }
        return arrayList;
    }

    private synchronized void a(Collection<bpz> collection) {
        this.i.clear();
        this.i.addAll(collection);
    }

    @Override // defpackage.bre
    public void c(Integer num) {
        TextView textView = (TextView) findViewById(bbq.none_found);
        if (this.k && this.l && this.i.size() == 0) {
            textView.setVisibility(0);
            if (this.p) {
                textView.setText(getString(bbt.enable_wireless_loc));
                return;
            } else {
                textView.setText(getString(bbt.no_local_teams));
                return;
            }
        }
        textView.setVisibility(8);
        if (!this.j && this.f.getChildCount() > 0) {
            this.f.requestFocus();
            this.j = this.f.hasFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        try {
            bpz bpzVar = this.i.get(i);
            if (bpzVar != null) {
                if (j().x().contains(Integer.valueOf(bpzVar.b()))) {
                    j().h(bpzVar.b());
                    this.g.a().remove(Integer.valueOf(bpzVar.b()));
                } else {
                    IService F = F();
                    if (F != null) {
                        j().a(bqa.b(Integer.valueOf(bpzVar.b()), F.e(bpzVar.e())));
                        F.a(bpzVar);
                        this.g.a().add(Integer.valueOf(bpzVar.b()));
                        if (!j().o().contains(Integer.valueOf(bpzVar.e()))) {
                            AbstractSettings.a(bpzVar, F(), j());
                        }
                    } else {
                        return;
                    }
                }
                this.g.notifyDataSetChanged();
                j().bm();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public synchronized void b(bmb bmbVar) {
        if (this.k && this.l) {
            List<bpz> aH = aH();
            if (aH.size() == 0) {
                aI();
                finish();
            } else {
                a(aH);
                c(Integer.valueOf(bmbVar.b));
                this.g.notifyDataSetChanged();
                if (j().at()) {
                    cak.a(this, this.f);
                }
            }
        }
    }

    private void aI() {
        Intent intent = new Intent(this, (Class<?>) SettingsSportsSelectActivity.class);
        intent.putExtra("ONBOARDING", true);
        startActivity(intent);
    }
}
