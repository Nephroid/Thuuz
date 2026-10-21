package com.thuuz.gtv.settings;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.bwa;
import defpackage.bwb;
import defpackage.bwc;
import defpackage.bwd;
import defpackage.bzy;
import java.util.logging.Level;
import twitter4j.auth.RequestToken;

/* loaded from: classes.dex */
public class SettingsTwitterOauthActivity extends AbsSettingsSigninActivity {
    private RequestToken f;
    private BroadcastReceiver g;
    private Button h;

    public SettingsTwitterOauthActivity() {
        super("Sign In to Twitter");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_twitter_oauth_layout);
        if (j().bO()) {
            setResult(-1);
            return;
        }
        setResult(0);
        try {
            this.g = new bwc(this, null);
            registerReceiver(this.g, new IntentFilter("TWIT_OAUTH_ACTION"), "com.thuuz.permission.local.broadcast", null);
            this.h = (Button) findViewById(bbq.twit_signin);
            expandWidthByDevice(this.h);
            this.h.setOnClickListener(new bwd(this, null));
            Button button = (Button) findViewById(bbq.cancel);
            expandWidthByDevice(button);
            button.setOnClickListener(new bwa(this));
            e().info("doCreate finished");
        } catch (Throwable th) {
            e().warning("Cannot twitter: " + th);
            if (d()) {
                b("Problem with Twitter", bbt.pls_try_again_later);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            e().info("destroying ");
            if (this.g != null) {
                unregisterReceiver(this.g);
                this.g = null;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        super.onDestroy();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        try {
            IService F = F();
            if (F != null) {
                this.f = F.s();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        W();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    public static void a(Uri uri, AbstractActivity abstractActivity) {
        if (abstractActivity != null && uri != null) {
            try {
                bzy.getStaticLogger(SettingsTwitterOauthActivity.class).info("handling twit oath in SettingsTwitter");
                String queryParameter = uri.getQueryParameter("oauth_verifier");
                if (queryParameter == null) {
                    if (uri.getQueryParameter("denied") != null) {
                        abstractActivity.c("Twitter Denied Sign-in", "Please check credentials and try again");
                    } else {
                        bzy.getStaticLogger(SettingsTwitterOauthActivity.class).warning("found no twitter success param");
                    }
                    b(uri, abstractActivity, (String) null);
                    abstractActivity.setResult(0);
                    abstractActivity.finish();
                    return;
                }
                IService F = abstractActivity.F();
                if (F != null) {
                    new bwb(abstractActivity, F.s(), queryParameter, uri).execute(new Void[0]);
                    return;
                }
                b(uri, abstractActivity, queryParameter);
                abstractActivity.setResult(0);
                abstractActivity.finish();
            } catch (Throwable th) {
                bzy.getStaticLogger(SettingsTwitterOauthActivity.class).log(Level.SEVERE, "Cannot handle twitter oath: " + uri, th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Uri uri, AbstractActivity abstractActivity, String str) {
        try {
            Intent intent = new Intent("TWIT_OAUTH_ACTION");
            if (str != null) {
                intent.putExtra("oauth_verifier", str);
            }
            try {
                String queryParameter = uri.getQueryParameter("share_type");
                if (queryParameter != null) {
                    intent.putExtra("share_type", Integer.parseInt(queryParameter));
                }
            } catch (Throwable th) {
                bzy.getStaticLogger(SettingsTwitterOauthActivity.class).log(Level.SEVERE, "problem adding shareType: ", th);
            }
            abstractActivity.sendBroadcast(intent, "com.thuuz.permission.local.broadcast");
        } catch (Throwable th2) {
            bzy.getStaticLogger(SettingsTwitterOauthActivity.class).log(Level.SEVERE, "problem: ", th2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#003";
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        e().info("config changed: " + configuration);
    }
}
