package com.thuuz.gtv.settings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.thuuz.gtv.activity.AbstractSettings;
// [removed legacy tracker import]
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bqv;
import defpackage.bry;
import defpackage.brz;
import defpackage.bsa;
import defpackage.bsc;
import defpackage.bsd;
import defpackage.bse;
import defpackage.bsf;
import defpackage.bsg;
import defpackage.cad;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsAlertsActivity extends AbstractSettings {
    public ProgressDialog f;
    private ListView g;
    private final List<ALERT_TYPE> h;
    private final bse i;
    private bsd j;
    private final SettingsAlertsActivity k;
    private Timer l;

    /* loaded from: classes.dex */
    public enum ALERT_TYPE {
        ALERT_INDEX_GLOBAL,
        ALERT_INDEX_SPORT,
        ALERT_INDEX_FRIEND,
        ALERT_INDEX_FANTASY,
        ALERT_INDEX_SOUNDS,
        ALERT_INDEX_REGISTRATION
    }

    public SettingsAlertsActivity() {
        super("Alerts");
        this.h = new ArrayList();
        this.i = new bse(this, null);
        this.k = this;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_alerts_layout);
        boolean z = j().i() && j().z();
        for (ALERT_TYPE alert_type : ALERT_TYPE.values()) {
            if (z || ALERT_TYPE.ALERT_INDEX_FANTASY != alert_type) {
                this.h.add(alert_type);
            }
        }
        this.j = new bsd(this, this);
        this.g = (ListView) findViewById(bbq.alerts_list);
        this.g.setAdapter((ListAdapter) this.j);
        this.g.setOnItemClickListener(new bsf(this, null));
        bqv.a(new bry(this));
    }

    public void a(Boolean bool) {
        if (this.l != null) {
            this.l.cancel();
            this.l = null;
        }
        runOnUiThread(new brz(this, bool));
    }

    private void aF() {
        this.l = new Timer("requestTimeout");
        this.l.schedule(new bsa(this), Constants.YOZIO_EVENT_QUEUE_MAX_SIZE);
    }

    private void aG() {
        if (System.currentTimeMillis() - 600000 < j().aq()) {
            cad.b(this.k, "Not Updating Registration ID", "An attempt has already been made within the last 10 minutes.  Please try again later.");
            return;
        }
        this.f = ProgressDialog.show(this.k, "", "Updating Registration ID", true);
        j().ap();
        aF();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        j().k("/prefs/alerts");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CheckBox checkBox, boolean z) {
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(z);
        checkBox.setOnCheckedChangeListener(this.i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        bsg bsgVar;
        try {
            ALERT_TYPE alert_type = this.h.get(i);
            if (alert_type == ALERT_TYPE.ALERT_INDEX_GLOBAL) {
                View childAt = this.g.getChildAt(i);
                if (childAt != null && (bsgVar = (bsg) childAt.getTag()) != null) {
                    bsgVar.c.toggle();
                }
            } else if (j().bD()) {
                switch (bsc.a[alert_type.ordinal()]) {
                    case 2:
                        if (j().aC()) {
                            startActivity(new Intent(k(), (Class<?>) SettingsFavSportsActivity.class));
                            break;
                        } else {
                            startActivity(new Intent(k(), (Class<?>) SettingsSportsSelectActivity.class));
                            d("Please select favorites", "");
                            break;
                        }
                    case 3:
                        startActivity(new Intent(k(), (Class<?>) SettingsFriendAlertsActivity.class));
                        break;
                    case 4:
                        C();
                        break;
                    case 5:
                        startActivity(new Intent(k(), (Class<?>) SettingsSoundsActivity.class));
                        break;
                    case 6:
                        aG();
                        break;
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#014";
    }
}
