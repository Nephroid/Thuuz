package defpackage;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bfa extends WebViewClient {
    final /* synthetic */ bey a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bfa(bey beyVar) {
        this.a = beyVar;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        WeakReference weakReference;
        weakReference = this.a.a;
        AbstractActivity abstractActivity = (AbstractActivity) weakReference.get();
        if (abstractActivity == null || abstractActivity.isDestroyed()) {
            return false;
        }
        try {
            abstractActivity.e().info("url load: " + str);
            abstractActivity.e(str, "");
            return true;
        } catch (Throwable th) {
            abstractActivity.e().log(Level.SEVERE, "problem: ", th);
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }
}
