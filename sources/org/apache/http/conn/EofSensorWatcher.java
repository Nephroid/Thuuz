package org.apache.http.conn;

import java.io.InputStream;

@Deprecated
/* loaded from: classes.dex */
public interface EofSensorWatcher {
    boolean eofDetected(InputStream inputStream);

    boolean streamAbort(InputStream inputStream);

    boolean streamClosed(InputStream inputStream);
}
