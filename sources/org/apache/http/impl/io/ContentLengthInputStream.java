package org.apache.http.impl.io;

import java.io.InputStream;
import org.apache.http.io.SessionInputBuffer;

@Deprecated
/* loaded from: classes.dex */
public class ContentLengthInputStream extends InputStream {
    public ContentLengthInputStream(SessionInputBuffer sessionInputBuffer, long j) {
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

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        throw new RuntimeException("Stub!");
    }
}
