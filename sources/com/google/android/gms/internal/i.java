package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.google.android.gms.internal.o;
import dalvik.system.DexClassLoader;
import defpackage.aed;
import defpackage.aht;
import defpackage.aik;
import defpackage.ajm;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public abstract class i extends aed {
    private static Method e;
    private static Method f;
    private static Method g;
    private static Method h;
    private static Method i;
    private static Method j;
    private static Method k;
    private static Method l;
    private static Method m;
    private static String n;
    private static String o;
    private static String p;
    private static o r;
    private static long q = 0;
    static boolean d = false;

    /* loaded from: classes.dex */
    public class a extends Exception {
        public a() {
        }

        public a(Throwable th) {
            super(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public i(Context context, aht ahtVar, aik aikVar) {
        super(context, ahtVar, aikVar);
    }

    static String a() {
        if (n == null) {
            throw new a();
        }
        return n;
    }

    static String a(Context context, aht ahtVar) {
        if (o != null) {
            return o;
        }
        if (h == null) {
            throw new a();
        }
        try {
            ByteBuffer byteBuffer = (ByteBuffer) h.invoke(null, context);
            if (byteBuffer == null) {
                throw new a();
            }
            o = ahtVar.a(byteBuffer.array(), true);
            return o;
        } catch (IllegalAccessException e2) {
            throw new a(e2);
        } catch (InvocationTargetException e3) {
            throw new a(e3);
        }
    }

    static ArrayList<Long> a(MotionEvent motionEvent, DisplayMetrics displayMetrics) {
        if (i == null || motionEvent == null) {
            throw new a();
        }
        try {
            return (ArrayList) i.invoke(null, motionEvent, displayMetrics);
        } catch (IllegalAccessException e2) {
            throw new a(e2);
        } catch (InvocationTargetException e3) {
            throw new a(e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static synchronized void a(String str, Context context, aht ahtVar) {
        synchronized (i.class) {
            if (!d) {
                try {
                    r = new o(ahtVar, null);
                    n = str;
                    g(context);
                    q = b().longValue();
                    d = true;
                } catch (a e2) {
                } catch (UnsupportedOperationException e3) {
                }
            }
        }
    }

    static Long b() {
        if (e == null) {
            throw new a();
        }
        try {
            return (Long) e.invoke(null, new Object[0]);
        } catch (IllegalAccessException e2) {
            throw new a(e2);
        } catch (InvocationTargetException e3) {
            throw new a(e3);
        }
    }

    static String b(Context context, aht ahtVar) {
        if (p != null) {
            return p;
        }
        if (k == null) {
            throw new a();
        }
        try {
            ByteBuffer byteBuffer = (ByteBuffer) k.invoke(null, context);
            if (byteBuffer == null) {
                throw new a();
            }
            p = ahtVar.a(byteBuffer.array(), true);
            return p;
        } catch (IllegalAccessException e2) {
            throw new a(e2);
        } catch (InvocationTargetException e3) {
            throw new a(e3);
        }
    }

    private static String b(byte[] bArr, String str) {
        try {
            return new String(r.a(bArr, str), HTTP.UTF_8);
        } catch (o.a e2) {
            throw new a(e2);
        } catch (UnsupportedEncodingException e3) {
            throw new a(e3);
        }
    }

    static String c() {
        if (g == null) {
            throw new a();
        }
        try {
            return (String) g.invoke(null, new Object[0]);
        } catch (IllegalAccessException e2) {
            throw new a(e2);
        } catch (InvocationTargetException e3) {
            throw new a(e3);
        }
    }

    static Long d() {
        if (f == null) {
            throw new a();
        }
        try {
            return (Long) f.invoke(null, new Object[0]);
        } catch (IllegalAccessException e2) {
            throw new a(e2);
        } catch (InvocationTargetException e3) {
            throw new a(e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String d(Context context) {
        if (j == null) {
            throw new a();
        }
        try {
            String str = (String) j.invoke(null, context);
            if (str == null) {
                throw new a();
            }
            return str;
        } catch (IllegalAccessException e2) {
            throw new a(e2);
        } catch (InvocationTargetException e3) {
            throw new a(e3);
        }
    }

    static ArrayList<Long> e(Context context) {
        if (l == null) {
            throw new a();
        }
        try {
            ArrayList<Long> arrayList = (ArrayList) l.invoke(null, context);
            if (arrayList == null || arrayList.size() != 2) {
                throw new a();
            }
            return arrayList;
        } catch (IllegalAccessException e2) {
            throw new a(e2);
        } catch (InvocationTargetException e3) {
            throw new a(e3);
        }
    }

    static int[] f(Context context) {
        if (m == null) {
            throw new a();
        }
        try {
            return (int[]) m.invoke(null, context);
        } catch (IllegalAccessException e2) {
            throw new a(e2);
        } catch (InvocationTargetException e3) {
            throw new a(e3);
        }
    }

    private static void g(Context context) {
        try {
            byte[] a2 = r.a(ajm.a());
            byte[] a3 = r.a(a2, ajm.b());
            File cacheDir = context.getCacheDir();
            if (cacheDir == null && (cacheDir = context.getDir("dex", 0)) == null) {
                throw new a();
            }
            File file = cacheDir;
            File createTempFile = File.createTempFile("ads", ".jar", file);
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            fileOutputStream.write(a3, 0, a3.length);
            fileOutputStream.close();
            try {
                DexClassLoader dexClassLoader = new DexClassLoader(createTempFile.getAbsolutePath(), file.getAbsolutePath(), null, context.getClassLoader());
                Class loadClass = dexClassLoader.loadClass(b(a2, ajm.e()));
                Class loadClass2 = dexClassLoader.loadClass(b(a2, ajm.q()));
                Class loadClass3 = dexClassLoader.loadClass(b(a2, ajm.k()));
                Class loadClass4 = dexClassLoader.loadClass(b(a2, ajm.i()));
                Class loadClass5 = dexClassLoader.loadClass(b(a2, ajm.s()));
                Class loadClass6 = dexClassLoader.loadClass(b(a2, ajm.g()));
                Class loadClass7 = dexClassLoader.loadClass(b(a2, ajm.o()));
                Class loadClass8 = dexClassLoader.loadClass(b(a2, ajm.m()));
                Class loadClass9 = dexClassLoader.loadClass(b(a2, ajm.c()));
                e = loadClass.getMethod(b(a2, ajm.f()), new Class[0]);
                f = loadClass2.getMethod(b(a2, ajm.r()), new Class[0]);
                g = loadClass3.getMethod(b(a2, ajm.l()), new Class[0]);
                h = loadClass4.getMethod(b(a2, ajm.j()), Context.class);
                i = loadClass5.getMethod(b(a2, ajm.t()), MotionEvent.class, DisplayMetrics.class);
                j = loadClass6.getMethod(b(a2, ajm.h()), Context.class);
                k = loadClass7.getMethod(b(a2, ajm.p()), Context.class);
                l = loadClass8.getMethod(b(a2, ajm.n()), Context.class);
                m = loadClass9.getMethod(b(a2, ajm.d()), Context.class);
            } finally {
                String name = createTempFile.getName();
                createTempFile.delete();
                new File(file, name.replace(".jar", ".dex")).delete();
            }
        } catch (o.a e2) {
            throw new a(e2);
        } catch (FileNotFoundException e3) {
            throw new a(e3);
        } catch (IOException e4) {
            throw new a(e4);
        } catch (ClassNotFoundException e5) {
            throw new a(e5);
        } catch (NoSuchMethodException e6) {
            throw new a(e6);
        } catch (NullPointerException e7) {
            throw new a(e7);
        }
    }

    @Override // defpackage.aed
    public void b(Context context) {
        try {
            try {
                a(1, c());
            } catch (a e2) {
            }
            try {
                a(2, a());
            } catch (a e3) {
            }
            try {
                long longValue = b().longValue();
                a(25, longValue);
                if (q != 0) {
                    a(17, longValue - q);
                    a(23, q);
                }
            } catch (a e4) {
            }
            try {
                ArrayList<Long> e5 = e(context);
                a(31, e5.get(0).longValue());
                a(32, e5.get(1).longValue());
            } catch (a e6) {
            }
            try {
                a(33, d().longValue());
            } catch (a e7) {
            }
            try {
                a(27, a(context, this.c));
            } catch (a e8) {
            }
            try {
                a(29, b(context, this.c));
            } catch (a e9) {
            }
            try {
                int[] f2 = f(context);
                a(5, f2[0]);
                a(6, f2[1]);
            } catch (a e10) {
            }
        } catch (IOException e11) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.aed
    public void c(Context context) {
        try {
            try {
                a(2, a());
            } catch (a e2) {
            }
            try {
                a(1, c());
            } catch (a e3) {
            }
            try {
                a(25, b().longValue());
            } catch (a e4) {
            }
            try {
                ArrayList<Long> a2 = a(this.a, this.b);
                a(14, a2.get(0).longValue());
                a(15, a2.get(1).longValue());
                if (a2.size() >= 3) {
                    a(16, a2.get(2).longValue());
                }
            } catch (a e5) {
            }
        } catch (IOException e6) {
        }
    }
}
