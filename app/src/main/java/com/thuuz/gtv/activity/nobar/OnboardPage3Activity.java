package com.thuuz.gtv.activity.nobar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.blc;
import defpackage.caz;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class OnboardPage3Activity extends AbstractOnboard {
    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        try {
            setContentView(bbr.onboard_page_3);
        } catch (Throwable th) {
            if (caz.a(th)) {
                a(th);
            } else {
                c("Problem Showing Splash Screen", "Please quit all apps and try again later.");
                e().log(Level.SEVERE, "problem displaying splash screen; caught and warned: ", th);
            }
        }
        View findViewById = findViewById(bbq.next_container);
        findViewById.setVisibility(0);
        findViewById.setOnClickListener(new blc(this));
        View findViewById2 = findViewById(bbq.notification_wrapper);
        if (b() || j().a((Activity) this)) {
            a(findViewById2, 400);
        }
        View findViewById3 = findViewById2.findViewById(bbq.notification);
        a(findViewById3, -2);
        a(findViewById3.findViewById(bbq.image_container), -2);
        View findViewById4 = findViewById(bbq.notification);
        ((TextView) findViewById4.findViewById(bbq.gec_great)).setText("100");
        ((TextView) findViewById4.findViewById(bbq.line1)).setText("Broncos at Patriots 24-14: 5:45 in the 3rd Pats rallying from 24 pts down.");
        aF();
    }

    private void aF() {
        View findViewById = findViewById(bbq.container);
        if (j().at()) {
            a(findViewById);
        }
    }

    private void a(View view, int i) {
        ViewGroup.LayoutParams layoutParams;
        if (view != null && (layoutParams = view.getLayoutParams()) != null) {
            if (i >= 0) {
                i = j().q(i);
            }
            layoutParams.width = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aG() {
        try {
            startActivity(new Intent(k(), (Class<?>) OnboardPage3aLocationActivity.class));
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aH() {
        try {
            startActivity(new Intent(k(), (Class<?>) OnboardPage4TvActivity.class));
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        aF();
    }
}
