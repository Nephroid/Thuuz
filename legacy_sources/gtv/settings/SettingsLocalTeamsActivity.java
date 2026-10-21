package com.thuuz.gtv.settings;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.bmb;
import defpackage.bpz;
import defpackage.bqa;
import defpackage.bqy;
import defpackage.bre;
import defpackage.brf;
import defpackage.btn;
import defpackage.bto;
import defpackage.btp;
import defpackage.btq;
import defpackage.btr;
import defpackage.bzt;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsLocalTeamsActivity extends AbstractSettings implements bre {
    private btr f;
    private final List<bpz> g;
    private BroadcastReceiver h;
    private boolean i;
    private ListView j;

    public SettingsLocalTeamsActivity() {
        super("Local Teams");
        this.g = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aF() {
        if (j().e("android.permission.ACCESS_COARSE_LOCATION")) {
            IntentFilter intentFilter = new IntentFilter("single_loc_local");
            this.h = new btn(this);
            registerReceiver(this.h, intentFilter, "com.thuuz.permission.local.broadcast", null);
            new brf(PendingIntent.getBroadcast(this, 0, new Intent("single_loc_local"), 134217728), this).execute(new Void[0]);
            return;
        }
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(k());
            builder.setTitle("Local Teams").setCancelable(true).setMessage("You have declined location services for Thuuz.  To enable go to Settings | Apps | Thuuz Sports | Permissions and turn Location on.");
            AlertDialog create = builder.create();
            create.setButton(-1, "Ok", new bto(this, create));
            a(create);
            create.show();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_local_teams_activity);
        this.j = (ListView) findViewById(bbq.team_list);
        this.f = new btr(this, this, this.g);
        this.j.setAdapter((ListAdapter) this.f);
        this.j.setOnItemClickListener(new btp(this));
        bqy.a(new btq(this));
        if (!b("android.permission.ACCESS_COARSE_LOCATION", true)) {
            aF();
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
        IService F = F();
        if (F != null) {
            if (!F.g()) {
                new bzt(this).execute(new Void[0]);
            } else {
                c();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
        j().k("/prefs/local_teams");
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        try {
            this.f.a(j().x());
            this.f.notifyDataSetChanged();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        try {
            bpz bpzVar = this.g.get(i);
            if (bpzVar != null) {
                if (j().x().contains(Integer.valueOf(bpzVar.b()))) {
                    j().h(bpzVar.b());
                    j().a("teams", "delete", String.valueOf(bpzVar.b()), 0L);
                    this.f.a().remove(Integer.valueOf(bpzVar.b()));
                } else {
                    IService F = F();
                    if (F != null) {
                        j().a(bqa.b(Integer.valueOf(bpzVar.b()), F.e(bpzVar.e())));
                        F.a(bpzVar);
                        j().a("teams", "add", String.valueOf(bpzVar.b()), 0L);
                        this.f.a().add(Integer.valueOf(bpzVar.b()));
                        j().a("teams", "delete", String.valueOf(bpzVar.b()), 0L);
                        if (!j().o().contains(Integer.valueOf(bpzVar.e()))) {
                            a(bpzVar, F(), j());
                        }
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

    @Override // defpackage.bre
    public void a(bmb bmbVar) {
        b(bmbVar);
        this.f.notifyDataSetChanged();
    }

    @Override // defpackage.bre
    public void c(Integer num) {
        TextView textView = (TextView) findViewById(bbq.none_found);
        if (this.g.size() == 0) {
            textView.setVisibility(0);
            if (num.intValue() == -666) {
                textView.setText(getString(bbt.enable_wireless_loc));
                return;
            } else {
                textView.setText(getString(bbt.no_local_teams));
                return;
            }
        }
        textView.setVisibility(8);
        if (!this.i && this.j.getChildCount() > 0) {
            this.j.requestFocus();
            this.i = this.j.hasFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    private synchronized void b(bmb bmbVar) {
        if (bmbVar.b == 0) {
            this.g.clear();
            this.g.addAll(bmbVar.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#006";
    }
}
