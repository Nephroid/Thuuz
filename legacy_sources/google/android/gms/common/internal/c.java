package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* loaded from: classes.dex */
public abstract class c implements SafeParcelable {
    private static final Object a = new Object();
    private static ClassLoader b = null;
    private static Integer c = null;
    private boolean d = false;

    private static boolean a(Class<?> cls) {
        try {
            return SafeParcelable.NULL.equals(cls.getField("NULL").get(null));
        } catch (IllegalAccessException e) {
            return false;
        } catch (NoSuchFieldException e2) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean a(String str) {
        ClassLoader b2 = b();
        if (b2 == null) {
            return true;
        }
        try {
            return a(b2.loadClass(str));
        } catch (Exception e) {
            return false;
        }
    }

    protected static ClassLoader b() {
        ClassLoader classLoader;
        synchronized (a) {
            classLoader = b;
        }
        return classLoader;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Integer c() {
        Integer num;
        synchronized (a) {
            num = c;
        }
        return num;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean d() {
        return this.d;
    }
}
