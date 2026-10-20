package com.thuuz.gtv.settings.loc;

import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.http.Headers;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ScrollView;
import com.thuuz.gtv.activity.AbstractSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bpu;
import defpackage.bwn;
import defpackage.bwo;
import defpackage.bwr;
import defpackage.bwv;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsEditLocationActivity extends AbstractSettings implements bwr {
    private Integer f;
    private bwv g;
    private boolean h;
    private boolean i;
    private TVProviderFragment j;
    private bwn k;
    private ScrollView l;

    public SettingsEditLocationActivity() {
        super("Set TV Provider");
        this.f = 0;
        this.h = true;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_edit_location);
        this.f = Integer.valueOf(getIntent().getIntExtra(Headers.LOCATION, -1));
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        this.j = (TVProviderFragment) supportFragmentManager.findFragmentByTag("tv");
        this.l = (ScrollView) findViewById(bbq.scroller);
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        if (this.j == null) {
            this.j = new TVProviderFragment();
            Bundle bundle2 = new Bundle();
            bundle2.putInt(Headers.LOCATION, this.f.intValue());
            this.j.setArguments(bundle2);
        }
        if (this.j.isAdded()) {
            beginTransaction.show(this.j);
        } else {
            beginTransaction.add(bbq.tv_provider_fragment, this.j, "tv");
        }
        beginTransaction.commit();
        this.k = new bwn(this, this.j);
        if (-1 == this.f.intValue()) {
            a("New TV Info");
        } else {
            a("TV Info");
        }
        this.g = new bwv(this);
        registerReceiver(this.g, new IntentFilter("set_zip"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            if (this.h) {
                this.h = false;
                new bwo(this, this.j).execute(new Void[0]);
            }
            if (this.j.getNeedWifiMessage().isShown() && T()) {
                this.j.showNeedWifiMessage(false);
            }
            j().k("/prefs/locations/edit");
            if (j().e() && !this.i) {
                this.j.getZipEditText().requestFocus();
                this.i = true;
            }
            c();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        this.j.doRefresh(false);
    }

    @Override // defpackage.bwr
    public boolean a(bpu bpuVar) {
        return this.k.a(bpuVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            if (this.g != null) {
                unregisterReceiver(this.g);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(Headers.LOCATION, this.f.intValue());
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        this.j.validateZip(this);
    }

    @Override // defpackage.bwr
    public void aG() {
        this.k.aG();
    }

    @Override // defpackage.bwr
    public void g(String str) {
        this.k.g(str);
    }

    @Override // defpackage.bwr
    public void aJ() {
        this.k.aJ();
    }

    @Override // defpackage.bwr
    public void aK() {
        finish();
    }

    @Override // defpackage.bwr
    public void aH() {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    @Override // defpackage.bwr
    public void aI() {
        finish();
    }

    public static String h(String str) {
        return "Xa4" + str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#017";
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        e().info("orientation: " + configuration);
    }

    @Override // android.app.Activity
    public void finish() {
        try {
            this.j.onFinish();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        super.finish();
    }

    @Override // defpackage.bwr
    public ScrollView aM() {
        return this.l;
    }
}
