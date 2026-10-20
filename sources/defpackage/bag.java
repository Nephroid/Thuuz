package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class bag implements axe {
    @Override // defpackage.axe
    public <T> axd<T> a(awi awiVar, bau<T> bauVar) {
        Class<? super T> a = bauVar.a();
        if (!Enum.class.isAssignableFrom(a) || a == Enum.class) {
            return null;
        }
        if (!a.isEnum()) {
            a = a.getSuperclass();
        }
        return new bat(a);
    }
}
