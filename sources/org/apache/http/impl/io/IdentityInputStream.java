package org.apache.http.impl.io;

import java.io.InputStream;
import org.apache.http.io.SessionInputBuffer;

@Deprecated
/* loaded from: classes.dex */
public class IdentityInputStream extends InputStream {
    public IdentityInputStream(SessionInputBuffer sessionInputBuffer) {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.InputStream
    public int available() {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.InputStream
    public int read() {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        throw new RuntimeException("Stub!");
    }
}
