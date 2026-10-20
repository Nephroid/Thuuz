package defpackage;

import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.Type;
import java.util.Map;

/* loaded from: classes.dex */
public final class ayz implements axe {
    private final axk a;
    private final boolean b;

    public ayz(axk axkVar, boolean z) {
        this.a = axkVar;
        this.b = z;
    }

    @Override // defpackage.axe
    public <T> axd<T> a(awi awiVar, bau<T> bauVar) {
        Type b = bauVar.b();
        if (!Map.class.isAssignableFrom(bauVar.a())) {
            return null;
        }
        Type[] b2 = C$Gson$Types.b(b, C$Gson$Types.e(b));
        return new aza(this, awiVar, b2[0], a(awiVar, b2[0]), b2[1], awiVar.a((bau) bau.a(b2[1])), this.a.a(bauVar));
    }

    private axd<?> a(awi awiVar, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? azm.f : awiVar.a((bau) bau.a(type));
    }
}
