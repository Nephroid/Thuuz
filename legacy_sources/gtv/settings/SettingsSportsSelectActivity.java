package com.thuuz.gtv.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thuuz.gtv.IService;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bpr;
import defpackage.bpx;
import defpackage.buw;
import defpackage.bux;
import defpackage.buy;
import defpackage.buz;
import defpackage.bzt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsSportsSelectActivity extends AbstractSettingsOnboardingActivity {
    private static final bpx f = new bpx("local", -10, "Local Teams", 0, "at", 0);
    private LayoutInflater g;
    private buy h;
    private boolean i;
    private ExpandableListView j;

    public SettingsSportsSelectActivity() {
        super("Pick Favorites");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_sports_select_activity);
        this.g = (LayoutInflater) getSystemService("layout_inflater");
        Intent intent = getIntent();
        this.h = new buy(this);
        this.j = (ExpandableListView) findViewById(bbq.sports_list);
        this.j.setGroupIndicator(null);
        this.j.setAdapter(this.h);
        this.j.setItemsCanFocus(true);
        this.j.setOnGroupClickListener(new buw(this));
        this.j.setOnChildClickListener(new bux(this));
        if (intent != null && intent.hasExtra("showPleaseSetFavorites")) {
            E_();
        }
        if (aF()) {
            TextView textView = (TextView) findViewById(bbq.onboarding_header);
            textView.setText("Pick Favorites:");
            textView.setVisibility(0);
            if (j().at() && b()) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.j.getLayoutParams();
                layoutParams.gravity = 1;
                layoutParams.width = k().getResources().getDisplayMetrics().widthPixels / 2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aL() {
        startActivity(new Intent(this, (Class<?>) SettingsLocalTeamsActivity.class));
        j().k("/local_teams");
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
        if (F() != null) {
            c();
            j().k("/prefs/add_sports");
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        try {
            aM();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(buz buzVar) {
        bpr bprVar;
        bpr bprVar2;
        bprVar = buzVar.b;
        if (bprVar != null) {
            Intent intent = new Intent(this, (Class<?>) SettingsTeamSelectActivity.class);
            if (aF()) {
                intent.putExtra("ONBOARDING", true);
            }
            bprVar2 = buzVar.b;
            intent.putExtra("league", bprVar2.g());
            startActivity(intent);
        }
    }

    private synchronized void aM() {
        ArrayList arrayList = new ArrayList();
        if (!aF()) {
            arrayList.add(new buz(this, f, (buw) null));
        }
        IService F = F();
        if (F != null) {
            for (bpx bpxVar : F.b()) {
                if (bpxVar.d()) {
                    arrayList.add(new buz(this, bpxVar, (buw) null));
                } else {
                    Iterator<bpr> it = F.f(bpxVar.a()).iterator();
                    while (it.hasNext()) {
                        arrayList.add(new buz(this, it.next(), (buw) null));
                    }
                }
            }
            this.h.a(arrayList);
            this.h.notifyDataSetChanged();
            if (!this.i && arrayList.size() > 0) {
                this.j.requestFocus();
                this.i = this.j.hasFocus();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#006";
    }
}
