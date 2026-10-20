package com.thuuz.gtv.activity.nobar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.thuuz.gtv.IService;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.blo;
import defpackage.blp;
import defpackage.blq;
import defpackage.bpr;
import defpackage.bpt;
import defpackage.bpz;
import defpackage.bwg;
import defpackage.cak;
import defpackage.caz;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class OnboardPage7LeaguesActivity extends AbstractOnboard {
    private blq f;
    private final List<bpr> g = new ArrayList();
    private final Set<bpr> h = new TreeSet();
    private final Set<bpr> i = new TreeSet();
    private ListView j;

    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        try {
            setContentView(bbr.onboard_page_7);
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
        findViewById.setOnClickListener(new blo(this));
        this.j = (ListView) findViewById(bbq.team_list);
        this.f = new blq(this, this, this.g);
        this.j.setAdapter((ListAdapter) this.f);
        this.j.setOnItemClickListener(new blp(this));
    }

    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        super.c();
        aF();
    }

    private void b(IService iService) {
        bpr c;
        if (iService != null) {
            try {
                String stringExtra = getIntent().getStringExtra("SOCIAL_PREFS_JSON");
                if (stringExtra != null) {
                    JSONArray jSONArray = new JSONObject(stringExtra).getJSONObject("recommended_prefs").getJSONArray("leagues");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        if (!jSONArray.get(i).equals(null) && (c = iService.c(jSONArray.getJSONObject(i).getInt("id"))) != null) {
                            if (c.g() <= 0 || c.f().length() == 0 || c.g() <= 0) {
                                e().severe("cannot get required team info: " + c.ar().toString());
                            } else {
                                this.i.add(c);
                                a(iService, c);
                            }
                        }
                    }
                }
                aF();
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    private void a(IService iService, bpr bprVar) {
        if (iService != null) {
            j().a(bpt.a(Integer.valueOf(bprVar.g()), iService.e(bprVar.g())));
        }
    }

    private void aF() {
        IService F = F();
        if (F != null) {
            List<bpr> y = F.y();
            TreeSet<Integer> x = j().x();
            ArrayList arrayList = new ArrayList();
            Iterator<Integer> it = x.iterator();
            while (it.hasNext()) {
                bpz a = F.a(it.next());
                if (a != null) {
                    bpr a2 = a.a(y);
                    arrayList.add(a2);
                    a(F, a2);
                }
            }
            this.f.a(j().o());
            this.h.clear();
            this.h.addAll(arrayList);
            TreeSet treeSet = new TreeSet(this.h);
            TreeSet treeSet2 = new TreeSet(this.i);
            treeSet2.removeAll(treeSet);
            this.g.clear();
            this.g.addAll(treeSet);
            this.g.addAll(treeSet2);
            this.f.notifyDataSetChanged();
            if (j().at()) {
                cak.a(this, this.j);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aG() {
        try {
            startActivity(new Intent(k(), (Class<?>) OnboardPage8Activity.class));
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            this.f.a(j().o());
            this.f.notifyDataSetChanged();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        try {
            if (isDestroyed()) {
                aj();
            }
            new bwg(this).execute(new Void[0]);
            b(F());
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        try {
            bpr bprVar = this.g.get(i);
            if (bprVar != null) {
                if (j().o().contains(Integer.valueOf(bprVar.g()))) {
                    j().b(bprVar.g());
                    this.f.a().remove(Integer.valueOf(bprVar.g()));
                } else {
                    IService F = F();
                    if (F != null) {
                        a(F, bprVar);
                        if (F.g(bprVar.g()).size() > 0) {
                            Iterator<Integer> it = j().r().iterator();
                            while (it.hasNext()) {
                                j().d(it.next().intValue());
                            }
                        }
                        this.f.a().add(Integer.valueOf(bprVar.g()));
                    } else {
                        return;
                    }
                }
                this.f.notifyDataSetChanged();
                j().bm();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
