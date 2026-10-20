package defpackage;

import android.os.AsyncTask;
import android.webkit.WebView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class cab extends AsyncTask<Void, Void, ThuuzWebViewClient.CONN_RETRY_STATE> {
    private final boolean a;
    private final WeakReference<AbstractActivity> b;
    private final WeakReference<ThuuzWebViewClient> c;

    public cab(boolean z, AbstractActivity abstractActivity, ThuuzWebViewClient thuuzWebViewClient) {
        this.a = z;
        this.b = new WeakReference<>(abstractActivity);
        this.c = new WeakReference<>(thuuzWebViewClient);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity != null) {
            abstractActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.thuuz.gtv.activity.AbstractActivity] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.thuuz.gtv.activity.AbstractActivity] */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.thuuz.gtv.util.ThuuzWebViewClient$CONN_RETRY_STATE] */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ThuuzWebViewClient.CONN_RETRY_STATE doInBackground(Void... voidArr) {
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity == 0) {
            return ThuuzWebViewClient.CONN_RETRY_STATE.DO_NOTHING;
        }
        try {
            abstractActivity.e().info("starting js connection status async");
            if (bya.a(abstractActivity.j(), abstractActivity.e(), "js refresh").a) {
                abstractActivity = ThuuzWebViewClient.CONN_RETRY_STATE.RETRY;
            } else {
                abstractActivity = ThuuzWebViewClient.CONN_RETRY_STATE.ERROR;
            }
            return abstractActivity;
        } catch (Throwable th) {
            abstractActivity.e().log(Level.SEVERE, "problem: ", th);
            return ThuuzWebViewClient.CONN_RETRY_STATE.ERROR;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(ThuuzWebViewClient.CONN_RETRY_STATE conn_retry_state) {
        AbstractActivity abstractActivity;
        ThuuzWebViewClient thuuzWebViewClient;
        WeakReference<WebView> m;
        WebView webView;
        if (conn_retry_state != null && (abstractActivity = this.b.get()) != null && (thuuzWebViewClient = this.c.get()) != null) {
            try {
                if (this.a) {
                    thuuzWebViewClient.b(false);
                }
                switch (cac.a[conn_retry_state.ordinal()]) {
                    case 1:
                        if (thuuzWebViewClient.m() == null) {
                            abstractActivity.e().info("no webview after failure");
                            break;
                        } else {
                            WebView webView2 = thuuzWebViewClient.m().get();
                            if (webView2 != null) {
                                abstractActivity.e().info("reloading webview failure WITH subsequent reconnection");
                                if (this.a) {
                                    thuuzWebViewClient.b(true);
                                }
                                String j = thuuzWebViewClient.j();
                                if (j != null) {
                                    byte[] f = thuuzWebViewClient.f();
                                    if (f != null) {
                                        webView2.postUrl(j, f);
                                        break;
                                    } else {
                                        webView2.loadUrl(j);
                                        break;
                                    }
                                } else {
                                    webView2.reload();
                                    break;
                                }
                            } else {
                                abstractActivity.e().info("no webview after failure");
                                break;
                            }
                        }
                    case 2:
                        break;
                    case 3:
                        if (!abstractActivity.isDestroyed()) {
                            if (abstractActivity.d()) {
                                abstractActivity.c(bbt.unable_to_update, bbt.pls_try_refresh_later);
                            }
                            if (!this.a && (m = thuuzWebViewClient.m()) != null && (webView = m.get()) != null) {
                                abstractActivity.e().info("loading 404 text");
                                webView.loadDataWithBaseURL(null, "<html><head><style>body { background-color: #000000; color: #f1f1f1; } </style></head><body><h2>Download failed; please try again later</h2></body></html>", "text/html", "utf-8", null);
                                break;
                            }
                        } else {
                            return;
                        }
                        break;
                    default:
                        abstractActivity.e().severe("unexpected state: " + conn_retry_state);
                        break;
                }
            } catch (Throwable th) {
                abstractActivity.e().log(Level.SEVERE, "problem postExecute of reconnection attempt w/ result: " + conn_retry_state, th);
            }
            abstractActivity.e(false);
            thuuzWebViewClient.e();
        }
    }
}
