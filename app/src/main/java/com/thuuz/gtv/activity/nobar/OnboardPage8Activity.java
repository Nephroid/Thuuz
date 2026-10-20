package com.thuuz.gtv.activity.nobar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.View;
import android.widget.TextView;
// [removed legacy tracker import]
import defpackage.bbp;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.blt;
import defpackage.caz;
import java.util.HashMap;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class OnboardPage8Activity extends AbstractOnboard {
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
        ((TextView) findViewById(bbq.welcome_message)).setText("You're ready to Thuuz!");
        ((TextView) findViewById(bbq.get_set_up_link)).setText("Tap anywhere to get started...");
        findViewById(bbq.container).setOnClickListener(new blt(this));
        aG();
        try {
            j().bm();
            aF();
        } catch (Throwable th2) {
            e().log(Level.SEVERE, "problem in OnboardPage8Activity: ", th2);
        }
    }

    public void aF() {
        try {
            String ay = j().ay();
            HashMap hashMap = new HashMap();
            if (ay == null) {
                Context applicationContext = j().getApplicationContext();
                j().getClass();
                AppsFlyerLib.a(applicationContext, "tv_off", hashMap);
            }
            if (!(j().n().length() > 0 || j().q().length() > 0 || j().u().length() > 0)) {
                Context applicationContext2 = j().getApplicationContext();
                j().getClass();
                AppsFlyerLib.a(applicationContext2, "prefs_off", hashMap);
            }
            Context applicationContext3 = j().getApplicationContext();
            j().getClass();
            AppsFlyerLib.a(applicationContext3, "notifications_on", hashMap);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem with AppsFlyer at end of onboarding: ", th);
        }
    }

    private void aG() {
        View findViewById = findViewById(bbq.image_container);
        if (b() && !j().at()) {
            a(findViewById);
            return;
        }
        Drawable drawable = getResources().getDrawable(bbp.splash);
        drawable.setAlpha(MotionEventCompat.ACTION_MASK);
        findViewById.setBackgroundDrawable(drawable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        aG();
    }
}
