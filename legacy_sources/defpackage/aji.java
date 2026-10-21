package defpackage;

import com.google.android.gms.internal.pf;
import java.io.IOException;

/* loaded from: classes.dex */
public abstract class aji {
    protected volatile int n = -1;

    public static final void a(aji ajiVar, byte[] bArr, int i, int i2) {
        try {
            pf a = pf.a(bArr, i, i2);
            ajiVar.a(a);
            a.b();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final byte[] a(aji ajiVar) {
        byte[] bArr = new byte[ajiVar.f()];
        a(ajiVar, bArr, 0, bArr.length);
        return bArr;
    }

    public void a(pf pfVar) {
    }

    protected int b() {
        return 0;
    }

    public int e() {
        if (this.n < 0) {
            f();
        }
        return this.n;
    }

    public int f() {
        int b = b();
        this.n = b;
        return b;
    }

    public String toString() {
        return ajj.a(this);
    }
}
