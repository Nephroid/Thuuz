package com.thuuz.gtv.activity.nobar;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.TransportMediator;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.facebook.internal.NativeProtocol;
import defpackage.bbn;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bks;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class DialogActivity extends AbstractNoActionBarActivity {
    private BroadcastReceiver f;
    private WebView g;

    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView(bbr.sched_dialog);
            String stringExtra = getIntent().getStringExtra("title");
            String stringExtra2 = getIntent().getStringExtra("msg");
            boolean z = getIntent().getIntExtra("html", 0) != 0;
            String stringExtra3 = getIntent().getStringExtra(NativeProtocol.IMAGE_URL_KEY);
            if (stringExtra2 == null && stringExtra3 == null) {
                e().severe("cannot get msg from intent");
                c("Error", "");
                setResult(0);
                finish();
                return;
            }
            if (stringExtra3 != null) {
                this.f = new bks(this);
                registerReceiver(this.f, new IntentFilter("TWIT_OAUTH_ACTION"), "com.thuuz.permission.local.broadcast", null);
            }
            TextView textView = (TextView) findViewById(bbq.alertTitle);
            if (stringExtra != null) {
                textView.setText(stringExtra);
            } else {
                textView.setVisibility(8);
            }
            ScrollView scrollView = (ScrollView) findViewById(bbq.textScrollView);
            LinearLayout linearLayout = (LinearLayout) findViewById(bbq.htmlContainer);
            this.g = (WebView) findViewById(bbq.dialog_html);
            this.g.getSettings().setDatabaseEnabled(false);
            this.g.getSettings().setCacheMode(2);
            this.g.setBackgroundResource(bbn.guide_bg);
            if (Build.VERSION.SDK_INT > 19) {
                this.g.getSettings().setMixedContentMode(0);
            }
            if (z) {
                if (stringExtra2 != null) {
                    scrollView.setVisibility(8);
                    linearLayout.setVisibility(0);
                    this.g.loadDataWithBaseURL(null, stringExtra2, "text/html", "utf-8", null);
                    return;
                }
                return;
            }
            scrollView.setVisibility(0);
            linearLayout.setVisibility(8);
            ((TextView) findViewById(bbq.dialog_text)).setText(stringExtra2 + "\n");
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        e().warning("destroying DialogActivity. Ignore any 'lost' warning--there's an Android bug with Dialog theme in 3.1");
        try {
            if (this.f != null) {
                unregisterReceiver(this.f);
                this.f = null;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void finish() {
        e().info("finishing DialogActivity.");
        super.finish();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (j().e() && keyEvent != null && (a(keyEvent) || keyEvent.getKeyCode() == 23)) {
            if (19 == keyEvent.getKeyCode() || 20 == keyEvent.getKeyCode()) {
                if (!(a(this.g, 33) || a(this.g, TransportMediator.KEYCODE_MEDIA_RECORD))) {
                    finish();
                    return true;
                }
            } else {
                finish();
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }
}
