package com.thuuz.gtv.activity.nobar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
// [removed legacy tracker import]
import java.util.HashMap;
import java.util.Timer;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class InitialActivity extends AbstractNoActionBarActivity {
    private Timer f = new Timer("startup");
    private boolean g = true;
    private boolean h;
    private boolean i;

    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        try {
            AppsFlyerLib.b(j().cd());
            AppsFlyerLib.a(getApplicationContext());
            HashMap hashMap = new HashMap();
            Context applicationContext = getApplicationContext();
            j().getClass();
            AppsFlyerLib.a(applicationContext, "app_opened", hashMap);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem with AppsFlyer event: ", th);
        }
        if (j().e()) {
            if ("com.thuuz.mob".equals(j().getPackageName())) {
                c("Wrong App!", "This app is for mobile. Please uninstall and use 'Thuuz for Google TV' app instead!");
                c("Wrong App!", "This app is for mobile. Please uninstall and use 'Thuuz for Google TV' app instead!");
            }
        } else if ("com.thuuz.gtv".equals(j().getPackageName())) {
            if (!j().f()) {
                c("Wrong App!", "This app is for Google TV. Please uninstall and use the regular Thuuz app (for mobile) instead!");
                c("Wrong App!", "This app is for Google TV. Please uninstall and use the regular Thuuz app (for mobile) instead!");
            }
        } else if ("com.thuuz.amzn".equals(j().getPackageName())) {
            c("Wrong App!", "This app is for an Amazon device. Please uninstall and use Thuuz app (for mobile) instead!");
            c("Wrong App!", "This app is for an Amazon device. Please uninstall and use Thuuz app (for mobile) instead!");
        }
        aI();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (!this.g) {
            e().warning("unexpected: reached initialActivity onStart TWICE!");
        }
        this.g = false;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        Timer timer;
        try {
            timer = this.f;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        if (timer == null) {
            finish();
            return;
        }
        timer.cancel();
        if (!aG() && !aH()) {
            if (F().a()) {
                Class aF = aF();
                e().info("launching directly to last activity: " + aF.getSimpleName());
                a(aF);
            } else {
                e().info("splashing after no data");
                aJ();
            }
        }
        finish();
    }

    private Class aF() {
        Class cls = null;
        if (getIntent() != null && getIntent().getExtras() != null) {
            cls = (Class) getIntent().getExtras().get("lastActivity");
        }
        if (cls == null) {
            return j().aE();
        }
        return cls;
    }

    private boolean aG() {
        int intExtra;
        Intent intent = getIntent();
        if (intent == null || (intExtra = intent.getIntExtra("game", -1)) == -1) {
            return false;
        }
        e().info("handling game launch: " + intExtra);
        a(intent);
        return true;
    }

    private boolean aH() {
        Bundle extras;
        String string;
        try {
            Intent intent = getIntent();
            if (intent != null && (extras = intent.getExtras()) != null && (string = extras.getString("sender")) != null && string.length() > 0) {
                e().info("launching profile activity via Initial");
                d(Integer.parseInt(string));
                return true;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        return false;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            if (this.f != null) {
                this.f.cancel();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        super.onDestroy();
    }

    private void aI() {
        try {
            e().info("splash from 'splash and finish'");
            aJ();
            finish();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem splashing: ", th);
        }
    }

    private synchronized void aJ() {
        try {
            if (!this.i) {
                this.i = true;
                startActivity(p());
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem splashing: ", th);
        }
    }

    @Override // android.app.Activity
    public synchronized void finish() {
        if (!this.h) {
            this.h = true;
            super.finish();
        }
    }
}
