package com.thuuz.gtv.activity.fantasy;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.util.ServerChoice;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bfc;
import defpackage.bfd;
import defpackage.bfe;

/* loaded from: classes.dex */
public class FantasyInviteActivity extends AbstractActivity {
    private Button f;
    private int g;
    private EditText h;
    private int i;

    public FantasyInviteActivity() {
        super("Invite Rivals");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        bfc bfcVar = null;
        setContentView(bbr.fantasy_invite);
        if (!j().i()) {
            e().warning("bailing: login required for fantasy invite");
            setResult(0);
            finish();
            return;
        }
        this.g = getIntent().getIntExtra("teamId", -1);
        if (this.g == -1 && bundle != null) {
            this.g = bundle.getInt("teamId");
        }
        if (this.g <= 0) {
            e().warning("bailing: cannot get team id for fantasy invite");
            setResult(0);
            finish();
            return;
        }
        this.i = getIntent().getIntExtra("compId", -1);
        WebView webView = (WebView) findViewById(bbq.webview);
        webView.setWebViewClient(new ThuuzWebViewClient(this));
        webView.setBackgroundColor(0);
        webView.getSettings().setCacheMode(2);
        webView.getSettings().setDatabaseEnabled(z());
        StringBuilder append = new StringBuilder(ServerChoice.q).append("app/").append("2.3/").append("fantasy/help/invite/");
        append.append(e(append.toString()));
        webView.loadUrl(append.toString());
        this.h = (EditText) findViewById(bbq.message);
        this.h.setOnEditorActionListener(new bfd(this));
        this.f = (Button) findViewById(bbq.share_now);
        expandWidthByDevice(this.f);
        this.f.setOnClickListener(new bfe(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
        j().k("/fantasy/invite_rivals");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("teamId", this.g);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        W();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        W();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public Class<? extends AbstractActivity> P() {
        return FantasyTeamActivity.class;
    }
}
