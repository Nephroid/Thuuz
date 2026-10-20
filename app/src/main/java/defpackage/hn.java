package defpackage;

import android.content.Context;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.google.android.gms.common.internal.ClientSettings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class hn {
    private String a;
    private int c;
    private View d;
    private String e;
    private final Context f;
    private FragmentActivity h;
    private hp j;
    private Looper k;
    private final Set<String> b = new HashSet();
    private final Map<he<?>, hf> g = new HashMap();
    private int i = -1;
    private final Set<ho> l = new HashSet();
    private final Set<hp> m = new HashSet();

    public hn(Context context) {
        this.f = context;
        this.k = context.getMainLooper();
        this.e = context.getPackageName();
    }

    private hm c() {
        ib a = ib.a(this.h);
        hm a2 = a.a(this.i);
        if (a2 == null) {
            a2 = new hs(this.f.getApplicationContext(), this.k, a(), this.g, this.l, this.m, this.i);
        }
        a.a(this.i, a2, this.j);
        return a2;
    }

    public ClientSettings a() {
        return new ClientSettings(this.a, this.b, this.c, this.d, this.e);
    }

    public hn a(he<? extends hh> heVar) {
        this.g.put(heVar, null);
        List<hq> b = heVar.b();
        int size = b.size();
        for (int i = 0; i < size; i++) {
            this.b.add(b.get(i).a());
        }
        return this;
    }

    public hn a(ho hoVar) {
        this.l.add(hoVar);
        return this;
    }

    public hn a(hp hpVar) {
        this.m.add(hpVar);
        return this;
    }

    public hm b() {
        kb.b(!this.g.isEmpty(), "must call addApi() to add at least one API");
        return this.i >= 0 ? c() : new hs(this.f, this.k, a(), this.g, this.l, this.m, -1);
    }
}
