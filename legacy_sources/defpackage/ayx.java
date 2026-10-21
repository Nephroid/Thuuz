package defpackage;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class ayx extends bav {
    private static final Writer a = new ayy();
    private static final awx b = new awx("closed");
    private final List<awu> c;
    private String d;
    private awu e;

    public ayx() {
        super(a);
        this.c = new ArrayList();
        this.e = awv.a;
    }

    public awu a() {
        if (!this.c.isEmpty()) {
            throw new IllegalStateException("Expected one JSON element but was " + this.c);
        }
        return this.e;
    }

    private awu j() {
        return this.c.get(this.c.size() - 1);
    }

    private void a(awu awuVar) {
        if (this.d != null) {
            if (!awuVar.g() || i()) {
                ((aww) j()).a(this.d, awuVar);
            }
            this.d = null;
            return;
        }
        if (this.c.isEmpty()) {
            this.e = awuVar;
            return;
        }
        awu j = j();
        if (j instanceof awr) {
            ((awr) j).a(awuVar);
            return;
        }
        throw new IllegalStateException();
    }

    @Override // defpackage.bav
    public bav b() {
        awr awrVar = new awr();
        a(awrVar);
        this.c.add(awrVar);
        return this;
    }

    @Override // defpackage.bav
    public bav c() {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        }
        if (j() instanceof awr) {
            this.c.remove(this.c.size() - 1);
            return this;
        }
        throw new IllegalStateException();
    }

    @Override // defpackage.bav
    public bav d() {
        aww awwVar = new aww();
        a(awwVar);
        this.c.add(awwVar);
        return this;
    }

    @Override // defpackage.bav
    public bav e() {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        }
        if (j() instanceof aww) {
            this.c.remove(this.c.size() - 1);
            return this;
        }
        throw new IllegalStateException();
    }

    @Override // defpackage.bav
    public bav a(String str) {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        }
        if (j() instanceof aww) {
            this.d = str;
            return this;
        }
        throw new IllegalStateException();
    }

    @Override // defpackage.bav
    public bav b(String str) {
        if (str == null) {
            return f();
        }
        a(new awx(str));
        return this;
    }

    @Override // defpackage.bav
    public bav f() {
        a(awv.a);
        return this;
    }

    @Override // defpackage.bav
    public bav a(boolean z) {
        a(new awx(Boolean.valueOf(z)));
        return this;
    }

    @Override // defpackage.bav
    public bav a(long j) {
        a(new awx((Number) Long.valueOf(j)));
        return this;
    }

    @Override // defpackage.bav
    public bav a(Number number) {
        if (number == null) {
            return f();
        }
        if (!g()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        a(new awx(number));
        return this;
    }

    @Override // defpackage.bav, java.io.Flushable
    public void flush() {
    }

    @Override // defpackage.bav, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.c.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.c.add(b);
    }
}
