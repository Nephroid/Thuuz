package defpackage;

/* loaded from: classes.dex */
public final class jf implements hp {
    private final ha a;

    public jf(ha haVar) {
        this.a = haVar;
    }

    @Override // defpackage.hp, defpackage.ha
    public void a(gw gwVar) {
        this.a.a(gwVar);
    }

    public boolean equals(Object obj) {
        return obj instanceof jf ? this.a.equals(((jf) obj).a) : this.a.equals(obj);
    }
}
