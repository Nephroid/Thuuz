package defpackage;

import android.webkit.WebView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.game_page.GamePageWebViewFragment;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bkl extends cah {
    boolean a;
    final /* synthetic */ GamePageWebViewFragment b;

    public /* synthetic */ bkl(GamePageWebViewFragment gamePageWebViewFragment, boolean z, String str, AbstractActivity abstractActivity, WebView webView, ThuuzWebViewClient thuuzWebViewClient, bki bkiVar) {
        this(gamePageWebViewFragment, z, str, abstractActivity, webView, thuuzWebViewClient);
    }

    public /* synthetic */ bkl(GamePageWebViewFragment gamePageWebViewFragment, boolean z, String str, AbstractActivity abstractActivity, WebView webView, ThuuzWebViewClient thuuzWebViewClient, boolean z2, bki bkiVar) {
        this(gamePageWebViewFragment, z, str, abstractActivity, webView, thuuzWebViewClient, z2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private bkl(GamePageWebViewFragment gamePageWebViewFragment, boolean z, String str, AbstractActivity abstractActivity, WebView webView, ThuuzWebViewClient thuuzWebViewClient) {
        super(str, abstractActivity, webView, thuuzWebViewClient);
        this.b = gamePageWebViewFragment;
        this.a = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private bkl(GamePageWebViewFragment gamePageWebViewFragment, boolean z, String str, AbstractActivity abstractActivity, WebView webView, ThuuzWebViewClient thuuzWebViewClient, boolean z2) {
        super(str, abstractActivity, webView, thuuzWebViewClient, z2);
        this.b = gamePageWebViewFragment;
        this.a = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.cah, android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(String str) {
        Logger logger;
        WebView webView;
        WebView webView2;
        ThuuzWebViewClient thuuzWebViewClient;
        WebView webView3;
        ThuuzWebViewClient thuuzWebViewClient2;
        ThuuzWebViewClient thuuzWebViewClient3;
        Logger logger2;
        try {
            try {
                if (!this.a && str != null) {
                    thuuzWebViewClient3 = this.b.thuuzWebViewClient;
                    if (str.equals(thuuzWebViewClient3.i())) {
                        logger2 = this.b.getLogger();
                        logger2.info("skipping view refresh since download is same");
                        AbstractActivity a = a();
                        if (a != null) {
                            a.e(false);
                        }
                        this.b.fetcher = null;
                        return;
                    }
                }
                if (str == null) {
                    thuuzWebViewClient2 = this.b.thuuzWebViewClient;
                    thuuzWebViewClient2.onReceivedError(g(), d(), f(), b());
                } else {
                    GamePageWebViewFragment gamePageWebViewFragment = this.b;
                    webView = this.b.webView;
                    gamePageWebViewFragment.scrollY = webView.getScrollY();
                    webView2 = this.b.webView;
                    webView2.loadDataWithBaseURL(b(), str, e(), c(), b());
                    thuuzWebViewClient = this.b.thuuzWebViewClient;
                    thuuzWebViewClient.a(str, e(), c());
                    this.b.setRefreshState(str, e(), c(), b());
                    webView3 = this.b.webView;
                    webView3.requestLayout();
                }
                AbstractActivity a2 = a();
                if (a2 != null) {
                    a2.e(false);
                }
                this.b.fetcher = null;
            } catch (Throwable th) {
                logger = this.b.getLogger();
                logger.log(Level.SEVERE, "problem in postExecute of peekFetcher: ", th);
                AbstractActivity a3 = a();
                if (a3 != null) {
                    a3.e(false);
                }
                this.b.fetcher = null;
            }
        } catch (Throwable th2) {
            AbstractActivity a4 = a();
            if (a4 != null) {
                a4.e(false);
            }
            this.b.fetcher = null;
            throw th2;
        }
    }
}
