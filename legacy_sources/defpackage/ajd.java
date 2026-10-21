package defpackage;

import com.google.android.gms.internal.pf;
import defpackage.ajd;

/* loaded from: classes.dex */
public abstract class ajd<M extends ajd<M>> extends aji {
    protected ajf m;

    @Override // defpackage.aji
    public void a(pf pfVar) {
        if (this.m == null) {
            return;
        }
        for (int i = 0; i < this.m.a(); i++) {
            this.m.a(i).a(pfVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean a(M m) {
        return (this.m == null || this.m.b()) ? m.m == null || m.m.b() : this.m.equals(m.m);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.aji
    public int b() {
        if (this.m == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.m.a(); i2++) {
            i += this.m.a(i2).a();
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int d() {
        if (this.m == null || this.m.b()) {
            return 0;
        }
        return this.m.hashCode();
    }
}
