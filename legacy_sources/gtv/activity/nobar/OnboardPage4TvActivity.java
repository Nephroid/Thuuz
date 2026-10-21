package com.thuuz.gtv.activity.nobar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ScrollView;
import android.widget.TextView;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.blg;
import defpackage.bpu;
import defpackage.bwn;
import defpackage.bwo;
import defpackage.bwr;
import defpackage.caz;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class OnboardPage4TvActivity extends AbstractOnboard implements bwr {
    private TVProviderFragment f;
    private bwn g;
    private boolean h = true;
    private ScrollView i;

    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        try {
            setContentView(bbr.onboard_page_4);
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            this.f = (TVProviderFragment) supportFragmentManager.findFragmentByTag("tv");
            this.i = (ScrollView) findViewById(bbq.scroller);
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            if (this.f == null) {
                this.f = new TVProviderFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("Add no provider", true);
                this.f.setArguments(bundle2);
            }
            if (this.f.isAdded()) {
                beginTransaction.show(this.f);
            } else {
                beginTransaction.add(bbq.tv_provider_fragment, this.f, "tv");
            }
            beginTransaction.commit();
            this.g = new bwn(this, this.f);
        } catch (Throwable th) {
            if (caz.a(th)) {
                a(th);
            } else {
                c("Problem Showing Splash Screen", "Please quit all apps and try again later.");
                e().log(Level.SEVERE, "problem displaying splash screen; caught and warned: ", th);
            }
        }
        findViewById(bbq.next_container).setOnClickListener(new blg(this));
        ((TextView) findViewById(bbq.onboarding_header)).setVisibility(0);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.h) {
            this.h = false;
            new bwo(this, this.f, false).execute(new Void[0]);
        }
    }

    public void aF() {
        try {
            startActivity(new Intent(k(), (Class<?>) OnboardPage6TeamsActivity.class));
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // defpackage.bwr
    public boolean a(bpu bpuVar) {
        boolean z = true;
        if (bpuVar == null) {
            return false;
        }
        boolean z2 = !j().e() && bpuVar.o();
        if (z2) {
            z = z2;
        } else if (j().e() || !bpuVar.f()) {
            z = false;
        }
        return z;
    }

    @Override // defpackage.bwr
    public void aG() {
        this.g.aG();
    }

    @Override // defpackage.bwr
    public void g(String str) {
        this.g.g(str);
    }

    @Override // defpackage.bwr
    public void aH() {
        aF();
    }

    @Override // defpackage.bwr
    public void aI() {
        aF();
    }

    @Override // defpackage.bwr
    public void aJ() {
        this.g.aJ();
    }

    @Override // defpackage.bwr
    public void aK() {
        aF();
        finish();
    }

    public void aL() {
        aF();
    }

    @Override // defpackage.bwr
    public ScrollView aM() {
        return this.i;
    }
}
