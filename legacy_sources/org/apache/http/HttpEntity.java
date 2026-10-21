package org.apache.http;

import java.io.InputStream;
import java.io.OutputStream;

@Deprecated
/* loaded from: classes.dex */
public interface HttpEntity {
    void consumeContent();

    InputStream getContent();

    Header getContentEncoding();

    long getContentLength();

    Header getContentType();

    boolean isChunked();

    boolean isRepeatable();

    boolean isStreaming();

    void writeTo(OutputStream outputStream);
}
