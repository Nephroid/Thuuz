package com.thuuz.gtv.activity.nobar;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.webkit.WebView;
import android.widget.Button;
import com.thuuz.gtv.IService;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bky;
import defpackage.bkz;

/* loaded from: classes.dex */
public class MessageActivity extends AbstractNoActionBarActivity {
    private int f = -1;
    private String g;

    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.message_dialog);
        this.f = getIntent().getIntExtra("MSG_ID", -1);
        this.g = getIntent().getStringExtra("MSG_STR");
        if (this.f == -1 || this.g == null) {
            if (!j().bQ()) {
                e().severe("msg activity cannot get msg id or str from intent");
            }
            setResult(0);
            finish();
        }
        ((Button) findViewById(bbq.message_ok)).setOnClickListener(new bky(this));
        aF();
    }

    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
    }

    private void aF() {
        WebView webView = (WebView) findViewById(bbq.message_html);
        webView.setWebViewClient(new bkz(this, null));
        webView.getSettings().setDatabaseEnabled(false);
        webView.getSettings().setCacheMode(2);
        webView.setScrollBarStyle(ViewCompat.MEASURED_STATE_TOO_SMALL);
        webView.loadDataWithBaseURL(null, this.g, "text/html", "utf-8", null);
        if (Build.VERSION.SDK_INT > 19) {
            webView.getSettings().setMixedContentMode(0);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        IService F = F();
        if (F != null) {
            F.i(this.f);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        j().k("/message/" + this.f);
    }
}
