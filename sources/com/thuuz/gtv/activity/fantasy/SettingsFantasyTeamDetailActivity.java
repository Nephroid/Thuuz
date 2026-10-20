package com.thuuz.gtv.activity.fantasy;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.FantasySettingsCustomizations;
import com.thuuz.gtv.settings.AbstractLoginRequiredSettingsActivity;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.bfy;
import defpackage.bga;
import defpackage.bgb;
import defpackage.bgc;
import defpackage.bgd;
import defpackage.bge;
import defpackage.bpg;
import defpackage.bph;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SettingsFantasyTeamDetailActivity extends AbstractLoginRequiredSettingsActivity {
    private int f;
    private ListView g;
    private final bgd h;
    private bgb i;
    private final List<bga> j;
    private bph k;

    public SettingsFantasyTeamDetailActivity() {
        super("Fantasy Alert Settings");
        this.f = -1;
        this.h = new bgd(this, null);
        this.j = new ArrayList();
        this.k = new bph(new JSONObject());
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_fantasy_team_detail_activity);
        this.f = getIntent().getIntExtra("team_id", -1);
        if (this.f == -1) {
            if (bundle != null) {
                this.f = bundle.getInt("team_id", -1);
            }
            if (this.f == -1) {
                e().info("no team_id for fantasy settings. bailing.");
                finish();
                return;
            }
        }
        String stringExtra = getIntent().getStringExtra("team_name");
        if (stringExtra == null) {
            if (bundle != null) {
                stringExtra = bundle.getString("team_name");
            }
            if (stringExtra == null) {
                e().info("no team_name for fantasy settings. bailing.");
                finish();
                return;
            }
        }
        ((TextView) findViewById(bbq.subtitle)).setText(stringExtra);
        this.g = (ListView) findViewById(bbq.list);
        this.i = new bgb(this, this);
        this.g.setAdapter((ListAdapter) this.i);
        this.g.setOnItemClickListener(new bgc(this, null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("team_id", this.f);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        IService F = F();
        if (F == null) {
            if (d()) {
                b("Cannot Access Service", bbt.pls_try_again_later);
            }
            finish();
        } else if (!F.g()) {
            c((AbstractActivity) this);
        } else {
            a(true, F);
        }
    }

    public boolean a(boolean z, IService iService) {
        boolean z2 = false;
        if (iService == null) {
            if (d()) {
                b("Cannot Access Service", bbt.pls_try_again_later);
            }
            finish();
            return false;
        }
        bpg l = iService.l(this.f);
        if (l == null) {
            if (d()) {
                b("Cannot Access Team", bbt.pls_try_again_later);
            }
            finish();
            return false;
        }
        this.k = j().f(this.f);
        if (this.k == null) {
            this.k = new bph(new JSONObject());
            this.k.c(false);
            z2 = true;
        }
        this.k.a(this.f);
        FantasySettingsCustomizations o = iService.o(l.b());
        if (o != null) {
            if (z2) {
                this.k.b(o.b(FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_COMPETITION));
                this.k.c(o.b(FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_SCORING_POSITION));
                this.k.d(o.b(FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_HOT_PLAYER));
                this.k.d(o.b(FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_INJURY));
            }
            this.j.clear();
            if (o.a(FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_COMPETITION)) {
                this.j.add(new bga(this, FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_COMPETITION, o));
            }
            if (o.a(FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_SCORING_POSITION)) {
                this.j.add(new bga(this, FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_SCORING_POSITION, o));
            }
            if (o.a(FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_HOT_PLAYER)) {
                this.j.add(new bga(this, FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_HOT_PLAYER, o));
            }
            if (o.a(FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_INJURY)) {
                this.j.add(new bga(this, FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_INJURY, o));
            }
        } else {
            if (z) {
                e().severe("cannot find settings for fantasy competition: " + l.b());
            }
            this.j.clear();
            this.j.add(new bga(this, FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_COMPETITION, null));
            this.j.add(new bga(this, FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_SCORING_POSITION, null));
            this.j.add(new bga(this, FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_HOT_PLAYER, null));
            this.j.add(new bga(this, FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_INJURY, null));
        }
        this.i.notifyDataSetChanged();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!j().bD() && this.k.f()) {
            F_();
        }
        j().k("/fantasy/alert_settings");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CheckBox checkBox, boolean z) {
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(z);
        checkBox.setOnCheckedChangeListener(this.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        bge bgeVar;
        try {
            View childAt = this.g.getChildAt(i);
            if (childAt != null && (bgeVar = (bge) childAt.getTag()) != null) {
                bgeVar.d.toggle();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#022";
    }

    private void c(AbstractActivity abstractActivity) {
        try {
            j().L();
            new bfy(this, abstractActivity).execute(new Void[0]);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "service connect problem starting sportinfo async: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }
}
