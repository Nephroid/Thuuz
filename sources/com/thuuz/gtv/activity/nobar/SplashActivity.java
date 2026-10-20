package com.thuuz.gtv.activity.nobar;

import android.content.Intent;
import android.os.Bundle;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.data.FetchRequest;
import defpackage.bbl;
import defpackage.bbr;
import defpackage.blu;
import defpackage.blv;
import defpackage.blz;
import defpackage.bpo;
import defpackage.caz;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SplashActivity extends AbstractNoActionBarActivity implements bbl {
    private FetchRequest f;

    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        try {
            setContentView(bbr.splash);
        } catch (Throwable th) {
            if (caz.a(th)) {
                a(th);
            } else {
                c("Problem Showing Splash Screen", "Please quit all apps and try again later.");
                e().log(Level.SEVERE, "problem displaying splash screen; caught and warned: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        try {
            if (isDestroyed()) {
                aj();
            } else {
                IService F = F();
                if (F == null) {
                    e().warning("cannot get service in Splash");
                    aG();
                } else if (!F.a()) {
                    aF();
                } else {
                    aG();
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aF() {
        try {
            if (F() != null) {
                if (this.f != null) {
                    e().warning("ignoring refresh--already in progress");
                } else if (ak()) {
                    new blz(this, null).execute(new Void[0]);
                } else {
                    runOnUiThread(new blu(this));
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Error: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            FetchRequest fetchRequest = this.f;
            if (fetchRequest != null) {
                fetchRequest.a((bbl) null);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Splash: problem in onDestroy: ", th);
        }
        super.onDestroy();
    }

    @Override // defpackage.bbl
    public void onStartFetch() {
    }

    @Override // defpackage.bbl
    public void onConcludeFetch(bpo bpoVar) {
        if (!isDestroyed() && !isFinishing()) {
            runOnUiThread(new blv(this, bpoVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aG() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getIntExtra("game", -1) != -1) {
                e().info("splash launching game and finishing");
                a(intent);
                if (!isFinishing()) {
                    finish();
                    return;
                }
                return;
            }
            if (intent.getBooleanExtra("SPIN_ONLY", Boolean.FALSE.booleanValue())) {
                e().info("splash spun 1 sec and out");
                if (!isFinishing()) {
                    finish();
                    return;
                }
                return;
            }
        }
        e().info("splash launching stack and finishing");
        a(j().aE());
        if (!isFinishing()) {
            finish();
        }
    }
}
