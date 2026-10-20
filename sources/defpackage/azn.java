package defpackage;

/* loaded from: classes.dex */
final class azn extends axd<Class> {
    @Override // defpackage.axd
    public void a(bav bavVar, Class cls) {
        if (cls == null) {
            bavVar.f();
            return;
        }
        throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
    }
}
