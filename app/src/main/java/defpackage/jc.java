package defpackage;

import android.os.Bundle;

/* loaded from: classes.dex */
public final class jc implements ho {
    private final gz a;

    public jc(gz gzVar) {
        this.a = gzVar;
    }

    @Override // defpackage.ho
    public void a(int i) {
        this.a.C_();
    }

    @Override // defpackage.ho
    public void a(Bundle bundle) {
        this.a.a(bundle);
    }

    public boolean equals(Object obj) {
        return obj instanceof jc ? this.a.equals(((jc) obj).a) : this.a.equals(obj);
    }
}
