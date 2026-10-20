package com.thuuz.gtv.activity.sports;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.util.ServerChoice;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bov;
import defpackage.bow;
import defpackage.bpr;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class StandingsActivity extends AbstractActivity {
    private WebView f;
    private String[] g;

    public StandingsActivity() {
        super("Standings");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.standings_activity);
        if (getIntent() != null && getIntent().hasExtra("league")) {
            this.g = new String[]{getIntent().getStringExtra("sport_slug"), getIntent().getStringExtra("league")};
        } else {
            this.g = j().aa();
        }
        if (this.g == null || this.g.length < 2 || App.b.equals(this.g[0]) || App.c.equals(this.g[0]) || App.d.equals(this.g[0]) || App.e.equals(this.g[0])) {
            e().severe("no filter info for league standings. bailing out of standings.");
            finish();
            return;
        }
        this.f = (WebView) findViewById(bbq.webview);
        this.f.setWebViewClient(new bow(this, this, null));
        this.f.getSettings().setJavaScriptEnabled(true);
        boolean z = z();
        this.f.getSettings().setDatabaseEnabled(z);
        if (Build.VERSION.SDK_INT > 19) {
            this.f.getSettings().setMixedContentMode(0);
        }
        if (!z) {
            this.f.getSettings().setCacheMode(2);
        }
        this.f.setOnKeyListener(new bov(this));
        s();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            c();
            if (this.g != null && this.g.length >= 2) {
                j().k("/standings/" + this.g[0] + "/" + this.g[1]);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        try {
            c();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        try {
            if (F() != null && d()) {
                aF();
                if (this.g != null && this.g.length >= 2) {
                    bpr a = F().a(this.g[0], this.g[1]);
                    if (a != null) {
                        if (!a.j()) {
                            e().severe("cannot show standings for: " + this.g[0] + "/" + this.g[1]);
                            finish();
                        }
                        StringBuilder append = new StringBuilder(ServerChoice.r).append(this.g[0]).append("/").append(this.g[1]).append("/standings/index.html");
                        append.append(e(append.toString()));
                        String sb = append.toString();
                        e().info("fetching: " + sb);
                        this.f.loadUrl(sb);
                    } else {
                        finish();
                    }
                }
                W();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void aF() {
        if (this.g != null) {
            a(a(this.g[0], this.g[1], F()) + " Standings");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public long q() {
        return 300000L;
    }
}
