package org.joda.convert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
final class MethodsStringConverter<T> extends ReflectionStringConverter<T> {
    private final Method fromString;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodsStringConverter(Class<T> cls, Method method, Method method2) {
        super(cls, method);
        if (method2.getParameterTypes().length != 1) {
            throw new IllegalStateException("FromString method must have one parameter");
        }
        if (method2.getParameterTypes()[0] != String.class) {
            throw new IllegalStateException("FromString method must take a String");
        }
        if (!method2.getReturnType().isAssignableFrom(cls)) {
            throw new IllegalStateException("FromString method must return specified class or a superclass");
        }
        this.fromString = method2;
    }

    @Override // org.joda.convert.FromStringConverter
    public T convertFromString(Class<? extends T> cls, String str) {
        try {
            return cls.cast(this.fromString.invoke(null, str));
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Method is not accessible");
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e2.getCause());
            }
            throw new RuntimeException(e2.getMessage(), e2.getCause());
        }
    }
}
