package com.thuuz.gtv.activity.nobar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.thuuz.gtv.view.GecCircleView;
import defpackage.bbn;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.blb;
import defpackage.caz;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class OnboardPage2Activity extends AbstractOnboard {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        aF();
    }

    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    @SuppressLint({"ResourceAsColor"})
    public void a(Bundle bundle) {
        try {
            setContentView(bbr.onboard_page_2);
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
        findViewById.setOnClickListener(new blb(this));
        ((GecCircleView) findViewById(bbq.avg)).setCircleColor(bbn.ok_color);
        aF();
    }

    private void aF() {
        View findViewById = findViewById(bbq.container);
        if (j().at()) {
            a(findViewById);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aG() {
        try {
            startActivity(new Intent(k(), (Class<?>) OnboardPage3Activity.class));
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
