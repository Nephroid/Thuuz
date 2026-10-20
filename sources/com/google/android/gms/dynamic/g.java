package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import defpackage.hb;
import defpackage.kb;

/* loaded from: classes.dex */
public abstract class g<T> {
    private final String a;
    private T b;

    /* loaded from: classes.dex */
    public class a extends Exception {
        public a(String str) {
            super(str);
        }

        public a(String str, Throwable th) {
            super(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public g(String str) {
        this.a = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final T a(Context context) {
        if (this.b == null) {
            kb.a(context);
            Context d = hb.d(context);
            if (d == null) {
                throw new a("Could not get remote context.");
            }
            try {
                this.b = b((IBinder) d.getClassLoader().loadClass(this.a).newInstance());
            } catch (ClassNotFoundException e) {
                throw new a("Could not load creator class.", e);
            } catch (IllegalAccessException e2) {
                throw new a("Could not access creator.", e2);
            } catch (InstantiationException e3) {
                throw new a("Could not instantiate creator.", e3);
            }
        }
        return this.b;
    }

    public abstract T b(IBinder iBinder);
}
