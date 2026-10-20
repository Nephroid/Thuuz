package defpackage;

/* loaded from: classes.dex */
class axc implements axe {
    private final bau<?> a;
    private final boolean b;
    private final Class<?> c;
    private final awz<?> d;
    private final awt<?> e;

    private axc(Object obj, bau<?> bauVar, boolean z, Class<?> cls) {
        this.d = obj instanceof awz ? (awz) obj : null;
        this.e = obj instanceof awt ? (awt) obj : null;
        axj.a((this.d == null && this.e == null) ? false : true);
        this.a = bauVar;
        this.b = z;
        this.c = cls;
    }

    @Override // defpackage.axe
    public <T> axd<T> a(awi awiVar, bau<T> bauVar) {
        boolean isAssignableFrom;
        axb axbVar = null;
        if (this.a != null) {
            isAssignableFrom = this.a.equals(bauVar) || (this.b && this.a.b() == bauVar.a());
        } else {
            isAssignableFrom = this.c.isAssignableFrom(bauVar.a());
        }
        if (isAssignableFrom) {
            return new axa(this.d, this.e, awiVar, bauVar, this);
        }
        return null;
    }
}
