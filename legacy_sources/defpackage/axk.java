package defpackage;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/* loaded from: classes.dex */
public final class axk {
    private final Map<Type, awq<?>> a;

    public axk(Map<Type, awq<?>> map) {
        this.a = map;
    }

    public <T> ayg<T> a(bau<T> bauVar) {
        Type b = bauVar.b();
        Class<? super T> a = bauVar.a();
        awq<?> awqVar = this.a.get(b);
        if (awqVar != null) {
            return new axl(this, awqVar, b);
        }
        awq<?> awqVar2 = this.a.get(a);
        if (awqVar2 != null) {
            return new axp(this, awqVar2, b);
        }
        ayg<T> a2 = a(a);
        if (a2 == null) {
            ayg<T> a3 = a(b, a);
            return a3 == null ? b(b, a) : a3;
        }
        return a2;
    }

    private <T> ayg<T> a(Class<? super T> cls) {
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new axq(this, declaredConstructor);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private <T> ayg<T> a(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new axr(this);
            }
            if (EnumSet.class.isAssignableFrom(cls)) {
                return new axs(this, type);
            }
            if (Set.class.isAssignableFrom(cls)) {
                return new axt(this);
            }
            if (Queue.class.isAssignableFrom(cls)) {
                return new axu(this);
            }
            return new axv(this);
        }
        if (Map.class.isAssignableFrom(cls)) {
            if (SortedMap.class.isAssignableFrom(cls)) {
                return new axw(this);
            }
            if ((type instanceof ParameterizedType) && !String.class.isAssignableFrom(bau.a(((ParameterizedType) type).getActualTypeArguments()[0]).a())) {
                return new axm(this);
            }
            return new axn(this);
        }
        return null;
    }

    private <T> ayg<T> b(Type type, Class<? super T> cls) {
        return new axo(this, cls, type);
    }

    public String toString() {
        return this.a.toString();
    }
}
