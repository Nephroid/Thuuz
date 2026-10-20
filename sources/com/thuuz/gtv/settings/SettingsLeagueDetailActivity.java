package com.thuuz.gtv.settings;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractSettings;
import com.thuuz.gtv.data.AlertSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbs;
import defpackage.bbt;
import defpackage.bpr;
import defpackage.bpt;
import defpackage.btj;
import defpackage.btk;
import defpackage.btm;
import defpackage.bzt;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsLeagueDetailActivity extends AbstractSettings {
    boolean f;
    int g;
    String h;
    public LinearLayout i;
    CheckBox j;
    public RadioButton k;
    public RadioButton l;
    public RadioButton m;
    public App.ALERT_LEVEL n;
    btm o;
    public bpt p;

    public SettingsLeagueDetailActivity() {
        super("Alert Settings");
        this.o = new btm(this, null);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_league_detail_layout);
        this.g = getIntent().getIntExtra("league_id", -1);
        if (this.g == -1) {
            if (bundle != null) {
                this.g = bundle.getInt("league_id", -1);
                this.f = bundle.getBoolean("is_league_group", false);
                this.h = bundle.getString("full_name");
            }
            if (this.g == -1) {
                e().warning("no id for team. bailing back to league selection settings!");
                finish();
                return;
            }
        } else {
            this.h = getIntent().getStringExtra("full_name");
            this.f = getIntent().getBooleanExtra("is_league_group", false);
        }
        if (this.f) {
            this.p = j().c(this.g);
        } else {
            this.p = j().a(this.g);
        }
        if (this.p == null) {
            this.p = bpt.a(Integer.valueOf(this.g), (AlertSettings) null);
            if (this.f) {
                j().b(this.p);
            } else {
                j().a(this.p);
            }
            j().bm();
        }
        if (this.p == null) {
            e().log(Level.WARNING, "No league settings found, bailing back.");
            finish();
            return;
        }
        ((TextView) findViewById(bbq.subtitle)).setText(this.h);
        this.i = (LinearLayout) findViewById(bbq.fan_level_container);
        this.j = (CheckBox) findViewById(bbq.gec_toggle);
        this.k = (RadioButton) findViewById(bbq.radio_casual);
        this.k.setTag(App.ALERT_LEVEL.ALERT_LEVEL_CASUAL);
        this.l = (RadioButton) findViewById(bbq.radio_enthuuziast);
        this.l.setTag(App.ALERT_LEVEL.ALERT_LEVEL_ENTHUUZIAST);
        this.m = (RadioButton) findViewById(bbq.radio_fanatic);
        this.m.setTag(App.ALERT_LEVEL.ALERT_LEVEL_FANATIC);
        this.j.setChecked(this.p.b().booleanValue());
        this.j.setOnCheckedChangeListener(new btj(this));
        this.n = this.p.d();
        switch (this.n) {
            case ALERT_LEVEL_CASUAL:
                this.k.setChecked(true);
                break;
            case ALERT_LEVEL_ENTHUUZIAST:
                this.l.setChecked(true);
                break;
            case ALERT_LEVEL_FANATIC:
                this.m.setChecked(true);
                break;
        }
        this.k.setOnCheckedChangeListener(this.o);
        this.l.setOnCheckedChangeListener(this.o);
        this.m.setOnCheckedChangeListener(this.o);
        this.i.setVisibility(this.p.b().booleanValue() ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("league_id", this.g);
        bundle.putBoolean("is_league_group", this.f);
        bundle.putString("full_name", this.h);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        String str = "Game";
        IService F = F();
        if (F != null) {
            bpr c = F.c(this.g);
            if (c != null) {
                str = c.c().e();
            }
            if (!F.g()) {
                new bzt(this).execute(new Void[0]);
            }
        }
        String str2 = str;
        ((TextView) findViewById(bbq.game_excitement_title)).setText(getResources().getString(bbt.game_excitement, str2));
        ((TextView) findViewById(bbq.game_excitement_subtitle)).setText(getResources().getString(bbt.get_alerts, str2.toLowerCase()));
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            super.onCreateOptionsMenu(menu);
            getSupportMenuInflater().inflate(bbs.delete, menu);
            a(menu, bbq.delete_option);
            return true;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
            return true;
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        try {
            if (menuItem.getItemId() == bbq.delete_option) {
                a(this.g, this.f, this.h, new btk(this));
                return true;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!j().bD() && this.p.b().booleanValue()) {
            F_();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aF() {
        boolean bF = j().bF();
        if (this.f) {
            j().b(this.p);
        } else {
            j().a(this.p);
        }
        j().bm();
        g(bF);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#006";
    }
}
