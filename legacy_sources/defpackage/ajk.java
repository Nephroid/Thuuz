package defpackage;

import com.google.android.gms.internal.pf;
import java.util.Arrays;

/* loaded from: classes.dex */
final class ajk {
    final int a;
    final byte[] b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return 0 + pf.f(this.a) + this.b.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(pf pfVar) {
        pfVar.e(this.a);
        pfVar.b(this.b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ajk)) {
            return false;
        }
        ajk ajkVar = (ajk) obj;
        return this.a == ajkVar.a && Arrays.equals(this.b, ajkVar.b);
    }

    public int hashCode() {
        return ((this.a + 527) * 31) + Arrays.hashCode(this.b);
    }
}
