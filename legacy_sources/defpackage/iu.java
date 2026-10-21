package defpackage;

import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class iu extends is {
    private WeakReference<io> e;

    @Override // defpackage.is
    protected void a(Drawable drawable, boolean z, boolean z2, boolean z3) {
        io ioVar;
        if (z2 || (ioVar = this.e.get()) == null) {
            return;
        }
        ioVar.a(this.a.a, drawable, z3);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof iu)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        iu iuVar = (iu) obj;
        io ioVar = this.e.get();
        io ioVar2 = iuVar.e.get();
        return ioVar2 != null && ioVar != null && jy.a(ioVar2, ioVar) && jy.a(iuVar.a, this.a);
    }

    public int hashCode() {
        return jy.a(this.a);
    }
}
