package com.thuuz.gtv.activity.nobar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bld;
import defpackage.blf;
import defpackage.caz;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class OnboardPage3aLocationActivity extends AbstractOnboard {
    public static /* synthetic */ void a(OnboardPage3aLocationActivity onboardPage3aLocationActivity) {
        onboardPage3aLocationActivity.aG();
    }

    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        try {
            setContentView(bbr.onboard_page_3a);
        } catch (Throwable th) {
            if (caz.a(th)) {
                a(th);
            } else {
                c("Problem Showing Splash Screen", "Please quit all apps and try again later.");
                e().log(Level.SEVERE, "problem displaying splash screen; caught and warned: ", th);
            }
        }
        findViewById(bbq.use_location_button).setOnClickListener(new bld(this));
        findViewById(bbq.continue_button).setOnClickListener(new blf(this));
        aF();
    }

    private void aF() {
        View findViewById = findViewById(bbq.container);
        if (j().at()) {
            a(findViewById);
        }
    }

    public void aG() {
        try {
            startActivity(new Intent(k(), (Class<?>) OnboardPage4TvActivity.class));
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
