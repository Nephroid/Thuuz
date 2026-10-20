package com.thuuz.gtv.activity.friends;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.internal.NativeProtocol;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.SettingsActivity;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.cah;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class WebviewActivity extends AbstractActivity {
    private WebView f;
    private String g;
    private ThuuzWebViewClient h;
    private boolean i;

    public WebviewActivity() {
        super("Web");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.webview_activity);
        this.g = getIntent().getStringExtra(NativeProtocol.IMAGE_URL_KEY);
        if (this.g == null) {
            if (bundle != null) {
                this.g = bundle.getString(NativeProtocol.IMAGE_URL_KEY);
            }
            if (this.g == null) {
                e().info("no url for webview page. bailing.");
                finish();
                return;
            }
        }
        if (getIntent().hasExtra("title")) {
            a(getIntent().getStringExtra("title"));
        }
        this.f = (WebView) findViewById(bbq.webview);
        this.h = new ThuuzWebViewClient(this);
        this.f.setWebViewClient(this.h);
        WebSettings settings = this.f.getSettings();
        settings.setJavaScriptEnabled(true);
        boolean z = z();
        settings.setDatabaseEnabled(z);
        if (!z) {
            settings.setCacheMode(2);
        }
        this.f.setScrollBarStyle(33554432);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT > 19) {
            settings.setMixedContentMode(0);
        }
        if (this.g.contains("twitter.com")) {
            settings.setUserAgentString("Mozilla/5.0 (Linux; U; Android 2.0; en-us; Droid Build/ESD20) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17");
        }
        this.f.setFocusable(true);
        this.f.setFocusableInTouchMode(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(NativeProtocol.IMAGE_URL_KEY, this.g);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            c();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        try {
            e().info("fetching: " + this.g);
            if (getIntent().getBooleanExtra("use_httpclient", false)) {
                new cah(this.g, this, this.f, this.h).execute(new Void[0]);
            } else {
                this.f.loadUrl(this.g);
            }
            W();
            if (j().e() && !this.i && !this.f.hasFocus()) {
                this.f.requestFocus();
                this.i = this.f.hasFocus();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        c();
    }

    public void g(String str) {
        this.g = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        String stringExtra = intent.getStringExtra(NativeProtocol.IMAGE_URL_KEY);
        if (stringExtra == null) {
            e().severe("incoming webview new intent has no url: " + intent);
            return;
        }
        if (intent.hasExtra("title")) {
            a(intent.getStringExtra("title"));
        }
        this.g = stringExtra;
        c();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        try {
            if (this.f != null && !this.h.d() && this.f.canGoBack()) {
                this.f.goBack();
            } else {
                super.onBackPressed();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem with back press: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public Class<? extends AbstractActivity> P() {
        return this.g.contains("apps/about/") ? SettingsActivity.class : super.P();
    }
}
