package org.apache.http.entity;

import java.io.OutputStream;

@Deprecated
/* loaded from: classes.dex */
public interface ContentProducer {
    void writeTo(OutputStream outputStream);
}
