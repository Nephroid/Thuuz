package defpackage;

import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class bau<T> {
    final Class<? super T> a;
    final Type b;
    final int c;

    protected bau() {
        this.b = a(getClass());
        this.a = (Class<? super T>) C$Gson$Types.e(this.b);
        this.c = this.b.hashCode();
    }

    bau(Type type) {
        this.b = C$Gson$Types.d((Type) axj.a(type));
        this.a = (Class<? super T>) C$Gson$Types.e(this.b);
        this.c = this.b.hashCode();
    }

    static Type a(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return C$Gson$Types.d(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public final Class<? super T> a() {
        return this.a;
    }

    public final Type b() {
        return this.b;
    }

    public final int hashCode() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof bau) && C$Gson$Types.a(this.b, ((bau) obj).b);
    }

    public final String toString() {
        return C$Gson$Types.f(this.b);
    }

    public static bau<?> a(Type type) {
        return new bau<>(type);
    }

    public static <T> bau<T> b(Class<T> cls) {
        return new bau<>(cls);
    }
}
