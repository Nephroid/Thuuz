package defpackage;

import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
final class ays implements axe {
    @Override // defpackage.axe
    public <T> axd<T> a(awi awiVar, bau<T> bauVar) {
        Type b = bauVar.b();
        if (!(b instanceof GenericArrayType) && (!(b instanceof Class) || !((Class) b).isArray())) {
            return null;
        }
        Type g = C$Gson$Types.g(b);
        return new ayr(awiVar, awiVar.a((bau) bau.a(g)), C$Gson$Types.e(g));
    }
}
