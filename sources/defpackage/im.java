package defpackage;

import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;

/* loaded from: classes.dex */
public abstract class im {
    protected final DataHolder a;
    protected int b;
    private int c;

    public im(DataHolder dataHolder, int i) {
        this.a = (DataHolder) kb.a(dataHolder);
        a(i);
    }

    protected void a(int i) {
        kb.a(i >= 0 && i < this.a.g());
        this.b = i;
        this.c = this.a.a(this.b);
    }

    public boolean a(String str) {
        return this.a.a(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int b() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long b(String str) {
        return this.a.a(str, this.b, this.c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int c(String str) {
        return this.a.b(str, this.b, this.c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean d(String str) {
        return this.a.d(str, this.b, this.c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String e(String str) {
        return this.a.c(str, this.b, this.c);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof im)) {
            return false;
        }
        im imVar = (im) obj;
        return jy.a(Integer.valueOf(imVar.b), Integer.valueOf(this.b)) && jy.a(Integer.valueOf(imVar.c), Integer.valueOf(this.c)) && imVar.a == this.a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float f(String str) {
        return this.a.e(str, this.b, this.c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] g(String str) {
        return this.a.f(str, this.b, this.c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Uri h(String str) {
        return this.a.g(str, this.b, this.c);
    }

    public int hashCode() {
        return jy.a(Integer.valueOf(this.b), Integer.valueOf(this.c), this.a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean i(String str) {
        return this.a.h(str, this.b, this.c);
    }
}
