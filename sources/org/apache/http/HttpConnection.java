package org.apache.http;

@Deprecated
/* loaded from: classes.dex */
public interface HttpConnection {
    void close();

    HttpConnectionMetrics getMetrics();

    int getSocketTimeout();

    boolean isOpen();

    boolean isStale();

    void setSocketTimeout(int i);

    void shutdown();
}
