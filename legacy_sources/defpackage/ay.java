package defpackage;

import com.android.volley.Request;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public class ay implements ax {
    private final az a;
    private final SSLSocketFactory b;

    public ay() {
        this(null);
    }

    public ay(az azVar) {
        this(azVar, null);
    }

    public ay(az azVar, SSLSocketFactory sSLSocketFactory) {
        this.a = azVar;
        this.b = sSLSocketFactory;
    }

    @Override // defpackage.ax
    public HttpResponse a(Request<?> request, Map<String, String> map) {
        String str;
        String c = request.c();
        HashMap hashMap = new HashMap();
        hashMap.putAll(request.h());
        hashMap.putAll(map);
        if (this.a != null) {
            str = this.a.a(c);
            if (str == null) {
                throw new IOException("URL blocked by rewriter: " + c);
            }
        } else {
            str = c;
        }
        HttpURLConnection a = a(new URL(str), request);
        for (String str2 : hashMap.keySet()) {
            a.addRequestProperty(str2, (String) hashMap.get(str2));
        }
        a(a, request);
        ProtocolVersion protocolVersion = new ProtocolVersion(HttpVersion.HTTP, 1, 1);
        if (a.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, a.getResponseCode(), a.getResponseMessage()));
        basicHttpResponse.setEntity(a(a));
        for (Map.Entry<String, List<String>> entry : a.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader(entry.getKey(), entry.getValue().get(0)));
            }
        }
        return basicHttpResponse;
    }

    private static HttpEntity a(HttpURLConnection httpURLConnection) {
        InputStream errorStream;
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            errorStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            errorStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(errorStream);
        basicHttpEntity.setContentLength(httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    protected HttpURLConnection a(URL url) {
        return (HttpURLConnection) url.openConnection();
    }

    private HttpURLConnection a(URL url, Request<?> request) {
        HttpURLConnection a = a(url);
        int s = request.s();
        a.setConnectTimeout(s);
        a.setReadTimeout(s);
        a.setUseCaches(false);
        a.setDoInput(true);
        if ("https".equals(url.getProtocol()) && this.b != null) {
            ((HttpsURLConnection) a).setSSLSocketFactory(this.b);
        }
        return a;
    }

    static void a(HttpURLConnection httpURLConnection, Request<?> request) {
        switch (request.a()) {
            case -1:
                byte[] l = request.l();
                if (l != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
                    httpURLConnection.addRequestProperty(HTTP.CONTENT_TYPE, request.k());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(l);
                    dataOutputStream.close();
                    return;
                }
                return;
            case 0:
                httpURLConnection.setRequestMethod(HttpGet.METHOD_NAME);
                return;
            case 1:
                httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
                b(httpURLConnection, request);
                return;
            case 2:
                httpURLConnection.setRequestMethod(HttpPut.METHOD_NAME);
                b(httpURLConnection, request);
                return;
            case 3:
                httpURLConnection.setRequestMethod(HttpDelete.METHOD_NAME);
                return;
            case 4:
                httpURLConnection.setRequestMethod(HttpHead.METHOD_NAME);
                return;
            case 5:
                httpURLConnection.setRequestMethod(HttpOptions.METHOD_NAME);
                return;
            case 6:
                httpURLConnection.setRequestMethod(HttpTrace.METHOD_NAME);
                return;
            case 7:
                httpURLConnection.setRequestMethod("PATCH");
                b(httpURLConnection, request);
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static void b(HttpURLConnection httpURLConnection, Request<?> request) {
        byte[] p = request.p();
        if (p != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty(HTTP.CONTENT_TYPE, request.o());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(p);
            dataOutputStream.close();
        }
    }
}
