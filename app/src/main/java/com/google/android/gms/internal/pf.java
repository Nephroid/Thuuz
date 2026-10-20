package com.google.android.gms.internal;

import android.support.v4.media.TransportMediator;
import defpackage.aji;
import defpackage.ajl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public final class pf {
    private final byte[] a;
    private final int b;
    private int c;

    /* loaded from: classes.dex */
    public class a extends IOException {
        a(int i, int i2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + i + " limit " + i2 + ").");
        }
    }

    private pf(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.c = i;
        this.b = i + i2;
    }

    public static pf a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static pf a(byte[] bArr, int i, int i2) {
        return new pf(bArr, i, i2);
    }

    public static int b(int i) {
        if (i >= 0) {
            return f(i);
        }
        return 10;
    }

    public static int b(int i, int i2) {
        return d(i) + b(i2);
    }

    public static int b(int i, aji ajiVar) {
        return (d(i) * 2) + c(ajiVar);
    }

    public static int b(int i, String str) {
        return d(i) + b(str);
    }

    public static int b(int i, boolean z) {
        return d(i) + b(z);
    }

    public static int b(String str) {
        try {
            byte[] bytes = str.getBytes(HTTP.UTF_8);
            return bytes.length + f(bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    public static int b(boolean z) {
        return 1;
    }

    public static int c(int i, long j) {
        return d(i) + c(j);
    }

    public static int c(int i, aji ajiVar) {
        return d(i) + d(ajiVar);
    }

    public static int c(long j) {
        return f(j);
    }

    public static int c(aji ajiVar) {
        return ajiVar.f();
    }

    public static int d(int i) {
        return f(ajl.a(i, 0));
    }

    public static int d(int i, long j) {
        return d(i) + d(j);
    }

    public static int d(long j) {
        return f(g(j));
    }

    public static int d(aji ajiVar) {
        int f = ajiVar.f();
        return f + f(f);
    }

    public static int f(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return ((-268435456) & i) == 0 ? 4 : 5;
    }

    public static int f(long j) {
        if (((-128) & j) == 0) {
            return 1;
        }
        if (((-16384) & j) == 0) {
            return 2;
        }
        if (((-2097152) & j) == 0) {
            return 3;
        }
        if (((-268435456) & j) == 0) {
            return 4;
        }
        if (((-34359738368L) & j) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j) == 0) {
            return 8;
        }
        return (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    public static long g(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public int a() {
        return this.b - this.c;
    }

    public void a(byte b) {
        if (this.c == this.b) {
            throw new a(this.c, this.b);
        }
        byte[] bArr = this.a;
        int i = this.c;
        this.c = i + 1;
        bArr[i] = b;
    }

    public void a(int i) {
        if (i >= 0) {
            e(i);
        } else {
            e(i);
        }
    }

    public void a(int i, int i2) {
        c(i, 0);
        a(i2);
    }

    public void a(int i, long j) {
        c(i, 0);
        a(j);
    }

    public void a(int i, aji ajiVar) {
        c(i, 2);
        b(ajiVar);
    }

    public void a(int i, String str) {
        c(i, 2);
        a(str);
    }

    public void a(int i, boolean z) {
        c(i, 0);
        a(z);
    }

    public void a(long j) {
        e(j);
    }

    public void a(aji ajiVar) {
        ajiVar.a(this);
    }

    public void a(String str) {
        byte[] bytes = str.getBytes(HTTP.UTF_8);
        e(bytes.length);
        b(bytes);
    }

    public void a(boolean z) {
        c(z ? 1 : 0);
    }

    public void b() {
        if (a() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void b(int i, long j) {
        c(i, 0);
        b(j);
    }

    public void b(long j) {
        e(g(j));
    }

    public void b(aji ajiVar) {
        e(ajiVar.e());
        ajiVar.a(this);
    }

    public void b(byte[] bArr) {
        b(bArr, 0, bArr.length);
    }

    public void b(byte[] bArr, int i, int i2) {
        if (this.b - this.c < i2) {
            throw new a(this.c, this.b);
        }
        System.arraycopy(bArr, i, this.a, this.c, i2);
        this.c += i2;
    }

    public void c(int i) {
        a((byte) i);
    }

    public void c(int i, int i2) {
        e(ajl.a(i, i2));
    }

    public void e(int i) {
        while ((i & (-128)) != 0) {
            c((i & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            i >>>= 7;
        }
        c(i);
    }

    public void e(long j) {
        while (((-128) & j) != 0) {
            c((((int) j) & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            j >>>= 7;
        }
        c((int) j);
    }
}
