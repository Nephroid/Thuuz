package com.yozio.android.invites;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.yozio.android.Constants;
import com.yozio.android.R;
import com.yozio.android.YozioService;
import com.yozio.android.helpers.Configs;
import java.util.ArrayList;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public class InviteSplashPageActivity extends Activity {
    private WebView _webView;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.yozio_webview);
        this._webView = (WebView) findViewById(R.id.yozio_webview);
        this._webView.loadData(Configs.getInstance().getConfig(Configs.KEY_INVITE_SPLASH_PAGE_BODY), "text/html", HTTP.UTF_8);
        this._webView.setWebViewClient(new WebViewClient() { // from class: com.yozio.android.invites.InviteSplashPageActivity.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Uri parse = Uri.parse(str);
                if (parse.getScheme().equalsIgnoreCase(Constants.YOZIO_SCHEMA)) {
                    String uri = parse.toString();
                    if (uri.equalsIgnoreCase("yozio://contacts_list/show")) {
                        InviteSplashPageActivity.this.startActivityForResult(new Intent(InviteSplashPageActivity.this, (Class<?>) ContactsListActivity.class), 1);
                    } else if (uri.equalsIgnoreCase("yozio://back")) {
                        InviteSplashPageActivity.this.finish();
                    }
                }
                return true;
            }
        });
        YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_USER_SAW_INVITE_SPLASH_PAGE, null, null, false);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null) {
            ArrayList arrayList = (ArrayList) intent.getSerializableExtra("invitees");
            Intent intent2 = new Intent();
            intent2.putExtra("invitees", arrayList);
            setResult(-1, intent2);
            finish();
        }
    }
}
