package defpackage;

import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* loaded from: classes.dex */
public class aof {
    private static Context a;
    private static amg b;

    public static amg a(Context context) {
        kb.a(context);
        if (b != null) {
            return b;
        }
        b(context);
        b = c(context);
        try {
            b.a(og.a(d(context).getResources()), 6111000);
            return b;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    private static <T> T a(Class<?> cls) {
        try {
            return (T) cls.newInstance();
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unable to call the default constructor of " + cls.getName());
        } catch (InstantiationException e2) {
            throw new IllegalStateException("Unable to instantiate the dynamic class " + cls.getName());
        }
    }

    private static <T> T a(ClassLoader classLoader, String str) {
        try {
            return (T) a(((ClassLoader) kb.a(classLoader)).loadClass(str));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Unable to find dynamic class " + str);
        }
    }

    private static boolean a() {
        return false;
    }

    private static Class<?> b() {
        try {
            return Build.VERSION.SDK_INT < 15 ? Class.forName("com.google.android.gms.maps.internal.CreatorImplGmm6") : Class.forName("com.google.android.gms.maps.internal.CreatorImpl");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void b(Context context) {
        int a2 = hb.a(context);
        switch (a2) {
            case 0:
                return;
            default:
                throw new GooglePlayServicesNotAvailableException(a2);
        }
    }

    private static amg c(Context context) {
        if (a()) {
            Log.i(aof.class.getSimpleName(), "Making Creator statically");
            return (amg) a(b());
        }
        Log.i(aof.class.getSimpleName(), "Making Creator dynamically");
        return amh.a((IBinder) a(d(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl"));
    }

    private static Context d(Context context) {
        if (a == null) {
            if (a()) {
                a = context.getApplicationContext();
            } else {
                a = hb.d(context);
            }
        }
        return a;
    }
}
