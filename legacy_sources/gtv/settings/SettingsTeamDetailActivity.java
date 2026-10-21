package com.thuuz.gtv.settings;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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
import defpackage.bpz;
import defpackage.bqa;
import defpackage.bva;
import defpackage.bvb;
import defpackage.bvc;
import defpackage.bvd;
import defpackage.bve;
import defpackage.bvj;
import defpackage.bvk;
import defpackage.bvm;
import defpackage.bvn;
import defpackage.bvo;
import defpackage.bvp;
import defpackage.bvq;
import defpackage.bvr;
import defpackage.bvs;
import defpackage.bvt;
import defpackage.bvu;
import defpackage.bwg;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsTeamDetailActivity extends AbstractSettings {
    public CheckBox A;
    public App.ALERT_LEVEL B;
    bvu C;
    public bqa D;
    public int f;
    TextView g;
    View h;
    TextView i;
    TextView j;
    public CheckBox k;
    View l;
    TextView m;
    TextView n;
    public View o;
    public CheckBox p;
    public RadioButton q;
    public RadioButton r;
    public RadioButton s;
    View t;
    TextView u;
    TextView v;
    public CheckBox w;
    View x;
    TextView y;
    TextView z;

    public static /* synthetic */ void a(SettingsTeamDetailActivity settingsTeamDetailActivity) {
        settingsTeamDetailActivity.aF();
    }

    public SettingsTeamDetailActivity() {
        super("Alert Settings");
        this.C = new bvu(this, null);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_team_details);
        this.f = getIntent().getIntExtra("team_id", -1);
        if (this.f == -1) {
            if (bundle != null) {
                this.f = bundle.getInt("team_id", -1);
            }
            if (this.f == -1) {
                e().warning("no id for team. bailing back");
                finish();
                return;
            }
        }
        this.D = j().g(this.f);
        if (this.D == null) {
            e().log(Level.SEVERE, "No fav team settings found, bailing.");
            finish();
            return;
        }
        this.g = (TextView) findViewById(bbq.subtitle);
        this.h = findViewById(bbq.reminder_container);
        this.i = (TextView) findViewById(bbq.game_reminder_title);
        this.j = (TextView) findViewById(bbq.game_reminder_subtitle);
        this.k = (CheckBox) findViewById(bbq.game_reminder_toggle);
        this.l = findViewById(bbq.excitement_container);
        this.m = (TextView) findViewById(bbq.game_excitement_title);
        this.n = (TextView) findViewById(bbq.game_excitement_subtitle);
        this.o = findViewById(bbq.fan_level_container);
        this.p = (CheckBox) findViewById(bbq.gec_toggle);
        this.q = (RadioButton) findViewById(bbq.radio_casual);
        this.q.setTag(App.ALERT_LEVEL.ALERT_LEVEL_CASUAL);
        this.r = (RadioButton) findViewById(bbq.radio_enthuuziast);
        this.r.setTag(App.ALERT_LEVEL.ALERT_LEVEL_ENTHUUZIAST);
        this.s = (RadioButton) findViewById(bbq.radio_fanatic);
        this.s.setTag(App.ALERT_LEVEL.ALERT_LEVEL_FANATIC);
        this.t = findViewById(bbq.scoring_plays_container);
        this.u = (TextView) findViewById(bbq.scoring_plays_title);
        this.v = (TextView) findViewById(bbq.scoring_plays_subtitle);
        this.w = (CheckBox) findViewById(bbq.scoring_plays_toggle);
        this.x = findViewById(bbq.game_updates_container);
        this.y = (TextView) findViewById(bbq.game_updates_title);
        this.z = (TextView) findViewById(bbq.game_updates_subtitle);
        this.A = (CheckBox) findViewById(bbq.game_updates_toggle);
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("team_id", this.f);
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
                a(this.f, new bva(this));
                return true;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        IService F = F();
        if (F != null) {
            if (F.a(Integer.valueOf(this.f)) != null) {
                c();
            } else {
                new bwg(this).execute(new Void[0]);
            }
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        try {
            IService F = F();
            if (F != null && d()) {
                bpz a = F.a(Integer.valueOf(this.f));
                if (a == null) {
                    if (F.C()) {
                        e().warning("Can't get team info from service, bailing for team ID: " + this.f);
                        if (d()) {
                            b("Team Info Not Available", bbt.pls_try_again_later);
                        }
                        finish();
                        return;
                    }
                    return;
                }
                AlertSettings e = F.e(a.e());
                if (e == null) {
                    if (U() == null) {
                        try {
                            AlertDialog.Builder builder = new AlertDialog.Builder(k());
                            builder.setTitle("League Not Available");
                            builder.setMessage("Cannot edit preferences for " + a.a() + " because its league is not available at this time. Please try again later.");
                            builder.setCancelable(true);
                            builder.setOnCancelListener(new bvm(this));
                            AlertDialog create = builder.create();
                            create.setButton(-1, "Ok", new bvn(this, create));
                            a(create);
                            if (!isDestroyed()) {
                                create.show();
                            } else {
                                return;
                            }
                        } catch (Throwable th) {
                            e().log(Level.SEVERE, "problem: ", th);
                        }
                    } else {
                        return;
                    }
                } else {
                    this.m.setText(e.c(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_EXCITEMENT));
                    this.n.setText(e.d(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_EXCITEMENT));
                    this.i.setText(e.c(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_REMINDERS));
                    this.j.setText(e.d(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_REMINDERS));
                    this.u.setText(e.c(AlertSettings.ALERT_TYPE.ALERT_TYPE_SCORING_PLAYS));
                    this.v.setText(e.d(AlertSettings.ALERT_TYPE.ALERT_TYPE_SCORING_PLAYS));
                    this.y.setText(e.c(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_UPDATES));
                    this.z.setText(e.d(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_UPDATES));
                    boolean a2 = e.a(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_REMINDERS);
                    this.h.setVisibility(a2 ? 0 : 8);
                    boolean a3 = e.a(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_EXCITEMENT);
                    this.l.setVisibility(a3 ? 0 : 8);
                    boolean a4 = e.a(AlertSettings.ALERT_TYPE.ALERT_TYPE_SCORING_PLAYS);
                    this.t.setVisibility(a4 ? 0 : 8);
                    boolean a5 = e.a(AlertSettings.ALERT_TYPE.ALERT_TYPE_GAME_UPDATES);
                    this.x.setVisibility(a5 ? 0 : 8);
                    if (!j().bD() && this.D.i()) {
                        F_();
                    }
                    if (j().e() && !a2) {
                        if (a5) {
                            this.A.requestFocus();
                        } else if (a4) {
                            this.w.requestFocus();
                        } else if (a3) {
                            this.p.requestFocus();
                        }
                    }
                }
                this.g.setText(a.c());
                this.k.setChecked(this.D.f().booleanValue());
                this.k.setOnCheckedChangeListener(new bvo(this));
                this.i.setOnClickListener(new bvp(this));
                this.j.setOnClickListener(new bvq(this));
                this.w.setChecked(this.D.g().booleanValue());
                this.w.setOnCheckedChangeListener(new bvr(this));
                this.u.setOnClickListener(new bvs(this));
                this.v.setOnClickListener(new bvt(this));
                this.A.setChecked(this.D.h().booleanValue());
                this.A.setOnCheckedChangeListener(new bvb(this));
                this.y.setOnClickListener(new bvc(this));
                this.z.setOnClickListener(new bvd(this));
                this.p.setChecked(this.D.b().booleanValue());
                this.p.setOnCheckedChangeListener(new bve(this));
                this.m.setOnClickListener(new bvj(this));
                this.n.setOnClickListener(new bvk(this));
                this.B = this.D.d();
                switch (this.B) {
                    case ALERT_LEVEL_CASUAL:
                        this.q.setChecked(true);
                        break;
                    case ALERT_LEVEL_ENTHUUZIAST:
                        this.r.setChecked(true);
                        break;
                    case ALERT_LEVEL_FANATIC:
                        this.s.setChecked(true);
                        break;
                }
                this.q.setOnCheckedChangeListener(this.C);
                this.r.setOnCheckedChangeListener(this.C);
                this.s.setOnCheckedChangeListener(this.C);
                this.o.setVisibility(this.D.b().booleanValue() ? 0 : 8);
            }
        } catch (Throwable th2) {
            e().log(Level.SEVERE, "problem: ", th2);
        }
    }

    public void aF() {
        boolean bF = j().bF();
        j().a(this.D);
        j().bm();
        g(bF);
        if (!j().bD() && this.D.i()) {
            F_();
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#006";
    }
}
