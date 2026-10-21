package twitter4j.internal.json;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import twitter4j.json.DataObjectFactory;

/* loaded from: classes.dex */
public class DataObjectFactoryUtil {
    private static final Method CLEAR_THREAD_LOCAL_MAP;
    private static final Method REGISTER_JSON_OBJECT;

    private DataObjectFactoryUtil() {
        throw new AssertionError("not intended to be instantiated.");
    }

    static {
        Method[] declaredMethods = DataObjectFactory.class.getDeclaredMethods();
        int length = declaredMethods.length;
        int i = 0;
        Method method = null;
        Method method2 = null;
        while (i < length) {
            Method method3 = declaredMethods[i];
            if (method3.getName().equals("clearThreadLocalMap")) {
                method3.setAccessible(true);
            } else if (method3.getName().equals("registerJSONObject")) {
                method3.setAccessible(true);
                method2 = method3;
                method3 = method;
            } else {
                method3 = method;
            }
            i++;
            method = method3;
        }
        if (method == null || method2 == null) {
            throw new AssertionError();
        }
        CLEAR_THREAD_LOCAL_MAP = method;
        REGISTER_JSON_OBJECT = method2;
    }

    public static void clearThreadLocalMap() {
        try {
            CLEAR_THREAD_LOCAL_MAP.invoke(null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e2) {
            throw new AssertionError(e2);
        }
    }

    public static <T> T registerJSONObject(T t, Object obj) {
        try {
            return (T) REGISTER_JSON_OBJECT.invoke(null, t, obj);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e2) {
            throw new AssertionError(e2);
        }
    }
}
