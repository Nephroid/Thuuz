package defpackage;

import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class can extends WebChromeClient {
    private boolean a;

    @Override // android.webkit.WebChromeClient
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        try {
            String message = consoleMessage.message();
            if (!message.contains("ThuuzFeed is not defined") && !message.contains("Can't find variable: ThuuzFeed") && !message.contains("displayed insecure content from")) {
                bzy.getStaticLogger(can.class).info("js log: " + consoleMessage.message() + " -- from line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            }
        } catch (Throwable th) {
            bzy.getStaticLogger(can.class).log(Level.SEVERE, "problem: ", th);
        }
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        if (i < 100) {
            this.a = true;
        } else if (i == 100) {
            this.a = false;
        }
    }

    public void a(boolean z) {
        this.a = z;
    }

    public boolean a() {
        return this.a;
    }
}
