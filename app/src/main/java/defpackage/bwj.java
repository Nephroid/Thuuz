package defpackage;

import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.WebViewFragment;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bwj extends ThuuzWebViewClient {
    final /* synthetic */ WebViewFragment a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bwj(WebViewFragment webViewFragment, AbstractActivity abstractActivity) {
        super(abstractActivity);
        this.a = webViewFragment;
    }

    @Override // com.thuuz.gtv.util.ThuuzWebViewClient, android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        Logger logger;
        super.onPageFinished(webView, str);
        logger = this.a.getLogger();
        logger.info("web frag: onPageFinshed()");
        if (!d()) {
            this.a.showContent();
        }
    }

    @Override // com.thuuz.gtv.util.ThuuzWebViewClient, android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        n();
    }

    private void n() {
        Logger logger;
        Logger logger2;
        LinearLayout linearLayout;
        WebView webView;
        LinearLayout linearLayout2;
        LinearLayout linearLayout3;
        String str;
        try {
            logger2 = this.a.getLogger();
            logger2.info("showing error");
            linearLayout = this.a.loadingLayout;
            linearLayout.setVisibility(0);
            webView = this.a.webView;
            webView.setVisibility(8);
            linearLayout2 = this.a.loadingLayout;
            ((ProgressBar) linearLayout2.findViewById(bbq.spinner)).setVisibility(8);
            linearLayout3 = this.a.loadingLayout;
            TextView textView = (TextView) linearLayout3.findViewById(bbq.loading_msg);
            String str2 = "Download failed. Please refresh later.";
            str = this.a.failureText;
            if (str != null) {
                str2 = this.a.failureText;
            }
            textView.setText(str2);
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
