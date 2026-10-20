package com.thuuz.gtv.settings;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.thuuz.gtv.activity.AbstractSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.brb;

/* loaded from: classes.dex */
public abstract class AbstractSettingsOnboardingActivity extends AbstractSettings {
    public AbstractSettingsOnboardingActivity(String str) {
        super(str);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.app.Activity
    public void setContentView(int i) {
        ActionBar supportActionBar;
        super.setContentView(i);
        if (aF() && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.hide();
            aG();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void aA() {
        getIntent();
        if (aF()) {
            ((DrawerLayout) findViewById(bbq.drawer_layout)).setDrawerLockMode(1);
        } else {
            super.aA();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean aF() {
        Intent intent = getIntent();
        return intent != null && intent.getBooleanExtra("ONBOARDING", false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean at() {
        if (aF()) {
            return false;
        }
        return super.at();
    }

    protected void aG() {
        LinearLayout linearLayout = (LinearLayout) findViewById(bbq.left_side_layout);
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(bbr.onboarding_header, (ViewGroup) null);
        linearLayout.addView(inflate, 0);
        TextView textView = (TextView) inflate.findViewById(bbq.next_container);
        textView.setVisibility(0);
        textView.setOnClickListener(new brb(this));
        textView.setText("done");
        linearLayout.setPadding(0, 0, 0, 0);
        ((TextView) inflate.findViewById(bbq.onboarding_header)).setText("Pick Favorites");
    }
}
