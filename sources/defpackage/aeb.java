package defpackage;

import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.internal.gv;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.protocol.HTTP;

@zy
/* loaded from: classes.dex */
public class aeb extends adq {
    public aeb(gv gvVar, boolean z) {
        super(gvVar, z);
    }

    protected WebResourceResponse a(Context context, String str, String str2) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
        try {
            acx.a(context, str, true, httpURLConnection, true);
            httpURLConnection.addRequestProperty("Cache-Control", "max-stale=3600");
            httpURLConnection.connect();
            return new WebResourceResponse("application/javascript", HTTP.UTF_8, new ByteArrayInputStream(acx.a(new InputStreamReader(httpURLConnection.getInputStream())).getBytes(HTTP.UTF_8)));
        } finally {
            httpURLConnection.disconnect();
        }
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        WebResourceResponse a;
        try {
            if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
                a = super.shouldInterceptRequest(webView, str);
            } else if (webView instanceof gv) {
                gv gvVar = (gv) webView;
                gvVar.f().e();
                if (gvVar.e().e) {
                    adn.d("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_interstitial.js)");
                    a = a(gvVar.getContext(), this.a.i().b, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_interstitial.js");
                } else if (gvVar.j()) {
                    adn.d("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_expanded_banner.js)");
                    a = a(gvVar.getContext(), this.a.i().b, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_expanded_banner.js");
                } else {
                    adn.d("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_banner.js)");
                    a = a(gvVar.getContext(), this.a.i().b, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_banner.js");
                }
            } else {
                adn.e("Tried to intercept request from a WebView that wasn't an AdWebView.");
                a = super.shouldInterceptRequest(webView, str);
            }
            return a;
        } catch (IOException e) {
            adn.e("Could not fetch MRAID JS. " + e.getMessage());
            return super.shouldInterceptRequest(webView, str);
        }
    }
}
