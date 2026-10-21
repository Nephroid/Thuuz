package defpackage;

import android.webkit.WebView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.fantasy.FantasyActivity;
import com.thuuz.gtv.util.ThuuzWebViewClient;

/* loaded from: classes.dex */
public class bei extends ThuuzWebViewClient {
    final /* synthetic */ FantasyActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bei(FantasyActivity fantasyActivity, AbstractActivity abstractActivity) {
        super(abstractActivity);
        this.a = fantasyActivity;
    }

    @Override // com.thuuz.gtv.util.ThuuzWebViewClient, android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.a.j = true;
        this.a.k = false;
    }

    @Override // com.thuuz.gtv.util.ThuuzWebViewClient, android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        this.a.j = false;
        this.a.k = false;
    }
}
