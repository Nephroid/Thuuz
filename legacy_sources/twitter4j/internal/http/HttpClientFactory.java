package twitter4j.internal.http;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public final class HttpClientFactory {
    private static final Constructor HTTP_CLIENT_CONSTRUCTOR;
    private static final String HTTP_CLIENT_IMPLEMENTATION = "twitter4j.http.httpClient";

    static {
        Class<?> cls = null;
        String property = System.getProperty(HTTP_CLIENT_IMPLEMENTATION);
        if (property != null) {
            try {
                cls = Class.forName(property);
            } catch (ClassNotFoundException e) {
            }
        }
        if (cls == null) {
            try {
                cls = Class.forName("twitter4j.internal.http.alternative.HttpClientImpl");
            } catch (ClassNotFoundException e2) {
            }
        }
        if (cls == null) {
            try {
                cls = Class.forName("twitter4j.internal.http.HttpClientImpl");
            } catch (ClassNotFoundException e3) {
                throw new AssertionError(e3);
            }
        }
        try {
            HTTP_CLIENT_CONSTRUCTOR = cls.getConstructor(HttpClientConfiguration.class);
        } catch (NoSuchMethodException e4) {
            throw new AssertionError(e4);
        }
    }

    public static HttpClient getInstance(HttpClientConfiguration httpClientConfiguration) {
        try {
            return (HttpClient) HTTP_CLIENT_CONSTRUCTOR.newInstance(httpClientConfiguration);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new AssertionError(e2);
        } catch (InvocationTargetException e3) {
            throw new AssertionError(e3);
        }
    }
}
