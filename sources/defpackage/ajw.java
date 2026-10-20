package defpackage;

import android.content.Context;
import android.view.View;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.gt;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
@zy
/* loaded from: classes.dex */
public class ajw {
    public final ajv a;
    public final String b;
    public final Context c;
    public final agc d;
    public final gt e;
    public tr f;
    public acq g;
    public acq h;
    public ay i;
    public acg j;
    public ach k;
    public acj l;
    public ua m;
    public zd n;
    public yr o;
    public zo p;
    public zr q;
    public ut r;
    public uu s;
    public List<String> t;
    public ym u;
    public aco v = null;
    public View w = null;
    public int x = 0;
    public boolean y = false;
    private HashSet<acj> z = null;

    public ajw(Context context, ay ayVar, String str, gt gtVar) {
        if (ayVar.e) {
            this.a = null;
        } else {
            this.a = new ajv(context);
            this.a.setMinimumWidth(ayVar.g);
            this.a.setMinimumHeight(ayVar.d);
            this.a.setVisibility(4);
        }
        this.i = ayVar;
        this.b = str;
        this.c = context;
        this.e = gtVar;
        this.d = new agc(new aka(this));
    }

    public HashSet<acj> a() {
        return this.z;
    }

    public void a(HashSet<acj> hashSet) {
        this.z = hashSet;
    }
}
