package defpackage;

import com.google.android.gms.internal.pf;
import defpackage.ajd;
import java.io.IOException;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class aje<M extends ajd<M>, T> {
    protected final int a;
    protected final Class<T> b;
    protected final int c;
    protected final boolean d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(Object obj) {
        return this.d ? b(obj) : c(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Object obj, pf pfVar) {
        if (this.d) {
            c(obj, pfVar);
        } else {
            b(obj, pfVar);
        }
    }

    protected int b(Object obj) {
        int i = 0;
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            if (Array.get(obj, i2) != null) {
                i += c(Array.get(obj, i2));
            }
        }
        return i;
    }

    protected void b(Object obj, pf pfVar) {
        try {
            pfVar.e(this.c);
            switch (this.a) {
                case 10:
                    int a = ajl.a(this.c);
                    pfVar.a((aji) obj);
                    pfVar.c(a, 4);
                    return;
                case 11:
                    pfVar.b((aji) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.a);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected int c(Object obj) {
        int a = ajl.a(this.c);
        switch (this.a) {
            case 10:
                return pf.b(a, (aji) obj);
            case 11:
                return pf.c(a, (aji) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.a);
        }
    }

    protected void c(Object obj, pf pfVar) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                b(obj2, pfVar);
            }
        }
    }
}
