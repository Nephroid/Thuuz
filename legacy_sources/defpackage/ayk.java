package defpackage;

import java.io.Writer;

/* loaded from: classes.dex */
final class ayk extends Writer {
    private final Appendable a;
    private final ayl b;

    private ayk(Appendable appendable) {
        this.b = new ayl();
        this.a = appendable;
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        this.b.a = cArr;
        this.a.append(this.b, i, i + i2);
    }

    @Override // java.io.Writer
    public void write(int i) {
        this.a.append((char) i);
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }
}
