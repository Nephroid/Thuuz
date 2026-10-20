package defpackage;

import android.content.Context;

/* loaded from: classes.dex */
public abstract class aex<T> {
    private static final Object c = new Object();
    private static afb d = null;
    protected final String a;
    protected final T b;
    private T e = null;

    /* JADX INFO: Access modifiers changed from: protected */
    public aex(String str, T t) {
        this.a = str;
        this.b = t;
    }

    public static aex<Integer> a(String str, Integer num) {
        return new aez(str, num);
    }

    public static aex<String> a(String str, String str2) {
        return new afa(str, str2);
    }

    public static aex<Boolean> a(String str, boolean z) {
        return new aey(str, Boolean.valueOf(z));
    }

    public static void a(Context context) {
        synchronized (c) {
            if (d == null) {
                d = new afc(context.getContentResolver());
            }
        }
    }

    public String a() {
        return this.a;
    }
}
