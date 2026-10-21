package org.joda.convert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
abstract class ReflectionStringConverter<T> implements StringConverter<T> {
    final Class<T> cls;
    final Method toString;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReflectionStringConverter(Class<T> cls, Method method) {
        if (method.getParameterTypes().length != 0) {
            throw new IllegalStateException("ToString method must have no parameters");
        }
        if (method.getReturnType() != String.class) {
            throw new IllegalStateException("ToString method must return a String");
        }
        this.cls = cls;
        this.toString = method;
    }

    @Override // org.joda.convert.ToStringConverter
    public String convertToString(T t) {
        try {
            return (String) this.toString.invoke(t, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Method is not accessible");
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e2.getCause());
            }
            throw new RuntimeException(e2.getMessage(), e2.getCause());
        }
    }

    public String toString() {
        return "RefectionStringConverter[" + this.cls.getSimpleName() + "]";
    }
}
