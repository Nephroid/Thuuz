package org.apache.http.conn;

@Deprecated
/* loaded from: classes.dex */
public interface ConnectionReleaseTrigger {
    void abortConnection();

    void releaseConnection();
}
