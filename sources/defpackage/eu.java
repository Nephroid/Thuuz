package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.protocol.HTTP;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class eu implements fq {
    private final String a;
    private final HttpClient b;
    private final Context c;
    private dz d;
    private URL e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public eu(HttpClient httpClient, Context context) {
        this(httpClient, dz.a(context), context);
    }

    eu(HttpClient httpClient, dz dzVar, Context context) {
        this.c = context.getApplicationContext();
        this.a = a("GoogleAnalytics", "3.0", Build.VERSION.RELEASE, ew.a(Locale.getDefault()), Build.MODEL, Build.ID);
        this.b = httpClient;
        this.d = dzVar;
    }

    private HttpEntityEnclosingRequest a(String str, String str2) {
        BasicHttpEntityEnclosingRequest basicHttpEntityEnclosingRequest;
        if (TextUtils.isEmpty(str)) {
            gn.d("Empty hit, discarding.");
            return null;
        }
        String str3 = str2 + "?" + str;
        if (str3.length() < 2036) {
            basicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest(HttpGet.METHOD_NAME, str3);
        } else {
            basicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest(HttpPost.METHOD_NAME, str2);
            try {
                basicHttpEntityEnclosingRequest.setEntity(new StringEntity(str));
            } catch (UnsupportedEncodingException e) {
                gn.d("Encoding error, discarding hit");
                return null;
            }
        }
        basicHttpEntityEnclosingRequest.addHeader(HTTP.USER_AGENT, this.a);
        return basicHttpEntityEnclosingRequest;
    }

    private void a(en enVar, URL url, boolean z) {
        URL url2;
        if (TextUtils.isEmpty(enVar.a()) || !b()) {
            return;
        }
        if (url == null) {
            try {
                url2 = this.e != null ? this.e : new URL("https://ssl.google-analytics.com/collect");
            } catch (MalformedURLException e) {
                return;
            }
        } else {
            url2 = url;
        }
        HttpHost httpHost = new HttpHost(url2.getHost(), url2.getPort(), url2.getProtocol());
        try {
            HttpEntityEnclosingRequest a = a(enVar.a(), url2.getPath());
            if (a != null) {
                a.addHeader(HTTP.TARGET_HOST, httpHost.toHostString());
                if (gn.a()) {
                    a(a);
                }
                if (z) {
                    ft.b(this.c);
                }
                HttpResponse execute = this.b.execute(httpHost, a);
                int statusCode = execute.getStatusLine().getStatusCode();
                HttpEntity entity = execute.getEntity();
                if (entity != null) {
                    entity.consumeContent();
                }
                if (statusCode != 200) {
                    gn.d("Bad response: " + execute.getStatusLine().getStatusCode());
                }
            }
        } catch (ClientProtocolException e2) {
            gn.d("ClientProtocolException sending monitoring hit.");
        } catch (IOException e3) {
            gn.d("Exception sending monitoring hit: " + e3.getClass().getSimpleName());
            gn.d(e3.getMessage());
        }
    }

    private void a(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        int available;
        StringBuffer stringBuffer = new StringBuffer();
        for (Header header : httpEntityEnclosingRequest.getAllHeaders()) {
            stringBuffer.append(header.toString()).append("\n");
        }
        stringBuffer.append(httpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
        if (httpEntityEnclosingRequest.getEntity() != null) {
            try {
                InputStream content = httpEntityEnclosingRequest.getEntity().getContent();
                if (content != null && (available = content.available()) > 0) {
                    byte[] bArr = new byte[available];
                    content.read(bArr);
                    stringBuffer.append("POST:\n");
                    stringBuffer.append(new String(bArr)).append("\n");
                }
            } catch (IOException e) {
                gn.c("Error Writing hit to log...");
            }
        }
        gn.c(stringBuffer.toString());
    }

    @Override // defpackage.fq
    public int a(List<gk> list, en enVar, boolean z) {
        int i;
        URL url;
        int i2 = 0;
        int min = Math.min(list.size(), 40);
        enVar.a("_hr", list.size());
        int i3 = 0;
        URL url2 = null;
        boolean z2 = true;
        int i4 = 0;
        while (i4 < min) {
            gk gkVar = list.get(i4);
            URL a = a(gkVar);
            if (a == null) {
                if (gn.a()) {
                    gn.d("No destination: discarding hit: " + gkVar.a());
                } else {
                    gn.d("No destination: discarding hit.");
                }
                i3++;
                URL url3 = url2;
                i = i2 + 1;
                url = url3;
            } else {
                HttpHost httpHost = new HttpHost(a.getHost(), a.getPort(), a.getProtocol());
                String path = a.getPath();
                String a2 = TextUtils.isEmpty(gkVar.a()) ? "" : gl.a(gkVar, System.currentTimeMillis());
                HttpEntityEnclosingRequest a3 = a(a2, path);
                if (a3 == null) {
                    i3++;
                    i = i2 + 1;
                    url = a;
                } else {
                    a3.addHeader(HTTP.TARGET_HOST, httpHost.toHostString());
                    if (gn.a()) {
                        a(a3);
                    }
                    if (a2.length() > 8192) {
                        gn.d("Hit too long (> 8192 bytes)--not sent");
                        i3++;
                    } else if (this.d.b()) {
                        gn.b("Dry run enabled. Hit not actually sent.");
                    } else {
                        if (z2) {
                            try {
                                ft.b(this.c);
                                z2 = false;
                            } catch (ClientProtocolException e) {
                                gn.d("ClientProtocolException sending hit; discarding hit...");
                                enVar.a("_hd", i3);
                            } catch (IOException e2) {
                                gn.d("Exception sending hit: " + e2.getClass().getSimpleName());
                                gn.d(e2.getMessage());
                                enVar.a("_de", 1);
                                enVar.a("_hd", i3);
                                enVar.a("_hs", i2);
                                a(enVar, a, z2);
                                return i2;
                            }
                        }
                        HttpResponse execute = this.b.execute(httpHost, a3);
                        int statusCode = execute.getStatusLine().getStatusCode();
                        HttpEntity entity = execute.getEntity();
                        if (entity != null) {
                            entity.consumeContent();
                        }
                        if (statusCode != 200) {
                            gn.d("Bad response: " + execute.getStatusLine().getStatusCode());
                        }
                    }
                    enVar.a("_td", a2.getBytes().length);
                    i = i2 + 1;
                    url = a;
                }
            }
            i4++;
            i2 = i;
            url2 = url;
        }
        enVar.a("_hd", i3);
        enVar.a("_hs", i2);
        if (z) {
            a(enVar, url2, z2);
        }
        return i2;
    }

    String a(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", str, str2, str3, str4, str5, str6);
    }

    URL a(gk gkVar) {
        if (this.e != null) {
            return this.e;
        }
        try {
            return new URL("http:".equals(gkVar.d()) ? "http://www.google-analytics.com/collect" : "https://ssl.google-analytics.com/collect");
        } catch (MalformedURLException e) {
            gn.a("Error trying to parse the hardcoded host url. This really shouldn't happen.");
            return null;
        }
    }

    @Override // defpackage.fq
    public void a(String str) {
        try {
            this.e = new URL(str);
        } catch (MalformedURLException e) {
            this.e = null;
        }
    }

    @Override // defpackage.fq
    public boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.c.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        gn.c("...no network connectivity");
        return false;
    }

    boolean b() {
        return Math.random() * 100.0d <= 1.0d;
    }
}
