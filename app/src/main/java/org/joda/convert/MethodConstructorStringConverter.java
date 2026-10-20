package org.joda.convert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* loaded from: classes.dex */
final class MethodConstructorStringConverter<T> extends ReflectionStringConverter<T> {
    private final Constructor<T> fromString;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodConstructorStringConverter(Class<T> cls, Method method, Constructor<T> constructor) {
        super(cls, method);
        if (cls.isInterface() || Modifier.isAbstract(cls.getModifiers()) || cls.isLocalClass() || cls.isMemberClass()) {
            throw new IllegalArgumentException("FromString constructor must be on an instantiable class");
        }
        if (constructor.getDeclaringClass() != cls) {
            throw new IllegalStateException("FromString constructor must be defined on specified class");
        }
        this.fromString = constructor;
    }

    @Override // org.joda.convert.FromStringConverter
    public T convertFromString(Class<? extends T> cls, String str) {
        try {
            return this.fromString.newInstance(str);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Constructor is not accessible");
        } catch (InstantiationException e2) {
            throw new IllegalStateException("Constructor is not valid");
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e3.getCause());
            }
            throw new RuntimeException(e3.getMessage(), e3.getCause());
        }
    }
}
