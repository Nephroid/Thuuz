package defpackage;

import android.content.Context;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.gt;
import com.google.android.gms.internal.gv;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public class sr implements sm {
    private final gv a;

    public sr(Context context, gt gtVar) {
        this.a = gv.a(context, new ay(), false, false, null, gtVar);
    }

    private void a(Runnable runnable) {
        if (adm.b()) {
            runnable.run();
        } else {
            adm.a.post(runnable);
        }
    }

    @Override // defpackage.sm
    public void a() {
        acx.a(this.a);
    }

    @Override // defpackage.sm
    public void a(ajq ajqVar, xq xqVar, uw uwVar, xt xtVar, boolean z, vh vhVar) {
        this.a.f().a(ajqVar, xqVar, uwVar, xtVar, z, vhVar, new ajx(false));
    }

    @Override // defpackage.sm
    public void a(String str) {
        a(new st(this, str));
    }

    @Override // defpackage.sm
    public void a(String str, JSONObject jSONObject) {
        a(new ss(this, str, jSONObject));
    }

    @Override // defpackage.sm
    public void a(String str, vg vgVar) {
        this.a.f().a(str, vgVar);
    }

    @Override // defpackage.sm
    public void a(sn snVar) {
        this.a.f().a(new su(this, snVar));
    }

    @Override // defpackage.sm
    public void b() {
        acx.b(this.a);
    }

    @Override // defpackage.sm
    public void b(String str) {
        this.a.f().a(str, (vg) null);
    }

    @Override // defpackage.sm
    public void c() {
        this.a.destroy();
    }
}
