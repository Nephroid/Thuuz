package com.thuuz.gtv.activity.fantasy;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.fragments.GameListFragment;
import com.thuuz.gtv.util.ServerChoice;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bfx;
import defpackage.bra;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PlayerActivity extends AbstractActivity implements bfx, bra {
    private int f;
    private WebView g;
    private int h;
    private final Map<String, String> i;
    private boolean j;

    public PlayerActivity() {
        super("Player Summary");
        this.i = new HashMap();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.player);
        this.f = getIntent().getIntExtra("playerId", -1);
        e().info("got create for player: " + this.f);
        if (this.f == -1 && bundle != null) {
            this.f = bundle.getInt("playerId");
            e().warning("using save ID for player: " + this.f);
        }
        if (this.f <= 0) {
            e().warning("bailing: cannot get id for player profile");
            setResult(0);
            finish();
            return;
        }
        this.g = (WebView) findViewById(bbq.webview);
        this.g.setWebViewClient(new ThuuzWebViewClient(this, this));
        this.g.setBackgroundColor(0);
        this.g.getSettings().setJavaScriptEnabled(true);
        this.g.getSettings().setCacheMode(2);
        this.g.getSettings().setDatabaseEnabled(z());
        if (Build.VERSION.SDK_INT > 19) {
            this.g.getSettings().setMixedContentMode(0);
        }
        String stringExtra = getIntent().getStringExtra("params");
        if (stringExtra != null) {
            try {
                JSONObject jSONObject = new JSONObject(stringExtra);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    this.i.put(next, jSONObject.getString(next));
                }
            } catch (JSONException e) {
                e().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("playerId", this.f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
        j().k("/player/" + this.f);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        View l;
        try {
            this.h = this.g.getScrollY();
            Uri.Builder buildUpon = Uri.parse(ServerChoice.n + "app/2.3/player/" + this.f + "/").buildUpon();
            for (String str : this.i.keySet()) {
                buildUpon.appendQueryParameter(str, this.i.get(str));
            }
            String builder = buildUpon.toString();
            String str2 = builder + e(builder);
            e().info("player loading: " + str2);
            String str3 = "user_cookie=" + j().bj();
            if (this.g != null) {
                this.g.postUrl(str2, EncodingUtils.getBytes(str3, "BASE64"));
            }
            W();
            if (!this.j && j().e() && (l = l()) != null) {
                l.requestFocus();
                this.j = l.hasFocus();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        c();
    }

    @Override // defpackage.bra
    public void reScroll() {
        if (this.h > 0) {
            this.g.scrollTo(0, this.h);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        int intExtra = intent.getIntExtra("playerId", -1);
        e().warning("got new intent for player: " + intExtra);
        if (intExtra > -1) {
            this.f = intExtra;
            c();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    /* renamed from: aF, reason: merged with bridge method [inline-methods] */
    public GameListFragment au() {
        return new GameListFragment(true);
    }
}
