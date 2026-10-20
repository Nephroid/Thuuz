package defpackage;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.thuuz.gtv.activity.nobar.MessageActivity;
import com.thuuz.gtv.settings.SettingsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bkz extends WebViewClient {
    final /* synthetic */ MessageActivity a;

    private bkz(MessageActivity messageActivity) {
        this.a = messageActivity;
    }

    public /* synthetic */ bkz(MessageActivity messageActivity, bky bkyVar) {
        this(messageActivity);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        try {
            if (str.contains("PrefsActivity") || str.contains(SettingsActivity.class.getSimpleName())) {
                this.a.startActivity(new Intent(this.a.k(), (Class<?>) SettingsActivity.class));
            } else {
                this.a.e(str, "");
            }
            return true;
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
            return true;
        }
    }
}
