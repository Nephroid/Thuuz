package defpackage;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.internal.gt;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

@zy
/* loaded from: classes.dex */
public class acl implements vp {
    private Context l;
    private gt m;
    private String v;
    private static final acl c = new acl();
    public static final String a = c.b;
    private final Object d = new Object();
    private BigInteger f = BigInteger.ONE;
    private final HashSet<acj> g = new HashSet<>();
    private final HashMap<String, aco> h = new HashMap<>();
    private boolean i = false;
    private boolean j = true;
    private boolean k = false;
    private boolean n = true;
    private sx o = null;
    private sy p = null;
    private sw q = null;
    private LinkedList<Thread> r = new LinkedList<>();
    private boolean s = false;
    private Bundle t = uk.a();
    private zx u = null;
    public final String b = acx.e();
    private final acm e = new acm(this.b);

    private acl() {
    }

    public static acl a() {
        return c;
    }

    public static Bundle a(Context context, acn acnVar, String str) {
        return c.b(context, acnVar, str);
    }

    public static String a(int i, String str) {
        return c.b(i, str);
    }

    public static void a(Context context, gt gtVar) {
        c.b(context, gtVar);
    }

    public static void a(Context context, boolean z) {
        c.b(context, z);
    }

    public static void a(Throwable th) {
        c.b(th);
    }

    public static void a(HashSet<acj> hashSet) {
        c.b(hashSet);
    }

    public static String c() {
        return c.d();
    }

    public static acm e() {
        return c.f();
    }

    public static boolean g() {
        return c.h();
    }

    public static boolean i() {
        return c.j();
    }

    public static String k() {
        return c.l();
    }

    public static Bundle m() {
        return c.n();
    }

    public sy a(Context context) {
        sy syVar = null;
        if (m().getBoolean(uk.g.a(), false) && agf.c() && !b()) {
            synchronized (this.d) {
                if (this.o == null) {
                    if (context instanceof Activity) {
                        this.o = new sx((Application) context.getApplicationContext(), (Activity) context);
                    }
                }
                if (this.q == null) {
                    this.q = new sw();
                }
                if (this.p == null) {
                    this.p = new sy(this.o, this.q, this.t, new zx(this.l, this.m, null, null));
                }
                this.p.a();
                syVar = this.p;
            }
        }
        return syVar;
    }

    public void a(acj acjVar) {
        synchronized (this.d) {
            this.g.add(acjVar);
        }
    }

    @Override // defpackage.vp
    public void a(Bundle bundle) {
        synchronized (this.d) {
            this.s = true;
            this.t = bundle;
            while (!this.r.isEmpty()) {
                zx.a(this.l, this.r.remove(0), this.m);
            }
        }
    }

    public void a(String str, aco acoVar) {
        synchronized (this.d) {
            this.h.put(str, acoVar);
        }
    }

    public void a(Thread thread) {
        synchronized (this.d) {
            if (this.s) {
                zx.a(this.l, thread, this.m);
            } else {
                this.r.add(thread);
            }
        }
    }

    public void a(boolean z) {
        synchronized (this.d) {
            this.n = z;
        }
    }

    public Bundle b(Context context, acn acnVar, String str) {
        Bundle bundle;
        synchronized (this.d) {
            bundle = new Bundle();
            bundle.putBundle("app", this.e.a(context, str));
            Bundle bundle2 = new Bundle();
            for (String str2 : this.h.keySet()) {
                bundle2.putBundle(str2, this.h.get(str2).a());
            }
            bundle.putBundle("slots", bundle2);
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            Iterator<acj> it = this.g.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().d());
            }
            bundle.putParcelableArrayList("ads", arrayList);
            acnVar.a(this.g);
            this.g.clear();
        }
        return bundle;
    }

    public String b(int i, String str) {
        Resources resources = this.m.e ? this.l.getResources() : hb.c(this.l);
        return resources == null ? str : resources.getString(i);
    }

    public void b(Context context, gt gtVar) {
        synchronized (this.d) {
            if (!this.k) {
                this.l = context.getApplicationContext();
                this.m = gtVar;
                this.j = acs.a(context);
                aex.a(context);
                vn.a(context, this);
                a(Thread.currentThread());
                this.v = acx.a(context, gtVar.b);
                this.k = true;
            }
        }
    }

    public void b(Context context, boolean z) {
        synchronized (this.d) {
            if (z != this.j) {
                this.j = z;
                acs.a(context, z);
            }
        }
    }

    public void b(Throwable th) {
        if (this.k) {
            new zx(this.l, this.m, null, null).b(th);
        }
    }

    public void b(HashSet<acj> hashSet) {
        synchronized (this.d) {
            this.g.addAll(hashSet);
        }
    }

    public boolean b() {
        boolean z;
        synchronized (this.d) {
            z = this.n;
        }
        return z;
    }

    public String d() {
        String bigInteger;
        synchronized (this.d) {
            bigInteger = this.f.toString();
            this.f = this.f.add(BigInteger.ONE);
        }
        return bigInteger;
    }

    public acm f() {
        acm acmVar;
        synchronized (this.d) {
            acmVar = this.e;
        }
        return acmVar;
    }

    public boolean h() {
        boolean z;
        synchronized (this.d) {
            z = this.i;
            this.i = true;
        }
        return z;
    }

    public boolean j() {
        boolean z;
        synchronized (this.d) {
            z = this.j;
        }
        return z;
    }

    public String l() {
        String str;
        synchronized (this.d) {
            str = this.v;
        }
        return str;
    }

    public Bundle n() {
        Bundle bundle;
        synchronized (this.d) {
            bundle = this.t;
        }
        return bundle;
    }
}
