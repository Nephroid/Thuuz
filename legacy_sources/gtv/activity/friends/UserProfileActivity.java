package com.thuuz.gtv.activity.friends;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.util.ServerChoice;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbs;
import defpackage.biw;
import defpackage.bqd;
import defpackage.can;
import java.util.logging.Level;
import org.apache.http.util.EncodingUtils;

/* loaded from: classes.dex */
public class UserProfileActivity extends AbstractActivity {
    private WebView f;
    private int g;
    private boolean h;
    private boolean i;
    private Menu j;
    private biw k;

    public UserProfileActivity() {
        super("Thuuzer");
        this.h = false;
        this.i = false;
        this.j = null;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.webview_activity);
        this.g = getIntent().getIntExtra("userId", -1);
        if (this.g == -1) {
            if (bundle != null) {
                this.g = bundle.getInt("userId", -1);
            }
            if (this.g == -1) {
                e().info("no id for profile page. bailing to dash");
                finish();
                return;
            }
        }
        this.h = this.g == Integer.valueOf(new bqd(j().bj()).a()).intValue();
        this.f = (WebView) findViewById(bbq.webview);
        this.k = new biw(this, this);
        this.f.setWebViewClient(this.k);
        this.f.setWebChromeClient(new can());
        this.f.setScrollBarStyle(33554432);
        this.f.setFocusable(true);
        WebSettings settings = this.f.getSettings();
        settings.setJavaScriptEnabled(true);
        boolean z = z();
        settings.setDatabaseEnabled(z);
        if (!z) {
            settings.setCacheMode(2);
        }
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT > 19) {
            settings.setMixedContentMode(0);
        }
        if (getIntent() != null && getIntent().getBooleanExtra("isNotification", Boolean.FALSE.booleanValue())) {
            j().a("go_to_user_profile", String.valueOf(this.g), "", 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("userId", this.g);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            c();
            j().k("/user_profile");
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        h(false);
    }

    public void h(boolean z) {
        try {
            if (d()) {
                W();
                if (this.k.g() && !z) {
                    e().info("skipping refresh because in progress");
                } else {
                    this.k.h();
                    StringBuilder append = new StringBuilder(ServerChoice.n).append("app/").append("2.3/").append("user/").append(this.g).append("/?page_params=true");
                    append.append(e(append.toString()));
                    String sb = append.toString();
                    e().info("fetching: " + sb);
                    this.f.postUrl(sb, EncodingUtils.getBytes("user_cookie=" + j().bj(), "BASE64"));
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            super.onCreateOptionsMenu(menu);
            if (!j().a((Activity) this) && !this.h) {
                getSupportMenuInflater().inflate(bbs.follow, menu);
            }
            this.j = menu;
            return true;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
            return true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x000d, code lost:
    
        r0 = super.onOptionsItemSelected(r5);
     */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem r5) {
        /*
            r4 = this;
            r0 = 1
            int r1 = defpackage.bbq.refresh_option     // Catch: java.lang.Throwable -> L26
            int r2 = r5.getItemId()     // Catch: java.lang.Throwable -> L26
            if (r1 != r2) goto Le
            r1 = 1
            r4.h(r1)     // Catch: java.lang.Throwable -> L26
        Ld:
            return r0
        Le:
            int r1 = r5.getItemId()     // Catch: java.lang.Throwable -> L26
            int r2 = defpackage.bbq.follow_option     // Catch: java.lang.Throwable -> L26
            if (r1 != r2) goto L32
            com.thuuz.gtv.App r1 = r4.j()     // Catch: java.lang.Throwable -> L26
            boolean r1 = r1.i()     // Catch: java.lang.Throwable -> L26
            if (r1 != 0) goto L37
            com.thuuz.gtv.settings.SettingsAccountActivity$SOURCE r1 = com.thuuz.gtv.settings.SettingsAccountActivity.SOURCE.SOURCE_FRIENDS     // Catch: java.lang.Throwable -> L26
            r4.a(r1)     // Catch: java.lang.Throwable -> L26
            goto Ld
        L26:
            r0 = move-exception
            java.util.logging.Logger r1 = r4.e()
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "problem: "
            r1.log(r2, r3, r0)
        L32:
            boolean r0 = super.onOptionsItemSelected(r5)
            goto Ld
        L37:
            biv r1 = new biv     // Catch: java.lang.Throwable -> L26
            r2 = 0
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L26
            r2 = 0
            java.lang.Void[] r2 = new java.lang.Void[r2]     // Catch: java.lang.Throwable -> L26
            r1.execute(r2)     // Catch: java.lang.Throwable -> L26
            goto Ld
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.activity.friends.UserProfileActivity.onOptionsItemSelected(com.actionbarsherlock.view.MenuItem):boolean");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(boolean z) {
        MenuItem findItem;
        if (this.j != null && (findItem = this.j.findItem(bbq.follow_option)) != null) {
            if (!j().i()) {
                this.j.removeItem(findItem.getItemId());
            } else {
                findItem.setTitle(z ? "Follow" : "Unfollow");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        int intExtra = intent.getIntExtra("userId", -1);
        if (intExtra > -1) {
            this.g = intExtra;
            e().info("onNewIntent called");
            if (intent.getBooleanExtra("isNotification", Boolean.FALSE.booleanValue())) {
                j().a("go_to_user_profile", String.valueOf(this.g), "", 0L);
                return;
            }
            return;
        }
        e().severe("incoming profile intent has no uid: " + intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public Class<? extends AbstractActivity> P() {
        return FriendsActivity.class;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        try {
            if (this.f == null) {
                super.onBackPressed();
            }
            if (this.f.canGoBack()) {
                this.f.goBack();
            } else {
                super.onBackPressed();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem with back press: ", th);
        }
    }
}
