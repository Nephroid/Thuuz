package twitter4j.internal.http;

import java.io.DataOutputStream;
import java.io.Serializable;
import twitter4j.internal.logging.Logger;

/* loaded from: classes.dex */
public abstract class HttpClientBase implements Serializable, HttpClient {
    private static final Logger logger = Logger.getLogger(HttpClientBase.class);
    private static final long serialVersionUID = 6944924907755685265L;
    protected final HttpClientConfiguration CONF;

    public HttpClientBase(HttpClientConfiguration httpClientConfiguration) {
        this.CONF = httpClientConfiguration;
    }

    @Override // twitter4j.internal.http.HttpClient
    public void shutdown() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isProxyConfigured() {
        return (this.CONF.getHttpProxyHost() == null || this.CONF.getHttpProxyHost().equals("")) ? false : true;
    }

    public void write(DataOutputStream dataOutputStream, String str) {
        dataOutputStream.writeBytes(str);
        logger.debug(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof HttpClientBase) && this.CONF.equals(((HttpClientBase) obj).CONF);
    }

    public int hashCode() {
        return this.CONF.hashCode();
    }

    public String toString() {
        return "HttpClientBase{CONF=" + this.CONF + '}';
    }
}
