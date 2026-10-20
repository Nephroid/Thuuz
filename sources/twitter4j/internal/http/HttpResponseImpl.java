package twitter4j.internal.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class HttpResponseImpl extends HttpResponse {
    private HttpURLConnection con;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpResponseImpl(HttpURLConnection httpURLConnection, HttpClientConfiguration httpClientConfiguration) {
        super(httpClientConfiguration);
        this.con = httpURLConnection;
        this.statusCode = httpURLConnection.getResponseCode();
        InputStream errorStream = httpURLConnection.getErrorStream();
        this.is = errorStream;
        if (errorStream == null) {
            this.is = httpURLConnection.getInputStream();
        }
        if (this.is != null && "gzip".equals(httpURLConnection.getContentEncoding())) {
            this.is = new StreamingGZIPInputStream(this.is);
        }
    }

    HttpResponseImpl(String str) {
        this.responseAsString = str;
    }

    @Override // twitter4j.internal.http.HttpResponse
    public String getResponseHeader(String str) {
        return this.con.getHeaderField(str);
    }

    @Override // twitter4j.internal.http.HttpResponse
    public Map<String, List<String>> getResponseHeaderFields() {
        return this.con.getHeaderFields();
    }

    @Override // twitter4j.internal.http.HttpResponse
    public void disconnect() {
        this.con.disconnect();
    }
}
