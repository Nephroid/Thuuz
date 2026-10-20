package defpackage;

import com.google.android.gms.internal.pf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ajg {
    private aje<?, ?> a;
    private Object b;
    private List<ajk> c = new ArrayList();

    private byte[] b() {
        byte[] bArr = new byte[a()];
        a(pf.a(bArr));
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        int i = 0;
        if (this.b != null) {
            return this.a.a(this.b);
        }
        Iterator<ajk> it = this.c.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = it.next().a() + i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(pf pfVar) {
        if (this.b != null) {
            this.a.a(this.b, pfVar);
            return;
        }
        Iterator<ajk> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().a(pfVar);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ajg)) {
            return false;
        }
        ajg ajgVar = (ajg) obj;
        if (this.b != null && ajgVar.b != null) {
            if (this.a == ajgVar.a) {
                return !this.a.b.isArray() ? this.b.equals(ajgVar.b) : this.b instanceof byte[] ? Arrays.equals((byte[]) this.b, (byte[]) ajgVar.b) : this.b instanceof int[] ? Arrays.equals((int[]) this.b, (int[]) ajgVar.b) : this.b instanceof long[] ? Arrays.equals((long[]) this.b, (long[]) ajgVar.b) : this.b instanceof float[] ? Arrays.equals((float[]) this.b, (float[]) ajgVar.b) : this.b instanceof double[] ? Arrays.equals((double[]) this.b, (double[]) ajgVar.b) : this.b instanceof boolean[] ? Arrays.equals((boolean[]) this.b, (boolean[]) ajgVar.b) : Arrays.deepEquals((Object[]) this.b, (Object[]) ajgVar.b);
            }
            return false;
        }
        if (this.c != null && ajgVar.c != null) {
            return this.c.equals(ajgVar.c);
        }
        try {
            return Arrays.equals(b(), ajgVar.b());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public int hashCode() {
        try {
            return Arrays.hashCode(b()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
