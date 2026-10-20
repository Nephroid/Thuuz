package com.thuuz.gtv.activity.nobar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.View;
import defpackage.bbp;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bla;
import defpackage.caz;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class OnboardPage1Activity extends AbstractOnboard {
    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        try {
            setContentView(bbr.onboard_page_1);
        } catch (Throwable th) {
            if (caz.a(th)) {
                a(th);
            } else {
                c("Problem Showing Splash Screen", "Please quit all apps and try again later.");
                e().log(Level.SEVERE, "problem displaying splash screen; caught and warned: ", th);
            }
        }
        findViewById(bbq.container).setOnClickListener(new bla(this));
        aF();
    }

    private void aF() {
        View findViewById = findViewById(bbq.image_container);
        if (b() && !j().at()) {
            a(findViewById);
            return;
        }
        Drawable drawable = getResources().getDrawable(bbp.splash);
        drawable.setAlpha(MotionEventCompat.ACTION_MASK);
        findViewById.setBackgroundDrawable(drawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aG() {
        try {
            Intent intent = new Intent(k(), (Class<?>) OnboardPage2Activity.class);
            intent.putExtra("ONBOARDING", true);
            startActivity(intent);
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
