package org.joda.convert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
public final class StringConvert {
    public static final StringConvert INSTANCE = new StringConvert();
    private final ConcurrentMap<Class<?>, StringConverter<?>> registered;

    public StringConvert() {
        this(true);
    }

    public StringConvert(boolean z) {
        this.registered = new ConcurrentHashMap();
        if (z) {
            for (JDKStringConverter jDKStringConverter : JDKStringConverter.values()) {
                this.registered.put(jDKStringConverter.getType(), jDKStringConverter);
            }
        }
    }

    public <T> String convertToString(T t) {
        if (t == null) {
            return null;
        }
        return findConverter(t.getClass()).convertToString(t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T convertFromString(Class<T> cls, String str) {
        if (str == null) {
            return null;
        }
        return findConverter(cls).convertFromString(cls, str);
    }

    public <T> StringConverter<T> findConverter(Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Class must not be null");
        }
        StringConverter<T> stringConverter = (StringConverter) this.registered.get(cls);
        if (stringConverter == null) {
            if (cls == Object.class) {
                throw new IllegalStateException("No registered converter found: " + cls);
            }
            for (Class<? super T> superclass = cls.getSuperclass(); superclass != Object.class && stringConverter == null; superclass = superclass.getSuperclass()) {
                stringConverter = (StringConverter) this.registered.get(superclass);
            }
            if (stringConverter == null && (stringConverter = findAnnotationConverter(cls)) == null) {
                throw new IllegalStateException("No registered converter found: " + cls);
            }
            this.registered.putIfAbsent(cls, stringConverter);
        }
        return stringConverter;
    }

    private <T> StringConverter<T> findAnnotationConverter(Class<T> cls) {
        Method findToStringMethod = findToStringMethod(cls);
        if (findToStringMethod == null) {
            return null;
        }
        Constructor<T> findFromStringConstructor = findFromStringConstructor(cls);
        Method findFromStringMethod = findFromStringMethod(cls, findFromStringConstructor == null);
        if (findFromStringConstructor == null && findFromStringMethod == null) {
            throw new IllegalStateException("Class annotated with @ToString but not with @FromString");
        }
        if (findFromStringConstructor != null && findFromStringMethod != null) {
            throw new IllegalStateException("Both method and constructor are annotated with @FromString");
        }
        if (findFromStringConstructor != null) {
            return new MethodConstructorStringConverter(cls, findToStringMethod, findFromStringConstructor);
        }
        return new MethodsStringConverter(cls, findToStringMethod, findFromStringMethod);
    }

    private Method findToStringMethod(Class<?> cls) {
        Method method;
        Method method2 = null;
        while (cls != Object.class && method2 == null) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            int length = declaredMethods.length;
            int i = 0;
            while (i < length) {
                Method method3 = declaredMethods[i];
                if (((ToString) method3.getAnnotation(ToString.class)) == null) {
                    method = method2;
                } else {
                    if (method2 != null) {
                        throw new IllegalStateException("Two methods are annotated with @ToString");
                    }
                    method = method3;
                }
                i++;
                method2 = method;
            }
            cls = cls.getSuperclass();
        }
        return method2;
    }

    private <T> Constructor<T> findFromStringConstructor(Class<T> cls) {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(String.class);
            if (((FromString) declaredConstructor.getAnnotation(FromString.class)) != null) {
                return declaredConstructor;
            }
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private Method findFromStringMethod(Class<?> cls, boolean z) {
        Method method;
        Method method2 = null;
        while (cls != Object.class && method2 == null) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            int length = declaredMethods.length;
            int i = 0;
            while (i < length) {
                Method method3 = declaredMethods[i];
                if (((FromString) method3.getAnnotation(FromString.class)) == null) {
                    method = method2;
                } else {
                    if (method2 != null) {
                        throw new IllegalStateException("Two methods are annotated with @ToString");
                    }
                    method = method3;
                }
                i++;
                method2 = method;
            }
            if (!z) {
                break;
            }
            cls = cls.getSuperclass();
        }
        return method2;
    }

    public <T> void register(Class<T> cls, StringConverter<T> stringConverter) {
        if (cls == null) {
            throw new IllegalArgumentException("Class must not be null");
        }
        if (stringConverter == null) {
            throw new IllegalArgumentException("StringConverter must not be null");
        }
        if (this == INSTANCE) {
            throw new IllegalStateException("Global singleton cannot be extended");
        }
        if (this.registered.putIfAbsent(cls, stringConverter) != null) {
            throw new IllegalStateException("Converter already registered for class: " + cls);
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
