package com.thuuz.gtv.activity.fantasy;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.data.FantasySite;
import com.thuuz.gtv.settings.AbstractLoginRequiredSettingsActivity;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbs;
import defpackage.bgf;
import defpackage.bgg;
import defpackage.bgh;
import defpackage.bgi;
import defpackage.bgj;
import defpackage.bgk;
import defpackage.bgl;
import defpackage.bgm;
import defpackage.bgn;
import defpackage.bgo;
import defpackage.bpe;
import defpackage.bpg;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SettingsFantasyTeamsActivity extends AbstractLoginRequiredSettingsActivity {
    private boolean f;
    private bgk g;
    private TextView h;
    private ListView i;
    private Button j;
    private final List<bgo> k;
    private bgj l;
    private bgn m;
    private bgl n;
    private bgm o;

    public SettingsFantasyTeamsActivity() {
        super("Fantasy Settings");
        this.k = new ArrayList();
        this.l = null;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        bgf bgfVar = null;
        setContentView(bbr.settings_fantasy_teams_activity);
        this.j = (Button) findViewById(bbq.import_teams);
        this.j.setOnClickListener(new bgf(this));
        this.h = (TextView) findViewById(bbq.no_teams);
        this.h.setVisibility(8);
        this.i = (ListView) findViewById(bbq.list);
        this.i.setItemsCanFocus(true);
        a(this.i);
        this.l = new bgj(this, this);
        this.i.setAdapter((ListAdapter) this.l);
        this.m = new bgn(this, bgfVar);
        this.n = new bgl(this, bgfVar);
        this.o = new bgm(this, bgfVar);
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        a(menu);
        getSupportMenuInflater().inflate(bbs.add_fantasy, menu);
        return true;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        try {
            if (menuItem.getItemId() == bbq.add_fant_option) {
                g();
                return true;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        if (F() != null) {
            c();
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        IService F = F();
        if (d() && F != null) {
            aF();
            if (this.g == null) {
                this.g = new bgk(this);
                this.g.execute(new Void[0]);
            } else {
                e().info("ignoring refresh because one already ongoing");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
        j().k("/prefs/fantasy/teams");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(boolean z) {
        this.f = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aF() {
        try {
            IService F = F();
            if (F != null) {
                ArrayList arrayList = new ArrayList();
                Iterator<bpe> it = F.r().iterator();
                while (it.hasNext()) {
                    Iterator<FantasySite> it2 = F.k(it.next().a()).iterator();
                    while (it2.hasNext()) {
                        FantasySite next = it2.next();
                        Iterator<bpg> it3 = F.a(next.a(), next.b()).iterator();
                        boolean z = true;
                        while (it3.hasNext()) {
                            arrayList.add(new bgo(this, z, it3.next()));
                            z = false;
                        }
                        bpg bpgVar = new bpg(new JSONObject());
                        bpgVar.a(Integer.valueOf(next.b()));
                        bpgVar.b(Integer.valueOf(next.a()));
                        bpgVar.a(-1);
                        bpgVar.a("Logout");
                        arrayList.add(new bgo(this, z, bpgVar));
                        bpg bpgVar2 = new bpg(new JSONObject());
                        bpgVar2.a(Integer.valueOf(next.b()));
                        bpgVar2.b(Integer.valueOf(next.a()));
                        bpgVar2.a(-2);
                        bpgVar2.a("Update team list from " + next.c());
                        arrayList.add(new bgo(this, false, bpgVar2));
                    }
                }
                boolean z2 = arrayList.isEmpty() && this.f;
                this.h.setVisibility(z2 ? 0 : 8);
                this.i.setVisibility(z2 ? 8 : 0);
                this.j.setText(z2 ? "Import Teams" : "Import More Teams");
                expandWidthByDevice(this.j);
                this.k.clear();
                this.k.addAll(arrayList);
                this.l.notifyDataSetChanged();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        try {
            bgo bgoVar = this.k.get(i);
            if (bgoVar != null) {
                bpg bpgVar = bgoVar.b;
                if (bpgVar.a() == -1) {
                    try {
                        a(bpgVar);
                    } catch (Throwable th) {
                        e().log(Level.SEVERE, "problem: ", th);
                    }
                } else if (bpgVar.a() == -2) {
                    b(bpgVar);
                } else {
                    a(bpgVar.a(), bpgVar.e());
                }
            }
        } catch (Throwable th2) {
            e().log(Level.SEVERE, "Cannot handle: ", th2);
        }
    }

    private void a(bpg bpgVar) {
        FantasySite b;
        AlertDialog.Builder builder = new AlertDialog.Builder(k());
        StringBuilder sb = new StringBuilder("Logout");
        IService F = F();
        if (F != null && (b = F.b(bpgVar.c(), bpgVar.b())) != null) {
            sb.append(" from ");
            sb.append(b.c());
        }
        sb.append("?");
        builder.setMessage(sb.toString()).setCancelable(true).setPositiveButton("Yes", new bgh(this, bpgVar)).setNegativeButton("No", new bgg(this));
        AlertDialog create = builder.create();
        a(create);
        if (!isDestroyed()) {
            create.show();
        }
    }

    private void b(bpg bpgVar) {
        int c = bpgVar.c();
        IService F = F();
        if (F != null) {
            new bgi(this, F.b(c, bpgVar.b()), this).execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#022";
    }
}
