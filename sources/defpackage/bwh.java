package defpackage;

import android.view.View;
import com.thuuz.gtv.settings.WebViewFragment;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bwh implements View.OnFocusChangeListener {
    final /* synthetic */ WebViewFragment a;

    public bwh(WebViewFragment webViewFragment) {
        this.a = webViewFragment;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        Logger logger;
        logger = this.a.getLogger();
        logger.info("webview focus: " + z);
    }
}
