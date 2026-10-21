package defpackage;

import com.google.android.gms.internal.pf;
import java.io.IOException;

/* loaded from: classes.dex */
class ajb implements aik {
    private pf a;
    private byte[] b;
    private final int c;

    public ajb(int i) {
        this.c = i;
        a();
    }

    @Override // defpackage.aik
    public void a() {
        this.b = new byte[this.c];
        this.a = pf.a(this.b);
    }

    @Override // defpackage.aik
    public void a(int i, long j) {
        this.a.a(i, j);
    }

    @Override // defpackage.aik
    public void a(int i, String str) {
        this.a.a(i, str);
    }

    @Override // defpackage.aik
    public byte[] b() {
        int a = this.a.a();
        if (a < 0) {
            throw new IOException();
        }
        if (a == 0) {
            return this.b;
        }
        byte[] bArr = new byte[this.b.length - a];
        System.arraycopy(this.b, 0, bArr, 0, bArr.length);
        return bArr;
    }
}
