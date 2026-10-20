package defpackage;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.google.android.gms.tagmanager.ce;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
public class arv {
    private static arv g;
    private final asa a;
    private final Context b;
    private final arn c;
    private final ati d;
    private final ConcurrentMap<att, Boolean> e;
    private final atv f;

    arv(Context context, asa asaVar, arn arnVar, ati atiVar) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.b = context.getApplicationContext();
        this.d = atiVar;
        this.a = asaVar;
        this.e = new ConcurrentHashMap();
        this.c = arnVar;
        this.c.a(new arw(this));
        this.c.a(new ato(this.b));
        this.f = new atv();
        b();
    }

    public static arv a(Context context) {
        arv arvVar;
        synchronized (arv.class) {
            if (g == null) {
                if (context == null) {
                    asj.a("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
                g = new arv(context, new arx(), new arn(new atx(context)), atj.b());
            }
            arvVar = g;
        }
        return arvVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        Iterator<att> it = this.e.keySet().iterator();
        while (it.hasNext()) {
            it.next().a(str);
        }
    }

    private void b() {
        if (Build.VERSION.SDK_INT >= 14) {
            this.b.registerComponentCallbacks(new ary(this));
        }
    }

    public void a() {
        this.d.a();
    }

    public synchronized boolean a(Uri uri) {
        boolean z;
        ce a = ce.a();
        if (a.a(uri)) {
            String d = a.d();
            switch (arz.a[a.b().ordinal()]) {
                case 1:
                    for (att attVar : this.e.keySet()) {
                        if (attVar.b().equals(d)) {
                            attVar.b(null);
                            attVar.a();
                        }
                    }
                    break;
                case 2:
                case 3:
                    for (att attVar2 : this.e.keySet()) {
                        if (attVar2.b().equals(d)) {
                            attVar2.b(a.c());
                            attVar2.a();
                        } else if (attVar2.c() != null) {
                            attVar2.b(null);
                            attVar2.a();
                        }
                    }
                    break;
            }
            z = true;
        } else {
            z = false;
        }
        return z;
    }
}
