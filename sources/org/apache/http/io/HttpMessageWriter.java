package org.apache.http.io;

import org.apache.http.HttpMessage;

@Deprecated
/* loaded from: classes.dex */
public interface HttpMessageWriter {
    void write(HttpMessage httpMessage);
}
