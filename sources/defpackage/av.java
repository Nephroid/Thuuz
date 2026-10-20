package defpackage;

import java.io.FilterInputStream;
import java.io.InputStream;

/* loaded from: classes.dex */
class av extends FilterInputStream {
    private int a;

    private av(InputStream inputStream) {
        super(inputStream);
        this.a = 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int read = super.read();
        if (read != -1) {
            this.a++;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int read = super.read(bArr, i, i2);
        if (read != -1) {
            this.a += read;
        }
        return read;
    }
}
