package defpackage;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.AdView;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public final class co implements dn, dp {
    private AdView a;
    private dc b;

    static cy a(Context context, dl dlVar, Bundle bundle, Bundle bundle2) {
        da daVar = new da();
        Date a = dlVar.a();
        if (a != null) {
            daVar.a(a);
        }
        int b = dlVar.b();
        if (b != 0) {
            daVar.a(b);
        }
        Set<String> c = dlVar.c();
        if (c != null) {
            Iterator<String> it = c.iterator();
            while (it.hasNext()) {
                daVar.a(it.next());
            }
        }
        Location d = dlVar.d();
        if (d != null) {
            daVar.a(d);
        }
        if (dlVar.e()) {
            daVar.b(adm.a(context));
        }
        if (bundle2.getInt("tagForChildDirectedTreatment") != -1) {
            daVar.a(bundle2.getInt("tagForChildDirectedTreatment") == 1);
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt("gw", 1);
        bundle.putString("mad_hac", bundle2.getString("mad_hac"));
        if (!TextUtils.isEmpty(bundle2.getString("adJson"))) {
            bundle.putString("_ad", bundle2.getString("adJson"));
        }
        bundle.putBoolean("_noRefresh", true);
        daVar.a(co.class, bundle);
        return daVar.a();
    }

    @Override // defpackage.dm
    public void a() {
        if (this.a != null) {
            this.a.a();
            this.a = null;
        }
        if (this.b != null) {
            this.b = null;
        }
    }

    @Override // defpackage.dn
    public void a(Context context, InterfaceC0005do interfaceC0005do, Bundle bundle, db dbVar, dl dlVar, Bundle bundle2) {
        this.a = new AdView(context);
        this.a.setAdSize(new db(dbVar.b(), dbVar.a()));
        this.a.setAdUnitId(bundle.getString("pubid"));
        this.a.setAdListener(new cp(this, interfaceC0005do));
        this.a.a(a(context, dlVar, bundle2, bundle));
    }

    @Override // defpackage.dp
    public void a(Context context, dq dqVar, Bundle bundle, dl dlVar, Bundle bundle2) {
        this.b = new dc(context);
        this.b.a(bundle.getString("pubid"));
        this.b.a(new cq(this, dqVar));
        this.b.a(a(context, dlVar, bundle2, bundle));
    }

    @Override // defpackage.dm
    public void b() {
        if (this.a != null) {
            this.a.b();
        }
    }

    @Override // defpackage.dm
    public void c() {
        if (this.a != null) {
            this.a.c();
        }
    }

    @Override // defpackage.dn
    public View d() {
        return this.a;
    }

    @Override // defpackage.dp
    public void e() {
        this.b.a();
    }
}
