package defpackage;

import android.webkit.WebView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.fantasy.FantasyLearnMoreActivity;
import com.thuuz.gtv.util.ThuuzWebViewClient;

/* loaded from: classes.dex */
public class bfg extends ThuuzWebViewClient {
    final /* synthetic */ FantasyLearnMoreActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bfg(FantasyLearnMoreActivity fantasyLearnMoreActivity, AbstractActivity abstractActivity) {
        super(abstractActivity);
        this.a = fantasyLearnMoreActivity;
    }

    @Override // com.thuuz.gtv.util.ThuuzWebViewClient, android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        WebView webView2;
        WebView webView3;
        WebView webView4;
        WebView webView5;
        WebView webView6;
        this.a.e().info("page finished");
        webView2 = this.a.f;
        webView2.setVisibility(0);
        webView3 = this.a.f;
        webView3.requestLayout();
        webView4 = this.a.f;
        if (!webView4.isShown()) {
            this.a.e().info("web view not shown; trying again");
            webView5 = this.a.f;
            webView5.setVisibility(0);
            webView6 = this.a.f;
            webView6.requestLayout();
        }
        super.onPageFinished(webView, str);
    }
}
