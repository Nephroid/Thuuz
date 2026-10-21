package defpackage;

import android.webkit.ValueCallback;
import android.webkit.WebView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ta implements Runnable {
    ValueCallback<String> a = new tb(this);
    final /* synthetic */ sv b;
    final /* synthetic */ WebView c;
    final /* synthetic */ sy d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ta(sy syVar, sv svVar, WebView webView) {
        this.d = syVar;
        this.b = svVar;
        this.c = webView;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c.getSettings().getJavaScriptEnabled()) {
            this.c.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.a);
        }
    }
}
