package com.thuuz.gtv.settings;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.facebook.Session;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.AbstractFbSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.bqg;
import defpackage.bqq;
import defpackage.brg;
import defpackage.brh;
import defpackage.bri;
import defpackage.brj;
import defpackage.brk;
import defpackage.brl;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsAccountActivity extends AbstractFbSettings {
    private int f;

    /* loaded from: classes.dex */
    public enum SOURCE {
        SOURCE_NORMAL_ACCOUNT_INFO(0),
        SOURCE_FRIENDS(1),
        SOURCE_SHARE(2),
        SOURCE_GAME_PAGE_THUUZES(3),
        SOURCE_FANTASY(4),
        SOURCE_WELCOME(5),
        SOURCE_EXPERTS(6);

        final int mValue;

        SOURCE(int i) {
            this.mValue = i;
        }

        public int a() {
            return this.mValue;
        }

        public static SOURCE a(int i) {
            for (SOURCE source : values()) {
                if (source.a() == i) {
                    return source;
                }
            }
            return SOURCE_NORMAL_ACCOUNT_INFO;
        }
    }

    public SettingsAccountActivity() {
        super("Register or Sign In");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_account_layout);
        TextView textView = (TextView) findViewById(bbq.subtitle1);
        this.f = getIntent().getIntExtra("context", -1);
        if (this.f == -1 && bundle != null) {
            this.f = bundle.getInt("context", -1);
        }
        if (this.f == -1) {
            this.f = SOURCE.SOURCE_NORMAL_ACCOUNT_INFO.a();
        }
        switch (brk.a[SOURCE.a(this.f).ordinal()]) {
            case 1:
                textView.setText("Create an account to connect with your friends");
                break;
            case 2:
                textView.setText("Create an account to share with your friends and other Thuuzers.");
                aK();
                break;
            case 3:
                textView.setText("Create an account to check-in to this game. Let your friends see which games you're watching.");
                break;
            case 4:
                textView.setText("Create an account to enable Fantasy");
                break;
            case 5:
                textView.setText("Create an account to follow experts");
                break;
        }
        ((TextView) findViewById(bbq.register_button)).setOnClickListener(new brg(this));
        ((TextView) findViewById(bbq.signin_button)).setOnClickListener(new brh(this));
        Session activeSession = Session.getActiveSession();
        if (activeSession != null) {
            activeSession.close();
        }
        aG().e(false);
    }

    @Override // com.thuuz.gtv.activity.AbstractFbSettings, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("context", this.f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractFbSettings, com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            if (j().i()) {
                e().info("finishing because account already logged in.");
                setResult(1);
                finish();
            } else {
                j().k("/prefs/account/reg_choice");
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractFbSettings, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 351:
            case 511:
                if (i2 == 1) {
                    e().info("reg success; finishing");
                    setResult(1);
                    finish();
                    return;
                }
                return;
            case 571:
                if (i2 == 1) {
                    e().info("reg success; finishing");
                    setResult(1);
                    finish();
                    return;
                }
                return;
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }

    private void aK() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(k());
            builder.setTitle("Please sign in");
            builder.setMessage("Sign in to share games with your friends and followers.");
            builder.setCancelable(true);
            AlertDialog create = builder.create();
            create.setButton(-1, "Ok", new bri(this, create));
            a(create);
            if (!isDestroyed()) {
                create.show();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#003";
    }

    public static bqq a(AbstractActivity abstractActivity, bqg bqgVar, boolean z) {
        brj brjVar;
        Throwable th;
        try {
            Session activeSession = Session.getActiveSession();
            if (activeSession == null || !activeSession.isOpened()) {
                abstractActivity.e().log(Level.SEVERE, "ignoring attempt at Facebook Connected Login prior to FB authentication!");
                if (abstractActivity.d()) {
                    abstractActivity.b("Facebook Connection failed", bbt.pls_try_again_later);
                }
                return null;
            }
            String bd = abstractActivity.j().bd();
            if (bd.length() == 0) {
                abstractActivity.e().log(Level.SEVERE, "ignoring attempt at Facebook Connected Login with invalid social id.");
                if (abstractActivity.d()) {
                    abstractActivity.b("Facebook Connection failed", bbt.pls_try_again_later);
                }
                return null;
            }
            String a = abstractActivity.j().aY().a(bd);
            if (a.length() == 0) {
                abstractActivity.e().log(Level.SEVERE, "Invalid social params.");
                if (abstractActivity.d()) {
                    abstractActivity.b("Facebook Connection led", bbt.pls_try_again_later);
                }
                return null;
            }
            brjVar = new brj(a, abstractActivity, z, bqgVar);
            try {
                brjVar.execute(new Void[0]);
                return brjVar;
            } catch (Throwable th2) {
                th = th2;
                abstractActivity.e().log(Level.SEVERE, "problem: ", th);
                return brjVar;
            }
        } catch (Throwable th3) {
            brjVar = null;
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractFbSettings
    public bqg aF() {
        return new brl(this);
    }
}
