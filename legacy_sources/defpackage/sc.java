package defpackage;

import android.content.Context;
import android.view.View;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.gt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

@zy
/* loaded from: classes.dex */
public final class sc implements sl {
    private final Object a = new Object();
    private final WeakHashMap<acg, sd> b = new WeakHashMap<>();
    private final ArrayList<sd> c = new ArrayList<>();

    public sd a(Context context, ay ayVar, acg acgVar, View view, gt gtVar) {
        sd sdVar;
        synchronized (this.a) {
            if (a(acgVar)) {
                sdVar = this.b.get(acgVar);
            } else {
                sdVar = new sd(context, ayVar, acgVar, view, gtVar);
                sdVar.a(this);
                this.b.put(acgVar, sdVar);
                this.c.add(sdVar);
            }
        }
        return sdVar;
    }

    public sd a(ay ayVar, acg acgVar) {
        return a(acgVar.b.getContext(), ayVar, acgVar, acgVar.b, acgVar.b.i());
    }

    public void a() {
        synchronized (this.a) {
            Iterator<sd> it = this.c.iterator();
            while (it.hasNext()) {
                it.next().k();
            }
        }
    }

    @Override // defpackage.sl
    public void a(sd sdVar) {
        synchronized (this.a) {
            if (!sdVar.f()) {
                this.c.remove(sdVar);
            }
        }
    }

    public boolean a(acg acgVar) {
        boolean z;
        synchronized (this.a) {
            sd sdVar = this.b.get(acgVar);
            z = sdVar != null && sdVar.f();
        }
        return z;
    }

    public void b() {
        synchronized (this.a) {
            Iterator<sd> it = this.c.iterator();
            while (it.hasNext()) {
                it.next().l();
            }
        }
    }

    public void b(acg acgVar) {
        synchronized (this.a) {
            sd sdVar = this.b.get(acgVar);
            if (sdVar != null) {
                sdVar.d();
            }
        }
    }

    public void c() {
        synchronized (this.a) {
            Iterator<sd> it = this.c.iterator();
            while (it.hasNext()) {
                it.next().m();
            }
        }
    }
}
