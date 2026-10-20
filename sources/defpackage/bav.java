package defpackage;

import com.facebook.internal.ServerProtocol;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* loaded from: classes.dex */
public class bav implements Closeable, Flushable {
    private static final String[] a = new String[128];
    private static final String[] b;
    private final Writer c;
    private int[] d = new int[32];
    private int e = 0;
    private String f;
    private String g;
    private boolean h;
    private boolean i;
    private String j;
    private boolean k;

    static {
        for (int i = 0; i <= 31; i++) {
            a[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        a[34] = "\\\"";
        a[92] = "\\\\";
        a[9] = "\\t";
        a[8] = "\\b";
        a[10] = "\\n";
        a[13] = "\\r";
        a[12] = "\\f";
        b = (String[]) a.clone();
        b[60] = "\\u003c";
        b[62] = "\\u003e";
        b[38] = "\\u0026";
        b[61] = "\\u003d";
        b[39] = "\\u0027";
    }

    public bav(Writer writer) {
        a(6);
        this.g = ":";
        this.k = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.c = writer;
    }

    public final void c(String str) {
        if (str.length() == 0) {
            this.f = null;
            this.g = ":";
        } else {
            this.f = str;
            this.g = ": ";
        }
    }

    public final void b(boolean z) {
        this.h = z;
    }

    public boolean g() {
        return this.h;
    }

    public final void c(boolean z) {
        this.i = z;
    }

    public final boolean h() {
        return this.i;
    }

    public final void d(boolean z) {
        this.k = z;
    }

    public final boolean i() {
        return this.k;
    }

    public bav b() {
        j();
        return a(1, "[");
    }

    public bav c() {
        return a(1, 2, "]");
    }

    public bav d() {
        j();
        return a(3, "{");
    }

    public bav e() {
        return a(3, 5, "}");
    }

    private bav a(int i, String str) {
        e(true);
        a(i);
        this.c.write(str);
        return this;
    }

    private bav a(int i, int i2, String str) {
        int a2 = a();
        if (a2 != i2 && a2 != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.j != null) {
            throw new IllegalStateException("Dangling name: " + this.j);
        }
        this.e--;
        if (a2 == i2) {
            k();
        }
        this.c.write(str);
        return this;
    }

    private void a(int i) {
        if (this.e == this.d.length) {
            int[] iArr = new int[this.e * 2];
            System.arraycopy(this.d, 0, iArr, 0, this.e);
            this.d = iArr;
        }
        int[] iArr2 = this.d;
        int i2 = this.e;
        this.e = i2 + 1;
        iArr2[i2] = i;
    }

    private int a() {
        if (this.e == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        return this.d[this.e - 1];
    }

    private void b(int i) {
        this.d[this.e - 1] = i;
    }

    public bav a(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.j != null) {
            throw new IllegalStateException();
        }
        if (this.e == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.j = str;
        return this;
    }

    private void j() {
        if (this.j != null) {
            l();
            d(this.j);
            this.j = null;
        }
    }

    public bav b(String str) {
        if (str == null) {
            return f();
        }
        j();
        e(false);
        d(str);
        return this;
    }

    public bav f() {
        if (this.j != null) {
            if (this.k) {
                j();
            } else {
                this.j = null;
                return this;
            }
        }
        e(false);
        this.c.write("null");
        return this;
    }

    public bav a(boolean z) {
        j();
        e(false);
        this.c.write(z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        return this;
    }

    public bav a(long j) {
        j();
        e(false);
        this.c.write(Long.toString(j));
        return this;
    }

    public bav a(Number number) {
        if (number == null) {
            return f();
        }
        j();
        String obj = number.toString();
        if (!this.h && (obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        }
        e(false);
        this.c.append((CharSequence) obj);
        return this;
    }

    public void flush() {
        if (this.e == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.c.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.c.close();
        int i = this.e;
        if (i > 1 || (i == 1 && this.d[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.e = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(java.lang.String r8) {
        /*
            r7 = this;
            r1 = 0
            boolean r0 = r7.i
            if (r0 == 0) goto L25
            java.lang.String[] r0 = defpackage.bav.b
        L7:
            java.io.Writer r2 = r7.c
            java.lang.String r3 = "\""
            r2.write(r3)
            int r4 = r8.length()
            r3 = r1
        L13:
            if (r3 >= r4) goto L46
            char r2 = r8.charAt(r3)
            r5 = 128(0x80, float:1.794E-43)
            if (r2 >= r5) goto L28
            r2 = r0[r2]
            if (r2 != 0) goto L2e
        L21:
            int r2 = r3 + 1
            r3 = r2
            goto L13
        L25:
            java.lang.String[] r0 = defpackage.bav.a
            goto L7
        L28:
            r5 = 8232(0x2028, float:1.1535E-41)
            if (r2 != r5) goto L3f
            java.lang.String r2 = "\\u2028"
        L2e:
            if (r1 >= r3) goto L37
            java.io.Writer r5 = r7.c
            int r6 = r3 - r1
            r5.write(r8, r1, r6)
        L37:
            java.io.Writer r1 = r7.c
            r1.write(r2)
            int r1 = r3 + 1
            goto L21
        L3f:
            r5 = 8233(0x2029, float:1.1537E-41)
            if (r2 != r5) goto L21
            java.lang.String r2 = "\\u2029"
            goto L2e
        L46:
            if (r1 >= r4) goto L4f
            java.io.Writer r0 = r7.c
            int r2 = r4 - r1
            r0.write(r8, r1, r2)
        L4f:
            java.io.Writer r0 = r7.c
            java.lang.String r1 = "\""
            r0.write(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bav.d(java.lang.String):void");
    }

    private void k() {
        if (this.f != null) {
            this.c.write("\n");
            int i = this.e;
            for (int i2 = 1; i2 < i; i2++) {
                this.c.write(this.f);
            }
        }
    }

    private void l() {
        int a2 = a();
        if (a2 == 5) {
            this.c.write(44);
        } else if (a2 != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        k();
        b(4);
    }

    private void e(boolean z) {
        switch (a()) {
            case 1:
                b(2);
                k();
                return;
            case 2:
                this.c.append(',');
                k();
                return;
            case 3:
            case 5:
            default:
                throw new IllegalStateException("Nesting problem.");
            case 4:
                this.c.append((CharSequence) this.g);
                b(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.h) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
        }
        if (!this.h && !z) {
            throw new IllegalStateException("JSON must start with an array or an object.");
        }
        b(7);
    }
}
