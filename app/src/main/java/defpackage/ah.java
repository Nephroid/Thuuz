package defpackage;

import com.android.volley.VolleyError;

/* loaded from: classes.dex */
public class ah<T> {
    public final T a;
    public final v b;
    public final VolleyError c;
    public boolean d;

    public static <T> ah<T> a(T t, v vVar) {
        return new ah<>(t, vVar);
    }

    public static <T> ah<T> a(VolleyError volleyError) {
        return new ah<>(volleyError);
    }

    public boolean a() {
        return this.c == null;
    }

    private ah(T t, v vVar) {
        this.d = false;
        this.a = t;
        this.b = vVar;
        this.c = null;
    }

    private ah(VolleyError volleyError) {
        this.d = false;
        this.a = null;
        this.b = null;
        this.c = volleyError;
    }
}
