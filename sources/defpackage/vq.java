package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;

@zy
/* loaded from: classes.dex */
public class vq extends iz<vr> {
    final int d;

    public vq(Context context, gz gzVar, ha haVar, int i) {
        super(context, gzVar, haVar, new String[0]);
        this.d = i;
    }

    @Override // defpackage.iz
    protected void a(js jsVar, jd jdVar) {
        jsVar.g(jdVar, this.d, i().getPackageName(), new Bundle());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.iz
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public vr a(IBinder iBinder) {
        return vs.a(iBinder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.iz
    public String d() {
        return "com.google.android.gms.ads.gservice.START";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.iz
    public String e() {
        return "com.google.android.gms.ads.internal.gservice.IGservicesValueService";
    }

    public vr l() {
        return (vr) super.k();
    }
}
