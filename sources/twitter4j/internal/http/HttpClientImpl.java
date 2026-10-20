package twitter4j.internal.http;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.auth.AUTH;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.protocol.HTTP;
import twitter4j.TwitterException;
import twitter4j.conf.ConfigurationContext;
import twitter4j.internal.logging.Logger;
import twitter4j.internal.util.z_T4JInternalStringUtil;

/* loaded from: classes.dex */
public class HttpClientImpl extends HttpClientBase implements Serializable, HttpResponseCode {
    private static final Map<HttpClientConfiguration, HttpClient> instanceMap;
    private static final Logger logger = Logger.getLogger(HttpClientImpl.class);
    private static final long serialVersionUID = -8819171414069621503L;

    static {
        if (ConfigurationContext.getInstance().isDalvik()) {
            System.setProperty("http.keepAlive", "false");
        }
        instanceMap = new HashMap(1);
    }

    public HttpClientImpl() {
        super(ConfigurationContext.getInstance());
    }

    public HttpClientImpl(HttpClientConfiguration httpClientConfiguration) {
        super(httpClientConfiguration);
    }

    public static HttpClient getInstance(HttpClientConfiguration httpClientConfiguration) {
        HttpClient httpClient = instanceMap.get(httpClientConfiguration);
        if (httpClient == null) {
            HttpClientImpl httpClientImpl = new HttpClientImpl(httpClientConfiguration);
            instanceMap.put(httpClientConfiguration, httpClientImpl);
            return httpClientImpl;
        }
        return httpClient;
    }

    public HttpResponse get(String str) {
        return request(new HttpRequest(RequestMethod.GET, str, null, null, null));
    }

    public HttpResponse post(String str, HttpParameter[] httpParameterArr) {
        return request(new HttpRequest(RequestMethod.POST, str, httpParameterArr, null, null));
    }

    @Override // twitter4j.internal.http.HttpClient
    public HttpResponse request(HttpRequest httpRequest) {
        HttpResponseImpl httpResponseImpl;
        Throwable th;
        OutputStream outputStream;
        int i;
        HttpResponseImpl httpResponseImpl2;
        HttpURLConnection connection;
        OutputStream outputStream2;
        OutputStream outputStream3;
        int httpRetryCount = this.CONF.getHttpRetryCount() + 1;
        HttpResponseImpl httpResponseImpl3 = null;
        for (int i2 = 0; i2 < httpRetryCount; i2++) {
            int i3 = -1;
            try {
                connection = getConnection(httpRequest.getURL());
                connection.setDoInput(true);
                setHeaders(httpRequest, connection);
                connection.setRequestMethod(httpRequest.getMethod().name());
                if (httpRequest.getMethod() != RequestMethod.POST) {
                    outputStream2 = null;
                } else {
                    if (HttpParameter.containsFile(httpRequest.getParameters())) {
                        String str = "----Twitter4J-upload" + System.currentTimeMillis();
                        connection.setRequestProperty(HTTP.CONTENT_TYPE, "multipart/form-data; boundary=" + str);
                        String str2 = "--" + str;
                        connection.setDoOutput(true);
                        outputStream3 = connection.getOutputStream();
                        try {
                            DataOutputStream dataOutputStream = new DataOutputStream(outputStream3);
                            for (HttpParameter httpParameter : httpRequest.getParameters()) {
                                if (httpParameter.isFile()) {
                                    write(dataOutputStream, str2 + "\r\n");
                                    write(dataOutputStream, "Content-Disposition: form-data; name=\"" + httpParameter.getName() + "\"; filename=\"" + httpParameter.getFile().getName() + "\"\r\n");
                                    write(dataOutputStream, "Content-Type: " + httpParameter.getContentType() + "\r\n\r\n");
                                    BufferedInputStream bufferedInputStream = new BufferedInputStream(httpParameter.hasFileBody() ? httpParameter.getFileBody() : new FileInputStream(httpParameter.getFile()));
                                    byte[] bArr = new byte[1024];
                                    while (true) {
                                        int read = bufferedInputStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        dataOutputStream.write(bArr, 0, read);
                                    }
                                    write(dataOutputStream, "\r\n");
                                    bufferedInputStream.close();
                                } else {
                                    write(dataOutputStream, str2 + "\r\n");
                                    write(dataOutputStream, "Content-Disposition: form-data; name=\"" + httpParameter.getName() + "\"\r\n");
                                    write(dataOutputStream, "Content-Type: text/plain; charset=UTF-8\r\n\r\n");
                                    logger.debug(httpParameter.getValue());
                                    dataOutputStream.write(httpParameter.getValue().getBytes(HTTP.UTF_8));
                                    write(dataOutputStream, "\r\n");
                                }
                            }
                            write(dataOutputStream, str2 + "--\r\n");
                            write(dataOutputStream, "\r\n");
                        } catch (Throwable th2) {
                            httpResponseImpl = httpResponseImpl3;
                            th = th2;
                            outputStream = outputStream3;
                            i = -1;
                            try {
                                try {
                                    outputStream.close();
                                } catch (Exception e) {
                                }
                                throw th;
                                break;
                            } catch (IOException e2) {
                                e = e2;
                                i3 = i;
                                httpResponseImpl2 = httpResponseImpl;
                            }
                        }
                    } else {
                        connection.setRequestProperty(HTTP.CONTENT_TYPE, URLEncodedUtils.CONTENT_TYPE);
                        String encodeParameters = HttpParameter.encodeParameters(httpRequest.getParameters());
                        logger.debug("Post Params: ", encodeParameters);
                        byte[] bytes = encodeParameters.getBytes(HTTP.UTF_8);
                        connection.setRequestProperty(HTTP.CONTENT_LEN, Integer.toString(bytes.length));
                        connection.setDoOutput(true);
                        outputStream3 = connection.getOutputStream();
                        outputStream3.write(bytes);
                    }
                    outputStream3.flush();
                    outputStream3.close();
                    outputStream2 = outputStream3;
                }
                try {
                    httpResponseImpl2 = new HttpResponseImpl(connection, this.CONF);
                } catch (Throwable th3) {
                    i = -1;
                    OutputStream outputStream4 = outputStream2;
                    httpResponseImpl = httpResponseImpl3;
                    th = th3;
                    outputStream = outputStream4;
                }
            } catch (Throwable th4) {
                httpResponseImpl = httpResponseImpl3;
                th = th4;
                outputStream = null;
                i = -1;
            }
            try {
                i3 = connection.getResponseCode();
                if (logger.isDebugEnabled()) {
                    logger.debug("Response: ");
                    Map<String, List<String>> headerFields = connection.getHeaderFields();
                    for (String str3 : headerFields.keySet()) {
                        for (String str4 : headerFields.get(str3)) {
                            if (str3 != null) {
                                logger.debug(str3 + ": " + str4);
                            } else {
                                logger.debug(str4);
                            }
                        }
                    }
                }
                if (i3 < 200 || (i3 != 302 && 300 <= i3)) {
                    if (i3 == 420 || i3 == 400 || i3 < 500 || i2 == this.CONF.getHttpRetryCount()) {
                        throw new TwitterException(httpResponseImpl2.asString(), httpResponseImpl2);
                        break;
                    }
                    try {
                        try {
                            outputStream2.close();
                        } catch (Exception e3) {
                        }
                        httpResponseImpl3 = httpResponseImpl2;
                    } catch (IOException e4) {
                        e = e4;
                    }
                    try {
                        if (logger.isDebugEnabled() && httpResponseImpl3 != null) {
                            httpResponseImpl3.asString();
                        }
                        logger.debug("Sleeping " + this.CONF.getHttpRetryIntervalSeconds() + " seconds until the next retry.");
                        Thread.sleep(this.CONF.getHttpRetryIntervalSeconds() * 1000);
                    } catch (InterruptedException e5) {
                    }
                } else {
                    try {
                        outputStream2.close();
                    } catch (Exception e6) {
                    }
                    return httpResponseImpl2;
                }
                e = e4;
                if (i2 == this.CONF.getHttpRetryCount()) {
                    throw new TwitterException(e.getMessage(), e, i3);
                }
                httpResponseImpl3 = httpResponseImpl2;
                if (logger.isDebugEnabled()) {
                    httpResponseImpl3.asString();
                }
                logger.debug("Sleeping " + this.CONF.getHttpRetryIntervalSeconds() + " seconds until the next retry.");
                Thread.sleep(this.CONF.getHttpRetryIntervalSeconds() * 1000);
            } catch (Throwable th5) {
                th = th5;
                outputStream = outputStream2;
                httpResponseImpl = httpResponseImpl2;
                i = i3;
                outputStream.close();
                throw th;
                break;
                break;
            }
        }
        return httpResponseImpl3;
    }

    private void setHeaders(HttpRequest httpRequest, HttpURLConnection httpURLConnection) {
        String authorizationHeader;
        if (logger.isDebugEnabled()) {
            logger.debug("Request: ");
            logger.debug(httpRequest.getMethod().name() + " ", httpRequest.getURL());
        }
        if (httpRequest.getAuthorization() != null && (authorizationHeader = httpRequest.getAuthorization().getAuthorizationHeader(httpRequest)) != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Authorization: ", z_T4JInternalStringUtil.maskString(authorizationHeader));
            }
            httpURLConnection.addRequestProperty(AUTH.WWW_AUTH_RESP, authorizationHeader);
        }
        if (httpRequest.getRequestHeaders() != null) {
            for (String str : httpRequest.getRequestHeaders().keySet()) {
                httpURLConnection.addRequestProperty(str, httpRequest.getRequestHeaders().get(str));
                logger.debug(str + ": " + httpRequest.getRequestHeaders().get(str));
            }
        }
    }

    protected HttpURLConnection getConnection(String str) {
        HttpURLConnection httpURLConnection;
        if (isProxyConfigured()) {
            if (this.CONF.getHttpProxyUser() != null && !this.CONF.getHttpProxyUser().equals("")) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Proxy AuthUser: " + this.CONF.getHttpProxyUser());
                    logger.debug("Proxy AuthPassword: " + z_T4JInternalStringUtil.maskString(this.CONF.getHttpProxyPassword()));
                }
                Authenticator.setDefault(new Authenticator() { // from class: twitter4j.internal.http.HttpClientImpl.1
                    @Override // java.net.Authenticator
                    protected PasswordAuthentication getPasswordAuthentication() {
                        if (getRequestorType().equals(Authenticator.RequestorType.PROXY)) {
                            return new PasswordAuthentication(HttpClientImpl.this.CONF.getHttpProxyUser(), HttpClientImpl.this.CONF.getHttpProxyPassword().toCharArray());
                        }
                        return null;
                    }
                });
            }
            Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(this.CONF.getHttpProxyHost(), this.CONF.getHttpProxyPort()));
            if (logger.isDebugEnabled()) {
                logger.debug("Opening proxied connection(" + this.CONF.getHttpProxyHost() + ":" + this.CONF.getHttpProxyPort() + ")");
            }
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection(proxy);
        } else {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        }
        if (this.CONF.getHttpConnectionTimeout() > 0) {
            httpURLConnection.setConnectTimeout(this.CONF.getHttpConnectionTimeout());
        }
        if (this.CONF.getHttpReadTimeout() > 0) {
            httpURLConnection.setReadTimeout(this.CONF.getHttpReadTimeout());
        }
        httpURLConnection.setInstanceFollowRedirects(false);
        return httpURLConnection;
    }
}
