package com.thuuz.gtv.activity.fantasy;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.util.ServerChoice;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bfg;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class FantasyLearnMoreActivity extends AbstractActivity {
    private WebView f;

    public FantasyLearnMoreActivity() {
        super("Learn More About Fantasy");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.learn_more);
        this.f = (WebView) findViewById(bbq.webview);
        this.f.setBackgroundColor(0);
        this.f.getSettings().setJavaScriptEnabled(true);
        this.f.getSettings().setAppCacheEnabled(false);
        this.f.getSettings().setCacheMode(2);
        this.f.setWebViewClient(new bfg(this, this));
        boolean z = z();
        this.f.getSettings().setDatabaseEnabled(z);
        if (!z) {
            this.f.getSettings().setCacheMode(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
        j().k("fantasy_learn_more");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        if (d()) {
            try {
                e(true);
                String str = "android";
                if (j().a((Activity) this)) {
                    str = "tablet";
                }
                StringBuilder append = new StringBuilder(ServerChoice.q).append("apps/").append(str).append("/fantasy/");
                append.append(e(append.toString()));
                String sb = append.toString();
                e().info("loading: " + sb);
                this.f.loadUrl(sb);
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem: ", th);
            } finally {
                e(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public Class<? extends AbstractActivity> P() {
        return FantasyActivity.class;
    }
}
