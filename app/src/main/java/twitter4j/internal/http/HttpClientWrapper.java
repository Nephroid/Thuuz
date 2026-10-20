package twitter4j.internal.http;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import twitter4j.TwitterException;
import twitter4j.auth.Authorization;
import twitter4j.conf.ConfigurationContext;

/* loaded from: classes.dex */
public final class HttpClientWrapper implements Serializable {
    private static final long serialVersionUID = -6511977105603119379L;
    private HttpClient http;
    private HttpResponseListener httpResponseListener;
    private final Map<String, String> requestHeaders;
    private final HttpClientWrapperConfiguration wrapperConf;

    public HttpClientWrapper(HttpClientWrapperConfiguration httpClientWrapperConfiguration) {
        this.wrapperConf = httpClientWrapperConfiguration;
        this.requestHeaders = httpClientWrapperConfiguration.getRequestHeaders();
        this.http = HttpClientFactory.getInstance(httpClientWrapperConfiguration);
    }

    public HttpClientWrapper() {
        this.wrapperConf = ConfigurationContext.getInstance();
        this.requestHeaders = this.wrapperConf.getRequestHeaders();
        this.http = HttpClientFactory.getInstance(this.wrapperConf);
    }

    public void shutdown() {
        this.http.shutdown();
    }

    private HttpResponse request(HttpRequest httpRequest) {
        try {
            HttpResponse request = this.http.request(httpRequest);
            if (this.httpResponseListener != null) {
                this.httpResponseListener.httpResponseReceived(new HttpResponseEvent(httpRequest, request, null));
            }
            return request;
        } catch (TwitterException e) {
            if (this.httpResponseListener != null) {
                this.httpResponseListener.httpResponseReceived(new HttpResponseEvent(httpRequest, null, e));
            }
            throw e;
        }
    }

    public void setHttpResponseListener(HttpResponseListener httpResponseListener) {
        this.httpResponseListener = httpResponseListener;
    }

    public HttpResponse get(String str, HttpParameter[] httpParameterArr, Authorization authorization) {
        return request(new HttpRequest(RequestMethod.GET, str, httpParameterArr, authorization, this.requestHeaders));
    }

    public HttpResponse get(String str, HttpParameter[] httpParameterArr) {
        return request(new HttpRequest(RequestMethod.GET, str, httpParameterArr, null, this.requestHeaders));
    }

    public HttpResponse get(String str, Authorization authorization) {
        return request(new HttpRequest(RequestMethod.GET, str, null, authorization, this.requestHeaders));
    }

    public HttpResponse get(String str) {
        return request(new HttpRequest(RequestMethod.GET, str, null, null, this.requestHeaders));
    }

    public HttpResponse post(String str, HttpParameter[] httpParameterArr, Authorization authorization) {
        return request(new HttpRequest(RequestMethod.POST, str, httpParameterArr, authorization, this.requestHeaders));
    }

    public HttpResponse post(String str, HttpParameter[] httpParameterArr) {
        return request(new HttpRequest(RequestMethod.POST, str, httpParameterArr, null, this.requestHeaders));
    }

    public HttpResponse post(String str, HttpParameter[] httpParameterArr, Map<String, String> map) {
        HashMap hashMap = new HashMap(this.requestHeaders);
        if (map != null) {
            hashMap.putAll(map);
        }
        return request(new HttpRequest(RequestMethod.POST, str, httpParameterArr, null, hashMap));
    }

    public HttpResponse post(String str, Authorization authorization) {
        return request(new HttpRequest(RequestMethod.POST, str, null, authorization, this.requestHeaders));
    }

    public HttpResponse post(String str) {
        return request(new HttpRequest(RequestMethod.POST, str, null, null, this.requestHeaders));
    }

    public HttpResponse delete(String str, HttpParameter[] httpParameterArr, Authorization authorization) {
        return request(new HttpRequest(RequestMethod.DELETE, str, httpParameterArr, authorization, this.requestHeaders));
    }

    public HttpResponse delete(String str, HttpParameter[] httpParameterArr) {
        return request(new HttpRequest(RequestMethod.DELETE, str, httpParameterArr, null, this.requestHeaders));
    }

    public HttpResponse delete(String str, Authorization authorization) {
        return request(new HttpRequest(RequestMethod.DELETE, str, null, authorization, this.requestHeaders));
    }

    public HttpResponse delete(String str) {
        return request(new HttpRequest(RequestMethod.DELETE, str, null, null, this.requestHeaders));
    }

    public HttpResponse head(String str, HttpParameter[] httpParameterArr, Authorization authorization) {
        return request(new HttpRequest(RequestMethod.HEAD, str, httpParameterArr, authorization, this.requestHeaders));
    }

    public HttpResponse head(String str, HttpParameter[] httpParameterArr) {
        return request(new HttpRequest(RequestMethod.HEAD, str, httpParameterArr, null, this.requestHeaders));
    }

    public HttpResponse head(String str, Authorization authorization) {
        return request(new HttpRequest(RequestMethod.HEAD, str, null, authorization, this.requestHeaders));
    }

    public HttpResponse head(String str) {
        return request(new HttpRequest(RequestMethod.HEAD, str, null, null, this.requestHeaders));
    }

    public HttpResponse put(String str, HttpParameter[] httpParameterArr, Authorization authorization) {
        return request(new HttpRequest(RequestMethod.PUT, str, httpParameterArr, authorization, this.requestHeaders));
    }

    public HttpResponse put(String str, HttpParameter[] httpParameterArr) {
        return request(new HttpRequest(RequestMethod.PUT, str, httpParameterArr, null, this.requestHeaders));
    }

    public HttpResponse put(String str, Authorization authorization) {
        return request(new HttpRequest(RequestMethod.PUT, str, null, authorization, this.requestHeaders));
    }

    public HttpResponse put(String str) {
        return request(new HttpRequest(RequestMethod.PUT, str, null, null, this.requestHeaders));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HttpClientWrapper httpClientWrapper = (HttpClientWrapper) obj;
        return this.http.equals(httpClientWrapper.http) && this.requestHeaders.equals(httpClientWrapper.requestHeaders) && this.wrapperConf.equals(httpClientWrapper.wrapperConf);
    }

    public int hashCode() {
        return (((this.wrapperConf.hashCode() * 31) + this.http.hashCode()) * 31) + this.requestHeaders.hashCode();
    }

    public String toString() {
        return "HttpClientWrapper{wrapperConf=" + this.wrapperConf + ", http=" + this.http + ", requestHeaders=" + this.requestHeaders + ", httpResponseListener=" + this.httpResponseListener + '}';
    }
}
