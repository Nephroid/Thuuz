package defpackage;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.gv;
import java.net.URI;
import java.net.URISyntaxException;

@zy
/* loaded from: classes.dex */
public class aee extends WebViewClient {
    private final String a;
    private boolean b = false;
    private final gv c;
    private final aas d;

    public aee(aas aasVar, gv gvVar, String str) {
        this.a = b(str);
        this.c = gvVar;
        this.d = aasVar;
    }

    private String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return str.endsWith("/") ? str.substring(0, str.length() - 1) : str;
        } catch (IndexOutOfBoundsException e) {
            adn.b(e.getMessage());
            return str;
        }
    }

    protected boolean a(String str) {
        boolean z = false;
        String b = b(str);
        if (!TextUtils.isEmpty(b)) {
            try {
                URI uri = new URI(b);
                if ("passback".equals(uri.getScheme())) {
                    adn.a("Passback received");
                    this.d.b();
                    z = true;
                } else if (!TextUtils.isEmpty(this.a)) {
                    URI uri2 = new URI(this.a);
                    String host = uri2.getHost();
                    String host2 = uri.getHost();
                    String path = uri2.getPath();
                    String path2 = uri.getPath();
                    if (jy.a(host, host2) && jy.a(path, path2)) {
                        adn.a("Passback received");
                        this.d.b();
                        z = true;
                    }
                }
            } catch (URISyntaxException e) {
                adn.b(e.getMessage());
            }
        }
        return z;
    }

    @Override // android.webkit.WebViewClient
    public void onLoadResource(WebView webView, String str) {
        adn.a("JavascriptAdWebViewClient::onLoadResource: " + str);
        if (a(str)) {
            return;
        }
        this.c.f().onLoadResource(this.c, str);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        adn.a("JavascriptAdWebViewClient::onPageFinished: " + str);
        if (this.b) {
            return;
        }
        this.d.a();
        this.b = true;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        adn.a("JavascriptAdWebViewClient::shouldOverrideUrlLoading: " + str);
        if (!a(str)) {
            return this.c.f().shouldOverrideUrlLoading(this.c, str);
        }
        adn.a("shouldOverrideUrlLoading: received passback url");
        return true;
    }
}
