package defpackage;

import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.Type;
import java.util.Collection;

/* loaded from: classes.dex */
public final class ayt implements axe {
    private final axk a;

    public ayt(axk axkVar) {
        this.a = axkVar;
    }

    @Override // defpackage.axe
    public <T> axd<T> a(awi awiVar, bau<T> bauVar) {
        Type b = bauVar.b();
        Class<? super T> a = bauVar.a();
        if (!Collection.class.isAssignableFrom(a)) {
            return null;
        }
        Type a2 = C$Gson$Types.a(b, (Class<?>) a);
        return new ayu(awiVar, a2, awiVar.a((bau) bau.a(a2)), this.a.a(bauVar));
    }
}
